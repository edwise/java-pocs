package com.edwise.springbootseries.actuator.service.impl;

import com.edwise.springbootseries.actuator.entity.Bug;
import com.edwise.springbootseries.actuator.service.BugsService;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

public class BugsServiceMockTest {
    private static final long BUG_ID_12 = 12l;

    private BugsService bugsService = new BugsServiceMock();

    @Test
    public void getById() {
        Bug bug = bugsService.getBug(BUG_ID_12);

        assertNotNull(bug);
        assertThat(bug.getId(), is(BUG_ID_12));
    }
    @Test
    public void getAll() {
        List<Bug> bugs = bugsService.getAllBugs();

        assertNotNull(bugs);
        assertThat(bugs, hasSize(5));
    }
}