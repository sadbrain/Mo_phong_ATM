package mo_phong_atm.view.changePin;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ChangePinFail extends JFrame{
	public ChangePinFail() {
        setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        JLabel subtitle = new JLabel("đổi pin không thành công  ");
        subtitle.setFont(new Font("Arial", Font.BOLD, 25)); 
        subtitle.setForeground(Color.decode("#903535"));
        subtitle.setHorizontalAlignment(JLabel.CENTER); 
        
        
        JLabel labelImg = new JLabel();
        labelImg.setHorizontalAlignment(JLabel.CENTER); 
        ImageIcon icon = new ImageIcon("icon-dấu-tích-xanh-400x400 1.png");
        labelImg.setIcon(icon);
        
        
        JLabel message = new JLabel("mã pin của quý khách vừa nhập không đúng ");
        message.setFont(new Font("Arial", Font.PLAIN, 18)); 
        message.setForeground(Color.black);
        message.setHorizontalAlignment(JLabel.CENTER);      
        add(subtitle);
        add(labelImg);
        add(message);
//        add(panelMessage);
//        add(panelButton);
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
//        setLayout(null);
        setVisible(true);
 
	}
	public static void main(String[] args) {
		new ChangePinFail();
	}
	
}
