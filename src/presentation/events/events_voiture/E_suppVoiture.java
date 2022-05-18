package presentation.events.events_voiture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Voiture_dao;
import metier.Voiture;
import presentation.F_formVoiture;
import presentation.F_gestionVoiture;
import presentation.exceptions.voiture.NoRowSelectedException;

public class E_suppVoiture implements ActionListener {
	//F_gestionVoiture f = new F_gestionVoiture();
	private F_formVoiture formV;

	public E_suppVoiture(F_formVoiture formV) {
		this.formV = formV;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(F_gestionVoiture.table.getSelectedRow() == -1)
				throw new NoRowSelectedException();
			
			else {
				Voiture voiture = new Voiture(Integer.parseInt(formV.getId().getText()));
				Voiture_dao D_voiture = new Voiture_dao();
				
				int r = D_voiture.delete(voiture);
				
				if(r==1) 
					JOptionPane.showMessageDialog(null,"Suppression avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
			}
				
		}
		catch(NoRowSelectedException E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}

}
