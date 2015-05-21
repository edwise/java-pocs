package com.edwise.pocs.itrestassured.service;


import com.edwise.pocs.itrestassured.entity.Info;

import java.util.List;

public interface InfoService {

    Info save(Info entity);

    Info findOne(Long id);

    List<Info> findAll();

    Info update(Info info);

    void delete(Long id);
}
