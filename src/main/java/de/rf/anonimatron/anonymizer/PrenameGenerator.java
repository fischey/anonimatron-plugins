package de.rf.anonimatron.anonymizer;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * Testdata based on the site https://migano.de/testdaten.php
 */
public class PrenameGenerator extends AbstractNameFromFileGenerator {

	public PrenameGenerator() throws URISyntaxException {
		super(PrenameGenerator.class.getResource("/prenames.csv").toURI(), StandardCharsets.UTF_8);
	}

	@Override
	public String getType() {
		return "PRENAME";
	}
}
