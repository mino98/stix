package uk.ac.ed.inf.wimo.stix.agent.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Rebootable;
import uk.ac.ed.inf.wimo.stix.agent.engine.TaskException;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class Reboot implements TaskInterface {

	private static Logger log = Logger.getLogger(Reboot.class);
	
	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {


		GenericDeviceDriver driver = device.getDeviceDriver();
		if (driver instanceof Rebootable) 
			((Rebootable) driver).reboot();
		else
			log.error("Device " + device + " not rebootable.");
		
		Hashtable<String, String> returnMap = new Hashtable<String, String>();
		return returnMap;
	}

}
