package com.graduation.bs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduation.bs.dao.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MyBatisPlusAutoGenerator
 * @since 2022-03-07
 */
public interface BookMapper extends BaseMapper<Book> {

    @Select("select DISTINCT ${field} as text, ${field} as value from book where is_sale = '是'")
    List<Map<String, Object>> getFieldList(String field);

}
