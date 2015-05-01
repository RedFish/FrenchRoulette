package exception;

public class MiseMaxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MiseMaxException() {
		this("Mise maximum autorisée dépassée");
	}

	public MiseMaxException(String arg0) {
		super(arg0);
	}
}
