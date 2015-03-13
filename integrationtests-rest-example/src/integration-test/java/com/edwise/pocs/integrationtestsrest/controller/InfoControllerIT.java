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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    // TODO tests completos...

    @Test
    public void getAll_InfosFound_ShouldReturnFoundInfos() throws Exception {
        mockMvc.perform(get("/api/info/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(120)))
                .andExpect(jsonPath("$[0].info", is("Info 1234")))
                .andExpect(jsonPath("$[0].creationDateTime", is(notNullValue())))
                .andExpect(jsonPath("$[1].id", is(121)))
                .andExpect(jsonPath("$[1].info", is("Info 4567")))
                .andExpect(jsonPath("$[1].creationDateTime", is(notNullValue())))
                .andExpect(jsonPath("$[2].id", is(122)))
                .andExpect(jsonPath("$[2].info", is("Info 7892")))
                .andExpect(jsonPath("$[2].creationDateTime", is(notNullValue())))
        ;
    }

    @Test
    public void getInfo_InfoFound_ShouldReturnCorrectInfo() throws Exception {
        mockMvc.perform(get("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1234)))
                .andExpect(jsonPath("$.info", is("Info 1234")))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
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
