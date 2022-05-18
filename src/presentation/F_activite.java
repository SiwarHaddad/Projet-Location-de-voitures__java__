package presentation;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.Client_dao;
import dao.Location_dao;
import dao.SingletonConnection;
import metier.Client;
import metier.Location;
import net.proteanit.sql.DbUtils;
import presentation.events.events_location.E_suppPALocation;
import presentation.exceptions.location.NoADataFound;
import presentation.exceptions.location.NoARDataFound;

import javax.swing.JButton;

public class F_activite extends JFrame {
	Connection conn = SingletonConnection.getConnection();
	
	F_detail detailV = null;
	private JDesktopPane contentPane;
	public static JTable table;
	private JTextField contenuRecherche;
	protected boolean detailFound = false;
	protected boolean locationFound = false;
	JDesktopPane desktopPane;
	JLabel titreMenu;

	Location Location = new Location();

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_activite frame = new F_activite();
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
	public F_activite() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Mes activités");

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
		
		JMenu F_courante = new JMenu("Mes activités | ");
		F_courante.setEnabled(false);
		menuBar_1.add(F_courante);
		
		JMenu activites = new JMenu("Page d'accueil");
		activites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				F_accueil accueil = new F_accueil();
				accueil.setVisible(true);
				dispose();
			}
		});
		menuBar_1.add(activites);
		
		
		JMenuBar menuBar_2 = new JMenuBar();
		menuBar_2.setBounds(442, 80, 442, 33);
		contentPane.add(menuBar_2);
		menuBar_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JMenu espace_1 = new JMenu(" ");
		espace_1.setEnabled(false);		
		menuBar_2.add(espace_1);
		
		JMenu menu_deconnexion = new JMenu("Se déconnecter");
		menu_deconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int yes_no  = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment se déconnecter", "Quitter", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"idLocation", "matricule"}));
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
										
					Location_dao location = new Location_dao();
					
					table.setModel(DbUtils.resultSetToTableModel(location.searchA(value, selection, F_accueil.idConnected)));
				
					if (!location.searchA(value, selection, F_accueil.idConnected).next())
						throw new NoARDataFound();
					} 
				catch(NoARDataFound E) {
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
				Location_dao location = new Location_dao();
				try {
					table.setModel(DbUtils.resultSetToTableModel(location.AllByIdClient(F_accueil.idConnected)));
					
					if (!location.AllByIdClient(F_accueil.idConnected).next())
						throw new NoADataFound();
					
				} 
				catch(NoADataFound E) {
					JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
				} catch (SQLException ex) {
					ex.printStackTrace();
				} 
			}
		});
		actualiser.setBounds(735, 152, 107, 23);
		contentPane.add(actualiser);
		
		//affichage des voitures
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 185, 803, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Location_dao location = new Location_dao();
		
		try {
			table.setModel(DbUtils.resultSetToTableModel(location.AllByIdClient(F_accueil.idConnected)));
			
			if (!location.AllByIdClient(F_accueil.idConnected).next())
				throw new NoADataFound();
			
		} 
		catch(NoADataFound E) {
			JOptionPane.showMessageDialog(null,E.getMessage(),"Alerte",JOptionPane.ERROR_MESSAGE);
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
		} 
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				
				int idL = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				String dateL = table.getModel().getValueAt(ligne, 3).toString();
				int duree_prev = Integer.parseInt(table.getModel().getValueAt(ligne, 4).toString());
				
				Location.setIdLocation(idL);
				Location.setDate_locat(dateL);
				Location.setDuree_prev(duree_prev);
			}
		});
		
		JButton annuler = new JButton("Annuler une location");
		annuler.setBounds(342, 470, 200, 23);
		E_suppPALocation e_suppL = new E_suppPALocation(Location);
		annuler.addActionListener(e_suppL);
		contentPane.add(annuler);
		
	}	
}

