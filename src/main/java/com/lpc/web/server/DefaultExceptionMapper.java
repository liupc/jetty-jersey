package com.lpc.web.server;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
@Provider
public class DefaultExceptionMapper
    implements ExceptionMapper<WebApplicationException> {

  @AllArgsConstructor
  @Getter
  @XmlRootElement
  public class ErrorMessage {
    @NonNull
    @XmlElement
    private String error;
  }

  @Override
  public Response toResponse(WebApplicationException exception) {
    return Response.status(exception.getResponse().getStatus())
        .entity(new ErrorMessage(exception.getMessage())).build();
  }
}
