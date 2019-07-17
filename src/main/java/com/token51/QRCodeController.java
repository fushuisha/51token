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
import java.io.ByteArrayInputStream;
import java.io.File;

@RestController
public class QRCodeController {
    private final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

    /**
     * http://127.0.0.1:8080/qrcode/encode?content=http://www.51token.club
     *
     * @return
     */
    @RequestMapping(value = "/qrcode/generate")
    public String qrCodeGenerate(MultipartHttpServletRequest request) throws Exception {
        String content = request.getParameter("content");
        content = StringUtils.trimToEmpty(content);
        if (StringUtils.isBlank(content)) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":内容不能为空", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        if (content.length() > 500) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":内容过长", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        MultipartFile avatar = request.getFile("avatar");
        long currentTime = System.currentTimeMillis();
        String destPath = "/usr/local/nginx/html/51token.xyz/images/" + currentTime + ".jpg";
        String failAttach = "";
        if (avatar == null) {
            failAttach = ":输入文件为空";
            QRCodeUtil.encode(content, destPath);
        } else {
            byte[] bytes = avatar.getBytes();
            if (bytes == null) {
                failAttach = ":输入文件为空";
                QRCodeUtil.encode(content, destPath);
            } else {
                Image image = ImageIO.read(new ByteArrayInputStream(bytes));
                if (image == null) {
                    failAttach = ":输入文件不是图片文件";
                    QRCodeUtil.encode(content, destPath);
                } else {
                    QRCodeUtil.encode(content, image, destPath, true);
                }
            }
        }
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS + failAttach, destPath.replace("/usr/local/nginx/html/", "http://www."));
        return JacksonUtils.callback(request, JacksonUtils.toJson(result));
    }

    @RequestMapping(value = "/qrcode/verify")
    public String qrCodeVerify(MultipartHttpServletRequest request) throws Exception {
        MultipartFile qrcode = request.getFile("qrcode");
        if (qrcode == null) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":输入文件为空", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        byte[] bytes = qrcode.getBytes();
        if (bytes == null) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":输入文件为空", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        String str = QRCodeUtil.decode(byteArrayInputStream);
        if (StringUtils.isBlank(str)) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":输入文件不是二维码文件", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, str);
        return JacksonUtils.callback(request, JacksonUtils.toJson(result));
    }

}
