package org.razorcane.origins.util;

import java.util.regex.Pattern;

public class Formatter {
    private final static Pattern COLOR = Pattern.compile("(?i)&([0-F])");
    
    public static String color(String line) {
        
        return COLOR.matcher(line).replaceAll("\u00A7$1");
    }
}
