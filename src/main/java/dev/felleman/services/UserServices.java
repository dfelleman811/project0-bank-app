package dev.felleman.services;

import dev.felleman.entities.User;

public interface UserServices {
	
	//public User createAccount(User user);
	
	public User login(User user, String username, String password);
	
	public User logout(User user);
	
	public User deposit(User user, double amount);
	
	public User withdrawal(User user, double amount);
	
	//public double getAccountBalance(User user);         
	
	

}
