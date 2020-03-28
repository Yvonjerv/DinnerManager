package impl;

import java.sql.ResultSet;

import basic.BaseDAO;
import dao.AdminDAO;

public class AdminDaoImpl implements AdminDAO {
	BaseDAO dao = new BaseDAO(); //invoke BaseDAO for process database
	
	@Override
	public boolean login(String id, String pwd) {
		String sql = "select * from Admin where id = ? and pwd = ?";
		Object[] para = {id,pwd};
		ResultSet rs = dao.query(sql, para);
		try {
			if(rs.next())
				return true;
			else
				return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
