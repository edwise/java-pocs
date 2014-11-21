package com.edwise.springbootseries.actuator.service;

import com.edwise.springbootseries.actuator.entity.Bug;

import java.util.List;

public interface BugsService {

    public List<Bug> getAllBugs();

    public Bug getBug(long id);
}
