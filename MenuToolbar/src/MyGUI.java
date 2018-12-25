import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class MyGUI extends JFrame{

	MyGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("툴바 만들기");
		this.setLayout(new FlowLayout());
		
		JToolBar toolBar = new JToolBar();
		JButton item = new JButton("아이템");
		
		add(toolBar,BorderLayout.NORTH);
		//JButton newItem = new JButton("새 문서");
		
		//toolBar.add(newItem);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("파일");
		JMenu viewItem = new JMenu("보기");
		JMenu infoItem = new JMenu("도움말");

		JMenuItem newItem = new JMenuItem("새로 만들기");
		JMenuItem openItem = new JMenuItem("열기");
		JMenuItem saveItem = new JMenuItem("저장");
		JMenuItem namediffItem = new JMenuItem("다른 이름 저장");
		JMenuItem endItem = new JMenuItem("끝네기");
		
		JTextArea area = new JTextArea(40,10);
		this.add(area);
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(viewItem);
		menuBar.add(infoItem);
		
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(namediffItem);
		fileMenu.add(endItem);// 파일 메뉴 설정
		
		

		JMenuItem smallItem = new JMenuItem("작게");
		JMenuItem mediumItem = new JMenuItem("보통");
		JMenuItem largeItem = new JMenuItem("크게");
		viewItem.add(smallItem);
		viewItem.add(mediumItem);
		viewItem.add(largeItem);
		
		JMenuItem proinfoItem = new JMenuItem("프로그램 정보");
		infoItem.add(proinfoItem);
		
		
		JLabel lbl = new JLabel("");
		this.add(lbl);
		setSize(300,200);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyGUI();
	}

}
