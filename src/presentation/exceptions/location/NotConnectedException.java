package presentation.exceptions.location;

public class NotConnectedException extends Exception {

	public NotConnectedException() {
		super("Vous n'êtes pas connecté");
	}
}
