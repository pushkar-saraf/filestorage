package com.benz.files.controller.constraint;

import com.benz.files.controller.constraint.validator.DataDTOValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DataDTOValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDataDTO {
    String message() default "Invalid age for provided DoB";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}