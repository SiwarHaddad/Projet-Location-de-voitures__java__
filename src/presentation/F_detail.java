package presentation;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.Location_dao;
import metier.Location;
import metier.Voiture;
import presentation.exceptions.location.NotAvailableException;
import presentation.exceptions.location.NotConnectedException;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.awt.event.ActionEvent;

public class F_detail extends JInternalFrame {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_detail frame = new F_detail(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JLabel contenuNom;
	private JLabel contenuDescription;
	private JLabel contenuPrix;
	private Component contenuMatricule;

	/**
	 * Create the frame.
	 * @param v 
	 */
	public F_detail(Voiture v) {
		//((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		
		setBounds(518, 149, 345, 338);
		getContentPane().setLayout(null);
		
		JLabel titre = new JLabel("D\u00E9tails");
		titre.setFont(new Font("Tahoma", Font.BOLD, 24));
		titre.setBounds(120, 23, 110, 25);
		getContentPane().add(titre);
		
		JLabel matricule = new JLabel("Matricule");
		matricule.setBounds(68, 74, 81, 14);
		getContentPane().add(matricule);
		
		contenuMatricule = new JLabel(v.getMatricule());
		contenuMatricule.setBounds(211, 74, 73, 14);
		getContentPane().add(contenuMatricule);
		
		JLabel nom = new JLabel("Nom");
		nom.setBounds(68, 110, 95, 14);
		getContentPane().add(nom);
		
		contenuNom = new JLabel(v.getNom());
		contenuNom.setBounds(211, 110, 73, 14);
		getContentPane().add(contenuNom);
		
		JLabel prix = new JLabel("Prix");
		prix.setBounds(68, 147, 95, 14);
		getContentPane().add(prix);
		
		contenuPrix = new JLabel(v.getPrix_locat().toString());
		contenuPrix.setBounds(211, 147, 73, 14);
		getContentPane().add(contenuPrix);
		
		JLabel disponible = new JLabel("Disponible");
		disponible.setBounds(68, 183, 110, 14);
		getContentPane().add(disponible);
		
		contenuDescription = new JLabel(v.getDiponible());
		contenuDescription.setBounds(211, 183, 73, 14);
		getContentPane().add(contenuDescription);
		
		JButton louer = new JButton("Louer");
		louer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(!F_accueil.connected)
						throw new NotConnectedException();
					else
						if(v.getDiponible().equals("non"))
							throw new NotAvailableException();
						else
							Louer(v);
				} 
				catch(NotConnectedException NC) {
					JOptionPane.showConfirmDialog(null, NC.getMessage(), "alerte", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				} 
				catch (NotAvailableException NA) {
					JOptionPane.showConfirmDialog(null, NA.getMessage(), "alerte", JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE);
				}
					
			}
		});
		louer.setBounds(120, 254, 89, 23);
		getContentPane().add(louer);
		
		JLabel sinondispo = new JLabel("Disponible dans: ");
		sinondispo.setBounds(68, 218, 110, 14);
		getContentPane().add(sinondispo);
		
		
		JLabel dispoDans = new JLabel("");
		dispoDans.setBounds(211, 218, 95, 14);
		getContentPane().add(dispoDans);
		
		if(v.getDiponible().equals("oui")) 
			dispoDans.setText("--");
		else {
			Location_dao D_location = new Location_dao();
			String dateL = null;
			int prev = 0;
			
			try {
				if(!(D_location.search(String.valueOf(v.getIdVoiture()), "location.idVoiture").next()))
					dispoDans.setText("--");
				else {
					ResultSet loo = D_location.find(v.getIdVoiture());
					loo.next();
					
					dateL = loo.getString("date_locat");
					prev = loo.getInt("duree_prev");
					
					LocalDate endL = LocalDate.parse(dateL).plusDays(prev);
					LocalDate now = java.time.LocalDate.now();
					
					int period = Period.between(now, endL).getDays();
					
					dispoDans.setText(String.valueOf(period)+" jour(s)");
				}
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void Louer(Voiture v) {
		
		F_location l = new F_location(v);
		l.setVisible(true);
		this.getDesktopPane().add(l);
		//this.dispose();
		l.toFront();
	}

	public JLabel getNom() {
		return contenuNom;
	}

	public void setNom(JLabel contenuNom) {
		this.contenuNom = contenuNom;
	}

	public JLabel getDescription() {
		return contenuDescription;
	}

	public void setDescription(JLabel contenuDescription) {
		this.contenuDescription = contenuDescription;
	}

	public JLabel getPrix() {
		return contenuPrix;
	}

	public void setPrix(JLabel contenuPrix) {
		this.contenuPrix = contenuPrix;
	}

	public Component getMatricule() {
		return contenuMatricule;
	}

	public void setMatricule(Component contenuMatricule) {
		this.contenuMatricule = contenuMatricule;
	}
}