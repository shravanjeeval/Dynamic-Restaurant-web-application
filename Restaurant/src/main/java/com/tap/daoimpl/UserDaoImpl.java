package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {
	Scanner scan  = new Scanner(System.in);
	// TODO Auto-generated method stub
    private final static 	  String url = "jdbc:mysql://localhost:3306/sepoctdb";
    private final static   String username = "root";
    private final static String password = "root";

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		
		
		String INSERT = "INSERT into `user`(`id` , `name` ,`password`, `email` ,`phone`,`address` , `role` ) values(?,?,?,?,?,?,?)";
	//	String choice =null;
		    
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			PreparedStatement pstmt = con.prepareStatement(INSERT);
			
			
			//	int id = scan.nextInt();
				pstmt.setInt(1, user.getUserId());
			//	String name = scan.next();
				pstmt.setString(2 , user.getUserName());
			//	String password = scan.next();
				pstmt.setString(3, user.getPassword());
			//	String email = scan.next();
				pstmt.setString(4 , user.getEmail());
			
			//	int phone = scan.nextInt();
				pstmt.setInt(5, user.getPhone());
			//	String address = scan.next();
				pstmt.setString(6, user.getAddress());
			//	String role = scan.next();
				pstmt.setString(7, user.getRole()); 
				
				int i =pstmt.executeUpdate();
				System.out.println(i);
				
		
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

		

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
