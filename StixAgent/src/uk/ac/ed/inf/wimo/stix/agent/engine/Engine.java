package uk.ac.ed.inf.wimo.stix.agent.engine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import uk.ac.ed.inf.wimo.stix.agent.Agent;
import uk.ac.ed.inf.wimo.stix.agent.communication.CommunicationManager;
import uk.ac.ed.inf.wimo.stix.agent.communication.EventMessage;
import uk.ac.ed.inf.wimo.stix.agent.communication.Message;
import uk.ac.ed.inf.wimo.stix.agent.device.Device;
import uk.ac.ed.inf.wimo.stix.agent.device.DeviceManager;
import uk.ac.ed.inf.wimo.stix.agent.log.LogManager;
import uk.ac.ed.inf.wimo.stix.agent.model.TaskInterface;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.EndEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.EventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.ExclusiveGatewayEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.FlowOutConditionComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.LogEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageCatchingEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MessageThrowingEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.ParameterComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.PoolComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartConditionEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartMessageEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.StartTimerEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.TaskComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.TimerEventComplexType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.Workflow;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.AttributeSetComplexType.AttributeType;
import uk.ac.ed.inf.wimo.stix.agent.model.workflow.MetadataComplexType.ValidityType;
import uk.ac.ed.inf.wimo.stix.agent.storage.StorageManager;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.Condition;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.IdentValueRetriever;
import uk.ac.ed.inf.wimo.stix.agent.util.condition.ParseException;


/**
 * The workflow Engine manages and executes the workflow
 * it receives from the WorkflowManager.
 * 
 * @author damon
 *
 */
public class Engine {
	
	// TODO where do we write logs (in file)?
	
	// Used for performance testing output
	private int workflowNumber = 0;

	private final static Logger log = Logger.getLogger(Engine.class);
	
	// Unique instance
	private static Engine instance;
	
	/** Runtime storage for workflows */
	Hashtable<String, Workflow> registeredWorkflows = new Hashtable<String, Workflow>();
	
	/** Mapping String - Event */
	Hashtable<Workflow, Hashtable<String, EventComplexType>> events = new Hashtable<Workflow, Hashtable<String,EventComplexType>>();
	
	/** Start message event storage */
	Hashtable<String, List<StartMessageEventComplexType>> startMessageEvents = new Hashtable<String, List<StartMessageEventComplexType>>();
	
	/** Start condition event storage */
	Hashtable<Condition, StartConditionEventComplexType> startConditionEvents = new Hashtable<Condition, StartConditionEventComplexType>();
	
	/** Workflow map of each event */
	Hashtable<EventComplexType, Workflow> eventWorkflows = new Hashtable<EventComplexType, Workflow>();
	
	/** Received messages from communication manager */
	Hashtable<String, String> receivedMessages = new Hashtable<String, String>();
	
	/** Registered timers */
	Hashtable<EventComplexType, Timer> registeredTimers = new Hashtable<EventComplexType, Timer>();
	
	/** List of persistent attribute names */
	HashSet<String> registeredPersistentVariables = new HashSet<String>();
	
	/** Per workflow devices */
	Hashtable<Workflow, Collection<Device>> workflowDevices = new Hashtable<Workflow, Collection<Device>>();

	
	private Engine() { }
	
	
	/**
	 * Returns the unique instance of the Engine.
	 * @return The unique instance
	 */
	public synchronized static Engine getInstance() {
		if (instance == null)
			instance = new Engine();
		return instance;
	}
	
	
	/**
	 * Registers a new workflow
	 * @param workflowXML The workflow to be registered
	 * @throws WorkflowException 
	 */
	public void registerNewWorkflow(final Workflow workflow) throws WorkflowException {
		log.debug("registerNewWorkflow: " + workflow.getMetadata().getUUID());
		
		// Increment the total number of workflows received
		workflowNumber++;
		
		// Check enabled flag
		MetadataComplexType metaData = workflow.getMetadata();
		if (!metaData.isEnabled()) {
			log.debug("Workflow is not enabled: " +	workflow.getMetadata().getUUID());
			return;
		}
		// Check end time
		if (isEndTimeInThePast(workflow)) {
			log.debug("Workflow endtime is in the past: " +	workflow.getMetadata().getUUID());
			return;
		}
		// Check and register in the future
		if (registerFutureRegistration(workflow))
			return;

		// Remove old revision if present
		Workflow outDatedWorkflow;
		if ((outDatedWorkflow = registeredWorkflows.get(workflow.getMetadata().getUUID())) != null) {
			if (outDatedWorkflow.getMetadata().getRev() >= workflow.getMetadata().getRev())
				return;
			else
				// TODO true or false for removePersistentAttrubutes??
				removeWorkflow(outDatedWorkflow, false);
		}
		
		// register persistent attributes
		registerPersistentVariables(workflow);
		// Register end time
		registerUnRegistration(workflow);
		// Register workflow
		registerWorkflow(workflow);
	}

	
	/**
	 * Registers persistent variables in persistent set and set value
	 * if they have one assigned at start.
	 * @param workflow The workflow whose persistent variables have to be registered
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	private void registerPersistentVariables(Workflow workflow) throws WorkflowException {
		log.debug("registerPersistentVariables: " + workflow.getMetadata().getUUID());
		
		// TODO we should do it per workflow
		StorageManager storageManager;
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			log.error(e);
			throw new WorkflowException(workflow, null, "Can't get StorageManager instance");
		}
		AttributeSetComplexType attributeSet = workflow.getAttributeSet();
		if (attributeSet != null) {
			for (AttributeType attribute: (List<AttributeType>) attributeSet.getAttribute()) {
				if (attribute.isPersistent()) {
					String name = attribute.getName();
					registeredPersistentVariables.add(name);
					String value = attribute.getValue();
					if (value != null) {
						storageManager.writeDeviceVariable(attribute.getName(), value);
						log.debug("registerPersistentVariables: " +
								attribute.getName() + "=" + value);
					}
				}
			}
		}
	}


	/**
	 * Schedules registration in the future if start date in the future
	 * @param workflow The workflow to be registered
	 * @return true if the start has been registered in the future, false otherwise
	 */
	private boolean registerFutureRegistration(final Workflow workflow) {
		log.debug("registerFutureRegistration: " + workflow.getMetadata().getUUID());
		// Check start date and register timer if future
		MetadataComplexType metaData = workflow.getMetadata();
		ValidityType validity = metaData.getValidity();
		Calendar start = validity.getNotBefore();
		if (start.getTimeInMillis() > System.currentTimeMillis()) {
			// Not all registrations delegated to other threads
			// so that failure reported to WorkflowManager
			TimerTask timerTask = new TimerTask() {
				public void run() {
					Thread thread = new Thread(new Date().toString() +
							" registerFutureRegistration: " +
							workflow.getMetadata().getUUID()) {
						public void run() {
							try {
								log.debug("Future registration started: " + workflow.getMetadata().getUUID());
								registerNewWorkflow(workflow);
							} catch (WorkflowException e) {
								log.error(e);
								LogManager.getInstance().writeLog(
										Agent.getAgentId(),
										e.getWorkflowId(), 
									    "WorkflowException",
									    e.getMessage(), new Date());
							}
						}
					};
					thread.setDaemon(true);
					thread.start();
				}
			};
			Timer timer = new Timer(new Date().toString() +
					" registerFutureRegistration: " +
					workflow.getMetadata().getUUID(), true);
			timer.schedule(timerTask, start.getTime());
			log.debug(workflow.getMetadata().getUUID() + " scheduled: " +
					start.getTime());
			return true;
		}
		return false;
	}
	
	
	/**
	 * Checks if the end time of the workflow is in the past
	 * @param workflow The workflow whose end time is being checked
	 * @return true if the end time is in the past false otherwise
	 */
	private boolean isEndTimeInThePast(Workflow workflow) {
		MetadataComplexType metaData = workflow.getMetadata();
		ValidityType validity = metaData.getValidity();
		// Check end date
		Calendar end = validity.getNotAfter();
		return (end.getTimeInMillis() < System.currentTimeMillis());
	}
	
	
	/**
	 * Registers removal of workflow at end time
	 * @param workflow the workflow whose removal has to be scheduled
	 */
	private void registerUnRegistration(final Workflow workflow) {
		log.debug("registerUnRegistration: " + workflow.getMetadata().getUUID());
		MetadataComplexType metaData = workflow.getMetadata();
		ValidityType validity = metaData.getValidity();
		final Calendar end = validity.getNotAfter();
		// register task removal
		TimerTask timerTask = new TimerTask() {
			public void run() {
				Thread thread = new Thread(new Date().toString() +
						" registerUnRegistration: " + end.getTime().toString() +
						", " + workflow.getMetadata().getUUID()) {
					public void run() {
						try {
							log.debug("Start un-registration: " + workflow.getMetadata().getUUID());
							removeWorkflow(workflow, true);
						} catch (WorkflowException e) {
							log.error(e);
							LogManager.getInstance().writeLog(
									Agent.getAgentId(),
									e.getWorkflowId(), 
								    "WorkflowException",
								    e.getMessage(), new Date());
						}
					}
				};
				thread.setDaemon(true);
				thread.start();
			}
		};
		Timer timer = new Timer(new Date().toString() +
				" registerUnRegistration: " + end.getTime().toString() +
				", " + workflow.getMetadata().getUUID(), true);
		timer.schedule(timerTask, end.getTime());
		log.debug(workflow.getMetadata().getUUID() + " un-registration  scheduled for "
				+ end.getTime());
	}

	
	/**
	 * Register workflow (no checks)
	 * Called by registerNewWorkflow after checking enabled, start time and end time.
	 * @param workflow The workflow to be registered
	 * @throws WorkflowException
	 */
	private void registerWorkflow(Workflow workflow) throws WorkflowException {
		// Add to runtime
		registeredWorkflows.put(workflow.getMetadata().getUUID(), workflow);
		// Register all workflow events
		addWorkflowEvents(workflow);
		// Register all devices to the workflow
		registerDevices(workflow);
		// Parse workflow and register timers, accepted messages and conditions
		registerStartEvents(workflow);			
	}

	
	/**
	 * Regsters all devices for this workflow
	 * @param workflow The workflow whose devices have to be registered
	 * @throws WorkflowException 
	 */
	private void registerDevices(Workflow workflow) throws WorkflowException {
		workflowDevices.put(workflow, 
				DeviceManager.getInstance().query(workflow, workflow.getPool().getQuery()));
	}


	/**
	 * Removes workflow from agent.
	 * @param workflow The workflow to be removed
	 */
	private void removeWorkflow(final Workflow workflow, boolean removePersistentAttributes) throws WorkflowException {
		// TODO what about running tasks?
		// Un-register all workflow events and timers
		removeWorkflowEventsAndTimers(workflow);
		// Remove workflow from runtime
		registeredWorkflows.remove(workflow.getMetadata().getUUID());
		// Remove workflow persistent attributes
		if (removePersistentAttributes)
			removePersistentAttributes(workflow);
	}
		
	
	/**
	 * Removes all persistent attributes of workflow.
	 * @param workflow The workflow whose attributes have to be removed
	 */
	@SuppressWarnings("unchecked")
	private void removePersistentAttributes(Workflow workflow) throws WorkflowException {
		log.debug("removePersistentAttributes: " + workflow.getMetadata().getUUID());
		// TODO we should do it per workflow
		StorageManager storageManager;
		try {
			storageManager = StorageManager.getInstance();
		} catch (IOException e) {
			log.error(e);
			throw new WorkflowException(workflow, null, "Can't get StorageManager instance");
		}
		AttributeSetComplexType attributeSet = workflow.getAttributeSet();
		for (AttributeType attribute: (List<AttributeType>) attributeSet.getAttribute()) {
			if (attribute.isPersistent()) {
				String name = attribute.getName();
				registeredPersistentVariables.remove(name);
				// TODO correct? (writing null to delete)
				storageManager.writeDeviceVariable(attribute.getName(), null);
				log.debug("removePersistentAttributes: " + attribute.getName());
			}
		}		
	}


	/**
	 * Add events of a workflow to the repository
	 * @param workflow The workflow whose events have to be added
	 * @throws WorkflowException 
	 */
	@SuppressWarnings("unchecked")
	void addWorkflowEvents(Workflow workflow) throws WorkflowException {
		log.debug("addWorkflowEvents: " + workflow.getMetadata().getUUID());
		PoolComplexType pool = workflow.getPool();
		// New table for current workflow
		Hashtable<String, EventComplexType> workflowEvents = new Hashtable<String, EventComplexType>();
		for (EventComplexType e: (List<EventComplexType>) pool.getExclusiveGateway()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: exclusiveGatewayEvent " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getStartConditionEvent()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: startConditionEvent " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getStartMessageEvent()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: startMessageEvent " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getStartTimerEvent()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: startTimerEvent" + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getEndPlainEvent()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: entPlainEvent " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getTerminateEvent()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: terminateEvent " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getTask()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: task " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getTimer()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: task " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getMessageCatch()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: messgeCatch " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getMessageThrow()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: messgeThrow " + e.getId());
		}
		for (EventComplexType e: (List<EventComplexType>) pool.getLog()) {
			workflowEvents.put(e.getId(), e);
			eventWorkflows.put(e, workflow);
			log.debug(workflow.getMetadata().getUUID() + " event added: log " + e.getId());
		}
		events.put(workflow, workflowEvents);
	}
	
	/**
	 * Remove events of a workflow from the repository
	 * @param workflow The workflow whose events have to be removed
	 */
	void removeWorkflowEventsAndTimers(Workflow workflow) {
		log.debug("removeWorkflowEventsAndTimers: " + workflow.getMetadata().getUUID());
		// Remove event list for current workflow
		Hashtable<String, EventComplexType> workflowEvents = events.remove(workflow);
		synchronized (receivedMessages) {
			// Remove events-workflow mappings and timers
			for (EventComplexType event: workflowEvents.values()) {
				Timer timer = registeredTimers.remove(event);
				if (timer != null) {
					timer.cancel();
					log.debug(workflow.getMetadata().getUUID() + " remove timer " + event.getId());
				}
				eventWorkflows.remove(event);
				log.debug(workflow.getMetadata().getUUID() + " remove event " + event.getId());
				if (event instanceof TimerEventComplexType)
					event.notifyAll();
			}
			// Notify all waiting thread to kill removed ones
			receivedMessages.notifyAll();
		}
	}
	
	@SuppressWarnings("unchecked")
	void registerStartEvents(Workflow workflow) throws WorkflowException {
		PoolComplexType pool = workflow.getPool();
		// Register start events
		for (StartConditionEventComplexType startConditionEvent:
			(List<StartConditionEventComplexType>) pool.getStartConditionEvent()) {
			registerStartCondition(startConditionEvent);
		}
		for (StartMessageEventComplexType startMessageEvent:
			(List<StartMessageEventComplexType>) pool.getStartMessageEvent()) {
			registerStartMessage(startMessageEvent);
		}
		for (StartTimerEventComplexType startTimerEvent:
			(List<StartTimerEventComplexType>) pool.getStartTimerEvent()) {
			registerStartTimer(startTimerEvent);
		}
	}

	/**
	 * Registers a start timer
	 * @param workflow The workflow whose start timer has to be registered
	 * @param startTimerEvent The start timer event to be registered
	 */
	void registerStartTimer(final StartTimerEventComplexType startTimerEvent) {
		final Workflow workflow = getWorkflow(startTimerEvent);
		log.debug("registerStartTimer: " + workflow.getMetadata().getUUID());
		final TimerTask timerTask = new TimerTask() {
			public void run() {
				log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() + " triggered");
				// Start a workflow thread for each device
				for (Device device: workflowDevices.get(workflow)) {
					Thread thread = new WorkflowThread(workflow, device,
							" startTimerEvent: " + startTimerEvent.getTimer().getTime().toString() +
							", " + startTimerEvent.getPeriod()) {
						// TimerTask expect the run method to return immediately
						public void run() {
							try {
								log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() +
										" timer event started " + startTimerEvent.getId());
								runWorkflow(startTimerEvent);
							} catch (WorkflowException e) {
								log.error(e);
								LogManager.getInstance().writeLog(
										Agent.getAgentId(),
										e.getWorkflowId(), 
									    "WorkflowException",
									    e.getMessage(), new Date());
							}
						}
					};
					thread.setDaemon(true);
					thread.start();
				}
				}
		};
		Timer timer = new Timer(new Date().toString() +
				" startTimerEvent: " + startTimerEvent.getTimer().getTime().toString() +
				", " + startTimerEvent.getPeriod() + ", " +
				workflow.getMetadata().getUUID(), true);
		// Registers timer in timer table
		registeredTimers.put(startTimerEvent, timer);
		Date time = startTimerEvent.getTimer().getTime();
		long startTimeInMs = time.getTime();
		long currentTimeInMs = System.currentTimeMillis();
		long period = startTimerEvent.getPeriod();
		if (period < 0) {
			// Start time in the future
			if (startTimeInMs >= currentTimeInMs) {
				timer.schedule(timerTask, time);
				log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() +
						", time: " + time);
			// Start time in the past
			} else {
				log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() +
						", NOT registered because time " + time + " in the past");
			}
		} else {
			// Start time in the past
			if (startTimeInMs <= currentTimeInMs) {
				// TODO test this
				long startTimeSkew = startTimeInMs % period;
				long currentTimeSkew = currentTimeInMs % period;
				long nextStartTimeInMs = startTimeSkew > currentTimeSkew?
						(currentTimeInMs - currentTimeSkew + startTimeSkew):
						(currentTimeInMs - currentTimeSkew + startTimeSkew + period);
				log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() +
						", start time in the past: " + time + ", period: " + period);
				time.setTime(nextStartTimeInMs);
				
			}
			timer.schedule(timerTask, time, period);
			log.debug("registerStartTimer: " + workflow.getMetadata().getUUID() +
				", time: " + time + ", period: " + period);
		}
	}
	

	/**
	 * Registers a start message event
	 * @param startMessageEvent The message event to be registered
	 */
	void registerStartMessage(StartMessageEventComplexType startMessageEvent) {
		log.debug("registerStartMessage: " + getWorkflow(startMessageEvent).getMetadata().getUUID());
		String message = startMessageEvent.getMessage();
		List<StartMessageEventComplexType> startMessageList = startMessageEvents.get(message);
		if (startMessageList == null) {
			startMessageList = new ArrayList<StartMessageEventComplexType>();
			startMessageEvents.put(message, startMessageList);
		}
		startMessageList.add(startMessageEvent);
		log.debug("registerStartMessage: " + getWorkflow(startMessageEvent).getMetadata().getUUID() +
				", message registered: " + message + ", event: " + startMessageEvent.getId());
	}

	/**
	 * Registers a start condition
	 * @param startConditionEvent The start condition to be registered
	 * @throws WorkflowException 
	 */
	void registerStartCondition(StartConditionEventComplexType startConditionEvent)
		throws WorkflowException {
		Workflow workflow = getWorkflow(startConditionEvent);
		log.debug("registerStartCondition: " + workflow.getMetadata().getUUID());
		String conditionString = startConditionEvent.getCondition();
		Condition condition;
			for (Device device: workflowDevices.get(workflow)) {
				try {
				condition = DeviceManager.getInstance().registerCondition(device, conditionString);
				startConditionEvents.put(condition, startConditionEvent);
				} catch (NumberFormatException e) {
					log.error("registerStartCondition: " + e);
					/* throw new WorkflowException(getWorkflow(startConditionEvent),
							startConditionEvent,
							"Error when evaluating condition: " + conditionString); */
				} catch (ParseException e) {
					log.error("registerStartCondition: " + e);
					/* throw new WorkflowException(getWorkflow(startConditionEvent),
							startConditionEvent,
							"Error when evaluating condition: " + conditionString); */
				} catch (IOException e) {
					log.error("registerStartCondition: " + e);
					/*throw new WorkflowException(getWorkflow(startConditionEvent),
					startConditionEvent,
					"Error when evaluating condition: " + conditionString); */
				}
			}
	}

	
	/**
 	* Runs workflow following the arrow and executes events, and tasks
 	* @param startEvent The event from were the workflow must start
 	* @throws WorkflowException
 	*/
	void runWorkflow(StartEventComplexType startEvent) throws WorkflowException {
		log.debug("runWorkflow: " + getWorkflow(startEvent).getMetadata().getUUID());
		// Create per run attribute storage
		
		EventComplexType event = getDefaultNext(startEvent);
		do {
			if (event instanceof ExclusiveGatewayEventComplexType)
				event = runEvent((ExclusiveGatewayEventComplexType) event);
			else if (event instanceof LogEventComplexType)
				event = runEvent((LogEventComplexType) event);
			else if (event instanceof MessageCatchingEventComplexType)
				event = runEvent((MessageCatchingEventComplexType) event);
			else if (event instanceof MessageThrowingEventComplexType)
				event = runEvent((MessageThrowingEventComplexType) event);
			else if (event instanceof TimerEventComplexType)
				event = runEvent((TimerEventComplexType) event);
			else if (event instanceof TaskComplexType)
				event = runEvent((TaskComplexType) event);
		} while (!(event instanceof EndEventComplexType));
		
		// Trick to notify calling test methods
		synchronized (event) {
			event.notifyAll();
		}
		log.debug("runWorkflow: " + getWorkflow(startEvent).getMetadata().getUUID() + " finished");
	}
	
	/**
	 * Executes a generic event (by throwing an exception since it is invalid)
	 * @param event The event to be run
	 * @return The method never returns
	 * @throws WorkflowException
	 */
/*	EventComplexType runEvent(EventComplexType event) throws WorkflowException {
		log.debug("runEvent: " + getWorkflow(event).getMetadata().getUUID() + ", invalid event");
		throw new WorkflowException(getWorkflow(event), event, "Invalid event");
	}*/
	
	/**
	 * Executes an exclusive gateway event
	 * @param event The exclusive gateway event to be executed
	 * @param localAttributes Map containing the name-value pair for local attributes
	 * @return The next event
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	EventComplexType runEvent(ExclusiveGatewayEventComplexType event) throws WorkflowException {
		log.debug("runEvent ExclusiveGatewayEventComplexType: " + getWorkflow(event).getMetadata().getUUID());
		for (FlowOutConditionComplexType out:
			(List<FlowOutConditionComplexType>) event.getFlowMapping().getOut()) {
			String condition = out.getCondition();
			log.debug("runEvent ExclusiveGatewayEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
					", condition: " + condition);
			try {
				if ((condition == null) || 
					(condition.length() == 0) ||
					(evaluateCondition(out.getCondition()))) {
					log.debug("runEvent ExclusiveGatewayEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
							", condition: " + condition + ", taken");
					return events.get(getWorkflow(event)).get(out.getValue());
				} else {
					log.debug("runEvent ExclusiveGatewayEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
							", condition: " + condition + ", not taken");
				}
			} catch (NumberFormatException e) {
				log.error(e);
				throw new WorkflowException(getWorkflow(event), event,
						"Error when evaluating condition: " + condition);
			} catch (ParseException e) {
				log.error(e);
				throw new WorkflowException(getWorkflow(event), event,
						"Error when evaluating condition: " + condition);
			} catch (IOException e) {
				log.error(e);
				throw new WorkflowException(getWorkflow(event), event,
						"Error when evaluating condition: " + condition);
			}
		}
		return null;
	}
	
	/**
	 * Evaluates a string condition
	 * @param conditionString The condition to be evaluated
	 * @param localAttributes Map containing the name-value pair for local attributes
	 * @return True if the condition is true, false otherwise
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 * @throws WorkflowException 
	 * @throws IOException 
	 */
	private boolean evaluateCondition(String conditionString)
			throws NumberFormatException, ParseException, WorkflowException, IOException {
		log.debug("evaluateCondition: " + conditionString);
		Condition condition = new Condition(conditionString);
		condition.registerIdentValueRetriever(new IdentValueRetriever() {
			public String getValue(String id) throws IOException {
				return getAttributeValue(id);
			}
		});
		boolean result = condition.parse();
		log.debug("evaluateCondition: " + condition + " = " + result);
		return result;
	}


	/**
	 * Runs a log event
	 * @param event The log event to be executer
	 * @param localAttributes Map containing the name-value pair for local attributes
	 * @return The next event
	 * @throws WorkflowException
	 */
	EventComplexType runEvent(LogEventComplexType event) throws WorkflowException {
		log.debug("runEvent LogEventComplexType: " + getWorkflow(event).getMetadata().getUUID());
		ParameterComplexType param = (ParameterComplexType) event.getDataMapping().getIn().getParameter().get(0);
		try {
			LogManager.getInstance().writeLog(
					Agent.getProperty("agentid"), 
					getWorkflow(event).getMetadata().getUUID(),
					param.getName(),
					getParameterValue(param),
					new Date());
		} catch (IOException e) {
			log.error("runEvent LogEventComplexType: " + e);
			throw new WorkflowException(getWorkflow(event), event,
			"Error when waiting for message");
		}
		return getDefaultNext(event);
	}
	

	/**
	 * Runs a message catching event
	 * @param event The message catching event to be run
	 * @return The next event
	 * @throws WorkflowException
	 */
	EventComplexType runEvent(MessageCatchingEventComplexType event) throws WorkflowException {
		log.debug("runEvent MessageCatchingEventComplexType: " + getWorkflow(event).getMetadata().getUUID());
		ParameterComplexType inParam = 
			(ParameterComplexType) event.getDataMapping().getIn().getParameter().get(0);
		String msgName = inParam.getValue();
		synchronized (receivedMessages) {
			String msgContent = receivedMessages.remove(msgName);
			if (msgContent == null) {
				long timeout = event.getTimeout();
				if (timeout >=0) {
					log.debug("runEvent MessageCatchingEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
							", message name: " + msgName + ", timeout: " + timeout);
					// Timeout specified
					try{
						Date startTime = new Date();
						do {
							// Check if event removed (expired)
							if (isRemoved(event))
								// TODO retrieve workflow somehow (instead of null)
								throw new WorkflowException(null, event,
								"Event removed (workflow expired)");
							receivedMessages.wait(timeout);
							Date endTime = new Date();
							timeout = timeout - (endTime.getTime() - startTime.getTime()); 
						} while ((!receivedMessages.containsKey(msgName)) ||
								timeout <= 0);
					} catch (InterruptedException e) {
						log.error("runEvent MessageCatchingEventComplexType: " + e);
						throw new WorkflowException(getWorkflow(event), event,
								"Error when waiting for message");
					}		
				} else {
					log.debug("runEvent MessageCatchingEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
							", message name: " + msgName + ", no timeout");
					// Timeout not specified
					try {
						do {
							// Check if event removed (expired)
							if (isRemoved(event))
								// TODO retrieve workflow somehow (instead of null)
								throw new WorkflowException(null, event,
								"Event removed (workflow expired)");
							receivedMessages.wait();
						} while ((!receivedMessages.containsKey(msgName)) ||
								timeout == 0);
					} catch (InterruptedException e) {
						log.error("runEvent MessageCatchingEventComplexType: " + e);
						throw new WorkflowException(getWorkflow(event), event,
								"Error when waiting for message");
					}
				}
				// TODO we should put the number of threads waiting on a msg and remove it
				// only when the last has restarted (maybe we should have a uuid for each msg too)
				msgContent = receivedMessages.remove(msgName);
			}
			// Write in variable
			if (msgContent != null) {
				ParameterComplexType outParam = 
					(ParameterComplexType) event.getDataMapping().getOut().getParameter().get(0);
				String varName = outParam.getAttribute();
				try {
					setAttributeValue(varName, msgContent);
					log.debug("runEvent MessageCatchingEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
							", message received: " + msgName + " = " + msgContent + ", written in: " + varName);
				} catch (IOException e) {
					log.error(e);
					throw new WorkflowException(getWorkflow(event), event,
					"Error setting attribute");
				}
			} else {
				log.debug("runEvent MessageCatchingEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
						", message not received: " + msgName);
			}
			// TODO Send acknowledgement
		}
		return getDefaultNext(event);
	}
	
	
	/**
	 * Checks if an event has been removed from the (still) registered workflow.
	 * @param event The event whose removal has to be checked.
	 * @return true if the event has been removed, false otherwise
	 */
	private boolean isRemoved(EventComplexType event) {
		return getWorkflow(event) == null;
	}

	
	/**
	 * Invoked to process a received message
	 * @param sourceAgent The unique ID of the source Agent
	 * @param content The content of the message
	 */
	public void receiveMessage(EventMessage message) {
		String msgName = message.getName();
		String msgContent = message.getContent();
		log.debug("receiveMessage: " + msgName + " = " + msgContent);
		if ("startMessage".equals(msgName)) {
			// Start workflows
			synchronized (startMessageEvents) {
				for (final StartMessageEventComplexType startMessageEvent :
					startMessageEvents.get(msgContent)) {
					Workflow workflow = this.getWorkflow(startMessageEvent);
					// Start a workflow thread for each device
					for (Device device: workflowDevices.get(workflow)) {
						Thread thread = new WorkflowThread(workflow, device,
								"startMessageEvent: " +
								msgName + " = " + msgContent) {
							public void run() {
								try {
									log.debug("receiveMessage startMessageEvent: " +
											getWorkflow().getMetadata().getUUID());
									runWorkflow(startMessageEvent);
								} catch (WorkflowException e) {
									log.error("receiveMessage startMessageEvent: " + e);
									LogManager.getInstance().writeLog(
											Agent.getAgentId(),
											e.getWorkflowId(), 
										    "WorkflowException",
										    e.getMessage(), new Date());
								}
							}
						};
						thread.setDaemon(true);
						thread.start();
					}
				}
			}
		} else {
			// Notify message catch events
			synchronized (receivedMessages) {
				receivedMessages.put(msgName, msgContent);
				receivedMessages.notifyAll();
			}
			log.debug("receiveMessage: " + msgName + " = " + msgContent +
					" put in received messages");
		}
	}

	
	/**
	 * Runs a message throwing event
	 * @param event The message to be thrown
	 * @return The next event
	 * @throws WorkflowException
	 * @throws  
	 */
	@SuppressWarnings("unchecked")
	EventComplexType runEvent(MessageThrowingEventComplexType event) throws WorkflowException {
		log.debug("runEvent MessageThrowingEventComplexType: " +
				getWorkflow(event).getMetadata().getUUID());
		List<ParameterComplexType> paramList =
			(List<ParameterComplexType>) event.getDataMapping().getIn().getParameter();
		if (paramList.size() != 3) {
			log.debug("runEvent MessageThrowingEventComplexType: " +
					getWorkflow(event).getMetadata().getUUID() +
					"wrong number of parameters (" + paramList.size() + ")");
			throw new WorkflowException(getWorkflow(event),
					event, "wrong number of parameters (" + paramList.size() + ")");
		}
		String msgName = null, msgContent= null, destination = null;
		for (ParameterComplexType param: paramList) {
			String name = param.getName();
			try {
			if (name.equals("messageName"))
				msgName = getParameterValue(param);
			if (name.equals("messageContent"))
				msgContent = getParameterValue(param);
			if (name.equals("destination"))
				destination = getParameterValue(param);
			} catch (IOException e) {
				log.error("runEvent MessageThrowingEventComplexType: " +
						getWorkflow(event).getMetadata().getUUID() + ", " + e);
				throw new WorkflowException(getWorkflow(event),
						event, "Message Throwing: error getting parameters");
			}
		}
		if (msgName == null) {
			log.debug("runEvent MessageThrowingEventComplexType: " +
					getWorkflow(event).getMetadata().getUUID() +
					"no message name");
			throw new WorkflowException(getWorkflow(event),
					event, "Message Throwing: no message name");
		}
		if (msgContent == null) {
			log.debug("runEvent MessageThrowingEventComplexType: " +
					getWorkflow(event).getMetadata().getUUID() +
					"no message content");
			throw new WorkflowException(getWorkflow(event),
					event, "Message Throwing: no message content");
		}
		if (destination == null) {
			log.debug("runEvent MessageThrowingEventComplexType: " +
					getWorkflow(event).getMetadata().getUUID() +
					"no destination for message");
			throw new WorkflowException(getWorkflow(event),
					event, "Message Throwing: no destination for message");
		}
		
		EventMessage eventMessage = new EventMessage();
		eventMessage.setName(msgName);
		eventMessage.setContent(msgContent);
		eventMessage.setSourceId(Agent.getAgentId());
		eventMessage.setMessageType(Message.EVENT_MESSAGE);
		CommunicationManager.Factory.getInstance().sendMessage(destination, eventMessage);
		log.debug("runEvent MessageThrowingEventComplexType: " +
				getWorkflow(event).getMetadata().getUUID() +
				"message sent: " + msgName + " = " + msgContent + " to destination " +
				destination);
	
		return getDefaultNext(event);
	}

	/**
	 * Runs a timer event
	 * @param event The timer to be run
	 * @return The next event
	 * @throws WorkflowException
	 */
	EventComplexType runEvent(TimerEventComplexType event) throws WorkflowException {
		log.debug("runEvent TimerEventComplexType: " + getWorkflow(event).getMetadata().getUUID());
		synchronized (event) {
			try {
				if (isRemoved(event)) {
					// TODO retrieve workflow somehow (instead of null)
					log.debug("runEvent TimerEventComplexType: Event removed (workflow expired)" +
							event.getId());
					throw new WorkflowException(null, event,
						"Event removed (workflow expired)");
				}
				long duration = event.getDuration();
				log.debug("runEvent TimerEventComplexType: " + getWorkflow(event).getMetadata().getUUID() +
						", waiting " + duration + "s");
				event.wait(duration);
				if (isRemoved(event)) {
					// TODO retrieve workflow somehow (instead of null)
					log.debug("runEvent TimerEventComplexType: Event removed (workflow expired)" +
							event.getId());
					throw new WorkflowException(null, event,
						"Event removed (workflow expired)");
				}
			} catch (InterruptedException e) {
				log.error("Event TimerEventComplexType: " +
						getWorkflow(event).getMetadata().getUUID() + ", " + e);
				throw new WorkflowException(getWorkflow(event), event, e.getMessage());
			}
		}
		
		return getDefaultNext(event);
	}

	/**
	 * Runs a task
	 * @param event The task to be run
	 * @return The next event
	 * @throws WorkflowException
	 */
	@SuppressWarnings("unchecked")
	EventComplexType runEvent(TaskComplexType event) throws WorkflowException {
		log.debug("runEvent TaskComplexType: " + getWorkflow(event).getMetadata().getUUID());
		// Check if we owe the class
		Class taskClass = null;
		String className = event.getClazz();
		try {
			// Try to load class
			log.debug("runEvent TaskComplexType: " +
					getWorkflow(event).getMetadata().getUUID() + 
					", trying to load class " + className);
			taskClass = Class.forName(className);
		} catch (ClassNotFoundException ex1) {
			log.debug("runEvent TaskComplexType: " +
					getWorkflow(event).getMetadata().getUUID() +
					"class " + className + " not found, trying download. " + ex1);
			try {
			// Retrieve class
			BufferedInputStream in =
				new BufferedInputStream(new URL(event.getURL()).openStream());
			FileOutputStream fos = new FileOutputStream(className);
			BufferedOutputStream bout = new BufferedOutputStream(fos,1024);
			byte data[] = new byte[1024];
			int count;
			while((count = in.read(data,0,1024)) < 0)
				bout.write(data,0,count);
			bout.close();
			fos.close();
			in.close();
			// Retry to load class
			log.debug("runEvent TaskComplexType: " +
					getWorkflow(event).getMetadata().getUUID() + 
					", re-trying to load class " + className);
			taskClass = Class.forName(className);
			} catch (Exception ex2) {
				log.debug("runEvent TaskComplexType: " +
						getWorkflow(event).getMetadata().getUUID() +
						", task error while retrieving class " + className +
						". "+ ex2);
				throw new WorkflowException(getWorkflow(event), event,
						"Task error while retrieving class " + className +
						"." + ex2.getMessage());
			}

		}
		
		// Call method
		try {
			TaskInterface task = (TaskInterface) taskClass.newInstance();
			// Collect parameter values
			Hashtable<String, String> inParameters = new Hashtable<String, String>();
			Map<String, String> outParameters = null;
			if (((event.getDataMapping()) != null) &&
				(event.getDataMapping().getIn() != null) &&
				(event.getDataMapping().getIn().getParameter() != null)) {
				for (ParameterComplexType parameter: 
					(List<ParameterComplexType>) event.getDataMapping().getIn().getParameter()) {
					String name = parameter.getName();
					String value = getParameterValue(parameter);
					inParameters.put(name, value);
					log.debug("runEvent TaskComplexType: " +
							getWorkflow(event).getMetadata().getUUID() + 
							", parameter collected: " + name + " = " + value);
				}
			}
			
			// Call task method
			try {
				log.debug("runEvent TaskComplexType: " +
						getWorkflow(event).getMetadata().getUUID() + 
						", calling runTask");
				Device device = ((WorkflowThread) Thread.currentThread()).getDevice();
				outParameters = task.runTask(device, inParameters);
			} catch (TaskException taskEx) {
				String errorEvent = event.getFlowMapping().getError();
				if (errorEvent != null) {
					log.debug("runEvent TaskComplexType: forseen error (Error flow) " + taskEx);
					return events.get(getWorkflow(event)).get(event.getFlowMapping().getOut());
				} else {
					log.debug("runEvent TaskComplexType: unforseen error (no Error flow) " + taskEx);
					throw new WorkflowException(getWorkflow(event), event, "Task error thrown but no Error flow");
				}
			}
			
			// Write back out parameters
			if (((event.getDataMapping()) != null) &&
					(event.getDataMapping().getOut() != null) &&
					(event.getDataMapping().getOut().getParameter() != null)) {
				for (ParameterComplexType parameter: 
					(List<ParameterComplexType>) event.getDataMapping().getOut().getParameter()) {
					String parameterName = parameter.getName();
					String attributeName = parameter.getAttribute();
					String parameterValue = outParameters.get(parameterName);
					setAttributeValue(attributeName, parameterValue);
					log.debug("runEvent TaskComplexType: " +
							getWorkflow(event).getMetadata().getUUID() + 
							", wrote back parameter " + parameterName + " = " + parameterValue +
							" in attribute " + attributeName);
				}		
			}
		} catch (Exception e) {
			log.error("runEvent TaskComplexType: " +
					getWorkflow(event).getMetadata().getUUID(), e);
			throw new WorkflowException(getWorkflow(event), event,
					"Error calling task method runTask: " + e.getMessage());
		}

		return events.get(getWorkflow(event)).get(event.getFlowMapping().getOut());
	}


	/**
	 * Returns the default next event in case there is only one
	 * @param event The current event
	 * @return The next event
	 */
	private EventComplexType getDefaultNext(FlowEventComplexType event) {
		return events.get(getWorkflow(event)).get(event.getFlowMapping().getOut());
	}
	
	
	/**
	 * Triggered (by the DeviceManager when a condition is met.
	 * @param conditionId The condition that became true
	 */
	public void conditionTriggered(final Condition condition, Device device) {
		log.debug("conditionTriggered: " + condition + ", device: " + device);
		final StartConditionEventComplexType startConditionEvent =
			startConditionEvents.get(condition);
		// Only start thread for device that has triggered the condition
		Thread thread = new WorkflowThread(this.getWorkflow(startConditionEvent), device,
				" startConditionEvent: " + condition) {
			public void run() {
				try {
					log.debug("conditionTriggered startConditionEvent: " +
							getWorkflow().getMetadata().getUUID() + 
							", conditionId: " + condition);
					runWorkflow(startConditionEvent);
				} catch (WorkflowException e) {
					log.error("conditionTriggered startConditionEvent: " +
							getWorkflow().getMetadata().getUUID() + e);
					LogManager.getInstance().writeLog(
							Agent.getAgentId(),
							e.getWorkflowId(), 
						    "WorkflowException",
						    e.getMessage(), new Date());
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
	}
	
	
	/**
	 * Returns the workflow an event belongs to.
	 * @param event The event whose workflow is being retrieved
	 * @return The workflow the event belongs to
	 */
	private Workflow getWorkflow(EventComplexType event) {
		return eventWorkflows.get(event);
	}
	
	
	/**
	 * Looks for and returns the value of a given attribute
	 * from local, workflow and device storage
	 * @param name The name of the value
	 * @param localAttributes Map containing all local attributes/values
	 * @return The value of the attribute searched
	 * @throws IOException 
	 * @throws WorkflowException 
	 */
	private String getAttributeValue(String name)
		throws IOException {
		if (registeredPersistentVariables.contains(name)) {
				return StorageManager.getInstance().readDeviceVariable(name);
		} else {
			return ((WorkflowThread) Thread.currentThread()).getLocalAttribute(name);

		}
		// TODO device property??
	}

	
	/**
	 * Sets the value of a given attribute
	 * from local, workflow and device storage
	 * @param name The name of the attribute
	 * @param value The value of the attribute
	 * @param localAttributes Map containing all local attributes/values
	 * @throws IOException 
	 */
	private void setAttributeValue(String name, String value)
		throws IOException {
			if (registeredPersistentVariables.contains(name)) {
				StorageManager.getInstance().writeDeviceVariable(name, value);
			} else {
				((WorkflowThread) Thread.currentThread()).setLocalAttribute(name, value);
			}
		// TODO device properties?
	}
	
	
	/**
	 * Returns the value for a parameter (either as a constant in XML
	 * or from an attribute
	 * @param parameter The parameter whose value is searched
	 * @param localAttributes Map containing all local attributes/values 
	 * @return The value of the parameter
	 * @throws IOException 
	 * @throws WorkflowException 
	 */
	private String getParameterValue(ParameterComplexType parameter) throws IOException {
		String value = parameter.getValue();
		if (value == null)
			value = getAttributeValue(parameter.getAttribute());
		return value;
	}

	/**
	 * Returns the total number of workflows received.
	 * Used for performance testing only.
	 * @return The total number of workflows received
	 */
	public int getWorkflowNumber() {
		return workflowNumber;
	}


}
