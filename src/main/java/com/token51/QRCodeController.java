package com.token51;

import com.token51.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

@RestController
public class QRCodeController {
    private final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

    /**
     * http://127.0.0.1:8080/qrcode/encode?content=http://www.51token.club
     *
     * @return
     */
    @RequestMapping(value = "/qrcode/encode")
    public String get4DigitToken(MultipartHttpServletRequest request) throws Exception {
        String content = request.getParameter("content");
        content = StringUtils.trimToEmpty(content);
        if (StringUtils.isBlank(content)) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL, "内容不能为空");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        if (content.length() > 500) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL, "内容过长");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        MultipartFile insertPic = request.getFile("insertPic");
        long currentTime = System.currentTimeMillis();
        String destPath = "/usr/local/nginx/html/51token.xyz/images/" + currentTime + ".jpg";
        if (insertPic == null) {
            QRCodeUtil.encode(content, destPath);
        } else {
            String imgPath = "/tmp/" + System.currentTimeMillis();
            CommonUtils.saveInsertPic(imgPath, insertPic.getBytes(), logger);
            File imgFile = new File(imgPath);
            Image image = ImageIO.read(imgFile);
            if (image == null) {
                JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL, "不是图片文件");
                return JacksonUtils.callback(request, JacksonUtils.toJson(result));
            }
            QRCodeUtil.encode(content, imgPath, destPath, true);
            imgFile.delete();
        }
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, destPath.replace("/usr/local/nginx/html/","http://www."));
        return JacksonUtils.callback(request, JacksonUtils.toJson(result));
    }

}
