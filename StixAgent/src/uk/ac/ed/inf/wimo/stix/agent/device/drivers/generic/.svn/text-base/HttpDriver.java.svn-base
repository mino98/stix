package uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpDriver {

	public static String getHttp(String urlStr) {
		try{ 
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection ();
			BufferedReader bufferedReader =
				new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null)
			{
				stringBuffer.append(line);
			}
			bufferedReader.close();
			return stringBuffer.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
