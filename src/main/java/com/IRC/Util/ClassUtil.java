package com.IRC.Util;

public class ClassUtil {
    public static boolean isType(java.lang.Class<?> T, Object obj) {
        try {
            T.cast(obj);
            return true;
        } catch (ClassCastException ex) {
            return false;
        }
    }
}
