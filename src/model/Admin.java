package model;
/**
 * administrator of DinnerManage System 
 * @author zjs
 *
 */
import java.util.ArrayList;
//Admin class Corresponding Admin table in mysql
public class Admin {
	private String id; // Corresponding id properties of table
	private String pwd; //Corresponding pwd properties of table

	public Admin() {
		
	}

	public Admin(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public static void main(String args[]){
		//if we get a administrator's information from database
		// the value is zhangjs and 123456
		Admin a = new Admin("zhangjs","123456");
		Admin b = new Admin("admin","123456");
		Admin c = new Admin("Marry","888888");
		ArrayList<Admin> list = new ArrayList<Admin>();
		list.add(a);
		list.add(b);
		list.add(c);
		
		for(int i=0;i<list.size();i++){
			Admin admin = (Admin)list.get(i);
			System.out.println(admin.getId()+","+admin.getPwd());
		}
		
		
	}
}
