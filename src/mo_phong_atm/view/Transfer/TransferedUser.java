package mo_phong_atm.view.Transfer;

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
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mo_phong_atm.Program;
import mo_phong_atm.Transaction;
import mo_phong_atm.User;
import mo_phong_atm.view.FailTransaction;
import mo_phong_atm.view.Home;
import mo_phong_atm.view.Services;

public class TransferedUser extends JFrame implements ActionListener {
//	private JLabel subtitle, stkLB, pinLB;
	private JTextField stk;
	private JButton buttonOk, buttonClear, buttonCancel;
	private Transaction transaction;
	private User TransferedUser;
//	
	public TransferedUser(Transaction transaction) {
		this.transaction = transaction;
		setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        setLayout(new BorderLayout());
        
        // Thêm hình ảnh logo Vietinbank vào phần header
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
        JLabel lblText = new JLabel("Vui lòng nhập số tài khoản/ Số thẻ thụ hưởng");
        lblText.setForeground(Color.BLUE); // Đặt màu chữ cho lblText
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(lblText, gbc);
        
        stk = new JTextField(20);
        stk.setBackground(Color.LIGHT_GRAY); // Đặt màu nền cho txtInput
        stk.setMargin(new Insets(10, 10, 10, 10)); 
        gbc.gridy = 1;
        mainPanel.add(stk, gbc);
        
        
        // Tạo panel chứa hai nút "Đồng ý" và "Nhập lại"
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 0)); // Sử dụng GridLayout để cân bằng vị trí các nút
        buttonPanel.setBackground(Color.decode("#609DB7"));
        buttonOk = new JButton("Đồng ý");
        buttonOk.setBackground(Color.GREEN); // Đặt màu nền cho btnDongY
        buttonOk.setMargin(new Insets(10, 10, 10, 10)); 
        buttonOk.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(buttonOk);
     
        buttonClear = new JButton("Nhập lại");
        buttonClear.setBackground(Color.YELLOW); // Đặt màu nền cho btnNhapLai
        buttonClear.setMargin(new Insets(10, 10, 10, 10)); 
        buttonClear.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(buttonClear);
        buttonCancel = new JButton("Hủy bỏ");
        
        buttonCancel.setBackground(Color.RED); // Đặt màu nền cho btnCancel
        buttonCancel.setMargin(new Insets(10, 10, 10, 10)); 
        buttonCancel.setPreferredSize(new Dimension(100, 50));
        buttonPanel.add(buttonCancel);
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);
        
        
//        gbc.gridy = 3;
//        gbc.gridwidth = 2;
//        mainPanel.add(buttonCancel, gbc);
        
       
        add(mainPanel, BorderLayout.CENTER);

//        pack();
      
        buttonCancel.addActionListener(this);
        buttonOk.addActionListener(this);
        buttonClear.addActionListener(this);
       
    

        setVisible(true);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		
		if(src.equals("Đồng ý")) {
//			
			String stkValue = stk.getText();
			if(stkValue.equals("")) {
				return;
			}
//			
			if(checkTranferedUser()) {
				this.setVisible(false);
				new AmountMoney(transaction, TransferedUser);
			}else {
//	
				this.setVisible(false);
				ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
				FailTransaction failTransaction = new FailTransaction("Người nhận không tồn tại", "Số tài khoản của người nhận không đúng");		
				executor.schedule(() -> {
//					
					failTransaction.setVisible(false);
					new Home();
		        }, 3, TimeUnit.SECONDS);
//				
//
//				
			}
//
		}else if(src.equals("Hủy bỏ")){
			this.setVisible(false);
			new Home();
//		
		}else if(src.equals("Nhập lại")){
//
			stk.setText("");
//		
		}
	}
	private boolean checkTranferedUser() {
		
		String stkValue = stk.getText();
		ArrayList<User> users = User.load(Program.con);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if(user.getAccount_number().equals(stkValue)) {
            	TransferedUser = user;
            	return true;
            }
        }
        
        return false;
		
				
	}
}
