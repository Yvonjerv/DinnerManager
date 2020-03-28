package impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basic.BaseDAO;
import dao.DinnerDAO;
import model.DinnerData;

public class DinnerDaoImpl implements DinnerDAO {
	BaseDAO dao = new BaseDAO();//invoke BaseDAO for process database
	
	@Override
	public boolean addDinner(DinnerData dinner) {
		String sql = "insert into dinnerdata(dtime,addr,totalaccount,"
				+ "participant,remark) values(?,?,?,?,?)";
		Object[] para = {dinner.getDtime(),dinner.getAddr(),dinner.getTotalaccount(),
				dinner.getParticipant(),dinner.getRemark()};
		int row = dao.update(sql, para);
		if(row>0)
			return true;
		else
			return false;
	}

	@Override
	public List<DinnerData> getDinner(String date) {
		String sql = "";
		List<DinnerData> list = new ArrayList<DinnerData>();
		ResultSet rs = null;
		if(date == null) {
			sql = "select * from dinnerdata";
			rs = dao.query(sql);
		}else {
			sql = "select * from dinnerdata where dtime = ?";
			Object[] para = {date};
			rs = dao.query(sql, para);
		}
		
		//Convert the data in the ResultSet to ArrayList<DinnerData>
		try {
			while(rs.next()) {
				DinnerData dinner = new DinnerData();
				dinner.setId(rs.getInt("id"));
				dinner.setDtime(rs.getString("dtime"));
				dinner.setAddr(rs.getString("addr"));
				dinner.setParticipant(rs.getString("participant"));
				dinner.setRemark(rs.getString("remark"));
				list.add(dinner);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.close(); //close connection resource
		return list;
	}

	@Override
	public String toTableList(List<DinnerData> list) {
		StringBuffer sb = new StringBuffer();
		sb.append(" 饭局日期         饭局地点         总金额        备注说明          参与人        \n");
		sb.append("----------------------------------------------\n");
		for(int i=0;i<list.size();i++){
			DinnerData obj = (DinnerData)list.get(i);
			sb.append("  " + obj.getDtime());
			sb.append("\t" + obj.getAddr());
			sb.append("\t" + obj.getTotalaccount());
			sb.append("\t" + obj.getRemark());
			sb.append("\t" + obj.getParticipant());
			sb.append("\n");
		}
		return sb.toString();
	}

}
