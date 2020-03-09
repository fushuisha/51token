import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FindComponent {
    public static void main(String[] args) throws Exception {
        find(new File("d:\\shadawei\\git\\yontaku\\03_Source\\mgame-admin\\src\\main\\java\\com\\mgame\\mapper"));
        find(new File("d:\\shadawei\\git\\yontaku\\03_Source\\mgame-socket\\src\\main\\java\\com\\mgame\\socket\\business\\mapper"));
    }

    private static void find(File fold) throws Exception {
        if (!fold.isDirectory()) {
            return;
        }
        File[] files = fold.listFiles();
        for (File dto : files) {
            if (dto.isDirectory()) {
                continue;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dto), "utf-8"));
            boolean hasTableId = false;
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                if (line.contains("@Component")) {
                    hasTableId = true;
                    break;
                }
            }
            bufferedReader.close();
            if (!hasTableId) {
                System.out.println(dto.getCanonicalPath());
            }
        }
    }
}
