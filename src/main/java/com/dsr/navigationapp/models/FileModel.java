package com.dsr.navigationapp.models;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileModel {
    private String path;
    private String type;
}
