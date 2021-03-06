package com.avic.controller.api;

import com.alibaba.fastjson.JSON;
import com.avic.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileController {

    @Value("${img.path}")
    private String path;

    @RequestMapping("/pdfUpload")
    @CrossOrigin(origins = {"*","null"})
    @ResponseBody
    public Map imageUpload(@RequestParam("fileName") MultipartFile file){
        String result_msg = "";//上传结果信息
        Map<String,Object> root = new HashMap<String, Object>();
        if (file.getSize() / 1000 > 20000){
            result_msg="error";
        }
        else{
            //判断上传文件格式
            String fileType = file.getContentType();
            final String localPath = path + "/web/mypdf/";

            File uploadImageDir = new File(localPath);
            if (!uploadImageDir.exists()) {
                uploadImageDir.mkdirs();
            }

            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName = UUID.randomUUID() + suffixName;
            if (FileUtils.upload(file, localPath, fileName)) {
                String relativePath = fileName;
                root.put("url", relativePath);
                result_msg="success";
            }
            else{
                result_msg="error";
            }
        }
        root.put("status",result_msg);
        String root_json = JSON.toJSONString(root);
        return root;
    }
}