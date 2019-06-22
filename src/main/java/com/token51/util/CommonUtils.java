package com.token51.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {
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
}
