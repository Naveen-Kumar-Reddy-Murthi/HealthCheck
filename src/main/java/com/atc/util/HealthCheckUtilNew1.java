package com.atc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;

import org.mule.api.MuleEventContext;
import org.mule.api.MuleMessage;
import org.mule.api.lifecycle.Callable;

import com.atc.response.Server;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * 
 * @author Naveen.Reddy
 *
 */
public class HealthCheckUtilNew1 implements Callable
{
	
	private static final String GOOD ="GOOD";
	private static final String BAD = "BAD";  

	@Override
	public Object onCall(MuleEventContext eventContext) throws Exception {
		
		
		String health = "GOOD";
		MuleMessage message = eventContext.getMessage();
		
		Map<String,String> paramMap = message.getInvocationProperty("paramMap");
		
		
//		String conType=paramMap.get("type");
		String conType= message.getInvocationProperty("conType");
		
		Server server= new Server();
		server.setType(conType);
//		server.setHost(paramMap.get("host"));
		
		
//		if("db".equals(conType))
		if((!"".equals(conType)&&conType!=null)&&(conType.substring(0, conType.indexOf("."))).equalsIgnoreCase("db"))
			health = CheckDBConnection(paramMap);			

//		else if("sftp".equals(conType))
		else if((!"".equals(conType)&&conType!=null)&&(conType.substring(0, conType.indexOf("."))).equalsIgnoreCase("sftp"))
			health = checkSFTPConnection(paramMap);

		else
			System.out.println("Type not handled for ::"+conType);
		
		if(BAD.equals(health))
			message.setInvocationProperty("health", health);
		
			server.setHealth(health);
			message.setInvocationProperty("response", server);
		return message;
	}
	
private String checkSFTPConnection(Map<String,String> paramMap){
		
	Properties config = new Properties();
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
	
private String CheckDBConnection(Map<String,String> paramMap){
			
			
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
		} 
}
