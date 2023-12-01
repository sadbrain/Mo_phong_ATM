package mo_phong_atm.view.balanceInquiry;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mo_phong_atm.Program;
import mo_phong_atm.view.Home;
import mo_phong_atm.view.changePin.ChangePin;

public class BalanceInquiry extends JFrame implements ActionListener  {

	public BalanceInquiry() {
        setTitle("VietinBank");
       
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        JLabel subtitle = new JLabel("Tài khoản dư của quý khách");
        subtitle.setFont(new Font("Arial", Font.BOLD, 25)); 
        subtitle.setForeground(Color.black);
        subtitle.setHorizontalAlignment(JLabel.CENTER); 
        
        
        
        
        JLabel fullNameLB = new JLabel("Họ và tên");
        fullNameLB.setFont(new Font("Arial", Font.PLAIN, 20)); 
        fullNameLB.setForeground(Color.black);
        
        JTextField fullName = new JTextField(20);
        fullName.setFont(new Font("Arial", Font.PLAIN, 20));
        fullName.setForeground(Color.black);
        fullName.setBackground(Color.decode("#D9D9D9"));
        fullName.setMargin(new Insets(10, 10, 10, 10)); 
        fullName.setEnabled(false); 
        fullName.setText(Program.current_user.getName());
        JPanel panelFullName = new JPanel();
        panelFullName.add(fullNameLB);
        panelFullName.add(fullName);
        panelFullName.setBackground(Color.decode("#609DB7"));
        
        JLabel moneyLB = new JLabel("Số tiền     ");
        moneyLB.setFont(new Font("Arial", Font.PLAIN, 20)); 
        moneyLB.setForeground(Color.black);
//        fullNameLB.setHorizontalAlignment(JLabel.Left); 	
        JTextField money = new JTextField(20);
        money.setFont(new Font("Arial", Font.PLAIN, 20));
        money.setForeground(Color.black);
        money.setBackground(Color.decode("#D9D9D9"));
        money.setMargin(new Insets(10, 10, 10, 10)); 
        money.setEnabled(false); 
        
        String formatBalance = new DecimalFormat("#,###").format(Program.current_user.getBalance());
        money.setText(formatBalance);
        JPanel panelMoney = new JPanel();
        panelMoney.add(moneyLB);
        panelMoney.add(money);
        panelMoney.setBackground(Color.decode("#609DB7"));

        JLabel label1 = new JLabel("Nhấn cancel để thoát");
        label1.setFont(new Font("Arial", Font.PLAIN, 15)); 
        label1.setForeground(Color.black);
        label1.setHorizontalAlignment(JLabel.CENTER); 
      
        
        JButton buttonCancel = new JButton("Hủy bỏ");
        buttonCancel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonCancel.setBackground(Color.decode("#D9D9D9"));
        buttonCancel.setMargin(new Insets(10, 10, 10, 10)); 
        buttonCancel.setPreferredSize(new Dimension(100, 50));
        buttonCancel.addActionListener(this);
        
        JPanel panelMessage = new JPanel();
        label1.setPreferredSize(new Dimension(600, 30));
        panelMessage.add(label1);
        panelMessage.setBackground(Color.decode("#609DB7"));
        panelMessage.setPreferredSize(new Dimension(600, 30));
        
        JPanel panelButton = new JPanel();
        panelButton.add(buttonCancel);
        panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButton.setPreferredSize(new Dimension(600, 60));
        panelButton.setBackground(Color.decode("#609DB7"));
       
        add(subtitle);
        add(panelFullName);
        add(panelMoney);
        add(panelMessage);
        add(panelButton);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
 
	}
	public static void main(String[] args) {
		new BalanceInquiry();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Hủy bỏ")) {
			this.setVisible(false);
			new Home();
		}
	}
	
}
