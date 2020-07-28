package de.rf.anonimatron.anonymizer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Testdata based on the site https://migano.de/testdaten.php
 */
public class SurnameGenerator extends AbstractNameFromFileGenerator {
	private static final String TYPE = "SURNAME";

	public SurnameGenerator() throws IOException {
		super(SurnameGenerator.class.getResource("/surnames.csv").openStream(), StandardCharsets.UTF_8);
	}

	@Override
	public String getType() {
		return TYPE;
	}
}
