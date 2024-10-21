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

@WebServlet("/DoctorLogin")
public class DoctorLoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		Connection connection=Database.getConnection();		
		try {
		if(checkMail(req,connection)) {
			if(checkPass(req,connection)) {
				
				 HttpSession session=req.getSession();
		         session.setAttribute("email",req.getParameter("email"));
				RequestDispatcher requestDispatcher=req.getRequestDispatcher("doctorHome.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				 req.setAttribute("message","Entered wrong password");
			     RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
			     dispatcher.forward(req, resp);
			}
		} else {
			 req.setAttribute("message", "Invalid Credentials");
		     RequestDispatcher dispatcher = req.getRequestDispatcher("doctorlogin.jsp");
		     dispatcher.forward(req, resp);
		}

		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	public static boolean checkMail(HttpServletRequest req,Connection connection) {

		String query="select * from doctors where email=?";
		String email=req.getParameter("email");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			ResultSet result= preparedStatement.executeQuery();
			
			return result.next();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}


	public static boolean checkPass(HttpServletRequest req,Connection connection) {

		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String pass=null;
		try {
			String query="select * from doctors where email=?";
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





