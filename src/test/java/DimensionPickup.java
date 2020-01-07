import com.token51.util.JacksonUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DimensionPickup {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\Users\\fushu\\Documents\\ProjectCode.txt"), "UTF-8"));
        Set<String> projectSet = new HashSet<>();
        while (bufferedReader.ready()) {
            projectSet.add(bufferedReader.readLine());
        }
        System.out.println(JacksonUtils.toPrettyJson(projectSet));
    }
}
