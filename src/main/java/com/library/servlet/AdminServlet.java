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

//修改用户密码
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获取当前用户登录信息
        User user = (User) request.getSession().getAttribute("user");
        String opwd = request.getParameter("opwd");
        //判断原密码是否输入正确
        if(!opwd.equals(user.getPassword())) {
        	out.print("<script>alert('原密码不正确，请重新输入！'); window.location='../jsp/admin.jsp'</script>");
			out.flush();
			out.close();
			return;
        }
        user.setPassword(request.getParameter("cpwd"));
        UserDao dao = new UserDao();
        try {
			if(dao.modifyUser(user)) {
				out.print("<script>alert('密码修改成功，请重新登录！'); window.location='../jsp/login.jsp'</script>");
				request.getSession().invalidate();
				out.flush();
				out.close();
				return;
			} else {
				out.print("<script>alert('密码修改失败，请检查！'); window.location='../jsp/login.jsp'</script>");
				request.getSession().invalidate();
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
