import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class code3_1 {

    public static void main(String[] args) {
        // 检查是否有输入文件路径
        if (args.length != 1) {
            System.out.println(
                    "Usage: java WordFrequency <\"D:\\muma\\Documents\\code\\website-design\\webdesign\\Index.html\">");
            return;
        }

        String filePath = args[0];
        String content = readFile(filePath);

        // 判断文件是否是HTML文件
        if (filePath.endsWith(".html") || filePath.endsWith(".htm")) {
            content = removeHtmlTags(content);
        }

        // 处理文本
        content = preprocessText(content);

        // 统计词频
        Map<String, Integer> wordFrequency = countWordFrequency(content);

        // 打印结果
        printWordFrequency(wordFrequency);
    }

    // 读取文件内容
    private static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    // 移除HTML标签
    private static String removeHtmlTags(String html) {
        return html.replaceAll("<[^>]*>", "");
    }

    // 预处理文本
    private static String preprocessText(String text) {
        // 移除所有数字和标点符号，转换为小写
        return text.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
    }

    // 统计词频
    private static Map<String, Integer> countWordFrequency(String text) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.isEmpty())
                continue;
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
        return wordFrequency;
    }

    // 打印词频
    private static void printWordFrequency(Map<String, Integer> wordFrequency) {
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordFrequency.entrySet());
        sortedWords.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
