package com.tap.servlet;



import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepoctdb", "root", "root");

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            PrintWriter out = response.getWriter();

            if (rs.next()) {
                response.sendRedirect("HomeServlet");
                out.print("hi from login");
                
            } else {
                response.getWriter().println("Invalid login credentials.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
