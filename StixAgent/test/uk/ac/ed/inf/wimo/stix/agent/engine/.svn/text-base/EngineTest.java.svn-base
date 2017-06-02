package uk.ac.ed.inf.wimo.stix.agent.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.xml.sax.InputSource;

import uk.ac.ed.inf.wimo.stix.agent.Agent;
import uk.ac.ed.inf.wimo.stix.agent.communication.EventMessage;
import uk.ac.ed.inf.wimo.stix.agent.communication.WorkflowMessage;
import uk.ac.ed.inf.wimo.stix.agent.device.DeviceManager;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataInComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataMappingComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.DataOutComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndPlainEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowMappingTaskComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.Condition;

public class EngineTest {
	
	private String readXmlFile(String fileName) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		StringBuffer stringBuffer = new StringBuffer();
		String str;
		while ((str = bufferedReader.readLine()) != null) {
			stringBuffer.append(str);
		}
		bufferedReader.close();
		
		return stringBuffer.toString();
	}
	
	private Workflow unMarshallWorkflow(String xmlFileName) throws IOException, JAXBException {
		WorkflowMessage workflowMessage = new WorkflowMessage();
		workflowMessage.setWorkflowSource(readXmlFile(xmlFileName));
		
		/* Unmarshall workflow */
		InputSource iSource = new InputSource(new ByteArrayInputStream(workflowMessage.getWorkflowSource().getBytes()));
		JAXBContext context = JAXBContext.newInstance("uk.ac.ed.inf.wimo.stix.agent.model.workflow");
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Workflow workflow = (Workflow) unmarshaller.unmarshal(iSource);
		return workflow;
	}
	
	private Workflow buildWorkflow(Workflow workflow) throws WorkflowException {
		// set start time in the past and end time in the future
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(System.currentTimeMillis() - 60000);
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(System.currentTimeMillis() + 360000);
		workflow.getMetadata().getValidity().setNotBefore(start);
		workflow.getMetadata().getValidity().setNotAfter(end);
		Engine.getInstance().registerNewWorkflow(workflow);
		return Engine.getInstance().registeredWorkflows.get("550e8400-e29b-41d4-a716-446655440000");
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRegisterNewWorkflow() throws IOException, JAXBException, WorkflowException {
		Workflow workflow = unMarshallWorkflow("xml/test/simpleworkflowexample.xml");

		MetadataComplexType metaData = workflow.getMetadata();
	    Calendar retrievedCal = metaData.getValidity().getNotBefore();
	    assertEquals(2001, retrievedCal.get(Calendar.YEAR));
	    assertEquals(11, retrievedCal.get(Calendar.MONTH));
	    assertEquals(31, retrievedCal.get(Calendar.DAY_OF_MONTH));
	    assertEquals(12, retrievedCal.get(Calendar.HOUR_OF_DAY));
	    assertEquals(34, retrievedCal.get(Calendar.MINUTE));
	    assertEquals(56, retrievedCal.get(Calendar.SECOND));
	    
	    retrievedCal = metaData.getValidity().getNotAfter();
	    assertEquals(2002, retrievedCal.get(Calendar.YEAR));
	    assertEquals(10, retrievedCal.get(Calendar.MONTH));
	    assertEquals(29, retrievedCal.get(Calendar.DAY_OF_MONTH));
	    assertEquals(13, retrievedCal.get(Calendar.HOUR_OF_DAY));
	    assertEquals(45, retrievedCal.get(Calendar.MINUTE));
	    assertEquals(07, retrievedCal.get(Calendar.SECOND));
	    
	    workflow = buildWorkflow(workflow);
	    
		assertNotNull(workflow);
		metaData = workflow.getMetadata();
	    assertEquals("stix:Name", metaData.getName());
	    assertEquals("stix:Author", metaData.getAuthor());
	    assertEquals("550e8400-e29b-41d4-a716-446655440000", metaData.getUUID());
	    assertEquals(123, metaData.getRev());
	    assertEquals("stix:Notes", metaData.getNotes());
	    assertEquals(true, metaData.isEnabled());
	    
	    AttributeSetComplexType attributeSet = workflow.getAttributeSet();
	    for (AttributeType attribute : (List<AttributeType>) attributeSet.getAttribute()) {
	    	assertEquals("stix:Name", attribute.getName());
	    	assertEquals("stix:Type", attribute.getType());
	    	assertEquals("stix:Value", attribute.getValue());
	    	assertFalse(attribute.isPersistent());
	    }
	    
	    PoolComplexType pool = workflow.getPool();
	    assertEquals("ON BTS DO", pool.getQuery());
	    assertEquals(654, pool.getX());
	    assertEquals(321, pool.getY());
	    for (StartMessageEventComplexType startMessageEventComplexType:
	    	(List<StartMessageEventComplexType>) pool.getStartMessageEvent()) {
	    	assertEquals("idvalue84", startMessageEventComplexType.getId());
	    	FlowMappingComplexType flowMappingComplexType = 
	    		startMessageEventComplexType.getFlowMapping();
	    	assertEquals("idvalue45", flowMappingComplexType.getOut());
	    	assertEquals("Hello World!", startMessageEventComplexType.getMessage());
	    	assertEquals(73, startMessageEventComplexType.getX());
	    	assertEquals(24, startMessageEventComplexType.getY());
	    }
	    
	    for (TaskComplexType taskComplexType: (List<TaskComplexType>) pool.getTask()) {
	    	assertEquals("uk.ac.ed.inf.wimo.stix.doSomething", taskComplexType.getClazz());
	    	assertEquals("idvalue45", taskComplexType.getId());
	    	assertEquals(74, taskComplexType.getX());
	    	assertEquals(25, taskComplexType.getY());	    	
	    	FlowMappingTaskComplexType flowMappingComplexType =
	    		taskComplexType.getFlowMapping();
	    	assertEquals("idvalue2", flowMappingComplexType.getOut());
	    	assertEquals("idvalue45", flowMappingComplexType.getError());
	    
	    	DataMappingComplexType dataMappingComplexType =
	    		taskComplexType.getDataMapping();
    		DataInComplexType inComplexType = dataMappingComplexType.getIn();
    		for (ParameterComplexType parameterComplexType:
    			(List<ParameterComplexType>) inComplexType.getParameter()) {
    			assertEquals("stix:AttributeIn", parameterComplexType.getAttribute());
    			assertEquals("stix:NameIn", parameterComplexType.getName());
    		}
    		DataOutComplexType outComplexType = dataMappingComplexType.getOut();
    		for (ParameterComplexType parameterComplexType:
    			(List<ParameterComplexType>) outComplexType.getParameter()) {
    			assertEquals("stix:AttributeOut", parameterComplexType.getAttribute());
    			assertEquals("stix:NameOut", parameterComplexType.getName());
    		}
	    }
	    
	    for (EndPlainEventComplexType endPlainEventComplexType:
	    	(List<EndPlainEventComplexType>) pool.getEndPlainEvent()) {
	    	assertEquals("idvalue2", endPlainEventComplexType.getId());
	    	assertEquals(75, endPlainEventComplexType.getX());
	    	assertEquals(26, endPlainEventComplexType.getY());	    	
	    }
	    
	    // TODO add registration of future event, not enabled event, event with emdtime < current
	}

	@Test
	public void testReceiveMessage()
		throws IOException, WorkflowException, InterruptedException, JAXBException {
		Workflow workflow = unMarshallWorkflow("xml/test/msgcatchingeventexample.xml");
	    workflow = buildWorkflow(workflow);
		final MessageCatchingEventComplexType msgCatch = 
			(MessageCatchingEventComplexType) workflow.getPool().getMessageCatch().get(0);
		
		EventMessage eventMessage = new EventMessage();
		eventMessage.setMessageType("string");
		eventMessage.setName("MessageNameValue");
		eventMessage.setContent("MessageContentValue");
		eventMessage.setSourceId(Agent.getAgentId());
		Engine.getInstance().receiveMessage(eventMessage);
		EventComplexType nextEvent = Engine.getInstance().runEvent(msgCatch);
		EndPlainEventComplexType endEvent = (EndPlainEventComplexType) workflow.getPool().getEndPlainEvent().get(0);
		assertEquals(endEvent, nextEvent);
		
		new Thread() {
			public void run() {
					try {
						Engine.getInstance().runEvent(msgCatch);
					} catch (WorkflowException e) {
						e.printStackTrace();
					}
			}
		}.start();
		Thread.yield(); Thread.sleep(1000);
		Engine.getInstance().receiveMessage(eventMessage);
		Thread.yield(); Thread.sleep(1000);
		junit.framework.Assert.assertTrue(Engine.getInstance().receivedMessages.isEmpty());
	}

	private void testThreads() throws InterruptedException {
		Thread.sleep(6000);
		Thread[] threads = new Thread[10];
		Thread.enumerate(threads);
		int threadCount = Thread.activeCount();
		int waitingThreadCountBefore = 0;
		for (int i = 0; i < threadCount; i++) {
			if (threads[i].getState() == Thread.State.WAITING)
				waitingThreadCountBefore++;
		}
		
		EventMessage eventMessage = new EventMessage();
		eventMessage.setMessageType("string");
		eventMessage.setName("MessageNameValue");
		eventMessage.setContent("MessageContentValue");
		eventMessage.setSourceId(Agent.getAgentId());
		Engine.getInstance().receiveMessage(eventMessage);
		Thread.yield();
		Thread.sleep(1000);
		
		threads = new Thread[10];
		Thread.enumerate(threads);
		threadCount = Thread.activeCount();
		int waitingThreadCountAfter = 0;
		for (int i = 0; i < threadCount; i++) {
			if (threads[i].getState() == Thread.State.WAITING)
				waitingThreadCountAfter++;
		}
			
		assertEquals(waitingThreadCountBefore - 1, waitingThreadCountAfter);
	}
	
	@Test
	public void testRegisterStartEvents() throws IOException, InterruptedException, JAXBException, WorkflowException {
		Workflow workflow = unMarshallWorkflow("xml/test/starteventsexample.xml");
		// Test start timer event
		StartTimerEventComplexType startTimerEventComplexType = 
			(StartTimerEventComplexType) workflow.getPool().getStartTimerEvent().get(0);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(new Date().getTime() + 5000);
		startTimerEventComplexType.setTimer(cal);
		workflow = buildWorkflow(workflow);
		testThreads();
		
		// Test start message event
		EventMessage eventMessage = new EventMessage();
		eventMessage.setMessageType("string");
		eventMessage.setName("startMessage");
		eventMessage.setContent("Hello World!");
		eventMessage.setSourceId(Agent.getAgentId());
		Engine.getInstance().receiveMessage(eventMessage);
		testThreads();
		
		// Test start condition event
		assertEquals(1, Engine.getInstance().startConditionEvents.size());
		Condition startCondition = 
			Engine.getInstance().startConditionEvents.keys().nextElement();
		Engine.getInstance().conditionTriggered(startCondition, DeviceManager.getInstance().getDevice("ubiquity1"));
		testThreads();
	}

	@Test
	public void testRunEventTaskComplexType() 
		throws IOException, InterruptedException, JAXBException, WorkflowException {
		Workflow workflow = unMarshallWorkflow("xml/test/taskexample.xml");
	    workflow = buildWorkflow(workflow);
		EndPlainEventComplexType endEvent = (EndPlainEventComplexType) workflow.getPool().getEndPlainEvent().get(0);
		synchronized (endEvent) {
			EventMessage eventMessage = new EventMessage();
			eventMessage.setMessageType("string");
			eventMessage.setName("startMessage");
			eventMessage.setContent("Hello World!");
			eventMessage.setSourceId(Agent.getAgentId());
			Engine.getInstance().receiveMessage(eventMessage);
			endEvent.wait();
		}
		// TODO add some tests of some sort
	
		// TODO test class downloading
	}
	
	@Test
	public void testRebootWorkflow() throws Exception {
		// Instanciate devices
		DeviceManager.getInstance().registerDevices();
		
		Workflow workflow = unMarshallWorkflow("xml/test/rebootworkflow.xml");
		// set reboot timer in 1 min
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis() + 10000);
		((StartTimerEventComplexType) workflow.getPool().getStartTimerEvent().get(0)).setTimer(cal);
		Engine.getInstance().registerNewWorkflow(workflow);
		Thread.sleep(60000);	
	}
	
	@Test
	public void testSnrWorkflow() throws Exception {
		// Instantiate devices
		DeviceManager.getInstance().registerDevices();
		
		Workflow workflow = unMarshallWorkflow("xml/test/snrworkflow.xml");
		// set reboot timer in 1 min
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis() + 10000);
		((StartTimerEventComplexType) workflow.getPool().getStartTimerEvent().get(0)).setTimer(cal);
		// set snr period to 10s
		((StartTimerEventComplexType) workflow.getPool().getStartTimerEvent().get(0)).setPeriod(10000);
		Engine.getInstance().registerNewWorkflow(workflow);
		Thread.sleep(30000);	
	}
	
	@Test
	public void testOutdateWorkflow() throws Exception {
		Workflow workflow = unMarshallWorkflow("xml/test/simpleworkflowexample.xml");
		Engine.getInstance().registerNewWorkflow(workflow);
		((StartMessageEventComplexType) Engine.getInstance().
				events.get(workflow).get("idvalue84")).setMessage("fava");
		assertEquals(1, Engine.getInstance().registeredPersistentVariables.size());
		String persistent = Engine.getInstance().registeredPersistentVariables.iterator().next();
		assertEquals("stix:Name2", persistent);
		Workflow workflow2 = unMarshallWorkflow("xml/test/simpleworkflowexample.xml");
		workflow2.getMetadata().setRev(124);
		AttributeSetComplexType attrSet = workflow2.getAttributeSet();
		((AttributeType) (attrSet.getAttribute().get(1))).setName("fava2");
		Engine.getInstance().registerNewWorkflow(workflow2);
		assertEquals(2, Engine.getInstance().registeredPersistentVariables.size());
		String okName = null;
		for (String name: Engine.getInstance().registeredPersistentVariables) {
			if ("stix:Name2".equals(name)) {
				okName = name;
				break;
			}
		}
		assertEquals(okName, "stix:Name2");
	}
}
