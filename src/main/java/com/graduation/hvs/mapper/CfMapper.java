package com.graduation.hvs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CfMapper {

    @Insert("INSERT INTO CF(doctor, patient, processid) VALUES (#{doctor}, #{patient}, #{processid})")
    void addCf(Integer doctor, Integer patient, Integer processid);

    @Select("SELECT CFID FROM CF order by date desc")
    List<Integer> selectCfid();
}
