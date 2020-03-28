package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dao.UserDAO;
import model.User;
import util.MsgBox;

/**
 *  append new user dialog
 * @author Administrator
 *
 */
public class AddUserDlg extends JDialog{
	private JLabel labUserName = new JLabel("      user name£º"); 
	private JTextField tfUserName = new JTextField(20); 
	private JLabel labBalance = new JLabel("initial balance£º"); 
	private JTextField tfBalance = new JTextField(20); 

	private Icon save = new ImageIcon("img/save.png");
	private JButton btnsave = new JButton("save");
	
	public AddUserDlg(){
		init();
		this.setTitle("append new user");
		this.setSize(350,150);
		this.setModal(true);
		ImageIcon icon=new ImageIcon("img/register.png");  
    	setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		this.add(box); //Box layout container added to form
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		btnsave.setIcon(save);
		p.add(btnsave);
		box.add(p);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p1.add(labUserName); p1.add(tfUserName);
		box.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p2.add(labBalance);p2.add(tfBalance);
		box.add(p2);
		
		
		//Register listener object for Save button
		btnsave.addActionListener(new OnSaveButtonClick());
	}
	
	//Define save button event listener class
	class OnSaveButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Get user input
			String username = tfUserName.getText();
			String sbalance = tfBalance.getText();
			//data verification
			if(username.equals("")){
				MsgBox.showErr("append new user", "User name cannot be empty!");
				return;
			}else if(sbalance.equals("")){
				MsgBox.showErr("append new user", "Account balance cannot be empty when registering£¡");
				return;
			}
			
			double balance = 0;
			try {
				balance = Double.parseDouble(sbalance);
			} catch (NumberFormatException e1) {
				MsgBox.showErr("append new user", "Please enter the number of account balance£¡");
				return; 
			}
			
			
			
			UserDAO udao = new impl.UserDaoImpl();
			User newUser = new User(username,balance);
			if(udao.addUser(newUser)==true){
			MsgBox.showMsg("add new user", "New user added successfully!");
			//AddUserDlg.this.dispose();
			tfUserName.setText("");
			tfBalance.setText("");
			}else{
			MsgBox.showMsg("add new user", "Username not available,	new user add failed!");
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddUserDlg frm = new AddUserDlg();
	}
}
