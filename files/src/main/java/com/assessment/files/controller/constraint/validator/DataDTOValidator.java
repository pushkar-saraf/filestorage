package com.assessment.files.controller.constraint.validator;

import com.assessment.files.controller.constraint.ValidDataDTO;
import com.assessment.files.controller.dto.DataDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataDTOValidator implements ConstraintValidator<ValidDataDTO, DataDTO> {

    @Override
    public boolean isValid(DataDTO value, ConstraintValidatorContext context) {
        return value.getAge() == LocalDate.now().getYear() - value.getDob().getYear();
    }
}
