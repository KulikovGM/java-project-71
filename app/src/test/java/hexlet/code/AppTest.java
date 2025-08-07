package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    AppTest() throws IOException {
    }

    private final Path formatStylish =
            Paths.get("src/test/resources/formatStylish.txt").toAbsolutePath().normalize();
    private final Path formatPlain =
            Paths.get("src/test/resources/formatPlain.txt").toAbsolutePath().normalize();
    private final Path formatJson =
            Paths.get("src/test/resources/resultJson.txt").toAbsolutePath().normalize();
    private final String pathYml1 = "src/test/resources/fileYml3.yml";
    private final String pathYml2 = "src/test/resources/fileYml4.yml";
    private final String pathJson1 = "src/test/resources/file3.json";
    private final String pathJson2 = "src/test/resources/file4.json";

    @Test
    void testYmlDefault() throws IOException {
        String actual = Files.readString(formatStylish);
        String expected = Differ.generate(pathYml1, pathYml2);
        assertEquals(actual, expected);
    }

    @Test
    void testYmlStylish() throws IOException {
        String actual = Files.readString(formatStylish);
        String expected = Differ.generate(pathYml1, pathYml2, "stylish");
        assertEquals(actual, expected);
    }

    @Test
    void testYmlPlain() throws IOException {
        String actual = Files.readString(formatPlain);
        String expected = Differ.generate(pathYml1, pathYml2, "plain");
        assertEquals(actual, expected);
    }

    @Test
    void testYmlJson() throws IOException {
        String actual = Files.readString(formatJson);
        String expected = Differ.generate(pathYml1, pathYml2, "json");
        assertEquals(actual, expected);
    }

    @Test
    void testJsonDefault() throws IOException {
        String actual = Files.readString(formatStylish);
        String expected = Differ.generate(pathJson1, pathJson2);
        assertEquals(actual, expected);
    }

    @Test
    void testJsonStylish() throws IOException {
        String actual = Files.readString(formatStylish);
        String expected = Differ.generate(pathJson1, pathJson2, "stylish");
        assertEquals(actual, expected);
    }

    @Test
    void testJsonPlain() throws IOException {
        String actual = Files.readString(formatPlain);
        String expected = Differ.generate(pathJson1, pathJson2, "plain");
        assertEquals(actual, expected);
    }

    @Test
    void testJson() throws IOException {
        String actual = Files.readString(formatJson);
        String expected = Differ.generate(pathJson1, pathJson2, "json");
        assertEquals(actual, expected);
    }
}
