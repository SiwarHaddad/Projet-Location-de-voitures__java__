package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.Film_dao;
import dao.Voiture_dao;
import metier.Film;
import metier.Voiture;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class F_film extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Film F;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F_film frame = new F_film();
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
	public F_film() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 32, 383, 169);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Film_dao film = new Film_dao();
		
		table.setModel(DbUtils.resultSetToTableModel(film.All()));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne = table.getSelectedRow();
				
				int idF = Integer.parseInt(table.getModel().getValueAt(ligne, 0).toString());
				String nomF = table.getModel().getValueAt(ligne, 1).toString();
				String genreF = table.getModel().getValueAt(ligne, 2).toString();
				int anneeF = Integer.parseInt(table.getModel().getValueAt(ligne, 3).toString());
				
				F = new Film(idF,nomF,genreF,anneeF);	
			}
		});
		
		JButton supp = new JButton("Supprimer");
		supp.setBounds(126, 227, 89, 23);
		supp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Film_dao film = new Film_dao();
				int r = film.delete(F);
				
				if(r==1) 
					JOptionPane.showMessageDialog(null,"Suppression avec succés","Message",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		contentPane.add(supp);
		
		JButton actualiser = new JButton("Actualiser");
		actualiser.setBounds(234, 227, 89, 23);
		actualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Film_dao film = new Film_dao();
				
				table.setModel(DbUtils.resultSetToTableModel(film.All()));
			}
		});
		contentPane.add(actualiser);
	}
}
