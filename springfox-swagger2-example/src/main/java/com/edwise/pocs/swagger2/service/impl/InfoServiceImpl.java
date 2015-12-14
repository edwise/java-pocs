package com.edwise.pocs.swagger2.service.impl;

import com.edwise.pocs.swagger2.entity.Info;
import com.edwise.pocs.swagger2.repository.InfoRepository;
import com.edwise.pocs.swagger2.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository infoRepository;

    @Override
    public Info save(Info info) {
        return infoRepository.save(info);
    }

    @Override
    public List<Info> findAll() {
        return infoRepository.findAll();
    }

    @Override
    public Info findOne(Long id) {
        return infoRepository.findOne(id);
    }

    @Override
    public Info update(Info info) {
        return infoRepository.update(info);
    }

    @Override
    public void delete(Long id) {
        infoRepository.delete(id);
    }
}
