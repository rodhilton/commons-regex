package org.commonsregex;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.commonsregex.RegexUtils;
import org.commonsregex.replacer.RegexReplacer;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class RegexUtilsPatternTest {

	RegexReplacer simpleReplacer;
	
	@Before
    public void setUp() {
         simpleReplacer = mock(RegexReplacer.class);
         when(simpleReplacer.replace(anyString())).thenReturn("A");
    }
	
	@Test
	public void shouldReplaceSimpleSequence() {
		assertRegexReplace("AAAAA", "hello", "[a-z]", simpleReplacer);
	}
	
	@Test
	public void shouldReplaceSimpleSequenceSeparatedByNonMatchingCharacters() {
		assertRegexReplace("AAAAA AAAAA", "hello world", "[a-z]", simpleReplacer);
	}
	
	@Test
	public void shouldNotAffectNonMatchingString() {
		assertRegexReplace("HELLO WORLD", "HELLO WORLD", "[a-z]", simpleReplacer);
	}
	
	@Test
	public void shouldHandleNullSource() {
		assertRegexReplace(null, null, "[a-z]", simpleReplacer);
	}
	
	@Test
	public void shouldHandleNullPattern() {
		Pattern pattern=null;
		String ret = RegexUtils.replaceAll("hello", pattern, simpleReplacer);
		assertEquals("hello", ret);
	}	
	
	@Test
	public void shouldHandleNullReplacer() {
		Pattern pattern=Pattern.compile("[a-z]");
		RegexReplacer replacer = null;
		String ret = RegexUtils.replaceAll("hello world", pattern, replacer);
		assertEquals("hello world", ret);
	}
	
	@Test
	public void shouldHandleNullReplacement() {
		Pattern pattern=Pattern.compile("[a-z]");
		String ret = RegexUtils.replaceAll("hello world", pattern, new RegexReplacer() {

			public String replace(String input) {
				return null;
			}
			
		});
		assertEquals("hello world", ret);
	}
	
	
	@Test
	public void shouldReplaceComplexSequence() {
		assertRegexReplace("HELlo worlD", "hello worlD", "^[a-z]{3}", new RegexReplacer() {
			public String replace(String input) {
				return input.toUpperCase();
			}
		});
	}
	
	@Test
	public void shouldReplaceReallyComplexSequence() {
		//Replace any e that is followed by an i with an upper case version
		assertRegexReplace("aEiou aeou", "aeiou aeou", "e(?=i)", new RegexReplacer() {
			public String replace(String input) {
				return input.toUpperCase();
			}
		});
	}
	
	private void assertRegexReplace(String expecting, String source, String regex, RegexReplacer replacer) {
		Pattern pattern=Pattern.compile(regex);
		String ret = RegexUtils.replaceAll(source, pattern, replacer);
		assertEquals(expecting, ret);
	}
	
}
