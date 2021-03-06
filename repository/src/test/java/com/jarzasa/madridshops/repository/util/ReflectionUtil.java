package com.jarzasa.madridshops.repository.util;

import java.lang.reflect.Method;

public class ReflectionUtil {
    public static boolean hasMethod(Class theClass, String methodName) {
        Method method = getMethod(theClass, methodName);
        if (method != null) {
            return true;
        }

        return false;
    }

    public static Method getMethod(Class theClass, String methodName) {
        Method methodToFind = null;
        try {
            methodToFind = theClass.getMethod(methodName, (Class<?>[]) null);
        } catch (NoSuchMethodException | SecurityException e) {
            // nothing to do here
        }

        return methodToFind;
    }
}
