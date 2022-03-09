package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.Category;
import com.graduation.bs.dao.User;
import com.graduation.bs.mapper.CategoryMapper;
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
 * @since 2022-03-07
 */
@RestController
@RequestMapping("/bs/category")
public class CategoryController {

    @Resource
    private CategoryMapper categoryMapper;

    @RequestMapping("/getCategoryList")
    public Map<String, Object> getCategoryList() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("belong");
        result.put("categoryList", categoryMapper.selectList(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getBelongList")
    public Map<String, Object> getBelongList() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.isNull("belong");
        result.put("belongList", categoryMapper.selectList(wrapper));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/addCategory")
    public Map<String, Object> addCategory(@RequestBody Map<String, String> params) throws Exception {
        String categoryName = params.get("categoryName");
        String categoryCode = params.get("categoryCode");
        String belong = params.get("belong");
        String des = params.get("des");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(categoryName) || MyUtil.isEmpty(categoryCode) || MyUtil.isEmpty(des)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("category_code", categoryCode);
        if (categoryMapper.selectCount(wrapper) > 0) {
            result.put("msg", "分类代码已被使用");
            return result;
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryCode(categoryCode);
        category.setDes(des);
        if (MyUtil.isNotEmpty(belong)) {
            category.setBelong(belong);
        }
        categoryMapper.insert(category);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateCategory")
    public Map<String, Object> updateCategory(@RequestBody Map<String, String> params) throws Exception {
        String categoryId = params.get("categoryId");
        String categoryName = params.get("categoryName");
        String belong = params.get("belong");
        String des = params.get("des");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(categoryId) || MyUtil.isEmpty(categoryName) || MyUtil.isEmpty(des)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Category category = new Category();
        category.setCategoryId(Integer.valueOf(categoryId));
        category.setCategoryName(categoryName);
        category.setDes(des);
        if (MyUtil.isNotEmpty(belong)) {
            category.setBelong(belong);
        }
        categoryMapper.updateById(category);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteCategory")
    public Map<String, Object> deleteCategory(@RequestBody Map<String, String> params) throws Exception {
        String categoryId = params.get("categoryId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(categoryId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        categoryMapper.deleteById(categoryId);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getCategorySelect")
    public Map<String, Object> getCategorySelect() throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("categorySelect", categoryMapper.getCategorySelect());
        result.put("success", true);
        return result;
    }
}

