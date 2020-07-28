package de.rf.anonimatron.anonymizer;

import java.io.IOException;

public class PrenameGeneratorTest extends AbstractNameFromFileGeneratorTest {
	@Override
	protected AbstractNameFromFileGenerator getObjectUnderTest() {
		try {
			return new PrenameGenerator();
		} catch (final IOException e) {
			throw new IllegalArgumentException("PrenameGenerator cannot be instantiated", e);
		}
	}
}