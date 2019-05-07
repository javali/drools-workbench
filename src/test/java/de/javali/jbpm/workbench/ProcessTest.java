package de.javali.jbpm.workbench;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;

/**
 * @author javali on 07.05.2019.
 */
public class ProcessTest extends JbpmJUnitBaseTestCase {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessTest.class);

	@Test
	public void test() {
		ProcessTest.LOG.debug("jBPM unit test sample");

		RuntimeManager runtimeManager = null;
		try {
			runtimeManager = createRuntimeManager("workflows/simple-switch.bpmn");
		} catch (Exception e) {
			ProcessTest.LOG.error(e.getLocalizedMessage());
			exit(1);
		}
		final RuntimeEngine runtimeEngine = getRuntimeEngine(null);
		final KieSession kieSession = runtimeEngine.getKieSession();

		final ProcessInstance processInstance = kieSession.startProcess("mein-test.simple-switch");

		assertProcessInstanceNotActive(processInstance.getId(), kieSession);
		assertNodeTriggered(processInstance.getId(), "Hello");

		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}
}
