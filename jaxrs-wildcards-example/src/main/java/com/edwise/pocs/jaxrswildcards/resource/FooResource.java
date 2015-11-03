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
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFooById(@PathParam("id") String id) {
        return "Foo with id: " + id;
    }

    @GET
    @Path("twoparams/{firstname}-{surname}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFooWithNameAndSurname(@PathParam("firstname") String firstname, @PathParam("surname") String surname) {
        return firstname + " " + surname;
    }

    @GET
    @Path("onlyinteger/{id : \\d+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFooWithIntegerId(@PathParam("id") int id) {
        return Integer.toString(id);
    }

    @GET
    @Path("wildcard/{subpath : .+}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFooWithWildcard(@PathParam("subpath") String subpath) {
        return subpath;
    }
}
