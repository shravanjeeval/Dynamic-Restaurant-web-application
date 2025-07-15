<%@page import="java.util.concurrent.TimeUnit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.tap.model.Menu" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu Items</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 30px;
        }
        body h1{
        text-align: center;
        color: orange;
        
        }
        .menu-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .menu-item {
            width: 300px;
            background-color: #fff;
            border-radius: 12px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            overflow: hidden;
            padding: 16px;
        }
        .menu-item img {
            width: 100%;
            height: 200px;
            object-fit: cover;
            border-radius: 10px;
            margin-bottom: 12px;
        }
        .menu-item h3 {
            margin: 0 0 10px;
            font-size: 20px;
            color: #333;
        }
        .menu-item p {
            margin: 4px 0;
            color: #555;
        }
        .menu-item .price {
            font-weight: bold;
            color: #e91e63;
        }
        .menu-item button {
            margin-top: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 16px;
            border-radius: 8px;
            cursor: pointer;
        }
        .menu-item button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h1>Menu</h1>
     <div class="menu-container">
   
   <%
    List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
    if (menuList != null && !menuList.isEmpty()) {
        for (Menu menuItem : menuList) {
%>
    <div class="menu-item">
        <img src="<%= menuItem.getImagePath() %>" alt="Menu Image">
        <h3>Item : <%= menuItem.getItemName() %></h3>
        <p>Description: <%= menuItem.getDescription() %></p>
        <p class="price">₹Price :<%= menuItem.getPrice() %></p>

        <form action="cart" >
            <input type="hidden" name="itemId" value="<%= menuItem.getMenuId() %>">
            Quantity:
            <input type="number" name="quantity" value="1" min="1" class="quantity-input" required>
            <input type="hidden" name="name" value="<%= menuItem.getItemName() %>">
            <input type="hidden" name="price" value="<%= menuItem.getPrice() %>">
            <input type="hidden" name="restaurantId" value="<%= menuItem.getRestaurantId() %>">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add To Cart" class="add-to-cart-btn">
        </form>
    </div>
<%
        } // end for
    } else {
%>
    <p>No menu items available.</p>
<%
    } // end if
%>

    </div>
</body>
</html>
