package dao;

import metier.Voiture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class Voiture_dao implements Class_dao {
	Connection connection = SingletonConnection.getConnection();
	
	Statement transmission;	
	PreparedStatement ps;
	ResultSet listeVoiture;
	ResultSet listeVoitureTrouve;

	private ResultSet listeVoitureByMatricule;

	//affichage de toutes les voitures
	@Override
	public ResultSet All() {
		try {
				transmission = connection.createStatement();
			listeVoiture = transmission.executeQuery("select * from voiture");
			
			ResultSet L = listeVoiture;
			while(L.next()) {
				System.out.println(L.getInt("idVoiture"));

				Location_dao location = new Location_dao();
				
				ResultSet VoitL = location.find(L.getInt("idVoiture"));
				
				while(VoitL.next()) {
					System.out.println(VoitL.getString("idLocation"));
					LocalDate dateLoc = LocalDate.parse(VoitL.getString("date_locat"));
					System.out.println(dateLoc);
					LocalDate endLoc = LocalDate.parse(VoitL.getString("date_locat")).plusDays(VoitL.getInt("duree_prev"));
					System.out.println(endLoc);
					LocalDate now = java.time.LocalDate.now();
					System.out.println(now);
					if (now.isAfter(dateLoc) && now.isAfter(endLoc)) {
						this.updateD(L.getInt("idVoiture"), "oui");
					}
				}
			}
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
		
		//updateDispo
		public int updateD(int idVoiture, String dispo) {
			int r =0;
			
			try {
				ps = connection.prepareStatement("update voiture set disponible=? where idVoiture=?");
				
				ps.setString(1,dispo);
				ps.setInt(2, idVoiture);

				
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


	public int getIdVoiture(String Matricule) {
		int id = 0;
		try {
			ps = connection.prepareStatement("select * from  voiture where matricule=?");
			ps.setString(1,Matricule);
			listeVoitureTrouve = ps.executeQuery();
			listeVoitureTrouve.next();
			id = listeVoitureTrouve.getInt("idVoiture");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
}
