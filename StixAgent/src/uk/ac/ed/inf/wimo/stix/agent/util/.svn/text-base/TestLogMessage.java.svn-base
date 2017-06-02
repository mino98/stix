package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.UUID;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogMessage;

public class TestLogMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
	
		LogMessage m = new LogMessage("sitea", UUID.randomUUID().toString(), "name1", "value1", new Date());
		m.setOriginator("192.168.2.101");
		m.setSender("192.168.2.101");
		
		String messageXml = m.toXml();

		Socket socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 9991);

		DataOutputStream bos = new DataOutputStream(socket.getOutputStream());
		bos.write(messageXml.getBytes());
		bos.close();
	}

}
