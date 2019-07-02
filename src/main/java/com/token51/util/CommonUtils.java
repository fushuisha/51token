package com.token51.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
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
        Runtime rt = Runtime.getRuntime(); // 运行时系统获取
//        Map<String, String> lineMap = new HashMap<String, String>();//存放返回值
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Process proc = rt.exec(cmd);// 执行命令
            InputStream stderr = proc.getInputStream();//执行结果 得到进程的标准输出信息流
            Thread.sleep(5000);
            InputStreamReader isr = new InputStreamReader(stderr);//将字节流转化成字符流
            BufferedReader br = new BufferedReader(isr);//将字符流以缓存的形式一行一行输出
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

    public static String obj2str (Object obj) {
        return obj == null ? "":obj.toString();
    }
}
