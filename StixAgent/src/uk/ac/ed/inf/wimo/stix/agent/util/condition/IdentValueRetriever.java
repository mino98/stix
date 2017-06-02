package uk.ac.ed.inf.wimo.stix.agent.util.condition;

import java.io.IOException;

public interface IdentValueRetriever {
	
	public String getValue(String id) throws IOException;

}
