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
    <title>PTIENTS DETAILS</title>
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
<%@ include file="adminHeader.jsp" %>

    <h2>PTIENTS DETAILS</h2>
	<br>
			<%
                String errorMessage = (String) request.getAttribute("message");
                if (errorMessage != null) {
            %>
                <div class="error"><%= errorMessage %></div>
            <%
                }
            %>
    <div class="table-container">
        <table>
            <thead>
            <%
            Connection connection=Database.getConnection();
		   	Statement statement=connection.createStatement();
		   	ResultSet resultSet=statement.executeQuery("select * from patients");
		    ResultSetMetaData metaData = resultSet.getMetaData();
            %>
                <tr>
                <% for(int i=2;i < metaData.getColumnCount();i++) { %>
                    <th><%=  metaData.getColumnName(i) %></th>
                 <% } %>
                 <th>Actions</th>
                </tr>
            </thead>
            <tbody>
              <%
			   	while(resultSet.next()) {
              %>
			  	<tr>
                    <% for(int i=2;i < metaData.getColumnCount();i++) { %>
                    <td><%= resultSet.getString(i) %></td>
                 <% } %>
                    <td>
					    <a href="editPatient.jsp?id=<%= resultSet.getInt(1) %>"><button class="btn">Edit</button></a>
					    <a href="/safehomes/deletePatient?id=<%= resultSet.getInt(1) %>"><button class="btn">Delete</button></a>
					</td>
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
