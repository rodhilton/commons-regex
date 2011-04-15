package org.commonsregex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.commonsregex.replacer.CapturingRegexReplacer;
import org.commonsregex.replacer.RegexReplacer;

/**
 * Utility class for common regular expression operations.
 *  
 * @author Rod Hilton
 *
 */
public class RegexUtils {

	public static String replaceAll(String source, Pattern pattern, final RegexReplacer replacer) {
		if(replacer == null) {
			return source;
		}
		
		return replaceAll(source, pattern, new CapturingRegexReplacer() {

			public String replace(String... groups) {
				return replacer.replace(groups[0]);
			}
			
		});
	}
	
	public static String replaceAll(String source, String regex, RegexReplacer replacer) {
		return replaceAll(source, Pattern.compile(regex), replacer);
	}
	
	public static String replaceAll(String source, String regex, CapturingRegexReplacer replacer) {
		return replaceAll(source, Pattern.compile(regex), replacer);
	}

	public static String replaceAll(String source, Pattern pattern, CapturingRegexReplacer replacer) {
		if (pattern == null || replacer == null || source == null) {
			return source;
		}
		
		Matcher matcher = pattern.matcher(source);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String[] groups = createGroupArrayFromMatcher(matcher);
			String replacement = replacer.replace(groups);
			if(replacement != null) {
				matcher.appendReplacement(sb, replacement);
			}
		}
		matcher.appendTail(sb);
		return sb.toString();	
	}

	private static String[] createGroupArrayFromMatcher(Matcher m) {
		String[] groups = new String[m.groupCount()+1];
		for(int i=0;i<groups.length;i++) {
			groups[i]=m.group(i);
		}
		return groups;
	}
	
}
