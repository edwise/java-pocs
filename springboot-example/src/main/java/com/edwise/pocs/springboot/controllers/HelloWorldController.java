package com.edwise.pocs.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Edu on 24/08/2014.
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    @ResponseBody
    public String sayHelloWorld() {
        return "Hello World in your SpringBoot Application!";
    }
}
