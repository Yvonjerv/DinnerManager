package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.RechargeDAO;
import dao.UserDAO;
import impl.RechargeDaoImpl;
import impl.UserDaoImpl;
import model.RechargeData;
import util.DateFormatUtil;
import util.MsgBox;

// 用户充值缴费窗体
public class RechargeDlg extends JDialog {
	private JLabel labUserName = new JLabel("             user name："); 
	private JTextField tfUserName = new JTextField(20); 
	private JLabel labAccount = new JLabel("recharge account："); 
	private JTextField tfAccount = new JTextField(20); 

	private Icon save = new ImageIcon("img/save.png");
	private JButton btnsave = new JButton("save");
	
	public RechargeDlg(){
		init();
		this.setTitle("User Recharge");
		this.setSize(400,180);
		this.setModal(true);
		ImageIcon icon=new ImageIcon("img/recharge.png");  
    	setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		this.add(box); 
		
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
		p2.add(labAccount);p2.add(tfAccount);
		box.add(p2);
		
		//Register listener object for Save button
		btnsave.addActionListener(new OnSaveButtonClick());
	}
	
	//Define save button event class
	class OnSaveButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Get user's payment information
			String username = tfUserName.getText();
			String saccount = tfAccount.getText();
			
			//data verification
			if(username.equals("")){
				MsgBox.showErr("User recharge", "User name cannot be empty！");
				return;
			}else if(saccount.equals("")){
				MsgBox.showErr("User recharge", "Payment amount cannot be empty！");
				return;
			}
			
			double account = 0;
			try {
				account = Double.parseDouble(saccount);
			} catch (NumberFormatException e1) {
				MsgBox.showErr("User recharge", "Please input the number of payment amount！");
				return; 
			}
			
			RechargeDaoImpl rdao = new RechargeDaoImpl();
			UserDAO udao = new UserDaoImpl();
			//Get current system time
			String datetime = DateFormatUtil.DateTimeToString(new Date());
			//Save the user's recharge information in the recharge object
			RechargeData recharge = new RechargeData();
			recharge.setUsername(username);
			recharge.setActime(datetime);
			recharge.setAccount(account);
			
			if(rdao.addRecharge(recharge)){
				if(udao.addMoney(account, username)) {
					MsgBox.showMsg("user recharge", "This user recharge	succeeded！ ");
					return;
				}else 		
					MsgBox.showMsg("user recharge", "This user recharge	failed！ ");
				
				
				
			/*udao.payMoney(account, username);// Modify the account
			//balance of this user
			MsgBox.showMsg("user recharge", "This user recharge	succeeded！ ");
			RechargeDlg.this.dispose();
			}else{
			MsgBox.showMsg("user recharge", "Sorry,this user recharge failed！ ");
			}*/
			}	
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RechargeDlg frm = new RechargeDlg();
	}
}
