package exceptions;

public class ValueException extends Exception {
	public ValueException(String valueType, String value, String newValue) {
		super("Mismatches " + valueType + " value. Currently " + value + ", trying to set to " + newValue);
	}


}
