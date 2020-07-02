package com.avic.controller.ueditor;

import com.avic.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UEditorController {

    @Value("${img.path}")
    private String path;

    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        return PublicMsg.UEDITOR_CONFIG;
    }

    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        return ueditor;
    }

    /**
     * 上传配置：即不走config.json，模拟config.json里的内容，解决后端配置项不正确，无法上传的问题
     * @return
     */
    @RequestMapping("/ueditor/config")
    @ResponseBody
    public String uploadConfig() {
        String s = "{\n" +
                "            \"imageActionName\": \"uploadimage\",\n" +
                "                \"imageFieldName\": \"upfile\", \n" +
                "                \"imageMaxSize\": 2048000, \n" +
                "                \"imageAllowFiles\": [\".png\", \".jpg\", \".jpeg\", \".gif\", \".bmp\"], \n" +
                "                \"imageCompressEnable\": true, \n" +
                "                \"imageCompressBorder\": 1600, \n" +
                "                \"imageInsertAlign\": \"none\", \n" +
                "                \"imageUrlPrefix\": \"\",\n" +
                "                \"imagePathFormat\": \"/ueditor/jsp/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}\" }";
        return s;
    }


    @PostMapping("/upload")
    @ResponseBody
    public Map<String,String> uploadFile(MultipartFile upfile){
        String result_msg = "";//上传结果信息

        if (upfile.getSize() / 1000 > 20000){
            return null;
        } else{
            //判断上传文件格式
            String fileType = upfile.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png") || fileType.equals("image/jpeg")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd/");
                String subPath = dateFormat.format(new Date());
                final String localPath = path + subPath;

                File uploadImageDir = new File(localPath);
                if (!uploadImageDir.exists()) {
                    uploadImageDir.mkdirs();
                }
                String fileName = upfile.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                fileName = UUID.randomUUID() + suffixName;
                if (FileUtils.upload(upfile, localPath, fileName)) {
                    String relativePath = subPath + fileName;
                    Map<String,String> map = new HashMap<String, String>();
                    map.put("state", "SUCCESS");
                    map.put("url", "/img/" + relativePath);
                    map.put("title", fileName);
                    map.put("original", fileName);
                    return map;
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
        }
    }

}