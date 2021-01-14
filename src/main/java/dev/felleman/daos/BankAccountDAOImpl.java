package dev.felleman.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimap;

import dev.felleman.entities.BankAccount;
import dev.felleman.entities.User;

public class BankAccountDAOImpl implements BankAccountDAO {
	
	// Create a map where account information will be stored (linked to user id) - this will eventually become the RDS database
	private Multimap<Integer, BankAccount> bank_account_table = HashMultimap.create();

	private static UserDAO userDAO = new UserDAOImpl();
	
	// starting account number = 1000;
	private int bankAccountNumberTracker = 1000;

	@Override
	public BankAccount openAccount(User user) {
		BankAccount account = new BankAccount();
		account.setUserId(user.getUserId());
		account.setAccountNumber(++bankAccountNumberTracker);
		
		bank_account_table.put(user.getUserId(), account);
		
		return account;
	}

	@Override
	public void viewBalance(User user) { // TODO do the same output for deposits and withdrawals
		System.out.println("\t::: ACCOUNT SUMMARY :::");
		for (BankAccount account : bank_account_table.values()) {
			if (account.getUserId() == user.getUserId()) {
				System.out.println("Account Number: " + account.getAccountNumber() 
									+ ".........." + account.getAccountBalance() + " USD  "
									+ account.getAccountType());
				
			}
		}
		//double accountBalance = bank_account_table.get(user.getUserId()).getAccountBalance();
		
	}
	
	@Override
	public Collection<BankAccount> getBankAccountById(int userId) {
		
		Collection<BankAccount> account = bank_account_table.get(userId);
		return account;
	}
	
	@Override
	public Collection<BankAccount> getAllUserBankAccountsById(int userId) {
		
		Multimap<Integer, BankAccount> bankAccounts = HashMultimap.create();
		
		for (BankAccount act : bank_account_table.values()) {
			if (act.getUserId() == userDAO.getUserById(userId).getUserId()) {
				BankAccount act = // basically make the collection<BA> into single ba
				bankAccounts.put(userId, bank_account_table.get(userId));
			}
		}
		return bankAccounts.get(userId);
	}
	
	@Override
	public List<BankAccount> getAllBankAccounts() {
		List<BankAccount> bankAccounts = new ArrayList<BankAccount>();
		bankAccounts.addAll(bank_account_table.values());
		return bankAccounts;
	}

	@Override
	public BankAccount withdraw(BankAccount account, double amount) {
		// warn if user is going to overdraw
		if (amount > account.getAccountBalance()) {
			System.out.println("WARNING: If you withdraw " + amount 
					           + ", your account will be overdrawn.");
			System.out.println("Do you wish to continue? Y/N");
		}
		account.setAccountBalance(account.getAccountBalance() - amount);
		return account;
	}

	@Override
	public BankAccount deposit(BankAccount account, double amount) {
		account.setAccountBalance(account.getAccountBalance() + amount);
		
		return account;
	}
	
	@Override
	public BankAccount updateAccount(BankAccount account) {
		BankAccount updatedAccount = bank_account_table.put(account.getUserId(), account);
		return updatedAccount;
	}

	@Override
	public BankAccount closeAccount(BankAccount account) {
		//  If account balance is empty
		if (account.getAccountBalance() == 0) {
			bank_account_table.remove(account.getUserId());
			System.out.println("You have successfully closed your account.");
			return null;
		}
		else {
			System.out.println("Your account must have 0 balance in order to be closed."); // TODO would you like to withdraw all funds and close account?
			return null;
		}
		
	}
	
	
	
}
