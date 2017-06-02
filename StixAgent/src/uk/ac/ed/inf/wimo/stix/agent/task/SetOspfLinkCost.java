package uk.ac.ed.inf.wimo.stix.agent.task;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.GenericDeviceDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Routable;
import uk.ac.ed.inf.wimo.stix.agent.engine.TaskException;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;

public class SetOspfLinkCost implements TaskInterface {

	private static Logger log = Logger.getLogger(SetOspfLinkCost.class);
	
	public Map<String, String> runTask(Device device, Map<String, String> parameters) throws TaskException {
		GenericDeviceDriver driver = device.getDeviceDriver();
		if (driver instanceof Routable) {
			((Routable) driver).setCost(parameters.get("Interface"),
					parameters.get("Cost"));
		} else {
			log.error("Cannot set OSPF Cost to device " + device +
					", Inteface: " + parameters.get("Interface") +
					", Cost: " + parameters.get("Cost"));
			throw new TaskException("Cannot set OSPF to device " + device);
		}
		Hashtable<String, String> returnMap = new Hashtable<String, String>();		
		return returnMap;
	}

}
