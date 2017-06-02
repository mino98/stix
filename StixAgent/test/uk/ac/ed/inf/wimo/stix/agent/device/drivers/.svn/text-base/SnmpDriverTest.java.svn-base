package uk.ac.ed.inf.wimo.stix.agent.device.drivers;


import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SnmpDriver;

public class SnmpDriverTest {

	@Test
	public void testSnmpSet() {
		SnmpDriver.snmpSet("129.215.197.211", "1051", 
				"s3cr3tw", ".1.3.6.1.4.1.4247.101.101.1", 1);
}

	@Test
	public void testSnmpGet() {
				SnmpDriver.snmpGet("129.215.197.211", "1051",
						"s3cr3t", ".1.3.6.1.4.1.4247.100.101.1");
		
	}

}
