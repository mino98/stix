package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UbiuityDeviceDriverTest {
	
	private UbiuityDeviceDriver ubiuityDeviceDriver;

	@Before
	public void setUp() throws Exception {
		ubiuityDeviceDriver = new UbiuityDeviceDriver();
		ubiuityDeviceDriver.getDevice().setAddress("ubnt:ubnt@testbedgw.wimo.inf.ed.ac.uk:1050");
	}

	@Test
	public void testGetSnr() {
		double snr = ubiuityDeviceDriver.getSnr("ath0");
		assertTrue(snr < 0);
	}

	@Test
	public void testReboot() {
		ubiuityDeviceDriver.reboot();
	}

}
