package presentation;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.SingletonConnection;
import dao.Voiture_dao;
import metier.Voiture;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;

public class F_accueil extends JFrame {
	
	Connection conn = SingletonConnection.getConnection();
	
	public static boolean connected = false;
	public static int idConnected = 0;
	
	F_detail detailV = null;
	private JDesktopPane contentPane;
	private JTable table;
	private JTextField contenuRecherche;
	protected boolean detailFound = false;
	protected boolean locationFound = false;
	JDesktopPane desktopPane;
	JLabel titreMenu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_accueil frame = new F_accueil();
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
	public F_accueil() {
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
		
		titreMenu = new JLabel("Liste des voitures |");
		menuBar.add(titreMenu);
		
		JMenu activites = new JMenu("Mes activit\u00E9s");
		activites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(!titreMenu.equals(activites.getText())) {
					F_activite activite = new F_activite();
					//activite.setVisible(true);
					dispose();	
				}
			}
		});
		menuBar.add(activites);
		
		
		JMenu menu_inscription = new JMenu("S'incrire");
		menu_inscription.addMenuListener(new MenuListener() {
			@Override
			public void menuSelected(MenuEvent e) {
				F_inscription inscription = new F_inscription();
				inscription.setVisible(true);
				dispose();	
			}

			@Override
			public void menuDeselected(MenuEvent e) {}

			@Override
			public void menuCanceled(MenuEvent e) {}
		});
		menuBar_1.add(menu_inscription);
		menu_inscription.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JMenu menu_connexion = new JMenu("Se connecter");
		menuBar_1.add(menu_connexion);
		menu_connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				F_connexion connexion = new F_connexion();
				connexion.setVisible(true);
				dispose();	
			}
		});
		
		
		JMenu menu_compte = new JMenu("Mon compte");
		menuBar_1.add(menu_compte);
		
		JMenuItem modifCompte = new JMenuItem("Modifier mon compte");
		modifCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!titreMenu.equals(modifCompte.getText())) {
					/*F_modifCompte M_Compte = new F_modifCompte();
					M_compte.setVisible(true);
					dispose();*/
				}
			}
		});
		menu_compte.add(modifCompte);
		
		JMenuItem suppCompte = new JMenuItem("Supprimer mon compte");
		suppCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!titreMenu.equals(suppCompte.getText())) {
					/*F_suppCompte S_Compte = new F_suppCompte();
					S_compte.setVisible(true);
					dispose();*/
				}
			}
		});
		menu_compte.add(suppCompte);
		
		JMenu menu_deconnexion = new JMenu("Se déconnecter");
		modifCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		menuBar_1.add(menu_deconnexion);
		
		JMenu espace_2 = new JMenu("");
		espace_2.setEnabled(false);
		menuBar_1.add(espace_2); 
		
		if(!connected) {
			activites.setVisible(false);
			menu_compte.setVisible(false);
			menu_deconnexion.setVisible(false);
		}
		/*
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 884, 33);
		contentPane.add(menuBar);
		
		JMenu menu_location = new JMenu("Louer une voiture");
		menuBar.add(menu_location);
		
		JMenu menu_inscription = new JMenu("S'incrire");
		menuBar.add(menu_inscription);
		
		
		JMenu menu_connexion = new JMenu("Se connecter");
		menuBar.add(menu_connexion);
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
		*/
		//GrandTitre 
		JLabel titre = new JLabel("Bienvenue dans AgenceL");
		titre.setFont(new Font("Tahoma", Font.BOLD, 27));
		titre.setBounds(261, 5, 405, 33);
		contentPane.add(titre);
		
		//SousTitre 
		JLabel sous_titre = new JLabel("Une agence de location de voitures");
		sous_titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sous_titre.setBounds(313, 35, 243, 33);
		contentPane.add(sous_titre);
		
		
		//recherche
		JLabel Rechercher = new JLabel("Rechercher");
		Rechercher.setBounds(39, 131, 86, 14);
		contentPane.add(Rechercher);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"idVoiture", "matricule", "nom"}));
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
										
					Voiture_dao voiture = new Voiture_dao();
					
					table.setModel(DbUtils.resultSetToTableModel(voiture.search(value, selection)));
				} catch(Exception ex) {
					ex.printStackTrace();
					
					
				}
			}
		});
		
		JLabel note = new JLabel("(Cliquer sur une voiture pour afficher plus de d\u00E9tails et louer)");
		note.setBounds(490, 160, 352, 14);
		contentPane.add(note);
		
		//affichage des voitures
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 185, 803, 298);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Voiture_dao voiture = new Voiture_dao();
		
		table.setModel(DbUtils.resultSetToTableModel(voiture.All()));
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(detailFound)
					detailV.dispose();	
				/*
				if(locationFound)
					locationV.dispose();	
				*/
				int ligne = table.getSelectedRow();
				
				int idV = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				String matriculeV = table.getModel().getValueAt(ligne, 1).toString();
				String nomV = table.getModel().getValueAt(ligne, 2).toString();
				Double prixV = Double.parseDouble(table.getModel().getValueAt(ligne, 3).toString());
				String disponibleV = table.getModel().getValueAt(ligne, 4).toString();
				
				Voiture V = new Voiture(idV,matriculeV,nomV,prixV,disponibleV);	
				
				note.setVisible(false);
				scrollPane.setBounds(29, 185, 458, 302);
				detailFound = true;
				detailV = new F_detail(V);
				contentPane.add(detailV);
				detailV.show();
				/*
				locationFound = true;
				locationV = new F_location(V);
				locationV.setVisible(true);
				contentPane.add(locationV);*/
			}
		});
	}	
}

	
		
