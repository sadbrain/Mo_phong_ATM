package mo_phong_atm.view.withdrawMoney;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mo_phong_atm.Program;
import mo_phong_atm.Transaction;
import mo_phong_atm.User;
import mo_phong_atm.view.Home;
import mo_phong_atm.view.SuccessTransaction;

public class Bill extends JFrame implements ActionListener{
	private JButton button1, button2;
	private JLabel messageLabel;
	private Transaction transaction;

	public Bill(Transaction transaction) {
		this.transaction = transaction;
		
		setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để kiểm soát vị trí và kích thước của các thành phần
        
        messageLabel = new JLabel("Bạn có muốn nhận hóa đơn không?");
        messageLabel.setFont(new Font(messageLabel.getFont().getName(), Font.PLAIN, 30)); // Đặt cỡ chữ là 30px
        messageLabel.setHorizontalAlignment(JLabel.CENTER); 
        messageLabel.setVerticalAlignment(JLabel.CENTER); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER; // Đặt văn bản ở giữa màn hình
        gbc.insets = new Insets(10, 10, 10, 10); 

        add(messageLabel, gbc);
       
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1)); 
        buttonPanel.setOpaque(false); 
        button1 = new JButton("Có");
        button1.setFont(new Font(button1.getFont().getName(), Font.PLAIN, 30)); // Đặt cỡ chữ là 30px

        button2 = new JButton("Không");
        button2.setFont(new Font(button2.getFont().getName(), Font.PLAIN, 30)); // Đặt cỡ chữ là 30px

        	
    	
    	button2 = new JButton("Không");
    	button2.setFont(new Font(button2.getFont().getName(), Font.PLAIN, 30));
    	button1.addActionListener(this);
    	button2.addActionListener(this);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Đặt JPanel ở góc phải
        gbc.insets = new Insets(0, 0, 10, 10); 

        add(buttonPanel, gbc);

         
//       setLayout(null);
      
      
         setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Có")) {
			
			User current_user = Program.current_user;
			LocalDateTime date_time = transaction.getDatetime();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
	        String formattedDateTime = date_time.format(formatter);
	        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("vi-VN"));
	        
		 	if(current_user != null) {
		 		String filePath = "bill_of_" +  current_user.getName() + "_at_" +formattedDateTime + ".txt";
		        try {
		            // Tạo một đối tượng FileWriter với đường dẫn của tệp tin
		        	
		       
		            FileWriter fileWriter = new FileWriter(filePath);
		            
		            // Viết nội dung vào tệp tin
		           
		            fileWriter.write("Người gủi: " + current_user.getName() +"\n");
		            fileWriter.write("Hình thức: " + transaction.getType() + "\n");
		            fileWriter.write("Số tiền: " + currencyFormat.format(transaction.getAmount()) + "\n");
		            fileWriter.write("Ngày thực hiện giao dịch: " + date_time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "\n");
		            fileWriter.write("Số tiền còn lại: " + currencyFormat.format(current_user.getBalance()) + "\n");
		            fileWriter.write("Hello, this is a sample text written to a file 2.");
		            // Đóng FileWriter để giải phóng tài nguyên
		            fileWriter.close();

		            System.out.println("File has been written successfully.");

		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
		        
		        try {
		            // Kiểm tra xem Desktop được hỗ trợ trên hệ thống không
		            if (Desktop.isDesktopSupported()) {
		                // Lấy đối tượng Desktop
		                Desktop desktop = Desktop.getDesktop();

		                // Mở tệp tin
		                File file = new File(filePath);
		                desktop.open(file);

		                System.out.println("File has been opened successfully.");

		            } else {
		                System.out.println("Desktop is not supported on this platform.");
		            }

		        } catch (IOException e1) {
		            e1.printStackTrace();
		        }
        	}

		}
		
		
			
		Program.current_user = null;
		this.setVisible(false);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		SuccessTransaction successTransaction = new SuccessTransaction("Giao dịch rút tiền thành công", "Cảm ơn quý khách đã giao dịch ");		
		executor.schedule(() -> {
//			
			successTransaction.setVisible(false);
			new Home();
        }, 3, TimeUnit.SECONDS);
	}

}
