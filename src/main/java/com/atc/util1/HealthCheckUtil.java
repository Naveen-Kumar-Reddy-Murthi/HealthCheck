package com.atc.util1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * 
 * @author Naveen.Reddy
 *
 */
public class HealthCheckUtil {
	
	public static final String GOOD ="GOOD";
	public static final String BAD = "BAD";
	private static final Properties config = new Properties(); 
	
	
	public String checkSFTPConnection(Map<String,String> paramMap){
		
		try {
	        JSch jsch = new JSch();
	        Session session = jsch.getSession(paramMap.get("user"), paramMap.get("host"));
	        session.setPort(Integer.parseInt(paramMap.get("port")));   
	        session.setPassword(paramMap.get("password"));
	        config.put("PreferredAuthentications", "password");
	        config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			
	        session.connect();
	        return GOOD;
	    } catch(Exception ex){
	    	System.out.println(ex.getMessage());
	    	ex.printStackTrace();
	      return BAD;   
	    }
	}
	
	public String CheckDBConnection(Map<String,String> paramMap){
			
		
		
//			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			StringBuilder url = new StringBuilder("jdbc:oracle:thin:@//")
					            .append(paramMap.get("host")).append(":")
					            .append(paramMap.get("port")).append("/")
					            .append(paramMap.get("sid"));
			String user 	= paramMap.get("user");
			String password = paramMap.get("password");
		     
			try(Connection con= DriverManager.getConnection(url.toString(), user, password)) {	
				
				if(con!=null&&con.isValid(5))
				   return GOOD;
					return BAD;
				
			} catch (Exception ex) {
				ex.printStackTrace();
				return BAD;
			}
		} /*catch (ClassNotFoundException e) {
			e.printStackTrace();
			return BAD;
		}
	}*/

}
