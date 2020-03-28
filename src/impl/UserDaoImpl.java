package impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basic.BaseDAO;
import dao.UserDAO;
import model.DinnerData;
import model.User;

public class UserDaoImpl implements UserDAO {
	BaseDAO dao = new BaseDAO();//invoke BaseDAO for process database
	
	@Override
	public boolean addUser(User user) {
		String sql = "insert into User(username,balance) values(?,?)";
		Object[] para = {user.getUsername(),user.getBalance()};
		int row = dao.update(sql, para);
		if(row>0)
			return true;
		else
			return false;
	}

	@Override
	public List<User> getUser(String username) {
		String sql = "";
		List<User> list = new ArrayList<User>();
		ResultSet rs = null;
		if(username == null) {
			sql = "select * from user";
			rs = dao.query(sql);
		}else {
			sql = "select * from user where username = ?";
			Object[] para = {username};
			rs = dao.query(sql, para);
		}
		
		//Convert the data in the ResultSet to ArrayList<DinnerData>
		try {
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setBalance(rs.getDouble("balance"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close(); //close connection resource
		return list;
	}

	@Override
	public boolean payMoney(double money,String username) {
		String sql = "update user set balance = balance - ? "
				+ "where username = ?";
		Object[] para = {money,username};
		int row = dao.update(sql, para);
		if(row>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean addMoney(double money,String username) {
		String sql = "update user set balance = balance + ? "
				+ "where username = ?";
		Object[] para = {money,username};
		int row = dao.update(sql, para);
		if(row>0)
			return true;
		else
			return false;
	}

	@Override
	public String toTableList(List<User> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" Username         Amount     \n");
		sb.append("----------------------------\n");
		for(int i=0;i<list.size();i++){
			User obj = (User)list.get(i);
			sb.append("  " + obj.getUsername());
			sb.append("\t" + obj.getBalance());
			sb.append("\n");
		}
		return sb.toString();
	}

}
