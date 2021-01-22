package com.vet.validators;

import com.vet.constraints.LocalTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class LocalTimeFormatterValidator implements ConstraintValidator<LocalTimeFormatter, LocalTime> {
    @Override
    public void initialize(LocalTimeFormatter constraintAnnotation) {}

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {
        String date = localTime.toString();
        String pattern = "^(\\d\\d:\\d\\d)";
        return Pattern.matches(pattern, date);
    }
}
