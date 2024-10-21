<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,com.safehomes.website.programs.Database"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DOCTOR HOME</title>
<style>
         .feature-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
            gap: 50px; 
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
    <%@ include file="doctorheader.jsp" %>
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
			   <h3 class="s">DOCTORS</h3><br>
			   <%
			   	Connection connection=Database.getConnection();
			   PreparedStatement preparedStatement=connection.prepareStatement("SELECT COUNT(*), MAX(doctor_id) FROM doctors WHERE email = ?");
			   	String email=(String)session.getAttribute("email");
			   	preparedStatement.setString(1, email);
			   	ResultSet doctorscount=preparedStatement.executeQuery();
			   	doctorscount.next();
			   %>
			  	<div class="s"><%= doctorscount.getInt(1) %></div>
			</div>		
			
			<div class="feature-box">
			    <h3 class="s">APPOINTMENTS</h3><br>
			   <%
			   	PreparedStatement stmt=connection.prepareStatement("select count(*) from appointments where doctor_id=?");
			   int doctor_id=doctorscount.getInt(2);
			   stmt.setInt(1, doctor_id);
			   ResultSet appointments=stmt.executeQuery();
			   appointments.next();
			   %>
			   <div class="s"><%= appointments.getInt(1) %></div>
			</div>
			
		
        </div>
    </div>
</body>
</html>