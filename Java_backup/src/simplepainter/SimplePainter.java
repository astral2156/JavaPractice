package simplepainter;

import javax.swing.JFrame;

public class SimplePainter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("SIMPLE PAINTER"); //������ ����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �ݱ��ư
		frame.setResizable(false); 
		
		PrimaryPanelView view = new PrimaryPanelView(); // view ��ü ����
		PrimaryPanelController controller = new PrimaryPanelController(view);	// controller ��ü ����
		frame.getContentPane().add(view); // view �߰� 
		
		frame.pack(); // pack
		frame.setVisible(true); //���̵���
		
		// �⸻���2
		// ����°� ���� �� ����
		// �� ��ư 2�� �����ϱ�
		// undo redo ���� ����Ʈ�� �����ϴ� ���� ���̸��.
		// �ؿ� �޼��� �ڽ��� �������.
		// ���þ��� �ڵ� ������ ������.
		// 
		
	} // main()
	
	// �⸻ ��� : 1, 2
} // SimplePainter class
