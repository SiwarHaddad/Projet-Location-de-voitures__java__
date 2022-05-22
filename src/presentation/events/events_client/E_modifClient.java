package presentation.events.events_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Client_dao;
import dao.Voiture_dao;
import metier.Client;
import metier.Voiture;
import presentation.F_accueil;
import presentation.F_formVoiture;
import presentation.F_gestionVoiture;
import presentation.F_modifCompte;
import presentation.exceptions.voiture.NoRowSelectedException;

public class E_modifClient implements ActionListener {

	private Client client = new Client();

	public E_modifClient() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//System.out.println(client.getAdresseClient());
		F_modifCompte Modif = new F_modifCompte();
		
		client.setIdClient(F_accueil.idConnected);
		client.setNomClient(Modif.getContenuNom().getText());
		client.setPrenomClient(Modif.getContenuPrenom().getText());
		client.setAdresseClient(Modif.getContenuAdresse().getText());
		client.setTelClient(Modif.getContenuTel().getText());
		client.setPseudo(Modif.getContenuPseudo().getText());
		
		Client_dao D_client = new Client_dao();
		
		int r = D_client.update(client);
		
		if(r==1)
			JOptionPane.showMessageDialog(null,"Modification avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
