package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.*;
import com.library.entity.*;
import com.library.entity.Class;

//图书信息修改
public class ModifybookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        BookDao dao = new BookDao();
        try {
			Book book = new Book();
			book.setId(id);
	        book.setName(request.getParameter("name"));
	        book.setWriter(request.getParameter("writer"));
	        book.setISBN(isbn);
	        if(!dao.getInfo(id).getISBN().equals(isbn)) {
	        	if(dao.isListed(isbn)) {
	        		out.print("<script>alert('ISBN和已有书籍重复了！'); window.location='../jsp/modifybook.jsp?id=" + id + "'</script>");
	        		out.flush();
	        		out.close();
	        		return;
	        	}
	        }
	        book.setId_category(Integer.parseInt(request.getParameter("category")));
	        book.setId_class(Integer.parseInt(request.getParameter("class")));
	        book.setId_region(Integer.parseInt(request.getParameter("region")));
	        book.setId_publisher(Integer.parseInt(request.getParameter("publisher")));
	        //更新小类图书数量信息
	        ClassDao dao1 = new ClassDao();
        	Class Class = dao1.getInfo(dao.getInfo(id).getId_class());
	        if(dao.getInfo(id).getId_category() == book.getId_category() && dao.getInfo(id).getId_class() == book.getId_class()) {
	        	book.setReference(dao.getInfo(id).getReference());
	        } else {
	        	CategoryDao dao2 = new CategoryDao();
	        	String symbol_category = dao2.getInfo(book.getId_category()).getSymbol();
	        	Class = dao1.getInfo(book.getId_class());
	        	String symbol_class = Class.getSymbol();
	        	Class.setCount(Class.getCount() + 1);
	        	int count = Class.getCount();
	        	String temp = String.format("%04d", count);
	        	//更新索书号
	        	String reference = symbol_category + symbol_class + temp;
	        	book.setReference(reference);
	        }
			if(dao.modifyBook(book) && dao1.modifyClass(Class)) {
				out.print("<script>alert('更新书籍信息成功！'); window.location='../jsp/managebook.jsp'</script>");
				out.flush();
				out.close();
				return;
			} else {
				out.print("<script>alert('更新书籍信息失败,请检查！'); window.location='../jsp/modifybook.jsp?id=" + id + "'</script>");
				out.flush();
				out.close();
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
