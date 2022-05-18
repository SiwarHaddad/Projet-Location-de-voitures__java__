package presentation.events.events_voiture;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.Voiture_dao;
import metier.Voiture;
import presentation.F_formVoiture;
import presentation.exceptions.voiture.NegativePriceException;
import presentation.exceptions.voiture.NullDispoException;
import presentation.exceptions.voiture.NullMatriculeException;
import presentation.exceptions.voiture.NullNomException;
import presentation.exceptions.voiture.NullPriceException;

public class E_ajoutVoiture implements ActionListener {
	private F_formVoiture formV;
	
	public E_ajoutVoiture(F_formVoiture formV) {
		this.formV = formV;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {	
			if(formV.getMatricule().getText().equals(""))
				throw new NullMatriculeException();
			
			if(formV.getNom().getText().equals(""))
				throw new NullNomException();
			
			if(formV.getPrixL().getText().equals(""))
				throw new NullPriceException();
			
			if(Double.parseDouble(formV.getPrixL().getText())<0)
				throw new NegativePriceException();
			
			if(!formV.getDispo_oui().isSelected() && !formV.getDispo_non().isSelected())
				throw new NullDispoException();
			
			String dispo = null;
			if(formV.getDispo_oui().isSelected())
				dispo = "oui";
			if(formV.getDispo_non().isSelected())
				dispo = "non";

			Voiture voiture = new Voiture(formV.getMatricule().getText(),formV.getNom().getText(),Double.parseDouble(formV.getPrixL().getText()),dispo);
			Voiture_dao D_voiture = new Voiture_dao();
			
			int r = D_voiture.insert(voiture);
			if(r==1) 
				JOptionPane.showMessageDialog(null,"Ajout avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
						
		}
		catch(NullMatriculeException E_m) {
			JOptionPane.showMessageDialog(null,E_m.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(NullNomException E_n) {
			JOptionPane.showMessageDialog(null,E_n.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPriceException E_p) {
			JOptionPane.showMessageDialog(null,E_p.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(NullDispoException E_d) {
			JOptionPane.showMessageDialog(null,E_d.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(NegativePriceException E_Np) {
			JOptionPane.showMessageDialog(null,E_Np.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		

	}
}
