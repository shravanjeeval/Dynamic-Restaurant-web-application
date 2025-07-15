package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String ridParam = req.getParameter("restaurantId");

	    // Check if restaurantId is missing
	    if (ridParam == null || ridParam.trim().isEmpty()) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty restaurantId parameter");
	        return;
	    }

	    int restaurantId;
	    try {
	        restaurantId = Integer.parseInt(ridParam);
	    } catch (NumberFormatException e) {
	        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurantId format");
	        return;
	    }

	    try {
	        MenuDaoImpl menuDao = new MenuDaoImpl();
	        List<Menu> allMenuByRestaurant = menuDao.getAllMenuByRestaurant(restaurantId);

	        req.setAttribute("menuList", allMenuByRestaurant);
	        req.getRequestDispatcher("Menu.jsp").forward(req, resp);

	    } catch (Exception e) {
	        e.printStackTrace();
	        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load menu items.");
	    }
	}

	}



