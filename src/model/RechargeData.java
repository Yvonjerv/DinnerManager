package model;

/**
 * user recharge data entity class
 * @author zjs
 *
 */

//User class Corresponding rechargedata table in mysql
public class RechargeData {
	private int  id; // Corresponding id properties of table
	private String username; // Corresponding username properties of table
	private double account; // Corresponding account properties of table
	private String actime; // Corresponding actime properties of table
	
	public RechargeData(){
		
	}

	public RechargeData(int id, String username, double account, String actime) {
		super();
		this.id = id;
		this.username = username;
		this.account = account;
		this.actime = actime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public String getActime() {
		return actime;
	}

	public void setActime(String actime) {
		this.actime = actime;
	}
}
