package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.ShellDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SshDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Powerable;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Voltage;

public class StixControlDeviceDriver extends GenericDeviceDriver implements Powerable, Voltage {

	private static Logger log = Logger.getLogger(StixControlDeviceDriver.class);

	static String COMMAND = "stixcontrol";
	
	private String getSsh(String stixControlCommand) {
		String user = getDevice().getAddress().split(":")[0];
		String password = getDevice().getAddress().split(":")[1].split("@")[0];
		String address = getDevice().getAddress().split(":")[1].split("@")[1];
		String port = getDevice().getAddress().split(":")[2].split("/")[0];
		String deviceNr = getDevice().getAddress().split(":")[2].split("/")[1];
		return SshDriver.getSsh(COMMAND + " " +  deviceNr + " " + stixControlCommand,
				address, Short.parseShort(port), user, password);		
	}
		
	public void switchOff() {
		if (getDevice().getAddress().contains(":")) {
			getSsh("-r 0");
		} else {
			ShellDriver.getShell(COMMAND + " " +  getDevice().getAddress() + " -r 0");
		}
	}

	public void switchOn() {
		if (getDevice().getAddress().contains(":")) {
			getSsh("-r 1");
		} else {
			ShellDriver.getShell(COMMAND + " " +  getDevice().getAddress() + " -r 1");
		}
	}

	public double getVoltage() {
		if (getDevice().getAddress().contains(":")) {
			return Double.parseDouble(getSsh("-v 0"));
		} else {
		return Double.parseDouble(
				ShellDriver.getShell(COMMAND + " " +  getDevice().getAddress() + " -v 0"));
		}
	}

	public double getCurrent() {
		if (getDevice().getAddress().contains(":")) {
			return Double.parseDouble(getSsh("-a 0"));
		} else {
		return Double.parseDouble(
				ShellDriver.getShell(COMMAND + " " + getDevice().getAddress() + " -a 0"));
		}
	}

	public void setCurrent(double value) {
		log.error("Setting of current not implemented.");
	}

	public void setVoltage(double value) {
		log.error("Setting of voltage not implemented.");
	}
	
	@Override
	public String getProperty(String name) {
		if ("Voltage".equals(name))
			return Double.toString(getVoltage());
		else if ("Current".equals(name))
			return Double.toString(getCurrent());
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		if ("Voltage".equals(name))
			setVoltage(Double.parseDouble(value) );
		else if ("Current".equals(name))
			setCurrent(Double.parseDouble(value));
	}

}
