package com.graduation.hvs.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface VformMapper {

    @Insert("INSERT INTO VFORM(patient,doctor, disease, cause,mediid,money,sm,tip) " +
            "VALUES (#{patient}, #{doctor},#{disease},#{cause}, #{mediid},#{money},#{sm},#{tip})")
    void addVfrom(Integer patient, Integer doctor, String disease, String cause, String mediid, BigDecimal money,
                  String sm, String tip);

    @Insert("INSERT INTO VFORM(patient,doctor, disease, cause,money,sm,tip,cfid,ji) " +
            "VALUES (#{patient}, #{doctor},#{disease},#{cause},#{money},#{sm},#{tip},#{cfid},#{ji})")
    void addVfrom1(Integer patient, Integer doctor, String disease, String cause, BigDecimal money,
                  String sm, String tip, Integer cfid, Integer ji);

    @Select("SELECT * FROM VFORM ORDER BY DATE desc")
    @Results({
            @Result(property = "vformid", column = "vformid")})
    List<Integer> selectVformid();

    @Select("select user.name as name,vform.disease as disease,vform.cause as cause,vform.mediid as mediid," +
            "vform.money as money,vform.sm as sm,vform.tip as tip,vform.cfid as cfid,vform.date as date,files.filename AS filename" +
            " ,process.processstep as processstep,process.doctor as doctor,process.processid as processid \n" +
            "from process LEFT JOIN vform on process.vformid=vform.vformid \n" +
            "LEFT JOIN user ON process.doctor=user.userid LEFT JOIN files ON process.filesid=files.fileid\n" +
            "where process.processstep<>'挂号' and process.processstep<>'就诊' and process.patient=#{userid}")
    @Results({
            @Result(property = "processid", column = "processid"),
            @Result(property = "doctor", column = "doctor"),
            @Result(property = "name", column = "name"),
            @Result(property = "disease", column = "disease"),
            @Result(property = "cause", column = "cause"),
            @Result(property = "mediid", column = "mediid"),
            @Result(property = "money", column = "money"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "cfid", column = "cfid"),
            @Result(property = "tip", column = "tip"),
            @Result(property = "date", column = "date"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "filename", column = "filename")})
    List<Map<String, Object>> getMyVform(Integer userid);

    @Select("select group_concat(mediname) as medinames from medi where mediid in (${mediid})")
    @Results({
            @Result(property = "medinames", column = "medinames")})
    String getMedinames(String mediid);

    @Select("select user.name as name,vform.disease as disease,vform.cause as cause,vform.mediid as mediid," +
            "vform.money as money,vform.sm as sm,vform.tip as tip,vform.date as date,files.filename AS filename" +
            " ,process.processstep as processstep,process.patient as patient,process.processid as processid \n" +
            "from process LEFT JOIN vform on process.vformid=vform.vformid \n" +
            "LEFT JOIN user ON process.patient=user.userid LEFT JOIN files ON process.filesid=files.fileid\n" +
            "where process.processstep='取药' and process.doctor=#{userid}")
    @Results({
            @Result(property = "processid", column = "processid"),
            @Result(property = "patient", column = "patient"),
            @Result(property = "name", column = "name"),
            @Result(property = "disease", column = "disease"),
            @Result(property = "cause", column = "cause"),
            @Result(property = "mediid", column = "mediid"),
            @Result(property = "money", column = "money"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "tip", column = "tip"),
            @Result(property = "date", column = "date"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "filename", column = "filename")})
    List<Map<String, Object>> getMyVform1(Integer userid);

    @Select("select user.name as name,vform.disease as disease,vform.cause as cause,vform.mediid as mediid," +
            "vform.money as money,vform.sm as sm,vform.tip as tip,vform.date as date,files.filename AS filename" +
            " ,process.processstep as processstep,process.patient as patient,process.processid as processid \n" +
            "from process LEFT JOIN vform on process.vformid=vform.vformid \n" +
            "LEFT JOIN user ON process.patient=user.userid LEFT JOIN files ON process.filesid=files.fileid\n" +
            "where process.processstep='取药' ")
    @Results({
            @Result(property = "processid", column = "processid"),
            @Result(property = "patient", column = "patient"),
            @Result(property = "name", column = "name"),
            @Result(property = "disease", column = "disease"),
            @Result(property = "cause", column = "cause"),
            @Result(property = "mediid", column = "mediid"),
            @Result(property = "money", column = "money"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "tip", column = "tip"),
            @Result(property = "date", column = "date"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "filename", column = "filename")})
    List<Map<String, Object>> getMyVform2();

    @Select("SELECT user.username as username,user.name as name,user.sex as sex,user.age as age,user.telephone as telephone," +
            "user.email as email,user.address as address,vform.disease as disease,vform.cause as cause,vform.money as money," +
            "vform.sm as sm,vform.tip as tip,vform.ji as ji,vform.cfid as cfid,vform.date as date,process.processstep as processstep\n" +
            "FROM process LEFT join user on process.patient=user.userid left join vform on process.vformid=vform.vformid " +
            "where process.doctor=#{doctor} and process.processstep in ('缴费','取药','结束') order by process.date desc")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "address", column = "address"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "tip", column = "tip"),
            @Result(property = "date", column = "date"),
            @Result(property = "disease", column = "disease"),
            @Result(property = "cause", column = "cause"),
            @Result(property = "money", column = "money"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "ji", column = "ji"),
            @Result(property = "cfid", column = "cfid")})
    List<Map<String, Object>> getMyCf(Integer doctor);

    @Select("SELECT user.username as username,user.name as name,user.sex as sex,user.age as age,user.telephone as telephone," +
            "user.email as email,user.address as address,vform.disease as disease,vform.cause as cause,vform.money as money," +
            "vform.sm as sm,vform.tip as tip,vform.ji as ji,vform.cfid as cfid,vform.date as date,process.processstep as processstep\n" +
            "FROM process LEFT join user on process.patient=user.userid left join vform on process.vformid=vform.vformid " +
            "where process.processid=#{processid} and process.processstep in ('缴费','取药','结束') order by process.date desc")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "age", column = "age"),
            @Result(property = "telephone", column = "telephone"),
            @Result(property = "email", column = "email"),
            @Result(property = "address", column = "address"),
            @Result(property = "sm", column = "sm"),
            @Result(property = "tip", column = "tip"),
            @Result(property = "date", column = "date"),
            @Result(property = "disease", column = "disease"),
            @Result(property = "cause", column = "cause"),
            @Result(property = "money", column = "money"),
            @Result(property = "processstep", column = "processstep"),
            @Result(property = "ji", column = "ji"),
            @Result(property = "cfid", column = "cfid")})
    List<Map<String, Object>> getMyCfByProcessid(Integer processid);

    @Select("select mediname,medinum from cfdetail where cfid =#{cfid}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "cfid", column = "cfid")})
    List<Map<String, Object>> getMyCfdetail(Integer cfid);
}
