package com.edwise.pocs.futurepoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SomeBigProcess {
    private final static Logger LOG = LoggerFactory.getLogger(SomeBigProcess.class);

    private final ExecutorService executor;

    public SomeBigProcess(ExecutorService executor) {
        this.executor = executor;
    }

    public Future<String> processVeryLong(String param) throws InterruptedException {
        return executor.submit(() -> {
            LOG.info("Comenzando processVeryLong...");
            TimeUnit.SECONDS.sleep(5);
            LOG.info("Terminando processVeryLong...");
            return param.concat(" result");
        });
    }

    public Future<String> processVeryLongThatThrowsException(String parama) throws InterruptedException {
        return executor.submit(() -> {
            LOG.info("Comenzando processVeryLongThatThrowsException...");
            TimeUnit.SECONDS.sleep(5);
            LOG.info("Terminando processVeryLongThatThrowsException...");
            throw new RuntimeException("Error!");
        });
    }
}
