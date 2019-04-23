package net.wizypay.wizypay.utils;

import java.util.Collection;

public class Utils {
    public static boolean isNullOrEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
