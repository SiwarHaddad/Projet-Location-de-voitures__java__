package dao;

import dao.SingletonConnection;
import metier.Voiture;
import net.proteanit.sql.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Voiture_dao implements Class_dao {
	Connection connection = SingletonConnection.getConnection();;
	
	Statement transmission;	
	PreparedStatement ps;
	ResultSet listeVoiture;
	ResultSet listeVoitureTrouve;

	//affichage de toutes les voitures
	@Override
	public ResultSet All() {
		try {
			transmission = connection.createStatement();
			listeVoiture = transmission.executeQuery("select * from voiture");
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeVoiture;
	}
	
	
	//recherche voiture
	@Override
	public ResultSet search(String value, String selection) {
		try {
			ps = connection.prepareStatement("select * from voiture where "+selection+" like '"+value+"%'");
			listeVoitureTrouve = ps.executeQuery(); 
		        // bech nzid nhassen feha
		        //if((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)||(textFieldSearch.equals(""))  ){
		              // selecttable();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeVoitureTrouve;
	}

	
	//insertion nouvelle voiture
	@Override
	public int insert(Object v) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("insert into voiture values(null,?,?,?,?)");
			
			ps.setString(1,((Voiture) v).getMatricule());
			ps.setString(2,((Voiture) v).getNom());
			ps.setDouble(3,((Voiture) v).getPrix_locat());
			ps.setString(4,((Voiture) v).getDiponible());
			
			r = ps.executeUpdate(); 
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	
	//mise a jour d'une voiture
	@Override
	public int update(Object v) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("update voiture set matricule=?,nom=?,prix_locat=?,disponible=? where idVoiture=?");
			
			ps.setString(1,((Voiture) v).getMatricule());
			ps.setString(2,((Voiture) v).getNom());
			ps.setDouble(3,((Voiture) v).getPrix_locat());
			ps.setString(4,((Voiture) v).getDiponible());
			ps.setInt(5,((Voiture) v).getIdVoiture());

			
			r=ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;		
	}
	//suppression d'une voiture
	@Override
	public int delete(Object v) {
		int r =0;

		try {
			ps = connection.prepareStatement("delete from voiture where idVoiture=?");
			
			ps.setInt(1,((Voiture) v).getIdVoiture());

			
			r = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;	
	}
}
