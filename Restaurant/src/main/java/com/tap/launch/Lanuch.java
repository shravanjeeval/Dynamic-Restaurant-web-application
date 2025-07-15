package com.tap.launch;

import java.util.Scanner;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

public class Lanuch {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("enter your id");
		User  u = new User();
	   int id = scan.nextInt();
		u.setUserId(id);
		System.out.println("enter your name");
		String name = scan.next();
		u.setUserName(name);
		System.out.println("enter your password");
		String password = scan.next();
		u.setPassword(password);
		System.out.println("enter your email");
		String email = scan.next();
		u.setEmail(email);
		System.out.println("enter your phone");
		int phone = scan.nextInt();
		u.setPhone(phone);
		System.out.println("enter your address");
		String address = scan.next();
		u.setAddress(address);
		System.out.println("enter your role");
		String role = scan.next();
		u.setRole(role);
		
		
		UserDaoImpl ud= new UserDaoImpl();
		
		ud.addUser(u);

	}

}
