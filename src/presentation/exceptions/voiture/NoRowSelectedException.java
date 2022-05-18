package presentation.exceptions.voiture;

import javax.swing.JOptionPane;

public class NoRowSelectedException extends Exception {

	public NoRowSelectedException() {
		super("Aucune voiture n'est sélectionnée");
	}
	
}
