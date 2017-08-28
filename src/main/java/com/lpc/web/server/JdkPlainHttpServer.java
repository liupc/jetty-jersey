package com.lpc.web.server;

import com.lpc.web.server.model.CustomerDao;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Copyright 2016, Xiaomi.
 * All rights reserved.
 * Author: pengcheng@xiaomi.com
 */
public class JdkPlainHttpServer {

  public static void main(String[] args) throws Exception {
    ResourceConfig config = new ResourceConfig();
    config.packages("com.lpc.web.server");
    config.register(new AbstractBinder() {
      @Override
      protected void configure() {
//        bind(CustomerDao.class).to(Customers.class).in(Singleton.class);
        bind(new CustomerDao()).to(CustomerDao.class);
      }
    });
    ServletHolder servlet = new ServletHolder(new
        ServletContainer(config));

    Server server = new Server(8080);
    HandlerCollection handlers = new HandlerCollection();
    ServletContextHandler context = new ServletContextHandler(handlers, "/api");
    context.addServlet(servlet, "/*");

    ResourceHandler resourceHandler = new ResourceHandler();
    String userDir = System.getProperty("user.dir");
    resourceHandler.setBaseResource(Resource.newResource(userDir + "/static"));
//    resourceHandler.setResourceBase(userDir + "/generated/swagger-ui");
    resourceHandler.setDirectoriesListed(true);
    ContextHandler resourceContext = new ContextHandler("/static");
    resourceContext.setHandler(resourceHandler);
    handlers.addHandler(resourceContext);

    server.setHandler(handlers);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }

  }
}
