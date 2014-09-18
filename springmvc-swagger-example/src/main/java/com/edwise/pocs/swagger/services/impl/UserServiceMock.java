package com.edwise.pocs.swagger.services.impl;

import com.edwise.pocs.swagger.entities.User;
import com.edwise.pocs.swagger.services.UserService;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceMock implements UserService {
    private static final long USER_ID_12 = 12L;
    private static final long USER_ID_140 = 140L;
    private static final long USER_ID_453321 = 45332L;
    private static final String NAME_GANDALF = "Gandalf";
    private static final String NAME_ARAGORN = "Aragorn";
    private static final String NAME_FRODO = "Frodo";
    private static final int TYPE_1 = 1;
    private static final int TYPE_2 = 2;
    private static final String PHONE_666554433 = "666554433";
    private static final String PHONE_661534411 = "661534411";
    private static final String PHONE_666222211 = "666222211";
    private static final String STRING_DATE_19110102 = "1911-01-02";
    private static final String STRING_DATE_19230716 = "1923-07-16";
    private static final String STRING_DATE_19511124 = "1951-11-24";


    @Override
    public User findById(Long id) {
        return new User()
                .setId(id)
                .setName(NAME_GANDALF)
                .setPhone(PHONE_666554433)
                .setType(TYPE_1)
                .setBirthDate(LocalDate.parse(STRING_DATE_19110102));
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(
                new User()
                        .setId(USER_ID_12)
                        .setName(NAME_GANDALF)
                        .setPhone(PHONE_666554433)
                        .setType(TYPE_1)
                        .setBirthDate(LocalDate.parse(STRING_DATE_19110102)),
                new User()
                        .setId(USER_ID_140)
                        .setName(NAME_ARAGORN)
                        .setPhone(PHONE_661534411)
                        .setType(TYPE_1)
                        .setBirthDate(LocalDate.parse(STRING_DATE_19230716)),
                new User()
                        .setId(USER_ID_453321)
                        .setName(NAME_FRODO)
                        .setPhone(PHONE_666222211)
                        .setType(TYPE_2)
                        .setBirthDate(LocalDate.parse(STRING_DATE_19511124))
        );
    }

    @Override
    public User save(User user) {
        return user.setId(USER_ID_453321);
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public void delete(Long id) {
        // delete user by id
    }
}
