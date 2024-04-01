package com.mbapps.forum.sardorfullstackforum.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponseModel {
  private int statusCode;
  private String message;
  private Boolean status;
}