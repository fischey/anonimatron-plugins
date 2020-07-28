package de.rf.anonimatron.anonymizer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.rolfje.anonimatron.anonymizer.Anonymizer;
import com.rolfje.anonimatron.synonyms.StringSynonym;
import com.rolfje.anonimatron.synonyms.Synonym;

public abstract class AbstractNameFromFileGenerator implements Anonymizer {
	private final Set<String> _lines;

	public AbstractNameFromFileGenerator(final InputStream inputStream, final Charset charset) {
		_lines = loadNames(inputStream, charset);
	}

	@Override
	public Synonym anonymize(final Object from, final int size, final boolean shortlived) {
		if (from == null) {
			return new StringSynonym(getType(), null, null, shortlived);
		}

		final String surname = (String) from;
		final Optional<String> sizeAndCharacterMatch = _lines.stream().filter(lessOrEqualSize(size)).filter(firstCharacterMatch(surname)).findAny();
		if (sizeAndCharacterMatch.isPresent()) {
			return new StringSynonym(getType(), surname, sizeAndCharacterMatch.get(), shortlived);
		} else {
			final Optional<String> sizeMatch = _lines.stream().filter(lessOrEqualSize(size)).findAny();
			return new StringSynonym(getType(), surname, sizeMatch.orElseThrow(() -> new IllegalArgumentException("No Surname found for the size <=" + size)), shortlived);
		}
	}

	private Predicate<String> firstCharacterMatch(final String surname) {
		return it -> it.toLowerCase().startsWith(surname.substring(0, 1).toLowerCase());
	}

	private Predicate<String> lessOrEqualSize(final int size) {
		return it -> it.length() <= size;
	}

	private Set<String> loadNames(final InputStream inputStream, final Charset charset) {
		try (final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(inputStream), charset))) {
			return bufferedReader.lines().collect(Collectors.toSet());
		} catch (final IOException e) {
			throw new IllegalArgumentException("InputStream cannot be loaded", e);
		}
	}
}
