package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoImpl implements  MenuDao{


    private static final String DB_URL = "jdbc:mysql://localhost:3306/sepoctdb";
    private static final String DB_USER = "root"; // Change to your DB username
    private static final String DB_PASS = "root"; // Change to your DB password
	
	@Override
	public void addMenu(Menu menu) {
		  String sql = "INSERT INTO menu (menu_id, restaurantId, item_name, description, price, is_available, image_path) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, menu.getMenuId());
	            pstmt.setInt(2, menu.getRestaurantId());
	            pstmt.setString(3, menu.getItemName());
	            pstmt.setString(4, menu.getDescription());
	            pstmt.setDouble(5, menu.getPrice());
	            pstmt.setBoolean(6, menu.isAvailable());
	            pstmt.setBytes(7, menu.getImagePath());          // <-- use setBytes



	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }		
	}

	@Override
	public void updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		   String sql = "UPDATE menu SET restaurantId=?, item_name=?, description=?, price=?, is_available=?, image_path=? WHERE menu_id=?";

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, menu.getRestaurantId());
	            pstmt.setString(2, menu.getItemName());
	            pstmt.setString(3, menu.getDescription());
	            pstmt.setDouble(4, menu.getPrice());
	            pstmt.setBoolean(5, menu.isAvailable());
	            pstmt.setBytes(6, menu.getImagePath());          // <-- use setBytes
	            pstmt.setInt(7, menu.getMenuId());

	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public void deleteMenu(int menuId) {  
		
		   String sql = "DELETE FROM menu WHERE menu_id = ?";

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, menuId);
	            pstmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

	@Override
	public Menu getMenu(int menuId) {
	    String sql = "SELECT * FROM menu WHERE menu_id = ?";
	    Menu menu = null;

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, menuId);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            menu = new Menu(
	                rs.getInt("menu_id"),
	                rs.getInt("restaurantid"),
	                rs.getString("item_name"),
	                rs.getString("description"),
	                rs.getDouble("price"),
	                rs.getBoolean("is_available"),
	                rs.getBytes("image_path")
	            );
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return menu; // ✅ Fix: return the Menu object
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantId) {
	    String sql = "SELECT * FROM menu WHERE restaurantid = ?";
	    List<Menu> menuList = new ArrayList<>();

	    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, restaurantId);
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Menu menu = new Menu(
	                rs.getInt("menu_id"),
	                rs.getInt("restaurantid"),
	                rs.getString("item_name"),
	                rs.getString("description"),
	                rs.getDouble("price"),
	                rs.getBoolean("is_available"),
	                rs.getBytes("image_path")
	            );
	            

	            // Debug print
	            System.out.println("Menu ID: " + menu.getMenuId() +
	                " | Restaurant ID: " + menu.getRestaurantId() +
	                " | Item: " + menu.getItemName() +
	                " | Description: " + menu.getDescription() +
	                " | Price: ₹" + menu.getPrice() +
	                " | Available: " + menu.isAvailable() +
	                " | Image Size: " + menu.getImagePath());

	            menuList.add(menu);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return menuList;
	}

}