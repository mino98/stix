package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.SshDriver;

public class SshDriverTest {

	@Test
	public void testGetSsh() {
		String result = SshDriver.getSsh("pwd", "testbedgw.wimo.inf.ed.ac.uk",
				(short) 22, "stix", "st1xx");
		System.out.println(result);
		assertEquals("/home/stix", result.trim());
	}

}
