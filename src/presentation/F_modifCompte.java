package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import dao.Voiture_dao;
import metier.Client;
import net.proteanit.sql.DbUtils;
import presentation.events.events_client.E_modifClient;
import presentation.exceptions.client.NullAdresseException;
import presentation.exceptions.client.NullNomException;
import presentation.exceptions.client.NullPasswordException;
import presentation.exceptions.client.NullPrenomException;
import presentation.exceptions.client.NullPseudoException;
import presentation.exceptions.client.NullTelException;
import presentation.exceptions.client.ShortPasswordException;
import presentation.exceptions.client.WrongTelException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;


public class F_modifCompte extends JFrame {

	private JPanel contentPane;
	private JTextField contenuPseudo;
	private JTextField contenuTel;
	private JTextField contenuAdresse;
	private JTextField contenuPrenom;
	private JTextField contenuNom;
	private Client clientN = new Client();

	/**
	 * Launch the application.
	**/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_modifCompte frame = new F_modifCompte();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	**/
	public F_modifCompte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 403);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Modification du compte");
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");  
	    setIconImage(icon); 
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Client_dao D_client = new Client_dao();
		ResultSet client = D_client.findC(F_accueil.idConnected);
		try {
			client.next();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		JLabel nom = new JLabel("Nom");
		nom.setBounds(57, 94, 100, 14);
		contentPane.add(nom);
		
		JLabel prenom = new JLabel("Pr\u00E9nom");
		prenom.setBounds(57, 134, 100, 14);
		contentPane.add(prenom);
		
		JLabel adresse = new JLabel("Adresse");
		adresse.setBounds(57, 174, 100, 14);
		contentPane.add(adresse);
		
		JLabel tel = new JLabel("Num t\u00E9l\u00E9phone");
		tel.setBounds(57, 214, 100, 14);
		contentPane.add(tel);
		
		JLabel pseudo = new JLabel("Pseudo");
		pseudo.setBounds(57, 254, 100, 14);
		contentPane.add(pseudo);
		
		try {
			contenuNom = new JTextField(client.getString("nomClient"));
			contenuNom.setColumns(10);
			contenuNom.setBounds(195, 88, 86, 20);
			contentPane.add(contenuNom);
			
			contenuPrenom = new JTextField(client.getString("prenomClient"));
			contenuPrenom.setColumns(10);
			contenuPrenom.setBounds(195, 128, 86, 20);
			contentPane.add(contenuPrenom);
			
			contenuAdresse = new JTextField(client.getString("adresseClient"));
			contenuAdresse.setColumns(10);
			contenuAdresse.setBounds(195, 168, 86, 20);
			contentPane.add(contenuAdresse);
			
			contenuTel = new JTextField(client.getString("telClient"));
			contenuTel.setColumns(10);
			contenuTel.setBounds(195, 208, 86, 20);
			contentPane.add(contenuTel);
			
			contenuPseudo = new JTextField(client.getString("pseudo"));
			contenuPseudo.setColumns(10);
			contenuPseudo.setBounds(195, 248, 86, 20);
			contentPane.add(contenuPseudo);
			
			clientN.setNomClient(contenuNom.getText());
			clientN.setPrenomClient(contenuPrenom.getText());
			clientN.setAdresseClient(contenuAdresse.getText());
			clientN.setTelClient(contenuTel.getText());
			clientN.setPseudo(contenuPseudo.getText());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblNewLabel_5 = new JLabel("Modifier mon compte");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_5.setBounds(41, 11, 293, 38);
		contentPane.add(lblNewLabel_5);
		
		JButton retour = new JButton("Retour");
		retour.setBounds(170, 307, 89, 23);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		contentPane.add(retour);
		
		JButton modifier = new JButton("Modifier");
		E_modifClient e_modifC = new E_modifClient(clientN);
		modifier.addActionListener(e_modifC);
		modifier.setBounds(71, 307, 89, 23);
		contentPane.add(modifier);
	}
}
