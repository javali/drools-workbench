package de.javali.jbpm;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author javali on 17.05.2019.
 */
public class WorkflowConfigTest {

	@Test
	public void testGetTimestamp() {
		String timestamp = WorkflowConfig.getTimestamp();

		assertNotNull(timestamp);
	}

}
