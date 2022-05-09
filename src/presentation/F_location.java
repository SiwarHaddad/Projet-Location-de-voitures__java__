package presentation;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import metier.Voiture;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class F_location extends JInternalFrame {
	private JTextField contenuMatricule;
	private JTextField contenuTotal;
	Double total=0.0;

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
		
		JLabel titre = new JLabel("location");
		titre.setFont(new Font("Tahoma", Font.BOLD, 23));
		titre.setBounds(113, 11, 104, 28);
		getContentPane().add(titre);
		
		JLabel matr = new JLabel("Matricule");
		matr.setBounds(48, 88, 46, 14);
		getContentPane().add(matr);
		
		contenuMatricule = new JTextField(v.getMatricule());
		contenuMatricule.setBounds(190, 88, 86, 20);
		getContentPane().add(contenuMatricule);
		contenuMatricule.setColumns(10);
		
		JLabel date = new JLabel("date");
		date.setBounds(48, 137, 46, 14);
		getContentPane().add(date);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(190, 140, 86, 14);
		getContentPane().add(dateChooser);
		
		String d1  = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
		
		JLabel duree = new JLabel("duree");
		duree.setBounds(48, 176, 46, 14);
		getContentPane().add(duree);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				Double totalP = v.getPrix_locat()*Double.parseDouble(spinner.getValue().toString());
				contenuTotal.setText(totalP.toString());
			}
		});
		spinner.setBounds(190, 176, 86, 20);
		getContentPane().add(spinner);
		
		JLabel total = new JLabel("total");
		total.setBounds(48, 216, 46, 14);
		getContentPane().add(total);
		
		contenuTotal = new JTextField();
		contenuTotal.setBounds(190, 216, 86, 20);
		getContentPane().add(contenuTotal);
		contenuTotal.setColumns(10);
		
		JButton retour = new JButton("Retour");
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		retour.setBounds(175, 271, 89, 23);
		getContentPane().add(retour);
		
		JButton louer = new JButton("Louer");
		louer.setBounds(60, 271, 89, 23);
		getContentPane().add(louer);
		
		

	}
}
