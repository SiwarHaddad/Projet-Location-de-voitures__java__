package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import dao.SingletonConnection;
import metier.Client;
import net.proteanit.sql.DbUtils;
import presentation.events.events_client.E_suppClient;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class F_gestionClient extends JFrame {
	Connection conn = SingletonConnection.getConnection();

	private JDesktopPane contentPane;
	public static JTable table;

	private JTextField contenuRecherche;

	JDesktopPane desktopPane;

	JLabel titreMenu;
	JMenuItem G_voitures;
	JMenuItem G_clients;
	JMenuItem G_locations;

	Client Client=new Client();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_gestionClient frame = new F_gestionClient();
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
	public F_gestionClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Gestion des clients");

		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");  
	    setIconImage(icon); 
	    
		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 75, 442, 33);
		contentPane.add(menuBar);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(442, 75, 442, 33);
		contentPane.add(menuBar_1);
		menuBar_1.add(Box.createHorizontalGlue());
		
		JMenu espace = new JMenu("");
		espace.setEnabled(false);
		menuBar.add(espace);
		
		titreMenu = new JLabel("Gestion des clients"+" |");
		menuBar.add(titreMenu);
		
		JMenu espace_1 = new JMenu("  ");
		espace_1.setEnabled(false);
		menuBar.add(espace_1);
		
		JMenu fnct = new JMenu("Fonctionnalit\u00E9s");
		menuBar.add(fnct);
		
		G_voitures = new JMenuItem("Gestion des voitures");
		G_voitures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!titreMenu.equals(G_voitures.getText())) {
					F_gestionVoiture F_G_voiture = new F_gestionVoiture();
					F_G_voiture.setVisible(true);
					dispose();
				}
			}
		});
		fnct.add(G_voitures);
		
		G_clients = new JMenuItem("Gestion des clients");
		G_clients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!titreMenu.equals(G_clients.getText())) {
					F_gestionClient F_G_client = new F_gestionClient();
					F_G_client.setVisible(true);
					dispose();
				}
			}
		});
		fnct.add(G_clients);
		
		G_locations = new JMenuItem("Gestion des locations");
		G_locations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!titreMenu.equals(G_locations.getText())) {
					F_gestionLocation F_G_location = new F_gestionLocation();
					F_G_location.setVisible(true);
					dispose();
				}
			}
		});
		fnct.add(G_locations);
		
		JMenu menu_deconnexion = new JMenu("Se déconnecter");
		menu_deconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int yes_no  = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment se déconnecter?", "Quitter", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
				if (yes_no == 0) {
					F_accueil.connected = false;
					F_accueil accueil = new F_accueil();
					accueil.setVisible(true);
					dispose();
				}
			}
		});
		menuBar_1.add(menu_deconnexion);
		
		JMenu espace_2 = new JMenu("");
		espace_2.setEnabled(false);
		menuBar_1.add(espace_2); 
		
		//GrandTitre 
		JLabel titre = new JLabel("AgenceL");
		titre.setFont(new Font("Tahoma", Font.BOLD, 27));
		titre.setBounds(399, 5, 113, 33);
		contentPane.add(titre);
		
		//SousTitre 
		JLabel sous_titre = new JLabel("Gestion des clients");
		sous_titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sous_titre.setBounds(390, 35, 132, 33);
		contentPane.add(sous_titre);
		
		
		//recherche
		JLabel Rechercher = new JLabel("Rechercher");
		Rechercher.setBounds(39, 131, 86, 14);
		contentPane.add(Rechercher);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"idClient", "nomClient", "prenomClient", "telClient"}));
		comboBox.setBounds(39, 152, 121, 22);
		contentPane.add(comboBox);
		
		contenuRecherche = new JTextField();
		contenuRecherche.setBounds(170, 153, 99, 20);
		contentPane.add(contenuRecherche);
		contenuRecherche.setColumns(10);

		contenuRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String selection =(String) comboBox.getSelectedItem();
					String value = contenuRecherche.getText();
										
					Client_dao client = new Client_dao();
					
					table.setModel(DbUtils.resultSetToTableModel(client.search(value, selection)));
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton actualiser = new JButton("Actualiser");
		actualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_dao client = new Client_dao();
				
				table.setModel(DbUtils.resultSetToTableModel(client.All()));
			}
		});
		actualiser.setBounds(618, 152, 107, 23);
		contentPane.add(actualiser);
		
		
		
		//affichage des clients
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 185, 803, 298);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Client_dao client = new Client_dao();
		
		table.setModel(DbUtils.resultSetToTableModel(client.All()));
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				int idC = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				
				Client.setIdClient(idC);
				
			}
		});


		JButton supprimer = new JButton("Supprimer");
		E_suppClient e_suppC = new E_suppClient(Client);
		supprimer.addActionListener(e_suppC);
		supprimer.setBounds(735, 152, 107, 23);
		contentPane.add(supprimer);
	}
		
}

