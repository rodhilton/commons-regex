package org.commonsregex;

import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;

public class MatcherTest {

    @Test
    public void shouldExtract() {
        String string = "this is my string";
        String regex = "this is my (.*)";

        String actual = RegexUtils.extract(string, regex, 1);

        assertEquals("string", actual);
    }

    @Test
    public void shouldExtractUsingPattern() {
        String string = "this is my string";
        Pattern regex = Pattern.compile("this is my (.*)");

        String actual = RegexUtils.extract(string, regex, 1);

        assertEquals("string", actual);
    }

    @Test
    public void shouldReturnNullWhenNotFound() {
        String string = "this is your string";
        Pattern regex = Pattern.compile("this is my (.*)");

        String actual = RegexUtils.extract(string, regex, 1);

        assertEquals(null, actual);
    }

    @Test
    public void shouldReturnNullWhenGroupInvalid() {
        String string = "this is your string";
        Pattern regex = Pattern.compile("this is my (.*)");

        String actual = RegexUtils.extract(string, regex, -1);

        assertEquals(null, actual);
    }
}
