package de.rf.anonimatron.anonymizer;

import java.io.IOException;

public class SurnameGeneratorTest extends AbstractNameFromFileGeneratorTest {
	@Override
	protected AbstractNameFromFileGenerator getObjectUnderTest() {
		try {
			return new SurnameGenerator();
		} catch (final IOException e) {
			throw new IllegalArgumentException("SurnameGenerator cannot be created", e);
		}
	}
}