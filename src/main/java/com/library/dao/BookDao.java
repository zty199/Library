package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.library.entity.*;

public class BookDao {

	//获取全部图书列表
	public List<Book> getAllBook() throws SQLException {
        List<Book> list = new ArrayList<Book>();
        String sql = "select * from book";
        Connection conn = DbUtil.getCon();        
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
            	Book book = new Book();
        		book.setId(rs.getInt("id"));
        		book.setISBN(rs.getString("ISBN"));
        		book.setReference(rs.getString("reference"));
        		book.setName(rs.getString("name"));
        		book.setWriter(rs.getString("writer"));
        		book.setDate(rs.getTimestamp("date"));
        		book.setId_region(rs.getInt("id_region"));
        		book.setId_publisher(rs.getInt("id_publisher"));
        		book.setId_category(rs.getInt("id_category"));
        		book.setId_class(rs.getInt("id_class"));
        		list.add(book);
            }
            rs.close();
        	pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return list;
    }
	
	//根据搜索信息返回相关图书
	public List<Book> getInfo(String temp) throws SQLException {
		List<Book> list = new ArrayList<Book>();
        String sql = "select * from book where ISBN = '" + temp + "' or name = '" + temp + "' or reference = '" + temp + "' or writer = '" + temp + "'";
        Connection conn = DbUtil.getCon();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		Book book = new Book();
        		book.setId(rs.getInt("id"));
        		book.setISBN(rs.getString("ISBN"));
        		book.setReference(rs.getString("reference"));
        		book.setName(rs.getString("name"));
        		book.setWriter(rs.getString("writer"));
        		book.setDate(rs.getTimestamp("date"));
        		book.setId_region(rs.getInt("id_region"));
        		book.setId_publisher(rs.getInt("id_publisher"));
        		book.setId_category(rs.getInt("id_category"));
        		book.setId_class(rs.getInt("id_class"));
        		list.add(book);
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return list;
    }
	
	//根据搜索信息返回相关图书（包括图书ID）
	public List<Book> getAdminInfo(String temp) throws SQLException {
		List<Book> list = new ArrayList<Book>();
        String sql = "select * from book where id = '" + temp + "' or ISBN = '" + temp + "' or name = '" + temp + "' or reference = '" + temp + "' or writer = '" + temp + "'";
        Connection conn = DbUtil.getCon();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		Book book = new Book();
        		book.setId(rs.getInt("id"));
        		book.setISBN(rs.getString("ISBN"));
        		book.setReference(rs.getString("reference"));
        		book.setName(rs.getString("name"));
        		book.setWriter(rs.getString("writer"));
        		book.setDate(rs.getTimestamp("date"));
        		book.setId_region(rs.getInt("id_region"));
        		book.setId_publisher(rs.getInt("id_publisher"));
        		book.setId_category(rs.getInt("id_category"));
        		book.setId_class(rs.getInt("id_class"));
        		list.add(book);
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return list;
    }
	
	//根据图书ID获取指定图书信息
	public Book getInfo(int id) throws SQLException {
        String sql = "select * from book where id = " + id;
        Connection conn = DbUtil.getCon();
        Book book = new Book();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		book.setId(rs.getInt("id"));
        		book.setISBN(rs.getString("ISBN"));
        		book.setReference(rs.getString("reference"));
        		book.setName(rs.getString("name"));
        		book.setWriter(rs.getString("writer"));
        		book.setDate(rs.getTimestamp("date"));
        		book.setId_region(rs.getInt("id_region"));
        		book.setId_publisher(rs.getInt("id_publisher"));
        		book.setId_category(rs.getInt("id_category"));
        		book.setId_class(rs.getInt("id_class"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        return book;
    }
	
	//判断录入图书是否已在列表
	public boolean isListed(String isbn) throws SQLException {
        String sql = "select * from book where ISBN = '" + isbn +"'";
        Connection conn = DbUtil.getCon();
        Book book = new Book();
        try {
        	PreparedStatement pst = conn.prepareStatement(sql);
        	ResultSet rs = pst.executeQuery();
        	while(rs.next()) {
        		book.setId(rs.getInt("id"));
        		book.setISBN(rs.getString("ISBN"));
        		book.setReference(rs.getString("reference"));
        		book.setName(rs.getString("name"));
        		book.setWriter(rs.getString("writer"));
        		book.setDate(rs.getTimestamp("date"));
        		book.setId_region(rs.getInt("id_region"));
        		book.setId_publisher(rs.getInt("id_publisher"));
        		book.setId_category(rs.getInt("id_category"));
        		book.setId_class(rs.getInt("id_class"));
        	}
        	rs.close();
        	pst.close();
        } catch (SQLException e) {
    		e.printStackTrace();
    	}
        conn.close();
        if(book.getISBN() != null && book.getISBN().equals(isbn)) {
        	return true;
    	} else {
    		return false;
    	}
    }
	
	//添加图书
	public boolean addBook(Book book) throws SQLException {
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	String sql = "insert into book(ISBN, reference, name, writer, date, id_region, id_publisher, id_category, id_class) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, book.getISBN());
    		pst.setString(2, book.getReference());
    		pst.setString(3, book.getName());
    		pst.setString(4, book.getWriter());
    		pst.setTimestamp(5, timestamp);
    		pst.setInt(6, book.getId_region());
    		pst.setInt(7, book.getId_publisher());
    		pst.setInt(8, book.getId_category());
    		pst.setInt(9, book.getId_class());
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
	
	//修改图书信息
	public boolean modifyBook(Book book) throws SQLException {
    	String sql = "update book set ISBN = ?, reference = ?, name = ?, writer = ?, id_region = ?, id_publisher = ?, id_category = ?, id_class = ? where id = ?";
    	Connection conn = DbUtil.getCon();
    	try {			
    		PreparedStatement pst = conn.prepareStatement(sql);
    		pst.setString(1, book.getISBN());
    		pst.setString(2, book.getReference());
    		pst.setString(3, book.getName());
    		pst.setString(4, book.getWriter());
    		pst.setInt(5, book.getId_region());
    		pst.setInt(6, book.getId_publisher());
    		pst.setInt(7, book.getId_category());
    		pst.setInt(8, book.getId_class());
    		pst.setInt(9, book.getId());
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
	
	//删除图书
	public boolean delBook(int id) throws SQLException {
    	String sql = "delete from book where id = '" + id + "'";
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
    }
}
