package com.api.vehicles.config.advisors;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.vehicles.exceptions.VehicleDataExceptions;

@ControllerAdvice
public class ErrorAdvisers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VehicleDataExceptions.class)
    public ResponseEntity<Error> hendler(VehicleDataExceptions vehicleDataExceptions) {
        return new ResponseEntity<>(
             Error.builder().mensagem(vehicleDataExceptions.getMessage()).time(LocalDateTime.now()).status(400).build()
             , HttpStatus.BAD_REQUEST);
    }
}
