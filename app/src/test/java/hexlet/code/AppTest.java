package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    AppTest() throws IOException {
    }

    private static Path formatStylish;
    private static Path formatPlain;
    private static Path formatJson;
    private static String pathYml1;
    private static String pathYml2;
    private static String pathJson1;
    private static String pathJson2;
    private static String wrongPath;

    @BeforeAll
    static void beforeAll() {
        formatStylish = Paths.get("src/test/resources/formatStylish.txt").toAbsolutePath().normalize();
        formatPlain = Paths.get("src/test/resources/formatPlain.txt").toAbsolutePath().normalize();
        formatJson = Paths.get("src/test/resources/resultJson.txt").toAbsolutePath().normalize();
        pathYml1 = "src/test/resources/fileYml3.yml";
        pathYml2 = "src/test/resources/fileYml4.yml";
        pathJson1 = "src/test/resources/file3.json";
        pathJson2 = "src/test/resources/file4.json";
        wrongPath = "src/file.json";
    }

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

    @Test
    void testWrongPathDiffer() throws IOException {
        boolean exceptionThrown = false;
        try {
            Differ.generate(wrongPath, wrongPath, "json");
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    void testUnknownFormat() throws IOException {
        boolean exceptionThrown = false;
        try {
            Differ.generate(wrongPath, wrongPath, "unknown format");
        } catch (IOException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

}
