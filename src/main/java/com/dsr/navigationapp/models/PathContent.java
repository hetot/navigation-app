package com.dsr.navigationapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PathContent {
    private boolean successful;
    private boolean isFolder;
    private String content;
    private List<FileModel> files;
}
