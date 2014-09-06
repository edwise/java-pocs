package com.edwise.pocs.springrest.entities;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private static final long USER_ID_12 = 12L;
    private static final String NAME_GANDALF = "Gandalf";
    private static final int TYPE_1 = 1;
    private static final String PHONE_661534411 = "661534411";
    private static final String STRING_DATE_19110102 = "1911-01-02";

    @Test
    public void copyFrom() {
        User user = createUser(USER_ID_12, NAME_GANDALF, TYPE_1, PHONE_661534411, STRING_DATE_19110102);
        User userTo = createUser(USER_ID_12, null, null, null, null);

        userTo.copyFrom(user);

        assertEquals(userTo, user);
    }

    private User createUser(Long id, String name, Integer type, String phone, String stringDate) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setType(type);
        user.setPhone(phone);
        if (stringDate != null) {
            user.setBirthDate(LocalDate.parse(stringDate));
        }

        return user;
    }
}