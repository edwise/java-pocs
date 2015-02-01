package com.edwise.springbootseries.integrationtests;

import com.edwise.springbootseries.integrationtests.entity.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class InfoControllerIT {
    private static final Long INFO_ID_1234 = 1234l;
    private static final String INFO_TEST = "Info 1234";
    private static final LocalDateTime INFO_CREATION_DATE_TIME = LocalDateTime.of(2011, 12, 9, 19, 15, 20);

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
    public void getInfo_ShouldReturnCorrectInfo_WithMockMvc() throws Exception {
        mockMvc.perform(get("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id", is(INFO_ID_1234.intValue())))
                .andExpect(jsonPath("$.info", is(INFO_TEST)))
                .andExpect(jsonPath("$.creationDateTime", is(notNullValue())));
    }

    @Test
    public void postInfo_ShouldReturnCreatedStatusAndCorrectInfo_WithMockMvc() throws Exception {
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
    public void deleteInfo_ShouldReturnNoContentStatus_WithMockMvc() throws Exception {
        mockMvc.perform(delete("/api/info/{id}", INFO_ID_1234))
                .andExpect(status().isNoContent());
    }

    @Value("${local.server.port}")
    long serverPort;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void getInfo_ShouldReturnCorrectInfo_WithTestRestTemplate() {
        ResponseEntity<Info> response =
                restTemplate.getForEntity("http://localhost:" + serverPort + "/api/info/{id}",
                        Info.class,
                        INFO_ID_1234);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().getId(), is(INFO_ID_1234));
        assertThat(response.getBody().getInfo(), is(INFO_TEST));
        assertThat(response.getBody().getCreationDateTime(), is(notNullValue()));
    }

    @Test
    public void postInfo_ShouldReturnCreatedStatusAndCorrectInfo_WithTestRestTemplate() {
        Info infoToSave = createInfo(null, INFO_TEST, INFO_CREATION_DATE_TIME);

        ResponseEntity<Info> response =
                restTemplate.postForEntity("http://localhost:" + serverPort + "/api/info/",
                        infoToSave,
                        Info.class,
                        INFO_ID_1234);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody(), is(notNullValue()));
        assertThat(response.getBody().getId(), is(INFO_ID_1234));
        assertThat(response.getBody().getInfo(), is(INFO_TEST));
        assertThat(response.getBody().getCreationDateTime(), is(notNullValue()));
    }

    @Test
    public void deleteInfo_ShouldReturnNoContentStatus_WithTestRestTemplate() {
        restTemplate.delete("http://localhost:" + serverPort + "/api/info/{id}",
                Info.class,
                INFO_ID_1234);
    }

    private Info createInfo(Long id, String info, LocalDateTime creationDateTime) {
        return new Info()
                .setId(id)
                .setInfo(info)
                .setCreationDateTime(creationDateTime);
    }
}
