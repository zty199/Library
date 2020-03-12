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
import com.library.entity.Class;

public class CascadeCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        ClassDao dao = new ClassDao();
		try {
			List<Class> list = dao.getAllClass(id);
			StringBuffer str = new StringBuffer();
	        for(int i = 0; i < list.size(); i++) {
	            str.append(list.get(i).getId()).append("=").append(list.get(i).getName());
	            if(i != list.size() - 1) {
	                str.append(",");
	            }
	        }
	        //servlet不转向或重定向到任何页面，使用response.getWriter().print()方法可以把文本响应给XMLHttpRequest对象
	        PrintWriter out = response.getWriter();
	        out.print(str.toString());
	        out.flush();
	        out.close();
	        return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
