package presentation;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import metier.Voiture;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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
		titre.setFont(new Font("Tahoma", Font.BOLD, 20));
		titre.setBounds(132, 27, 80, 25);
		getContentPane().add(titre);
		
		JLabel matricule = new JLabel("matricule");
		matricule.setBounds(68, 74, 46, 14);
		getContentPane().add(matricule);
		
		contenuMatricule = new JLabel(v.getMatricule());
		contenuMatricule.setBounds(211, 74, 46, 14);
		getContentPane().add(contenuMatricule);
		
		JLabel nom = new JLabel("nom");
		nom.setBounds(68, 110, 46, 14);
		getContentPane().add(nom);
		
		contenuNom = new JLabel(v.getNom());
		contenuNom.setBounds(211, 110, 46, 14);
		getContentPane().add(contenuNom);
		
		JLabel prix = new JLabel("prix");
		prix.setBounds(68, 147, 46, 14);
		getContentPane().add(prix);
		
		contenuPrix = new JLabel(v.getPrix_locat().toString());
		contenuPrix.setBounds(211, 147, 46, 14);
		getContentPane().add(contenuPrix);
		
		JLabel disponible = new JLabel("disponible");
		disponible.setBounds(68, 183, 61, 14);
		getContentPane().add(disponible);
		
		contenuDescription = new JLabel(v.getDiponible());
		contenuDescription.setBounds(211, 183, 46, 14);
		getContentPane().add(contenuDescription);
		
		JButton louer = new JButton("Louer");
		louer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Louer(v);
			}
		});
		louer.setBounds(120, 254, 89, 23);
		getContentPane().add(louer);
		
		JLabel sinondispo = new JLabel("Disponible dans: ");
		sinondispo.setBounds(68, 224, 110, 14);
		getContentPane().add(sinondispo);
		
		

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