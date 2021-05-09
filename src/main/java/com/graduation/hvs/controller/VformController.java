package com.graduation.hvs.controller;


import com.alibaba.fastjson.JSONArray;
import com.graduation.hvs.service.*;
import com.graduation.hvs.utils.DateUtils;
import com.graduation.hvs.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vform")
@CrossOrigin(originPatterns = "*",allowCredentials ="true")
public class VformController {

    @Autowired
    private VformService vformService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private MediService mediService;

    @Autowired
    private CfService cfService;

    @Autowired
    private CfdetailService cfdetailService;

    @RequestMapping("/addVform")
    public Map<String, Object> addVform(@RequestBody Map<String, String> params) throws Exception {
        String doctor = params.get("doctor");
        String patient = params.get("patient");
        String disease = params.get("disease");
        String cause = params.get("cause");
        String money = params.get("money");
        String sm = params.get("sm");
        String tip = params.get("tip");
        String processid = params.get("processid");
        String ji = params.get("ji");
        List<Map<String,Object>> domains = (List<Map<String,Object>>) JSONArray.parse(params.get("domains"));
        Map<String, Object> result = new HashMap<>();
        if (doctor.isEmpty() || patient.isEmpty() || disease.isEmpty() || cause.isEmpty() || domains.isEmpty() ||
                money.isEmpty() || sm.isEmpty() || tip.isEmpty() || processid.isEmpty() || ji.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Integer cfid = cfService.addCf(Integer.valueOf(doctor), Integer.valueOf(patient), Integer.valueOf(processid));
        for (Map<String, Object> map : domains) {
            cfdetailService.addCfdetail(cfid,map.get("value").toString(),(Integer) map.get("num"));
        }
        vformService.addVform1(Integer.valueOf(patient),Integer.valueOf(doctor),disease,cause,
                new BigDecimal(money),sm,tip,cfid,Integer.valueOf(ji));
        processService.updateProcessstepByProcessid("缴费",vformService.selectVformid(),Integer.valueOf(processid));
        String msg = "你好，医生已经为你开出了就诊单，请先缴费，然后取药";
        msgService.addMsgHvs(msg,13,Integer.valueOf(processid),Integer.valueOf(patient),Integer.valueOf(doctor));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getMyVform")
    public List<Map<String, Object>> getMyVform(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> vformList = vformService.getMyVform(Integer.valueOf(userid));
        for (Map<String, Object> map : vformList) {
//            String m = map.get("mediid").toString().replace("[","")
//                    .replace("]","");
//            map.put("medinames",vformService.getMedinames(m));
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return vformList;
    }

    @RequestMapping("/getMyVform1")
    public List<Map<String, Object>> getMyVform1(@RequestBody Map<String, String> params) throws Exception {
        String userid = params.get("userid");
        if (userid.isEmpty()) {
            return null;
        }
        //List<Map<String, Object>> vformList = vformService.getMyVform1(Integer.valueOf(userid));
        List<Map<String, Object>> vformList = vformService.getMyVform2();
        for (Map<String, Object> map : vformList) {
//            String m = map.get("mediid").toString().replace("[","")
//                    .replace("]","");
//            map.put("medinames",vformService.getMedinames(m));
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return vformList;
    }

    @RequestMapping("/getMoney")
    public Map<String, Object> getMoney(@RequestBody Map<String, String> params) throws Exception {
        List<Map<String,Object>> domains = (List<Map<String,Object>>) JSONArray.parse(params.get("domains"));
        String ji = params.get("ji");
        Map<String, Object> result = new HashMap<>();
        if (domains.isEmpty() || ji.isEmpty()) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<String> listMedi = ListUtils.getListValue(domains, "value");
        if (ListUtils.hasRe(listMedi)) {
            result.put("msg", "有重复的药");
            return result;
        }
        BigDecimal money = new BigDecimal("0.00");
        for (Map<String,Object> map : domains) {
            if (map.get("value").equals("") || map.get("num")=="" || map.get("num")==null || map.get("value")==null) {
                result.put("msg", "请先选择药品和数量");
                return result;
            }
            Map<String, Object> mediMap = mediService.getMediByMedi((String) map.get("value"));
            if ((int) map.get("num") > (int) mediMap.get("num")) {
                result.put("msg", "【库存不足】"+mediMap.get("mediname")+"库存只有"+mediMap.get("num"));
                return result;
            }
            money = money.add(new BigDecimal(mediMap.get("money").toString()).multiply(new BigDecimal(map.get("num").toString())));
        }
        money = money.multiply(new BigDecimal(ji));
        result.put("money", money);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getMyCf")
    public List<Map<String, Object>> getMyCf(@RequestBody Map<String, String> params) throws Exception {
        String doctor = params.get("doctor");
        if (doctor.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> myCf = vformService.getMyCf(Integer.valueOf(doctor));
        for (Map<String, Object> map : myCf) {
            map.put("date", DateUtils.D2NYR(map.get("date")));
        }
        return myCf;
    }

    @RequestMapping("/getMyCfdetail")
    public List<Map<String, Object>> getMyCfdetail(@RequestBody Map<String, String> params) throws Exception {
        String cfid = params.get("cfid");
        if (cfid.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> myCfdetail = vformService.getMyCfdetail(Integer.valueOf(cfid));
        return myCfdetail;
    }
}
