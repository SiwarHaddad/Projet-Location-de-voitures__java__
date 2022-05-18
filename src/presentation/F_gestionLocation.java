package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import dao.Location_dao;
import dao.SingletonConnection;
import metier.Location;
import net.proteanit.sql.DbUtils;
import presentation.events.events_location.E_suppPGLocation;
import presentation.exceptions.location.NoDataFound;

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
import java.time.LocalDate;
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

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;


public class F_gestionLocation extends JFrame {
	Connection conn = SingletonConnection.getConnection();

	private JDesktopPane contentPane;
	public static JTable table;

	private JTextField contenuRecherche;

	JDesktopPane desktopPane;

	JLabel titreMenu;
	JMenuItem G_voitures;
	JMenuItem G_clients;
	JMenuItem G_locations;

	Location Location = new Location();
	private JTextField c1;
	private JTextField c2;
	private JTextField c3;

	protected int duree_prev;

	protected String dateL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_gestionLocation frame = new F_gestionLocation();
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
	public F_gestionLocation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Gestion des locations");

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
		
		titreMenu = new JLabel("Gestion des locations"+" |");
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
		JLabel sous_titre = new JLabel("Gestion des locations");
		sous_titre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sous_titre.setBounds(390, 35, 197, 33);
		contentPane.add(sous_titre);
		
		
		//recherche
		JLabel Rechercher = new JLabel("Rechercher");
		Rechercher.setBounds(39, 131, 86, 14);
		contentPane.add(Rechercher);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"idLocation", "matricule", "nomClient", "telClient"}));
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
					
					if (!location.search(value, selection).next())
						throw new NoDataFound();
					
					table.setModel(DbUtils.resultSetToTableModel(location.search(value, selection)));
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
				Location_dao location = new Location_dao();
				
				table.setModel(DbUtils.resultSetToTableModel(location.All()));
			}
		});
		actualiser.setBounds(548, 152, 107, 23);
		contentPane.add(actualiser);
		
		
		
		//affichage des locations
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 185, 803, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		
		Location_dao location = new Location_dao();
		
		table.setModel(DbUtils.resultSetToTableModel(location.All()));
		
		table = F_gestionLocation.getNewRenderedTable(table);
		scrollPane.setViewportView(table);
		 	
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne = table.getSelectedRow();
				
				int idL = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				
				dateL = table.getModel().getValueAt(ligne, 5).toString();
				duree_prev = Integer.parseInt(table.getModel().getValueAt(ligne, 6).toString());
				
				Location.setIdLocation(idL);
				Location.setDate_locat(dateL);
				Location.setDuree_prev(duree_prev);
			}
		});


		JButton supprimer = new JButton("Annuler une location");
		supprimer.setBounds(667, 152, 175, 23);
		E_suppPGLocation e_suppL = new E_suppPGLocation(Location);
		supprimer.addActionListener(e_suppL);
		contentPane.add(supprimer);
		
		
		c1 = new JTextField();
		c1.setBackground(new Color(255, 228, 225));
		c1.setBounds(58, 480, 20, 20);
		contentPane.add(c1);
		c1.setColumns(10);
		
		JLabel c1_l = new JLabel("Location(s) non encore commenc\u00E9e(s)");
		c1_l.setBounds(88, 483, 249, 14);
		contentPane.add(c1_l);
		
		c2 = new JTextField();
		c2.setColumns(10);
		c2.setBackground(new Color(175, 238, 238));
		c2.setBounds(399, 480, 20, 20);
		contentPane.add(c2);
		
		JLabel c2_l = new JLabel("Location(s) en cours");
		c2_l.setBounds(429, 483, 136, 14);
		contentPane.add(c2_l);
		
		c3 = new JTextField();
		c3.setColumns(10);
		c3.setBackground(new Color(204, 255, 204));
		c3.setBounds(637, 480, 20, 20);
		contentPane.add(c3);
		
		JLabel c3_l = new JLabel("Location(s) termin\u00E9e(s)");
		c3_l.setBounds(667, 483, 143, 14);
		contentPane.add(c3_l);
	}
	
	
	private static JTable getNewRenderedTable(final JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                
    			String date = table.getModel().getValueAt(row, 5).toString();
    			String dateC = java.time.LocalDate.now().toString();
    			String dateP = (LocalDate.parse(table.getModel().getValueAt(row, 5).toString()).plusDays(Integer.parseInt(table.getModel().getValueAt(row, 6).toString()))).toString();
    			
                if (dateC.compareTo(date)>=0) {
                	if (dateC.compareTo(dateP)<=0) {
                		setBackground(new Color(175, 238, 238));
                	}
                	else {
                		setBackground(new Color(204, 255, 204));
                	}
                	
                } else {
                    setBackground(new Color(255, 228, 225));
                }       
                return this;
            }   
        });
        return table;
    }
}

