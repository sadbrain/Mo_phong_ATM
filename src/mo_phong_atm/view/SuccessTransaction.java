package mo_phong_atm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuccessTransaction extends JFrame {
	public SuccessTransaction(String title, String messageError) {
        setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        setLayout(new GridBagLayout()); 
        
        JLabel subtitle = new JLabel(title);
        subtitle.setFont(new Font("Arial", Font.BOLD, 25)); 
        subtitle.setForeground(Color.decode("#903535"));
        subtitle.setHorizontalAlignment(JLabel.CENTER); // Căn văn bản vào giữa
        subtitle.setVerticalAlignment(JLabel.CENTER); // Căn văn bản vào giữa
        subtitle.setFont(new Font(subtitle.getFont().getName(), Font.PLAIN, 20)); // Đặt kích thước chữ là 20px

        
//        JLabel labelImg = new JLabel();
//        labelImg.setHorizontalAlignment(JLabel.CENTER); 
//        ImageIcon icon = new ImageIcon("icon-dấu-tích-xanh-400x400 1.png");
//        labelImg.setIcon(icon);
        
        
        JLabel message = new JLabel(messageError);
        message.setFont(new Font("Arial", Font.PLAIN, 18)); 
        message.setForeground(Color.black);
        message.setHorizontalAlignment(JLabel.CENTER);      
        message.setVerticalAlignment(JLabel.CENTER); // Căn văn bản vào giữa
        JPanel circlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GREEN); // Màu xanh lá cây
                g.fillOval(0, 0, 40, 40); // Vẽ một hình tròn với kích thước 40x40 pixel
                g.setColor(Color.WHITE); // Đặt màu chữ là màu trắng
                g.setFont(new Font(g.getFont().getName(), Font.BOLD, 20)); // Đặt kích thước chữ là 20px và đậm
                g.drawString("✓", 14, 28); // Vẽ dấu tích thành công
            }
        };
        circlePanel.setPreferredSize(new Dimension(40, 40)); // Kích thước của panel là 40x40 pixel
        circlePanel.setBorder(null); // Loại bỏ viền

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER; // Đặt văn bản và ô tròn ở giữa màn hình
        gbc.insets = new Insets(10, 10, 10, 10); // Tạo khoảng cách 10px từ viền khung

        add(subtitle, gbc);
        gbc.gridy = 1;
        add(circlePanel, gbc);

        gbc.gridy = 2;
        add(message, gbc);
        
//        add(subtitle);
////        add(labelImg);
//        
//        add(message);
//        add(panelMessage);
//        add(panelButton);

//        setLayout(null);
        setVisible(true);
 
	}
	public static void main(String[] args) {
		
	}
}
