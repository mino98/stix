package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SnmpDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Rebootable;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Wireless;

public class PcEngineDeviceDriver extends GenericDeviceDriver implements Rebootable, Wireless {

	private static Logger log = Logger.getLogger(PcEngineDeviceDriver.class);

	@Override
	public String getProperty(String name) {
		if ("SNR".equals(name))
			return Double.toString(getSnr(""));
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		if ("SNR".equals(name))
			setSnr(Double.parseDouble(value));
	}

	public void reboot() {
		String address = getDevice().getAddress().split(":")[0];
		String port = getDevice().getAddress().split(":")[1];
		SnmpDriver.snmpSet(address, port, 
				"s3cr3tw", ".1.3.6.1.4.1.4247.101.101.1", 1);
		log.debug("PCEngines rebooted: " + address + " : " + port);
	}

	public double getSnr(String interf) {
		String address = getDevice().getAddress().split(":")[0];
		String port = getDevice().getAddress().split(":")[1];
		String result = SnmpDriver.snmpGet(address, port, "s3cr3t",
		".1.3.6.1.4.1.4247.100.101.1");
		double snr = Double.parseDouble(result);
		log.debug("PCEngines SNR for " + getDevice().getAddress() + ": " + snr);
		return snr;
	}

	public void setSnr(double value) {
		log.error("PCEngines set SNR not implemented");		
	}

}
