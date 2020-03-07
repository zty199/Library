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

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        UserDao dao = new UserDao();
        try {
			if(dao.isValid(user.getUsername(), user.getPassword())) {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("../jsp/index.jsp");
			} else {
				PrintWriter out = response.getWriter();
				out.print("<script>alert('用户名或密码错误！'); window.location='../jsp/login.jsp'</script>");
				out.flush();
				out.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
