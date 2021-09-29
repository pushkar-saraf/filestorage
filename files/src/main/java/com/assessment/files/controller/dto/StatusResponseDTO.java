package com.assessment.files.controller.dto;

import lombok.Value;

@Value
public class StatusResponseDTO {
    public static final StatusResponseDTO SUCCESS = new StatusResponseDTO("Success", "");

    public static StatusResponseDTO failed(String message) {
        return new StatusResponseDTO("Failed", message);
    }

    String status;
    String message;
}
