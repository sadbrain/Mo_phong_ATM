package mo_phong_atm.view.Login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mo_phong_atm.Program;
import mo_phong_atm.User;
import mo_phong_atm.view.FailTransaction;
import mo_phong_atm.view.Home;
import mo_phong_atm.view.Services;
import mo_phong_atm.view.balanceInquiry.BalanceInquiry;

public class Login extends JFrame implements ActionListener{
	private JLabel subtitle, stkLB, pinLB;
	private JTextField stk, pin;
	private JButton buttonCancel, buttonOK;
	public Login() {
        setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        subtitle = new JLabel("Xin vui lòng nhập số PIN và STK");
        subtitle.setFont(new Font("Arial", Font.BOLD, 25)); 
        subtitle.setForeground(Color.black);
        subtitle.setHorizontalAlignment(JLabel.CENTER); 
        
        stkLB = new JLabel("Số tài khoản");
        stkLB.setFont(new Font("Arial", Font.PLAIN, 20)); 
        stkLB.setForeground(Color.black);
        
        stk = new JTextField(20);
        stk.setFont(new Font("Arial", Font.PLAIN, 20));
        stk.setForeground(Color.black);
        stk.setBackground(Color.decode("#D9D9D9"));
        stk.setMargin(new Insets(10, 10, 10, 10)); 
        
        JPanel panelSTK = new JPanel();
        panelSTK.add(stkLB);
        panelSTK.add(stk);
        panelSTK.setBackground(Color.decode("#609DB7"));
        
        pinLB = new JLabel("PIN              ");
        pinLB.setFont(new Font("Arial", Font.PLAIN, 20)); 
        pinLB.setForeground(Color.black);
//        fullNameLB.setHorizontalAlignment(JLabel.Left); 	
        pin = new JTextField(20);
        pin.setFont(new Font("Arial", Font.PLAIN, 20));
        pin.setForeground(Color.black);
        pin.setBackground(Color.decode("#D9D9D9"));
        pin.setMargin(new Insets(10, 10, 10, 10)); 
     
        JPanel panelPin = new JPanel();
        panelPin.add(pinLB);
        panelPin.add(pin);
        panelPin.setBackground(Color.decode("#609DB7"));
      
        
        buttonCancel = new JButton("Hủy bỏ");
        buttonCancel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonCancel.setBackground(Color.decode("#D9D9D9"));
        buttonCancel.setMargin(new Insets(10, 10, 10, 10)); 
        buttonCancel.setPreferredSize(new Dimension(100, 50));

        
        buttonOK = new JButton("Đồng ý");
        buttonOK.setFont(new Font("Arial", Font.BOLD, 16));
        buttonOK.setBackground(Color.decode("#D9D9D9"));
        buttonOK.setMargin(new Insets(10, 10, 10, 10)); 
        buttonOK.setPreferredSize(new Dimension(100, 50));
        buttonOK.addActionListener(this);
        buttonCancel.addActionListener(this);
        
        JPanel panelButtonCancel = new JPanel();
        panelButtonCancel.add(buttonCancel);
        panelButtonCancel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButtonCancel.setPreferredSize(new Dimension(600, 60));
        panelButtonCancel.setBackground(Color.decode("#609DB7"));
       
        
        JPanel panelButtonOk = new JPanel();
        panelButtonOk.add(buttonOK);
        panelButtonOk.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButtonOk.setPreferredSize(new Dimension(600, 60));
        panelButtonOk.setBackground(Color.decode("#609DB7"));
        
        add(subtitle);
        add(panelSTK);
        add(panelPin);
        add(panelButtonOk);
        add(panelButtonCancel);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
 
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Đồng ý")) {
			String stkValue = stk.getText();
			String pinValue = pin.getText();
			if(stkValue.equals("") || pinValue.equals("")) {
				return;
			}
			if(login()) {
				this.setVisible(false);
				new Services();
			}else {
				this.setVisible(false);
				ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
				FailTransaction failTransaction = new FailTransaction("Đăng nhập không thành công", "Mã pin hoặc stk của quý khách vừa nhập không đúng");

				executor.schedule(() -> {
					
					failTransaction.setVisible(false);
					new Home();
		        }, 3, TimeUnit.SECONDS);

				
			}

		}else if(src.equals("Hủy bỏ")){
			this.setVisible(false);
			new Home();
		}
		
	}
	private boolean login() {
		
		String stkValue = stk.getText();
		String pinValue = pin.getText();
		ArrayList<User> users = User.load(Program.con);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if(user.getAccount_number().equals(stkValue) && user.getPin().equals(pinValue)) {
            	Program.current_user = user;
            	return true;
            }
        }
        
        return false;
		
		
		
		
	}
	public static void main(String[] args) {
		new Login();
	}
	
	
}
