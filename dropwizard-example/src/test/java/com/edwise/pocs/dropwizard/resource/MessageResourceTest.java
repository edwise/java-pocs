package com.edwise.pocs.dropwizard.resource;

import com.edwise.pocs.dropwizard.model.Message;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageResourceTest {
    private static final String HELLO_WORLD_NOBODY = "Hello world Nobody!";
    private static final String HELLO_WORLD_NAME123 = "Hello world Name123!";
    private static final String NAME_123 = "Name123";
    
    private MessageResource messageResource;

    @Before
    public void setUp() {
        messageResource = new MessageResource();
    }

    @Test
    public void getHelloWorldMessageShouldReturnDefaultNameIfNameNull() {
        Message result = messageResource.getHelloWorldMessage(null);

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(HELLO_WORLD_NOBODY);
    }

    @Test
    public void getHelloWorldMessageShouldReturnNameIfNameNotNull() {
        Message result = messageResource.getHelloWorldMessage(NAME_123);

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(HELLO_WORLD_NAME123);
    }
}