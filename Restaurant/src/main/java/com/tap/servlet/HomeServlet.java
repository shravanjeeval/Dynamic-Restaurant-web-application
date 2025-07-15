package com.tap.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            RestaurantDaoImpl rdao = new RestaurantDaoImpl();
            List<Restaurant> allRestaurants = rdao.getAllRestaurant();
            
          

            req.setAttribute("restaurants", allRestaurants);		
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, resp);	

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error loading restaurants: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load restaurants.");
        }
    }
}
