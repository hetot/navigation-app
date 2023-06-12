package com.dsr.navigationapp.models.responses.rest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteResponseBody {
    private boolean deleted;
}
