package model;

import util.NumFormatUtil;

/**
 * user entity class
 * @author zjs
 *
 */

//User class Corresponding User table in mysql
public class User {
	private String username; // Corresponding username properties of table
	private double balance; // Corresponding balance properties of table 
	
	public User(){
		
	}

	public User(String username) {
		super();
		this.username = username;
	}
	
	public User(String username, double balance) {
		super();
		this.username = username;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return NumFormatUtil.moneyFormat(balance);
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
