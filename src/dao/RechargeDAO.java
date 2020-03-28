package dao;

import java.util.List;

import model.DinnerData;
import model.RechargeData;

public interface RechargeDAO {

	/**
	 * add a recharge record 
	 * @param RechargeData
	 * @return success return true, faild return false
	 */
	public boolean addRecharge(RechargeData recharge);
	
	/**
	 * get recharge by username, if username is null, return all recharge record
	 * @param username
	 * @return Recharge data set
	 */
	public List<RechargeData> getRecharge(String username);
	
	/**
	 * Converts a list to a string in a table format that can be displayed in textarea
	 * @return String
	 */
	public String toTableList(List<RechargeData> list);
}
