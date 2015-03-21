package com.edwise.example.passbyvalue;

import org.junit.Test;

public class PassByValueExampleTest {

    private PassByValueExample passByValueExample = new PassByValueExample();

    @Test
    public void testPrimitiveExample() {
        passByValueExample.primitiveExample();
    }

    @Test
    public void testObjectExample() {
        passByValueExample.objectExample();
    }

    @Test
    public void testOtherObjectExample() {
        passByValueExample.otherObjectExample();
    }
}