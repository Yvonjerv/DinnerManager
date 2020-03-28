package model;

/**
 * dinner data
 * @author zjs
 *
 */
//User class Corresponding dinnerdata table in mysql
public class DinnerData {
	private int id; // Corresponding id properties of table
	private String dtime;// Corresponding dtime properties of table
	private String addr;// Corresponding addr properties of table£¬
	private String remark;// Corresponding remark properties of table
	private double totalaccount;// Corresponding totalaccount properties of table
	private String participant;//Corresponding participant properties of table
	
	public DinnerData(){
		
	}

	public DinnerData(int id, String dtime, String addr, String remark,
			double totalaccount, String participant) {
		this.id = id;
		this.dtime = dtime;
		this.addr = addr;
		this.remark = remark;
		this.totalaccount = totalaccount;
		this.participant = participant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTotalaccount() {
		return totalaccount;
	}

	public void setTotalaccount(double totalaccount) {
		this.totalaccount = totalaccount;
	}

	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}
}
