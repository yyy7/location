package com.yyy.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.yyy.user.dom.ExecuteDB;
import com.yyy.user.factory.HibernateSessionFactory;
import com.yyy.user.model.TLocation;

public class UpdateLocation {
	private String latitude;
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	private String longitude;
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	private String city;
    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	HttpServletResponse response =ServletActionContext.getResponse();
	PrintWriter pw=null;
	private void setresponse(){
		response.setHeader("Access-Control-Allow-Origin", "*");
     	response.setHeader("Access-Control-Allow-Methods", "GET,POST");
     	response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
     	response.setHeader("Access-Control-Allow-Credentials", "true");
     	response.setCharacterEncoding("UTF-8");
	}
     public String update() {
    	this.setresponse(); 		
     	System.out.println(latitude+","+longitude);
     	ExecuteDB exc=new ExecuteDB();  	
        try {
        	
			pw =response.getWriter();
			if(exc.updateDb(latitude, longitude))
		     {
				 Map<String, String> maps = exc.slectDb();
				 String y = maps.get("latitude");
				 String x = maps.get("longitude");
				 System.out.println(y+","+x);
		         pw.println("result({\"latitude\":\""+ y  + "\",\"longitude\":\""+ x + "\"})");
		     }
		     else{
		          pw.println("result({\"result\":\"error\"})");
		     }	 		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       finally{
 		pw.flush();
 			
     	pw.close();	
       }	
        return null;

	}
      public String hbinsert(){
    	  Session session =HibernateSessionFactory.getSession();
    	  TLocation tlocation = new TLocation();
    	  tlocation.setLatitude(latitude);
    	  tlocation.setLongitude(longitude);
    	  tlocation.setCity(city);
    	  tlocation.setCtime(new Date());
    	  session.save(tlocation);
    	  session.beginTransaction().commit();
    	  HibernateSessionFactory.closeSession();
    	  JSONObject jobject = new JSONObject();
    	  jobject.put("latitude", latitude);
    	  jobject.put("longitude", longitude);
    	  jobject.put("city", city);
    	  try {
			pw =response.getWriter();
			if(latitude==null||longitude==null||city==null)
			{
				pw.close();
			}
			pw.println(jobject.toString());
			pw.flush();
	    	pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	  
		return null;
    	  
      }
}
