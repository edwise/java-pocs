package com.edwise.pocs.integrationtestsrest.repository.impl;

import com.edwise.pocs.integrationtestsrest.entity.Info;
import com.edwise.pocs.integrationtestsrest.repository.InfoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    @Override
    public List<Info> findAll() {
        return Arrays.asList(
                new Info()
                        .setId(120L)
                        .setInfo("Info 1234")
                        .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30)),
                new Info()
                        .setId(121L)
                        .setInfo("Info 1234")
                        .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30)),
                new Info()
                        .setId(122L)
                        .setInfo("Info 1234")
                        .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30))
        );
    }

    @Override
    public Info update(Info info) {
        return new Info()
                .setId(info.getId())
                .setInfo("Info 1234 Updated")
                .setCreationDateTime(LocalDateTime.of(2012, 11, 12, 19, 35, 10));
    }
}
