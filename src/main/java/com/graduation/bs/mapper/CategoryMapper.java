package com.graduation.bs.mapper;

import com.graduation.bs.dao.Category;
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
 * @since 2022-03-07
 */
public interface CategoryMapper extends BaseMapper<Category> {

    @Select("select parent.category_code as pcode,parent.category_name as pname,child.category_code as ccode,child.category_name as cname\n" +
            "     from category parent\n" +
            "        join category child\n" +
            "        on parent.category_code= child.belong\n" +
            "  order by pcode")
    List<Map<String, Object>> getCategorySelect();

    @Select("SELECT\n" +
            "    GROUP_CONCAT(c.category_name separator '/') category\n" +
            "FROM\n" +
            "    (select * from category where category_code in (${categorySub})) c")
    String getCategory(String categorySub);
}
