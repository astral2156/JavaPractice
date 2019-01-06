
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;


//-------------------------------------------panel,dialog
public class ProjectMain extends JFrame{

	ProjectDAO dao = new ProjectDAO();
	LogoPanel logoP;
	LoginPanel loginP;
	MainPanel2 main;
	CardLayout card;
	boardRGDialog boardId;
	postDialog postdialog;
	registDialog dialog;
	
	ProjectMain() //생성자
	{
		startUI(); 
		action();
		dao.connect(); //db 연결
		boardId.setVisible(false);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nextPanel();
	}
	
	
	public void action()//이벤트함수
	{
		loginP.login.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		loginP.register.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.boardRegisterBtn.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.boardDeleteBtn.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.ContentsRegisterBtn.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.ContentsLogOutBtn.addActionListener(new projectController2(loginP,main,this,boardId,dao));	
		boardId.make.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.deleteButton.addActionListener(new projectController2(loginP,main,this,boardId,dao));
		main.hartButton.addActionListener(new projectController2(loginP,main,this,boardId,dao));
	}
	
	public void startUI() // UI함수
	{
		card = new CardLayout();
		getContentPane().setLayout(card);
		setSize(1200,600);
		setTitle("testProject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardId = new boardRGDialog(this, "title");
		logoP = new LogoPanel(this);
		loginP = new LoginPanel(this);
		main = new MainPanel2(this);
	//	boardId = new boardRGDialog(frame, title)
		getContentPane().add("1", logoP);
		getContentPane().add("2",loginP);
		getContentPane().add("3",main);
		
		//card.show(this,"2");
		setVisible(true);
	}
	
	public void nextPanel()//패널 체인지 함수 
	{
		card.next(this.getContentPane());
	}
	public void previousPanel()
	{
		card.previous(this.getContentPane());
	}
	
	public static void main(String[] args) {
		
		new ProjectMain();
	}

}
