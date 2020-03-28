package util;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumFormatUtil {
	/**
	 * Keep two decimal places in amount format
	 * @param account  Number to format
	 * @return Formatted number
	 */
	public static double moneyFormat(double account){
		DecimalFormat df = new DecimalFormat("###,###.00");
		
		String s = df.format(account);
		Number d = 0;
		try {
			d = df.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return d.doubleValue();
	}
	
	public static void main(String args[]){
		double d = NumFormatUtil.moneyFormat(31.233344444);
		System.out.println(d);
	}
}