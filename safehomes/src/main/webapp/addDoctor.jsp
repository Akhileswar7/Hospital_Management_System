<%@page import="com.safehomes.website.programs.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Registration</title>
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
        .form-group input, .form-group select {
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

    <div class="container">
        <h2>Doctor Registration</h2>
        <form action="/safehomes/adddoctor" method="post">
            <div class="form-group">
                <label for="full_name">Full Name:</label>
                <input type="text" id="full_name" name="full_name" required>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
            </div>

            <div class="form-group">
			    <label for="qualification">Qualification:</label>
			    <select id="qualification" name="qualification" required>
			        <option value="">--Select Qualification--</option>
			        <option value="MBBS">MBBS (Bachelor of Medicine and Bachelor of Surgery)</option>
			        <option value="MD">MD (Doctor of Medicine)</option>
			        <option value="DO">DO (Doctor of Osteopathic Medicine)</option>
			        <option value="MS">MS (Master of Surgery)</option>
			        <option value="DNB">DNB (Diplomate of National Board)</option>
			        <option value="DM">DM (Doctorate of Medicine)</option>
			        <option value="MCh">MCh (Master of Chirurgiae)</option>
			        <option value="BDS">BDS (Bachelor of Dental Surgery)</option>
			        <option value="MDS">MDS (Master of Dental Surgery)</option>
			        <option value="BAMS">BAMS (Bachelor of Ayurvedic Medicine and Surgery)</option>
			        <option value="BHMS">BHMS (Bachelor of Homeopathic Medicine and Surgery)</option>
			        <option value="BUMS">BUMS (Bachelor of Unani Medicine and Surgery)</option>
			        <option value="BPT">BPT (Bachelor of Physiotherapy)</option>
			        <option value="MPT">MPT (Master of Physiotherapy)</option>
			        <option value="PhD">PhD (Doctor of Philosophy in Medicine)</option>
			        <option value="MPH">MPH (Master of Public Health)</option>
			    </select>
			</div>

			<div class="form-group">
			    <label for="specialist">Specialist:</label>
			    <select id="specialist" name="specialist" required>
            <option value="">-- Select an option --</option>
            <%
              
                try {
                   
                    Connection connection= Database.getConnection();

                    String query = "SELECT * FROM specialist";  
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);

                    while (rs.next()) {
                        String speciality = rs.getString(1);        
            %>
                        <option value="<%= speciality %>"><%= speciality %></option> 
            <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            %>
            </select>
			</div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="mobno">Mobile Number:</label>
                <input type="text" id="mobno" name="mobno" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Register</button>
        </form>
    </div>
<%@ include file="homefooter.jsp" %>

</body>
 </html>
