package com.benz.files.controller.dto;

import com.benz.files.controller.constraint.ValidDataDTO;
import com.benz.files.controller.constraint.groups.Additional;
import com.benz.files.controller.constraint.groups.Basic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Value
@ValidDataDTO(groups = Additional.class)
@GroupSequence({DataDTO.class, Basic.class, Additional.class})
public class DataDTO {

    @NotBlank(groups = Basic.class)
    String name;

    @NotNull(groups = Basic.class)
    LocalDate dob;

    @Min(value = 0, groups = Additional.class)
    @NotNull(groups = Basic.class)
    BigDecimal salary;

    @Min(value = 0, groups = Additional.class)
    @Max(value = 100, groups = Additional.class)
    @NotNull(groups = Basic.class)
    Integer age;
}
