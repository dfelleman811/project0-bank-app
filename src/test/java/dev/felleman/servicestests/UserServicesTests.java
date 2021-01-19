package dev.felleman.servicestests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.felleman.daos.UserDAO;
import dev.felleman.daos.UserDAOImpl;
import dev.felleman.entities.User;
import dev.felleman.services.UserServices;
import dev.felleman.services.UserServicesImpl;

class UserServicesTests {

	UserServices userServ = new UserServicesImpl();
	UserDAO userDAO = new UserDAOImpl();
	
	@Test
	void loginTest() {
		
		User user = userDAO.getUserById(6);
		
		userServ.login(user, "wormtail", "traitor");
		
		Assertions.assertEquals(1, user.getLoggedIn());
		
	}
	
	@Test
	void logoutTest() {
		User user = userDAO.getUserById(6);
		
		userServ.logout(user);
		
		Assertions.assertEquals(0, user.getLoggedIn());
	}

}
