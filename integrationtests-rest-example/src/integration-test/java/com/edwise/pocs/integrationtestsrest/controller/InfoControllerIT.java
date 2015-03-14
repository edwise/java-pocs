package com.edwise.pocs.integrationtestsrest.controller;

import com.edwise.pocs.integrationtestsrest.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest({"server.port=0"})
public class InfoControllerIT {
    private static final Long INFO_ID_1234 = 1234l;
    private static final Long INFO_ID_120 = 120l;
    private static final Long INFO_ID_121 = 121l;
    private static final Long INFO_ID_122 = 122l;
    private static final String INFO_TEXT_1234 = "Info 1234";
    private static final String INFO_TEXT_4567 = "Info 4567";
    private static final String INFO_TEXT_7892 = "Info 7892";
    private static final String INFO_TEXT_1234_NEW = "Info 1234 New";

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    @Test
    public void getInfo_InfoFound_ShouldReturnCorrectInfo() throws Exception {
        mockMvc.perform(get("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(INFO_ID_1234.intValue())))
                .andExpect(jsonPath("$.infoText", is(INFO_TEXT_1234)))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
    }

    @Test
    public void getAll_InfosFound_ShouldReturnFoundInfos() throws Exception {
        mockMvc.perform(get("/api/info/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(INFO_ID_120.intValue())))
                .andExpect(jsonPath("$[0].infoText", is(INFO_TEXT_1234)))
                .andExpect(jsonPath("$[0].creationDateTime", is(notNullValue())))
                .andExpect(jsonPath("$[1].id", is(INFO_ID_121.intValue())))
                .andExpect(jsonPath("$[1].infoText", is(INFO_TEXT_4567)))
                .andExpect(jsonPath("$[1].creationDateTime", is(notNullValue())))
                .andExpect(jsonPath("$[2].id", is(INFO_ID_122.intValue())))
                .andExpect(jsonPath("$[2].infoText", is(INFO_TEXT_7892)))
                .andExpect(jsonPath("$[2].creationDateTime", is(notNullValue())));
    }

    @Test
    public void postInfo_InfoCorrect_ShouldReturnCreatedStatusAndCorrectInfo() throws Exception {
        mockMvc.perform(post("/api/info/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"infoText\":\"Info 1234 New\",\"creationDateTime\":\"2013-10-11T20:10:10\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(INFO_ID_1234.intValue())))
                .andExpect(jsonPath("$.infoText", is(INFO_TEXT_1234_NEW)))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
    }

    @Test
    public void putInfo_InfoCorrect_ShouldReturnNoContentStatus() throws Exception {
        mockMvc.perform(put("/api/info/{id}", INFO_ID_1234)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"infoText\":\"Info 1234 Updated\",\"creationDateTime\":\"2014-10-25T19:16:21\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInfo_ShouldReturnNoContentStatus() throws Exception {
        mockMvc.perform(delete("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isNoContent());
    }
}
