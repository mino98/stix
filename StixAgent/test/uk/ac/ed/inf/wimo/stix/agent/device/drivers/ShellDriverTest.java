package uk.ac.ed.inf.wimo.stix.agent.device.drivers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.device.drivers.generic.ShellDriver;

public class ShellDriverTest {

	@Test
	public void testGetShell() {
		String output = ShellDriver.getShell("echo test");
		assertEquals("test", output);
	}

}
