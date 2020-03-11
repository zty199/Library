package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.*;

public class CategoryDao {

	public List<Category> getAllCategory() throws SQLException {
        List<Category> list = new ArrayList<Category>();
        String sql = "select * from category";
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Category category = new Category();
            	category.setId(rs.getInt("id"));
            	category.setName(rs.getString("name"));
            	category.setSymbol(rs.getString("symbol"));
                list.add(category);
            }
            rs.close();
        	pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
	
	public Category getInfo(int id) throws SQLException {
        String sql = "select * from category where id = " + id;
        Connection conn = DbUtil.getCon();
        Category category = new Category();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		category.setId(rs.getInt("id"));
            	category.setName(rs.getString("name"));
            	category.setSymbol(rs.getString("symbol"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return category;
    }
	
	/*public boolean addCategory(Category category) throws SQLException {
    	String sql = "insert into category(id, name, symbol) values (?, ?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setInt(1, category.getId());
    		pst.setString(2, category.getName());
    		pst.setString(3, category.getSymbol());
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
	
	public boolean modifyCategory(Category category) throws SQLException {
    	String sql = "update category set name = ?, symbol = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, category.getName());
    		pst.setString(2, category.getSymbol());
    		pst.setInt(3, category.getId());
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
	
	public boolean delCategory(int id) throws SQLException {
    	String sql = "delete from category where id = '" + id + "'";
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
