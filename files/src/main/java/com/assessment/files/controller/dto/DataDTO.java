package com.assessment.files.controller.dto;

import com.assessment.files.controller.constraint.ValidDataDTO;
import com.assessment.files.controller.constraint.groups.Additional;
import com.assessment.files.controller.constraint.groups.Basic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@ValidDataDTO(groups = Additional.class)
@GroupSequence({DataDTO.class, Basic.class, Additional.class})
@NoArgsConstructor
@Data
public class DataDTO {

    @NotBlank(groups = Basic.class)
    private String name;

    @NotNull(groups = Basic.class)
    private LocalDate dob;

    @Min(value = 0, groups = Additional.class)
    @NotNull(groups = Basic.class)
    private BigDecimal salary;

    @Min(value = 0, groups = Additional.class)
    @Max(value = 100, groups = Additional.class)
    @NotNull(groups = Basic.class)
    private Integer age;
}
