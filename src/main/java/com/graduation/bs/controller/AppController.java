package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.App;
import com.graduation.bs.mapper.AppMapper;
import com.graduation.bs.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MyBatisPlusAutoGenerator
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/bs/app")
public class AppController {

    @Resource
    private AppMapper appMapper;

    @RequestMapping("/getAppByType")
    public Map<String, Object> getAppByType(@RequestBody Map<String, String> params) throws Exception {
        String type = params.get("type");
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<App> wrapper = new QueryWrapper<>();
        if (MyUtil.isEmpty(type)) {
            wrapper.isNull("type");
        } else {
            wrapper.le("type",type);
        }
        result.put("app", appMapper.selectList(wrapper));
        result.put("success", true);
        return result;
    }

}

