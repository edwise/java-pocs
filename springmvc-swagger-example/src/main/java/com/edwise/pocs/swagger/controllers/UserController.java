package com.edwise.pocs.swagger.controllers;

import com.edwise.pocs.swagger.entities.User;
import com.edwise.pocs.swagger.services.UserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Api(value = "users", description = "Users API")
public class UserController {

    private static final int RESPONSE_CODE_OK = 200;
    private static final int RESPONSE_CODE_CREATED = 201;
    private static final int RESPONSE_CODE_NOCONTENT = 204;
    private static final int RESPONSE_CODE_NOTFOUND = 404;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Users", response = List.class, notes = "Returns all users")
    @ApiResponses({
            @ApiResponse(code = RESPONSE_CODE_OK, message = "All users in db")
    })
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get one User", response = ResponseEntity.class, notes = "Returns one user")
    @ApiResponses({
            @ApiResponse(code = RESPONSE_CODE_OK, message = "Exists this user"),
            @ApiResponse(code = RESPONSE_CODE_NOTFOUND, message = "No exists this user")
    })
    public ResponseEntity<User> getUser(@PathVariable long userId) {
        User user = userService.findById(userId);
        ResponseEntity<User> response;
        if (user == null) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            response = new ResponseEntity<>(user, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insert User", notes = "Insert a complete new User")
    @ApiResponses({
            @ApiResponse(code = RESPONSE_CODE_CREATED, message = "User inserted")
    })
    public void insertUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Update User", notes = "Updates a user")
    @ApiResponses({
            @ApiResponse(code = RESPONSE_CODE_CREATED, message = "User updated")
    })
    public void updateUser(@PathVariable long userId, @RequestBody User user) {
        User userOld = userService.findById(userId);
        if (userOld != null) {
            userService.update(userOld.copyFrom(user));
        }
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete User", notes = "Deletes a user")
    @ApiResponses({
            @ApiResponse(code = RESPONSE_CODE_NOCONTENT, message = "Deleted user")
    })
    public void deleteUser(@PathVariable long userId) {
        userService.delete(userId);
    }
}
