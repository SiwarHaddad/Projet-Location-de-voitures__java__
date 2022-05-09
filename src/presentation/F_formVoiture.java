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
	private JTextField matricule;
	private JTextField nom;
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
		
		JLabel lblNewLabel = new JLabel("matricule");
		lblNewLabel.setBounds(40, 79, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("nom");
		lblNewLabel_1.setBounds(40, 129, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("prix de location");
		lblNewLabel_2.setBounds(40, 173, 137, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("disponibilit\u00E9");
		lblNewLabel_3.setBounds(40, 227, 62, 14);
		getContentPane().add(lblNewLabel_3);
		
		matricule = new JTextField();
		matricule.setBounds(233, 76, 86, 20);
		getContentPane().add(matricule);
		matricule.setColumns(10);
		
		nom = new JTextField();
		nom.setBounds(233, 126, 86, 20);
		getContentPane().add(nom);
		nom.setColumns(10);
		
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
		prixL.setBounds(233, 170, 86, 20);
		getContentPane().add(prixL);
		prixL.setColumns(10);
		
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
		return matricule;
	}

	public JTextField getNom() {
		return nom;
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
