package mo_phong_atm.view.Transfer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mo_phong_atm.Program;
import mo_phong_atm.Transaction;
import mo_phong_atm.User;
import mo_phong_atm.view.FailTransaction;
import mo_phong_atm.view.Home;

public class AmountMoney extends JFrame implements ActionListener {
	private JButton button1, button2, button3, button4, button5, button6;
	private Transaction transaction;
	private User transferedUser;
	public AmountMoney(Transaction transaction, User TransferedUser) {
		this.transaction = transaction;
		this.transferedUser = TransferedUser;
		setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        setLayout(new BorderLayout(10, 10));
        ImageIcon logoIcon = new ImageIcon("vietinbank.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        add(logoLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(Color.decode("#609DB7"));
        JLabel textLabel = new JLabel("Vui lòng nhập số tiền");
        textLabel.setFont(new Font(textLabel.getFont().getFontName(), Font.BOLD, 16));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        centerPanel.add(textLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // Sử dụng GridLayout cho buttonPanel với 1 hàng và 2 cột
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.decode("#609DB7"));
        	button1 = new JButton("100.000");
        	button2 = new JButton("200.000");
        	button3	 = new JButton("500.000");
        	button4 = new JButton("1.000.000");
        	button5 = new JButton("2.000.000");
        	button6 = new JButton("Chọn số khác");
         
            // Thay đổi kích thước các ô button nhỏ
            Dimension buttonSize = new Dimension(120, 40);
            button1.setPreferredSize(buttonSize);
            button2.setPreferredSize(buttonSize);
            button3.setPreferredSize(buttonSize);
            button4.setPreferredSize(buttonSize);
            button5.setPreferredSize(buttonSize);
            button6.setPreferredSize(buttonSize);
            
        	button1.addActionListener(this);
        	button2.addActionListener(this);
        	button3.addActionListener(this);
        	button4.addActionListener(this);
        	button5.addActionListener(this);
        	button6.addActionListener(this);
         
         
	    	 JPanel leftColumnPanel = new JPanel(new GridLayout(3, 1, 10, 10));
	         leftColumnPanel.add(button1);
	         leftColumnPanel.add(button2);
	         leftColumnPanel.add(button3);
	         leftColumnPanel.setBackground(Color.decode("#609DB7"));
	        
	         JPanel rightColumnPanel = new JPanel(new GridLayout(3, 1, 10, 10));
	         rightColumnPanel.add(button4);
	         rightColumnPanel.add(button5);
	         rightColumnPanel.add(button6);
	         rightColumnPanel.setBackground(Color.decode("#609DB7"));
	         
	         buttonPanel.add(leftColumnPanel);
	         buttonPanel.add(rightColumnPanel);
	
	         centerPanel.add(buttonPanel, BorderLayout.CENTER);
	
	         add(centerPanel, BorderLayout.CENTER);
	
	         JLabel feeLabel = new JLabel("Ngân hàng sẽ thu phí dịch vụ chuyển tiền 300vnd/giao dịch");
	         feeLabel.setHorizontalAlignment(JLabel.CENTER);
	         add(feeLabel, BorderLayout.SOUTH);
         
   
//       setLayout(null);
         setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Chọn số khác")) {
			
			new AmountMoneyElse(transaction, transferedUser);
			this.setVisible(false);
		}else {
			if(Program.current_user == null) {
				return;	
			}
			int amountMoney = Integer.parseInt(src.replace(".", ""));
			System.out.println(amountMoney);
			int CurrentUserMoney = Program.current_user.getBalance();
			if(amountMoney > CurrentUserMoney) {
				//thất bại
				this.setVisible(false);
				ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
				FailTransaction failTransaction = new FailTransaction("Chuyển khoảng không thành công", "Số tiền trong tài khoảng không đủ để thực hiện giao dịch");		
				executor.schedule(() -> {
//					
					failTransaction.setVisible(false);
					new Home();
		        }, 3, TimeUnit.SECONDS);
				
			}
			else {
				//cập nhật thông tin database
				Program.current_user.setBalance(CurrentUserMoney - amountMoney);
				User.update(Program.con, Program.current_user);
				transferedUser.setBalance(transferedUser.getBalance() + amountMoney);
				User.update(Program.con, transferedUser);
				
				transaction.setTransfered_user_id(transferedUser.getId());
				transaction.setAmount(amountMoney);
				try {
					Transaction.create(Program.con, transaction);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.setVisible(false);
				new Bill(transaction, transferedUser);
				
			}
			
			
			
		}
		
	}
}
