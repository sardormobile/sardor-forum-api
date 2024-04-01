package com.mbapps.forum.sardorfullstackforum.exceptions;

public class DataNotFoundException extends RuntimeException {
  public DataNotFoundException(Integer productId) {
    super("Data not found with ID: " + productId);
  }

  public DataNotFoundException(String message) {
    super(message);
  }
}