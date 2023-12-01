package mo_phong_atm.view.changePin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import mo_phong_atm.view.SuccessTransaction;

public class ChangePin extends JFrame implements ActionListener {
	private JLabel subtitle, label1;
	private JTextField textField;
	private JButton buttonOk, buttonClear, buttonCancel;
	private Transaction transaction;
	public ChangePin(Transaction transaction) {
		this.transaction = transaction;
        setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        subtitle = new JLabel("Vui lòng nhập số PIN mới, sau đó nhấn ");
        subtitle.setFont(new Font("Arial", Font.BOLD, 25)); 
        subtitle.setForeground(Color.black);
        subtitle.setHorizontalAlignment(JLabel.CENTER); 
        
        
        textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setForeground(Color.black);
        textField.setBackground(Color.decode("#D9D9D9"));
        textField.setMargin(new Insets(10, 10, 10, 10)); 
        
//        JLabel vndLabel = new JLabel("VND");
//        vndLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
//        vndLabel.setForeground(Color.black);
//        vndLabel.setHorizontalAlignment(JLabel.CENTER); 
//        vndLabel.setOpaque(true);
//        vndLabel.setBackground(Color.decode("#D9D9D9"));
//        vndLabel.set
//        vndLabel.setMargin(new Insets(10, 10, 10, 10)); 
         label1 = new JLabel("Độ dài của PIN là 6 số");
        label1.setFont(new Font("Arial", Font.PLAIN, 15)); 
        label1.setForeground(Color.black);
        label1.setHorizontalAlignment(JLabel.CENTER); 
        
       
//        
//        JLabel label2 = new JLabel("(Nhấn Cancel để thoát)");
//        label2.setFont(new Font("Arial", Font.PLAIN, 15)); 
//        label2.setForeground(Color.black);
//        label2.setHorizontalAlignment(JLabel.CENTER); 
       
        
         buttonOk = new JButton("Đồng ý");
        buttonOk.setFont(new Font("Arial", Font.BOLD, 16));
        buttonOk.setBackground(Color.decode("#D9D9D9"));
        buttonOk.setMargin(new Insets(10, 10, 10, 10)); 
        buttonOk.setPreferredSize(new Dimension(100, 50));
        
         buttonCancel = new JButton("Hủy bỏ");
        buttonCancel.setFont(new Font("Arial", Font.BOLD, 16));
        buttonCancel.setBackground(Color.decode("#D9D9D9"));
        buttonCancel.setMargin(new Insets(10, 10, 10, 10)); 
        buttonCancel.setPreferredSize(new Dimension(100, 50));
        
        buttonClear = new JButton("Nhập lại");
        buttonClear.setFont(new Font("Arial", Font.BOLD, 16));
        buttonClear.setBackground(Color.decode("#D9D9D9"));
        buttonClear.setMargin(new Insets(10, 10, 10, 10)); 
        buttonClear.setPreferredSize(new Dimension(100, 50));
        
        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
        buttonClear.addActionListener(this);
        JPanel panelTextField = new JPanel();
        panelTextField.add(textField);
//        panelTextField.add(vndLabel);
        panelTextField.setBackground(Color.decode("#609DB7"));
        
        JPanel panelMessage = new JPanel();
        label1.setPreferredSize(new Dimension(600, 30));
//        label2.setPreferredSize(new Dimension(600, 30));
        panelMessage.add(label1);
//        panelMessage.add(label2);
        panelMessage.setBackground(Color.decode("#609DB7"));
        panelMessage.setPreferredSize(new Dimension(600, 60));
        
        JPanel panelButton = new JPanel();
        panelButton.add(buttonClear);
        panelButton.add(buttonCancel);
        panelButton.add(buttonOk);
        panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButton.setPreferredSize(new Dimension(600, 60));
        panelButton.setBackground(Color.decode("#609DB7"));
       
        add(subtitle);
        add(panelTextField);
        add(panelMessage);
        add(panelButton);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
//        setLayout(null);
        setVisible(true);
 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();

			
		if(src.equals("Đồng ý")) {
			String pinValue = textField.getText();
			if(pinValue.equals("")) {
				return;
			}
			
			
			if(Program.current_user == null) {
				return;	
			}
			Program.current_user.setPin(pinValue);
			User.update(Program.con, Program.current_user);
			try {
				Transaction.create(Program.con, transaction);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Program.current_user = null;
			this.setVisible(false);
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			SuccessTransaction successTransaction = new SuccessTransaction("Giao dịch đổi pin thành công", "Cảm ơn quý khách đã giao dịch ");		
			executor.schedule(() -> {
//				
				successTransaction.setVisible(false);
				new Home();
	        }, 3, TimeUnit.SECONDS);
			
		}else if(src.equals("Hủy bỏ")){
			this.setVisible(false);
			new Home();
	//	
		}else if(src.equals("Nhập lại")){
	//
			textField.setText("");
	//	
		}
	}
	
}
