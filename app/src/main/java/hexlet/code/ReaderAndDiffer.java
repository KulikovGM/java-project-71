package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ReaderAndDiffer {

    public static String readAndDiff(String filePath1, String filePath2) throws Exception {

        // Формируем абсолютный путь
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        // Проверяем существование файла(ов)
        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new Exception("File '" + path1 + "' or/and File '" + path2 + "' does not exist");
        }
        // Mapping
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> mappedFile1 = Parser.parsing(path1);
        Map<String, Object> mappedFile2 = Parser.parsing(path2);

        // Compare
        List<Map<String, Object>> compareResult = differ(mappedFile1, mappedFile2);

        return (Stylish.formatStylish(compareResult));
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

    public class Stylish {
        public static String formatStylish(List<Map<String, Object>> differences) {
            StringBuilder result = new StringBuilder("{\n");
            for (Map<String, Object> diffs : differences) {
                switch (diffs.get("status").toString()) {
                    case "removed" -> result.append("  - ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    case "added" -> result.append("  + ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("newValue")).append("\n");
                    case "unchanged" -> result.append("    ").append(diffs.get("key")).append(": ")
                            .append(diffs.get("oldValue")).append("\n");
                    default -> {
                        result.append("  - ").append(diffs.get("key")).append(": ")
                                .append(diffs.get("oldValue")).append("\n");
                        result.append("  + ").append(diffs.get("key")).append(": ")
                                .append(diffs.get("newValue")).append("\n");
                    }
                }
            }
            result.append("}");
            return result.toString();
        }
    }
}
