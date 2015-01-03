package com.edwise.springbootseries.jackson.controller;

import com.edwise.springbootseries.jackson.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable long id) {
        return new User()
                .setId(id)
                .setName("Aragorn")
                .setBirthDateTime(LocalDateTime.of(2015, 7, 25, 19, 30, 10));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(user.setId(1234), HttpStatus.CREATED);
    }
}
