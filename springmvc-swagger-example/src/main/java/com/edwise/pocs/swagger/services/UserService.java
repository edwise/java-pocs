package com.edwise.pocs.swagger.services;

import com.edwise.pocs.swagger.entities.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(Long id);
}
