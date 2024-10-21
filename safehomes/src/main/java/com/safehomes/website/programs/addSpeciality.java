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

@WebServlet("/addSpeciality")
public class addSpeciality extends HttpServlet {
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String speciality = req.getParameter("speciality");
		try (Connection connection = Database.getConnection()) {
			
			String checkQuery = "SELECT COUNT(*) FROM specialist WHERE speciality = ?";
			PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
			checkStatement.setString(1, speciality);
			ResultSet resultSet = checkStatement.executeQuery();
			resultSet.next();
			int count = resultSet.getInt(1);

			if (count > 0) {
				
				req.setAttribute("message", "Speciality already exists.");
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminhome.jsp");
				requestDispatcher.forward(req, resp);
			} else {
				
				String insertQuery = "INSERT INTO specialist (speciality) VALUES (?)"; // Ensure the column name is correct
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
				preparedStatement.setString(1, speciality);
				int result = preparedStatement.executeUpdate();
				
				if (result > 0) {
					req.setAttribute("message", "Speciality added successfully.");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminhome.jsp");
					requestDispatcher.forward(req, resp);
				} else {
					req.setAttribute("error", "Failed to add speciality.");
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminhome.jsp");
					requestDispatcher.forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "An error occurred while processing your request.");
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("adminhome.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
}
