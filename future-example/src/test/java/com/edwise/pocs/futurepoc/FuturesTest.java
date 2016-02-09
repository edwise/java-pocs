package com.edwise.pocs.futurepoc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class FuturesTest {
    private final static Logger LOG = LoggerFactory.getLogger(FuturesTest.class);

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
    public void testFuture() throws InterruptedException, ExecutionException {
        SomeBigProcess someBigProcess = new SomeBigProcess(executor);

        LOG.info("Lanzamos el futuro...");
        Future<String> future = someBigProcess.processVeryLong("test");
        LOG.info("Futuro lanzado.");

        assertEquals("test result", future.get());
    }

    @Test(expected = ExecutionException.class)
    public void testFutureWithException() throws InterruptedException, ExecutionException {
        SomeBigProcess someBigProcess = new SomeBigProcess(executor);

        LOG.info("Lanzamos el futuro...");
        Future<String> future = someBigProcess.processVeryLongThatThrowsException("test");
        LOG.info("Futuro lanzado.");

        future.get();
    }
}