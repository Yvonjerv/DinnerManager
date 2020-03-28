package dao;

public interface AdminDAO {
	/**
	 * use id and pwd varify administrator 
	 * @param id
	 * @param pwd
	 * @return varfiy successfully return true, otherwise return false
	 */
	public boolean login(String id, String pwd);
}
