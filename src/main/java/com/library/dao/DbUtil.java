package com.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	//数据库连接驱动
	public static final String Driver = "com.mysql.cj.jdbc.Driver";
	//数据库连接地址（MySQL8.0需要添加时区设置）
	public static final String url = "jdbc:mysql://localhost:3306/library?serverTimezone=Asia/Shanghai";
	//数据库连接用户名
	public static final String username = "root";
	//数据库连接密码
	public static final String pwd = "root";
	
	//建立连接
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
	    
	//关闭连接
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
	 
    //返回连接信息
	public static void main(String[] args) {
	    System.out.println(DbUtil.getCon());
    }

}
