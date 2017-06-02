package uk.ac.ed.inf.wimo.stix.agent.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Voltage;
import uk.ac.ed.inf.wimo.stix.agent.engine.TaskException;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class Electrical implements TaskInterface {

	private static Logger log = Logger.getLogger(Electrical.class);
	
	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {
		double voltage = 0;
		double current = 0;
		GenericDeviceDriver driver = device.getDeviceDriver();
		if (driver instanceof Voltage) {
				voltage = ((Voltage) driver).getVoltage();
				current = ((Voltage) driver).getCurrent();		
		} else {
			log.error("Cannot get voltage and current from device " + device);
			throw new TaskException("Cannot get voltage and current from device " + device);
		}
		Hashtable<String, String> returnMap = new Hashtable<String, String>();		
		returnMap.put("Voltage", Double.toString(voltage));
		returnMap.put("Current", Double.toString(current));
		return returnMap;
	}

}
