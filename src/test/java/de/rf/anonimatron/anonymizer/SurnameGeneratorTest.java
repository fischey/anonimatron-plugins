package de.rf.anonimatron.anonymizer;

import java.net.URISyntaxException;

public class SurnameGeneratorTest extends AbstractNameFromFileGeneratorTest {
	@Override
	protected AbstractNameFromFileGenerator getObjectUnderTest() {
		try {
			return new SurnameGenerator();
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException("SurnameGenerator cannot be created", e);
		}
	}
}