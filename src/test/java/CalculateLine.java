import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CalculateLine {
    public static void main(String[] args) throws Exception {
        File fold = new File("d:\\tmp\\calline");
        File[] files = fold.listFiles();
        int lineNum = 0;
        for (File file : files) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while (bufferedReader.ready()) {
                lineNum++;
                bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        System.out.println(lineNum);
    }
}
