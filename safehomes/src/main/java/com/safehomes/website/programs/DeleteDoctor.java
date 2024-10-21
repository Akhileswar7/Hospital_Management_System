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

@WebServlet("/deleteDoctor")
public class DeleteDoctor extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection connection= Database.getConnection();
			String query= "delete from doctors where doctor_id=?";
			PreparedStatement preparedStatement =connection.prepareStatement(query);
			int id=Integer.parseInt(request.getParameter("id"));
			preparedStatement.setInt(1, id);
			
			int result= preparedStatement.executeUpdate();
			
			if (result > 0) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewDoctors.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Failed to delete Doctor");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewDoctors.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while deleting the Doctor");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewDoctors.jsp");
            requestDispatcher.forward(request, response);
        }
	}
}
