package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Parser {

    public static Map<String, Object> parsing(String data, String format) throws IOException {
        ObjectMapper mapper;
        if (format.equals("json")) {
            mapper = new ObjectMapper();
        } else if (format.equals("yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IOException("Unknown format");
        }

        Map<String, Object> mappedData = new HashMap<>();
        mappedData = mapper.readValue(new File(data), new TypeReference<>() {
        });
        return mappedData;
    }

}
