package uk.ac.ed.inf.wimo.stix.agent.device.operations;

public interface Wireless {

	public double getSnr(String interf);
	
	public void setSnr(double value);
	
}
