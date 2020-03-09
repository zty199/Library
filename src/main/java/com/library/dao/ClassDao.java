package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.Class;

public class ClassDao {

	public List<Class> getAllClass(int id) throws SQLException {
        List<Class> list = new ArrayList<Class>();
        String sql = "select * from class where id_category = " + id;
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Class Class = new Class();
            	Class.setId(rs.getInt("id"));
            	Class.setName(rs.getString("name"));
            	Class.setSymbol(rs.getString("symbol"));
            	Class.setId_category(rs.getInt("id_category"));
            	Class.setCount(rs.getInt("count"));
                list.add(Class);
            }
            rs.close();
        	pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
	
	public Class getInfo(int id) throws SQLException {
        String sql = "select * from class where id = " + id;
        Connection conn = DbUtil.getCon();
        Class Class = new Class();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		Class.setId(rs.getInt("id"));
            	Class.setName(rs.getString("name"));
            	Class.setSymbol(rs.getString("symbol"));
            	Class.setId_category(rs.getInt("id_category"));
            	Class.setCount(rs.getInt("count"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return Class;
    }
	
	/*public boolean addClass(Class Class) throws SQLException {
    	String sql = "insert into class(name, symbol, id_category) values (?, ?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, class.getName());
    		pst.setString(2, class.getSymbol());
    		pst.setInt(3, class.getId_category());
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
	
	public boolean modifyClass(Class Class) throws SQLException {
    	String sql = "update Class set name = ?, symbol = ?, id_category = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, class.getName());
    		pst.setString(2, class.getSymbol());
    		pst.setInt(3, class.getId_category());
    		pst.setInt(4, class.getId());
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
	
	public boolean delClass(int id) throws SQLException {
    	String sql = "delete from class where id = " + id;
    	Connection conn = DbUtil.getCon();
    	try {
    		PreparedStatement pst = conn.prepareStatement(sql);
    		int flag = pst.executeUpdate();
    		pst.close();
    		conn.close();
    		return flag > 0 ? true : false;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		conn.close();
    		return false;
    	}
    }*/
}
