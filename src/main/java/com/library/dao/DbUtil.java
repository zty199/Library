package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	 public static final String Driver = "com.mysql.cj.jdbc.Driver";
	 public static final String url = "jdbc:mysql://localhost:3306/library?serverTimezone=Asia/Shanghai";
	 public static final String username = "root";
	 public static final String pwd = "root";
	 
	 public static Connection getCon() {
	     Connection conn = null;
	     try {
	         Class.forName(Driver);
	         conn = DriverManager.getConnection(url,username,pwd);       
	     } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	     return conn;
	 }
	    
	 public static void close(Connection conn) {
	     try {
	         if(conn!=null) {
	             conn.close();
	         }
	     } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	     }
	 }
	 
     public static void main(String[] args) {
	     System.out.println(DbUtil.getCon());
     }

}
