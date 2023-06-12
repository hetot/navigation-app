package com.dsr.navigationapp.models.requests.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenameRequestBody {
    private String path;
    private String newName;
}
