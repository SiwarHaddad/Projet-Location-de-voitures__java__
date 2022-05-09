package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import metier.Client;

public class Client_dao implements Class_dao {
	Connection connection = SingletonConnection.getConnection();;
	
	Statement transmission;	
	PreparedStatement ps;
	ResultSet listeClient;
	ResultSet listeClientTrouve;
	
	//affichage liste des clients
	@Override
	public ResultSet All() {
		try {
			transmission = connection.createStatement();
			listeClient = transmission.executeQuery("select * from client");
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeClient;
	}
	
	//recherche d'un client
	@Override
	public ResultSet search(String value, String selection) {
		try {
			ps = connection.prepareStatement("select * from client where "+selection+" like '"+value+"%'");
			listeClientTrouve = ps.executeQuery(); 
		        // bech nzid nhassen feha
		        //if((e.getKeyCode() == KeyEvent.VK_BACK_SPACE)||(textFieldSearch.equals(""))  ){
		              // selecttable();
			
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
		
		return listeClientTrouve;
	}
	
	//insertion d'un nouveau client
	@Override
	public int insert(Object c) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("insert into client values(null,?,?,?,?,?,?)");
			
			ps.setString(1,((Client) c).getNomClient());
			ps.setString(2,((Client) c).getPrenomClient());
			ps.setString(3,((Client) c).getPseudo());
			ps.setString(4,((Client) c).getMdpClient());
			ps.setString(5,((Client) c).getTelClient());
			ps.setString(6,((Client) c).getAdresseClient());
			
			r = ps.executeUpdate(); 
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	//mise à jour d'un client
	@Override
	public int update(Object c) {
		int r =0;
		
		try {
			ps = connection.prepareStatement("update Client set nomClient=?, prenomClient=?, pseudo=?, mdpClient=?, telClient=?, adresseClient=? where idClient=?");
			
			ps.setString(1,((Client) c).getNomClient());
			ps.setString(2,((Client) c).getPrenomClient());
			ps.setString(3,((Client) c).getPseudo());
			ps.setString(4,((Client) c).getMdpClient());
			ps.setString(5,((Client) c).getTelClient());
			ps.setString(6,((Client) c).getAdresseClient());
			ps.setInt(7,((Client) c).getIdClient());

			
			r=ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;		
	}

	//suppression d'un client
	@Override
	public int delete(Object c) {
		int r =0;

		try {
			ps = connection.prepareStatement("delete from client where idClient=?");
			
			ps.setInt(1,((Client) c).getIdClient());

			
			r = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return r;	
	}
}
