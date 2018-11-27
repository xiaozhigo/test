package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wulei
 * @date 2018-11-22 17:22
 */
@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    @RequestMapping
    public String index(){
        return "index";
    }

    @RequestMapping("/upload1")
    @ResponseBody
    public Map<String,String> upload1(@RequestParam("file") MultipartFile file) throws IOException {
           // TODO 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx 进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
           file.transferTo(new File("C:\\Users\\wulei\\Desktop\\"+file.getOriginalFilename()));
           Map<String, String> map = new HashMap<>();
           map.put("contentType",file.getContentType());
           map.put("fileName",file.getOriginalFilename());
           map.put("fileSize",file.getSize()+"");
           return map;
    }

    @RequestMapping("/upload2")
    @ResponseBody
    public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
           if(files.length == 0 || files == null){
               return null;
           }
           List<Map<String, String>> list = new ArrayList<>();
           for(int i = 0;i < files.length;i++){
               Map<String, String> map = new HashMap<>();
               MultipartFile file = files[i];
               file.transferTo(new File("C:\\Users\\wulei\\Desktop\\"+file.getOriginalFilename()));
               map.put("contentType",file.getContentType());
               map.put("fileName",file.getOriginalFilename());
               map.put("fileSize",file.getSize()+"");
               list.add(map);
           }
           return list;
    }

    @RequestMapping("upload3")
    @ResponseBody
    public void upload3(String base64) throws IOException {
        // TODO BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
       final File file = new File("C:\\Users\\wulei\\Desktop\\test.jpg");
        // TODO 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes,file);
    }
}
