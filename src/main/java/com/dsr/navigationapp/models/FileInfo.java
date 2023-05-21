package com.dsr.navigationapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileInfo {
    private String name;
    private long size;
    private String created;
    private String modified;
}
