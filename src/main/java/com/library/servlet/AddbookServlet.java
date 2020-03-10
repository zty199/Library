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

public class AddbookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String isbn = request.getParameter("isbn");
        BookDao dao = new BookDao();
        try {
        	if(dao.isListed(isbn)) {
        		out.print("<script>alert('这本书已经录入过了！'); window.location='../jsp/addbook.jsp'</script>");
        		out.flush();
        		out.close();
        		return;
        	}
        	String[] array = isbn.split("-");
        	String region = array[0] + array[1];
        	String publisher = array[0] + array[1] + array[2];
        	RegionDao dao1 = new RegionDao();
        	PublisherDao dao2 = new PublisherDao();
        	int id_region = 0;
        	int id_publisher = 0;
			if(dao1.isListed(region) && dao2.isListed(publisher)) {
				id_region = dao1.getInfo(region).getId();
				id_publisher = dao2.getInfo(publisher).getId();
			} else {
				if(!dao1.isListed(region)) {
					out.print("<script>alert('没有找到国家地区及出版社信息，录入后请及时修改！'); window.location='../jsp/addbook.jsp'</script>");
					out.flush();
					out.close();
				} else {
					if(!dao2.isListed(publisher)) {
						id_region = dao1.getInfo(region).getId();
						out.print("<script>alert('没有找到出版社信息，录入后请及时修改！'); window.location='../jsp/addbook.jsp'</script>");
						out.flush();
						out.close();
					}
				}
			}
			Book book = new Book();
	        book.setName(request.getParameter("name"));
	        book.setWriter(request.getParameter("writer"));
	        book.setISBN(isbn);
	        book.setId_category(Integer.parseInt(request.getParameter("category")));
	        book.setId_class(Integer.parseInt(request.getParameter("class")));
	        book.setId_region(id_region);
	        book.setId_publisher(id_publisher);
	        CategoryDao dao3 = new CategoryDao();
	        String symbol_category = dao3.getInfo(book.getId_category()).getSymbol();
	        ClassDao dao4 = new ClassDao();
	        Class Class = dao4.getInfo(book.getId_class());
	        String symbol_class = Class.getSymbol();
	        Class.setCount(Class.getCount() + 1);
	        int count = Class.getCount();
	        String temp = String.format("%04d", count);
	        String reference = symbol_category + symbol_class + temp;
	        book.setReference(reference);
			if(dao.addBook(book) && dao4.modifyClass(Class)) {
				out.print("<script>alert('添加书籍成功！'); window.location='../jsp/index.jsp'</script>");
				out.flush();
				out.close();
				return;
			} else {
				out.print("<script>alert('添加书籍失败,请检查数据库！'); window.location='../jsp/addbook.jsp'</script>");
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
