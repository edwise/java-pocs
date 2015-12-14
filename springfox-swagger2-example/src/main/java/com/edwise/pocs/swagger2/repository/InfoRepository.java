package com.edwise.pocs.swagger2.repository;

import com.edwise.pocs.swagger2.entity.Info;

import java.util.List;

public interface InfoRepository {

    Info save(Info info);

    Info findOne(Long id);

    List<Info> findAll();

    Info update(Info info);

    void delete(Long id);
}
