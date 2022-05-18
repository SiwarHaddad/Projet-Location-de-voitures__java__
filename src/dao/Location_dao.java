package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import metier.Location;

public class Location_dao implements Class_dao {

Connection connection = SingletonConnection.getConnection();
	
	Statement transmission;	
	PreparedStatement ps;
	ResultSet listeLocation;
	ResultSet listeLocationClient;
	ResultSet listeLocationTrouve;

	//affichage de toutes les locations
	@Override
	public ResultSet All() {
		try {
			transmission = connection.createStatement();
			listeLocation = transmission.executeQuery("select idLocation as id_location, matricule as matricule_voiture,"
													+ "nomClient as nom_client, prenomClient as prenom_client, telClient as tel_client,"
													+ "date_locat as date_de_location, duree_prev as duree_prevue, total_locat as total "
													+ "from location,voiture,client " 
													+ "where location.idVoiture=voiture.idVoiture "
													+ "and location.idClient=client.idClient");
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeLocation;
	}
	
	//affichage de toutes les locations d'un client
	public ResultSet AllByIdClient(int idClient) {
		try {
			ps = connection.prepareStatement("select idLocation as id_location, matricule as matricule_voiture, nom as nom_voiture,"
											+ "date_locat as date_de_location, duree_prev as duree_prevue, total_locat as total "
											+ "from location,voiture "
											+ "where location.idVoiture=voiture.idVoiture "
											+ "and idClient=?");
			
			ps.setInt(1,idClient);
			
			listeLocationClient = ps.executeQuery(); 
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeLocationClient;
	}
		
	
	//recherche location
	@Override
	public ResultSet search(String value, String selection) {
		try {
			ps = connection.prepareStatement("select idLocation as id_location, matricule as matricule_voiture,"
											+ "nomClient as nom_client, prenomClient as prenom_client, telClient as tel_client,"
											+ "date_locat as date_de_location, duree_prev as duree_prevue, total_locat as total "
											+ "from location,voiture,client " 
											+ "where location.idVoiture=voiture.idVoiture "
											+ "and location.idClient=client.idClient "
											+ "and " +selection+" like '"+value+"%'");
			
			listeLocationTrouve = ps.executeQuery(); 
		        // bech nzid nhassen feha
		        //if((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)||(textFieldSearch.equals(""))  ){
		              // selecttable();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeLocationTrouve;
	}

	public ResultSet searchA(String value, String selection, int idClient) {
		try {
			ps = connection.prepareStatement("select idLocation as id_location, matricule as matricule_voiture,nom as nom_voiture,"
											+ "date_locat as date_de_location, duree_prev as duree_prevue, total_locat as total "
											+ "from location,voiture " 
											+ "where location.idVoiture=voiture.idVoiture "
											+ "and idClient=? " 
											+ "and " +selection+" like '"+value+"%'");
			
			ps.setInt(1,idClient);
			
			listeLocationTrouve = ps.executeQuery(); 
		        // bech nzid nhassen feha
		        //if((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)||(textFieldSearch.equals(""))  ){
		              // selecttable();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeLocationTrouve;
	}
	//insertion nouvelle location
	@Override
	public int insert(Object l) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("insert into location values(null,?,?,?,?,?)");
			
			ps.setInt(1,((Location) l).getIdVoiture());
			ps.setInt(2,((Location) l).getIdClient());
			ps.setString(3,((Location) l).getDate_locat());
			ps.setInt(4,((Location) l).getDuree_prev());
			ps.setDouble(5,((Location) l).getTotal_locat());
			
			r = ps.executeUpdate(); 
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Message",JOptionPane.INFORMATION_MESSAGE);
		}
		return r;
	}

	@Override
	public int update(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

/*
	//mise a jour d'une Location
	@Override
	public int update(Object l) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("update location set matricule=?,nom=?,prix_locat=?,disponible=? where idLocation=?");
			
			ps.setString(1,((Location) l).getMatricule());
			ps.setString(2,((Location) l).getNom());
			ps.setDouble(3,((Location) l).getPrix_locat());
			ps.setString(4,((Location) l).getDiponible());
			ps.setInt(5,((Location) l).getIdLocation());

			
			r=ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;		
	}*/
	//suppression d'une Location
	@Override
	public int delete(Object l) {
		int r =0;

		try {
			ps = connection.prepareStatement("delete from Location where idLocation=?");
			
			ps.setInt(1,((Location) l).getIdLocation());

			
			r = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;	
	}

	public ResultSet find(int idVoiture) {
		try {
			ps = connection.prepareStatement("select * from location where idVoiture=?");
			
			ps.setInt(1,idVoiture);
			
			listeLocationTrouve = ps.executeQuery(); 
		        // bech nzid nhassen feha
		        //if((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)||(textFieldSearch.equals(""))  ){
		              // selecttable();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeLocationTrouve;
	}

	
}
