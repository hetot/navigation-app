package com.dsr.navigationapp.models.responses.rest;

import com.dsr.navigationapp.models.FileInfo;
import com.dsr.navigationapp.models.FileModel;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PathContentResponse {
    private Integer totalItems;
    private Integer totalPages;
    private Integer currentPage;
    private List<FileModel> contents;
    private List<String> lines;
}
