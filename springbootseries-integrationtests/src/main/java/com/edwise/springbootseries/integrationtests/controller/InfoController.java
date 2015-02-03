package com.edwise.springbootseries.integrationtests.controller;

import com.edwise.springbootseries.integrationtests.entity.Info;
import com.edwise.springbootseries.integrationtests.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/info/")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Info getInfo(@PathVariable Long id) {
        return infoService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Info> createInfo(@RequestBody Info info) {
        Info infoCreated = infoService.save(info);
        return new ResponseEntity<>(infoCreated, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteInfo(@PathVariable Long id) {
        infoService.delete(id);
    }
}
