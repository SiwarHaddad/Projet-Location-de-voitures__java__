package presentation.exceptions.client;

import javax.swing.JOptionPane;

public class NoRowSelectedException extends Exception {

	public NoRowSelectedException() {
		super("Aucun client n'est sélectionné");
	}
	
}
