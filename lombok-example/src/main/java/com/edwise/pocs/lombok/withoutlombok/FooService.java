package com.edwise.pocs.lombok.withoutlombok;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FooService {
    private static final Logger log =
            LogManager.getLogger(FooService.class);

    public String getFooById(Long id) {
        String myFoo = null;
        if (id != null) {
            myFoo = "myFoo!";
        } else {
            log.error("id cannot be null!");
        }

        return myFoo;
    }

}
