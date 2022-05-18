package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import dao.Location_dao;
import dao.SingletonConnection;
import dao.Voiture_dao;
import metier.Client;
import metier.Voiture;
import net.proteanit.sql.DbUtils;
import presentation.exceptions.location.NoADataFound;
import presentation.exceptions.voiture.NoDataFound;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MenuDragMouseEvent;
import java.awt.Toolkit;
import java.awt.Image;

public class F_accueil extends JFrame {
	
	Connection conn = SingletonConnection.getConnection();
	
	public static boolean connected = false;
	public static int idConnected = 0;
	public static boolean admin = false;
	
	private JTable table;
	
	F_detail detailV = null;
	private JDesktopPane contentPane;
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
		
		Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Siwar\\Desktop\\projetJava\\LocationVoiture\\src\\asset\\icon.jpg");  
	    setIconImage(icon); 

		contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(0, 80, 442, 33);
		contentPane.add(menuBar_1);
		
		JMenu espace = new JMenu(" ");
		espace.setEnabled(false);		
		menuBar_1.add(espace);
		
		JMenu F_courante = new JMenu("Liste des voitures | ");
		F_courante.setEnabled(false);
		menuBar_1.add(F_courante);
		
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(442, 80, 442, 33);
		contentPane.add(menuBar_2);
		menuBar_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JMenu espace_1 = new JMenu(" ");
		espace_1.setEnabled(false);		
		menuBar_2.add(espace_1);
		
		if(!F_accueil.connected) {
			JMenu menu_connexion = new JMenu("Se connecter");
			menu_connexion.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					F_connexion connexion = new F_connexion();
					connexion.setVisible(true);
					dispose();
				}
			});
			menuBar_2.add(menu_connexion);
			
			JMenu menu_inscription = new JMenu("S'incrire");
			menu_inscription.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					/*  if(menu_inscription.getText().equals(menu_inscription))	*/
					F_inscription inscription = new F_inscription();
					inscription.setVisible(true);
					dispose();
				}
			});
			menuBar_2.add(menu_inscription);
		}
		
		else{
			JMenu activites = new JMenu("Mes activit\u00E9s");
			activites.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					F_activite activite = new F_activite();
					activite.setVisible(true);
					dispose();
				}
			});
			menuBar_1.add(activites);
			
			
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
			menuBar_2.add(menu_deconnexion);
			
			JMenu menu_compte = new JMenu("Mon compte");
			menuBar_2.add(menu_compte);
			
			JMenuItem modifCompte = new JMenuItem("Modifier mon compte");
			modifCompte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					F_modifCompte M_Compte = new F_modifCompte();
					M_Compte.setVisible(true);
					dispose();
				}
			});
			menu_compte.add(modifCompte);
		
			JMenuItem suppCompte = new JMenuItem("Supprimer mon compte");
			suppCompte.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int yes_no  = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce compte? \nToutes vos locations seront annulées", "Supprimer mon compte", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					
					if (yes_no == 0) {
						Client client = new Client(F_accueil.idConnected);
						Client_dao D_client = new Client_dao();
						D_client.delete(client);
						
						F_accueil.connected = false;
						
						F_accueil accueil = new F_accueil();
						accueil.setVisible(true);
						dispose();
					}
				}
			});
			menu_compte.add(suppCompte);
		}
		
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
					
					if (!voiture.search(value, selection).next())
						throw new NoDataFound();
					
					table.setModel(DbUtils.resultSetToTableModel(voiture.search(value, selection)));
				} 
				catch(NoDataFound E) {
					JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				}
				catch(Exception ex) {
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
		actualiser.setBounds(385, 152, 107, 23);
		actualiser.setVisible(false);
		contentPane.add(actualiser);
		
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
				
				actualiser.setVisible(true);
				note.setVisible(false);
				scrollPane.setBounds(39, 185, 458, 302);
				detailFound = true;
				detailV = new F_detail(V);
				contentPane.add(detailV);
				detailV.show();
				
				
			}
		});
	}
	
}

	
		
