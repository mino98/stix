package uk.ac.ed.inf.wimo.stix.agent.util.condition;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import uk.ac.ed.inf.wimo.stix.agent.engine.WorkflowException;

public class ConditionTest {

	@Test
	public void testParse() throws NumberFormatException, ParseException,
		WorkflowException, IOException {
		Condition condition = new Condition("(1<=2) AND (2>0)");
		assertTrue(condition.parse());
		condition = new Condition("(1==1) AND (2<0)");
		assertFalse(condition.parse());
		condition = new Condition("(1>2) AND (2<0)");
		assertFalse(condition.parse());
		condition = new Condition("(1<2) OR (2>=0)");
		assertTrue(condition.parse());
		condition = new Condition("(1==2) OR (2>=0)");
		assertTrue(condition.parse());
		condition = new Condition("(1==2) OR (2<0)");
		assertFalse(condition.parse());
		condition = new Condition("1+1-1==1");
		assertTrue(condition.parse());
		condition = new Condition("10*100/10 == 100/10*10");
		assertTrue(condition.parse());
		condition = new Condition("((1+1==2) OR (2>10*10-99)) AND (1<2)");
		assertTrue(condition.parse());		
	}

}
