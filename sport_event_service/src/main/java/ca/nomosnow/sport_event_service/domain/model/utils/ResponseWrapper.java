package ca.nomosnow.sport_event_service.domain.model.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;


@Getter
@Setter
@ToString
@JsonInclude(NON_NULL)
public class ResponseWrapper {

    private Object data;
    private Object metadata;
    private List<ErrorMessage> errors;

	public ResponseWrapper(Object data, Object metadata, List<ErrorMessage> errors) {
		super();
		this.data = data;
		this.metadata = metadata;
		this.errors = errors;
	}

   
}
