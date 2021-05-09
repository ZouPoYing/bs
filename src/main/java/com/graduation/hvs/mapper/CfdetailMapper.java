package com.graduation.hvs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CfdetailMapper {

    @Insert("INSERT INTO CFDETAIL(cfid, mediname, medinum) VALUES (#{cfid}, #{mediname}, #{medinum})")
    void addCfdetail(Integer cfid, String mediname, Integer medinum);
}
