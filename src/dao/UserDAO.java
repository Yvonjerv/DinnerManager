package dao;

import java.util.List;

import model.RechargeData;
import model.User;

public interface UserDAO {
	/**
	 * add a user 
	 * @param user 
	 * @return success return true, faild return false
	 */
	public boolean addUser(User user);
	
	/**
	 * get User by username, if username is null, return all user
	 * @param username
	 * @return user data set
	 */
	public List<User> getUser(String username);
	
	/**
	 * deduct meal expenses from user account 
	 * @param money
	 * @param username
	 * @return successful return true, otherwise return false
	 */
	public boolean payMoney(double money,String username);
	
	/**
	 * Converts a list to a string in a table format that can be displayed in textarea
	 * @return String
	 */
	public String toTableList(List<User> list);

	public boolean addMoney(double account, String username);
}

