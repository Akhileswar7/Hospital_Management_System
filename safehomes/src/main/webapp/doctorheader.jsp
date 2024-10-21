<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
        body {
            font-family: Arial, sans-serif;
        }
        .navbar {
            background-color: #3cb371;
            padding: 10px;
            height:70px;
        }
        .navbar a {
            float: left;
            padding: 14px 20px;
            text-decoration: none;
            color: white;
            text-align: center;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
        .navbar-right {
            float: right;
        }
    </style>
</head>
<body>
<div class="navbar">
        <h2><a href="doctorHome.jsp"> SAFE HOMES</a></h2>
         <a href="viewDoctorAppointments.jsp">VIEW APPOINTMENTS</a>
         <div class="navbar-right">
         <a href="/safehomes/doctorlogout">LOGOUT</a>
         </div>
         
         
    </div>
</body>
</html>