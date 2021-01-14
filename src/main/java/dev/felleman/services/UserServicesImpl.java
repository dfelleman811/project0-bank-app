package dev.felleman.services;

import dev.felleman.daos.BankAccountDAO;
import dev.felleman.daos.BankAccountDAOImpl;
import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public class UserServicesImpl implements UserServices {
	
	UserDAO uDAO = new UserDAOImpl();
	BankAccountDAO bankAccountDAO = new BankAccountDAOImpl();
	
	
//	@Override
//	public User createAccount(User user) {
//		user.setAccountNumber(++accountNumber); // can I call the BankAccountDAO here?
//		uDAO.updateUser(user);
//		return user;
//	}

	@Override
	public User login(User user, String username, String password) {
		user.setLoggedIn(true);
		uDAO.updateUser(user);
		return user;
	}

	@Override
	public User logout(User user) {
		user.setLoggedIn(false);
		uDAO.updateUser(user);
		System.out.println("You have successfully logged out. Thank you for choosing online banking. See you next time!");
		return user;
		
	}
	@Override
	public User deposit(User user, double amount) {
		BankAccount account = bankAccountDAO.getBankAccountById(user.getUserId());
		bankAccountDAO.deposit(account, amount);
		uDAO.updateUser(user);
		return user;
	}

	@Override
	public User withdrawal(User user, double amount) {
		BankAccount account = bankAccountDAO.getBankAccountById(user.getUserId());
		bankAccountDAO.withdraw(account, amount);
		uDAO.updateUser(user);
		return user;
	}

	
	public double getAccountBalance(User user) {		
		if (user.getLoggedIn()) {
			
			double accountBalance = bankAccountDAO.viewBalance(user);
			
			return accountBalance;
		}
		else {
			System.out.println("You need to be logged in to view your account balance. Please Login.");
			return 0.00;
		}
		
	}

}
