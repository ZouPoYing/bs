package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.Book;
import com.graduation.bs.dao.File;
import com.graduation.bs.dao.Order;
import com.graduation.bs.dao.Shopping;
import com.graduation.bs.mapper.BookMapper;
import com.graduation.bs.mapper.OrderMapper;
import com.graduation.bs.mapper.ShoppingMapper;
import com.graduation.bs.utils.MyUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
 * @since 2022-03-09
 */
@RestController
@RequestMapping("/bs/order")
public class OrderController {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ShoppingMapper shoppingMapper;

    @Resource
    private BookMapper bookMapper;

    @RequestMapping("/pay")
    public Map<String, Object> pay(@RequestBody Map<String, String> params) throws Exception {
        String addressId = params.get("addressId");
        String total = params.get("total");
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(addressId) || MyUtil.isEmpty(total) || MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Order order = new Order();
        order.setAddressId(Integer.valueOf(addressId));
        order.setPrice(total);
        order.setUserId(Integer.valueOf(userId));
        order.setStatus("已付款");
        orderMapper.insert(order);
        QueryWrapper<Shopping> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId).isNull("order_id");
        List<Shopping> shoppings = shoppingMapper.selectList(wrapper);
        for (Shopping shopping : shoppings) {
            shopping.setPrice(new BigDecimal(shopping.getNum().toString()).multiply(new BigDecimal(bookMapper.selectById(shopping.getBookId()).getPrice())).toString());
            shopping.setOrderId(order.getOrderId());
            shoppingMapper.updateById(shopping);
        }
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getOrder")
    public Map<String, Object> getOrder(@RequestBody Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(userId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        List<Map<String, Object>> orderList = new ArrayList<>();
        List<Map<String, Object>> orders = orderMapper.getOrderByUserId(userId);
        for (Map<String, Object> order : orders) {
            order.put("time", MyUtil.formatTime(order.get("createTime")));
            orderList.add(order);
        }
        result.put("order", orderList);
        result.put("success", true);
        return result;
    }

}

