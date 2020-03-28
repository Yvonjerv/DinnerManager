package impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

import basic.BaseDAO;
import dao.RechargeDAO;
import model.RechargeData;

public class RechargeDaoImpl implements RechargeDAO {
	BaseDAO dao = new BaseDAO();//invoke BaseDAO for process database
	
	@Override
	public boolean addRecharge(RechargeData recharge) {
		String sql = "insert into rechargedata(username,account,actime) values(?,?,?)";
		Object[] para = {recharge.getUsername(),recharge.getAccount(),
				recharge.getActime()};
		int row = dao.update(sql, para);
		if(row>0)
			return true;
		else
			return false;
	}

	@Override
	public List<RechargeData> getRecharge(String username) {
		String sql = "";
		List<RechargeData> list = new ArrayList<RechargeData>();
		ResultSet rs = null;
		if(username == null) {
			sql = "select * from rechargedata";
			rs = dao.query(sql);
		}else {
			sql = "select * from rechargedata where username = ?";
			Object[] para = {username};
			rs = dao.query(sql, para);
		}
		
		//Convert the data in the ResultSet to ArrayList<DinnerData>
		try {
			while(rs.next()) {
				RechargeData recharge = new RechargeData();
				recharge.setId(rs.getInt("id"));
				recharge.setUsername(rs.getString("username"));;
				recharge.setAccount(rs.getDouble("account"));
				recharge.setActime(rs.getString("actime"));
				list.add(recharge);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close(); //close connection resource
		return list;
	}

	@Override
	public String toTableList(List<RechargeData> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" 用户姓名        金额       日期 \n");
		sb.append("----------------------------\n");
		for(int i=0;i<list.size();i++){
			RechargeData obj = (RechargeData)list.get(i);
			sb.append("  " + obj.getUsername());
			sb.append("\t" + obj.getAccount());
			sb.append("\t" + obj.getActime());
			sb.append("\n");
		}
		return sb.toString();
	}

}
