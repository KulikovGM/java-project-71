package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    String path1 = "src/test/resources/file1.json";
    String path2 = "src/test/resources/file2.json";

    private final String expectedString =
            """
                    {
                      - follow: false
                        host: hexlet.io
                      - proxy: 123.234.53.22
                      - timeout: 50
                      + timeout: 20
                      + verbose: true
                    }""";

    @Test
    public void testGet() {
        try {
            assertEquals(expectedString, ReaderAndDiffer.readAndDiff(path1, path2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
