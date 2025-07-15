package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {
	
	private Connection connection ;
	public  RestaurantDaoImpl() throws ClassNotFoundException , SQLException{
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/sepoctdb", "root", "root");
     
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {
		
		String INSERT = "INSERT into restaurant(name , cuisinetype , deliveryTime,address ,adminuserId ,"
				+ "rating , id , image) values(?,?,?,?,?,?,?,?) ";

        try ( PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
           
           
            stmt.setString(1, restaurant.getName());
            stmt.setString(2, restaurant.getCuisineType());
            stmt.setInt(3, restaurant.getDeliveryTime());
            stmt.setString(4, restaurant.getAddress());
            stmt.setInt(5, restaurant.getAdminUserId());
            stmt.setDouble(6, restaurant.getRating());
         // remove this line if 'id' is auto-generated  
           stmt.setDouble(7, restaurant.getRestaurantId());
          
            // ✅ Read image as InputStream and set as Blob
            if (restaurant.getImageData() != null) {
                stmt.setBytes(8, restaurant.getImageData());
            } else {
                stmt.setNull(8, java.sql.Types.BLOB);
            }
              
            
          
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
            	throw new SQLException("Inserting restaurant failed , no row affected");
            }
     
        } catch (Exception e) {
           throw new RuntimeException("Error Inserting restaurant",e); 
        }
	     
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		 String query = "UPDATE restaurant SET name = ?, cuisineType = ?, address = ?, rating = ?, image = ? WHERE id = ?";
           
    try (PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setString(1, restaurant.getName());
		        ps.setString(2, restaurant.getCuisineType());
		        ps.setString(3, restaurant.getAddress());
		        ps.setDouble(4, restaurant.getRating());
		        ps.setInt(6, restaurant.getRestaurantId());
		        ps.setBytes(5, restaurant.getImageData());// ✅ setting the image path

		        int updatedRows = ps.executeUpdate();

		        if (updatedRows > 0) {
		            System.out.println("Restaurant with ID " + restaurant.getRestaurantId() + " updated successfully.");
		        } else {
		            System.out.println("No restaurant found with ID " + restaurant.getRestaurantId());
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("Error updating restaurant", e);
		    }
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		 String query = "DELETE FROM restaurant WHERE id = ?";

		    try (PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setInt(1, restaurantId);

		        int rowsDeleted = ps.executeUpdate();

		        if (rowsDeleted > 0) {
		            System.out.println("Restaurant with ID " + restaurantId + " deleted successfully.");
		        } else {
		            System.out.println("No restaurant found with ID " + restaurantId);
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("Error deleting restaurant", e);
		    }
		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		   Restaurant restaurant = null;
		    
		    String query = "SELECT * FROM restaurant WHERE id = ?";
   
		    try (PreparedStatement ps = connection.prepareStatement(query)) {
		        ps.setInt(1, restaurantId);

		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            restaurant = new Restaurant();
		            restaurant.setRestaurantId(rs.getInt("id"));
		            restaurant.setName(rs.getString("name"));
		            restaurant.setCuisineType(rs.getString("cuisineType"));
		            restaurant.setAddress(rs.getString("address"));
		            restaurant.setRating(rs.getDouble("rating"));
		            restaurant.setImageData(rs.getBytes("image")); 
		            restaurant.setDeliveryTime(rs.getInt("deliveryTime"));

		            // Debug print
		            System.out.println("Found Restaurant ID: " + restaurant.getRestaurantId() +
		                " Name: " + restaurant.getName() +
		                " Cuisine Type: " + restaurant.getCuisineType() +
		                " Address: " + restaurant.getAddress() +
		                " Rating: " + restaurant.getRating()+
		                " Delivery Time: "+  restaurant.getDeliveryTime());
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("Error fetching restaurant by ID", e);
		    }

		    return restaurant; 
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		 List<Restaurant> restaurants = new ArrayList<>();
		
		String select = "SELECT * from restaurant";
		

	    try (Statement stmt = connection.createStatement()) {
	        ResultSet rs = stmt.executeQuery(select);

	        while (rs.next()) {
	            Restaurant restaurant = new Restaurant();

	            restaurant.setRestaurantId(rs.getInt("id"));
	            restaurant.setName(rs.getString("name"));
	            restaurant.setCuisineType(rs.getString("cuisineType"));
	            restaurant.setAddress(rs.getString("address"));
	            restaurant.setRating(rs.getDouble("rating"));
	            restaurant.setImageData(rs.getBytes("image"));
	            restaurant.setDeliveryTime(rs.getInt("deliveryTime")); // fetch BLOB

	            // Debug print
	            System.out.println("Restaurant ID: " + restaurant.getRestaurantId() +
	                " Name: " + restaurant.getName() +
	                " Cuisine Type: " + restaurant.getCuisineType() +
	                " Address: " + restaurant.getAddress() +
	                " Delivery Time: "+  restaurant.getDeliveryTime() +
	                " Rating: " + restaurant.getRating() +
	                "image " + restaurant.getImageData());

	            // ✅ THIS LINE WAS MISSING
	            restaurants.add(restaurant);
	        }
	     
	        } catch (Exception e) {
	           throw new RuntimeException("Error Inserting restaurant",e); 
	           
	        }
		return restaurants;
		
	     
	}
		


	}
	

