package hexlet.code;

import org.junit.jupiter.api.Test;

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
    public void testJson1() {
        try {
            assertEquals(expectedString, Differ.generate(path1, path2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String pathY1 = "src/test/resources/fileYml1.yml";
    String pathY2 = "src/test/resources/fileYml2.yml";
    private final String expectedYmlString =
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
    public void testYml1() {
        try {
            assertEquals(expectedYmlString, Differ.generate(pathY1, pathY2));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String path3 = "src/test/resources/file3.json";
    String path4 = "src/test/resources/file4.json";

    private final String expected =
            """
                    {
                        chars1: [a, b, c]
                      - chars2: [d, e, f]
                      + chars2: false
                      - checked: false
                      + checked: true
                      - default: null
                      + default: [value1, value2]
                      - id: 45
                      + id: null
                      - key1: value1
                      + key2: value2
                        numbers1: [1, 2, 3, 4]
                      - numbers2: [2, 3, 4, 5]
                      + numbers2: [22, 33, 44, 55]
                      - numbers3: [3, 4, 5]
                      + numbers4: [4, 5, 6]
                      + obj1: {nestedKey=value, isNested=true}
                      - setting1: Some value
                      + setting1: Another value
                      - setting2: 200
                      + setting2: 300
                      - setting3: true
                      + setting3: none
                    }""";

    @Test
    public void testJson2() {
        try {
            assertEquals(expected, Differ.generate(path3, path4));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    String pathY3 = "src/test/resources/fileYml3.yml";
    String pathY4 = "src/test/resources/fileYml4.yml";
    private final String expectedYml2 =
            """
                    {
                        chars1: [a, b, c]
                      - chars2: [d, e, f]
                      + chars2: false
                      - checked: false
                      + checked: true
                      - default: null
                      + default: [value1, value2]
                      - id: 45
                      + id: null
                      - key1: value1
                      + key2: value2
                        numbers1: [1, 2, 3, 4]
                      - numbers2: [2, 3, 4, 5]
                      + numbers2: [22, 33, 44, 55]
                      - numbers3: [3, 4, 5]
                      + numbers4: [4, 5, 6]
                      + obj1: {nestedKey=value, isNested=true}
                      - setting1: Some value
                      + setting1: Another value
                      - setting2: 200
                      + setting2: 300
                      - setting3: true
                      + setting3: none
                    }""";

    @Test
    public void testYml2() {
        try {
            assertEquals(expectedYml2, Differ.generate(pathY3, pathY4));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private final String expectedYml2Plain =
            """
                    
                     Property 'chars2' was updated. From [complex value] to false
                     Property 'checked' was updated. From false to true
                     Property 'default' was updated. From null to [complex value]
                     Property 'id' was updated. From 45 to null
                     Property 'key1' was removed
                     Property 'key2' was added with value: 'value2'
                     Property 'numbers2' was updated. From [complex value] to [complex value]
                     Property 'numbers3' was removed
                     Property 'numbers4' was added with value: [complex value]
                     Property 'obj1' was added with value: [complex value]
                     Property 'setting1' was updated. From 'Some value' to 'Another value'
                     Property 'setting2' was updated. From 200 to 300
                     Property 'setting3' was updated. From true to 'none'
                     """;

    @Test
    public void testYml2Plain() {
        try {
            assertEquals(expectedYml2Plain, Differ.generate(pathY3, pathY4, "plain"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
