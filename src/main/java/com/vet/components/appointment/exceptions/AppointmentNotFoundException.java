package com.vet.components.appointment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Appointment with provided id not found")
public class AppointmentNotFoundException extends RuntimeException{
}
