package com.vet.validators;

import com.vet.constraints.LocalDateFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class LocalDateFormatterValidator implements ConstraintValidator<LocalDateFormatter, LocalDate> {
    @Override
    public void initialize(LocalDateFormatter constraintAnnotation) {}

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate presentDate = LocalDate.now();
        if(date.isBefore(presentDate))
            return false;
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        String dateToString = date.toString();
        return Pattern.matches(pattern, dateToString);
    }
}
