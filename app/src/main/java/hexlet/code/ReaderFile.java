package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class ReaderFile {

    public static void reading() throws Exception {

        String readFilePath = "src/test/resources/file1.json";
        String readFile2Path = "src/test/resources/file2.json";
        // Формируем абсолютный путь
        Path path = Paths.get(readFilePath).toAbsolutePath().normalize();
        Path path2 = Paths.get(readFile2Path).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path) || !Files.exists(path2)) {
            throw new Exception("File(s) '" + path + " " + path2 + "' does not exist");
        }
        // Читаем файл
        String content = Files.readString(path);
        String content2 = Files.readString(path2);
        // Выводим содержимое
        System.out.println(content);
        System.out.println(content2);
        // Mapping file
        ObjectMapper mapper = new ObjectMapper();
        Map mappedFile = mapper.readValue(content, Map.class);
        Map mappedFile2 = mapper.readValue(content2, Map.class);
        System.out.println("mappedFile = " + mappedFile);
        System.out.println("mappedFile = " + mappedFile2);
    }
}
