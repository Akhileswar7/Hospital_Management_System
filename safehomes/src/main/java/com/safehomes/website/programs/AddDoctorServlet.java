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

@WebServlet("/adddoctor")
public class AddDoctorServlet extends  HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	
			String fullName = request.getParameter("full_name");
	        String dob = request.getParameter("dob");
	        String qualification = request.getParameter("qualification");
	        String specialist = request.getParameter("specialist");
	        String email = request.getParameter("email");
	        String mobno = request.getParameter("mobno");
	        String password = request.getParameter("password");
	        
	            try {
	                Connection connection=Database.getConnection();
	                String checkEmailQuery = "SELECT COUNT(*) FROM doctors WHERE email = ?";
	                PreparedStatement checkEmailStmt = connection.prepareStatement(checkEmailQuery);
	                checkEmailStmt.setString(1, email);
	                ResultSet rs = checkEmailStmt.executeQuery();
	                rs.next();
	                int emailCount = rs.getInt(1);

	                if (emailCount > 0) {
	                    request.setAttribute("message", "Email already exists. Please use a different email.");
	                    RequestDispatcher dispatcher = request.getRequestDispatcher("adminlogin.jsp");
	                    dispatcher.forward(request, response);
	                } else {
	                    
	                String query = "INSERT INTO doctors (full_name, dob, qualification, specialist, email, mobno, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
	                PreparedStatement stmt = connection.prepareStatement(query);
	                
	                
	                stmt.setString(1, fullName);
	                stmt.setString(2, dob);
	                stmt.setString(3, qualification);
	                stmt.setString(4, specialist);
	                stmt.setString(5, email);
	                stmt.setString(6, mobno);
	                stmt.setString(7, password);
	                
	                int rows = stmt.executeUpdate();
	                
	                if(rows > 0) {
	                	 request.setAttribute("message","Successfully Registered");
	    			     RequestDispatcher dispatcher = request.getRequestDispatcher("adminhome.jsp");
	    			     dispatcher.forward(request, response);
	                } else {
	                	request.setAttribute("message","Registration failed. Please try again.");
	    			     RequestDispatcher dispatcher = request.getRequestDispatcher("adminhome.jsp");
	    			     dispatcher.forward(request, response);
	                }

	                stmt.close();
	                connection.close();
	            } 
	            }catch (Exception e) {
	            	request.setAttribute("message",e.getMessage());
   			     RequestDispatcher dispatcher = request.getRequestDispatcher("adminhome.jsp");
   			     dispatcher.forward(request, response);
	            }
	}
}
