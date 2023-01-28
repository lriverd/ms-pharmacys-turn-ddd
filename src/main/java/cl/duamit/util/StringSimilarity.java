package cl.duamit.util;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Locale;
import java.util.Objects;

public class StringSimilarity {

	/**
	 * Calculates the similarity (a number within 0 and 1) between two strings.
	 */
	public static double similarity(String s1, String s2) {

		String longer = Objects.toString(s1).toLowerCase(Locale.ROOT);
		String shorter = s2.toLowerCase(Locale.ROOT);
		if (s1.length() < s2.length()) {
			longer = s2;
			shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0; /* both strings are zero length */
		}
		LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
		return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength;

	}

}
