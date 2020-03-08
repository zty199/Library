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
            	Region Region = new Region();
            	Region.setId(rs.getInt("id"));
            	Region.setName(rs.getString("name"));
                list.add(Region);
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
        String sql = "select * from region where id = '" + id + "'";
        Connection conn = DbUtil.getCon();
        Region Region = new Region();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		Region.setId(rs.getInt("id"));
            	Region.setName(rs.getString("name"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return Region;
    }
	
	/*public boolean addRegion(Region region) throws SQLException {
    	String sql = "insert into Region(id, name) values (?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setInt(1, Region.getId());
    		pst.setString(2, Region.getName());
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
    		pst.setString(1, Region.getName());
    		pst.setInt(2, Region.getId());
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
