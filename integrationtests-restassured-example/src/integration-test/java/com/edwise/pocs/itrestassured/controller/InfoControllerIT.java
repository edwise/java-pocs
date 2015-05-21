package com.edwise.pocs.itrestassured.controller;

import com.edwise.pocs.itrestassured.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest({"server.port=0"})
public class InfoControllerIT {

    // TODO implement integration tests with REST-Assured
}
