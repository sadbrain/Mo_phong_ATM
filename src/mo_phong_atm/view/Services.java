package mo_phong_atm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mo_phong_atm.Program;
import mo_phong_atm.Transaction;
import mo_phong_atm.view.Login.Login;
import mo_phong_atm.view.Transfer.TransferedUser;
import mo_phong_atm.view.balanceInquiry.BalanceInquiry;
import mo_phong_atm.view.changePin.ChangePin;
import mo_phong_atm.view.withdrawMoney.AmountMoney;

public class Services extends JFrame implements ActionListener {
	private JButton buttonWithdrawMoney, buttonChangePin, buttonTransfer,
	buttonDisplayInfo, buttonPay, buttonServiceEsle;
	private JLabel titleLabel;
	public Services() {
		setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10)); 
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); 
        titleLabel = new JLabel("Vui lòng lựa chọn giao dịch");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20)); // Đặt kích thước chữ là 30px
        panel.add(titleLabel);
        panel.add(new JLabel());
        panel.setBackground(Color.decode("#609DB7"));
         buttonWithdrawMoney = new JButton("Rút tiền");
         buttonChangePin = new JButton("Đổi PIN");
         buttonTransfer	 = new JButton("Chuyển khoản");
         buttonDisplayInfo = new JButton("Vấn tin số dư");
         buttonPay = new JButton("Thanh toán");
         buttonServiceEsle = new JButton("Dịch vụ khác ");
         
         buttonWithdrawMoney.addActionListener(this);
         buttonChangePin.addActionListener(this);
         buttonTransfer.addActionListener(this);
         buttonDisplayInfo.addActionListener(this);
         buttonPay.addActionListener(this);
         buttonServiceEsle.addActionListener(this);
         
         
         Dimension buttonSize = new Dimension(140, 40); // Kích thước ô nút là 30x30 pixel
         buttonWithdrawMoney.setPreferredSize(buttonSize);
         buttonChangePin.setPreferredSize(buttonSize);
         buttonTransfer.setPreferredSize(buttonSize);
         buttonDisplayInfo.setPreferredSize(buttonSize);
         buttonPay.setPreferredSize(buttonSize);
         buttonServiceEsle.setPreferredSize(buttonSize);
         
         panel.add(buttonWithdrawMoney);
         panel.add(buttonChangePin);
         panel.add(buttonTransfer);
         panel.add(buttonDisplayInfo);
         panel.add(buttonPay);
         panel.add(buttonServiceEsle);
         add(panel);
         setLayout(new FlowLayout(FlowLayout.CENTER));
//       setLayout(null);
         setVisible(true);
	}
	public static void main(String[] args) {
		new Services();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Rút tiền")) {
			Transaction transaction = new Transaction();
			transaction.setUser_id(Program.current_user.getId());
			transaction.setType("Rút tiền");
			LocalDateTime currentDateTime = LocalDateTime.now();
			transaction.setDatetime(currentDateTime);
			
			this.setVisible(false);
			new AmountMoney(transaction);
			
//			this.setVisible(false);
//			new Login();
		}else if(src.equals("Đổi PIN")) {
			Transaction transaction = new Transaction();
			transaction.setUser_id(Program.current_user.getId());
			transaction.setType("Đổi PIN");
			LocalDateTime currentDateTime = LocalDateTime.now();
			transaction.setDatetime(currentDateTime);
			
			this.setVisible(false);
			new ChangePin(transaction);
			
		}else if(src.equals("Chuyển khoản")) 
		{
			if(Program.current_user != null) {
				//tạo một giao dịch 
				Transaction transaction = new Transaction();
				transaction.setUser_id(Program.current_user.getId());
				transaction.setType("Chuyển khoản");
				LocalDateTime currentDateTime = LocalDateTime.now();
				transaction.setDatetime(currentDateTime);
				
				
				this.setVisible(false);
				new TransferedUser(transaction);
			}

		}else if(src.equals("Vấn tin số dư")) {
			this.setVisible(false);
			new BalanceInquiry();
			
		}else if(src.equals("Thanh toán")) {
			
		}else if(src.equals("Dịch vụ khác ")) {
			
		}
		
		
		
		
	}
}
