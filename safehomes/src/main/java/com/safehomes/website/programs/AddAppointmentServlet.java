package com.safehomes.website.programs;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/scheduleAppointment")
public class AddAppointmentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("useremail");

        String patientId = request.getParameter("patient_id");
        String doctorId = request.getParameter("doctor_id");
        String appointmentDate = request.getParameter("appointment_date");
        String appointmentType = request.getParameter("appointment_type");
        String symptoms = request.getParameter("symptoms");

        try {
         
            Connection connection = Database.getConnection();
            String insertQuery = "INSERT INTO appointments (patient_id, doctor_id, appointment_date, appointment_type, symptoms, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, Integer.parseInt(patientId));
            preparedStatement.setInt(2, Integer.parseInt(doctorId));
            preparedStatement.setDate(3, java.sql.Date.valueOf(appointmentDate));
            preparedStatement.setString(4, appointmentType);
            preparedStatement.setString(5, symptoms);
            preparedStatement.setString(6, "pending"); 

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                request.setAttribute("message", "Failed to schedule the appointment.");
                request.getRequestDispatcher("userhome.jsp").forward(request, response);

            } else {
                request.setAttribute("message", "Failed to schedule the appointment.");
                request.getRequestDispatcher("createAppointment.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while scheduling the appointment.");
            request.getRequestDispatcher("createAppointment.jsp").forward(request, response);
        } 
    }
}
