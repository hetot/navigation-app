package com.dsr.navigationapp.utils;

import com.dsr.navigationapp.directories.DirectoryInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Objects;

public class MyFileReader {
    private static boolean isText(String path) {
        File file = new File(path);
        if (file.exists()) {
            return Objects.requireNonNull(DirectoryInfo.getType(path)).toLowerCase().contains("text");
        }
        return false;
    }

    public static String readFile(String path) {
        if (isText(path)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                StringBuilder stringBuilder = new StringBuilder();
                char[] buffer = new char[10];
                while (reader.read(buffer) != -1) {
                    stringBuilder.append(new String(buffer));
                    buffer = new char[10];
                }
                reader.close();

                return stringBuilder.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
}
