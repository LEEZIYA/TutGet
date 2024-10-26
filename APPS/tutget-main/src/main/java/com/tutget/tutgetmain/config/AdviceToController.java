package com.tutget.tutgetmain.config;

import com.tutget.tutgetmain.exception.AuthenticationError;
import com.tutget.tutgetmain.exception.AuthCookieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceToController {
  @ExceptionHandler(AuthCookieNotFoundException.class)
  public ResponseEntity<String> handleUnacceptableness(AuthCookieNotFoundException ex) {
    return ResponseEntity
      .status(HttpStatus.NOT_ACCEPTABLE)
      .body(ex.getMessage());
  }

  @ExceptionHandler(AuthenticationError.class)
  public ResponseEntity<String> handleUnauthenticated(AuthenticationError ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("NO");
  }
}
