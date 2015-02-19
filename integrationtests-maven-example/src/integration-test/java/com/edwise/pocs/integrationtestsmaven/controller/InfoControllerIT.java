package com.edwise.pocs.integrationtestsmaven.controller;

import com.edwise.pocs.integrationtestsmaven.Application;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest({"server.port=0"})
public class InfoControllerIT {
    private static final Long INFO_ID_1234 = 1234l;

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
    public void getInfo_ShouldReturnCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":1234,\"info\":\"Info 1234\",\"creationDateTime\":\"2001-12-12T13:40:30\"}";

        mockMvc.perform(get("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
    }

    @Test
    public void postInfo_ShouldReturnCreatedStatusAndCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":1234,\"info\":\"Info 1234 New\",\"creationDateTime\":\"2015-10-25T19:13:21\"}";

        mockMvc.perform(post("/api/info/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"info\":\"Info 1234 New\",\"creationDateTime\":\"2015-10-25T19:13:21\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(jsonExpected));
        ;
    }

    @Test
    public void deleteInfo_ShouldReturnNoContentStatus() throws Exception {
        mockMvc.perform(delete("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isNoContent());
    }
}
