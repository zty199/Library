package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.*;

public class RegionDao {

	public List<Region> getAllRegion() throws SQLException {
        List<Region> list = new ArrayList<Region>();
        String sql = "select * from region";
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Region region = new Region();
            	region.setId(rs.getInt("id"));
            	region.setReference(rs.getString("reference"));
            	region.setName(rs.getString("name"));
            	region.setSymbol(rs.getString("symbol"));
                list.add(region);
            }
            rs.close();
        	pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
	
	public Region getInfo(int id) throws SQLException {
        String sql = "select * from region where id = " + id;
        Connection conn = DbUtil.getCon();
        Region region = new Region();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		region.setId(rs.getInt("id"));
            	region.setReference(rs.getString("reference"));
            	region.setName(rs.getString("name"));
            	region.setSymbol(rs.getString("symbol"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return region;
    }
	
	public Region getInfo(String reference) throws SQLException {
        String sql = "select * from region where reference = '" + reference + "'";
        Connection conn = DbUtil.getCon();
        Region region = new Region();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		region.setId(rs.getInt("id"));
            	region.setReference(rs.getString("reference"));
            	region.setName(rs.getString("name"));
            	region.setSymbol(rs.getString("symbol"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return region;
    }
	
	public boolean isListed(String reference) throws SQLException {
        String sql = "select * from region where reference = '" + reference + "'";
        Connection conn = DbUtil.getCon();
        Region region = new Region();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		region.setId(rs.getInt("id"));
            	region.setReference(rs.getString("reference"));
            	region.setName(rs.getString("name"));
            	region.setSymbol(rs.getString("symbol"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        if(region.getReference() != null && region.getReference().equals(reference)) {
        	return true;
    	} else {
    		return false;
    	}
    }
	
	/*public boolean addRegion(Region region) throws SQLException {
    	String sql = "insert into Region(id, name) values (?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setInt(1, region.getId());
    		pst.setString(2, region.getName());
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
    
	public boolean modifyRegion(Region region) throws SQLException {
    	String sql = "update region set name = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, region.getName());
    		pst.setInt(2, region.getId());
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
    
	public boolean delRegion(int id) throws SQLException {
    	String sql = "delete from region where id = '" + id + "'";
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
