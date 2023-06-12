package com.dsr.navigationapp.filesystem;

import com.dsr.navigationapp.utils.ConfigReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectoryNavigation {
    private static DirectoryNavigation instance = null;
    private final String homeDir;

    private DirectoryNavigation() {
        homeDir = ConfigReader.getInstance().getConfigPath();
    }

    public static DirectoryNavigation getInstance() {
        if (instance == null) {
            instance = new DirectoryNavigation();
        }
        return instance;
    }

    public List<String> getPaths(String path) {
        List<String> result = new ArrayList<>();
        File tmpFile = new File(path);
        for (String filePath : Objects.requireNonNull(tmpFile.list())) {
            if (path.endsWith("/")) {
                result.add(path + filePath);
            } else {
                result.add(path + "/" + filePath);
            }
        }
        return result;
    }

    public void remove(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isFile()) {
            file.delete();
        } else {
            FileUtils.deleteDirectory(file);
        }
    }

    public boolean rename(String path, String name) {
        File file = new File(path);
        StringBuilder newPath = new StringBuilder();
        String[] paths = path.split("/");
        for (int i = 0; i < paths.length; i++) {
            newPath.append("/");
            if (i == paths.length - 1) {
                newPath.append(name);
            } else {
                newPath.append(paths[i]);
            }
        }
        File newFile = new File(newPath.toString());
        return file.renameTo(newFile);
    }

    public boolean copy(String origin, String destination) {
        File originFile = new File(origin);
        File destinationFile = new File(destination);
        if (originFile.isFile()) {
            try {
                FileUtils.copyFile(originFile, destinationFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            try {
                FileUtils.copyDirectory(originFile, destinationFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public boolean move(String origin, String destination) {
        File originFile = new File(origin);
        File destinationFile = new File(destination);
        if (originFile.isFile()) {
            try {
                FileUtils.moveFile(originFile, destinationFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            try {
                FileUtils.moveDirectory(originFile, destinationFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return true;
    }

    public String getFullPath(String path) {
        return homeDir + path;
    }

    public boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
