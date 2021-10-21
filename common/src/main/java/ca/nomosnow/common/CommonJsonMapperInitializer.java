package ca.nomosnow.common;

import javax.annotation.PostConstruct;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.eventuate.common.json.mapper.JSonMapper;
public class CommonJsonMapperInitializer {
    @PostConstruct
    public static void registerMoneyModule() {
        JSonMapper.objectMapper.registerModule(new MoneyModule());
        JSonMapper.objectMapper.registerModule(new JavaTimeModule());
        JSonMapper.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }
}
