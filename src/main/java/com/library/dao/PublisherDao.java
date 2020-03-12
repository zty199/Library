package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.*;

public class PublisherDao {

	public List<Publisher> getAllPublisher(int id) throws SQLException {
        List<Publisher> list = new ArrayList<Publisher>();
        String sql = "select * from publisher where id_region = " + id;
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Publisher publisher = new Publisher();
            	publisher.setId(rs.getInt("id"));
            	publisher.setReference(rs.getString("reference"));
            	publisher.setName(rs.getString("name"));
            	publisher.setId_region(rs.getInt("id_region"));
                list.add(publisher);
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
        String sql = "select * from publisher where id = " + id;
        Connection conn = DbUtil.getCon();
        Publisher publisher = new Publisher();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		publisher.setId(rs.getInt("id"));
        		publisher.setReference(rs.getString("reference"));
        		publisher.setName(rs.getString("name"));
            	publisher.setId_region(rs.getInt("id_region"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return publisher;
    }
	
	public Publisher getInfo(String reference) throws SQLException {
        String sql = "select * from publisher where reference = '"  + reference + "'";
        Connection conn = DbUtil.getCon();
        Publisher publisher = new Publisher();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		publisher.setId(rs.getInt("id"));
        		publisher.setReference(rs.getString("reference"));
        		publisher.setName(rs.getString("name"));
            	publisher.setId_region(rs.getInt("id_region"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return publisher;
    }
	
	public boolean isListed(String reference) throws SQLException {
        String sql = "select * from publisher where reference = '" + reference + "'";
        Connection conn = DbUtil.getCon();
        Publisher publisher = new Publisher();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		publisher.setId(rs.getInt("id"));
        		publisher.setReference(rs.getString("reference"));
        		publisher.setName(rs.getString("name"));
            	publisher.setId_region(rs.getInt("id_region"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        if(publisher.getReference() != null && publisher.getReference().equals(reference)) {
        	return true;
    	} else {
    		return false;
    	}
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
