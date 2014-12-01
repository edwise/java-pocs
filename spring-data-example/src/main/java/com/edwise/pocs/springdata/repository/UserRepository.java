package com.edwise.pocs.springdata.repository;

import com.edwise.pocs.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
