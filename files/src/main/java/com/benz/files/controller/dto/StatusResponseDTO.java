package com.benz.files.controller.dto;

import lombok.Value;

@Value
public class StatusResponseDTO {
    public static final StatusResponseDTO SUCCESS = new StatusResponseDTO("Success");
    public static final StatusResponseDTO FAILED = new StatusResponseDTO("Failed");

    String status;
}
