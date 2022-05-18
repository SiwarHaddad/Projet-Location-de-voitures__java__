package presentation.events.events_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Client_dao;
import dao.Voiture_dao;
import metier.Client;
import metier.Voiture;
import presentation.F_formVoiture;
import presentation.F_gestionVoiture;
import presentation.exceptions.voiture.NoRowSelectedException;

public class E_modifClient implements ActionListener {

	private Client client;

	public E_modifClient(Client client) {
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Client_dao D_client = new Client_dao();
		
		int r = D_client.update(client);
		if(r==1)
			JOptionPane.showMessageDialog(null,"Modification avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(null,"Modifi","Message",JOptionPane.INFORMATION_MESSAGE);
		
	}
}