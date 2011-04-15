package org.commonsregex;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.commonsregex.RegexUtils;
import org.commonsregex.replacer.CapturingRegexReplacer;
import org.commonsregex.replacer.MapReplacer;
import org.commonsregex.replacer.RegexReplacer;
import org.junit.Test;

/**
 * This is an end-to-end test.  Call the RegexUtils method with actual RegexReplacer instances.  This is mainly where I wrote out some
 * examples of how to use the library which I put on the documentation wiki page.
 * 
 * @author Rod Hilton
 *
 */
public class RegexUtilsFunctionalTest {

	@Test
	public void shouldCapitalizeFirstCharacter() {
		Pattern pattern = Pattern.compile("^[a-z]");
		String ret = RegexUtils.replaceAll("just now", pattern, new RegexReplacer() {

			public String replace(String input) {
				return input.toUpperCase();
			}

		});
		assertEquals("Just now", ret);
	}

	@Test
	public void shouldReplaceHello() {
		String originalText = "Hello world!";

		Pattern pattern = Pattern.compile("[Hh]ello");

		String replacement = RegexUtils.replaceAll(originalText, pattern, new RegexReplacer() {
			public String replace(String input) {
				return "Hi";
			}
		});

		assertEquals("Hi world!", replacement);
	}

	@Test
	public void shouldCapitalizeString() {
		String originalText = "the quick brown fox jumps right over the lazy dog.";

		Pattern pattern = Pattern.compile("\\b[a-z]");

		String replacement = RegexUtils.replaceAll(originalText, pattern, new RegexReplacer() {
			public String replace(String input) {
				return input.toUpperCase();
			}
		});

		assertEquals("The Quick Brown Fox Jumps Right Over The Lazy Dog.", replacement);
	}

	@Test
	public void shouldReplaceWithMapReplacer() {
		String source = "This is a string with __hangman__ style __variables__.  Lots of __variables__";

		Pattern hangmanPattern = Pattern.compile("__\\w+__");

		Map<String, String> variableMap = new HashMap<String, String>() {{
			put("__hangman__", "O-<-<");
			put("__variables__", "e=mc^2");
		}};

		String replacement = RegexUtils.replaceAll(source, hangmanPattern, new MapReplacer(variableMap));
		assertEquals("This is a string with O-<-< style e=mc^2.  Lots of e=mc^2", replacement);
	}

	@Test
	public void shouldReplaceUsingGroups() {
		String originalText = "There's shrimp soup, shrimp stew, shrimp salad, shrimp burger, shrimp sandwich...";

		Pattern pattern = Pattern.compile("shrimp\\s+(\\S+)");

		String replacement = RegexUtils.replaceAll(originalText, pattern, new CapturingRegexReplacer() {
			public String replace(String... groups) {
				return groups[1].toUpperCase();
			}
		});

		assertEquals("There's SOUP, STEW, SALAD, BURGER, SANDWICH...", replacement);

	}
}
