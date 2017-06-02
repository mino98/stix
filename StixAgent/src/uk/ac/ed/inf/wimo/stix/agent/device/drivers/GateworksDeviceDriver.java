package uk.ac.ed.inf.wimo.stix.agent.device.drivers;


import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.HttpDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SshDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Rebootable;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Routable;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Wireless;

public class GateworksDeviceDriver extends GenericDeviceDriver implements Routable, Wireless, Rebootable {

	private static final String SET_OSPF_COMMAND = "/usr/bin/vtysh";
	private static final String SET_COST_COMMAND = "/usr/bin/vtysh\nconf t\n";
	private static final String GET_SNR_COMMAND_PRE = 
		"/bin/cat /proc/net/wireless | /bin/grep ";
	private static final String GET_SNR_COMMAND_POST = 
		" | `which cut` -c 14-17";
	
	private static Logger log = Logger.getLogger(GateworksDeviceDriver.class);
	private short sshPort;
	private String sshAddress;
	private String sshUser;
	private String sshPassword;


	public double getSnr(String interf) {
		double snr = Double.parseDouble(
				SshDriver.getSsh(
						GET_SNR_COMMAND_PRE + interf + GET_SNR_COMMAND_POST,
						sshAddress, sshPort, sshUser, sshPassword));
		log.debug("Read SNR interface " + interf + " for " + sshAddress + ": " + snr);
		return snr;
	}
	
	public void setSnr(double value) {
		log.error("Setting of SNR not implemented.");
	}

	public void reboot() {
		HttpDriver.getHttp(getDevice().getAddress() + "/reboot.php");
		log.debug("Rebooted " + getDevice().getAddress());
	}

	@Override
	public String getProperty(String name) {
		if ("SNR0".equals(name))
			return Double.toString(getSnr("ath0"));
		else if ("SNR1".equals(name))
			return Double.toString(getSnr("ath1"));
		else if ("OSPF".equals(name))
			return getOspf();
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		if("sshaddress".equals(name)) {
			sshUser = value.split(":")[0];
			sshPassword = value.split(":")[1].split("@")[0];
			sshAddress = value.split(":")[1].split("@")[1];
			sshPort = value.split(":").length >= 3?Short.parseShort(getDevice().getAddress().split(":")[2]):22;
		} else if ("OSPF".equals(name)) {
			setOspf(value);
		} else if ("Cost0".equals(name)) {
			setCost("ath0", value);
		} else if ("Cost1".equals(name)) {
			setCost("ath1", value);
		}
	}

	public String getOspf() {
		log.error("Setting of SNR not implemented.");
		return null;
	}

	public void setOspf(String value) {
		String ospfCommand = SET_OSPF_COMMAND + " " + value;
		SshDriver.getSsh(ospfCommand, sshAddress, sshPort, sshUser, sshPassword);
		log.debug("OSPF command done: " + ospfCommand);
	}

	public void setCost(String interf, String cost) {
		String ospfCostCommand = "echo 'conf t\n" +
		"int " + interf + "\n" +
		"ip ospf cost " + cost + "\n" +
		"exit\n" +
		"exit\n" +
		"write\n" +
		"exit\n" +
		"' | vtysh\n";
//			SET_COST_COMMAND +
//			"int " + interf +
//			"\nip ospf cost " + cost +
//			"\nexit\nexit\nwrite\nexit\n";
		SshDriver.getSsh(ospfCostCommand,
				sshAddress, sshPort, sshUser, sshPassword);
		log.debug("OSPF cost set. Command: " + ospfCostCommand);
	}

	public String getCost(String interf) {
		log.error("Getting of cost not implemented.");
		return null;
	}
}
