package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.*;
import impl.AdminDaoImpl;
import util.MsgBox;
// 登录窗体
public class LoginDlg extends JDialog implements ActionListener{	
	Icon icon1 = new ImageIcon("img/user.png");
    JLabel labuserid = new JLabel("userid：", icon1, JLabel.LEFT);
    
    Icon icon2 = new ImageIcon("img/pwd.png");
    JLabel labpwd = new JLabel("pwd：", icon2, JLabel.LEFT);
    
    JTextField txtuserid = new JTextField(15);
    JPasswordField txtpwd = new JPasswordField(15);
    
    JButton btnOK = new JButton("Login");
    JButton btnCancel = new JButton("Cancel");
    
    public LoginDlg(){
    	init();
    	this.setTitle("Login");
    	this.setSize(220,150);
    	this.setLocationRelativeTo(null);
    	this.setResizable(false);
    	ImageIcon icon=new ImageIcon("img/login.png");  
    	setIconImage(icon.getImage());
    	this.setModal(true);
    	this.setVisible(true);
    
    }
    
    public void init(){
    	this.setLayout(new GridLayout(3,2,5,5));
    	this.add(labuserid); this.add(txtuserid);
    	this.add(labpwd); this.add(txtpwd);
    	this.add(btnOK); this.add(btnCancel);
    	
    	btnOK.addActionListener(this);
    	btnCancel.addActionListener(this);
    	this.addWindowListener(new WindowCloseAction());
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK){
			//Get the user name and password entered by the user
			String userid = txtuserid.getText().trim();
			String pwd = txtpwd.getText().trim();
			//data verification
			if(userid.equals("")){
				MsgBox.showErr("login", "User login name cannot be empty！");
				return;
			}else if(pwd.equals("")){
				MsgBox.showErr("login", "User login password cannot be empty！");
				return;
			}
			
			AdminDAO adao = new AdminDaoImpl();
			if(adao.login(userid, pwd)==true){
			MsgBox.showMsg("login", "User authentication succeeded,	welcome to AA pay management system");
			this.dispose();
			}else{
			MsgBox.showErr("login", "Incorrect login user name and password");
			}
			
		}		
		else if(e.getSource() == btnCancel){
			MsgBox.showMsg("system", "Thank you for using. Welcome to visit next time");
			this.dispose();
			System.exit(0);
		}
	}
	
	class WindowCloseAction extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			MsgBox.showMsg("系统", "谢谢使用，欢迎您下次光临");
			LoginDlg.this.dispose();
			System.exit(0);
		}
		
	}

	public static void main(String[] args) {
		new LoginDlg();

	}
}