package ui;

import javax.swing.*;

import dao.DinnerDAO;
import impl.DinnerDaoImpl;
import util.DateFormatUtil;
import util.MsgBox;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * 饭局信息窗体
 * @author Administrator
 *
 */
public class DinnerHistoryDlg extends JDialog {
	private JLabel labquery = new JLabel("query with date：");
	private JTextField tfquery = new JTextField(20);
	private JButton btnquery = new JButton("query",new ImageIcon("img/query.png"));
	private JTextArea taquery = new JTextArea(); 
	private JScrollPane span = new JScrollPane(taquery); 
	
	 public DinnerHistoryDlg(){
	    	init();
	    	this.setTitle("Dinner Account History");
	    	this.setSize(550,350);
	    	this.setLocationRelativeTo(null);
	    	this.setResizable(false);
	    	ImageIcon icon=new ImageIcon("img/accountHistory.png");  
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
		
		btnquery.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String query = tfquery.getText();
				DinnerDAO ddao = new DinnerDaoImpl();
				if(query.equals("")){
				/*If the user does not enter query criteria,
				*all meal information will be output
				*/
				List list = ddao.getDinner(null);
				taquery.setText(ddao.toTableList(list));
				}else{
				/*If the user enters a valid meal time, the
				* qualified meal information will be queried and returned
				*/
				List list = ddao.getDinner(query);
				taquery.setText(ddao.toTableList(list));
				}
			}
		});
	}
	 
	public static void main(String args[]){
		DinnerHistoryDlg frm = new DinnerHistoryDlg();
	}
}
