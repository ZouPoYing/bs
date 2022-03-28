package com.graduation.bs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.graduation.bs.dao.Book;
import com.graduation.bs.dao.File;
import com.graduation.bs.mapper.BookMapper;
import com.graduation.bs.mapper.CategoryMapper;
import com.graduation.bs.mapper.FileMapper;
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
@RequestMapping("/bs/book")
public class BookController {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @RequestMapping("/getBookList")
    public Map<String, Object> getBookList() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        List<Book> books = bookMapper.selectList(wrapper);
        List<Book> bookList = new ArrayList<>();
        for (Book book : books) {
            book.setCategory(categoryMapper.getCategory(book.getCategory()));
            bookList.add(book);
        }
        result.put("bookList", bookList);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getSaleBookList")
    public Map<String, Object> getSaleBookList() throws Exception {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("is_sale", "是");
        List<Book> books = bookMapper.selectList(wrapper);
        List<Book> bookList = new ArrayList<>();
        for (Book book : books) {
            book.setCategory(categoryMapper.getCategory(book.getCategory()));
            bookList.add(book);
        }
        result.put("bookList", bookList);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/addBook")
    public Map<String, Object> addBook(@RequestBody Map<String, String> params) throws Exception {
        String bookName = params.get("bookName");
        String author = params.get("author");
        String press = params.get("press");
        String price = params.get("price");
        String bookPic = params.get("bookPic");
        String category = params.get("category");
        String isSale = params.get("isSale");
        String des = params.get("des");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(bookName) || MyUtil.isEmpty(author) || MyUtil.isEmpty(press) ||
                MyUtil.isEmpty(price) || MyUtil.isEmpty(bookPic) || MyUtil.isEmpty(category) ||
                MyUtil.isEmpty(isSale) || MyUtil.isEmpty(des)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        File file = fileMapper.selectById(bookPic);
        Book book = new Book();
        book.setAuthor(author);
        book.setBookName(bookName);
        book.setPress(press);
        book.setPrice(price);
        book.setCategory(category);
        book.setIsSale(isSale);
        book.setDes(des);
        book.setBookPic("http://localhost:9091/" + file.getFileName());
        bookMapper.insert(book);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/updateBook")
    public Map<String, Object> updateBook(@RequestBody Map<String, String> params) throws Exception {
        String bookId = params.get("bookId");
        String bookName = params.get("bookName");
        String author = params.get("author");
        String press = params.get("press");
        String price = params.get("price");
        String bookPic = params.get("bookPic");
        String category = params.get("category");
        String isSale = params.get("isSale");
        String des = params.get("des");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(bookName) || MyUtil.isEmpty(author) || MyUtil.isEmpty(press) ||
                MyUtil.isEmpty(price) || MyUtil.isEmpty(category) ||
                MyUtil.isEmpty(isSale) || MyUtil.isEmpty(des) || MyUtil.isEmpty(bookId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        Book book = new Book();
        book.setBookId(Integer.valueOf(bookId));
        book.setAuthor(author);
        book.setBookName(bookName);
        book.setPress(press);
        book.setPrice(price);
        if (!MyUtil.isPic(category)) {
            book.setCategory(category);
        }
        book.setIsSale(isSale);
        book.setDes(des);
        if (MyUtil.isNotEmpty(bookPic)) {
            File file = fileMapper.selectById(bookPic);
            book.setBookPic("http://localhost:9091/" + file.getFileName());
        }
        bookMapper.updateById(book);
        result.put("success", true);
        return result;
    }

    @RequestMapping("/getFieldList")
    public Map<String, Object> getFieldList(@RequestBody Map<String, String> params) throws Exception {
        String type = params.get("type");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(type)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        result.put(type, bookMapper.getFieldList(type));
        result.put("success", true);
        return result;
    }

    @RequestMapping("/deleteBook")
    public Map<String, Object> deleteBook(@RequestBody Map<String, String> params) throws Exception {
        String bookId = params.get("bookId");
        Map<String, Object> result = new HashMap<>();
        if (MyUtil.isEmpty(bookId)) {
            result.put("msg", "参数不能为空");
            return result;
        }
        bookMapper.deleteById(bookId);
        result.put("success", true);
        return result;
    }
}

