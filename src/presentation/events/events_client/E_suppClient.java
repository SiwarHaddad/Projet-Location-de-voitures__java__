package presentation.events.events_client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Client_dao;
import dao.Voiture_dao;
import metier.Client;
import metier.Voiture;
import presentation.F_formVoiture;
import presentation.F_gestionClient;
import presentation.F_gestionVoiture;
import presentation.exceptions.client.NoRowSelectedException;

public class E_suppClient implements ActionListener{
	private Client f;

	public E_suppClient(Client f) {
		this.f = f;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(F_gestionClient.table.getSelectedRow() == -1)
				throw new NoRowSelectedException();
			
			else {
				System.out.println(f.getIdClient());
				//Client client = new Client(f);
				Client_dao D_client = new Client_dao();
				
				int r = D_client.delete(f);
				
				if(r==1) 
					JOptionPane.showMessageDialog(null,"Suppression avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		catch(NoRowSelectedException E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.WARNING_MESSAGE);
		}
	}	
		
}
