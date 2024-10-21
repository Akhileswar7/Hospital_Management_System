<%@page import="java.sql.*"%>
<%@page import="com.safehomes.website.programs.Database"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Patient</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .container h2 {
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input, .form-group select, .form-group textarea {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #fff;
            color: #333;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<%@ include file="adminHeader.jsp" %>

<%
    int patientId = Integer.parseInt(request.getParameter("id"));
    String name = "", dateOfBirth = "", gender = "", phoneNumber = "", email = "", address = "", status = "";

    try {
        Connection connection = Database.getConnection();
        String query = "SELECT * FROM patients WHERE patient_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, patientId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            name = resultSet.getString("name");
            dateOfBirth = resultSet.getString("date_of_birth");
            gender = resultSet.getString("gender");
            phoneNumber = resultSet.getString("phone_number");
            email = resultSet.getString("email");
            address = resultSet.getString("address");
            status = resultSet.getString("status");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<div class="container">
    <h2>Edit Patient Details</h2>
    <form action="/safehomes/updatePatient" method="post">
        <input type="hidden" name="id" value="<%= patientId %>"> <!-- Hidden field for patient ID -->

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>
        </div>

        <div class="form-group">
            <label for="date_of_birth">Date of Birth:</label>
            <input type="date" id="date_of_birth" name="date_of_birth" value="<%= dateOfBirth %>" required>
        </div>

        <div class="form-group">
            <label for="gender">Gender:</label>
            <select id="gender" name="gender">
                <option value="<%= gender %>" selected><%= gender %></option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
        </div>

        <div class="form-group">
            <label for="phone_number">Phone Number:</label>
            <input type="text" id="phone_number" name="phone_number" value="<%= phoneNumber %>">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= email %>" required>
        </div>

        <div class="form-group">
            <label for="address">Address:</label>
            <textarea id="address" name="address"><%= address %></textarea>
        </div>

        <div class="form-group">
            <label for="status">Status:</label>
            <select id="status" name="status">
                <option value="<%= status %>" selected><%= status %></option>
                <option value="Admitted">Admitted</option>
                <option value="Discharged">Discharged</option>
                <option value="Under Observation">Under Observation</option>
                <option value="In Surgery">In Surgery</option>
                <option value="Transferred">Transferred</option>
                <option value="Deceased">Deceased</option>
                <option value="Pending">Pending</option>
            </select>
        </div>

        <button type="submit" class="btn">Update Patient</button>
    </form>
</div>

<%@ include file="homefooter.jsp" %>

</body>
</html>
