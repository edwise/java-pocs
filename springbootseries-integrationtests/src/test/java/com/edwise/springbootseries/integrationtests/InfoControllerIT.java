package com.edwise.springbootseries.integrationtests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class InfoControllerIT {
    private static final Long INFO_ID_1234 = 1234l;
    private static final String INFO_TEST = "Info 1234";

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
        mockMvc.perform(get("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id", is(INFO_ID_1234.intValue())))
                .andExpect(jsonPath("$.info", is(INFO_TEST)))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
    }

    @Test
    public void postInfo_ShouldReturnCreatedStatusAndCorrectInfo() throws Exception {
        mockMvc.perform(post("/api/info/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"info\":\"Info 1234\",\"creationDateTime\":\"2001-12-12T13:40:30\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.info", is(INFO_TEST)))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
        ;
    }

    @Test
    public void deleteInfo_ShouldReturnNoContentStatus() throws Exception {
        mockMvc.perform(delete("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isNoContent());
    }
}
