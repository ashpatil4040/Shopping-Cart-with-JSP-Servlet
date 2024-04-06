package com.shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.shoppingcart.connection.DbCon;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
		
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
           try {
			UserDao udao = new UserDao(DbCon.getConnection());
			User user = udao.userLogin(email, password);
			
			if (user != null) {
				request.getSession().setAttribute("auth", user); 
				response.sendRedirect("index.jsp");
			} else {
				out.println("there is no user");
			}

		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}

	}

}
}       