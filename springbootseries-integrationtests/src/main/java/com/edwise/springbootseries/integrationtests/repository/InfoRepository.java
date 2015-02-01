package com.edwise.springbootseries.integrationtests.repository;

import com.edwise.springbootseries.integrationtests.entity.Info;

public interface InfoRepository {

    Info save(Info info);

    Info findOne(Long id);

    void delete(Long id);
}
