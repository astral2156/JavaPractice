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
		setTitle("���� �����");
		this.setLayout(new FlowLayout());
		
		JToolBar toolBar = new JToolBar();
		JButton item = new JButton("������");
		
		add(toolBar,BorderLayout.NORTH);
		//JButton newItem = new JButton("�� ����");
		
		//toolBar.add(newItem);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("����");
		JMenu viewItem = new JMenu("����");
		JMenu infoItem = new JMenu("����");

		JMenuItem newItem = new JMenuItem("���� �����");
		JMenuItem openItem = new JMenuItem("����");
		JMenuItem saveItem = new JMenuItem("����");
		JMenuItem namediffItem = new JMenuItem("�ٸ� �̸� ����");
		JMenuItem endItem = new JMenuItem("���ױ�");
		
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
		fileMenu.add(endItem);// ���� �޴� ����
		
		

		JMenuItem smallItem = new JMenuItem("�۰�");
		JMenuItem mediumItem = new JMenuItem("����");
		JMenuItem largeItem = new JMenuItem("ũ��");
		viewItem.add(smallItem);
		viewItem.add(mediumItem);
		viewItem.add(largeItem);
		
		JMenuItem proinfoItem = new JMenuItem("���α׷� ����");
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
