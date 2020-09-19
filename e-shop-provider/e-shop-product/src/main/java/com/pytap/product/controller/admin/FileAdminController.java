package com.pytap.product.controller.admin;

import com.pytap.common.annotation.Log;
import com.pytap.common.exception.GeneralException;
import com.pytap.common.utils.ResultEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件上传类
 * @author Ecin520
 * @date 2020/9/12 12:08
 */
@RequestMapping("/admin/file")
@RestController
public class FileAdminController {

    private static final String SAVE_PATH = "/usr/local/webserver/nginx/html/files/";

    @Log("上传文件")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultEntity<Object> fileUpload(@RequestParam("file") MultipartFile file) throws GeneralException {
        String fileName;
        if (null != file.getOriginalFilename()) {
            // 获取.号字符串的索引
            int begin = file.getOriginalFilename().lastIndexOf(".");
            int last = file.getOriginalFilename().length();
            // 重写文件名并加上获取的文件后缀名
            fileName = System.currentTimeMillis() + file.getOriginalFilename().substring(begin, last);
        } else {
            throw new GeneralException("文件格式不正确");
        }
        String path = SAVE_PATH + fileName;
        return saveFileToServer(file, path, fileName);
    }

    private ResultEntity<Object> saveFileToServer(MultipartFile file, String path, String fileName) {
        try {
            OutputStream os = new FileOutputStream(path);
            InputStream is = file.getInputStream();
            int len;
            while((len=is.read())!=(-1)) {
                os.write(len);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultEntity.fail(e.getMessage());
        }
        return ResultEntity.success( "上传成功", SAVE_PATH + fileName);
    }
}
