package yxzz.controller;


import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import yxzz.util.FileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by shhao.
 * Date: 14-9-1
 * Time:ÏÂÎç4:32
 */
@Controller
public class FileUploadController {

    Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("upload")
    public void upload(@RequestParam("studentPhoto") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = FileUpload.uploadFile(file, request);
        logger.info("filePath:" + filePath);
        response.setContentType("text/html;charset=utf8");
        response.getWriter().write("<img src='"+filePath+"'/>");
    }

    @RequestMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        OutputStream os = response.getOutputStream();
        try {
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("image/jpeg; charset=utf-8");
            os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
