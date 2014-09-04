package com.edwise.pocs.swagger.controllers;

import com.edwise.pocs.swagger.entities.User;
import com.edwise.pocs.swagger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {
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
    public void insertUser(@RequestBody User user) {
        userService.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void updateUser(@RequestBody User user) {
        userService.update(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") long userId) {
        userService.delete(userId);
    }
}
