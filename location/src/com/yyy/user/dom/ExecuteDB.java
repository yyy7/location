package com.yyy.user.dom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ExecuteDB extends ConnectDb {
	   
	  public Map<String, String> slectDb() {
		Map<String,String> map = new HashMap<String, String>();
		this.connectdb();
		String sql = "select latitude,longitude from t_location where id=1";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
			   map.put("latitude", rs.getString(1));
			   map.put("longitude", rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			this.closedb();
		}
		return map;
		}
	
     public boolean updateDb(String s1,String s2) {
    	 this.connectdb();
    	 
    	 String sql="update t_location set latitude = ?, longitude =? where id = 1";
    	 try {
    		conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s1);
			ps.setString(2, s2);
			int j  = ps.executeUpdate();
			conn.commit();
			if(j>0)
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 finally{
    		 this.closedb();
    	 }
    	
		return false;

	}
}
