package com.edwise.pocs.itrestassured.controller;

import com.edwise.pocs.itrestassured.Application;
import com.edwise.pocs.itrestassured.entity.Info;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest({"server.port=0"})
public class InfoControllerIT {
    private static final String INFO_TEXT_FIELD = "infoText";
    private static final String CREATION_DATE_TIME_FIELD = "creationDateTime";
    private static final String HEADER_LOCATION = "location";
    private static final Long INFO_ID_1234 = 1234l;
    private static final String INFO_TEXT_1234 = "Info 1234";
    private static final String INFO_TEXT_4567 = "Info 4567";
    private static final String INFO_TEXT_7892 = "Info 7892";
    private static final String INFO_TEXT_1234_NEW = "Info 1234 New";
    private static final String INFO_TEXT_1234_UPDATED = "Info 1234 Updated";
    private static final LocalDateTime CREATION_DATE_TIME_UPDATED = LocalDateTime.of(2014, 10, 25, 19, 16, 21);
    private static final LocalDateTime CREATION_DATE_TIME_NEW = LocalDateTime.of(2013, 10, 11, 20, 10, 10);

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void getInfo_InfoFound_ShouldReturnCorrectInfo() {
        given()
            .accept(ContentType.JSON)
            .pathParam("id", INFO_ID_1234)
        .when()
            .get("/api/info/{id}")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(INFO_TEXT_FIELD, equalTo(INFO_TEXT_1234))
            .body(CREATION_DATE_TIME_FIELD, notNullValue());
    }

    @Test
    public void getAll_InfosFound_ShouldReturnFoundInfos() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get("/api/info/")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(INFO_TEXT_FIELD,
                    hasItems(INFO_TEXT_1234, INFO_TEXT_4567, INFO_TEXT_7892))
            .body(CREATION_DATE_TIME_FIELD, everyItem(notNullValue()));

    }

    @Test
    public void postInfo_InfoCorrect_ShouldReturnCreatedStatusAndNoBody() {
        given()
            .body(createMockInfo(INFO_TEXT_1234_NEW, CREATION_DATE_TIME_NEW))
            .contentType(ContentType.JSON)
        .when()
            .post("/api/info/")
        .then()
            .statusCode(HttpStatus.SC_CREATED)
            .body(isEmptyString())
            .header(HEADER_LOCATION, containsString("/api/info/" + INFO_ID_1234));
    }

    @Test
    public void putInfo_InfoCorrect_ShouldReturnNoContentStatus() {
        given()
            .body(createMockInfo(INFO_TEXT_1234_UPDATED, CREATION_DATE_TIME_UPDATED))
            .contentType(ContentType.JSON)
            .pathParam("id", INFO_ID_1234)
        .when()
            .put("/api/info/{id}")
        .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void deleteInfo_ShouldReturnNoContentStatus() {
        given()
            .pathParam("id", INFO_ID_1234)
        .when()
            .delete("/api/info/{id}")
        .then()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    private Info createMockInfo(String text, LocalDateTime creationDateTime) {
        return new Info()
                .setInfoText(text)
                .setCreationDateTime(creationDateTime);
    }
}
