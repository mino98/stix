package uk.ac.ed.inf.wimo.stix.agent.communication;

import static uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil.docToString;
import static uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil.parse;
import static uk.ac.ed.inf.wimo.stix.agent.util.XmlUtil.removeWhitespaceNodes;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import uk.ac.ed.inf.wimo.stix.agent.Agent;
import uk.ac.ed.inf.wimo.stix.agent.engine.Engine;
import uk.ac.ed.inf.wimo.stix.agent.log.LogManager;
import uk.ac.ed.inf.wimo.stix.agent.log.LogQueryRunnable;
import uk.ac.ed.inf.wimo.stix.agent.workflow.WorkflowManager;


/**
 * Connects with other agents via SOAP messages. It listen on a
 * network socket for incoming messages and subsequently
 * dispatches them to the appropriate component
 *  
 * @author matt
 *
 */
public class CommunicationManager implements Runnable {

	private final static Logger log = Logger.getLogger(CommunicationManager.class);
	
	public final static String NEIGHBORS_PROP = "/neighbors.properties";
	public final static String AGENT_PROP = "/agent.properties";
	
	protected ServerSocket serverSocket;
	protected String agentId;
	protected boolean isRunning = false;
	protected int mPort = Agent.getDefaultPort();	
	protected int SOCKET_TIMEOUT = 200;
	protected Map<String, String> neighborTable;
	protected int remotePort = 0;
	
	private CommunicationManager(){
		
//		/* load some properties for the agent */
//		InputStream is = CommunicationManager.class.getResourceAsStream(AGENT_PROP);
//		
//		try {
//			Properties prop = new Properties();
//			prop.load(is);
//		
//			agentId = prop.getProperty("agentid");
//		} catch (IOException e) {
//			log.error(e);
//		}
		
		agentId = Agent.getAgentId();
	}
	
	private CommunicationManager(int port){
		this();
		this.mPort = port;
	}
	
	public void run() {
		
		try {
			serverSocket = new ServerSocket(mPort);
			serverSocket.setSoTimeout(SOCKET_TIMEOUT);
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
			return;
		}
		
		isRunning = true;
		
		//there is a race condition here
		while(isRunning){        
			
			Socket socket = null;
			//boolean handleSocket = false;
			
			try {
				socket = serverSocket.accept();
				
				Runnable socketRunner = new SocketThread(socket);
				new Thread(socketRunner).start();
				
//				InputStream is = socket.getInputStream();
//				
//				//TODO probably move all this processing into new thread
//				Document doc = null;
//				try {
//					doc = parse(is);
//				} catch (SAXException e) {
//					log.error("There was an error processing the XML message");
//					log.error(e);
//					continue;
//				}
//				
//				boolean isValid = true; //validate(doc, "xml/Message.xsd");
//				if(!isValid){
//					log.error("Received message xml was invalid...abandoning.");
//					continue;
//				}
//				
//				String xmlString = docToString(doc);
//				log.info("received message from destination "+socket.getRemoteSocketAddress().toString());
//				log.info(xmlString);
//				
//				//lolh4x!!
//				removeWhitespaceNodes(doc.getDocumentElement());
//				
//				//get Message object and dispatch to appropriate module
//				Message message = Message.toObject(doc);
//				
//				delegateMessage(message, socket);
				
			} catch (SocketTimeoutException e){
				continue;
			} catch (IOException e) {
				log.error("There was a network error receiving Message");
				log.error(e);
				continue;
			}
//			finally {
//				try {
//					if(socket != null && !handleSocket){
//						socket.close();  //close the current connection
//					}
//				} catch (IOException e) {
//					log.error(e);
//				}
//			}
		}
		
		//close the server
		try {
			serverSocket.close();
		} catch (IOException e) {
			log.error(e);
		}
		
	}
	
	public static String getCurrentEnvironmentNetworkIp() {
        
		Enumeration<NetworkInterface> netInterfaces = null;
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            log.error("Somehow we have a socket error...");
        }

        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            
            while (address.hasMoreElements()) {
                InetAddress addr = address.nextElement();
                if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                        && !(addr.getHostAddress().indexOf(":") > -1)) {
                    return addr.getHostAddress();
                }
            }
        }
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

	
	class SocketThread implements Runnable {
		
		private Socket socket;
		
		public SocketThread(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			
			try {
				InputStream is = socket.getInputStream();

				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				
				StringBuffer buffer = new StringBuffer();
				String line = null;
				while((line = reader.readLine()) != null){
					
					if(line.contains("EOF")){
						line = line.replace("EOF", "");
						buffer.append(line);
						break;
					}
					
					buffer.append(line);
				} 
				
				ByteArrayInputStream byteIS = new ByteArrayInputStream(buffer.toString().getBytes());
				
				Document doc = null;
				try {
					doc = parse(byteIS);
				} catch (SAXException e) {
					log.error("There was an error processing the XML message");
					log.error(e);
				}

				boolean isValid = true; // validate(doc, "xml/Message.xsd");
				if (!isValid) {
					log.error("Received message xml was invalid...abandoning.");
				}

				String xmlString = docToString(doc);
				log.info("received message from destination "
						+ socket.getRemoteSocketAddress().toString());
				log.info(xmlString);

				// lolh4x!!
				removeWhitespaceNodes(doc.getDocumentElement());

				// get Message object and dispatch to appropriate module
				Message message = Message.toObject(doc);
				
				//String remoteAddress = socket.getInetAddress().getHostAddress();
				//message.setSender(remoteAddress);
				
				delegateMessage(message, socket);
				
			} catch(IOException e){
				log.error(e);
			}
			
		}
	}
	
	/**
	 * Classifies a received message and dispatches the message to the appropriate component
	 * @param message
	 * @param socket
	 * @return returns whether the socket maintenance will be handled by this method
	 */
	protected boolean delegateMessage(Message message, Socket socket) throws IOException {
		
		if(message.getMessageType().equals(Message.LOG_MESSAGE)){
			log.info("dispatching LOG_MESSAGE");
			
			LogManager logManager = LogManager.getInstance();
			
			LogMessage logMessage = (LogMessage)message;
			logManager.writeLog(logMessage);
			
			socket.close();
		}
		else if(message.getMessageType().equals(Message.EVENT_MESSAGE)){
			log.info("dispatching EVENT_MESSAGE");
			
			EventMessage eventMessage = (EventMessage)message;
			Engine engine = Engine.getInstance();
			engine.receiveMessage(eventMessage);
			
			socket.close();
		}
		else if(message.getMessageType().equals(Message.WORKFLOW_MESSAGE)){
			log.info("dispatching WORKFLOW_MESSAGE");
			
			WorkflowManager workflowManager = WorkflowManager.getInstance();
			
			WorkflowMessage workflowMessage = (WorkflowMessage)message;
			//String source = workflowMessage.getWorkflowSource();
			
			//TODO this WorkflowXML object is useless now
			//WorkflowXML workflowXML = new WorkflowXML();
			//workflowXML.setXml(source);
			
			workflowManager.receiveWorkflow(workflowMessage);
			
			socket.close();
		}
		else if(message.getMessageType().equals(Message.LOG_QUERY_MESSAGE)){
			
			log.info("dispatching LOG_QUERY_MESSAGE");
			
			LogQueryMessage logQueryMessage = (LogQueryMessage)message;
			
			String query = logQueryMessage.getQuery();
			
			LogQueryRunnable runnable = new LogQueryRunnable(query, socket);
			Thread t = new Thread(runnable);
			t.start();
			
			return true;   //the LogQueryRunnable will handle closing the socket
		}
		
		return false;
	}
	
	public int getPort() {
		return mPort;
	}
	
	public int getRemotePort() {
		return remotePort;
	}
	
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRunning(){
		return isRunning;
	}
	
	/**
	 * 
	 */
	public void stop(){
		isRunning = false;
	}
	
	public void sendMessageToNeighbors(Message message){
		
		String originator = message.getOriginator();
		String sender = message.getSender();
		String[] neighbors = getNeighbors();
		
		message.setSender(getCurrentEnvironmentNetworkIp()); //set ourselves as new sender
		
		for(String neighbor : neighbors){
			
			String address = getNeighborAddress(neighbor);
			
			if(sender != null && originator != null){
			   if(address.equals(originator) || address.equals(sender)){
				   continue;
			   }
			}
						
			sendMessage(address, message);
		}
		
	}
	
	/**
	 * Sends a message to another STIXAgent and the message content
	 * @param destinationAgent Unique identifier of the receiving STIXagent
	 * @param message Message to be sent to the receiving STIXagent
	 */
	public void sendMessage(String destinationAgent, Message message)  {
		
		Socket socket = null;
		
		message.setSender(getCurrentEnvironmentNetworkIp());
		
		int port = mPort;
		
		try {
			
			if(remotePort > 0){
				port = remotePort;
			}
			
			socket = new Socket(destinationAgent, port);
			OutputStream os = socket.getOutputStream();
			
			BufferedOutputStream bos = new BufferedOutputStream(os);
			bos.write(message.toXml().getBytes());
			bos.close();
		} catch (UnknownHostException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		}		
		finally {
			if (socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String[] getNeighbors(){
		
		if(neighborTable == null){
			neighborTable = new HashMap<String,String>();
			
			InputStream is = this.getClass().getResourceAsStream("/neighbors.properties");

			Properties prop = new Properties();

			try {
				prop.load(is);
			} catch (IOException e) {
				log.error(e);
			}

			String addressStr = (String) prop.get(getAgentId());

			String[] neighborArray = addressStr.split(";");
			for (int i = 0; i < neighborArray.length; i++) {
				String neighborStr = neighborArray[i];
				String[] neighbor = neighborStr.split(",");

				String agent = neighbor[0];
				String address = neighbor[1];
			
				neighborTable.put(agent, address);
			}
		}
		
		return neighborTable.keySet().toArray(new String[0]);	
	}
	
	/**
	 * 
	 * @param neighbor
	 * @return
	 */
	public String getNeighborAddress(String neighbor){
		return neighborTable.get(neighbor);
	}
	
	public String getAgentId() {
		return agentId;
	}
	
	public void setAgentId(String agentId){
		this.agentId = agentId;
	}
	
	/**
	 * 
	 * @author matt
	 */
	public static class Factory { 
		
		private static final Hashtable<Integer, CommunicationManager> instanceTable = new Hashtable<Integer, CommunicationManager>();
		
		public static synchronized CommunicationManager getInstance(){
			return getInstance(Agent.getDefaultPort());
		}
		
		public static synchronized CommunicationManager getInstance(int port){
			
			if(instanceTable.get(port) == null){
				CommunicationManager cm = new CommunicationManager(port);
				instanceTable.put(port, cm);
			}
			
			return instanceTable.get(port);
		}
		
	}

}
