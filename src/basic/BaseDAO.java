package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * database process base class
 * @author zjs
 *
 */
public class BaseDAO extends ConnectDB{
	private Connection con =null;
	private PreparedStatement stmt = null;
	
	/**
	 * Encapsulating the closing operation of database objects
	 */
	public void close() {
		this.close(con, stmt);
	}
	/**
	 * execute insert into/update/delete SQL Statement
	 * @param sql  insert into/update/delete statment contain ? placeholder
	 * @param para parameters valule corrsponding to ? in the sql
	 * @return  success return >0    faild  return <=0
	 */
	public int update(String sql,Object[] para) {
		try {
			con = this.getConnection();
			stmt = con.prepareStatement(sql);
			//set value to ? placeholder
			for(int i=0;i<para.length;i++) {
				stmt.setObject(i+1, para[i]);
			}
			int row =  stmt.executeUpdate();
			this.close();
			return row;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL syntax error or SQL execute error!");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection faild!");
		}
		return 0;
	}
	
	/**
	 * Execute select SQL and return ResultSet
	 * @param sql  select SQL without ? placeholder
	 * @return ResultSet
	 */
	public ResultSet query(String sql) {
		try {
			con = this.getConnection();
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			//close(con,stmt);
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL syntax error or SQL execute error!");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection faild!");
		}
		return null;
	}
	
	/**
	 * Execute select SQL and return ResultSet
	 * @param sql  select SQL with ? placeholder
	 * @return ResultSet
	 */
	public ResultSet query(String sql,Object para[]) {
		try {
			con = this.getConnection();
			stmt = con.prepareStatement(sql);
			//set value to ? placeholder
			for(int i=0;i<para.length;i++) {
				stmt.setObject(i+1, para[i]);
			}
			ResultSet rs = stmt.executeQuery();
			//close(con,stmt);
			return rs;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL syntax error or SQL execute error!");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection faild!");
		}
		return null;
	}
	
	public static void main(String args[]){
		String sql = "select * from user where username = ?";
		Object para[] = {"Zhang Jin Sheng"};
		BaseDAO  dao = new BaseDAO();
		ResultSet rs = dao.query(sql, para);
		try{
			while(rs.next()){
				System.out.println(rs.getString("username"));
				System.out.println(rs.getDouble("balance"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
