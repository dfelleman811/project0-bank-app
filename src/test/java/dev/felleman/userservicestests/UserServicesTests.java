package dev.felleman.userservicestests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.User;
import dev.felleman.services.UserServices;
import dev.felleman.services.UserServicesImpl;

public class UserServicesTests {
	
	UserDAO uDAO = new UserDAOImpl();
	UserServices uServ = new UserServicesImpl();
	
//	@Test
//	void createAccountTest() {
//		User user = new User("Marty", "McFly", "teamdoc");
//		uDAO.createUser(user);
//		
//		uServ.createAccount(user);
//		
//		uDAO.updateUser(user);
//		
//		User user2 = new User("John", "Doe", "jokes");
//		uDAO.createUser(user2);
//		
//		uServ.createAccount(user2);
//		
//		uDAO.updateUser(user2);
//		
//		System.out.println(user);
//		System.out.println(user2);
//	}
	
	
	@Test
	void loginTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		
		uServ.login(user, "dfelleman", "password");
		
		Assertions.assertEquals(true, user.getLoggedIn());
	
	}
	
	
	@Test
	void logoutTest() {
		User user = uDAO.createUser("Daniel", "Felleman", "dfelleman", "password");
		
		uServ.login(user, "dfelleman", "password");
		//System.out.println(user);
		//uDAO.updateUser(user);
		
		uServ.logout(user);
		
		Assertions.assertEquals(false, user.getLoggedIn());
		
	
	}
	
	
//	@Test
// 	void depositTest() {
// 		User user = new User("Hermioine", "Granger", "crookshanks");
// 		uDAO.createUser(user);
// 		
// 		uServ.deposit(user, 500.00);
// 		
// 		uDAO.updateUser(user);
// 		
// 		//System.out.println(user);
// 		
// 		Assertions.assertEquals(500.00, user.getAccountBalance());
// 
// 	}
//
//	@Test
//	void withdrawalTest() {
//		User user = new User("Hermioine", "Granger", "crookshanks");
// 		uDAO.createUser(user);
// 		
// 		uServ.deposit(user, 500.00);
// 		
// 		uDAO.updateUser(user);
// 		
// 		uServ.withdrawal(user, 250);
// 		
// 		uDAO.updateUser(user);
// 		
// 		System.out.println(user);
//
// 		Assertions.assertEquals(250, user.getAccountBalance());
//	}
//	
	
	
//	@Test
//	void getAccountBalanceTest() {
//		User user = new User("Daniel", "Felleman", "dfelleman");
//		user.setPassword("password");
//		uDAO.createUser(user);
//		uServ.login(user, user.getUsername(), user.getPassword());
//		
//		uServ.deposit(user, 500);
//		
//		System.out.println(uServ.getAccountBalance(user));
//		
//		
//	}
 	
	
	
	
	
	
}
