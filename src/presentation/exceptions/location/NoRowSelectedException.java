package presentation.exceptions.location;

import javax.swing.JOptionPane;

public class NoRowSelectedException extends Exception {

	public NoRowSelectedException() {
		super("Aucun location n'est sélectionnée");
	}
	
}
