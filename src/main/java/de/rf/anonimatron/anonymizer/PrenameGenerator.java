package de.rf.anonimatron.anonymizer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Testdata based on the site https://migano.de/testdaten.php
 */
public class PrenameGenerator extends AbstractNameFromFileGenerator {

	public PrenameGenerator() throws IOException {
		super(PrenameGenerator.class.getResource("/prenames.csv").openStream(), StandardCharsets.UTF_8);
	}

	@Override
	public String getType() {
		return "PRENAME";
	}
}
