package presentation.events.events_location;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import dao.Location_dao;
import dao.Voiture_dao;
import metier.Location;
import metier.Voiture;
import presentation.F_accueil;
import presentation.F_formVoiture;
import presentation.F_location;
import presentation.exceptions.location.NullDateException;
import presentation.exceptions.location.NullDureeException;
import presentation.exceptions.location.WrongDateException;
import presentation.exceptions.voiture.NegativePriceException;
import presentation.exceptions.voiture.NullDispoException;
import presentation.exceptions.voiture.NullMatriculeException;
import presentation.exceptions.voiture.NullNomException;
import presentation.exceptions.voiture.NullPriceException;

public class E_ajoutLocation implements ActionListener {
	JTextField matricule;
	JDateChooser date_locat;
	JSpinner duree_prev;
	JTextField total;

	public E_ajoutLocation(JTextField matricule, JDateChooser date_locat, JSpinner duree_prev, JTextField total) {
		super();
		this.matricule = matricule;
		this.date_locat = date_locat;
		this.duree_prev = duree_prev;
		this.total = total;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {	
			if(this.date_locat.getDate() == null)
				throw new NullDateException();
			
			SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
			String date = Date_Format.format(this.date_locat.getDate());
			String dateC = java.time.LocalDate.now().toString();
			
			if(dateC.compareTo(date)>=0)
				throw new WrongDateException();
			
			if(Integer.parseInt(this.duree_prev.getValue().toString())<1)
				throw new NullDureeException();
			
			Voiture_dao D_voiture = new Voiture_dao();
			
			D_voiture.updateD(D_voiture.getIdVoiture(this.matricule.getText()), "non");
			
			Location location = new Location(D_voiture.getIdVoiture(this.matricule.getText()),F_accueil.idConnected,date,Integer.parseInt(this.duree_prev.getValue().toString()),Double.parseDouble(this.total.getText()));
			
			Location_dao D_location = new Location_dao();
			int r = D_location.insert(location);
			
			if(r==1) 
				JOptionPane.showMessageDialog(null,"Location effectuée avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
			
		}
		catch(NullDateException E_d) {
			JOptionPane.showMessageDialog(null,E_d.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(WrongDateException E_wd) {
			JOptionPane.showMessageDialog(null,E_wd.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
		catch(NullDureeException E_dr) {
			JOptionPane.showMessageDialog(null,E_dr.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		}
	}
}
