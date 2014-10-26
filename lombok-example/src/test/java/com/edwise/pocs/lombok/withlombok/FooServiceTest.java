package com.edwise.pocs.lombok.withlombok;

import org.junit.Test;

import static org.junit.Assert.assertNull;

public class FooServiceTest {

    @Test
    public void testGetFooByIdWithNullParameter() {
        FooService fooService = new FooService();

        // Logs ERROR in the console!
        String foo = fooService.getFooById(null);

        assertNull(foo);
    }
}