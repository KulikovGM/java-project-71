package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReaderFile {

    public static void reading() throws Exception {

        String readFile1Path = "src/test/resources/file1.json";
        String readFile2Path = "src/test/resources/file2.json";
        // Формируем абсолютный путь
        Path path1 = Paths.get(readFile1Path).toAbsolutePath().normalize();
        Path path2 = Paths.get(readFile2Path).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path1) || !Files.exists(path2)) {
            throw new Exception("File(s) '" + path1 + "' or/and '" + path2 + "' does not exist");
        }
        // Читаем файл
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);
        // Выводим содержимое
        System.out.println(content1);
        System.out.println(content2);
        // Mapping file
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> mappedFile1 = new HashMap<>();
        Map<String, Object> mappedFile2 = new HashMap<>();

        mappedFile1 = mapper.readValue(new File(readFile1Path), new TypeReference<>() { });
        mappedFile2 = mapper.readValue(new File(readFile2Path), new TypeReference<>() { });


        System.out.println("mappedFile1 = " + mappedFile1);
        System.out.println("mappedFile2 = " + mappedFile2);

        //compare
        List<Map<String, Object>> compareResult = differ(mappedFile1, mappedFile2);
        System.out.println(compareResult);
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
}
