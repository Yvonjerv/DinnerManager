package dao;

import java.util.List;

import model.DinnerData;
import model.User;

public interface DinnerDAO {
	/**
	 * add a dinner record and caculate pay money for each user 
	 * @param DinnerData
	 * @return success return true, faild return false
	 */
	public boolean addDinner(DinnerData dinner);
	
	/**
	 * get dinner by date, if date is null, return all dinner record
	 * @param date
	 * @return dinner data set
	 */
	public List<DinnerData> getDinner(String date);
	
	/**
	 * Converts a list to a string in a table format that can be displayed in textarea
	 * @return String
	 */
	public String toTableList(List<DinnerData> list);
}
