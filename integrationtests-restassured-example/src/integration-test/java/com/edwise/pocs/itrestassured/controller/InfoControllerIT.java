package com.edwise.pocs.itrestassured.controller;

import com.edwise.pocs.itrestassured.Application;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest({"server.port=0"})
public class InfoControllerIT {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void getInfo_InfoFound_ShouldReturnCorrectInfo() {
        given()
            .contentType(ContentType.JSON)
            .pathParam("id", 1234)
        .when()
            .get("/api/info/{id}")
        .then()
            .statusCode(200)
            .body("infoText", equalTo("Info 1234"))
            .body("creationDateTime", notNullValue());
    }

    // TODO implement more integration tests with REST-Assured
}
