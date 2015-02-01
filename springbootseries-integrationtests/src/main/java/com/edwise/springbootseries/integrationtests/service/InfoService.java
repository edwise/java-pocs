package com.edwise.springbootseries.integrationtests.service;

import com.edwise.springbootseries.integrationtests.entity.Info;

public interface InfoService {
    Info save(Info entity);

    Info findOne(Long id);

    void delete(Long id);
}
