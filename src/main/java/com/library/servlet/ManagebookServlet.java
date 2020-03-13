package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.BookDao;
import com.library.entity.Book;

public class ManagebookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String content = request.getParameter("content");
        BookDao dao = new BookDao();
        List<Book> list = new ArrayList<Book>();
        try {
        	if(content.equals("")) {
            	list = dao.getAllBook();
            } else {
            	list = dao.getAdminInfo(content);
            }
			if(list.size() == 0) {
				out.print("<script>alert('查询不到相关书籍！'); window.location='../jsp/managebook.jsp'</script>");
				out.flush();
        		out.close();
        		return;
			} else {
				request.getSession().setAttribute("list", list);
				response.sendRedirect("../jsp/managebook.jsp");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
