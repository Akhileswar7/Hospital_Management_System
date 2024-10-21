package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/userSignup")
public class AddUserServlet extends HttpServlet {
	
	@Override
     public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
        	Connection connection=Database.getConnection();
            String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        	PreparedStatement statement=connection.prepareStatement(query);

            statement = connection.prepareStatement(query);
            statement.setString(1, username); 
            statement.setString(2, email);  
            statement.setString(3, password); 

            int result = statement.executeUpdate();
            if (result > 0) {
            	request.setAttribute("message","Successfully Registered");
			     RequestDispatcher dispatcher = request.getRequestDispatcher("userhome.jsp");
			     dispatcher.forward(request, response);
            } else {
            	request.setAttribute("message","Registration failed. Please try again.");
			     RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
			     dispatcher.forward(request, response);
            }

        } catch (Exception e) {
        	request.setAttribute("message",e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
			dispatcher.forward(request, response);
        } 
    }
}
