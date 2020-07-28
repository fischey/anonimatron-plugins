package de.rf.anonimatron.anonymizer;

import java.net.URISyntaxException;

public class PrenameGeneratorTest extends AbstractNameFromFileGeneratorTest {
	@Override
	protected AbstractNameFromFileGenerator getObjectUnderTest() {
		try {
			return new PrenameGenerator();
		} catch (final URISyntaxException e) {
			throw new IllegalArgumentException("PrenameGenerator cannot be instantiated", e);
		}
	}
}