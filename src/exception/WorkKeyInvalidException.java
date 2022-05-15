package exception;

@SuppressWarnings("serial")
public class WorkKeyInvalidException extends Exception {

	public WorkKeyInvalidException(String key, int number) {
		super("The key "+key+" is invalid because it has " +number+ " caracters more than the text");
	}
}