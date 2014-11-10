package com.edwise.pocs.jerseyrest.resource;

import com.edwise.pocs.jerseyrest.entity.User;
import com.edwise.pocs.jerseyrest.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("users")
public class UserController {

    @Inject
    private UserService userService;

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
        userService.save(user);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") long userId, User user) {
        Response response;
        User userOld = userService.findById(userId);
        if (userOld != null) {
            userService.update(userOld.copyFrom(user));
            response = Response.status(Response.Status.CREATED).build();
        } else {
            response = Response.status(Response.Status.NOT_FOUND).build();
        }

        return response;
    }

    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") long userId) {
        userService.delete(userId);
    }
}
