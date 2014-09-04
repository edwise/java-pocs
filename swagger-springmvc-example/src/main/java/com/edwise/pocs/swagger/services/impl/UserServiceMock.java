package com.edwise.pocs.swagger.services.impl;

import com.edwise.pocs.swagger.entities.User;
import com.edwise.pocs.swagger.services.UserService;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceMock implements UserService {

    @Override
    public User findById(Long id) {
        return new User().setId(id).setName("edwise").setPhone("555668877").setType(1).setBirthDate(new LocalDate(1883, 10, 23));
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(
                new User().setId(1378l).setName("edwise").setPhone("555668877").setType(1).setBirthDate(new LocalDate(1883, 10, 23)),
                new User().setId(2744l).setName("aragorn").setPhone("666284567").setType(1).setBirthDate(new LocalDate(1810, 2, 11)),
                new User().setId(57422l).setName("gandalf").setPhone("888276374").setType(0).setBirthDate(new LocalDate(1750, 12, 29)));
    }

    @Override
    public User save(User user) {
        return user.setId(45999l);
    }

    @Override
    public User update(User user) {
        return user;
    }

    @Override
    public void delete(Long id) {
    }
}
