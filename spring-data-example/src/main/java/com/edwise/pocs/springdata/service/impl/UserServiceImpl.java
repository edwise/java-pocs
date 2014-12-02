package com.edwise.pocs.springdata.service.impl;

import com.edwise.pocs.springdata.entity.User;
import com.edwise.pocs.springdata.repository.UserRepository;
import com.edwise.pocs.springdata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public boolean existsUser(Long id) {
        return userRepository.exists(id);
    }
}
