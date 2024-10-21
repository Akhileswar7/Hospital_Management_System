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

@WebServlet("/deleteAppointment")
public class DeleteAppointmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int appointmentId = Integer.parseInt(request.getParameter("id"));

        try {
            Connection connection = Database.getConnection();
            String query = "DELETE FROM appointments WHERE appointment_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
            	RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewAppointments.jsp");
                requestDispatcher.forward(request, response);
            } else {
            	request.setAttribute("message", "Failed to delete Appointment");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewAppointments.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception e) {
        	 request.setAttribute("message", "An error occurred while deleting the Appointment");
             RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewAppointments.jsp");
             requestDispatcher.forward(request, response);
        }
    }
}
