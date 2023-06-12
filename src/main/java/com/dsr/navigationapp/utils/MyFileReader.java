package com.dsr.navigationapp.utils;

import com.dsr.navigationapp.filesystem.DirectoryInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyFileReader {
    private static boolean isText(String path) {
        File file = new File(path);
        if (file.exists()) {
            return Objects.requireNonNull(DirectoryInfo.getType(path)).toLowerCase().contains("text");
        }
        return false;
    }

    public static int countLines(String path) {
        if (isText(path)) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                int lines = 0;
                while (reader.readLine() != null) lines++;
                reader.close();
                return lines;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return -1;
    }

    public static List<String> readFile(String path) {
        if (isText(path)) {
            try {
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = reader.readLine();
                }
                reader.close();

                return lines;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }

    public static List<String> readFile(String path, int low, int high) {
        if (isText(path)) {
            try {
                int counter = 0;
                List<String> lines = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = reader.readLine();
                while (line != null) {
                    counter++;
                    if (counter >= low && counter <= high) {
                        lines.add(line);
                    }
                    line = reader.readLine();
                }
                reader.close();

                return lines;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return null;
    }
}
