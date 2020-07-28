package de.rf.anonimatron.anonymizer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

import com.rolfje.anonimatron.synonyms.Synonym;

public abstract class AbstractNameFromFileGeneratorTest {

	@Test
	public void generateName() {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		final Synonym anonymize = objectUnderTest.anonymize("Müller", 100, false);

		assertThat(anonymize.getTo()).isNotNull().isNotEqualTo("Müller");
	}

	protected abstract AbstractNameFromFileGenerator getObjectUnderTest();

	@Test
	public void nameStartsWithSameCharacterIfPossible() {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		final Synonym anonymize = objectUnderTest.anonymize("Müller", 100, false);

		assertThat((String) anonymize.getTo()).isNotNull().isNotEqualTo("Müller").startsWith("M");
	}

	@Test
	public void respectMaxLength() {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		final Synonym anonymize = objectUnderTest.anonymize("Müller", 5, false);

		assertThat((String) anonymize.getTo()).isNotNull().isNotEqualTo("Müller").satisfies(synonym -> assertThat(synonym.length()).isLessThanOrEqualTo(5));
	}

	@Test
	public void smallLengthsWillBeAProblem() {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		try {
			objectUnderTest.anonymize("Müller", 1, false);
		} catch (final Exception e) {
			assertThat(e).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("No Surname found for the size <=1");
		}
	}

	@Test
	public void nullWillProduceNullSynonym() {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		final Synonym synonym = objectUnderTest.anonymize(null, 100, false);

		assertThat(synonym).isNotNull().extracting(Synonym::getTo).isNull();
	}

	@Test
	public void inputIsNotTheSameAsOutput() throws URISyntaxException, IOException {
		final AbstractNameFromFileGenerator objectUnderTest = getObjectUnderTest();

		final Stream<String> lines = Files.lines(Paths.get(getClass().getResource("/surnames.csv").toURI()), StandardCharsets.UTF_8);

		lines.forEach(line -> {
			final Synonym synonym = objectUnderTest.anonymize(line, 100, false);
			System.out.println(line + " | " + synonym.getTo());
			assertThat(synonym).isNotNull().isNotEqualTo(line);
		});
	}
}
