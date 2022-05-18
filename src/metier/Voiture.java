package metier;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.SingletonConnection;
import net.proteanit.sql.DbUtils;

public class Voiture {
	private int idVoiture;
	private String matricule;
	private String nom;
	private Double prix_locat;
	private String diponible;
	
	Connection conn = SingletonConnection.getConnection();
	Statement transmission;	
	ResultSet listeVoiture;
	ResultSet listeVoitureTrouve;
	PreparedStatement ps;

	//constructors
	public Voiture() {
		 
	}

	public Voiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public Voiture( String matricule, String nom, Double prix_locat, String diponible) {
		this.matricule = matricule;
		this.nom = nom;
		this.prix_locat = prix_locat;
		this.diponible = diponible;
	}

	public Voiture(int idVoiture, String matricule, String nom, Double prix_locat, String diponible) {
		this.idVoiture = idVoiture;
		this.matricule = matricule;
		this.nom = nom;
		this.prix_locat = prix_locat;
		this.diponible = diponible;
	}

	public int getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(int idVoiture) {
		this.idVoiture = idVoiture;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix_locat() {
		return prix_locat;
	}

	public void setPrix_locat(Double prix_locat) {
		this.prix_locat = prix_locat;
	}

	public String getDiponible() {
		return diponible;
	}

	public void setDiponible(String diponible) {
		this.diponible = diponible;
	}

	public Connection getConn() {
		return conn;
	}

	public Statement getTransmission() {
		return transmission;
	}

	public ResultSet getListeVoiture() {
		return listeVoiture;
	}

	public PreparedStatement getPs() {
		return ps;
	}
/*
	//affichage de toutes les voitures
	public ResultSet All(){
		try {
			transmission = conn.createStatement();
			listeVoiture = transmission.executeQuery("select * from voiture");
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeVoiture;
	}
	
	//recherche voiture
	
	public ResultSet search(String Value, String selection){
		try {
			ps = conn.prepareStatement("select * from voiture where "+selection+"=? ");
			ps.setString(0,Value);
			listeVoitureTrouve = ps.executeQuery(); 
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeVoitureTrouve;
	}
	
	
	//insertion nouvelle voiture
	public int insert() {
		int r =0;
		try {
			ps = conn.prepareStatement("insert into voiture values(null,?,?,?,?)");
			
			ps.setString(1,this.matricule);
			ps.setString(2,this.nom);
			ps.setDouble(3,this.prix_locat);
			ps.setString(4,this.diponible);
			
			r = ps.executeUpdate(); 
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//mise a jour d'une voiture
	public int update() {
		int r =0;
		try {
			ps = conn.prepareStatement("update voiture set matricule=?,nom=?,prix_locat=?,disponible=? where idVoiture=?");
			
			ps.setString(1,this.matricule);
			ps.setString(2,this.nom);
			ps.setDouble(3,this.prix_locat);
			ps.setString(4,this.diponible);
			ps.setInt(5,this.idVoiture);

			
			r=ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;		
	}
	
	//suppression d'une voiture
	public int delete() {
		int r =0;
		try {
			ps = conn.prepareStatement("delete from voiture where idVoiture=?");
			
			ps.setInt(1,this.idVoiture);

			
			r = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;		
	}*/
}