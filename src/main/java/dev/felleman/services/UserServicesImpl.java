package dev.felleman.services;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public class UserServicesImpl implements UserServices {
	
	UserDAO userDAO = new UserDAOImpl();
	BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	
	
//	@Override
//	public User createAccount(User user) {
//		user.setAccountNumber(++accountNumber); // can I call the BankAccountDAO here?
//		uDAO.updateUser(user);
//		return user;
//	}

	@Override
	public boolean login(User user, String username, String password) {
		// check password matches
		if (password.equals(user.getPassword())) {
			userDAO.getUserById(user.getUserId());
			user.setLoggedIn(1);
			userDAO.updateUser(user);
			return true;
		} else {
			
			return false;
		}
	}

	@Override
	public User logout(User user) {
		userDAO.getUserById(user.getUserId());
		user.setLoggedIn(0);
		userDAO.updateUser(user);
		System.out.println("You have successfully logged out. Thank you for choosing online banking. See you next time!");
		// Show a summary of what happened? 
		return user;
		
	}
//	@Override
//	public User deposit(User user, double amount) {
//		BankAccount account = bankAccountDAO.getBankAccountById(user.getUserId());
//		bankAccountDAO.deposit(account, amount);
//		uDAO.updateUser(user);
//		return user;
//	}
//
//	@Override
//	public User withdrawal(User user, double amount) {
//		BankAccount account = bankAccountDAO.getBankAccountById(user.getUserId());
//		bankAccountDAO.withdraw(account, amount);
//		uDAO.updateUser(user);
//		return user;
//	}
//
//	
//	public double getAccountBalance(User user) {		
//		if (user.getLoggedIn()) {
//			
//			double accountBalance = bankAccountDAO.viewBalance(user);
//			
//			return accountBalance;
//		}
//		else {
//			System.out.println("You need to be logged in to view your account balance. Please Login.");
//			return 0.00;
//		}
//		
//	}

}
