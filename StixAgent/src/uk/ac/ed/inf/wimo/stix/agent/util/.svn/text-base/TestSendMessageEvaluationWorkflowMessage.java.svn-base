package uk.ac.ed.inf.wimo.stix.agent.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TestSendMessageEvaluationWorkflowMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		FileInputStream fis = new FileInputStream("xml/EvaluationEventMessageExample.xml");


		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(
				new FileReader("xml/EvaluationEventMessageExample.xml"));
		char[] buf = new char[1024];
		int numRead=0;
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();

		Socket socket = new Socket("testbedgw.wimo.inf.ed.ac.uk", 9992);
		//Socket socket = new Socket("localhost", 9999);


		DataOutputStream bos = new DataOutputStream(socket.getOutputStream());
		bos.write(fileData.toString().getBytes());
		bos.close();

	}

}
