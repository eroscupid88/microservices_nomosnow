package ca.nomosnow.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import ca.nomosnow.common.Address;
import static org.junit.jupiter.api.Assertions.*;

class CommonJsonMapperInitializerTest {

    String testString = "{\n" +
            "  \"amount\": 29.95,\n" +
            "  \"currency\": \"EUR\",\n" +
            "  \"formatted\": \"29,95 EUR\"\n" +
            "}";
    String testAddress ="{\n" +
            "  \"street1\": \"1940 EastHill\",\n" +
            "  \"street2\": null,\n" +
            "  \"city\": \"Saskatoon\",\n" +
            "  \"province\": \"Saskatchewan\",\n" +
            "  \"country\": \"Canada\"\n" +
            "}";

    @Test
    void parse() throws JsonProcessingException {
        JsonNode node = CommonJsonMapperInitializer.parse(testString);
        assertEquals(node.get("amount").asText(),"29.95");
        assertEquals(node.get("currency").asText(),"EUR");
        assertEquals(node.get("formatted").asText(),"29,95 EUR");
    }
    @Test
    void fromJson() throws JsonProcessingException {
        JsonNode node = CommonJsonMapperInitializer.parse(testAddress);
        Address address = CommonJsonMapperInitializer.fromJson(node,Address.class);
        assertEquals(address.getStreet1(),"1940 EastHill");
        assertNull(address.getStreet2());
        assertEquals(address.getCity(),"Saskatoon");
        assertEquals(address.getProvince(),"Saskatchewan");
        assertEquals(address.getCountry(),"Canada");

    }

    @Test
    void toJson() {
    }

    @Test
    void prettyPrintMoney() {
    }


}