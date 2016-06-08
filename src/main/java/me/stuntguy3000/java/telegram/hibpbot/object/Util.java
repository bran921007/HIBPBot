package me.stuntguy3000.java.telegram.hibpbot.object;

import java.util.regex.Pattern;

/**
 * @author stuntguy3000
 */
public class Util {
    private static Pattern URL_REGEX = Pattern.compile("^(?:(?!-)[\\w-]{1,63}(?<!-)\\.)+(?!-)[\\w-]{1,63}(?<!-)$", Pattern.CASE_INSENSITIVE);

    /**
     * Returns if the input matches the regex validation
     *
     * @param input String the string to be tested
     *
     * @return true if the input is valid
     */
    public static boolean isValidURL(String input) {
        return URL_REGEX.matcher(input).find();
    }
}
