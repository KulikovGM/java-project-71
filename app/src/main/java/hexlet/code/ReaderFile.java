package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReaderFile {

    public static void main(String[] args) throws Exception {

        // Чтение файла:
        // Получаем путь к нужному файлу
        String readFilePath = "src/test/resources/file1.json";

        // Формируем абсолютный путь,
        // если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path = Paths.get(readFilePath).toAbsolutePath().normalize();


        // Проверяем существование файла
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        // Читаем файл
        String content = Files.readString(path);

        // Выводим содержимое
        System.out.println(content);
    }
}
