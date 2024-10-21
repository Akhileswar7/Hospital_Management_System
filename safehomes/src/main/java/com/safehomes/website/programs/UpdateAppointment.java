package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateAppointment")
public class UpdateAppointment extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String review = request.getParameter("review");
        String status = request.getParameter("status");
        int appointmentId = Integer.parseInt(idStr);
        try {
            Connection connection = Database.getConnection();
            String sql = "UPDATE appointments SET review = ?, status = ? WHERE appointment_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, review);
            preparedStatement.setString(2, status);
            preparedStatement.setInt(3, appointmentId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                 request.getRequestDispatcher("viewDoctorAppointments.jsp").forward(request, response);
            } else {
            	 request.setAttribute("message", "Failed to schedule the appointment.");
                 request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
       	 request.setAttribute("message", "Failed to schedule the appointment.");
         request.getRequestDispatcher("editAppointment.jsp").forward(request, response);
        }
    }
}
