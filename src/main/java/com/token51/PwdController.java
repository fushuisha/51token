package com.token51;

import com.token51.util.CommonUtils;
import com.token51.util.ConstUtils;
import com.token51.util.JacksonUtils;
import com.token51.util.JsonResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class PwdController {

    private final String typeOpenssl = "openssl";
    private final String typeGpg = "gpg";
    private final String typeGpg2 = "gpg2";
    private final String typeEasy = "easy";
    private final String typeNormal = "normal";
    private final String typeHard = "hard";

    /**
     * http://127.0.0.1:8080/pwdgen?type=openssl&length=14
     *
     * @return
     */
    @RequestMapping(value = "/pwdgen")
    public String pwdgen(@RequestParam(required = false) String type,
                               @RequestParam(required = false) String length) {
        type = StringUtils.trimToEmpty(type);
        int lengthInt = 10;
        if (StringUtils.isNotBlank(length) && GenericValidator.isInt(length)) {
            lengthInt = Integer.parseInt(length);
        }
        if (lengthInt > 16) {
            lengthInt = 10;
        }
        String dataResult;
        switch (type) {
            case typeNormal: {
                String cmd =  "pwgen -s " + lengthInt+" 1";
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
            case typeHard: {
                String cmd =  "pwgen -cnys " + lengthInt+" 1";
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
            default: {
                // easy
                String cmd = "pwgen " + lengthInt+" 1";
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
        }
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, dataResult);
        return JacksonUtils.callback(CommonUtils.getCurrentRequest(), JacksonUtils.toJson(result));
    }

    /**
     * http://127.0.0.1:8080/pwdgenBase64?type=openssl&length=14
     *
     * @return
     */
    @RequestMapping(value = "/pwdgenBase64")
    public String pwdgenBase64(@RequestParam(required = false) String type,
                               @RequestParam(required = false) String length) {
        type = StringUtils.trimToEmpty(type);
        int lengthInt = 10;
        if (StringUtils.isNotBlank(length) && GenericValidator.isInt(length)) {
            lengthInt = Integer.parseInt(length);
        }
        if (lengthInt > 16) {
            lengthInt = 10;
        }
        String dataResult;
        switch (type) {
            case typeOpenssl: {
                String cmd = "openssl rand -base64 " + lengthInt;
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
            case typeGpg: {
                String cmd = "gpg --gen-random --armor 1 " + lengthInt;
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
            case typeGpg2: {
                String cmd = "gpg2 --gen-random --armor 1 " + lengthInt;
                dataResult = CommonUtils.executeCmd(cmd);
                break;
            }
            default: {
                // java
                dataResult = Base64.encodeBase64String(((System.currentTimeMillis() * (new Random().nextInt(99) + 2)) + "").getBytes());
                dataResult = StringUtils.substring(dataResult, lengthInt * -1);
                break;
            }
        }
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, dataResult);
        return JacksonUtils.callback(CommonUtils.getCurrentRequest(), JacksonUtils.toJson(result));
    }

}
