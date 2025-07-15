package com.tap.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image")
public class ImageServlet extends HttpServlet {
    private RestaurantDaoImpl rdao;

    @Override
    public void init() throws ServletException {
        try {
            rdao = new RestaurantDaoImpl();
        } catch (Exception e) {
            throw new ServletException("Error initializing DAO", e);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Restaurant r = rdao.getRestaurant(id);
        if (r != null && r.getImageData() != null) {
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(r.getImageData());
        }
    }
    
  
}
