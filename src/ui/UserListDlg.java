package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dao.UserDAO;
import impl.UserDaoImpl;
import model.User;

/**
 *  User information browsing form
 * @author Administrator
 *
 */
public class UserListDlg extends JDialog {
	private JLabel labquery = new JLabel("query with name:");
	private JTextField tfquery = new JTextField(20);
	private JButton btnquery = new JButton("query",new ImageIcon("img/query.png"));
	private JTextArea taquery = new JTextArea(); 
	private JScrollPane span = new JScrollPane(taquery); 
	
	 public UserListDlg(){
	    	init();
	    	this.setTitle("user information list");
	    	this.setSize(550,350);
	    	this.setLocationRelativeTo(null);
	    	this.setResizable(false);
	    	ImageIcon icon=new ImageIcon("img/user.png");  
	    	setIconImage(icon.getImage());
	    	this.setModal(true);
	    	this.setVisible(true);
	    }
	
	public void init(){
		this.setLayout(new BorderLayout(5,5));
		
		JPanel p1 = new JPanel(); 
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		p1.add(labquery); p1.add(tfquery); p1.add(btnquery);
		
		this.add(p1,BorderLayout.NORTH);
		this.add(span,BorderLayout.CENTER);
		
		
		/*Realize the query function. If the user name entered by 
		 * the user is blank, all users will be queried. If it is 
		 * not blank, query by the entered user name
		 */
		btnquery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = tfquery.getText();
				
				UserDAO udao = new UserDaoImpl();
				if(query.equals("")){
				 ArrayList<User> list =(ArrayList<User>)udao.getUser(null);
					 String s = udao.toTableList(list);
					 taquery.setText(s);
					//taquery.setText(udao.toTableList(udao.getUser(null)));
				}else{
				taquery.setText(udao.toTableList(udao.getUser(query)));
				}
				
				
			}
		});
	}
	 
	public static void main(String args[]){
		UserListDlg frm = new UserListDlg();
	}
}
