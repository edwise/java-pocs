package com.edwise.springbootseries.integrationtests.repository.impl;

import com.edwise.springbootseries.integrationtests.entity.Info;
import com.edwise.springbootseries.integrationtests.repository.InfoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class InfoRepositoryMock implements InfoRepository {

    @Override
    public Info save(Info info) {
        return info.setId(1234L);
    }

    @Override
    public Info findOne(Long id) {
        return new Info()
                .setId(id)
                .setInfo("Info 1234")
                .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30));
    }

    @Override
    public void delete(Long id) {
        // delete info by id
    }
}
