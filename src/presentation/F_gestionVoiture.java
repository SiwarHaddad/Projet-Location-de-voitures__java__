package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import dao.SingletonConnection;
import dao.Voiture_dao;
import net.proteanit.sql.DbUtils;
import presentation.events.events_voiture.E_ajoutVoiture;
import presentation.events.events_voiture.E_modifVoiture;
import presentation.events.events_voiture.E_suppVoiture;

import javax.swing.JLabel;
import java.awt.Font;

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

public class F_gestionVoiture extends JFrame {
	Connection conn = SingletonConnection.getConnection();
	
	F_detail detailV = null;
	private JDesktopPane contentPane;
	public static JTable table;

	protected static int point;
	private JTextField contenuRecherche;
	protected boolean detailFound = false;
	protected boolean locationFound = false;

	JDesktopPane desktopPane;

	JLabel titreMenu;

	JMenuItem G_voitures;
	JMenuItem G_clients;
	JMenuItem G_locations;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_gestionVoiture frame = new F_gestionVoiture();
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
	public F_gestionVoiture() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Home");

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
		
		titreMenu = new JLabel("Gestion des voitures"+" |");
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
		
		
		JMenu menu_inscription = new JMenu("S'incrire");
		menuBar_1.add(menu_inscription);
		menu_inscription.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JMenu menu_connexion = new JMenu("Se connecter");
		menuBar_1.add(menu_connexion);
		menu_connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
				internalFrame.setBounds(342, 40, 334, 399);
				contentPane.add(internalFrame);
				internalFrame.setVisible(true);
			}
		});
		
		JMenu espace_2 = new JMenu("");
		espace_2.setEnabled(false);
		menuBar_1.add(espace_2); 
		
		//GrandTitre 
		JLabel titre = new JLabel("AgenceL");
		titre.setFont(new Font("Tahoma", Font.BOLD, 27));
		titre.setBounds(399, 5, 113, 33);
		contentPane.add(titre);
		
		//SousTitre 
		JLabel sous_titre = new JLabel("Gestion des voitures");
		sous_titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sous_titre.setBounds(390, 35, 132, 33);
		contentPane.add(sous_titre);
		
		
		//recherche
		JLabel Rechercher = new JLabel("Rechercher");
		Rechercher.setBounds(29, 131, 86, 14);
		contentPane.add(Rechercher);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"idVoiture", "matricule", "nom"}));
		comboBox.setBounds(29, 152, 121, 22);
		contentPane.add(comboBox);
		
		contenuRecherche = new JTextField();
		contenuRecherche.setBounds(164, 153, 86, 20);
		contentPane.add(contenuRecherche);
		contenuRecherche.setColumns(10);

		contenuRecherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					String selection =(String) comboBox.getSelectedItem();
					String value = contenuRecherche.getText();
										
					Voiture_dao voiture = new Voiture_dao();
					
					table.setModel(DbUtils.resultSetToTableModel(voiture.search(value, selection)));
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JButton actualiser = new JButton("Actualiser");
		actualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voiture_dao voiture = new Voiture_dao();
				
				table.setModel(DbUtils.resultSetToTableModel(voiture.All()));
			}
		});
		actualiser.setBounds(380, 152, 107, 23);
		contentPane.add(actualiser);
		
		//affichage des voitures
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 185, 458, 268);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Voiture_dao voiture = new Voiture_dao();
		
		table.setModel(DbUtils.resultSetToTableModel(voiture.All()));
		//form
		F_formVoiture formV = new F_formVoiture();
		formV.setBounds(518, 149, 345, 338);
		contentPane.add(formV);
		formV.show();
		
		
		JButton ajouter = new JButton("Ajouter");
		E_ajoutVoiture e_ajoutV=new E_ajoutVoiture(formV);
		ajouter.addActionListener(e_ajoutV);
		ajouter.setBounds(56, 464, 107, 23);
		contentPane.add(ajouter);
		
		
		JButton modifier = new JButton("Modifier");
		E_modifVoiture e_modifV = new E_modifVoiture(formV);
		modifier.addActionListener(e_modifV) ;
		modifier.setBounds(204, 464, 107, 23);
		contentPane.add(modifier);
		
		JButton supprimer = new JButton("Supprimer");
		E_suppVoiture e_suppV = new E_suppVoiture(formV);
		supprimer.addActionListener(e_suppV);
		supprimer.setBounds(345, 464, 107, 23);
		contentPane.add(supprimer);
		
		
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				int idV = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				String matriculeV = table.getModel().getValueAt(ligne, 1).toString();
				String nomV = table.getModel().getValueAt(ligne, 2).toString();
				Double prixV = Double.parseDouble(table.getModel().getValueAt(ligne, 3).toString());
				String disponibleV = table.getModel().getValueAt(ligne, 4).toString();
				
				formV.getId().setText(Integer.toString(idV));
				formV.getMatricule().setText(matriculeV);
				formV.getNom().setText(nomV);
				formV.getPrixL().setText(prixV.toString());
				if(disponibleV.equals("oui"))
					formV.getDispo_oui().setSelected(true);
				if(disponibleV.equals("non"))
					formV.getDispo_non().setSelected(true);
 
			}
		});
	}	
}

	
		
