package com.tutget.tutgetmain.config;

import com.tutget.tutgetmain.exception.PermissionsException;
import com.tutget.tutgetmain.exception.AuthCookieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceToController {
  @ExceptionHandler(AuthCookieNotFoundException.class)
  public ResponseEntity<String> handleUnacceptableness(AuthCookieNotFoundException ex) {
    System.out.println("In advice to controller");
    return ResponseEntity
      .status(HttpStatus.NOT_ACCEPTABLE)
      .body(ex.getMessage());
  }

  @ExceptionHandler(PermissionsException.class)
  public ResponseEntity<String> handleForbidden(PermissionsException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("NOT ALLOWED");
  }
}
