package com.edwise.pocs.integrationtestsrest.service;

import com.edwise.pocs.integrationtestsrest.entity.Info;

import java.util.List;

public interface InfoService {

    Info save(Info entity);

    Info findOne(Long id);

    List<Info> findAll();

    void delete(Long id);

    Info update(Info info);
}
