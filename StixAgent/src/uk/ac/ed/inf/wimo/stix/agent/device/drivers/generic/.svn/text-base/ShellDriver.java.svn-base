package uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class ShellDriver {

	private static Logger log = Logger.getLogger(ShellDriver.class);

	public static String getShell(String command) {
		try {
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);
			BufferedReader buffer =
				new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String tempString;
			while ((tempString = buffer.readLine()) != null) {
				stringBuffer.append(tempString);
			}
			tempString = stringBuffer.toString(); 
			log.debug("Run shell command: '" + command + "'. Output: " + tempString);
			return tempString;
		} catch (Exception e) {
			log.error("Cannot run shell command", e);
		}
		return null;
	}
	
}
