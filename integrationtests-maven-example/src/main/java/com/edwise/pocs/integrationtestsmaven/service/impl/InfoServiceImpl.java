package com.edwise.pocs.integrationtestsmaven.service.impl;


import com.edwise.pocs.integrationtestsmaven.entity.Info;
import com.edwise.pocs.integrationtestsmaven.repository.InfoRepository;
import com.edwise.pocs.integrationtestsmaven.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public Info save(Info info) {
        return infoRepository.save(info);
    }

    @Override
    public Info findOne(Long id) {
        return infoRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        infoRepository.delete(id);
    }
}
