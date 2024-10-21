package com.safehomes.website.programs;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/userlogout")
public class UserLogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session= req.getSession();
		session.invalidate();
		
		req.setAttribute("message", "Successfully Logged out.");
		RequestDispatcher requestDispatcher=req.getRequestDispatcher("userlogin.jsp");
		requestDispatcher.forward(req, resp);
	}
}