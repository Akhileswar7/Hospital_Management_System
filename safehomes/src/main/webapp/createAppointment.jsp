<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.safehomes.website.programs.Database"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule Appointment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }
        input, select, textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<%@ include file="userHeader.jsp" %>

<div class="container">
    <h2>Schedule an Appointment</h2>

    <%
        String errorMessage = (String) request.getAttribute("message");
        if (errorMessage != null) {
    %>
        <div class="error"><%= errorMessage %></div>
    <%
        }
    %>

    <form action="/safehomes/scheduleAppointment" method="post">

        <div class="form-group">
            <label for="patient_id">Select Patient:</label>
            <select id="patient_id" name="patient_id" required>
            <option>Select patient</option>
                <%
                    try {
                        Connection connection = Database.getConnection();
                        String patientQuery = "SELECT patient_id,name FROM patients where users=?"; 
                        PreparedStatement preparedStatement =connection.prepareStatement(patientQuery);
                        String useremail=(String)session.getAttribute("useremail");
                        preparedStatement.setString(1, useremail);
                        ResultSet resultSet=preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            int patientId = resultSet.getInt("patient_id");
                            String patientName = resultSet.getString("name");
                %>
                    <option value="<%= patientId %>"><%= patientName %></option>
                <%
                        }
                    } catch (Exception e) {
                        out.println("<option value=\"\">Error fetching patients</option>");
                    }
                %>
            </select>
            <a href="addPatient.jsp">add your details</a>
        </div>

        <div class="form-group">
            <label for="doctor_id">Select Doctor:</label>
            <select id="doctor_id" name="doctor_id" required>
            <option>Select Doctor</option>
                <%
                    try {
                        Connection connection = Database.getConnection();
                        String patientQuery = "SELECT doctor_id,full_name,specialist FROM doctors"; 
                        PreparedStatement preparedStatement =connection.prepareStatement(patientQuery);
                        ResultSet resultSet=preparedStatement.executeQuery();

                        while (resultSet.next()) {
                            int doctor_id = resultSet.getInt("doctor_id");
                            String full_name = resultSet.getString("full_name");
                            String specialist = resultSet.getString("specialist");
                %>
                    <option value="<%= doctor_id %>"><%= full_name+"("+specialist+")"%></option>
                <%
                        }
                    } catch (Exception e) {
                        out.println("<option value=\"\">Error fetching patients</option>");
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="appointment_date">Appointment Date:</label>
            <input type="date" id="appointment_date" name="appointment_date" required>
        </div>
        
		<div class="form-group">
		    <label for="appointment_type">Appointment Type:</label>
		    <select id="appointment_type" name="appointment_type" required>
		        <option value="Check-up">Check-up</option>
		        <option value="Surgery">Surgery</option>
		        <option value="Consultation">Consultation</option>
		        <option value="Follow-up">Follow-up</option>
		        <option value="Emergency">Emergency</option>
		        <option value="Telemedicine">Telemedicine</option>
		    </select>
		</div>

        <div class="form-group">
            <label for="symptoms">Symptoms:</label>
            <textarea id="symptoms" name="symptoms" rows="4" placeholder="Describe your symptoms" required></textarea>
        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn">Schedule Appointment</button>
    </form>
</div>

</body>
</html>
