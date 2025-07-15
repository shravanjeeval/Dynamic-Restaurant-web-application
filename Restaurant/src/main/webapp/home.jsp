<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.tap.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Home - Top Restaurants</title>
    <link rel="stylesheet" href="stylehome.css">
   
</head>
<body>
    <header>
        <div class="nav-bar">
            <div class="nav-title">
                <h1>FoodDelivery</h1>
            </div>
            <div class="nav-links">
                <a href="home.jsp">Home</a>
                <a href="">Menu</a>
                <a href="#">Offers</a>
                <a href="#">About</a>
                <a href="#">Contact</a>
            </div>
            <div class="nav-actions">
                <a href="login.html">Login</a>
                <a href="index.html">Logout</a>
                <a href="Cart.jsp">Cart</a>
                <div class="search-bar">
                    <form action="searchRestaurant" method="get">
                        <input type="text" name="query" placeholder="Search restaurants...">
                    </form>
                </div>
            </div>
        </div>
    </header>

    <div class="restaurant-container">
        <%
            List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
            if (restaurants != null && !restaurants.isEmpty()) {
                for (Restaurant r : restaurants) {
        %>
       <a href="menu?restaurantId=<%= r.getRestaurantId() %>">

             <div class="restaurant-card">
        <img src="image?id=<%= r.getRestaurantId() %>" alt="<%= r.getName() %>">
        <h2><%= r.getName() %></h2>
        <p>Cuisine: <%= r.getCuisineType() %></p>
        <p>Delivery Time: <%= r.getDeliveryTime() %></p>
        <p>Rating: <%= r.getRating() %></p>
        <button>Order Now</button>
    </div>
            
            </a>
        <%
                }
            } else {
        %>
            <p>No restaurants available.</p>
        <%
            }
        %>
    </div>

    <footer>
        <p>&copy; 2025 Top Restaurants. All rights reserved.</p>
        <p>
            <a href="#">Privacy</a> |
            <a href="#">Terms</a> |
            <a href="#">Help</a>
        </p>
    </footer>
</body>
</html>