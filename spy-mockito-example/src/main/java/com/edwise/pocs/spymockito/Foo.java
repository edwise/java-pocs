package com.edwise.pocs.spymockito;

import java.util.concurrent.TimeUnit;

public class Foo {

    public String doSomethingWithDatabase() {
        System.out.println("Method that connect with DB...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished connection whit DB.");

        return "OK real Foo";
    }
}
