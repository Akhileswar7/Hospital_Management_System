package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.naming.java.javaURLContextFactory;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updatePatient")
public class UpdatePatient extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Get the patient details from the request
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String dob = request.getParameter("date_of_birth");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String status = request.getParameter("status");

        try {
            // Get the connection from your database class
            Connection connection = Database.getConnection();

            // SQL query to update the patient record
            String query = "UPDATE patients SET name=?, date_of_birth=?, gender=?, phone_number=?, email=?, address=?, status=? WHERE patient_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the query
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, java.sql.Date.valueOf(dob));
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, status);
            preparedStatement.setInt(8, id);

            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewPatients.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Failed to Update Patient");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("editPatient.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "An error occurred while updating the patient");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("editPatient.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
