package com.dsr.navigationapp.genarators;

import com.dsr.navigationapp.directories.DirectoryInfo;
import com.dsr.navigationapp.directories.DirectoryNavigation;
import com.dsr.navigationapp.models.*;
import com.dsr.navigationapp.utils.MyFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BodyGenerator {
    public static BooleanResponse rename(String path, String newName) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return new BooleanResponse(DirectoryNavigation.getInstance().rename(path, newName));
    }

    public static BooleanResponse copy(String origin, String destination) {
        origin = DirectoryNavigation.getInstance().getFullPath(origin);
        destination = DirectoryNavigation.getInstance().getFullPath(destination);
        return new BooleanResponse(DirectoryNavigation.getInstance().copy(origin, destination));
    }

    public static BooleanResponse move(String origin, String destination) {
        origin = DirectoryNavigation.getInstance().getFullPath(origin);
        destination = DirectoryNavigation.getInstance().getFullPath(destination);
        return new BooleanResponse(DirectoryNavigation.getInstance().move(origin, destination));
    }


    public static List<FileModel> getFiles(String path) {
        List<FileModel> response = new ArrayList<>();
        for (String filePath : DirectoryNavigation.getInstance().getPaths(path)) {
            response.add(DirectoryInfo.getFile(filePath));
        }
        return response;
    }

    public static FileInfo getFileInfo(String path) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return DirectoryInfo.getFileInfo(path);
    }

    public static PathContent getFileContent(String path) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        if (DirectoryNavigation.getInstance().exists(path)) {
            if (Objects.requireNonNull(DirectoryInfo.getType(path)).toLowerCase().contains("directory")) {
                return new PathContent(true, true, null, getFiles(path));
            } else {
                String content = MyFileReader.readFile(path);
                if (content != null) {
                    return new PathContent(true, false, content, null);
                }
            }
        }
        return new PathContent(false, false, null, null);
    }

    public static DeleteResponse removeFile(String path) {
        path = DirectoryNavigation.getInstance().getFullPath(path);
        return new DeleteResponse(DirectoryNavigation.getInstance().remove(path), path);
    }
}
