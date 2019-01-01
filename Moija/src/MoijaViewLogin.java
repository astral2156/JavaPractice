import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MoijaViewLogin extends JPanel {
	private JPanel mainPanel;
	private JLabel idLbl, pwLbl;
	protected JButton registerBtn, loginBtn;
	protected JTextField idTxt, pwTxt;
	private actionListener action;
	
	
	
	public MoijaViewLogin(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800,400));
		setLayout(null);
		
		action =new actionListener();
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(10, 10, 780, 380);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(mainPanel);
		
		idLbl = new JLabel("ID : ");
		idLbl.setPreferredSize(new Dimension(30,17));
		idLbl.setVisible(true);
		mainPanel.add(idLbl);
		
		idTxt = new JTextField(15);
		idTxt.setBounds(600, 200, 60, 30);
		idTxt.setVisible(true);
		mainPanel.add(idTxt);
		
		pwLbl = new JLabel("PW : ");
		//idLbl.setPreferredSize(new Dimension(30,17));
		pwLbl.setVisible(true);
		pwLbl.setBounds(20, 20, 60, 17);
		mainPanel.add(pwLbl);
		
		pwTxt =new JTextField(15);
		pwTxt.setVisible(true);
		mainPanel.add(pwTxt);
		
		registerBtn = new JButton("register");
		registerBtn.setFont(new Font("Verdana", Font.BOLD,10));
		registerBtn.setBackground(Color.white);
		registerBtn.setPreferredSize(new Dimension(150,70));
		registerBtn.setForeground(Color.black);
		registerBtn.addActionListener(action);
		mainPanel.add(registerBtn);
		
		loginBtn = new JButton("Login");
		loginBtn.setFont(new Font("Verdana", Font.BOLD,10));
		loginBtn.setBackground(Color.white);
		loginBtn.setPreferredSize(new Dimension(150,70));
		loginBtn.setForeground(Color.black);
		loginBtn.addActionListener(action);
		mainPanel.add(loginBtn);
		
	}
	
	private class actionListener implements ActionListener
	{

		MoijaMain main = new MoijaMain();
		@Override
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if(obj == loginBtn) {
				main.changePanelMain();
				LoginController loginc = new LoginController();// id,pw º¸³¿
				
			}else if(obj == registerBtn){
				main.changePanelRegister();
			}
			
		}
		
	}

}
