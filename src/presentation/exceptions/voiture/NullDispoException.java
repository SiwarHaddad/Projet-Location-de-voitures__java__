package presentation.exceptions.voiture;

public class NullDispoException extends Exception {
	public NullDispoException() {
		super("Disponiblité vide");
	}
}
