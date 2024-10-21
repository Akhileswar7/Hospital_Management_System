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

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection connection = Database.getConnection();
            String query = "DELETE FROM patients WHERE patient_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewPatients.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Failed to delete patient");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewPatients.jsp");
                requestDispatcher.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred while deleting the patient");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewPatients.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
