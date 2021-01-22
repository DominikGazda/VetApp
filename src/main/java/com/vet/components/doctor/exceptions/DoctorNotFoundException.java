package com.vet.components.doctor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Doctor with provided id not found")
public class DoctorNotFoundException extends RuntimeException{
}
