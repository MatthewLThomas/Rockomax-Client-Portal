package com.rcp.config;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class FileConnectionsUtil {

	private String url="";
	private String username="";
	private String password="";
	private static FileConnectionsUtil instance;
	private FileConnectionsUtil() {
		
	}
	
	public static FileConnectionsUtil getInstance() {
		if (instance == null) {
			instance = new FileConnectionsUtil();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, username, password);
	}
	{
		FileInputStream f;
		try {
			f = new FileInputStream("src/main/java/com/rcp/config/connection.properties");
		
		Properties p = new Properties();
		p.load(f);
		username=p.getProperty("username");
		password=p.getProperty("password");
		url = p.getProperty("url");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
