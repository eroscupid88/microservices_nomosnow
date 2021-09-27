package ca.nomosnow.sportorganizationservice.model.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessage {

	private String message;
	private String code;
    private String detail;


	/**
	 * Construct ErrorMessage with 3 arguments
	 * @param message String message
	 * @param code	String code
	 * @param detail String detail
	 */
	public ErrorMessage(String message, String code, String detail) {
		super();
		this.message = message;
		this.code = code;
		this.detail = detail;
	}
	/**
	 * Construct ErrorMessage with 2 arguments
	 * @param message String message
	 * @param detail String detail
	 */
	public ErrorMessage(String message, String detail) {
		super();
		this.message = message;
		this.detail = detail;
	}
	/**
	 * Construct ErrorMessage with 1 arguments
	 * @param message String message
	 */
    public ErrorMessage(String message) {
        this(message, "", "");
    }

}
