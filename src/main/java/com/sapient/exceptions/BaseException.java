package com.sapient.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends Exception {

  private final String msg;

  public BaseException(String msg) {
    this.msg = msg;
  }
}
