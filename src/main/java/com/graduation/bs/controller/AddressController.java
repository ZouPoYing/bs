package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.Address;
import com.graduation.bs.dao.Book;
import com.graduation.bs.dao.Category;
import com.graduation.bs.dao.File;
import com.graduation.bs.mapper.AddressMapper;
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
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/bs/address")
public class AddressController {
    @Resource
    private AddressMapper addressMapper;

    @RequestMapping("/getAddressList")
    public Map<String, Object> getAddressList(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("is_default");
        result.put("addressList", addressMapper.selectList(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/addAddress")
    public Map<String, Object> addAddress(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String name = params.get("name");
        String phone = params.get("phone");
        String address = params.get("address");
        String detail = params.get("detail");
        String isDefault = params.get("isDefault");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(name) || MyUtil.isEmpty(phone) ||
                MyUtil.isEmpty(address) || MyUtil.isEmpty(detail) || MyUtil.isEmpty(isDefault)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("is_default","是");
        if (addressMapper.selectCount(wrapper) > 0) {
            if ("是".equals(isDefault)) {
                addressMapper.setAddressIsDefault(userId, " and 1=1 ");
            }
        } else {
            isDefault = "是";
        }
        Address addr = new Address();
        addr.setUserId(Integer.valueOf(userId));
        addr.setName(name);
        addr.setPhone(phone);
        addr.setAddress(address);
        addr.setDetail(detail);
        addr.setIsDefault(isDefault);
        addressMapper.insert(addr);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateAddress")
    public Map<String, Object> updateAddress(@RequestBody Map<String, String> params) throws Exception {
        String addressId = params.get("addressId");
        String userId = params.get("userId");
        String name = params.get("name");
        String phone = params.get("phone");
        String address = params.get("address");
        String detail = params.get("detail");
        String isDefault = params.get("isDefault");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(name) || MyUtil.isEmpty(phone) ||
                MyUtil.isEmpty(address) || MyUtil.isEmpty(detail) || MyUtil.isEmpty(isDefault)
                || MyUtil.isEmpty(addressId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("is_default","是");
        if (addressMapper.selectCount(wrapper) > 0) {
            if ("是".equals(isDefault)) {
                addressMapper.setAddressIsDefault(userId, " and address_id <> " + addressId);
            }
        } else {
            isDefault = "是";
        }
        Address addr = new Address();
        addr.setAddressId(Integer.valueOf(addressId));
        addr.setUserId(Integer.valueOf(userId));
        addr.setName(name);
        addr.setPhone(phone);
        addr.setAddress(address);
        addr.setDetail(detail);
        addr.setIsDefault(isDefault);
        addressMapper.updateById(addr);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteAddress")
    public Map<String, Object> deleteAddress(@RequestBody Map<String, String> params) throws Exception {
        String addressId = params.get("addressId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(addressId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        addressMapper.deleteById(addressId);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getMyDefaultAddress")
    public Map<String, Object> getMyDefaultAddress(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("is_default","是").eq("user_id", userId);
        result.put("address", addressMapper.selectOne(wrapper));
        result.put("success", true);
        return result;
    }
}

