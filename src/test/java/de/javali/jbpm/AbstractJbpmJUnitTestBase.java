package de.javali.jbpm;

import org.jbpm.test.JbpmJUnitBaseTestCase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;

/**
 * @author javali on 17.05.2019.
 */
public class AbstractJbpmJUnitTestBase extends JbpmJUnitBaseTestCase {

	protected RuntimeManager runtimeManager;
	protected RuntimeEngine runtimeEngine;
	protected KieSession kieSession;

	protected void createRuntime(String processName) {
		runtimeManager = createRuntimeManager(processName);
		runtimeEngine = getRuntimeEngine(null);
		kieSession = runtimeEngine.getKieSession();
	}

	protected void dispose() {
		runtimeManager.disposeRuntimeEngine(runtimeEngine);
		runtimeManager.close();
	}

}
