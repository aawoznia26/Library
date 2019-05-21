package com.crud.kodillalibrary.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionHandlingController {


    @ExceptionHandler(NullPointerException.class)
    public final String handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return "That object does not exist";

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return "The rider has already rent that book";

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public final String handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return "That object does not exist";

    }
}
