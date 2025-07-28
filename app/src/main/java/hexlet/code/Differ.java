package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

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
        // Mapping

        Map<String, Object> mappedFile1 = Parser.parsing(filePath1);
        Map<String, Object> mappedFile2 = Parser.parsing(filePath2);

        // Compare
        List<Map<String, Object>> compareResult = differ(mappedFile1, mappedFile2);

        return (Formatter.formatter(compareResult, format));
    }

    public static List<Map<String, Object>> differ(Map<String, Object> mappedFile1, Map<String, Object> mappedFile2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(mappedFile1.keySet());
        keysSet.addAll(mappedFile2.keySet());
        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (mappedFile1.containsKey(key) && !mappedFile2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("status", "removed");
            } else if (!mappedFile1.containsKey(key) && mappedFile2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", mappedFile2.get(key));
                map.put("status", "added");
            } else if (!Objects.equals(mappedFile1.get(key), mappedFile2.get(key))) {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("newValue", mappedFile2.get(key));
                map.put("status", "updated");
            } else {
                map.put("key", key);
                map.put("oldValue", mappedFile1.get(key));
                map.put("status", "unchanged");
            }
            result.add(map);
        }
        return result;
    }

    public static class Formatter {
        public static String formatter(List<Map<String, Object>> differences, String format) {
            switch (format) {
                case "stylish":
                    return Stylish.format(differences);
                case "plain":
                    return Plain.format(differences);
                case "json":
                    return Json.format(differences); // NewFormat JSON ->
                default:
                    System.out.println("Format" + format + "is not correct!");
            }
            return Stylish.format(differences);
        }
    }
}
