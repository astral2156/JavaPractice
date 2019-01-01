import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;

public class MoijaMain {

	static boolean ch=true;
	static Connection conn = null;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Moija Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MoijaViewLogin view = new MoijaViewLogin();
		frame.getContentPane().add(view);	
		
		frame.pack();
		frame.setVisible(true);
		
		// DB 연결
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/javadb?characterEncoding=UTF-8&serverTimezone=UTC", "root", "kim27903");
			System.out.println("connected!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	// 패널 Change 부분
	
	public void changePanelMain() {
		
		JFrame frame = new JFrame("Moija Main");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("change to Main");	
					
		MoijaView view = new MoijaView();
		frame.getContentPane().add(view);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	public void changePanelRegister() {
		JFrame frame = new JFrame("Moija");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println("change to Register");	
					
		MoijaViewRegister view = new MoijaViewRegister();
		frame.getContentPane().add(view);
		
		frame.pack();
		frame.setVisible(true);
	}
	public void disposePanel() {
		
	}

}
