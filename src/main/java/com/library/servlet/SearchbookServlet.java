package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.dao.*;
import com.library.entity.*;

//图书查询界面搜索
public class SearchbookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String content = request.getParameter("content");
        BookDao dao = new BookDao();
        try {
			List<Book> list = dao.getInfo(content);
			if(list.size() == 0) {
				out.print("<script>alert('查询不到相关书籍！'); window.location='../jsp/searchbook.jsp'</script>");
				out.flush();
        		out.close();
        		return;
			} else {
				request.getSession().setAttribute("list", list);
				response.sendRedirect("../jsp/searchbook.jsp");
				return;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
