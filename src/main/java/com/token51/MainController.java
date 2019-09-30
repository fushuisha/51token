package com.token51;

import com.token51.util.CommonUtils;
import com.token51.util.ConstUtils;
import com.token51.util.JacksonUtils;
import com.token51.util.JsonResult;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@RestController
public class MainController {

    @Value("${server.port}")
    String port;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * http://127.0.0.1:8080/health_check
     *
     * @return
     */
    @RequestMapping(value = "/health_check")
    public String healthCheck() {
        return "ok:" + port;
    }

    /**
     * http://127.0.0.1:8080/action/count
     *
     * @return
     */
    @RequestMapping(value = "/action/count")
    public String actionCount() {
        Map<String, String> resultMap = new HashMap<>();
        String actionCountKey = CommonUtils.getActionCountKey("/qrcode/generate");
        resultMap.put(actionCountKey, stringRedisTemplate.opsForValue().get(actionCountKey));
        return JacksonUtils.toPrettyJson(resultMap);
    }

    /**
     * http://127.0.0.1:8080/get4DigitToken?schema=51token
     *
     * @return
     */
    @RequestMapping(value = "/get4DigitToken")
    public String get4DigitToken(@RequestParam(required = false) String schema,
                                 @RequestParam(required = false) String length,
                                 @RequestParam(required = false) String expire) {
        schema = StringUtils.trimToEmpty(schema);
        length = StringUtils.trimToEmpty(length);
        expire = StringUtils.trimToEmpty(expire);
        if (StringUtils.isBlank(schema) || schema.length() > 16) {
            schema = ConstUtils.SCHEMA;
        }
        int lengthInt = 4;
        if (StringUtils.isNotBlank(length)) {
            if (GenericValidator.isInt(length)) {
                lengthInt = Integer.parseInt(length);
                if (lengthInt < 1 || lengthInt > 9) {
                    lengthInt = 4;
                }
            }
        }

        int expireInt = 30;
        if (StringUtils.isNotBlank(expire)) {
            if (GenericValidator.isInt(expire)) {
                expireInt = Integer.parseInt(expire);
                if (expireInt < 1 || expireInt > 60 * 60 * 24) {
                    expireInt = 30;
                }
            }
        }

        long currentTimeMillis = System.currentTimeMillis();
        Random random = new Random(currentTimeMillis);
        int random4Digit = random.nextInt(new Double(Math.pow(10, lengthInt * 1d)).intValue());
        String pattern = StringUtils.EMPTY;
        for (int i = 0; i < lengthInt; i++) {
            pattern += "0";
        }
        final DecimalFormat df = new DecimalFormat(pattern);
        String random4DigitStr = df.format(random4Digit);
        Map<String, String> random4DigitMap = new ConcurrentHashMap<>(8);
        random4DigitMap.put("schema", schema);
        random4DigitMap.put("time", String.valueOf(currentTimeMillis));
        random4DigitMap.put("token", random4DigitStr);
        stringRedisTemplate.boundValueOps("schema=" + Hex.encodeHexString(schema.getBytes()))
                .set(JacksonUtils.toPrettyJson(random4DigitMap), expireInt, TimeUnit.SECONDS);
        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS, random4DigitStr);
        CommonUtils.actionCount("/get4DigitToken", stringRedisTemplate);
        return JacksonUtils.callback(CommonUtils.getCurrentRequest(), JacksonUtils.toJson(result));
    }

    /**
     * http://127.0.0.1:8080/verify4DigitToken?schema=51token&token=9684
     *
     * @return
     */
    @RequestMapping(value = "/verify4DigitToken")
    public String verify4DigitToken(@RequestParam(required = false) String schema, @RequestParam String token) {
        schema = StringUtils.trimToEmpty(schema);
        token = StringUtils.trimToEmpty(token);
        if (StringUtils.isBlank(schema)) {
            schema = ConstUtils.SCHEMA;
        }

        if (StringUtils.isNotBlank(token)) {
            String redisStr = stringRedisTemplate.boundValueOps("schema=" + Hex.encodeHexString(schema.getBytes())).get();
            if (StringUtils.isNotBlank(redisStr)) {
                Map random4DigitMap = JacksonUtils.fromJson(redisStr, ConcurrentHashMap.class);
                if (CommonUtils.isValidCollect(random4DigitMap)) {
                    String saveToken = (String) random4DigitMap.get("token");
                    if (StringUtils.equals(token, saveToken)) {
                        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.SUCCESS,
                                schema + ":" + token + ":" + ConstUtils.SUCCESS);
                        return JacksonUtils.callback(CommonUtils.getCurrentRequest(), JacksonUtils.toJson(result));
                    }
                }
            }
        }

        JsonResult result = JacksonUtils.genJsonResult(ConstUtils.FAIL, schema + ":" + token + ":" + ConstUtils.FAIL);
        CommonUtils.actionCount("/verify4DigitToken", stringRedisTemplate);
        return JacksonUtils.callback(CommonUtils.getCurrentRequest(), JacksonUtils.toJson(result));
    }
}
