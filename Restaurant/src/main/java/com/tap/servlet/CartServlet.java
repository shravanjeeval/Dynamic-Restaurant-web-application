package com.tap.servlet;

import com.tap.dao.MenuDao;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String action = request.getParameter("action");
      if (action == null) {
          // No action provided, redirect to menu or error page
          response.sendRedirect("Menu.jsp");
          return;
      }

      HttpSession session = request.getSession();
      Cart cart = (Cart) session.getAttribute("cart");

      // Safely get old restaurant ID from session
      Integer oldRestIdObj = (Integer) session.getAttribute("restaurantId");
      int oldRestId = (oldRestIdObj != null) ? oldRestIdObj : -1;

      // Parse new restaurant ID safely from request parameter
      int newRestId;
      try {
          newRestId = Integer.parseInt(request.getParameter("restaurantId"));
      } catch (NumberFormatException e) {
          e.printStackTrace();
          // Redirect or show error page when restaurantId is invalid
          response.sendRedirect("Menu.jsp");
          return;
      }

      // If no cart or restaurant changed, create a new cart
      if (cart == null || oldRestId != newRestId) {
          cart = new Cart();
          session.setAttribute("cart", cart);
          session.setAttribute("restaurantId", newRestId);
      }

      // Perform action based on the parameter
      switch (action) {
          case "add":
              addItemToCart(request, cart);
              break;
          case "update":
              updateCartItem(request, cart);
              break;
          case "remove":
              removeItemFromCart(request, cart);
              break;
          default:
              // Unknown action, redirect to menu page
              response.sendRedirect("Menu.jsp");
              return;
      }

      // After action, redirect to cart page
      response.sendRedirect("Cart.jsp");
  }

private void addItemToCart(HttpServletRequest request, Cart cart) {
    int itemId = Integer.parseInt(request.getParameter("itemId"));
// String name = request.getParameter("name");
// int price = Integer.parseInt(request.getParameter("price"));
int quantity = Integer.parseInt(request.getParameter("quantity"));
    
MenuDao menuDao;
  try {
      menuDao = new MenuDaoImpl();
      Menu menuItem = menuDao.getMenu(itemId);
      System.out.println("The menu in Cart Servlet" + menuItem);
      if(menuItem != null) {
   	CartItem item =new CartItem(
   			menuItem.getMenuId(),
   			menuItem.getRestaurantId(),
   			menuItem.getItemName(),
   			quantity,
   			menuItem.getPrice()
   			);
        
        cart.addItem(item);
      }
    } catch (NumberFormatException | NullPointerException e) {
        e.printStackTrace();
    }
}


private void updateCartItem(HttpServletRequest request, Cart cart) {
	    try {
	        int itemId = Integer.parseInt(request.getParameter("itemId"));
	        int quantity = Integer.parseInt(request.getParameter("quantity"));
	        cart.updateItem(itemId, quantity);
	    } catch (NumberFormatException | NullPointerException e) {
	        e.printStackTrace();
	    }
	}


private void removeItemFromCart(HttpServletRequest request, Cart cart) {
    try {
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        cart.removeItem(itemId);
    } catch (NumberFormatException | NullPointerException e) {
        e.printStackTrace();
    }
}
}