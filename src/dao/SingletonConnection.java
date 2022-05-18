package dao;


import java.sql.*;
import javax.swing.JOptionPane;

public class SingletonConnection {
	private static Connection conn;
	
	private SingletonConnection(){
		
	}
	
	public static Connection getConnection() {
		try
		{
			if(conn==null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dblouervoiture","root","");
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e.getMessage(),"Erreur de Connexion",JOptionPane.ERROR_MESSAGE);
		}
		
		return conn;
	}
}
