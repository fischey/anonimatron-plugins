package com.rolfje.anonimatron.anonymizer;

import java.io.IOException;
import java.util.Random;

public class GoblinNameGenerator extends AbstractNameGenerator {
	private static final String TYPE = "GOBLIN_NAME";
	private static final String SYLABLE_FILE = "goblin-names.syl";

	private static final Random r = new Random();

	public GoblinNameGenerator() throws IOException {
		super(SYLABLE_FILE);
	}

	public String getType() {
		return TYPE;
	}

	String getName() {
		// Generate a name of 2 to 5 sylables
		final int syls = Math.round((r.nextFloat() * 5) + 2);
		return compose(syls);
	}
}
