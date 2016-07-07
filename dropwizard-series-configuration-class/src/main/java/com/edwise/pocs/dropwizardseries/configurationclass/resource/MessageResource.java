package com.edwise.pocs.dropwizardseries.configurationclass.resource;

import com.edwise.pocs.dropwizardseries.configurationclass.model.Message;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    private static final String HELLO_WORLD_SENTENCE = "Hello world %s, from %s!";

    private final String host;
    private final String defaultName;

    public MessageResource(String host, String defaultName) {
        this.host = host;
        this.defaultName = defaultName;
    }

    @GET
    public Message getHelloWorldMessage(@QueryParam("name") String name) {
        return new Message(String.format(HELLO_WORLD_SENTENCE, name != null ? name : defaultName, host));
    }
}
