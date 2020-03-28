package ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import util.MsgBox;

//主菜单窗体
public class MainFrm extends JFrame implements ActionListener{
	JMenuBar bar = new JMenuBar();
	JMenu sysMenu = new JMenu("System");
	JMenu userMenu = new JMenu("User");
	JMenu dinnerMenu = new JMenu("Dinner");
	JMenu helpMenu = new JMenu("Help");
	JMenuItem exitItem = new JMenuItem("Exit",new ImageIcon("img/exit.png"));
	JMenuItem regUserItem = new JMenuItem("Register User",new ImageIcon("img/register.png"));
	JMenuItem userQueryItem = new JMenuItem("Query User",new ImageIcon("img/queryUser.png"));
	JMenuItem userPayItem = new JMenuItem("Recharge",new ImageIcon("img/recharge.png"));
	JMenuItem payHistoryItem = new JMenuItem("Recharge History",new ImageIcon("img/rechargeHistory.png"));
	JMenuItem dinnerAccountItem = new JMenuItem("Write Account",new ImageIcon("img/writeAccount.png"));
	JMenuItem dinnerHistoryItem = new JMenuItem("Account History",new ImageIcon("img/accountHistory.png"));
	JMenuItem aboutItem = new JMenuItem("About...");
	
	JToolBar toolBar = new JToolBar();    
	JButton btnExit = new JButton(new ImageIcon("img/exit.png"));
	JButton btnAddUser = new JButton(new ImageIcon("img/register.png"));
	JButton btnUserInfo = new JButton(new ImageIcon("img/queryUser.png"));
	JButton btnPay = new JButton(new ImageIcon("img/recharge.png"));
	JButton btnPayHistory = new JButton(new ImageIcon("img/rechargeHistory.png"));
	JButton btnAddDinner = new JButton(new ImageIcon("img/writeAccount.png"));
	JButton btnDinnerHistory = new JButton(new ImageIcon("img/accountHistory.png"));
	
	
	public MainFrm(){
		
		init();
		this.setTitle("Dinner Manager");
		this.setSize(800,450);
		this.setLocationRelativeTo(null);
		ImageIcon icon=new ImageIcon("img/dinnerManage.png");  
    	setIconImage(icon.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void init(){
		
		sysMenu.add(exitItem);
		
		userMenu.add(regUserItem);
		userMenu.add(userQueryItem);
		userMenu.addSeparator();
		userMenu.add(userPayItem);
		userMenu.add(payHistoryItem);
		
		
		dinnerMenu.add(dinnerAccountItem);
		dinnerMenu.addSeparator();
		dinnerMenu.add(dinnerHistoryItem);
		
		helpMenu.add(aboutItem);
		
		bar.add(sysMenu);
		bar.add(userMenu);
		bar.add(dinnerMenu);
		bar.add(helpMenu);
		
		this.setJMenuBar(bar);
		
		//工具栏设置
		//toolBar.setFloatable(false); 
		toolBar.add(btnExit);
		toolBar.addSeparator();
		toolBar.add(btnAddUser);
		toolBar.add(btnUserInfo);
		toolBar.addSeparator();
		toolBar.add(btnPay);
		toolBar.add(btnPayHistory);
		toolBar.addSeparator();
		toolBar.add(btnAddDinner);
		toolBar.add(btnDinnerHistory);
		
		getContentPane().add(toolBar, BorderLayout.NORTH);  
		
		exitItem.addActionListener(this);
		regUserItem.addActionListener(this);
		userQueryItem.addActionListener(this);
		userPayItem.addActionListener(this);
		dinnerAccountItem.addActionListener(this);
		dinnerHistoryItem.addActionListener(this);
		payHistoryItem.addActionListener(this);
		helpMenu.addActionListener(this);
		
		btnExit.addActionListener(this);
		btnAddUser.addActionListener(this);
		btnUserInfo.addActionListener(this);
		btnPay.addActionListener(this);
		btnPayHistory.addActionListener(this);
		btnAddDinner.addActionListener(this);
		btnDinnerHistory.addActionListener(this);
		
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				boolean op = MsgBox.showConfirm("exit system", "Are you sure you want to quit the system？");
				if(op){
					System.exit(0);
				}else if(e.getSource()==regUserItem || e.getSource()==btnAddUser){
					new AddUserDlg();
				}
				else if(e.getSource()==userQueryItem ||
				e.getSource()==btnUserInfo){
				new UserListDlg();
				}
				else if(e.getSource()==userPayItem || e.getSource()==btnPay){
				new RechargeDlg();
				}
				else if(e.getSource()==payHistoryItem ||
				e.getSource()==btnPayHistory){
				new RechargeHistoryDlg();
				}
				else if(e.getSource()==dinnerAccountItem ||
				e.getSource()==btnAddDinner){
				new AddDinnerDlg();
				}
				else if(e.getSource()==dinnerHistoryItem ||
				e.getSource()==btnDinnerHistory){
				new DinnerHistoryDlg();
				}
				else if(e.getSource()==helpMenu){
				}
			}
			
		});
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==exitItem || e.getSource()==btnExit){
			boolean op = MsgBox.showConfirm("exit system", "Are you sure you want to quit the system？");
			if(op){
				System.exit(0);
			}
		}else if(e.getSource()== regUserItem || e.getSource()== btnAddUser) {
			new AddUserDlg(); //open add user dialog
		}else if(e.getSource()== userQueryItem || e.getSource()== btnUserInfo) {
			new UserListDlg();  
		}else if(e.getSource()== userPayItem || e.getSource()== btnPay) {
			new RechargeDlg();  
		}else if(e.getSource()== payHistoryItem || e.getSource()== btnPayHistory) {
			new RechargeHistoryDlg();  
		}else if(e.getSource()== dinnerAccountItem || e.getSource()== btnAddDinner) {
			new AddDinnerDlg();  
		}else if(e.getSource()== dinnerHistoryItem || e.getSource()== btnDinnerHistory) {
			new DinnerHistoryDlg();  
		}
		
	}

	public static void main(String[] args) {
		System.setProperty("user.language","en_US") ;
		MainFrm.setDefaultLookAndFeelDecorated(true);
		MainFrm f = new MainFrm();
	 LoginDlg.setDefaultLookAndFeelDecorated(true);
		//login first before open the Main Frame
	LoginDlg loginDlg = new LoginDlg();
		
	} 
	
}
