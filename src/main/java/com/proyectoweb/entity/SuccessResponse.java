package com.proyectoweb.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SuccessResponse {
  private String message;
  private Object data;
  private HttpStatus status = HttpStatus.OK;

  public SuccessResponse(String message, Object data) {
    this.message = message;
    this.data = data;
  }

  public SuccessResponse(String message, HttpStatus status, Object data) {
    this.message = message;
    this.data = data;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public ResponseEntity<SuccessResponse> response() {
    return ResponseEntity.status(this.status).body(this);
  }
}
