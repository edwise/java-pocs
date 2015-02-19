package com.edwise.pocs.integrationtestsmaven.service;


import com.edwise.pocs.integrationtestsmaven.entity.Info;

public interface InfoService {
    
    Info save(Info entity);

    Info findOne(Long id);

    void delete(Long id);
}
