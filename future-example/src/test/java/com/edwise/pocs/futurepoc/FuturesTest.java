package com.edwise.pocs.futurepoc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.fail;

public class FuturesTest {
    private static ExecutorService executor;

    @BeforeClass
    public static void setUp() {
        executor = Executors.newFixedThreadPool(5);
    }

    @AfterClass
    public static void shutDown() {
        executor.shutdown();
    }

    @Test
    public void testApplication() {
        fail("Not yet implemented");
    }
}