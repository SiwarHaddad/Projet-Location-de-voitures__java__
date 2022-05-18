package presentation.exceptions.client;

public class WrongTelException extends Exception {

	public WrongTelException() {
		super("Le numéro de téléphone doit etre composé de 8 chiffres");
	}

}
