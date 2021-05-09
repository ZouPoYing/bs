package com.graduation.hvs.service;

import com.graduation.hvs.mapper.CfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CfService {

    @Autowired(required = false)
    private CfMapper cfMapper;

    public Integer addCf(Integer doctor, Integer patient, Integer processid) throws Exception {
        cfMapper.addCf(doctor,patient,processid);
        return cfMapper.selectCfid().get(0);
    }
}
