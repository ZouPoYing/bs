package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.App;
import com.graduation.bs.dao.User;
import com.graduation.bs.mapper.UserMapper;
import com.graduation.bs.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/bs/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/regist")
    public Map<String, Object> regist(@RequestBody Map<String, String> params) throws Exception {
        String userName = params.get("userName");
        String phone = params.get("phone");
        String password = params.get("password");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userName) || MyUtil.isEmpty(phone) || MyUtil.isEmpty(password)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (MyUtil.isNotPhone(phone)) {
            result.put("msg", "电话号码格式不正确");
            return result;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        if (userMapper.selectCount(wrapper) > 0) {
            result.put("msg", "电话号码已被注册");
            return result;
        }
        wrapper.clear();
        User user = new User();
        user.setUserName(userName);
        user.setPhone(phone);
        user.setPassword(password);
        user.setType("1");
        user.setMoney("0");
        userMapper.insert(user);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params) throws Exception {
        String phone = params.get("phone");
        String password = params.get("password");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(phone) || MyUtil.isEmpty(password)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (MyUtil.isNotPhone(phone)) {
            result.put("msg", "电话号码格式不正确");
            return result;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone).eq("password", password);
        if (userMapper.selectCount(wrapper) < 1) {
            result.put("msg", "账号密码错误");
            return result;
        }
        result.put("user", userMapper.selectOne(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getUserById")
    public Map<String, Object> getUserById(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        result.put("user", userMapper.selectOne(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateUser")
    public Map<String, Object> updateUser(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String userName = params.get("userName");
        String age = params.get("age");
        String sex = params.get("sex");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(userName) || MyUtil.isEmpty(age)
                || MyUtil.isEmpty(sex)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        user.setUserName(userName);
        user.setAge(age);
        user.setSex(sex);
        userMapper.updateById(user);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getUserList")
    public Map<String, Object> getUserList() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "1");
        result.put("userList", userMapper.selectList(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateUserDetail")
    public Map<String, Object> updateUserDetail(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String userName = params.get("userName");
        String password = params.get("password");
        String phone = params.get("phone");
        String age = params.get("age");
        String sex = params.get("sex");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(userName) || MyUtil.isEmpty(age)
                || MyUtil.isEmpty(sex) || MyUtil.isEmpty(password) || MyUtil.isEmpty(phone)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        User user = new User();
        user.setUserId(Integer.valueOf(userId));
        user.setUserName(userName);
        user.setPhone(phone);
        user.setPassword(password);
        user.setAge(age);
        user.setSex(sex);
        userMapper.updateById(user);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/addUserDetail")
    public Map<String, Object> addUserDetail(@RequestBody Map<String, String> params) throws Exception {
        String userName = params.get("userName");
        String password = params.get("password");
        String phone = params.get("phone");
        String age = params.get("age");
        String sex = params.get("sex");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userName) || MyUtil.isEmpty(age)
                || MyUtil.isEmpty(sex) || MyUtil.isEmpty(password) || MyUtil.isEmpty(phone)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        if (MyUtil.isNotPhone(phone)) {
            result.put("msg", "电话号码格式不正确");
            return result;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", phone);
        if (userMapper.selectCount(wrapper) > 0) {
            result.put("msg", "电话号码已被注册");
            return result;
        }
        User user = new User();
        user.setUserName(userName);
        user.setPhone(phone);
        user.setPassword(password);
        user.setAge(age);
        user.setSex(sex);
        user.setType("1");
        user.setMoney("0");
        userMapper.insert(user);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteUser")
    public Map<String, Object> deleteUser(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        userMapper.deleteById(userId);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getAdmin")
    public Map<String, Object> getAdmin() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("type", "9");
        result.put("admin", userMapper.selectOne(wrapper));
        result.put("success", true);
        return result;
    }
}

