package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StixControlDeviceDriverTest {
	
	private StixControlDeviceDriver stixControl;

	@Before
	public void setUp() {
		stixControl = new StixControlDeviceDriver();
		stixControl.getDevice().setAddress("root:st1xx@testbedgw.wimo.inf.ed.ac.uk:1001/0");
	}
	
	@Test
	public void testSwitchOff() {
		stixControl.switchOff();
	}

	@Test
	public void testSwitchOn() {
		stixControl.switchOn();
	}

	@Test
	public void testGetVoltage() {
		double voltage = stixControl.getVoltage();
		assertTrue(voltage > 0);
		assertTrue(voltage < 24);
	}

	@Test
	public void testGetCurrent() {
		double current = stixControl.getCurrent();
		assertTrue(current > 0);
		assertTrue(current < 10);
	}

}
