package com.edwise.springbootseries.actuator.checkers;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FileManagerTest {

    @Test
    public void testCheckStatus() {
        FileManager.Status status = FileManager.checkStatus();

        assertNotNull(status);
    }
}