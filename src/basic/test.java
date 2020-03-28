package basic;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String userid = "'' or 1=1 ",pwd = "123456";
		String sql = "select * from user where userid = " +
		userid +" and pwd = " +pwd;
		System.out.println(sql);

	}

}
