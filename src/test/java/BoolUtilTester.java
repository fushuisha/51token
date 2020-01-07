import org.apache.commons.lang3.BooleanUtils;

import java.util.HashMap;
import java.util.Map;

public class BoolUtilTester {
    public static void main(String[] args) throws Exception {
        System.out.println(BooleanUtils.toBoolean("no"));
        Object i = Long.MAX_VALUE;
        System.out.println(i);
        Map<String, Object> data = new HashMap<>();
        data.put("i",i);
        Long l = paramConvert(data,"i",Long.class);
        System.out.println(l);
    }


    public static <T>T paramConvert(Map<String, Object> data, String key, Class<T> clazz) {
        Object obj = data.get(key);
        if (obj == null) {
            return null;
        } else {
            if (clazz.equals(Long.class) ) {
                Long result = new Long(obj.toString());
                return (T)result;
            } else {
                return (T)obj;
            }
        }
    }
}
