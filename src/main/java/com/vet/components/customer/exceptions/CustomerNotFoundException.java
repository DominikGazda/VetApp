package com.vet.components.customer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Customer with provided id not found")
public class CustomerNotFoundException extends RuntimeException{
}
