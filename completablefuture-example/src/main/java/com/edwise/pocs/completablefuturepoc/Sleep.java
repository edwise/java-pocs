package com.edwise.pocs.completablefuturepoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public final class Sleep {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sleep.class);

    private Sleep() {
    }

    public static void sleepSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            LOGGER.error("Error in sleep of TimeUnit: {}", e.getMessage(), e);
        }
    }
}
