package mo_phong_atm.view.withdrawMoney;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mo_phong_atm.Program;
import mo_phong_atm.Transaction;
import mo_phong_atm.User;
import mo_phong_atm.view.FailTransaction;
import mo_phong_atm.view.Home;


public class AmountMoneyElse  extends JFrame implements ActionListener{
	private JTextField amountMoneyTF;
	private JButton okButton, cancelButton, clearButton;
	private Transaction transaction;
//	
		public AmountMoneyElse(Transaction transaction) {
		this.transaction = transaction;
		
		setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        setLayout(new BorderLayout());
        
        ImageIcon logo = new ImageIcon("path/to/logo.png");
        JLabel lblLogo = new JLabel(logo);
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(Color.decode("#609DB7"));
        headerPanel.add(lblLogo);
        // Đặt màu nền cho headerPanel
        add(headerPanel, BorderLayout.NORTH);
        
        // Tạo panel chứa các thành phần chính
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#609DB7"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        JLabel lblText = new JLabel("Nhập số tiền mà bạn muốn giao dịch");
//        lblText.setForeground(Color.BLUE); // Đặt màu chữ cho lblText
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblText, gbc);
        
        
        amountMoneyTF = new JTextField(20);
        amountMoneyTF.setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho txtInput
        amountMoneyTF.setMargin(new Insets(10, 10, 10, 10)); 
        gbc.gridy = 1;
        mainPanel.add(amountMoneyTF, gbc);
        
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Sử dụng GridLayout để cân bằng vị trí các nút
        buttonPanel.setBackground(Color.decode("#609DB7"));
        okButton = new JButton("Đồng ý");
//        okButton.setBackground(Color.GREEN); // Đặt màu nền cho btnDongY
        okButton.setMargin(new Insets(10, 10, 10, 10)); 
        okButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(okButton);
     
        clearButton = new JButton("Nhập lại");
//        clearButton.setBackground(Color.YELLOW); // Đặt màu nền cho btnNhapLai
        clearButton.setMargin(new Insets(10, 10, 10, 10)); 
        clearButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(clearButton);
        cancelButton = new JButton("Hủy bỏ");
        
//        cancelButton.setBackground(Color.RED); // Đặt màu nền cho btnCancel
        cancelButton.setMargin(new Insets(10, 10, 10, 10)); 
        cancelButton.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(cancelButton);
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);
        
        
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        mainPanel.add(buttonCancel, gbc);
        
       
        add(mainPanel, BorderLayout.CENTER);

//        pack();
      
        cancelButton.addActionListener(this);
        okButton.addActionListener(this);
        clearButton.addActionListener(this);
////        JPanel panel = new JPanel();
////        panel.setLayout(new BorderLayout());
////
////        // Tạo label cho hình ảnh logo Vietinbank
////        ImageIcon logo = new ImageIcon("path/to/logo.png");
////        JLabel logoLabel = new JLabel(logo);
////        panel.add(logoLabel, BorderLayout.NORTH);
////
////        // Tạo panel chứa hai ô button và ô nhập
////        JPanel buttonPanel = new JPanel();
////        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0)); // Use GridLayout for buttonPanel
////
////        JButton button1 = new JButton("Không có gì");
////        JButton button2 = new JButton("VND");
//
////        buttonPanel.add(button1);
////        buttonPanel.add(button2);
////        panel.add(buttonPanel, BorderLayout.SOUTH);
//
//        // Tạo panel chứa ô nhập và label "Số tiền nhập vào phải là VND"
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for inputPanel
//        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add some padding
//
//        JLabel textLabel = new JLabel("Nhập số tiền mà bạn muốn giao dịch");
//        inputPanel.add(textLabel);
//
//        amountMoneyTF = new JTextField(20);
//        inputPanel.add(amountMoneyTF);
//        JLabel errorLabel = new JLabel("Số tiền nhập vào phải là VND");
//        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        inputPanel.add(errorLabel);
//
////        panel.add(inputPanel, BorderLayout.CENTER);
//     
//        JPanel buttonPanel2 = new JPanel();
//        buttonPanel2.setLayout(new GridLayout(1, 2, 10, 0)); // Use GridLayout for buttonPanel2
//        buttonPanel2.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add some padding
//       
//        okButton = new JButton("Đồng ý");
//        cancelButton = new JButton("Hủy bỏ");
//
//        okButton.addActionListener(this);
//        cancelButton.addActionListener(this);       
//
//         buttonPanel2.add(okButton);
//         buttonPanel2.add(cancelButton);
//         panel.add(buttonPanel2, BorderLayout.SOUTH);
//
//         // Thêm panel vào frame
//         getContentPane().add(panel);
         
      
//       setLayout(null);
         setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Hủy bỏ")) {
			this.setVisible(false);
			new Home();
		}else if(src.equals("Nhập lại")){
			//
			amountMoneyTF.setText("");
//		
		}
		else if(src.equals("Đồng ý")) {
			if(Program.current_user == null) {
				return;	
			}
			int amountMoney = Integer.parseInt(amountMoneyTF.getText());
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
				Program.current_user.setBalance(CurrentUserMoney - amountMoney);
				User.update(Program.con, Program.current_user);
				transaction.setAmount(amountMoney);
				try {
					Transaction.create(Program.con, transaction);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				this.setVisible(false);
				new Bill(transaction);
			}
		}
	}

}
