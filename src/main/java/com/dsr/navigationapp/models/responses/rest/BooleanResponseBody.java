package com.dsr.navigationapp.models.responses.rest;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooleanResponseBody {
    private boolean successful;
    private String message;
}
