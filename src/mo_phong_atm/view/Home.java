package mo_phong_atm.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mo_phong_atm.view.Login.Login;

public class Home extends JFrame implements ActionListener{
	public Home() {
        setTitle("VietinBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#609DB7"));
        
        JLabel label = new JLabel("Chào mừng bạn đến với VietinBank");
        label.setFont(new Font("Arial", Font.PLAIN, 25)); 
        label.setForeground(Color.black);
        label.setHorizontalAlignment(JLabel.CENTER);     
        
        JButton button = new JButton("Tiếp tục");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#D9D9D9"));
        button.setMargin(new Insets(10, 10, 10, 10)); 
        button.setPreferredSize(new Dimension(100, 50));
        button.addActionListener(this);
        
        JPanel panelButton = new JPanel();
        panelButton.add(button);
        panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButton.setPreferredSize(new Dimension(600, 100));
        panelButton.setBackground(Color.decode("#609DB7"));
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        add(label);
        add(panelButton);
        setLayout(new FlowLayout(FlowLayout.CENTER));
//      setLayout(null);
        setVisible(true);
        	
	}
	
	
	public static void main(String[] args) {
		new Home();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("Tiếp tục")) {
			this.setVisible(false);
			new Login();
		}
		
	}
}
