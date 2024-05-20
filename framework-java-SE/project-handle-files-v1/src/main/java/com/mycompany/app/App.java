package com.mycompany.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {

    public static void main(String[] args) throws Exception {
        // Create a basic Jetty server object without declaring the port.
        Server server = new Server(8000);

        // The ServletContextHandler is a Jetty specific handler that takes care of
        // mapping servlets to URLs.
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add the FileUploadServlet
        context.addServlet(new ServletHolder(new FileUploadServlet()), "/files");

        // Start the server
        server.start();
        server.join();
    }
}
