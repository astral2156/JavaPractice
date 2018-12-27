import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class MyGUI extends JFrame {
	
	private String root= "";
	
	public MyGUI() {

		FileWriter writeFile = null;
		FileReader readerFile = null;
		BufferedWriter writer = null;
		BufferedReader reader = null;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Note Pad");
		this.setLayout(new FlowLayout());

		JToolBar toolBar = new JToolBar();
		JButton item = new JButton("������");

		add(toolBar, BorderLayout.NORTH);
		// JButton newItem = new JButton("�� ����");

		// toolBar.add(newItem);

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("����");
		JMenu viewItem = new JMenu("����");
		JMenu infoItem = new JMenu("����");

		JMenuItem newItem = new JMenuItem("���� �����");
		JMenuItem openItem = new JMenuItem("����");
		JMenuItem saveItem = new JMenuItem("����");
		JMenuItem namediffItem = new JMenuItem("�ٸ� �̸� ����");
		JMenuItem endItem = new JMenuItem("���ױ�");

		// ./ ���� ���� ���
		// println���� ��� ����

		JTextArea area = new JTextArea(30, 30);
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

		setSize(600, 500);
		setVisible(true);

		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setText(null);

			}
		});
		
		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("./");
				int result = fileChooser.showOpenDialog(null);
				File selectedFile;
				
				if (result == JFileChooser.APPROVE_OPTION) {

					selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile);
					root = selectedFile.toString();
					BufferedReader in;
					try {
						in = new BufferedReader(new FileReader(selectedFile));
						String line = in.readLine();
						while(line!=null) {
							area.setText(line);
							area.append(line +"\n");
							line= in.readLine();
							
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		});
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// root�� ���ٸ� �ڵ� ����
				if(root!="") {
					
					File file = new File(root);
					System.out.println("save : root : "+root);
					JOptionPane.showMessageDialog(null, "Saved with same name!", "Save Info", JOptionPane.CLOSED_OPTION);
					System.exit(0);
				}
				
				
				JFileChooser fileChooser = new JFileChooser("./");	
				int userSelection = fileChooser.showSaveDialog(null);
			
				
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					
					File fileTosave = fileChooser.getSelectedFile();
					System.out.println("save as fle : " + fileTosave.getAbsolutePath()+".txt");
					//BufferedWriter outFile = null;
					String str = area.getText();
					root = fileTosave.getAbsolutePath().toString();
					File fileR = new File(fileTosave.getAbsolutePath()+".txt");
					PrintWriter out;
					try(BufferedWriter outFile = new BufferedWriter(new FileWriter(fileR))){
						area.write(outFile);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if(!fileR.exists()) {
						try {
							fileR.createNewFile();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					
					
				}
				
			}
		});
		namediffItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("./");				
				int userSelection = fileChooser.showSaveDialog(null);
				if(userSelection == JFileChooser.APPROVE_OPTION) {
					File fileTosave = fileChooser.getSelectedFile();
					System.out.println("save as fle : " + fileTosave.getAbsolutePath()+".txt");
					//BufferedWriter outFile = null;
					String str = area.getText();
					
					File fileR = new File(fileTosave.getAbsolutePath()+".txt");
					PrintWriter out;
					try(BufferedWriter outFile = new BufferedWriter(new FileWriter(fileR))){
						area.write(outFile);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					if(!fileR.exists()) {
						try {
							fileR.createNewFile();
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		endItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "close?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.NO_OPTION) {

				} else {
					System.exit(0);
				}
			}

		});
		smallItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setFont(new Font("Verdana", Font.PLAIN, 5));
			}
		});
		mediumItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setFont(new Font("Verdana", Font.PLAIN, 10));
			}
		});
		largeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				area.setFont(new Font("Verdana", Font.PLAIN, 15));
			}
		});
		proinfoItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "my name is KDY, 2018.12.16", "AppInfo", JOptionPane.CLOSED_OPTION);
			}
		});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyGUI();
	}

}
