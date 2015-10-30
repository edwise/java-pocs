package com.edwise.pocs.jaxrswildcards;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class Application extends ResourceConfig {

    public Application() {
        packages("com.edwise.pocs.jaxrswildcards");
    }
}
