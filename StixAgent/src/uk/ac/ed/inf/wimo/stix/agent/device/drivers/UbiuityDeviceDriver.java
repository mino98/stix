package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SshDriver;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Rebootable;
import uk.ac.ed.inf.wimo.stix.agent.device.operations.Wireless;

public class UbiuityDeviceDriver extends GenericDeviceDriver implements Wireless, Rebootable {
	
	private static Logger log = Logger.getLogger(UbiuityDeviceDriver.class);

	private String getSsh(String command) {
		String user = getDevice().getAddress().split(":")[0];
		String[] splitAddr = getDevice().getAddress().split(":")[1].split("@");
		StringBuffer password = new StringBuffer();
		password.append(splitAddr[0]);
		for (int i = 1; i < splitAddr.length - 1; i++) {
			password.append("@");
			password.append(splitAddr[i]);
		}
		String address = splitAddr[splitAddr.length - 1];
		short port = getDevice().getAddress().split(":").length >= 3?Short.parseShort(getDevice().getAddress().split(":")[2]):22;
		return SshDriver.getSsh(command, address, port, user, password.toString());
	}
	
	public double getSnr(String interf) {
		double snr = Double.parseDouble(
				getSsh("/bin/cat /proc/net/wireless | /bin/grep " +
						interf + " | `which cut` -c 19-23"));
		log.debug("Ubiquity SNR for " + getDevice().getAddress().split(":")[1].split("@")[1] +
				": " + snr);
		return snr;
	}

	public void setSnr(double value) {
		log.error("Ubiquity set SNR not implemented");				
	}

	public void reboot() {
		getSsh("/sbin/reboot");
		log.debug("Ubiquity reboot for " + getDevice().getAddress().split(":")[1].split("@")[1]);
	}

	@Override
	public String getProperty(String name) {
		if ("SNR".equals(name))
			return Double.toString(getSnr("ath0"));
		return null;
	}

	@Override
	public void setProperty(String name, String value) {
		if ("SNR".equals(name))
			setSnr(Double.parseDouble(value));
	}

}
