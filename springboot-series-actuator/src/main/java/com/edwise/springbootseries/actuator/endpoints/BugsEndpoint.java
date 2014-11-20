package com.edwise.springbootseries.actuator.endpoints;

import com.edwise.springbootseries.actuator.entity.Bug;
import com.edwise.springbootseries.actuator.service.BugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BugsEndpoint extends AbstractEndpoint<List<Bug>> {

    @Autowired
    BugsService bugsService;

    public BugsEndpoint() {
        super("bugs");
    }


    @Override
    public List<Bug> invoke() {
        return bugsService.getAllBugs();
    }
}