package com.dsr.navigationapp.utils;

public class ValidatorClass {
    public static boolean validatePath(String path) {
        return !path.contains("/../") && !path.startsWith("../") && !path.endsWith("/..");
    }
}
