package com.lpc.web.server;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
public class CustomerExistException extends ClientErrorException {

  public CustomerExistException(String message, Throwable cause) {
    super(message, Response.Status.BAD_REQUEST, cause);
  }

  public CustomerExistException(String message) {
    super(message, Response.Status.BAD_REQUEST);
  }
}
