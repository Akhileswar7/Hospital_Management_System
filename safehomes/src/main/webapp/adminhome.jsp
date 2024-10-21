<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.safehomes.website.programs.Database"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SAFE HOMES</title>
   <style>
        .feature-container {
            display: flex;
            justify-content: space-between;
            flex-wrap: wrap; 
            margin-top: 100px;
            padding-left:80px;
            padding-right:80px;
        }
        .feature-box {
           width:500px;
            border: 2px solid #ddd; 
            padding: 25px;
            border-radius: 10px; 
            text-align: center;
            box-shadow: 0px 0px 15px 0px #ccc; 
            margin-bottom: 20px; 
            font-size: 18px; 
        }
        .feature-box h3 {
            margin-bottom: 15px;
            color: #333;
            font-size: 24px; 
        }
        .feature-box p {
            color: #555;
            font-size: 16px; 
        }
        .container h2 {
            font-size: 32px;
            text-align: center;
        }
        .s{
        	text-decoration:none;
        	font-size:30px;
        	 color: #333;
        }
       .error{
       text-align:center;
       font-size:22px;
       margin-top:10px;
       }
    </style>
</head>
<body>
<%@ include file="adminHeader.jsp" %>
			 <%
			 
                String errorMessage = (String) request.getAttribute("message");
                if (errorMessage != null) {
            %>
                <div class="error"><%= errorMessage %></div>
            <%
                }
            %>
<div class="container">
        <div class="feature-container">
         
			<div class="feature-box">
			   <a href="addDoctor.jsp" class="s">DOCTORS</a><br>
			   <%
			   	Connection connection=Database.getConnection();
			   	Statement statement=connection.createStatement();
			   	ResultSet doctorscount=statement.executeQuery("select count(*) from doctors");
			   	doctorscount.next();
			   %>
			  	<div class="s"><%= doctorscount.getInt(1) %></div>
			</div>		
			<div class="feature-box">
			  <h3 class="s">PATIENTS</h3>
			    <%
			   	ResultSet patienscount=statement.executeQuery("select count(*) from patients");
			   	patienscount.next();
			   %>
			  	<div class="s"><%= patienscount.getInt(1) %></div>
			</div>
			<div class="feature-box">
			   <h3 class="s">APPOINTMENTS</h3>
			   <%
			   	ResultSet appointments=statement.executeQuery("select count(*) from appointments");
			   appointments.next();
			   %>
			   <div class="s"><%= appointments.getInt(1) %></div>
			</div>
			<div class="feature-box">
			    <a href="addSpecialist.jsp" class="s">SPECIALISTS</a><br>
			     <%
			   	ResultSet specialist=statement.executeQuery("select count(*) from specialist");
			     specialist.next();
			   %>
			  	<div class="s"><%= specialist.getInt(1) %></div>
			</div>
		
        </div>
    </div>
<%@ include file="homefooter.jsp" %>
</body>
</html>