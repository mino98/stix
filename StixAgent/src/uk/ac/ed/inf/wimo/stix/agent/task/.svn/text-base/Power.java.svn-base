package uk.ac.ed.inf.wimo.stix.agent.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Powerable;
import uk.ac.ed.inf.wimo.stix.agent.engine.TaskException;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class Power implements TaskInterface {

	private static Logger log = Logger.getLogger(Power.class);
	
	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {


		GenericDeviceDriver driver = device.getDeviceDriver();
		if (driver instanceof Powerable) {
			if ("1".equals(parameters.get("On"))) {
				((Powerable) driver).switchOn();
			} else if ("0".equals(parameters.get("On"))) {
					((Powerable) driver).switchOff();		
			} else
				log.error("No \"On\" parameter specified for device " + device);
		} else
			log.error("Device " + device + " not powerable.");
		
		Hashtable<String, String> returnMap = new Hashtable<String, String>();
		return returnMap;
	}

}
