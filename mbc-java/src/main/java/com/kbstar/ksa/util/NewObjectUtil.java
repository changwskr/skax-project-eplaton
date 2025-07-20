package com.kbstar.ksa.util;

/**
 * Stub class for ObjectUtil
 */
public class NewObjectUtil {

    public NewObjectUtil() {
        // Stub constructor
    }

    // Add stub methods as needed
    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    public static String toString(Object obj) {
        return obj != null ? obj.toString() : null;
    }

    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2)
            return true;
        if (obj1 == null || obj2 == null)
            return false;
        return obj1.equals(obj2);
    }
}