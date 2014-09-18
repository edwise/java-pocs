package com.edwise.pocs.swagger.impl;

import com.edwise.pocs.swagger.entities.User;
import com.edwise.pocs.swagger.services.UserService;
import com.edwise.pocs.swagger.services.impl.UserServiceMock;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;

public class UserServiceMockTest {
    private static final long USER_ID_12 = 12l;
    private static final String NAME_GANDALF = "Gandalf";
    private static final int TYPE_1 = 1;
    private static final String PHONE_661534411 = "661534411";
    private static final String STRING_DATE_19110102 = "1911-01-02";

    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserServiceMock();
    }

    @Test
    public void findById() {
        User user = userService.findById(USER_ID_12);

        assertNotNull(user);
        assertThat(user.getId(), is(USER_ID_12));
    }

    @Test
    public void findAll() {
        List<User> users = userService.findAll();

        assertNotNull(users);
        assertThat(users, hasSize(3));
    }

    @Test
    public void save() {
        User user = userService.save(
                createUser(null, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102)
        );

        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void update() {
        User user = userService.update(
                createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102)
        );

        assertNotNull(user);
        assertThat(user.getId(), is(USER_ID_12));
    }

    @Test
    public void delete() {
        userService.delete(USER_ID_12);
    }

    private User createUser(Long id, String name, int type, String phone, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setType(type);
        user.setPhone(phone);
        user.setBirthDate(LocalDate.parse(stringDate));

        return user;
    }
}