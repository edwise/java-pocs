package com.edwise.pocs.jerseyrest.service;

import com.edwise.pocs.jerseyrest.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User save(User user);

    User update(User user);

    void delete(Long id);
}
