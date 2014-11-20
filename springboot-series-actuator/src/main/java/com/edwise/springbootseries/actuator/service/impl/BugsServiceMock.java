package com.edwise.springbootseries.actuator.service.impl;

import com.edwise.springbootseries.actuator.entity.Bug;
import com.edwise.springbootseries.actuator.service.BugsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BugsServiceMock implements BugsService {

    @Override
    public List<Bug> getAllBugs() {
        return Arrays.asList(
                createRandomBug(12345, "bug434632", "Error in system 45322352", "2009-12-08T12:35:10"),
                createRandomBug(34555, "bug564645", "Error in system 62357272", "2013-02-13T11:16:30"),
                createRandomBug(34536, "bug569565", "Error in system 47347423", "2014-12-22T10:05:20"),
                createRandomBug(56463, "bug675673", "Error in system 46734673", "2014-11-11T11:56:30"),
                createRandomBug(457345, "bug411132", "Error in system 65835655", "2014-03-25T05:22:45")
        );
    }

    @Override
    public Bug getBug(long id) {
        return createRandomBug(id, "bug434631", "Error in system bla bla bla", "2014-03-25T05:22:45");
    }

    private Bug createRandomBug(long id, String name, String description, String dateString) {
        return new Bug()
                .setId(id)
                .setName(name)
                .setDescription(description)
                .setDate(LocalDateTime.parse(dateString));
    }
}
