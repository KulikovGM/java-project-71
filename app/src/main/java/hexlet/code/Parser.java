package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public final class Parser {
    private Parser() {
        throw new IllegalStateException("Utility class");
    }
    public static Map<String, Object> parsing(String data, String format) throws IOException {
        ObjectMapper mapper;
        if (format.equals("json")) {
            mapper = new ObjectMapper();
        } else if (format.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IOException("Unknown format '" + format + "'");
        }

        Map<String, Object> mappedData;
        mappedData = mapper.readValue(new File(data), new TypeReference<>() {
        });
        return mappedData;
    }

}
