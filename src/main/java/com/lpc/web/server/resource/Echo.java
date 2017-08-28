package com.lpc.web.server.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Copyright 2017, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
@Path("/home")
public class Echo {

  @GET
  @Path("/hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String echoHello(){
    return "hello";
  }
}
