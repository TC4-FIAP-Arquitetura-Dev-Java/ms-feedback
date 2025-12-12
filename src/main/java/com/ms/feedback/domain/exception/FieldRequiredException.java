package com.ms.feedback.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FieldRequiredException extends ResponseStatusException {

    public FieldRequiredException(String message) {
      super(HttpStatus.BAD_REQUEST, message);
    }
}
