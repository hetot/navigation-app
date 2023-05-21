package com.dsr.navigationapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteResponse {
    private boolean deleted;
    private String path;
}
