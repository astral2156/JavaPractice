import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MoijaView extends JPanel{
	
	JPanel boardUpPanel, boardCUpPanel; 
	//boardUpPanel : 좌측 상단, 버튼 있는부분 / boardContentsPanel : 우측 상단 버튼 부분
	JPanel boardDownPanel, boardCDownPanel;
	//boardDownPanel : 좌측 리스트 뷰 부분
	JPanel boardListPanel, boardCListPanel;
	
	
	JLabel infoLbl, boardContensLbl, boardLbl;
	
	JButton boardRegisterBtn, boardDeleteBtn;
	JButton ContentsLogOutBtn;
	JButton ContentsRegisterBtn;
	private actionListener action;
	
	private String boardTitle;
	
	
	public MoijaView() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200,600));
		setLayout(null);
		
		action = new actionListener();
		
		boardUpPanel = new JPanel();
		boardUpPanel.setBackground(Color.white);
		boardUpPanel.setBounds(10, 10, 400, 80);
		boardUpPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(boardUpPanel); //게시판 등록, 삭제 버튼 추가
		
		boardDownPanel = new JPanel();
		boardDownPanel.setBackground(Color.white);
		boardDownPanel.setBounds(10,100, 400, 480);
		boardDownPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(boardDownPanel);
		
		boardCUpPanel = new JPanel();
		boardCUpPanel.setBackground(Color.white);
		boardCUpPanel.setBounds(420,10, 750, 80);
		boardCUpPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(boardCUpPanel);
		
		boardCDownPanel= new JPanel();
		boardCDownPanel.setBackground(Color.white);
		boardCDownPanel.setBounds(420,100, 750, 480);
		boardCDownPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		add(boardCDownPanel);
		
		
		boardRegisterBtn = new JButton("boardRegisterBtn");
		boardRegisterBtn.setFont(new Font("Verdana", Font.BOLD,10));
		boardRegisterBtn.setBackground(Color.white);
		boardRegisterBtn.setForeground(Color.black);
		boardRegisterBtn.setPreferredSize(new Dimension(180,70));
		boardRegisterBtn.addActionListener(action);
		boardUpPanel.add(boardRegisterBtn);
		
		boardDeleteBtn = new JButton("boardDeleteBtn");
		boardDeleteBtn.setFont(new Font("Verdana", Font.BOLD,10));
		boardDeleteBtn.setBackground(Color.white);
		boardDeleteBtn.setPreferredSize(new Dimension(180,70));
		boardDeleteBtn.setForeground(Color.black);
		boardUpPanel.add(boardDeleteBtn);

		infoLbl = new JLabel("User iInfo");
		infoLbl.setPreferredSize(new Dimension(500,70));
		infoLbl.setVisible(true);
		boardCUpPanel.add(infoLbl);	
		
		
		ContentsLogOutBtn = new JButton("End");
		ContentsLogOutBtn.setFont(new Font("Verdana", Font.BOLD,10));
		ContentsLogOutBtn.setBackground(Color.white);
		ContentsLogOutBtn.setPreferredSize(new Dimension(150,70));
		ContentsLogOutBtn.setForeground(Color.black);
		ContentsLogOutBtn.addActionListener(action);
		boardCUpPanel.add(ContentsLogOutBtn); // setHorizontalAl..SwingContants.right
		
		boardContensLbl = new JLabel("게시글 보기");
		boardContensLbl.setPreferredSize(new Dimension(500, 70));
		boardContensLbl.setVisible(true);
		boardCDownPanel.add(boardContensLbl);
		
		ContentsRegisterBtn = new JButton("NEW");
		ContentsRegisterBtn.setFont(new Font("Verdana", Font.BOLD,10));
		ContentsRegisterBtn.setBackground(Color.white);
		ContentsRegisterBtn.setPreferredSize(new Dimension(150,70));
		ContentsRegisterBtn.setForeground(Color.black);
		ContentsRegisterBtn.addActionListener(action);
		boardCDownPanel.add(ContentsRegisterBtn);
		
		boardLbl = new JLabel("게시판 목록");
		boardLbl.setPreferredSize(new Dimension(370,20));
		boardLbl.setVisible(true);
		boardDownPanel.add(boardLbl);
		
		boardListPanel =new JPanel();
		boardListPanel.setBackground(Color.white);
		boardListPanel.setLayout(new GridLayout(1,1));
		boardListPanel.setPreferredSize(new Dimension(370,430));
		boardListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		boardDownPanel.add(boardListPanel);	
		
		boardCListPanel =new JPanel();
		boardCListPanel.setBackground(Color.white);
		boardCListPanel.setLayout(new GridLayout(1,1));
		boardCListPanel.setPreferredSize(new Dimension(700,380));
		boardCListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		boardCDownPanel.add(boardCListPanel);	
		
		/// 위에는 UI 끝
		/// 밑에는 ListView 만들기
		
		Board board1 =new Board(boardTitle);
		Board board2 = new Board("");
		Board board3 = new Board("");
		
		
	}
	
	
	private class actionListener implements ActionListener
	{

		MoijaMain main = new MoijaMain();
		@Override
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();
			
			if(obj == ContentsLogOutBtn) {
				System.exit(0);
			} else if (obj == boardRegisterBtn) {
				boardTitle=JOptionPane.showInputDialog("제목을 입력해 주세요");
			} else if(obj == ContentsRegisterBtn) {
				String content = JOptionPane.showInputDialog("제목, 내용, 사진을 입력해 주세요. (운동/사랑/독서)");
				System.out.println(content);
			}

			
		}
		
		
	}
	
//frame change, panel 안에 panel
}
