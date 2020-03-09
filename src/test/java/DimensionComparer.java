import com.token51.util.ConstUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DimensionComparer {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\Users\\fushu\\Documents\\Account.txt"), "UTF-8"));
        Set<String> accountSet = new HashSet<>();
        while (bufferedReader.ready()) {
            accountSet.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\Users\\fushu\\Documents\\科目コード.txt"), "UTF-8"));
        Set<String> codeSet = new HashSet<>();
        while (bufferedReader.ready()) {
            codeSet.add(bufferedReader.readLine());
        }

        for (String code : codeSet) {
            if (!accountSet.contains(code)) {
                System.out.println(code);
            }
        }
    }
}
