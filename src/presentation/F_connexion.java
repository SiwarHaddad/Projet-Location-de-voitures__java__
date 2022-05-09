package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class F_connexion extends JInternalFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField pwd;
	//private JFrame autrePage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_connexion frame = new F_connexion();
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
	public F_connexion() {
		//this.autrePage = autrePage;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue");
		lblNewLabel.setBounds(142, 34, 142, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(87, 91, 46, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(87, 133, 64, 14);
		contentPane.add(lblNewLabel_2);
		
		login = new JTextField();
		login.setBounds(214, 94, 86, 20);
		contentPane.add(login);
		login.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(214, 131, 86, 20);
		contentPane.add(pwd);
		
		JButton Sinscrire = new JButton("S'inscrire");
		Sinscrire.setBounds(44, 201, 89, 23);
		contentPane.add(Sinscrire);
		Sinscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_inscription inscri = new F_inscription();
				inscri.setVisible(true);
				dispose();
			}
		});
		
		JButton Valider = new JButton("Valider");
		Valider.setBounds(170, 201, 89, 23);
		contentPane.add(Valider);
		Valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(login.getText().equals("siwar") && pwd.getText().equals("1234")) { 
					F_voiture voiture = new F_voiture();
					voiture.setVisible(true);
					dispose();
				}
				else
					if(login.getText().equals("siwar") && !pwd.getText().equals("1234"))
						JOptionPane.showMessageDialog(null, login.getText()+", mot de passe incorrect");
					else
						JOptionPane.showMessageDialog(null,"login et mot de passe incorrects");
			}
		});
		
		JButton retour = new JButton("retour");
		retour.setBounds(294, 201, 89, 23);
		contentPane.add(retour);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		
	}
}
