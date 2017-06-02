package uk.ac.ed.inf.wimo.stix.agent.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Wireless;
import uk.ac.ed.inf.wimo.stix.agent.engine.TaskException;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class GetSNR implements TaskInterface {

	private static Logger log = Logger.getLogger(GetSNR.class);
	
	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {

		Hashtable<String, String> returnMap = new Hashtable<String, String>();

		GenericDeviceDriver driver = device.getDeviceDriver();
		double snr;
		if (driver instanceof Wireless) 
			snr = ((Wireless) driver).getSnr("ath0");
		else {
			log.error("Device " + device + " not wireless (can't get SNR).");
			throw new TaskException("Device " + device + " not wireless (can't get SNR).");
		}
		returnMap.put("SNR", Double.toString(snr));
		return returnMap;
	}

}
