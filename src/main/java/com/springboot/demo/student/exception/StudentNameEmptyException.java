package com.springboot.demo.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentNameEmptyException extends RuntimeException {

    public StudentNameEmptyException(String exception) {
        super(exception);
    }
}
