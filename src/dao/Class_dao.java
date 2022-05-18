package dao;

import java.sql.ResultSet;

public interface Class_dao {
	public ResultSet All();
		
	public ResultSet search(String Value, String selection);

	public int insert(Object o);
	
	public int update(Object o);
	
	public int delete(Object o);

}
