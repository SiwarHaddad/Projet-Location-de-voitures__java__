package presentation.exceptions.voiture;

public class NoDataFound extends Exception {

	public NoDataFound() {
		super("Pas de voiture avec ces critères");
	}

}
