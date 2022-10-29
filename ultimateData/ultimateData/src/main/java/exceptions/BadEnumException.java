package exceptions;

public class BadEnumException extends Exception {

	public BadEnumException(String value, String enumName) {
		super("no value " + value + " in enum "+ enumName);
	}

}
