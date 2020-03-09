package com.token51.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.slf4j.Logger;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {
    public static void main(String[] args) throws Exception {
        String cmd = "ping www.baidu.com";
        String result = executeCmd(cmd);
        System.out.println(result);
    }

    public static HttpServletRequest getCurrentRequest() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return request;
        } catch (Exception ex) {
            return null;
        }
    }

    public static boolean isValidCollect(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof Collection) {
            Collection<?> objl = (Collection<?>) obj;
            return !objl.isEmpty();
        } else if (obj instanceof Object[]) {
            Object[] objs = (Object[]) obj;
            return objs.length > 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) > 0;
        } else if (obj instanceof Map) {
            Map<?, ?> objm = (Map<?, ?>) obj;
            return !objm.isEmpty();
        } else if (obj instanceof Iterable) {
            Iterable obji = (Iterable) obj;
            return obji.iterator() == null ? false : obji.iterator().hasNext();
        } else {
            return false;
        }
    }

    public static String getSpringProfilesActive() {
        String active = System.getProperty(ConstUtils.SPRING_PROFILES_ACTIVE);
        if (StringUtils.isNotBlank(active)) {
            return active;
        }
        active = System.getenv(ConstUtils.SPRING_PROFILES_ACTIVE);
        return active;
    }

    public static String executeCmd(String cmd) {
        Runtime rt = Runtime.getRuntime();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Process proc = rt.exec(cmd);
            InputStream stderr = proc.getInputStream();
            Thread.sleep(50);
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            while (br.ready()) {
                String line = br.readLine();
                stringBuilder.append(line + "\n");
            }
            br.close();
            isr.close();
            stderr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static boolean saveInsertPic(String picPath, byte[] picBytes, Logger logger) {
        if (StringUtils.isBlank(picPath) || picBytes == null) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(picPath);
            if (!file.isFile()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(picBytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception ex) {
            logger.warn(picPath, ex);
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception ex) {
                    logger.warn(picPath, ex);
                }
            }
        }
    }

    public static String getActionCountKey(String arg) {
        return "action_count" + arg.replace("/", "_");
    }

    public static void actionCount(String arg, StringRedisTemplate stringRedisTemplate) {
        String actionCountKey = CommonUtils.getActionCountKey(arg);
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(actionCountKey);
        String actionCountStr = boundValueOperations.get();
        if (StringUtils.isNotBlank(actionCountStr) && GenericValidator.isLong(actionCountStr)) {
            Long actionCountLong = Long.parseLong(actionCountStr);
            actionCountLong += 1;
            boundValueOperations.set(actionCountLong.toString());
        } else {
            boundValueOperations.set("1");
        }
    }
}
