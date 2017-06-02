package uk.ac.ed.inf.wimo.stix.agent.device;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.engine.Engine;
import uk.ac.ed.inf.wimo.stix.agent.engine.WorkflowException;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.Condition;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.IdentValueRetriever;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.ParseException;

/**
 * The DeviceManager is responsible for the communication with
 * the network device either via Operating System tools in case
 * the STIXagent is running as a software agent on the device itself
 * or via a management protocol.
 * 
 * @author damon
 *
 */
public class DeviceManager {
	
	private final static String DEVICE_PROP = "device.properties";
	private static Logger log = Logger.getLogger(DeviceManager.class);

	private static DeviceManager instance;
	
	private Hashtable<String, Device> devices = new Hashtable<String, Device>();
	
	private Hashtable<Condition, Device> conditionDevices = new Hashtable<Condition, Device>();
	
	/**
	 * Returns the only instance of the DeviceManager.
	 * @return The only instance of the DeviceManager
	 */
	public static synchronized DeviceManager getInstance() {
		if (instance == null)
			instance = new DeviceManager();
		return instance;
	}
	
	private DeviceManager() {}
	
	
	/**
	 * Register all devices by reading device.properties
	 */
	public void registerDevices() {
		log.debug("Reading device properties");
		Properties properties = new Properties();
		InputStream is = DeviceManager.class.getResourceAsStream("/" + DEVICE_PROP);
		try {
			if (is == null)
				is = new FileInputStream(DEVICE_PROP);
			properties.load(is);
		} catch (IOException e) {
			log.error("Error reading property file", e);
		}
		for (Enumeration<Object> keys = properties.keys(); keys.hasMoreElements();) {
			String key = (String) keys.nextElement();
			String[] splitKey = key.split("\\.");
			String deviceId = splitKey[0];
			String name = splitKey[1];
			String value = properties.getProperty(key);
			Device device = devices.get(deviceId);
			if (device == null) {
				device = new Device();
				device.setName(deviceId);
				devices.put(deviceId, device);
				Object deviceDriverObj = properties.getProperty(deviceId + ".driver");
				if ((deviceDriverObj != null) && (!"".equals(deviceDriverObj))) {
					device.setProperty("driver", (String) deviceDriverObj);
					log.debug("Device " + deviceId + ", new driver " + value);
				} else {
					log.debug("No driver " + deviceDriverObj + " found for device: " + deviceId);					
				}
				log.debug("New device created: " + deviceId);
			}
			if (!"driver".equals(name)) {
				device.setProperty(name, value);
				log.debug("Device " + deviceId + ", new property " + name + " = " + value);
			}
		}
	}
	
	/**
	 * Registers a new condition
	 * @param condition The new condition to be registered
	 * @return The ID of the new condition registered
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @throws WorkflowException 
	 * @throws IOException 
	 */
	public Condition registerCondition(final Device device, String conditionString)
		throws NumberFormatException, ParseException, WorkflowException, IOException {
		
		Condition condition = new Condition(conditionString);
		condition.registerIdentValueRetriever(new IdentValueRetriever() {
			public String getValue(String id) {
				return device.getProperty(id);
			}
		});
		// only parsed to check for errors
		condition.parse();
		condition.reInit();
		conditionDevices.put(condition, device);
		return condition;
	}

	/**
	 * Internal method to be called to check for conditions in query
	 * @param conditionString
	 * @return
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 */
	private boolean checkQueryCondition(final Device device, String conditionString) throws WorkflowException, NumberFormatException, ParseException, IOException {
		Condition condition = new Condition(conditionString);
		condition.registerIdentValueRetriever(new IdentValueRetriever() {
			public String getValue(String id) {
				return device.getProperty(id);
			}
		});
		return condition.parse();
		// TODO mechanism to call this method
	}
	
	/**
	 * Sends a ping to a host.
	 * @param host The target host for the ping
	 * @param timeout The timeout of the ping request
	 * @return true if the host replies, false otherwise
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public boolean ping(String host, int timeout) throws UnknownHostException, IOException {
		return InetAddress.getByName(host).isReachable(timeout);
	}
	
	
	/**
	 * Return a device given a device name.
	 * @param name The unique name of the device
	 * @return The device object
	 */
	public Device getDevice(String name) {
		return devices.get(name);
	}

	public Collection<Device> query(Workflow workflow, String query) throws WorkflowException {
		query = query.trim();
		int indexOfOn = query.indexOf("ON");
		if (indexOfOn == 0) {
			query = query.substring(2, query.length() - 2).trim();
			ArrayList<Device> selectedDevices = new ArrayList<Device>();
			for (Enumeration<Device> deviceEnum = devices.elements(); deviceEnum.hasMoreElements(); ) {
				Device device = deviceEnum.nextElement();
				try {
					if (checkQueryCondition(device, query)) {
						selectedDevices.add(device);
						log.debug("Device " + device.getName() + " added to list for query " +
								query);
					} else {
						log.debug("Device " + device.getName() + " discarded to list for query " +
								query);						
					}
				} catch (Exception e) {
					log.debug("Device " + device.getName() + " doesn't have some properties for " +
							query);						
				}
			}
			return selectedDevices;
		} else if (query.indexOf("DO") == 0) {
			Collection<Device> deviceColl = devices.values();
			return deviceColl;
		} else {
			log.error("Workflow " + workflow.getMetadata().getUUID() 
					+ " has unvalid query: " + query);
			throw new WorkflowException(workflow, null,
					"Workflow query not valid: " + query);
		}

	}
	
	
	/**
	 * Starts thread that polls for all conditions with specified period.
	 * @param period The polling period in milliseconds
	 */
	public void startConditionPolling(long period) {
		TimerTask timerTask = new TimerTask() {
			public void run() {
				Thread thread = new Thread(new Date().toString() +
						" conditionPolling") {
					public void run() {
							log.debug("Condition polling triggered");
							pollDevicesForConditions();
							log.debug("Condition polling finished");
					}
				};
				thread.setDaemon(true);
				thread.start();
			}
		};
		Timer timer = new Timer(new Date().toString() +
				" conditionPolling Timer", true);
		timer.schedule(timerTask, 0, period);
		log.debug("Condition polling timer started with period: " + period);
	}
	
	
	/**
	 * Inner method for iterating through conditions and trigger the engine.
	 */
	private void pollDevicesForConditions() {
		Enumeration<Condition> keyEnum = conditionDevices.keys();
		while (keyEnum.hasMoreElements()) {
			Condition condition = keyEnum.nextElement();
			try {
				condition.reInit();
				if (condition.parse()) {
					try {
						Engine.getInstance().conditionTriggered(condition, conditionDevices.get(condition));
					}catch (Exception e) {
						log.error("Error triggering condition: " + condition, e);
					}
				}	
			} catch (Exception e) {
				log.error("Error parsing condition: " + condition, e);
			}
		}
	}

}
