package com.tutget.tutgetmain.exception;

public class AuthCookieNotFoundException extends RuntimeException {
  public AuthCookieNotFoundException() {
    super("Auth Cookie not found!");
  }
}
