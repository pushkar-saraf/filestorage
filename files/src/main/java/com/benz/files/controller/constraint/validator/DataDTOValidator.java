package com.benz.files.controller.constraint.validator;

import com.benz.files.controller.constraint.ValidDataDTO;
import com.benz.files.controller.dto.DataDTO;
import org.apache.tomcat.jni.Local;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.time.LocalDate;

public class DataDTOValidator implements ConstraintValidator<ValidDataDTO, DataDTO> {

    @Override
    public boolean isValid(DataDTO value, ConstraintValidatorContext context) {
        return value.getAge() == LocalDate.now().getYear() - value.getDob().getYear();
    }
}
