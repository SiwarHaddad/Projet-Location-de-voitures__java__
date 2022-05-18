package presentation;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;

public class F_formVoiture extends JInternalFrame {
	private JTextField contenuMatricule;
	private JTextField contenuNom;
	private JTextField prixL;
	private JRadioButton Dispo_oui;
	private JRadioButton Dispo_non;
	private JTextField id;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_formVoiture frame = new F_formVoiture();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
static int point=0;
	/**
	 * Create the frame.
	 */
	public F_formVoiture() {
		setBounds(512, 44, 362, 456);
		getContentPane().setLayout(null);
		
		JLabel Matricule = new JLabel("Matricule");
		Matricule.setBounds(48, 61, 100, 14);
		getContentPane().add(Matricule);
		
		JLabel Nom = new JLabel("Nom");
		Nom.setBounds(48, 111, 100, 14);
		getContentPane().add(Nom);
		
		JLabel Prix = new JLabel("Prix de location");
		Prix.setBounds(48, 161, 137, 14);
		getContentPane().add(Prix);
		
		JLabel Dispo = new JLabel("Disponibilit\u00E9");
		Dispo.setBounds(51, 227, 100, 14);
		getContentPane().add(Dispo);
		
		contenuMatricule = new JTextField();
		contenuMatricule.setColumns(10);
		contenuMatricule.setBounds(183, 58, 86, 20);
		getContentPane().add(contenuMatricule);
		
		contenuNom = new JTextField();
		contenuNom.setColumns(10);
		contenuNom.setBounds(183, 108, 86, 20);
		getContentPane().add(contenuNom);
		
		prixL = new JTextField();
		prixL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
				if (c==".".charAt(0)) {
					//System.out.println(c==".".charAt(0));
					point++;
				}
				//System.out.println(point);
				if(((Character.isDigit(c)))||(c==".".charAt(0) && point<=1) && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)){
					//System.out.println("good");
				}
				else {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		prixL.setColumns(10);
		prixL.setBounds(183, 158, 86, 20);
		getContentPane().add(prixL);

		id = new JTextField();
		id.setBounds(250, 11, 86, 20);
		getContentPane().add(id);
		id.setColumns(10);
		id.setVisible(false);

		Dispo_oui = new JRadioButton("oui");
		Dispo_oui.setBounds(167, 223, 46, 23);
		getContentPane().add(Dispo_oui);
		
		Dispo_non = new JRadioButton("non");
		Dispo_non.setBounds(220, 223, 109, 23);
		getContentPane().add(Dispo_non);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(Dispo_oui);
		grp.add(Dispo_non);
		
	}

	public JTextField getId() {
		return id;
	}

	public JTextField getMatricule() {
		return contenuMatricule;
	}

	public JTextField getNom() {
		return contenuNom;
	}

	public JTextField getPrixL() {
		return prixL;
	}
	
	public JRadioButton getDispo_oui() {
		return Dispo_oui;
	}

	public JRadioButton getDispo_non() {
		return Dispo_non;
	}

}
