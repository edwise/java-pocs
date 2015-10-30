package com.edwise.pocs.jaxrswildcards.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("foo")
public class FooResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllFoos() {
        return "All foos";
    }

    @GET
    @Path("wildcard/{subpath : .+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getWithWildcard(@PathParam("subpath") String subpath) {
        return subpath;
    }
}
