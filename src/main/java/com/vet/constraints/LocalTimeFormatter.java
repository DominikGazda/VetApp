package com.vet.constraints;

import com.vet.validators.LocalTimeFormatterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalTimeFormatterValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalTimeFormatter{
    String message() default "Wrong time expression (HH-MM)";
    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default {};
}