package com.edwise.pocs.dropwizardseries.configurationclass.resource;

import com.edwise.pocs.dropwizardseries.configurationclass.model.Message;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageResourceTest {
    private static final String HELLO_WORLD_NOBODY = "Hello world %s, from %s!";
    private static final String HELLO_WORLD_NAME123 = "Hello world Name123, from %s!";
    private static final String NAME_123 = "Name123";

    private MessageResource messageResource;

    @Before
    public void setUp() {
        messageResource = new MessageResource("localhost", "default");
    }

    @Test
    public void getHelloWorldMessageShouldReturnDefaultNameIfNameNull() {
        Message result = messageResource.getHelloWorldMessage(null);

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(String.format(HELLO_WORLD_NOBODY, "default", "localhost"));
    }

    @Test
    public void getHelloWorldMessageShouldReturnNameIfNameNotNull() {
        Message result = messageResource.getHelloWorldMessage(NAME_123);

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(String.format(HELLO_WORLD_NAME123, "localhost"));
    }
}