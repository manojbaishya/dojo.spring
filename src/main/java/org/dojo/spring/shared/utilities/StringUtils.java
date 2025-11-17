package org.dojo.spring.shared.utilities;

public class StringUtils {
    public static boolean isNotNullOrEmpty(String string) { return string != null && !string.isBlank(); }

    public static boolean isNullOrEmpty(String checkString) { return checkString == null || checkString.isBlank(); }
}
