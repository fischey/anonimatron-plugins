package com.rolfje.anonimatron.anonymizer;

import java.io.IOException;
import java.util.Random;

public class FantasyNameGenerator extends AbstractNameGenerator {
	private static final String TYPE = "FANTASY_NAME";
	private static final String SYLABLE_FILE = "fantasy-names.syl";

	private static final Random r = new Random();

	public FantasyNameGenerator() throws IOException {
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
