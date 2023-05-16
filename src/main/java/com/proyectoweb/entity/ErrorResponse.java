package com.proyectoweb.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
  private String message;
  private HttpStatus status = HttpStatus.CONFLICT;
  private String error;

  public ErrorResponse(String message, HttpStatus status, String error) {
    this.message = message;
    this.status = status;
    this.error = error;
  }

  public ErrorResponse(String message, String error) {
    this.message = message;
    this.error = error;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public ResponseEntity<ErrorResponse> response() {
    return ResponseEntity.status(this.status).body(this);
  }
}
