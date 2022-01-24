import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CalculateLine {
    public static void main(String[] args) throws Exception {
        File fold = new File("d:\\shadawei\\git\\rpa\\03_Source\\rpa_admin_vue\\");
        List<String> filePathList = new ArrayList<>();
        treeFold(fold, filePathList);
        int lineNum = 0;
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/tmp/enrobot.txt")));
        for (String filePath : filePathList) {
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while (bufferedReader.ready()) {
                lineNum++;
                String line = bufferedReader.readLine();
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                bufferedWriter.write(line);
                bufferedWriter.write("\n");
            }
            bufferedReader.close();
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        System.out.println(lineNum);
    }

    private static void treeFold(File fold, List<String> filePathList) {
        if (fold.isDirectory()) {
            File[] files = fold.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String filePath = file.getPath();
                    if (StringUtils.endsWithAny(filePath, ".js", ".css", ".scss", ".vue", ".xml", ".yml", ".java", ".py")) {
                        filePathList.add(filePath);
                    }
                } else {
                    treeFold(file, filePathList);
                }
            }
        } else {
            return;
        }
    }
}
