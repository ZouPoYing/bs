package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.Book;
import com.graduation.bs.dao.Shopping;
import com.graduation.bs.mapper.CategoryMapper;
import com.graduation.bs.mapper.ShoppingMapper;
import com.graduation.bs.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MyBatisPlusAutoGenerator
 * @since 2022-03-08
 */
@RestController
@RequestMapping("/bs/shopping")
public class ShoppingController {

    @Resource
    private ShoppingMapper shoppingMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @RequestMapping("/addShoppingCar")
    public Map<String, Object> addShoppingCar(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String bookId = params.get("bookId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(bookId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Shopping> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).eq("book_id", bookId).isNull("order_id");
        if (shoppingMapper.selectCount(wrapper) > 0) {
            Shopping shopping = shoppingMapper.selectOne(wrapper);
            shopping.setNum(shopping.getNum()+1);
            shoppingMapper.updateById(shopping);
        } else {
            Shopping shopping = new Shopping();
            shopping.setBookId(Integer.valueOf(bookId));
            shopping.setUserId(Integer.valueOf(userId));
            shopping.setNum(1);
            shoppingMapper.insert(shopping);
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getShoppingCar")
    public Map<String, Object> getShoppingCar(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> shoppingCar = new ArrayList<>();
        List<Map<String, Object>> shoppingCars = shoppingMapper.getShoppingCarByUserId(userId);
        for (Map<String, Object> shopping : shoppingCars) {
            shopping.put("categoryValue",categoryMapper.getCategory(shopping.get("category").toString()));
            shoppingCar.add(shopping);
        }
        result.put("shoppingCar", shoppingCar);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getOrderDetail")
    public Map<String, Object> getOrderDetail(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String orderId = params.get("orderId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId) || MyUtil.isEmpty(orderId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> shoppingCar = new ArrayList<>();
        List<Map<String, Object>> shoppingCars = shoppingMapper.getShoppingCarByUserIdAndOrderId(userId,orderId);
        for (Map<String, Object> shopping : shoppingCars) {
            shopping.put("categoryValue",categoryMapper.getCategory(shopping.get("category").toString()));
            shoppingCar.add(shopping);
        }
        result.put("shoppingCar", shoppingCar);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteShopping")
    public Map<String, Object> deleteShopping(@RequestBody Map<String, String> params) throws Exception {
        String shoppingId = params.get("shoppingId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(shoppingId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        shoppingMapper.deleteById(shoppingId);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/changeShoppingNum")
    public Map<String, Object> changeShoppingNum(@RequestBody Map<String, String> params) throws Exception {
        String shoppingId = params.get("shoppingId");
        String num = params.get("num");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(shoppingId) || MyUtil.isEmpty(num)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Shopping shopping = new Shopping();
        shopping.setShoppingId(Integer.valueOf(shoppingId));
        shopping.setNum(Integer.valueOf(num));
        shoppingMapper.updateById(shopping);
        result.put("success", true);
        return result;
    }

}

