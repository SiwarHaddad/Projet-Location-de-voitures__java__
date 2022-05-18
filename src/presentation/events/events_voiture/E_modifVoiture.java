package presentation.events.events_voiture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Voiture_dao;
import metier.Voiture;
import presentation.F_formVoiture;
import presentation.F_gestionVoiture;
import presentation.exceptions.voiture.NoRowSelectedException;

public class E_modifVoiture implements ActionListener {
	private F_formVoiture formV;
	
	public E_modifVoiture(F_formVoiture formV) {
		this.formV = formV;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(F_gestionVoiture.table.getSelectedRow() == -1)
				throw new NoRowSelectedException();
			
			else {
				String dispo = null;
				if(formV.getDispo_oui().isSelected())
					dispo = "oui";
				if(formV.getDispo_non().isSelected())
					dispo = "non";
				
				Voiture voiture = new Voiture(Integer.parseInt(formV.getId().getText()),formV.getMatricule().getText(),formV.getNom().getText(),Double.parseDouble(formV.getPrixL().getText()),dispo);
			
				Voiture_dao D_voiture = new Voiture_dao();
				
				int r = D_voiture.update(voiture);
				if(r==1)
					JOptionPane.showMessageDialog(null,"Modification avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null,"Modifi","Message",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(NoRowSelectedException E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.WARNING_MESSAGE);
		}
	}
}
