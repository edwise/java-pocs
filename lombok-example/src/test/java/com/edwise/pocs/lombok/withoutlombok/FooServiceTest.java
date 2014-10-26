package com.edwise.pocs.lombok.withoutlombok;

import org.junit.Test;

import static org.junit.Assert.*;

public class FooServiceTest {

    @Test
    public void testGetFooById() {
        FooService fooService = new FooService();

        // Logs ERROR in the console!
        String foo = fooService.getFooById(null);

        assertNull(foo);
    }
}