package com.edwise.springbootseries.actuator.endpoints;

import com.edwise.springbootseries.actuator.checkers.FileManager;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class BetterHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        FileManager.Status diskStatus = FileManager.checkStatus();

        Health health = null;
        if (diskStatus.equals(FileManager.Status.OK)) {
            health = Health.up().build();
        } else {
            health = Health.down().withDetail("diskStatus", diskStatus).build();
        }

        return health;
    }
}
