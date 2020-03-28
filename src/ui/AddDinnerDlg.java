package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import dao.DinnerDAO;
import dao.RechargeDAO;
import dao.UserDAO;
import impl.DinnerDaoImpl;
import impl.RechargeDaoImpl;
import impl.UserDaoImpl;
import model.DinnerData;
import model.RechargeData;
import model.User;
import util.DateFormatUtil;
import util.MsgBox;

public class AddDinnerDlg extends JDialog {
	private JLabel labTime = new JLabel("       Dinner Date:"); 
	private JTextField tfTime = new JTextField(20); 
	private JLabel labAddr = new JLabel("       Dinner Addr:"); 
	private JTextField tfAddr = new JTextField(20); 
	private JLabel labMoney = new JLabel("Dinner Account:"); 
	private JTextField tfMoney = new JTextField(20); 
	private JLabel labRemark = new JLabel(" Dinner Remark:"); 
	private JTextArea tfRemark = new JTextArea(4,20);
	private JScrollPane spRemark = new JScrollPane(tfRemark);
	private JLabel labpart =  new JLabel("=======Select participants=======");
	private Icon save = new ImageIcon("img/save.png");
	private JButton btnsave = new JButton("save");
	
	List<JCheckBox> ckblist = new ArrayList<JCheckBox>(); //ÓÃ»§¸´Ñ¡¿ò×é
	
	public AddDinnerDlg(){
		init();
		this.setTitle("Add Dinner");
		this.setSize(450,390);
		this.setModal(true);
		ImageIcon icon=new ImageIcon("img/writeAccount.png");  
    	setIconImage(icon.getImage());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		this.add(box); //ºÐ×Ó²¼¾ÖÈÝÆ÷Ìí¼Óµ½´°Ìå
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		btnsave.setIcon(save);
		p.add(btnsave);
		box.add(p);
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p1.add(labTime); p1.add(tfTime);
		box.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p2.add(labAddr); p2.add(tfAddr);
		box.add(p2);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p3.add(labMoney); p3.add(tfMoney);
		box.add(p3);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p4.add(labRemark); p4.add(spRemark);
		box.add(p4);
		
		JPanel p5 = new JPanel();
		p5.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		p5.add(labpart);
		box.add(p5);
		
		JPanel p6 = new JPanel();
		p6.setPreferredSize(new Dimension(350,100));
		p6.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		//Create meal participant from user in user array check box
		UserDaoImpl udao = new UserDaoImpl();
		List<User> users = udao.getUser(null); //get all user from user table
		for(int i=0;i<users.size();i++){
		//Use the user's name as a label for the checkbox
		User user = (User)users.get(i);
		JCheckBox userselect = new JCheckBox(user.getUsername());
		ckblist.add(userselect);
		p6.add(userselect);
		}
		
		
		box.add(p6);
		
		//Register listener object for Save button
		btnsave.addActionListener(new OnSaveButtonClick());
	}
	
	//Define save button event class
	class OnSaveButtonClick implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Get basic information about the meal
			String actime = tfTime.getText();
			String acaddr = tfAddr.getText();
			String acmoney = tfMoney.getText();
			String acremark = tfRemark.getText();
			
			double money = 0 ;
			
			/*By traversing the check box, the user name of the selected check box 
			 * is extracted and the participant user array is constructed
			 */
			List<String> sellist = new ArrayList<String>();
			for(int i=0;i<ckblist.size();i++){
				JCheckBox obj = (JCheckBox)ckblist.get(i);
				if(obj.isSelected()==true){ //If the check box is selected
					sellist.add(obj.getText()); //Add the selected person to the array
				}
			}
			
			 
			if(actime.equals("")){
				MsgBox.showErr("Add Dinner", "Meal date time cannot be empty!");
				return;
			}else if(!DateFormatUtil.isDateTime(actime)){
				MsgBox.showErr("Add Dinner", "The format of meal date and time is incorrect. It needs to be yyyy-mm-dd HH: mm!");
				return;
			}else if(acaddr.equals("")){
				MsgBox.showErr("Add Dinner", "Dining place cannot be empty!");
				return;
			}else if(acmoney.equals("")){
				MsgBox.showErr("Add Dinner", "Meal consumption amount cannot be empty!");
				return;
			}
			
			try {
				money = Double.parseDouble(acmoney);
			} catch (NumberFormatException e1) {
				MsgBox.showErr("Add Dinner", "Dinner consumption amount must be of digital type!");
				return;
			}
			
			if(sellist.size()<=0){
				MsgBox.showErr("Add Dinner", "No users choose to join the dinner party!");
				return;
			}
			
			/*Three business classes are required for this operation
			* (1)Add a meal record and implement it with DinnerrdDaoImpl
			business class
			* (2)Add an account for each participant's transaction using
			RechargeDaoImpl business class
			* (3)Modify the account balance of each participant user using
			UserDaoImpl business class
			**/
			//Create three business class objects
			DinnerDaoImpl ddao = new DinnerDaoImpl();
			RechargeDAO rdao = new RechargeDaoImpl();
			UserDAO udao = new impl.UserDaoImpl();
			//Convert list to array and then to string separated by ','
			String[] participantsArray = (String[])sellist.toArray(new String[sellist.size()]);
			
			String participants = String.join(",", participantsArray);
			//Save AA pay information to a DinnerData object
			DinnerData dinner = new	DinnerData(0,actime,acaddr,acremark,money,participants);
			//Add a meal AA pay record to the database
			ddao.addDinner(dinner);


			double avgpay = money / sellist.size();


			for(int i=0;i<sellist.size();i++){
			String username = (String)sellist.get(i);
			/*Use the recharge method to add a user's consumption
			*record with a negative consumption amount
			*/
			RechargeData recharge = new	RechargeData(0,username,0-avgpay,actime);
			rdao.addRecharge(recharge);
			//Modify the account balance of the user
			udao.payMoney(avgpay, username);
			
			//Lesson 13 (2) The final realization process of AA pay management system of meal fee
			}
			MsgBox.showMsg("Save AA pay record of dinner","Save AA pay record of dinner Succeed！ ");
			AddDinnerDlg.this.dispose();
			
			
		}
	}
	
	public static void main(String[] args) {
		// ÓÃÓÚ²âÊÔ
		AddDinnerDlg frm = new AddDinnerDlg();
		
	}
}
