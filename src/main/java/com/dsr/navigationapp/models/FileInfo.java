package com.dsr.navigationapp.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private long size;
    private String created;
    private String modified;
}
