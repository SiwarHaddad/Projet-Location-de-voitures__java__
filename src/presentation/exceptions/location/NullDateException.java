package presentation.exceptions.location;

public class NullDateException extends Exception {

	public NullDateException() {
		super("Aucune date n'est spécifiée");
	}

}
