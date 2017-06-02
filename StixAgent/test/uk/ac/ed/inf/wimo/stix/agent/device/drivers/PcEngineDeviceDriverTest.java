package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PcEngineDeviceDriverTest {

	private PcEngineDeviceDriver pcDeviceDriver;
	
	@Before
	public void setUp() throws Exception {
		pcDeviceDriver = new PcEngineDeviceDriver();
		pcDeviceDriver.getDevice().setAddress("testbedgw.wimo.inf.ed.ac.uk:1051");
	}

	@Test
	public void testReboot() {
		pcDeviceDriver.reboot();
	}

	@Test
	public void testGetSnr() {
		double snr = pcDeviceDriver.getSnr("ath0");
		assertTrue(snr < 0);
	}

}
