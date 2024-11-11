package com.tutget.tutgetmain.dto;

public record ProfileDTO (
  String id,
  String acadLvl,
  String userID,
  String userType,
  String firstName,
  String postalCode,
  String authenticateStatus
) {}
