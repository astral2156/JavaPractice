import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiChatUI extends JFrame{

	private JPanel loginPanel;
	protected JButton loginButton;
	private JLabel inLabel;
	protected JLabel outLabel;
	
	protected JTextField idInput;

	private JPanel logoutPanel;
	protected JButton logoutButton;
	
	private JPanel msgPanel;
	protected JTextField msgInput;
	protected JButton exitButton;
	protected String id;
	
	protected Container tab;
	protected CardLayout cardLayout;
	protected JTextArea msgOut;
	
	
	public MultiChatUI() {
		super(":: Multi Chat ::");
		//
		loginPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());
		
		idInput = new JTextField(15);
		loginButton =new JButton("Login");
		inLabel = new JLabel(" Chat Name : ");
		loginPanel.add(inLabel, BorderLayout.WEST);
		loginPanel.add(idInput, BorderLayout.CENTER);
		loginPanel.add(loginButton, BorderLayout.EAST);
		
		//
		logoutPanel = new JPanel();
		logoutPanel.setLayout(new BorderLayout());
		outLabel = new JLabel("OUT ");
		logoutButton = new JButton("Logout");
	
		logoutPanel.add(outLabel, BorderLayout.WEST);
		//logoutPanel.add(inLabel,BorderLayout.CENTER);
		logoutPanel.add(logoutButton, BorderLayout.EAST);
		//this.add(logoutPanel,BorderLayout.CENTER);
			
		
		//
		msgPanel = new JPanel();
		msgPanel.setLayout(new BorderLayout());
		msgInput = new JTextField(10);
		
		exitButton = new JButton("Exit");
		msgPanel.add(msgInput, BorderLayout.CENTER);
		msgPanel.add(exitButton, BorderLayout.EAST);
		
		tab =new JPanel();
		cardLayout =new CardLayout();
		tab.setLayout(cardLayout);
		tab.add(loginPanel,"login");
		tab.add(logoutPanel,"logout");
		
		cardLayout.show(tab, "login");
		
		msgOut =new JTextArea("", 10, 30);
		msgOut.setEditable(false);
		//this.add(msgPanel,BorderLayout.SOUTH);
		
		JScrollPane jsp =new JScrollPane(msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(tab, BorderLayout.NORTH);
		
		this.add(jsp, BorderLayout.CENTER);
		this.add(msgPanel, BorderLayout.SOUTH);
		//this.add(loginPanel, BorderLayout.NORTH);
		
		setVisible(true);
		setSize(400,600);
		
		
	}
	
	public void addButtonActionListener(ActionListener listener) {
		loginButton.addActionListener(listener);
		logoutButton.addActionListener(listener);
		exitButton.addActionListener(listener);
		msgInput.addActionListener(listener);
		
	}

}
