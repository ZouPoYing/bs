package com.graduation.hvs.service;

import com.graduation.hvs.mapper.CfdetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CfdetailService {

    @Autowired(required = false)
    private CfdetailMapper cfdetailMapper;

    public void addCfdetail(Integer doctor, String mediname, Integer medinum) throws Exception {
        cfdetailMapper.addCfdetail(doctor,mediname,medinum);
    }
}
