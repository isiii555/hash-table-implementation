import org.example.implementations.JsonConverter;
import org.example.implementations.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonConverterTests {
    @Test
    void testBasicObjectConversion() throws Exception {
        JsonConverter converter = new JsonConverter();
        Person person = new Person("Islam","Salamzade");
        String json = converter.convertToJson(person);

        String expected = "{\"name\":\"Islam\",\"surname\":\"Salamzade\"}";
        assertEquals(expected, json);
    }
}
