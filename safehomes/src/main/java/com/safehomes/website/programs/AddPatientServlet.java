package com.safehomes.website.programs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String dateOfBirth = request.getParameter("date_of_birth");
        String gender = request.getParameter("gender");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        if (name == null || name.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("message", "Name and Email are required.");
            request.getRequestDispatcher("/addPatient.jsp").forward(request, response);
            return;
        }

        try {
            Connection connection=Database.getConnection();
            String sql = "INSERT INTO patients (name, age, date_of_birth, gender, phone_number, email, address,users) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement=connection.prepareCall(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(age));
            preparedStatement.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            preparedStatement.setString(4, gender);
            preparedStatement.setString(5, phoneNumber);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, address);
            HttpSession session=request.getSession();
            String useremail=(String) session.getAttribute("useremail");
            preparedStatement.setString(8, useremail);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                request.setAttribute("message", "Patient added successfully!");
                request.getRequestDispatcher("/createAppointment.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Failed to add patient. Please try again.");
                request.getRequestDispatcher("/addPatient.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("/addPatient.jsp").forward(request, response);
        } 
        
    }
}
