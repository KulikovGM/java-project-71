package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    public AppTest() throws IOException { //AppTest is empty
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
    public void testYmlDefault() {
        try {
            assertEquals(Files.readString(formatStylish), Differ.generate(pathYml1, pathYml2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testYmlStylish() {
        try {
            assertEquals(Files.readString(formatStylish), Differ.generate(pathYml1, pathYml2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testYmlPlain() {
        try {
            assertEquals(Files.readString(formatPlain), Differ.generate(pathYml1, pathYml2, "plain"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final String expectedYml2Json = Files.readString(formatJson);

    @Test
    public void testYmlJson() {
        try {
            assertEquals(expectedYml2Json, Differ.generate(pathYml1, pathYml2, "json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJsonDefault() {
        try {
            assertEquals(Files.readString(formatStylish), Differ.generate(pathJson1, pathJson2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJsonStylish() {
        try {
            assertEquals(Files.readString(formatStylish), Differ.generate(pathJson1, pathJson2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJsonPlain() {
        try {
            assertEquals(Files.readString(formatPlain), Differ.generate(pathJson1, pathJson2, "plain"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testJson() {
        try {
            assertEquals(Files.readString(formatJson), Differ.generate(pathJson1, pathJson2, "json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
