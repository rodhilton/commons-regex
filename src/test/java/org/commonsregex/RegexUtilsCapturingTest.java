package org.commonsregex;

import java.util.regex.Pattern;

import org.commonsregex.RegexUtils;
import org.commonsregex.replacer.CapturingRegexReplacer;
import org.junit.Test;


import static org.junit.Assert.*;

public class RegexUtilsCapturingTest {
	@Test
	public void shouldSupplyReplacerWithCapturingGroup() {
		Pattern pattern = Pattern.compile("he(.*)o w([a-z]+)d");
		RegexUtils.replaceAll("this is hello world!", pattern, new CapturingRegexReplacer() {

			public String replace(String... groups) {
				assertEquals("hello world", groups[0]);
				assertEquals("ll", groups[1]);
				assertEquals("orl", groups[2]);
				return null;
			}
			
		});
	}
	
	@Test
	public void shouldReplaceWithCapturingGroup() {
		Pattern pattern = Pattern.compile("he(.*)o w([a-z]+)d");
		String ret = RegexUtils.replaceAll("this is hello world!", pattern, new CapturingRegexReplacer() {

			public String replace(String... groups) {
				return "SPARTA";
			}
			
		});
		assertEquals("this is SPARTA!", ret);
	}
}
