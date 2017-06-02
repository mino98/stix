package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GateworksDeviceDriverTest {
	
	private GateworksDeviceDriver gateworksDeviceDriver;
	
	@Before
	public void setUp() {
		gateworksDeviceDriver = new GateworksDeviceDriver();
		gateworksDeviceDriver.getDevice().setAddress("http://testbedgw.wimo.inf.ed.ac.uk:1052");
	}
	@Test
	public void testGetSnr() {
		double snr = gateworksDeviceDriver.getSnr("ath0");
		assertTrue(snr < 0);
	}

	@Test
	public void testReboot() {
		gateworksDeviceDriver.reboot();
	}

}
