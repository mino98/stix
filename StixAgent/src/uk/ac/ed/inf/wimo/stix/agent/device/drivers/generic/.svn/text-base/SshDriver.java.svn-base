package uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic;

import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SshDriver {
	
	private static Logger log = Logger.getLogger(SshDriver.class);

	public static String getSsh(String command, String address, short port, String user, String password) {
		log.debug("Executing ssh command: '" + command + "' on host " + user + ":****@" + address + ":" + port);
		StringBuffer stringBuffer = new StringBuffer();
		JSch jsch = new JSch();
		try {
			Session session=jsch.getSession(user, address, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec)channel).setErrStream(System.err);
			InputStreamReader in = new InputStreamReader(channel.getInputStream());
			channel.connect();

			char[] tempBuffer = new char[1024];
			while(true){
				while(true){
					int i = in.read(tempBuffer, 0, 1024);
					if (i < 0)
						break;
					stringBuffer.append(tempBuffer, 0, i);
				}
				if (channel.isClosed()) {
					log.debug("SSH exit-status: " + channel.getExitStatus());
					break;
				}
				try { 
					Thread.sleep(1000);
				} catch(Exception ee) {
					log.error("SSH error", ee);
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e){
			log.error("SSH error", e);
		}
		String result = stringBuffer.toString();
		log.debug("Result of ssh command: '" + command + "' on host " +
				address + ":" + port + ": " + result);
		return result;

	}
}
