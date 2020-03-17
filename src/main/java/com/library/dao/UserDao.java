package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.library.entity.*;

public class UserDao {

	//根据用户名获取用户信息
	public User getInfo(String username) throws SQLException {
        String sql = "select * from user where username = '" + username + "'";
        Connection conn = DbUtil.getCon();
        User user = new User();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
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
        return user;
    }

	//判断用户名密码是否匹配
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
	
	//修改用户密码
	public boolean modifyUser(User user) throws SQLException {
    	String sql = "update user set password = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, user.getPassword());
    		pst.setInt(2, user.getId());
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
