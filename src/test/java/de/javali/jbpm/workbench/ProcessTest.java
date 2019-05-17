package de.javali.jbpm.workbench;

import de.javali.jbpm.AbstractJbpmJUnitBaseTest;
import org.drools.core.command.runtime.process.SetProcessInstanceVariablesCommand;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.process.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author javali on 07.05.2019.
 * <p>
 * http://www.mtitek.com/tutorials/apps/bpm/hello-jbpm.php
 * https://docs.jboss.org/jbpm/release/7.14.0.Final/jbpm-docs/html_single/
 */
public class ProcessTest extends AbstractJbpmJUnitBaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessTest.class);

	@Test
	public void testProcessExecution() {

		runtimeManager = createRuntimeManager("timestamp.bpmn");
		RuntimeEngine runtimeEngine = getRuntimeEngine(null);
		KieSession kieSession = runtimeEngine.getKieSession();
		ProcessInstance processInstance = kieSession.startProcess("process.timestamp");

		Map<String, Object> variables = new HashMap<>();
		variables.put("timestamp", "startvalue");

		kieSession.execute(new SetProcessInstanceVariablesCommand(processInstance.getId(), variables));

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "log timestamp");

		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}

	@Test
	public void testProcessExecution_withInitialVariableSet_executedWithValue() {
		runtimeManager = createRuntimeManager("timestamp.bpmn");
		RuntimeEngine runtimeEngine = getRuntimeEngine(null);
		KieSession kieSession = runtimeEngine.getKieSession();

		Map<String, Object> params = new HashMap<>();
		params.put("timestamp", "initial value");
		ProcessInstance processInstance = kieSession.startProcess("process.timestamp", params);

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "log timestamp");

		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}
}
