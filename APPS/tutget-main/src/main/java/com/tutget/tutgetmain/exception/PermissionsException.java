package com.tutget.tutgetmain.exception;

public class PermissionsException extends RuntimeException {
  public PermissionsException() {
    super("NOT ALLOWED");
  }
}
