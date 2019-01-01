import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoijaViewRegister  extends JPanel{

	private JPanel mainPanel;
	private JLabel idLbl, pwLbl, pwReLbl;
	private JButton registerBtn, BackBtn;
	
	public MoijaViewRegister(){
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(800,400));
		setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setBounds(10, 10, 780, 380);
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(mainPanel);
		
		
		
	}
}
