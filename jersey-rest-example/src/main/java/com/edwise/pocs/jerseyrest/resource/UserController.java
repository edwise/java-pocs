package com.edwise.pocs.jerseyrest.resource;

import com.edwise.pocs.jerseyrest.entity.User;
import com.edwise.pocs.jerseyrest.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("users")
public class UserController {

    @Inject
    private UserService userService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long userId) {
        Response response;
        User user = userService.findById(userId);
        if (user == null) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(user).build();
        }
        return response;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(User user) {
        User userSaved = userService.save(user);
        return Response.created(createURIFromResource(userSaved)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") long userId, User user) {
        Response response;
        User userOld = userService.findById(userId);
        if (userOld != null) {
            userService.update(userOld.copyFrom(user));
            response = Response.status(Response.Status.NO_CONTENT).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long userId) {
        userService.delete(userId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    private URI createURIFromResource(User userSaved) {
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        return uriBuilder.path(userSaved.getId().toString()).build();
    }
}
