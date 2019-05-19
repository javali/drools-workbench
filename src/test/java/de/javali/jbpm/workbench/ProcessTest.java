package de.javali.jbpm.workbench;

import de.javali.jbpm.AbstractJbpmJUnitTestBase;
import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.drools.core.time.impl.PseudoClockScheduler;
import org.junit.Test;
import org.kie.api.runtime.process.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author javali on 07.05.2019.
 * <p>
 * http://www.mtitek.com/tutorials/apps/bpm/hello-jbpm.php
 * https://docs.jboss.org/jbpm/release/7.14.0.Final/jbpm-docs/html_single/
 * https://www.programcreek.com/java-api-examples/?class=org.kie.api.runtime.KieSession&method=startProcess
 */
public class ProcessTest extends AbstractJbpmJUnitTestBase {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessTest.class);

	@Test
	public void testProcessExecution_bpmn2LegacyProcess_succeeds() {

		createRuntime("timer.bpmn2");

		ProcessInstance processInstance = kieSession.startProcess("de.javali.jbpm.process.timer");
		Map<String, Object> variables = new HashMap<>();
		variables.put("timestamp", "startvalue");

		kieSession.execute(new SetProcessInstanceVariablesCommand(processInstance.getId(), variables));

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);

		dispose();
	}

	@Test
	public void testProcessExecution() {

		createRuntime("timestamp.bpmn");
		ProcessInstance processInstance = kieSession.startProcess("process.timestamp");

		Map<String, Object> variables = new HashMap<>();
		variables.put("timestamp", "startvalue");

		kieSession.execute(new SetProcessInstanceVariablesCommand(processInstance.getId(), variables));

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "log timestamp");

		dispose();
	}

	@Test
	public void testProcessExecution_withInitialVariableSet_executedWithValue() {
		createRuntime("timestamp.bpmn");

		Map<String, Object> params = new HashMap<>();
		params.put("timestamp", "initial value");
		ProcessInstance processInstance = kieSession.startProcess("process.timestamp", params);

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "log timestamp");

		dispose();
	}

	/**
	 * Testcase for scheduler based execution.
	 */
	@Test
	public void testProcessExecution_withPseudoTimeScale_executedWithValue() {
		System.setProperty("drools.clockType", "pseudo");

		createRuntime("timer2.bpmn");
		PseudoClockScheduler sessionClock = kieSession.getSessionClock();

		Map<String, Object> params = new HashMap<>();
		params.put("timestamp", "initial value");
		ProcessInstance processInstance = kieSession.startProcess("process.timer2", params);

		// add one day
		sessionClock.advanceTime(1, TimeUnit.DAYS);
		long currentTime = sessionClock.getCurrentTime();
		logTime(currentTime);

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "Task");

		dispose();
	}

	private void logTime(long currentTime) {
		Date date = new Date(currentTime*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		String formattedDate = sdf.format(date);
		LOG.info("time in process scheduler is now {}", formattedDate);
	}


}
