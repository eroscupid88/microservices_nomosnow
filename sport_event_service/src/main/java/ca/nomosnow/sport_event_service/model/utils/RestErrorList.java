package ca.nomosnow.sport_event_service.model.utils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static java.util.Arrays.asList;

@Getter
@Setter
public class RestErrorList extends ArrayList<ErrorMessage> {


	/** Generated Serial Version*/
	private static final long serialVersionUID = -721424777198115589L;
	private HttpStatus status;

	/**
	 * Construct RestErrorList take HttpStatus and ErrorMessage as an arguments
	 * @param status HttpStatus type
	 * @param errors ErrorMessage type
	 */
	public RestErrorList(HttpStatus status, ErrorMessage... errors) {
		this(status.value(), errors);
	}

	/**
	 * Construct RestErrorList take Integer and ErrorMessage as an arguments
	 * @param status as Integer
	 * @param errors ErrorMessage type
	 */
	public RestErrorList(int status, ErrorMessage... errors) {
		super();
		this.status = HttpStatus.valueOf(status);
		addAll(asList(errors));
	}


}