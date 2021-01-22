package com.vet.constraints;

import com.vet.validators.LocalDateFormatterValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LocalDateFormatterValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateFormatter {
    String message() default "Wrong date expression (YYYY-MM-DD) or past date provided";
    Class<?>[]groups() default {};
    Class<? extends Payload> [] payload() default {};
}
