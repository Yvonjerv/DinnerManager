package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import dao.RechargeDAO;
import impl.RechargeDaoImpl;

/**
 * User recharge and query form
 * @author Administrator
 *
 */
public class RechargeHistoryDlg extends JDialog {
	private JLabel labquery = new JLabel("query with user name：");
	private JTextField tfquery = new JTextField(20);
	private JButton btnquery = new JButton("query",new ImageIcon("img/query.png"));
	private JTextArea taquery = new JTextArea(20,30); //The size of the text area doesn't make sense
	private JScrollPane span = new JScrollPane(taquery); //Add a scroll bar to the text area
	
	 public RechargeHistoryDlg(){
	    	init();
	    	this.setTitle("User recharge history information");
	    	this.setSize(550,350);
	    	this.setLocationRelativeTo(null);
	    	this.setResizable(false);
	    	ImageIcon icon=new ImageIcon("img/rechargeHistory.png");  
	    	setIconImage(icon.getImage());
	    	this.setModal(true);
	    	this.setVisible(true);
	    }
	
	public void init(){
		this.setLayout(new BorderLayout(5,5));
		
		JPanel p1 = new JPanel(); //Use a panel at the top
		p1.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		p1.add(labquery); p1.add(tfquery); p1.add(btnquery);
		
		this.add(p1,BorderLayout.NORTH);
		this.add(span,BorderLayout.CENTER);
		
		btnquery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = tfquery.getText();
						
				RechargeDAO rdao = new RechargeDaoImpl();
				if(query.equals("")){
				/*If the user does not enter query criteria,
				*all recharge information will be output
				*/
				List list = rdao.getRecharge(null);
				taquery.setText(rdao.toTableList(list));
				}else{
				/*If the user enters the name of the recharge user
				*to be queried, the query returns the qualified
				*historical recharge record of the user
				*/
				List list = rdao.getRecharge(query);
				taquery.setText(rdao.toTableList(list));
				}
			}
		});
	}
	 
	public static void main(String args[]){
		RechargeHistoryDlg frm = new RechargeHistoryDlg();
	}
}
