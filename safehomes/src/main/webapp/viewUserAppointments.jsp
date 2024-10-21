<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="com.safehomes.website.programs.Database"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DOCTOR DETAILS</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h2{
        	text-align:center;
        }

        .table-container {
            padding-left: 5%;
            padding-right:5%;
            margin: 0 auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #a0a0a0;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
		    display: inline-block; 
		    padding: 12px 20px; 
		    font-size: 16px; 
		    font-weight: bold; 
		    color: #fff;
		    background-color: #007bff; 
		    border: none; 
		    border-radius: 5px; 
		    cursor: pointer; 
		    text-align: center; 
		    text-decoration: none;
		}
    </style>
</head>
<body>
<%@ include file="userHeader.jsp" %>

    <h2>APPIOINTMENT DETAILS</h2>
	<br>
    <div class="table-container">
        <table>
            <thead>
            <%
            Connection connection=Database.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("select * from appointments where patient_id in (select patient_id from patients where users=?)");
		   	String email=(String) session.getAttribute("useremail"); 
		   	preparedStatement.setString(1, email);
		   	ResultSet resultSet=preparedStatement.executeQuery();
		    ResultSetMetaData metaData = resultSet.getMetaData();
            %>
                <tr>
                <% for(int i=4;i <= metaData.getColumnCount();i++) { %>
                    <th><%=  metaData.getColumnName(i) %></th>
                 <% } %>
               </tr>
            </thead>
            <tbody>
              <%
			   	while(resultSet.next()) {
              %>
			  	<tr>
                    <% for(int i=4;i <= metaData.getColumnCount();i++) { %>
                    <td><%= resultSet.getString(i) %></td>
                 <% } %>
                </tr>	
                <% 
                	}
			   	%>   
            </tbody>
        </table>
    </div>
<%@ include file="homefooter.jsp" %>

</body>
</html>
