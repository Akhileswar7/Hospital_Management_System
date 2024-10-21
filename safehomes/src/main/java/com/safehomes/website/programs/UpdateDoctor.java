package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.catalina.startup.FailedContext;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateDoctor")
public class UpdateDoctor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		int id=Integer.parseInt(request.getParameter("id"));
		String fullName = request.getParameter("full_name");
        String dob = request.getParameter("dob");
        String qualification = request.getParameter("qualification");
        String specialist = request.getParameter("specialist");
        String email = request.getParameter("email");
        String mobno = request.getParameter("mobno");
		try {
			Connection connection=Database.getConnection();
			String query="update doctors set full_name=?,dob=?,qualification=?,specialist=?,email=?,mobno=? where doctor_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, dob);
            preparedStatement.setString(3, qualification);
            preparedStatement.setString(4, specialist);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, mobno);
            preparedStatement.setInt(7, id);
			int result= preparedStatement.executeUpdate();
			if(result>0) {
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("viewDoctors.jsp");
				requestDispatcher.forward(request, resp);
			} else {
				request.setAttribute("message", "Failed to Update");
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("viewDoctors.jsp");
				requestDispatcher.forward(request, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
