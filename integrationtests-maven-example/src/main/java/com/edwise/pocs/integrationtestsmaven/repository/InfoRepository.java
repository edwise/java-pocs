package com.edwise.pocs.integrationtestsmaven.repository;


import com.edwise.pocs.integrationtestsmaven.entity.Info;

public interface InfoRepository {

    Info save(Info info);

    Info findOne(Long id);

    void delete(Long id);
}
