package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.*;

public class PublisherDao {

	public List<Publisher> getAllPublisher() throws SQLException {
        List<Publisher> list = new ArrayList<Publisher>();
        String sql = "select * from publisher";
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Publisher Publisher = new Publisher();
            	Publisher.setId(rs.getInt("id"));
            	Publisher.setName(rs.getString("name"));
                list.add(Publisher);
            }
            rs.close();
        	pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
	
	public Publisher getInfo(int id) throws SQLException {
        String sql = "select * from publisher where id = '" + id + "'";
        Connection conn = DbUtil.getCon();
        Publisher Publisher = new Publisher();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		Publisher.setId(rs.getInt("id"));
            	Publisher.setName(rs.getString("name"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return Publisher;
    }
	
	/*public boolean addPublisher(Publisher publisher) throws SQLException {
    	String sql = "insert into publisher(id, name) values (?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setInt(1, Publisher.getId());
    		pst.setString(2, Publisher.getName());
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
	
	public boolean modifyPublisher(Publisher Publisher) throws SQLException {
    	String sql = "update Publisher set name = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, Publisher.getName());
    		pst.setInt(2, Publisher.getId());
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
	
	public boolean delPublisher(int id) throws SQLException {
    	String sql = "delete from publisher where id = '" + id + "'";
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
