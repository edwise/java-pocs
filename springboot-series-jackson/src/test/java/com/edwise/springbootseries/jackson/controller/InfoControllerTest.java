package com.edwise.springbootseries.jackson.controller;

import com.edwise.springbootseries.jackson.entity.Info;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDateTime;
//import java.time.LocalDateTime;


public class InfoControllerTest {

    private static final long INFO_ID_1234 = 1234;
    private static final String INFO_TEST = "Test 1234";
    private static final LocalDateTime INFO_CREATION_DATE_TIME = new LocalDateTime(2011, 12, 9, 19, 15, 20);
//    private static final LocalDateTime INFO_CREATION_DATE_TIME = LocalDateTime.of(2011, 12, 9, 19, 15, 20);

    private InfoController infoController;

    @Before
    public void setUp() {
        infoController = new InfoController();
    }

    @Test
    public void testGetInfo() {
        Info info = infoController.getInfo(INFO_ID_1234);

        assertNotNull(info);
        assertThat(info.getId(), is(INFO_ID_1234));
    }

    @Test
    public void testCreateInfo() {
        ResponseEntity<Info> response = infoController.createInfo(
                new Info()
                        .setInfo(INFO_TEST)
                        .setCreationDateTime(INFO_CREATION_DATE_TIME)
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getInfo(), is(INFO_TEST));
        assertThat(response.getBody().getCreationDateTime(), is(INFO_CREATION_DATE_TIME));
    }
}