package com.ms.feedback.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeedbackNotFoundException extends ResponseStatusException {

  public FeedbackNotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
    }
}
