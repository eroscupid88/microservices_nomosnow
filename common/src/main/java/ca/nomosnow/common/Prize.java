package ca.nomosnow.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import lombok.Setter;
import org.javamoney.moneta.Money;
import org.zalando.jackson.datatype.money.MoneyModule;

import javax.money.MonetaryAmount;
import java.util.Locale;

@Getter
@Setter
public class Prize {
    private MonetaryAmount price;


}
