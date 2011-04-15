package org.commonsregex.replacer;

import java.util.Map;

public class MapReplacer implements RegexReplacer {

	private final Map<String, String> map;

	public MapReplacer(Map<String, String> map) {
		this.map = map;		
	}

	public String replace(String input) {
		return map.get(input);
	}

}
