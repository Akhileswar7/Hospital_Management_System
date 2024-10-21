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
            margin-top: 40px;
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
    </style>
</head>
<body>
<%@ include file="homeHeader.jsp" %>
<div class="container">
        <h2 style="text-align: center; margin-top: 20px;">Key Features of our Hospital</h2>
        <div class="feature-container">
           <div class="feature-box">
    <h3>100% Safety</h3>
    <p>We prioritize patient safety by adhering to the highest medical standards and protocols, ensuring a secure environment for every procedure and treatment.</p>
</div>
<div class="feature-box">
    <h3>Clean Environment</h3>
    <p>Our facility maintains strict hygiene and sanitation measures to provide a clean and sterile environment, ensuring the well-being of both patients and staff.</p>
</div>
<div class="feature-box">
    <h3>Friendly Doctors</h3>
    <p>Our team of compassionate and experienced doctors is dedicated to offering personalized care, ensuring that every patient feels comfortable and well-cared for.</p>
</div>
<div class="feature-box">
    <h3>Medical Research</h3>
    <p>We are committed to advancing medical knowledge through continuous research, helping to develop innovative treatments and improve patient outcomes.</p>
</div>
        </div>
    </div>
<%@ include file="homefooter.jsp" %>
</body>
</html>