package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//Date format class
public class DateFormatUtil {
	/*Convert a date object to a Chinese date string
	 * @param date Date object to format
	 * @return String Formatted date format string
	 */
	public static String DateTimeToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(date);
	}
	
	/**
	 * Determine whether the string entered by the user conforms 
	 * to the date format of "yyyy-mm-dd HH: mm"
	 * @param sdate  Date format string
	 * @return Return true for matching and false for mismatch;
	 */
	public static boolean isDateTime(String sdate){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(sdate != null){
			try {
				df.parse(sdate); //First convert string to date
				return true;
			} catch (ParseException e) {
				
			} 
		}
		return false;
	}
	
	/**
	 * Determine whether the string entered by the user conforms 
	 * to the date format of "yyyy-mm-dd"
	 * @param sdate  Date format string
	 * @return Return true for matching and false for mismatch;
	 */
	public static boolean isDate(String sdate){
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		if(sdate != null){
			try {
				df.parse(sdate); //First convert string to date
				return true;
			} catch (ParseException e) {
				
			} 
		}
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}