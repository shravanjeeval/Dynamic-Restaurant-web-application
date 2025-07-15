package com.tap.dao;

import java.util.List;

import com.tap.model.User;

public interface UserDao {
	
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(int userId);
	User getUser(int userId);
	List<User> getAllUser();
	

}
