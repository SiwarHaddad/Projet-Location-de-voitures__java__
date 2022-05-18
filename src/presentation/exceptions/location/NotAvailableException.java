package presentation.exceptions.location;

public class NotAvailableException extends Exception {

	public NotAvailableException() {
		super("Voiture non disponible pour le moment");
	}

}
