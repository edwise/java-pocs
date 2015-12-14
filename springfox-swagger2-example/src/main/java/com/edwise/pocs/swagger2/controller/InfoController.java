package com.edwise.pocs.swagger2.controller;

import com.edwise.pocs.swagger2.entity.Info;
import com.edwise.pocs.swagger2.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/infos")
@Api(value = "infos", description = "Infos API", produces = "application/json")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Infos", notes = "Returns all infos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exits one info at least")
    })
    public List<Info> getAllInfos() {
        return infoService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get one Info", notes = "Returns one info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Exists this info")
    })
    public Info getInfo(@ApiParam(defaultValue = "1", value = "The id of the info to return")
                        @PathVariable Long id) {
        return infoService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Info", notes = "Create a info")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful create of a info")
    })
    public ResponseEntity<Info> createInfo(@RequestBody Info info) {
        Info infoCreated = infoService.save(info);
        return new ResponseEntity<>(createHeadersWithLocation(infoCreated), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    @ApiOperation(value = "Update Info", notes = "Update a info")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successful update of a info")
    })
    public void updateInfo(@ApiParam(defaultValue = "1", value = "The id of the info to update")
                           @PathVariable Long id,
                           @RequestBody Info info) {
        infoService.update(info.setId(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    @ApiOperation(value = "Delete Info", notes = "Delete a info")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successful delete of a info")
    })
    public void deleteInfo(@ApiParam(defaultValue = "1", value = "The id of the info to delete")
                           @PathVariable Long id) {
        infoService.delete(id);
    }

    private HttpHeaders createHeadersWithLocation(Info info) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(info.getId())
                        .toUri());
        return httpHeaders;
    }
}
