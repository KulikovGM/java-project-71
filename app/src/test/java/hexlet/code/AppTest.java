package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    static void beforeAll() throws IOException {
        formatStylish = Paths.get("src/test/resources/formatStylish.txt").toAbsolutePath().normalize();
        formatPlain = Paths.get("src/test/resources/formatPlain.txt").toAbsolutePath().normalize();
        formatJson = Paths.get("src/test/resources/resultJson.txt").toAbsolutePath().normalize();
        pathYml1 = "src/test/resources/fileYml3.yml";
        pathYml2 = "src/test/resources/fileYml4.yml";
        pathJson1 = "src/test/resources/file3.json";
        pathJson2 = "src/test/resources/file4.json";
        wrongPath = "src/file.json";
        expectedStylish = Files.readString(formatStylish);
        expectedPlain = Files.readString(formatPlain);
        expectedJson = Files.readString(formatJson);
    }

    @Test
    void testYmlDefault() throws IOException {
        String actual = Differ.generate(pathYml1, pathYml2);
        assertEquals(actual, expectedStylish);
    }

    @Test
    void testYmlStylish() throws IOException {
        String actual = Differ.generate(pathYml1, pathYml2, "stylish");
        assertEquals(actual, expectedStylish);
    }

    @Test
    void testYmlPlain() throws IOException {
        String actual = Differ.generate(pathYml1, pathYml2, "plain");
        assertEquals(actual, expectedPlain);
    }

    @Test
    void testYmlJson() throws IOException {
        String actual = Differ.generate(pathYml1, pathYml2, "json");
        assertEquals(actual, expectedJson);
    }

    @Test
    void testJsonDefault() throws IOException {
        String actual = Differ.generate(pathJson1, pathJson2);
        assertEquals(actual, expectedStylish);
    }

    @Test
    void testJsonStylish() throws IOException {
        String actual = Differ.generate(pathJson1, pathJson2, "stylish");
        assertEquals(actual, expectedStylish);
    }

    @Test
    void testJsonPlain() throws IOException {
        String actual = Differ.generate(pathJson1, pathJson2, "plain");
        assertEquals(actual, expectedPlain);
    }

    @Test
    void testJson() throws IOException {
        String actual = Differ.generate(pathJson1, pathJson2, "json");
        assertEquals(actual, expectedJson);
    }

    @Test
    void testWrongPathDiffer() {
        assertThrows(IOException.class, () -> {
            Differ.generate(wrongPath, wrongPath, "json");
            throw new IOException("Exception wrong path");
        });
    }

    @Test
    void testUnknownFormat() {
        assertThrows(IOException.class, () -> {
            Differ.generate(pathJson1, pathJson2, "unknown format");
            throw new IOException("Exception unknown format");
        });
    }
}
