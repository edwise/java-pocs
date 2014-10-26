package com.edwise.pocs.lombok.withlombok;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FooService {

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
