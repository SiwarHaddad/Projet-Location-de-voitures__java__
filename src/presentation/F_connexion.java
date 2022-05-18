package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import metier.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;

public class F_connexion extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField pwd;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Connexion");

		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");  
	    setIconImage(icon); 
		
	    ImageIcon p = new ImageIcon("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");
	    getContentPane().add(new JLabel(p));
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenue");
		lblNewLabel.setBounds(142, 34, 142, 24);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(104, 96, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(104, 133, 64, 14);
		contentPane.add(lblNewLabel_2);
		
		login = new JTextField();
		login.setBounds(235, 94, 86, 20);
		contentPane.add(login);
		login.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(235, 131, 86, 20);
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
				Client_dao D_client = new Client_dao();
				ResultSet client = D_client.find(login.getText());
				
				try {
					if(client.next()) {
						if(client.getString("mdpClient").equals(pwd.getText())) {
							F_accueil.connected = true;
							F_accueil.idConnected = client.getInt("idClient");
							
							if(client.getString("pseudo").equals("admin")) {
								F_accueil.admin = true;
								F_gestionVoiture G_voiture = new F_gestionVoiture();
								G_voiture.setVisible(true);
								dispose();
							}
							else {
								F_accueil accueil = new F_accueil();
								accueil.setVisible(true);
								dispose();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, login.getText()+", mot de passe incorrect");
						}
					}
					else
						JOptionPane.showMessageDialog(null,"Pas de client avec ce pseudo");
				} catch (HeadlessException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JButton retour = new JButton("Retour");
		retour.setBounds(294, 201, 89, 23);
		contentPane.add(retour);
		
		JLabel img_pwd = new JLabel("New label");
		img_pwd.setIcon(new ImageIcon("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\pwd.jpg"));
		img_pwd.setBounds(209, 134, 22, 14);
		contentPane.add(img_pwd);
		
		JLabel img_user = new JLabel("New label");
		img_user.setIcon(new ImageIcon("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\user.jpg"));
		img_user.setBounds(209, 96, 22, 18);
		contentPane.add(img_user);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		
	}
}
