package com.edwise.pocs.jerseyrest;

import com.edwise.pocs.jerseyrest.service.UserService;
import com.edwise.pocs.jerseyrest.service.impl.UserServiceMock;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("com.edwise.pocs.jerseyrest");

        // Si usamos un JEE container (Glassfish, TomEE, etc), este registro no es necesario.
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new UserServiceMock()).to(UserService.class);
            }
        });

        register(JacksonFeature.class);
    }
}
