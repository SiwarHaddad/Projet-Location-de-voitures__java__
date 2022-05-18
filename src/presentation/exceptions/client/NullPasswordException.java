package presentation.exceptions.client;

public class NullPasswordException extends Exception {

	public NullPasswordException() {
		super("Mot de passe nul");
	}

}
