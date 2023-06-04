package com.dsr.navigationapp.directories;

import com.dsr.navigationapp.models.FileInfo;
import com.dsr.navigationapp.models.FileModel;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryInfo {
    public static FileInfo getFileInfo(String filePath) {
        File file = new File(filePath);
        String createdTime = null;
        String lastModifiedTime = null;
        Path path = Path.of(filePath);
        try {
            createdTime = String.valueOf(Files.getAttribute(path, "creationTime"));
            lastModifiedTime = String.valueOf(Files.getLastModifiedTime(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (file.isFile()) {
            return new FileInfo(file.getName(), file.length(), createdTime, lastModifiedTime);
        }
        return new FileInfo(file.getName(), FileUtils.sizeOfDirectory(file), createdTime, lastModifiedTime);
    }

    public static FileModel getFile(String filePath) {
        FileModel fileModel;
        File file = new File(filePath);
        try {
            fileModel = new FileModel(file.getName(), getType(filePath));
            return fileModel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String getType(String filePath) {
        try {
            ProcessBuilder pb = new ProcessBuilder("sh", "-c", "file " + "'" + filePath + "'");
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                return line.split(": ")[1];
            } else {
                return "Undefined";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Undefined";
        }
    }
}
