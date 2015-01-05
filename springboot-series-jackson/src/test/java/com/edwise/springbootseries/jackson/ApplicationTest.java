package com.edwise.springbootseries.jackson;

import com.edwise.springbootseries.jackson.entity.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

import org.joda.time.LocalDateTime;
//import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ApplicationTest {

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    public void testSerializingLocalDateTimeIsCorrect() throws Exception {
        LocalDateTime date = new LocalDateTime(2011, 12, 9, 19, 15, 20);
        //LocalDateTime date = LocalDateTime.of(2011, 12, 9, 19, 15, 20);

        String result = objectMapper.writeValueAsString(date);
        assertThat(result, containsString("2011-12-09T19:15:20"));
    }

    @Test
    public void testDeserializingLocalDateTimeIsCorrect() throws Exception {
        String infoJson = "{\"id\":1234,\"info\":\"Info Test\",\"creationDateTime\":\"2011-12-09T19:15:20\"}";

        Info result = objectMapper.reader(Info.class).readValue(infoJson);

        assertThat(result.getCreationDateTime(), is(new LocalDateTime(2011, 12, 9, 19, 15, 20)));
        //assertThat(result.getCreationDateTime(), is(LocalDateTime.of(2011, 12, 9, 19, 15, 20)));
    }
}