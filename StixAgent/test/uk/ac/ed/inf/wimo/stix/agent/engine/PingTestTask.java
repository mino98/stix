package uk.ac.ed.inf.wimo.stix.agent.engine;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.DeviceManager;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class PingTestTask implements TaskInterface {

	private static final int DEFAULT_WAITINTERVAL = 3000;

	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {

		// TODO implement ping in the actual device
		
		String ip = parameters.get("DestIP");
		int packetCount = Integer.parseInt(parameters.get("Numpckts"));
		String waitIntervalString = parameters.get("WaitInterval");
		int waitInterval;
		if (waitIntervalString != null)
			waitInterval = Integer.parseInt(waitIntervalString);
		else
			waitInterval = DEFAULT_WAITINTERVAL;
			
			
		long timeSum = 0;
		int successCount = 0;
		for (int i = 0; i < packetCount; i++) {
			long startTime = Calendar.getInstance().getTimeInMillis();
			boolean success;
			try {
				success = DeviceManager.getInstance().ping(ip, waitInterval);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				throw new TaskException("Unknown Host: " + ip);
			} catch (IOException e) {
				e.printStackTrace();
				throw new TaskException("IO Error");
			}
			if (success) {
				timeSum += Calendar.getInstance().getTimeInMillis() - startTime;
				successCount++;
			}
		}
		
		Hashtable<String, String> returnMap = new Hashtable<String, String>();
		if (successCount == 0)
			returnMap.put("Avg_RTT", "0");
		else
			returnMap.put("Avg_RTT", Double.toString(timeSum/successCount));
		returnMap.put("PacketLoss", Double.toString((packetCount - successCount)/packetCount));
		
		return returnMap;
	}

}
