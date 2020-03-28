package util;

import javax.swing.JOptionPane;
//message box tools
public class MsgBox {
	/**
	 * normal information message box
	 * @param title   
	 * @param msg    message
	 */
	public static void showMsg(String title, String msg){
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * error information message box
	 * @param title   
	 * @param msg   message
	 */
	public static void showErr(String title,String msg){
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * comfirm box
	 * @param title   
	 * @param msg    message
	 * @return boolean   user choice yes， return true ，choice no，return false
	 */
	public static boolean showConfirm(String title,String msg){
		if( JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION)==0) 
			return true;
		else 
			return false;
	}
	
	//test
	public static void main(String args[]){	
		if(MsgBox.showConfirm("delete", "Are you sure delete this data？")){
			System.out.println("ok, deleted");
		}
		else{
			System.out.println("no, I think again");
		}
	}
}