
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//import com.sun.prism.Image;

public class projectView {

}

class LogoPanel extends JPanel // 첫 로고가 나오는 화면 패널 스플래쉬 이미지
{
	ImageIcon imag;
	JLabel imageLabel;
	ProjectMain _f;

	LogoPanel(ProjectMain f) {
		_f = f;
		setLayout(null);
		URL url;
		try {
			URL urlInput = new URL("https://user-images.githubusercontent.com/37032956/50673760-c9612f00-1023-11e9-8d3b-3f86b266654d.PNG");
			BufferedImage image = ImageIO.read(urlInput); // 로고 이미지 url

			imageLabel = new JLabel();
			imageLabel.setBounds(200, 100, 800, 400);

			setBackground(Color.WHITE);
			add(imageLabel);
			imageLabel.setIcon(new ImageIcon(image));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class LoginPanel extends JPanel // 로그인 화면 패널
{
	JLabel idLabel;
	JLabel pwLabel;
	JLabel loginLabel;
	JButton login;
	JButton register;
	JTextField idArea;
	JPasswordField pwArea;
	ProjectMain _f;

	LoginPanel(ProjectMain f) {
		_f = f;
		setLayout(null);
		setBackground(Color.white);
		idLabel = new JLabel("아이디");
		pwLabel = new JLabel("패스워드");
		loginLabel = new JLabel("로그인");
		login = new JButton("로그인");
		register = new JButton("회원가입");
		idArea = new JTextField();
		pwArea = new JPasswordField();

		idLabel.setBounds(430, 215, 50, 50);
		idLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		// idLabel.setForeground(Color.BLUE);
		pwLabel.setBounds(430, 295, 80, 50);
		pwLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 15));
		loginLabel.setFont(new Font("나눔스퀘어", Font.BOLD, 20));
		loginLabel.setBounds(570, 100, 80, 50);
		idArea.setBounds(520, 220, 200, 40);
		pwArea.setBounds(520, 300, 200, 40);
		login.setBounds(625, 440, 100, 50);
		login.setFont(new Font("나눔스퀘어", Font.BOLD, 12));
		register.setBounds(480, 440, 100, 50);
		register.setFont(new Font("나눔스퀘어", Font.BOLD, 12));

		add(idLabel);
		add(pwLabel);
		add(loginLabel);
		add(login);
		add(register);
		add(idArea);
		add(pwArea);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.drawRoundRect(400, 50, 400, 470, 30, 30);
	}
}

class registDialog extends JDialog // 로그인화면에서 회원가입 버튼을 누르면 나오는 다이얼로그
{
	JLabel register;
	JLabel id;
	JLabel pw, pwcheck;
	JLabel pwCheckConfirm;
	JLabel email, pwNcorrect;
	JButton registButton, idcheck;
	JTextField idF, emailF;
	JPasswordField pwF, pwcheckF;
	Font f = new Font("나눔스퀘어", Font.PLAIN, 15);

	registDialog() {

	}

	registDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(null);
		this.setBackground(Color.blue); // 왜안대징
		register = new JLabel("회원가입");
		register.setBounds(160, 10, 100, 50);
		register.setFont(new Font("나눔스퀘어", Font.BOLD, 15));

		id = new JLabel("아이디");
		id.setBounds(20, 80, 100, 50);
		id.setFont(f);
		pw = new JLabel("비밀번호");
		pw.setBounds(20, 130, 100, 50);
		pw.setFont(f);

		pwCheckConfirm = new JLabel("*** Password Check ***");
		pwCheckConfirm.setBounds(130, 205, 150, 40);
		pwCheckConfirm.setFont(new Font("verdana", Font.PLAIN, 8));
		pwCheckConfirm.setForeground(Color.RED);

		pwcheck = new JLabel("비밀번호확인");
		pwcheck.setBounds(20, 180, 100, 50);
		pwcheck.setFont(f);
		email = new JLabel("이메일");
		email.setBounds(20, 230, 100, 50);
		email.setFont(f);
		pwNcorrect = new JLabel("비밀번호가 일치하지 않습니다.");

		registButton = new JButton("회원 등록");
		registButton.setBounds(100, 300, 200, 40);

		idcheck = new JButton("id확인");
		idcheck.setBounds(300, 90, 70, 30);

		idcheck.setFont(new Font("나눔스퀘어", Font.PLAIN, 10));
		idF = new JTextField();
		idF.setBounds(130, 90, 150, 30);
		pwF = new JPasswordField();
		pwF.setBounds(130, 140, 150, 30);
		pwcheckF = new JPasswordField();
		pwcheckF.setBounds(130, 190, 150, 30);
		emailF = new JTextField();
		emailF.setBounds(130, 240, 200, 30);

		add(register);
		add(id);
		add(pw);
		add(pwcheck);
		add(email);
		add(registButton);
		add(idF);
		add(pwF);
		add(pwcheckF);
		add(emailF);
		add(idcheck);
	}
}

class boardRGDialog extends JDialog // 새로운 게시판 생성 누르면 나오는 다이얼로그
{
	JLabel makeboard, boardName, alert;
	JTextField nameField;
	JButton make;
	String msg = "";

	boardRGDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(null);

		make = new JButton("생 성");
		make.setBounds(155, 160, 80, 30);

		makeboard = new JLabel("새로운 게시판 생성");
		makeboard.setBounds(120, 0, 200, 50);
		makeboard.setFont(new Font("나눔스퀘어", Font.BOLD, 15));

		alert = new JLabel(msg);
		alert.setBounds(150, 50, 200, 30);
		alert.setFont(new Font("나눔스퀘어", Font.PLAIN, 8));
		alert.setForeground(Color.red);

		boardName = new JLabel("게시판 이름");
		boardName.setBounds(30, 80, 100, 40);

		nameField = new JTextField();
		nameField.setBounds(120, 80, 230, 35);

		add(alert);
		add(make);
		add(makeboard);
		add(boardName);
		add(nameField);
	}

}

class postDialog extends JDialog // 새로운 게시물 생성 다이얼로그
{
	JButton regist, cancel;
	JLabel newPost, postname, imageLabel;
	JTextArea context;
	JTextField postTitle;
	ImageIcon img;
	JButton selectChooser; // kdy selectShooser부분
	JCheckBox chkFill;

	postDialog(JFrame frame, String title) {
		super(frame, title);
		setLayout(null);

		newPost = new JLabel("새로운 게시물 생성");
		newPost.setBounds(160, 20, 200, 40);
		newPost.setFont(new Font("나눔스퀘어", Font.BOLD, 17));
		postname = new JLabel("제 목");
		postname.setBounds(50, 90, 50, 40);
		postTitle = new JTextField("제목을 입력하세요.");
		postTitle.setBounds(110, 90, 300, 40);
		img = new ImageIcon("images/image3.jpg");
		imageLabel = new JLabel(img);
		imageLabel.setBounds(50, 150, 40, 40);
		context = new JTextArea("내용을 작성해주세요.");
		context.setBounds(50, 200, 380, 380);
		regist = new JButton("등록");
		regist.setBounds(280, 600, 80, 30);
		cancel = new JButton("취소");
		cancel.setBounds(130, 600, 80, 30);
		selectChooser = new JButton("이미지 선택"); // kdy
		selectChooser.setBounds(50, 133, 120, 50);
		chkFill = new JCheckBox("전체 공지");
		chkFill.setBackground(Color.WHITE);
		chkFill.setBounds(180, 133, 80, 50);

		add(selectChooser);// kdy

		add(newPost);
		add(postTitle);
		add(postname);
		add(context);
		add(regist);
		add(cancel);
		add(imageLabel);
		add(chkFill);
	}
}

class MainPanel2 extends JPanel // 덕영오빠 메인패널
{

	JPanel boardUpPanel, boardCUpPanel;
	// boardUpPanel : 좌측 상단, 버튼 있는부분 / boardContentsPanel : 우측 상단 버튼 부분
	JPanel boardDownPanel, boardCDownPanel;
	// boardDownPanel : 좌측 리스트 뷰 부분
	JPanel boardListPanel, boardCListPanel, postpanel;

	JLabel infoLbl, boardContensLbl, boardLbl;

	JButton boardRegisterBtn, boardDeleteBtn;
	JButton ContentsLogOutBtn;
	JButton ContentsRegisterBtn;
	String[] names = { "asd", "asd", "Aasd" };

	JScrollPane scrollPane;

	private String boardTitle;
	JList strList;

	ProjectMain _f;

	ImageIcon hart = new ImageIcon("imgZip\\hartoff.png");
	JButton hartButton = new JButton(hart);
	ImageIcon delete = new ImageIcon("imgZip\\delete.png");
	JButton deleteButton = new JButton(delete);

	JComboBox postCombo = new JComboBox();
	
	ProjectDAO _dao;

	MainPanel2(){
		
	}
	public MainPanel2(ProjectMain f) {
		_f = f;
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200, 600));
		setLayout(null);

		boardUpPanel = new JPanel();
		boardUpPanel.setBackground(Color.white);
		boardUpPanel.setBounds(10, 10, 400, 80);
		boardUpPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(boardUpPanel); // 게시판 등록, 삭제 버튼 추가

		boardDownPanel = new JPanel();
		boardDownPanel.setBackground(Color.white);
		boardDownPanel.setBounds(10, 100, 400, 480);
		boardDownPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(boardDownPanel);

		boardCUpPanel = new JPanel();
		boardCUpPanel.setBackground(Color.white);
		boardCUpPanel.setBounds(420, 10, 750, 80);
		boardCUpPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(boardCUpPanel);

		boardCDownPanel = new JPanel();
		boardCDownPanel.setBackground(Color.white);
		boardCDownPanel.setBounds(420, 100, 750, 480);
		boardCDownPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		add(boardCDownPanel);

		boardRegisterBtn = new JButton("boardRegisterBtn");
		boardRegisterBtn.setFont(new Font("Verdana", Font.BOLD, 10));
		boardRegisterBtn.setBackground(Color.white);
		boardRegisterBtn.setForeground(Color.black);
		boardRegisterBtn.setPreferredSize(new Dimension(180, 70));
		boardUpPanel.add(boardRegisterBtn);

		boardDeleteBtn = new JButton("boardDeleteBtn");
		boardDeleteBtn.setFont(new Font("Verdana", Font.BOLD, 10));
		boardDeleteBtn.setBackground(Color.white);
		boardDeleteBtn.setPreferredSize(new Dimension(180, 70));
		boardDeleteBtn.setForeground(Color.black);
		boardUpPanel.add(boardDeleteBtn);

		infoLbl = new JLabel("User iInfo");
		infoLbl.setPreferredSize(new Dimension(500, 70));
		infoLbl.setVisible(true);
		boardCUpPanel.add(infoLbl);

		ContentsLogOutBtn = new JButton("Logout");
		ContentsLogOutBtn.setFont(new Font("Verdana", Font.BOLD, 10));
		ContentsLogOutBtn.setBackground(Color.white);
		ContentsLogOutBtn.setPreferredSize(new Dimension(150, 70));
		ContentsLogOutBtn.setForeground(Color.black);
		boardCUpPanel.add(ContentsLogOutBtn); // setHorizontalAl..SwingContants.right

		boardContensLbl = new JLabel("게시글 보기");
		boardContensLbl.setPreferredSize(new Dimension(300, 70));
		boardContensLbl.setVisible(true);
		boardCDownPanel.add(boardContensLbl);

		boardCDownPanel.add(postCombo);///////////////////
		boardCDownPanel.add(deleteButton);
		boardCDownPanel.add(hartButton);
		

		ContentsRegisterBtn = new JButton("NEW");
		ContentsRegisterBtn.setFont(new Font("Verdana", Font.BOLD, 10));
		ContentsRegisterBtn.setBackground(Color.white);
		ContentsRegisterBtn.setPreferredSize(new Dimension(150, 70));
		ContentsRegisterBtn.setForeground(Color.black);
		boardCDownPanel.add(ContentsRegisterBtn);

		boardLbl = new JLabel("게시판 목록");
		boardLbl.setPreferredSize(new Dimension(370, 20));
		boardLbl.setVisible(true);
		boardDownPanel.add(boardLbl);

		boardListPanel = new JPanel();
		// boardListPanel.setBackground(Color.white);
		// boardListPanel.setLayout(new GridLayout(1, 1));
		// boardListPanel.setPreferredSize(new Dimension(370, 430));
		// boardListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,
		// 1));
		strList = new JList(names);
		strList.setPreferredSize(new Dimension(369, 429));
		boardListPanel.add(strList);
		// boardDownPanel.add(boardListPanel);

		boardCListPanel = new JPanel();
		boardCListPanel.setBackground(Color.white);
		boardCListPanel.setLayout(new GridLayout(1, 1));
		boardCListPanel.setPreferredSize(new Dimension(700, 380));
		boardCListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		postpanel = new JPanel();
		postpanel.setLayout(new BoxLayout(postpanel, BoxLayout.Y_AXIS));
		postpanel.setAutoscrolls(true);
		boardCListPanel.add(postpanel, BorderLayout.CENTER);

		scrollPane = new JScrollPane(postpanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(0, 0, 700, 380);

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(700, 380));
		contentPane.add(scrollPane);
		contentPane.setBackground(Color.darkGray);

		boardCListPanel.add(contentPane, BorderLayout.CENTER);
		// boardCListPanel.pack();
		boardCDownPanel.add(boardCListPanel);
	}

	class boardPanel extends JPanel {
		int num = 1;
		JLabel numLabel;
		
		ImageIcon image;
		JTextArea contentsArea = new JTextArea();

		int Img_Width = 200;// 이미지사이즈
		int Img_Height = 200;
		ImageIcon hart;
		JButton hartButton;
		ImageIcon hartOn ;
		JButton hartOnButton;
		
		boolean hartonoff = false;

		boardPanel(int num, String title, String imagePath, String contents, int likes) {

			 hart = new ImageIcon("imgZip\\hartoff.png");
			 hartButton = new JButton(hart);

			 hartOn = new ImageIcon("imgZip\\harton.png");
			 hartOnButton = new JButton(hartOn);
			
			 if(likes == 1) {
				 System.out.println("likes : "+likes);
			hartOnButton.setVisible(true);
			 }else {
				 hartOnButton.setVisible(false);
					 
			 }
			numLabel = new JLabel("No. " + String.valueOf(num));
			JLabel titleLabel = new JLabel(title);

			titleLabel.setFont(new Font("바탕", Font.BOLD, 20));
			titleLabel.setPreferredSize(new Dimension(500, 40));

			ImageIcon originIcon = new ImageIcon(imagePath);// 이미지 벡터로
			Image originImg = originIcon.getImage();
			Image changedImg = originImg.getScaledInstance(Img_Width, Img_Height, Image.SCALE_SMOOTH);
			image = new ImageIcon(changedImg);

			contentsArea.setEditable(false);
			contentsArea.append(contents);

			setLayout(new BorderLayout());

			JPanel north = new JPanel();

			numLabel.setBounds(0, 0, 40, 40);
			titleLabel.setBounds(40, 0, 500, 40);
			hartButton.setBounds(620, 0, 35, 35);
			hartOnButton.setBounds(550, 0, 35, 35);

			north.setPreferredSize(new Dimension(700, 40));
			north.setLayout(null);
			north.add(numLabel);
			north.add(titleLabel);
			north.add(hartButton);
			north.add(hartOnButton);
			

			add(north, BorderLayout.NORTH);
			add(new JLabel(image), BorderLayout.WEST);
			add(new JScrollPane(contentsArea), BorderLayout.CENTER);

		}
		
	}
	//int num, String title, String imagePath, String contents
	public void heartChange() {  // 이너클래스의 접근하기위한 메소드 생성 하트 바꾸기
		System.out.println("heartChange");
		boardPanel bp = new boardPanel(0, "","","",0); 
		bp.hartOnButton.setVisible(true);
		
	}

	
	public void setBoardList(ArrayList<boardData> datas) {  // 게시판 리스트 관리
		Vector vec = new Vector();
		for (boardData p : datas) {
			vec.addElement(p.bName);
		}
		strList.setListData(vec);
		boardListPanel.add(strList);
		boardDownPanel.add(strList);
		// strList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public void setPostList(ArrayList<postData> posts) {// 용혁2 게시물 리스트 관리
		// boardListPanel.removeAll();
		postpanel.removeAll();
		postCombo.removeAllItems();
		for (postData p : posts) {
			boardPanel BP = new boardPanel(p.pId, p.pTitle, p.pImage, p.pContents, p.likes);
			postpanel.add(BP);
			BP.requestFocus();
			postCombo.addItem(p.pId);
		}
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
	
	
	
	
}
