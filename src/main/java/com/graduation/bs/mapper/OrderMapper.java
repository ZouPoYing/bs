package com.graduation.bs.mapper;

import com.graduation.bs.dao.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MyBatisPlusAutoGenerator
 * @since 2022-03-09
 */
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT o.order_id as orderId,o.status,o.price,o.create_time as createTime," +
            "a.name,a.phone,a.address,a.detail FROM bs.order o left join address a on " +
            "o.address_id=a.address_id where o.user_id = #{userId} order by o.create_time desc")
    List<Map<String, Object>> getOrderByUserId(String userId);

}
