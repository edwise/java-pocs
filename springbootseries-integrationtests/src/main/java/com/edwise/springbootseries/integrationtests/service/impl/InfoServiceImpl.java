package com.edwise.springbootseries.integrationtests.service.impl;

import com.edwise.springbootseries.integrationtests.entity.Info;
import com.edwise.springbootseries.integrationtests.repository.InfoRepository;
import com.edwise.springbootseries.integrationtests.service.InfoService;
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
