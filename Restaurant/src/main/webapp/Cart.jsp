<%@ page import="com.tap.model.Cart, com.tap.model.CartItem" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Your Cart</title>
    <style>
        body { font-family: Arial; padding: 20px; }
        table { width: 80%; margin: auto; border-collapse: collapse; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }
        h1 { text-align: center; color: orange; }
        .btn {
            padding: 8px 15px;
            background-color: orange;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        .btn:hover {
            background-color: darkorange;
        }
        form.inline {
            display: inline;
        }
        input[type=number] {
            width: 50px;
        }
    </style>
</head>
<body>
    <h1>Your Cart</h1>
    <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) {
    %>
        <table>
            <tr>
                <th>Item</th>
                <th>Price (₹)</th>
                <th>Quantity</th>
                <th>Total (₹)</th>
                <th>Actions</th>
            </tr>
            <%
                for (CartItem item : cart.getItems()) {
                	 double  itemTotal = item.getPrice() * item.getQuantity();
            %>
            <tr>
                <td><%= item.getName() %></td>
                <td><%= item.getPrice() %></td>
                <td>
                    <form action="cart" method="get" class="inline">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" required>
                        <input type="submit" value="Update" class="btn">
                    </form>
                </td>
                <td><%= itemTotal %></td>
                <td>
                    <form action="cart" method="get" class="inline">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                        <input type="submit" value="Remove" class="btn">
                    </form>
                </td>
            </tr>
            <% } %>
            <tr>
                <td colspan="3" style="text-align: right;"><strong>Total</strong></td>
                <td colspan="2"><strong>₹<%= cart.getTotalCost() %></strong></td>
            </tr>
        </table>
    <% } else { %>
        <p style="text-align:center;">Your cart is empty.</p>
    <% } %>

    <br><br>
    <%-- Add More Items Button --%>
    <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn">Add More Items</a>

    <br><br>
    <%-- Proceed to Checkout Button --%>
    <%
        if (cart != null && !cart.getItems().isEmpty()) {
    %>
        <form action="checkout.jsp" method="post">
            <input type="submit" value="Proceed to Checkout" class="btn">
        </form>
    <%
        }
    %>

</body>
</html>
