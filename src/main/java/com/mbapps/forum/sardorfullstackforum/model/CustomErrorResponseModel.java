package com.mbapps.forum.sardorfullstackforum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomErrorResponseModel {
  private LocalDateTime timestamp;
  private int status;
  private String error;
  private String message;
  private String path;

}