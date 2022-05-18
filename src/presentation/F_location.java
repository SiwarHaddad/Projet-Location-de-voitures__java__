package presentation;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import metier.Voiture;
import presentation.events.events_location.E_ajoutLocation;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class F_location extends JInternalFrame {
	private JTextField contenuMatricule;
	private JDateChooser date_locat;
	private JSpinner duree_prev;
	private JTextField total;
	private JTextField contenuTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_location frame = new F_location(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public F_location(Voiture v) {
		//((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
		
		setBounds(518, 149, 345, 338);
		getContentPane().setLayout(null);
		
		JLabel titre = new JLabel("Location");
		titre.setFont(new Font("Tahoma", Font.BOLD, 24));
		titre.setBounds(112, 20, 104, 28);
		getContentPane().add(titre);
		
		JLabel matr = new JLabel("Matricule");
		matr.setBounds(49, 79, 101, 14);
		getContentPane().add(matr);
		
		contenuMatricule = new JTextField(v.getMatricule());
		contenuMatricule.setColumns(10);
		contenuMatricule.setBounds(191, 76, 86, 20);
		getContentPane().add(contenuMatricule);
		
		JLabel dateDeLocation = new JLabel("Date de location");
		dateDeLocation.setBounds(49, 119, 101, 14);
		getContentPane().add(dateDeLocation);
		
		date_locat = new JDateChooser();
		date_locat.setBounds(191, 116, 86, 20);
		getContentPane().add(date_locat);
		
		JLabel duree = new JLabel("Dur\u00E9e (en jours)");
		duree.setBounds(49, 162, 101, 14);
		getContentPane().add(duree);
		
		duree_prev = new JSpinner();
		duree_prev.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Double totalP = v.getPrix_locat()*Double.parseDouble(duree_prev.getValue().toString());
				contenuTotal.setText(totalP.toString());
			}
		});
		duree_prev.setBounds(191, 156, 86, 20);
		getContentPane().add(duree_prev);
		
		JLabel total = new JLabel("Total");
		total.setBounds(49, 202, 101, 14);
		getContentPane().add(total);
		
		contenuTotal = new JTextField("0.0");
		contenuTotal.setColumns(10);
		contenuTotal.setBounds(191, 196, 86, 20);
		getContentPane().add(contenuTotal);
		
		
		JButton louer = new JButton("Louer");
		E_ajoutLocation e_ajoutL = new E_ajoutLocation(this.getMatricule(), this.getDate_locat(), this.getDuree_prev(), this.getTotal());
		louer.addActionListener(e_ajoutL);
		louer.setBounds(60, 260, 89, 23);
		getContentPane().add(louer);
		
		JButton retour = new JButton("Retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		retour.setBounds(176, 260, 89, 23);
		getContentPane().add(retour);
		
	}

	public JTextField getMatricule() {
		return contenuMatricule;
	}

	public void setMatricule(JTextField contenuMatricule) {
		this.contenuMatricule = contenuMatricule;
	}

	public JDateChooser getDate_locat() {
		return date_locat;
	}

	public void setDate_locat(JDateChooser date_locat) {
		this.date_locat = date_locat;
	}

	public JSpinner getDuree_prev() {
		return duree_prev;
	}

	public void setDuree_prev(JSpinner duree_prev) {
		this.duree_prev = duree_prev;
	}

	public JTextField getTotal() {
		return contenuTotal;
	}

	public void setTotal(JTextField contenuTotal) {
		this.contenuTotal = contenuTotal;
	}
}
