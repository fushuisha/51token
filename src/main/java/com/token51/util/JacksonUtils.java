package com.token51.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JacksonUtils {
    private static ObjectMapper jackson = new ObjectMapper();

    static {
        jackson.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        jackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jackson.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jackson.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static ObjectMapper getJackson() {
        return jackson;
    }

    public static void setJackson(ObjectMapper jackson) {
        JacksonUtils.jackson = jackson;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return jackson.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object obj) {
        try {
            return jackson.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toPrettyJson(Object obj) {
        try {
            return jackson.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJsonToList(String json, Class<T> collectionClass, Class<?>... elementClasses) {
        try {
            JavaType javatype = jackson.getTypeFactory().constructParametricType(collectionClass, elementClasses);
            return jackson.readValue(json, javatype);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
//		return jackson.getTypeFactory().constructParametricType(collectionClass, elementClasses);
//	}

//	public static class Test{
//		private String a;
//		private Long c;
//		private Object d;
//		private String mm;
//		
//		public Object getD() {
//			return d;
//		}
//		public void setD(Object d) {
//			this.d = d;
//		}
//		public String getA() {
//			return a;
//		}
//		public void setA(String a) {
//			this.a = a;
//		}
//		public Long getC() {
//			return c;
//		}
//		public void setC(Long c) {
//			this.c = c;
//		}
//		public String getMm() {
//			return mm;
//		}
//		public void setMm(String mm) {
//			this.mm = mm;
//		}
//		
//		
//	}

//	public static void main(String [] args) {
//		String test = "{\"a\":\"b\",\"c\":2,\"d\":[1,2,3]}";
//		Test obj = (Test) JacksonUtils.fromJson(test, Test.class);
//		System.out.println(obj.d);
//		System.out.println(JacksonUtils.toJson(obj));
//		
//	}

    public static String callback(HttpServletRequest request,
                                  String response) {
        String callback = request.getParameter("callback");
        if (StringUtils.isNotBlank(callback)) {
            StringBuilder repBuilder = new StringBuilder();
            repBuilder.append(callback)
                    .append("(").append(response).append(")");
            return repBuilder.toString();
        }
        return response;
    }

    public static JsonResult genJsonResult(String msg, Object data) {
        JsonResult result = new JsonResult();
        result.setCode(StringUtils.startsWith(msg, ConstUtils.FAIL) ? "500" : "200");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }


}
