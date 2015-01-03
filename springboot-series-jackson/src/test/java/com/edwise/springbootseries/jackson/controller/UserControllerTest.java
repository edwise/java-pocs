package com.edwise.springbootseries.jackson.controller;

import com.edwise.springbootseries.jackson.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class UserControllerTest {

    private static final long USER_ID_1234 = 1234;
    private static final String USER_NAME_GANDALF = "Gandalf";
    private static final LocalDateTime USER_BIRTH_DATE_TIME = LocalDateTime.of(1971, 1, 13, 14, 25, 10);

    private UserController userController;

    @Before
    public void setUp() {
        userController = new UserController();
    }

    @Test
    public void testGetUser() {
        User user = userController.getUser(USER_ID_1234);

        assertNotNull(user);
        assertThat(user.getId(), is(USER_ID_1234));
    }

    @Test
    public void testCreateUser() {
        ResponseEntity<User> response = userController.createUser(
                new User()
                        .setName(USER_NAME_GANDALF)
                        .setBirthDateTime(USER_BIRTH_DATE_TIME)
        );

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
        assertThat(response.getBody().getName(), is(USER_NAME_GANDALF));
        assertThat(response.getBody().getBirthDateTime(), is(USER_BIRTH_DATE_TIME));
    }
}