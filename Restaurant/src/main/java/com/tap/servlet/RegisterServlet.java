package com.tap.servlet;

import java.io.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("password"));
        String role = request.getParameter("role");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepoctdb", "root", "root");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user(username, password , email,phone ,address ,role) VALUES (?, ?,?,?,?,?)");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setInt(4, phone);
            stmt.setString(5, address);
            stmt.setString(6, role);
            
            stmt.executeUpdate();

            response.sendRedirect("login.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
