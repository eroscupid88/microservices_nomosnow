package ca.nomosnow.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.zalando.jackson.datatype.money.MoneyModule;
import javax.annotation.PostConstruct;
import java.util.Locale;

public class CommonJsonMapperInitializer {

    private static ObjectMapper mapper = getDefaultObjectMapper();


    private static ObjectMapper getDefaultObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new MoneyModule().withDefaultFormatting())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    }

    public static JsonNode parse(String string) throws JsonProcessingException {
        return mapper.readTree(string);
    }

    public static <T> T fromJson(JsonNode node, Class<T> clazz) throws JsonProcessingException {
        return  mapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object o) {
        return mapper.valueToTree(o);
    }

    public static String prettyPrintMoney(JsonNode node) throws JsonProcessingException {
        ObjectWriter objectWriter = mapper.writer().with(SerializationFeature.INDENT_OUTPUT).with(Locale.CANADA);
        return objectWriter.writeValueAsString(node);
    }

}
