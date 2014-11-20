package com.edwise.springbootseries.actuator.checkers;

import java.util.Random;

public class FileManager {

    public enum Status {
        OK,
        LOW,
        DANGER
    }

    // This is a random mock!
    public static Status checkStatus() {

        Random r = new Random();
        return Status.values()[r.nextInt(Status.values().length)];
    }
}
