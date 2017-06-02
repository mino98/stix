package uk.ac.ed.inf.wimo.stix.agent.log;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.communication.LogQueryMessage;

public class LogQueryRunnableTest {

	@BeforeClass
	public static void setUp(){
		//need to insert log entries
		LogManager logManager = LogManager.getInstance();
		
		for(int i=0; i<10; i++){
			logManager.writeLog("agent2", "9871239871", "name", "value"+i, new Date());
		}
	}
	
	@AfterClass
	public static void tearDown(){
		LogManager logManager = LogManager.getInstance();
		logManager.queryLog("DELETE FROM log");
	}
	
	@Test
	public void testGetResults() {
		LogQueryRunnable runnable = new LogQueryRunnable("SELECT * FROM log", null);
		LogQueryMessage message = runnable.getResults();
		System.out.println(message);
	}

}
