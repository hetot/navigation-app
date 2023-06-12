package com.dsr.navigationapp.utils;

import com.dsr.navigationapp.filesystem.DirectoryNavigation;

public class CommonUtils {
    public static String extractPath(String uri) {
        return DirectoryNavigation.getInstance()
                .getFullPath(uri.replace("/api/v1/web/directories", ""));
    }
}
