package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import metier.Client;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class F_inscription extends JFrame {

	private JPanel contentPane;
	private JTextField nom;
	private JTextField prenom;
	private JTextField adresse;
	private JTextField telC;
	private JTextField pseudo;
	private JPasswordField password;

	/**
	 * Launch the application.
	**/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_inscription frame = new F_inscription();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	**/
	public F_inscription() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Inscription");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("nom");
		lblNewLabel.setBounds(38, 101, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("prenom");
		lblNewLabel_1.setBounds(38, 141, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("adresse");
		lblNewLabel_2.setBounds(38, 181, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("num telephone");
		lblNewLabel_3.setBounds(38, 221, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("pseudo");
		lblNewLabel_4.setBounds(38, 261, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		nom = new JTextField();
		nom.setBounds(211, 98, 86, 20);
		contentPane.add(nom);
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setBounds(211, 138, 86, 20);
		contentPane.add(prenom);
		prenom.setColumns(10);
		
		adresse = new JTextField();
		adresse.setBounds(211, 178, 86, 20);
		contentPane.add(adresse);
		adresse.setColumns(10);
		
		telC = new JTextField();
		telC.setBounds(211, 218, 86, 20);
		contentPane.add(telC);
		telC.setColumns(10);
		
		pseudo = new JTextField();
		pseudo.setBounds(211, 258, 86, 20);
		contentPane.add(pseudo);
		pseudo.setColumns(10);
		
		JButton btnNewButton = new JButton("s'incrire");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client client = new Client(nom.getText(),prenom.getText(),adresse.getText(),telC.getText(),pseudo.getText(),password.getText());
				
				Client_dao D_client = new Client_dao();
				D_client.insert(client);
				F_accueil.connected = true;
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
						
			}
		});
		btnNewButton.setBounds(73, 360, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Inscription");
		lblNewLabel_5.setBounds(79, 21, 206, 38);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel_5);
		
		JButton retour = new JButton("retour");
		retour.setBounds(172, 360, 89, 23);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		contentPane.add(retour);
		
		JLabel lblNewLabel_6 = new JLabel("mot de passe");
		lblNewLabel_6.setBounds(38, 301, 96, 14);
		contentPane.add(lblNewLabel_6);
		
		password = new JPasswordField();
		password.setBounds(211, 298, 86, 21);
		contentPane.add(password);
		
		/*
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Client");
		rdbtnNewRadioButton.setBounds(30, 197, 54, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Admin");
		rdbtnNewRadioButton_1.setBounds(85, 197, 109, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		ButtonGroup bg = new ButtonGroup();  
	    bg.add(rdbtnNewRadioButton);
	    bg.add(rdbtnNewRadioButton_1); 
	    */
	}
}
