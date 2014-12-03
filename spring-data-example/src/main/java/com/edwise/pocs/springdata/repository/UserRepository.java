package com.edwise.pocs.springdata.repository;

import com.edwise.pocs.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByType(Integer type);
}
