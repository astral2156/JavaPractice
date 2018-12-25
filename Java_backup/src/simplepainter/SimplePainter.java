package simplepainter;

import javax.swing.JFrame;

public class SimplePainter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("SIMPLE PAINTER"); //프레임 생성
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫기버튼
		frame.setResizable(false); 
		
		PrimaryPanelView view = new PrimaryPanelView(); // view 객체 생성
		PrimaryPanelController controller = new PrimaryPanelController(view);	// controller 객체 생성
		frame.getContentPane().add(view); // view 추가 
		
		frame.pack(); // pack
		frame.setVisible(true); //보이도록
		
		// 기말고사2
		// 지우는거 나올 수 있음
		// 빈 버튼 2개 구현하기
		// undo redo 구현 리스트로 관리하니 갯수 줄이면됨.
		// 밑에 메세지 박스가 비어있음.
		// 관련없는 코드 있을시 감점함.
		// 
		
	} // main()
	
	// 기말 고사 : 1, 2
} // SimplePainter class
