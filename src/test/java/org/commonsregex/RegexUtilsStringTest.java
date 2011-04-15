package org.commonsregex;

import static org.junit.Assert.*;

import org.commonsregex.RegexUtils;
import org.commonsregex.replacer.RegexReplacer;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;

public class RegexUtilsStringTest {

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
	public void shouldTreatEmptyRegexStringAsMatchingBoundaries() {
		String ret = RegexUtils.replaceAll("hello", "", simpleReplacer);
		assertEquals("AhAeAlAlAoA", ret);
	}
	
	private void assertRegexReplace(String expecting, String source, String regex, RegexReplacer replacer) {
		String ret = RegexUtils.replaceAll(source, regex, replacer);
		assertEquals(expecting, ret);
	}
	
}
