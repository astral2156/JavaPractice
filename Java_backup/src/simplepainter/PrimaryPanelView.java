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

public class PrimaryPanelView extends JPanel { // 창 ui 글자 등 설정



	private JPanel menuOptionPanel, messagePanel; // 패널 생성
	private JPanel menuPanel, optionPanel;
	
	
	protected DrawingPanel drawingPanel; // 드로잉 패널 생성
	protected JButton[] btnMenuArray;

	protected JButton btnColor; // 버튼 생성
	protected JLabel lblSizeNWidth; // 라벨 생성
	protected JTextField txtSizeNWidth; //
	protected JCheckBox	chkFill; // 체크박스 생성
	protected JLabel lblStatus;
	
	
	private HoveringListener hoverL; // 호버링
	
	
	public PrimaryPanelView() { // 판넬 채움
		setBackground(Color.white); // 색 설정
		setPreferredSize(new Dimension(420, 640)); // 크기 설정
		setLayout(null);
		
		hoverL = new HoveringListener(); //호버링
		
		menuOptionPanel = new JPanel(); //패널 생성
		menuOptionPanel.setBackground(Color.white); // 색 설정
		menuOptionPanel.setBounds(10, 10, 400, 120); // 크기 위치 생성
		menuOptionPanel.setLayout(null); 
		menuOptionPanel.setBorder( // 가장자리 설정
				BorderFactory.createLineBorder(Color.lightGray, 1));
		add(menuOptionPanel); // 추가
		
		drawingPanel = new DrawingPanel(this);  // upcall을 하려고 파라미터 패싱 나 자신을 보냄 = 뷰 나 자신
		drawingPanel.setBounds(10, 140, 400, 400); // 크기 위치
		add(drawingPanel); // 추가
		
		messagePanel = new JPanel(); // 패널 생성
		messagePanel.setBackground(Color.white); // 배경 색
		messagePanel.setBounds(10, 550, 400, 80); // 크기 위치
		messagePanel.setBorder( // 가장자리
				BorderFactory.createTitledBorder("MESSAGE"));
		add(messagePanel); // 추가
		
		menuPanel = new JPanel(); //메뉴 패널  - 왼쪽 menu 부분
		menuPanel.setBackground(Color.black); // 색 설정
		menuPanel.setBounds(10, 10, 210, 100); // 위치
		menuPanel.setBorder( // 경계
				BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2, 3)); // 그리드 레이아웃
		menuOptionPanel.add(menuPanel); //메뉴패널에 추가
		
		optionPanel = new JPanel(); // 옵션패널 - 오른쪽 option 부분
		optionPanel.setBackground(Color.green); // 색 설정
		optionPanel.setBounds(225, 10, 165, 100); // 위치 설정
		optionPanel.setBorder( // 경계 설정
				BorderFactory.createTitledBorder("OPTION"));
		menuOptionPanel.add(optionPanel); // 패널에 추가
		
		btnMenuArray = new JButton[6];
		for (int i=0; i<6; i++) {
			btnMenuArray[i] = new JButton(DrawConstants.MENU[i]); // 버튼 만들고
			btnMenuArray[i].setFont(	// 폰트 설정
					new Font("Verdana",Font.BOLD,10)); 
			btnMenuArray[i].setBackground(	// 색 설정
					DrawConstants.MENU_EXIT[0]); // DrawConstants에 저장되 있는 값 사용
			btnMenuArray[i].setForeground(
					DrawConstants.MENU_EXIT[1]);// DrawConstants에 저장되 있는 값 사용
			btnMenuArray[i].addMouseListener(hoverL);// 호버링 추가
			menuPanel.add(btnMenuArray[i]);// 메뉴에 추가
		} // for
		
		//여기는 생성자
		
		btnColor= new JButton(" Color "); //버튼
		optionPanel.add(btnColor); //버튼 추가

		chkFill = new JCheckBox(" Fill "); // 체크박스
		chkFill.setBackground(Color.WHITE); //색 설정
		chkFill.setVisible(false); // 처음엔 안보이도록
		optionPanel.add(chkFill); // 체크박스 추가
		
		
		lblSizeNWidth = new JLabel("Size : "); // 라벨
		lblSizeNWidth.setVisible(false); // 라벨 안보이게
		optionPanel.add(lblSizeNWidth); // 라벨 추가
		
		txtSizeNWidth = new JTextField(5); // 텍스트필드
		txtSizeNWidth.setVisible(false); // 안보이게
		optionPanel.add(txtSizeNWidth); // 추가
		
		lblStatus = new JLabel("status : ");
		lblStatus.setVisible(true);
		messagePanel.add(lblStatus);
	
		
		
		
		
		
		
	} // PrimaryPanelView()
	
	private class HoveringListener implements MouseListener //버튼에 커서 가져다 대면 깜빡이도록
	{
		public void mouseClicked(MouseEvent event) {} 
		public void mousePressed(MouseEvent event) {}
		public void mouseReleased(MouseEvent event) {}
		
		public void mouseEntered(MouseEvent event) {  // 마우스가 들어왔을때
			JButton obj = (JButton)event.getSource(); 
			obj.setBackground(DrawConstants.MENU_ENTER[0]); // 색깔 다르게 함
			obj.setForeground(DrawConstants.MENU_ENTER[1]); // 색깔 다르게 함
		} // mouseEnterd()
		
		public void mouseExited(MouseEvent event) {  // 마우스가 나갔을 때
			JButton obj = (JButton)event.getSource(); 
			obj.setBackground(DrawConstants.MENU_EXIT[0]); // 색 다르게
			obj.setForeground(DrawConstants.MENU_EXIT[1]); // 색 다르게
		} // mouseExited()
		
	} // HovieringListener class
	
	public void addMenuListener(ActionListener listener) {
		
		for (int i=0; i<6; i++) {
			btnMenuArray[i].addActionListener(listener); // 액션리스너 추가
		}//for
		
		btnColor.addActionListener(listener); //button 눌렀을때 컨트롤러 파트로 제어가 넘어감
		
	} // addMenuListener()
	
} // PrimaryPanelView class
