package presentation.exceptions.voiture;

public class NullPriceException extends Exception {
	public NullPriceException() {
		super("Prix vide");
	}
}
