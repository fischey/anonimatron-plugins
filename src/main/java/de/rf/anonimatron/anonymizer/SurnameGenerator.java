package de.rf.anonimatron.anonymizer;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * Testdata based on the site https://migano.de/testdaten.php
 */
public class SurnameGenerator extends AbstractNameFromFileGenerator {
	private static final String TYPE = "SURNAME";

	public SurnameGenerator() throws URISyntaxException {
		super(SurnameGenerator.class.getResource("/surnames.csv").toURI(), StandardCharsets.UTF_8);
	}

	@Override
	public String getType() {
		return TYPE;
	}
}
