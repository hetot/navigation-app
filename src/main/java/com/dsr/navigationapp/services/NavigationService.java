package com.dsr.navigationapp.services;

import com.dsr.navigationapp.filesystem.DirectoryInfo;
import com.dsr.navigationapp.filesystem.DirectoryNavigation;
import com.dsr.navigationapp.models.*;
import com.dsr.navigationapp.models.responses.rest.BooleanResponseBody;
import com.dsr.navigationapp.models.responses.rest.DeleteResponseBody;
import com.dsr.navigationapp.models.responses.rest.PathContentResponse;
import com.dsr.navigationapp.utils.MyFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavigationService {
    private static final DirectoryNavigation fileSystem = DirectoryNavigation.getInstance();

    public static BooleanResponseBody rename(String path, String newName) {
        if (!fileSystem.exists(path)) {
            return BooleanResponseBody.builder()
                    .successful(false)
                    .message("current path does not exist")
                    .build();
        }
        return BooleanResponseBody.builder()
                .successful(fileSystem.rename(path, newName))
                .build();
    }

    public static BooleanResponseBody copy(String origin, String destination) {
        return BooleanResponseBody.builder()
                .successful(fileSystem.copy(origin, destination))
                .build();
    }

    public static BooleanResponseBody move(String origin, String destination) {
        return BooleanResponseBody.builder()
                .successful(fileSystem.move(origin, destination))
                .build();
    }

    public static FileInfo getFileInfo(String path) {
        return DirectoryInfo.getFileInfo(path);
    }

    public static PathContentResponse getPathContent(String path, Integer page, Integer size) {
        if (DirectoryNavigation.getInstance().exists(path)) {
            if (Objects.requireNonNull(DirectoryInfo.getType(path)).toLowerCase().contains("directory")) {
                var list = fileSystem.getPaths(path);
                var totalSize = list.size();
                if (size != null) {
                    int low = (page - 1) * size;
                    int high = (page - 1) * size + size;
                    if (totalSize <= high) {
                        high = totalSize;
                    }
                    if (low >= high) {
                        return PathContentResponse.builder()
                                .totalItems(totalSize)
                                .build();
                    }
                    list = list.subList(low, high);
                    return PathContentResponse.builder()
                            .totalItems(totalSize)
                            .currentPage(page)
                            .totalPages((int) Math.ceil((double) totalSize / (double) size))
                            .contents(getFiles(list))
                            .build();
                }
                return PathContentResponse.builder()
                        .totalItems(list.size())
                        .contents(getFiles(list))
                        .build();
            } else {
                FileInfo info = getFileInfo(path);
                if (info.getSize() > 10 * 1048576) {
                    return PathContentResponse.builder().build();
                }
                var totalLines = MyFileReader.countLines(path);
                if (size != null) {
                    int low = (page - 1) * size + 1;
                    int high = (page - 1) * size + size;
                    if (totalLines <= high) {
                        high = totalLines;
                    }
                    if (low >= high) {
                        return PathContentResponse.builder()
                                .totalItems(totalLines)
                                .build();
                    }
                    var lines = MyFileReader.readFile(path, low, high);
                    return PathContentResponse.builder()
                            .totalItems(totalLines)
                            .currentPage(page)
                            .totalPages((int) Math.ceil((double) totalLines / (double) size))
                            .lines(lines)
                            .build();
                }
                return PathContentResponse.builder()
                        .totalItems(totalLines)
                        .lines(MyFileReader.readFile(path))
                        .build();
            }
        }
        return PathContentResponse.builder().build();
    }

    public static List<FileModel> getFiles(List<String> pathList) {
        List<FileModel> response = new ArrayList<>();
        for (String filePath : pathList) {
            response.add(DirectoryInfo.getFile(filePath));
        }
        return response;
    }

    public static DeleteResponseBody removeFile(String path) {
        try {
            DirectoryNavigation.getInstance().remove(path);
            return DeleteResponseBody.builder()
                    .deleted(true)
                    .build();
        } catch (Exception e) {
            return DeleteResponseBody.builder()
                    .deleted(false)
                    .build();
        }
    }
}
