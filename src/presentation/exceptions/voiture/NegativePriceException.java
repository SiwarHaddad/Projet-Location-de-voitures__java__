package presentation.exceptions.voiture;

public class NegativePriceException extends Exception {

	public NegativePriceException() {
		super("Le prix de voiture est négatif");
	}

}
