package com.edwise.springbootseries.war.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class HelloWorldControllerTest {

    private HelloWorldController helloWorldController;

    @Before
    public void setUp() {
        helloWorldController = new HelloWorldController();
    }

    @Test
    public void testSayHelloWorld() {
        String msgResult = helloWorldController.sayHelloWorld();

        assertNotNull(msgResult);
        assertThat(msgResult, is("Hello World in your SpringBoot Application!"));
    }
}