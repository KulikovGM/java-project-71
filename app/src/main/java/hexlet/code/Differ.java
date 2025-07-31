package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"java:S118", "java:S106", "java:S1192"})
public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        // Формируем абсолютный путь
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        // Проверяем существование файла(ов)
        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new Exception("File '" + path1 + "' or/and File '" + path2 + "' does not exist");
        }
        String format1 = filePath1.substring(filePath1.indexOf(".") + 1);
        String format2 = filePath2.substring(filePath2.indexOf(".") + 1);

        Map<String, Object> mappedFile1 = Parser.parsing(filePath1, format1);
        Map<String, Object> mappedFile2 = Parser.parsing(filePath2, format2);

        List<Map<String, Object>> compareResult = Difference.diff(mappedFile1, mappedFile2);

        return (Formatter.formatter(compareResult, format));
    }

}
