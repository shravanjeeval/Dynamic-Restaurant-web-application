package com.tap.daoimpl;

import com.tap.dao.CartDao;
import com.tap.model.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/sepoctdb";
    private static final String DB_USER = "root"; // change if needed
    private static final String DB_PASS = "root"; // change if needed

    @Override
    public boolean addCartItem(CartItem item) {
        String query = "INSERT INTO cart (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, item.getItemId());
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setInt(4, item.getQuantity());

            int i = ps.executeUpdate();
            return i > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateCartItem(CartItem item) {
        String query = "UPDATE cart SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, item.getName());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            ps.setInt(4, item.getItemId());

            int i = ps.executeUpdate();
            return i > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCartItem(int id) {
        String query = "DELETE FROM cart WHERE id = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            int i = ps.executeUpdate();
            return i > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CartItem> getAllCartItems() {
        List<CartItem> list = new ArrayList<>();
        String query = "SELECT * FROM cart";
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CartItem item = new CartItem();
                item.setItemId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getInt("price"));
                item.setQuantity(rs.getInt("quantity"));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
