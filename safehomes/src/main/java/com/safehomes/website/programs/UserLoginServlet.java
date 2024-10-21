package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		Connection connection=Database.getConnection();		
		try {
		if(checkMail(req,connection)) {
			if(checkPass(req,connection)) {
				
				 HttpSession session=req.getSession();
		         session.setAttribute("useremail",req.getParameter("useremail"));
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("userhome.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				 req.setAttribute("message","Entered wrong password");
			     RequestDispatcher dispatcher = req.getRequestDispatcher("userlogin.jsp");
			     dispatcher.forward(req, resp);
			}
		} else {
			 req.setAttribute("message", "Invalid Credentials");
		     RequestDispatcher dispatcher = req.getRequestDispatcher("userlogin.jsp");
		     dispatcher.forward(req, resp);
		}

		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	public static boolean checkMail(HttpServletRequest req,Connection connection) {

		String query="select * from users where email=?";
		String username=req.getParameter("useremail");
		boolean flag=false;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			ResultSet result= preparedStatement.executeQuery();
			flag= result.next();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	public static boolean checkPass(HttpServletRequest req,Connection connection) {

		String email=req.getParameter("useremail");
		String password=req.getParameter("password");
		String pass=null;
		try {
			String query="select * from users where email=?";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet resultSet= preparedStatement.executeQuery();
			resultSet.next();
			pass = resultSet.getString("password");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password.equals(pass);
	}
	
}




