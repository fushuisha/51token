package com.token51;

import com.token51.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStreamImpl;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@RestController
public class QRCodeController {
    private final Logger logger = LoggerFactory.getLogger(QRCodeController.class);

    /**
     * http://127.0.0.1:8080/qrcode/encode?content=http://www.51token.club
     *
     * @return
     */
    @RequestMapping(value = "/qrcode/generate", method = {RequestMethod.GET, RequestMethod.POST})
    public String qrCodeGenerate(String qrContent, @RequestParam(required = false) String qrLogo) throws Exception {
        String content = StringUtils.trimToEmpty(qrContent);
        HttpServletRequest request = CommonUtils.getCurrentRequest();
        if (StringUtils.isBlank(content)) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":内容不能为空", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
        if (content.length() > 500) {
            JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL + ":内容过长", "");
            return JacksonUtils.callback(request, JacksonUtils.toJson(result));
        }
//        MultipartFile logo = ((MultipartHttpServletRequest)request).getFile("qrLogo");
        Image logoImage = null;
        if (StringUtils.isNotBlank(qrLogo)) {
            try {
                byte[] bytes = Base64.decodeBase64(qrLogo.split("base64,")[1]);
                logoImage = ImageIO.read(new ByteArrayInputStream(bytes));
            } catch (Exception ex) {
                logger.warn(qrLogo, ex);
            }
        }
        BufferedImage bufferedImage = QRCodeUtil.createImage(content, logoImage, true);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, QRCodeUtil.FORMAT_NAME, outputStream);
        //ImageIO.write(bufferedImage, QRCodeUtil.FORMAT_NAME, new File("/tmp/qrcode/qrcode.jpg"));
        String data = "data:image/jpeg;base64," + Base64.encodeBase64String(outputStream.toByteArray());
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, data);
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


    private class QrImageInputStreamImpl extends ImageInputStreamImpl {
        private ByteArrayInputStream stream;

        public QrImageInputStreamImpl(byte[] bytes) {
            this.stream = new ByteArrayInputStream(bytes);
        }

        @Override
        public int read() throws IOException {
            bitOffset = 0;

            int val = stream.read();

            if (val != -1) {
                streamPos++;
            }

            return val;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            if (b == null) {
                throw new NullPointerException("b == null!");
            }
            if (off < 0 || len < 0 || off + len > b.length || off + len < 0) {
                throw new IndexOutOfBoundsException("off < 0 || len < 0 || off+len > b.length || off+len < 0!");
            }

            bitOffset = 0;

            if (len == 0) {
                return 0;
            }

            int read = stream.read(b, off, len);

            if (read > 0) {
                streamPos += read;
            }

            return read;
        }
    }
}
