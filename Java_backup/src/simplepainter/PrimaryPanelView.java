package simplepainter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PrimaryPanelView extends JPanel { // â ui ���� �� ����



	private JPanel menuOptionPanel, messagePanel; // �г� ����
	private JPanel menuPanel, optionPanel;
	
	
	protected DrawingPanel drawingPanel; // ����� �г� ����
	protected JButton[] btnMenuArray;

	protected JButton btnColor; // ��ư ����
	protected JLabel lblSizeNWidth; // �� ����
	protected JTextField txtSizeNWidth; //
	protected JCheckBox	chkFill; // üũ�ڽ� ����
	protected JLabel lblStatus;
	
	
	private HoveringListener hoverL; // ȣ����
	
	
	public PrimaryPanelView() { // �ǳ� ä��
		setBackground(Color.white); // �� ����
		setPreferredSize(new Dimension(420, 640)); // ũ�� ����
		setLayout(null);
		
		hoverL = new HoveringListener(); //ȣ����
		
		menuOptionPanel = new JPanel(); //�г� ����
		menuOptionPanel.setBackground(Color.white); // �� ����
		menuOptionPanel.setBounds(10, 10, 400, 120); // ũ�� ��ġ ����
		menuOptionPanel.setLayout(null); 
		menuOptionPanel.setBorder( // �����ڸ� ����
				BorderFactory.createLineBorder(Color.lightGray, 1));
		add(menuOptionPanel); // �߰�
		
		drawingPanel = new DrawingPanel(this);  // upcall�� �Ϸ��� �Ķ���� �н� �� �ڽ��� ���� = �� �� �ڽ�
		drawingPanel.setBounds(10, 140, 400, 400); // ũ�� ��ġ
		add(drawingPanel); // �߰�
		
		messagePanel = new JPanel(); // �г� ����
		messagePanel.setBackground(Color.white); // ��� ��
		messagePanel.setBounds(10, 550, 400, 80); // ũ�� ��ġ
		messagePanel.setBorder( // �����ڸ�
				BorderFactory.createTitledBorder("MESSAGE"));
		add(messagePanel); // �߰�
		
		menuPanel = new JPanel(); //�޴� �г�  - ���� menu �κ�
		menuPanel.setBackground(Color.black); // �� ����
		menuPanel.setBounds(10, 10, 210, 100); // ��ġ
		menuPanel.setBorder( // ���
				BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2, 3)); // �׸��� ���̾ƿ�
		menuOptionPanel.add(menuPanel); //�޴��гο� �߰�
		
		optionPanel = new JPanel(); // �ɼ��г� - ������ option �κ�
		optionPanel.setBackground(Color.green); // �� ����
		optionPanel.setBounds(225, 10, 165, 100); // ��ġ ����
		optionPanel.setBorder( // ��� ����
				BorderFactory.createTitledBorder("OPTION"));
		menuOptionPanel.add(optionPanel); // �гο� �߰�
		
		btnMenuArray = new JButton[6];
		for (int i=0; i<6; i++) {
			btnMenuArray[i] = new JButton(DrawConstants.MENU[i]); // ��ư �����
			btnMenuArray[i].setFont(	// ��Ʈ ����
					new Font("Verdana",Font.BOLD,10)); 
			btnMenuArray[i].setBackground(	// �� ����
					DrawConstants.MENU_EXIT[0]); // DrawConstants�� ����� �ִ� �� ���
			btnMenuArray[i].setForeground(
					DrawConstants.MENU_EXIT[1]);// DrawConstants�� ����� �ִ� �� ���
			btnMenuArray[i].addMouseListener(hoverL);// ȣ���� �߰�
			menuPanel.add(btnMenuArray[i]);// �޴��� �߰�
		} // for
		
		//����� ������
		
		btnColor= new JButton(" Color "); //��ư
		optionPanel.add(btnColor); //��ư �߰�

		chkFill = new JCheckBox(" Fill "); // üũ�ڽ�
		chkFill.setBackground(Color.WHITE); //�� ����
		chkFill.setVisible(false); // ó���� �Ⱥ��̵���
		optionPanel.add(chkFill); // üũ�ڽ� �߰�
		
		
		lblSizeNWidth = new JLabel("Size : "); // ��
		lblSizeNWidth.setVisible(false); // �� �Ⱥ��̰�
		optionPanel.add(lblSizeNWidth); // �� �߰�
		
		txtSizeNWidth = new JTextField(5); // �ؽ�Ʈ�ʵ�
		txtSizeNWidth.setVisible(false); // �Ⱥ��̰�
		optionPanel.add(txtSizeNWidth); // �߰�
		
		lblStatus = new JLabel("status : ");
		lblStatus.setVisible(true);
		messagePanel.add(lblStatus);
	
		
		
		
		
		
		
	} // PrimaryPanelView()
	
	private class HoveringListener implements MouseListener //��ư�� Ŀ�� ������ ��� �����̵���
	{
		public void mouseClicked(MouseEvent event) {} 
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		
		public void mouseEntered(MouseEvent event) {  // ���콺�� ��������
			JButton obj = (JButton)event.getSource(); 
			obj.setBackground(DrawConstants.MENU_ENTER[0]); // ���� �ٸ��� ��
			obj.setForeground(DrawConstants.MENU_ENTER[1]); // ���� �ٸ��� ��
		} // mouseEnterd()
		
		public void mouseExited(MouseEvent event) {  // ���콺�� ������ ��
			JButton obj = (JButton)event.getSource(); 
			obj.setBackground(DrawConstants.MENU_EXIT[0]); // �� �ٸ���
			obj.setForeground(DrawConstants.MENU_EXIT[1]); // �� �ٸ���
		} // mouseExited()
		
	} // HovieringListener class
	
	public void addMenuListener(ActionListener listener) {
		
		for (int i=0; i<6; i++) {
			btnMenuArray[i].addActionListener(listener); // �׼Ǹ����� �߰�
		}//for
		
		btnColor.addActionListener(listener); //button �������� ��Ʈ�ѷ� ��Ʈ�� ��� �Ѿ
		
	} // addMenuListener()
	
} // PrimaryPanelView class
