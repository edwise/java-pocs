package com.edwise.pocs.itrestassured.repository.impl;

import com.edwise.pocs.itrestassured.entity.Info;
import com.edwise.pocs.itrestassured.repository.InfoRepository;
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
                .setInfoText("Info 1234")
                .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30));
    }

    @Override
    public List<Info> findAll() {
        return Arrays.asList(
                new Info()
                        .setId(120L)
                        .setInfoText("Info 1234")
                        .setCreationDateTime(LocalDateTime.of(2001, 12, 12, 13, 40, 30)),
                new Info()
                        .setId(121L)
                        .setInfoText("Info 4567")
                        .setCreationDateTime(LocalDateTime.of(2012, 11, 5, 10, 44, 30)),
                new Info()
                        .setId(122L)
                        .setInfoText("Info 7892")
                        .setCreationDateTime(LocalDateTime.of(2013, 12, 10, 13, 33, 12))
        );
    }

    @Override
    public Info update(Info info) {
        return new Info()
                .setId(info.getId())
                .setInfoText("Info 1234 Updated")
                .setCreationDateTime(LocalDateTime.of(2012, 11, 12, 19, 35, 10));
    }

    @Override
    public void delete(Long id) {
        // delete info by id
    }
}
