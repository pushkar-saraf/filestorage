package com.benz.files.controller.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class DataDTO {
    String name;
    LocalDate dob;
    BigDecimal salary;


    Integer age;
}
