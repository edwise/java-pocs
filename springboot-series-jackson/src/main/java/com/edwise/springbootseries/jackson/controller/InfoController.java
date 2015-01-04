package com.edwise.springbootseries.jackson.controller;

import com.edwise.springbootseries.jackson.entity.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.joda.time.LocalDateTime;
//import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/info/")
public class InfoController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Info getInfo(@PathVariable long id) {
        return new Info()
                .setId(id)
                .setInfo("Info 1234")
                .setCreationDateTime(new LocalDateTime(2001, 12, 12, 13, 40, 30));
//                .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Info> createInfo(@RequestBody Info info) {
        return new ResponseEntity<>(info.setId(1234), HttpStatus.CREATED);
    }
}
