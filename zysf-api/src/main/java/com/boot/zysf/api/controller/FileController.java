package com.boot.zysf.api.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
@Controller
@ResponseBody
@RequestMapping("/file")
public class FileController {

    @Value("${file.path_dev}")
    String Path;
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "文件上传")
//    @ApiImplicitParams(
//            @ApiImplicitParam(name="fieldName",value = "检索领域",required = true,dataTypeClass = String.class)
//    )
    public String fileUplod(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return "文件为空，上传失败";
        }
        try {
            String fileName = file.getOriginalFilename();
            String newPath = Path +fileName;
            java.nio.file.Path path = Paths.get(newPath);
            Files.write(path, file.getBytes());
            return "succeed";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }


    @GetMapping("/download")
    public String downLoad(HttpServletResponse response,@RequestParam("filename") String filename) throws UnsupportedEncodingException {
        File file = new File(Path + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(filename,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download---" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

}
