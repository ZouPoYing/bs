package com.graduation.bs.mapper;

import com.graduation.bs.dao.Shopping;
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
 * @since 2022-03-08
 */
public interface ShoppingMapper extends BaseMapper<Shopping> {

    @Select("select s.shopping_id as shoppingId,s.num,b.book_id as bookId,b.book_name as bookName," +
            "b.author,b.press,b.price,b.category,b.book_pic as bookPic  from shopping s left join " +
            "book b on s.book_id=b.book_id where s.user_id = #{userId} and order_id is null")
    List<Map<String, Object>> getShoppingCarByUserId(String userId);

    @Select("select s.shopping_id as shoppingId,s.num,b.book_id as bookId,b.book_name as bookName," +
            "b.author,b.press,s.price,b.category,b.book_pic as bookPic  from shopping s left join " +
            "book b on s.book_id=b.book_id where s.user_id = #{userId} and order_id = #{orderId}")
    List<Map<String, Object>> getShoppingCarByUserIdAndOrderId(String userId, String orderId);

}
