package exceptions;

public class WrongSizeRowException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706676764334760784L;
	private final int desiredValue;
	private final int actualValue;
	
	public WrongSizeRowException(int desiredValue, int actualValue){
		super("Incorrect number of cells in row. Should be " + desiredValue + ", is " + actualValue);
		this.desiredValue = desiredValue;
		this.actualValue = actualValue;
	}

	public int getDesiredValue() {
		return desiredValue;
	}

	public int getActualValue() {
		return actualValue;
	}

}
