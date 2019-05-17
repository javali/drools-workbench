package de.javali.jbpm;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author javali on 17.05.2019.
 */
public class WorkflowConfig {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

	public static String getTimestamp() {
		return ZonedDateTime.now(ZoneId.of("Europe/Berlin")).format(formatter);
	}
}
