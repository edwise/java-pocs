package com.edwise.pocs.springdata.service;

import com.edwise.pocs.springdata.entity.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User save(User user);

    void delete(Long id);

    boolean existsUser(Long id);
}
