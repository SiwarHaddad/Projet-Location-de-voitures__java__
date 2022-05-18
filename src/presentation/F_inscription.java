package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import dao.Voiture_dao;
import metier.Client;
import metier.Voiture;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import java.awt.Color;

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
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");  
	    setIconImage(icon); 
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setBounds(38, 101, 96, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Pr\u00E9nom");
		lblNewLabel_1.setBounds(38, 141, 96, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresse");
		lblNewLabel_2.setBounds(38, 181, 74, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Num t\u00E9l\u00E9phone");
		lblNewLabel_3.setBounds(38, 221, 96, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Pseudo");
		lblNewLabel_4.setBounds(38, 261, 96, 14);
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
		telC.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				
				char c = e.getKeyChar();
			
				if((Character.isDigit(c)) && (c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_DELETE)){
					//System.out.println("good");
				}
				else {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		telC.setBounds(211, 218, 86, 20);
		contentPane.add(telC);
		telC.setColumns(10);
		
		pseudo = new JTextField();
		pseudo.setBounds(211, 258, 86, 20);
		contentPane.add(pseudo);
		pseudo.setColumns(10);
		
		JButton btnNewButton = new JButton("S'incrire");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {	
					if(nom.getText().equals(""))
						throw new NullNomException();
					
					if(prenom.getText().equals(""))
						throw new NullPrenomException();
					
					if(adresse.getText().equals(""))
						throw new NullAdresseException();
					
					if(telC.getText().equals(""))
						throw new NullTelException();
					
					if(telC.getText().length() != 8)
						throw new WrongTelException();
					
					if(pseudo.getText().equals(""))
						throw new NullPseudoException();
					
					if(password.getText().equals(""))
						throw new NullPasswordException();
					
					if(password.getText().length()<4)
						throw new ShortPasswordException();
					
					Client client = new Client(nom.getText(),prenom.getText(),pseudo.getText(),password.getText(),telC.getText(),adresse.getText());
					
					Client_dao D_client = new Client_dao();
					D_client.insert(client);
					
					F_accueil.connected = true;
					F_accueil.idConnected = D_client.getIdClient(pseudo.getText());
					
					F_accueil accueil = new F_accueil();
					accueil.setVisible(true);
					dispose();
				}
				catch(NullNomException E_n) {
					JOptionPane.showMessageDialog(null,E_n.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPrenomException E_p) {
					JOptionPane.showMessageDialog(null,E_p.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullAdresseException E_a) {
					JOptionPane.showMessageDialog(null,E_a.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullTelException E_t) {
					JOptionPane.showMessageDialog(null,E_t.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(WrongTelException E_Wt) {
					JOptionPane.showMessageDialog(null,E_Wt.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPseudoException E_ps) {
					JOptionPane.showMessageDialog(null,E_ps.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPasswordException E_pwd) {
					JOptionPane.showMessageDialog(null,E_pwd.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(ShortPasswordException E_Sp) {
					JOptionPane.showMessageDialog(null,E_Sp.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(73, 360, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Inscription");
		lblNewLabel_5.setBounds(79, 21, 206, 38);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		contentPane.add(lblNewLabel_5);
		
		JButton retour = new JButton("Retour");
		retour.setBounds(172, 360, 89, 23);
		retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		contentPane.add(retour);
		
		JLabel lblNewLabel_6 = new JLabel("Mot de passe");
		lblNewLabel_6.setBounds(38, 301, 96, 14);
		contentPane.add(lblNewLabel_6);
		
		password = new JPasswordField();
		password.setBounds(211, 298, 86, 21);
		contentPane.add(password);
	}
}
