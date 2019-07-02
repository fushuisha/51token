package com.token51.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {
    public static void main(String[] args) throws Exception  {
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
            Thread.sleep(5000);
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

}
