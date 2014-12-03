package com.edwise.pocs.springdata.repository;

import com.edwise.pocs.springdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByType(Integer type);

    public List<User> findByNameIgnoreCase(String name);

    @Query("SELECT u FROM User u WHERE u.phone LIKE CONCAT(:phonePrefix, '%')")
    public List<User> findByPhonePrefix(@Param("phonePrefix") String phonePrefix);
}
