package com.rolfje.anonimatron.anonymizer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

public class GoblinNameGeneratorTest {
	Logger LOG = Logger.getLogger(GoblinNameGenerator.class);

	@Test
	public void testUniqueness() throws Exception {
		final GoblinNameGenerator r = new GoblinNameGenerator();
		final Set<String> names = new HashSet<>();

		for (int i = 0; i < 10000; i++) {
			final String name = (String) r.anonymize("fakename", 100, false).getTo();
			assertFalse("The name " + name
					+ " was already generated. This is iteration " + i,
					names.contains(name));
			names.add(name);
		}
	}

	@Test
	public void testNullFrom() throws Exception {
		final GoblinNameGenerator r = new GoblinNameGenerator();
		assertNull(r.anonymize(null, 100, false).getTo());
	}

	@Test
	@Ignore("skip for performance reasons")
	public void testMaxNames() throws Exception {

		final GoblinNameGenerator r = new GoblinNameGenerator();
		final Set<String> names = new HashSet<>();

		try {
			String name;
			boolean wasInSet;
			do {
				name = (String) r.anonymize("fakename", 100, false).getTo();
				wasInSet = names.contains(name);
				names.add(name);
			} while (!wasInSet);
		} catch (final UnsupportedOperationException e) {
			// expected, generator crashes when it runs out of names.
		}

		LOG.info("GoblinNameGenerator can generate " + names.size()
				+ " unique names.");
	}
}
