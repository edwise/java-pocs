package com.edwise.pocs.springbootdevtoolstest.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/other")
public class OtherController {

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public String otherGet(@PathVariable String name) {
        return "Hello" + name;
    }
}
