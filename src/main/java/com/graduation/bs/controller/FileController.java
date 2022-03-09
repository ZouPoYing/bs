package com.graduation.bs.controller;


import com.graduation.bs.mapper.FileMapper;
import com.graduation.bs.utils.FileDownLoad;
import com.graduation.bs.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
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
@RequestMapping("/bs/file")
public class FileController {

    @Resource
    private FileMapper fileMapper;

    @RequestMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("userId")  String userId) throws Exception {
        Map<String, Object> result = new HashMap<>();
        if(userId.isEmpty()){
            result.put("msg", "参数不能为空");
            return result;
        }
        String fileName = FileUploadUtil.upload(multipartFile,userId);
        com.graduation.bs.dao.File file = new com.graduation.bs.dao.File();
        file.setFileName(fileName);
        file.setUserId(userId);
        fileMapper.insert(file);
        result.put("success", true);
        result.put("fileId", file.getFileId());
        return result;
    }

    @GetMapping("/downloadFile/{fileName}")
    public void downloadFile(@PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
        //1.获取文件绝对路径
        String path = "D:/IDEAProjects/bs/src/main/resources/static/" + fileName;
        //2.通过绝对路径定义File
        File f = new File(path);
        //3.调用FileUtil下载文件
        FileDownLoad.downloadFile(request,response,f,false);
    }
}

