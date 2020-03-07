package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.entity.*;

public class UserDao {

	public boolean isValid(String username, String password) throws SQLException {
		String sql = "select * from user where username = '" + username + "'";
		Connection conn = DbUtil.getCon();
		User user = new User();
    	try {
    		PreparedStatement pst = conn.prepareStatement(sql);
    		ResultSet rs = pst.executeQuery();
    		while (rs.next()) {
    			user.setId(rs.getInt("id"));
            	user.setUsername(rs.getString("username"));
            	user.setPassword(rs.getString("password"));
    		}
    		rs.close();
    		pst.close();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	conn.close();
    	if(user.getUsername() != null && user.getUsername().equals(username) && user.getPassword().equals(password))
    		return true;
    	else
    		return false;
	}
	
	public boolean modifyUser(User user, String temp) throws SQLException {
    	String sql = "update user set username = ?, password = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, user.getUsername());
    		pst.setString(2, temp);
    		pst.setInt(3, user.getId());
    		int flag = pst.executeUpdate();
    		pst.close();
    		conn.close();
    		return flag > 0 ? true : false;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		conn.close();
    		return false;
    	}
    }

}
