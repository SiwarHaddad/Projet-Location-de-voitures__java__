package presentation.exceptions.location;

public class NoDataFound extends Exception {

	public NoDataFound() {
		super("Pas de location avec ces critères");
	}

}
