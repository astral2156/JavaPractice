
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class projectController2 implements ActionListener {
	
	//액션리스너 동작에 관한 클래스.

	LogoPanel _logo;
	LoginPanel _login;
	MainPanel2 _main;
	ProjectMain _f;
	boardRGDialog _boardId;
	postDialog _postdialog;
	registDialog _dialog;
	boardData BD;
	ProjectDAO _dao;
	UserAct userAct = new UserAct();
	postData PD;// = new postData();
	String id;

	// boardPanel boardPn;// 용혁
	File selectedFile;

	projectController2(LoginPanel login, MainPanel2 main, ProjectMain f, boardRGDialog boardId, ProjectDAO dao) {
		_login = login;
		_main = main;
		_f = f;
		_boardId = boardId;
		_dao = dao;
		
	}// 클래스들을 연결 시켜준다.

	@Override
	public void actionPerformed(ActionEvent e) { // 버튼 클릭에 대한 Action들.
		

		if (e.getSource() == _login.register) {  // 회원 등록 버튼이 눌렸을 경우
			_dialog = new registDialog(_f, "test");
			_dialog.setVisible(true);
			_dialog.setSize(400, 400);
			_dialog.registButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// 비번과 비번 확인 비교부분, 그렇지 않으면 실행 0102
					if (String.valueOf(_dialog.pwF.getPassword()) // 회원 등록
							.equals(String.valueOf(_dialog.pwcheckF.getPassword()))) {
						userAct.userRegist(_dialog.idF.getText(), String.valueOf(_dialog.pwF.getPassword()),
								_dialog.emailF.getText());
						JOptionPane.showConfirmDialog(null, "회원 등록 완료!", "", JOptionPane.DEFAULT_OPTION);
					} else { 
						System.out.println(String.valueOf(_dialog.pwF.getPassword()) + ","
								+ String.valueOf(_dialog.pwcheckF.getPassword()));
						JOptionPane.showConfirmDialog(null, "비밀번호가 다릅니다!", "", JOptionPane.DEFAULT_OPTION);
					}
				}
			});
			_dialog.idcheck.addActionListener(new ActionListener() {  // 아이디 중복 검사
				public void actionPerformed(ActionEvent e) {
					id = _dialog.idF.getText();
					if (userAct.idCheck(id) == false) { // idCheck 메소드
						JOptionPane.showConfirmDialog(null, id + "는 사용할 수 없습니다. 다른 아디를 입력하세요", "",
								JOptionPane.DEFAULT_OPTION);

					} else {
						JOptionPane.showConfirmDialog(null, "사용할 수 있는 ID입니다", "", JOptionPane.DEFAULT_OPTION);

					}

				}

			});

		}
		if (e.getSource() == _login.login) { // 로그인 버튼을 눌렀을 경우
			String resultLogin;
			resultLogin = userAct.loginCheck(_login.idArea.getText(), String.valueOf(_login.pwArea.getPassword()));

			if (resultLogin == "login") { // 로그인 됐을 경우
				int bCount = 0;
				id = _login.idArea.getText();// id 받아오고 전해줘야함
				_dao.getId(id);
				_f.nextPanel();
				_main.setBoardList(_dao.refreshBD());
				_main.setPostList(_dao.refreshPS());// 용혁
				// System.out.println(PD.pWriter + " :www " + id);
				bCount = userAct.getPostNumber(id);
				_main.infoLbl.setText("User Info : " + id + " (작성 게시글 :" + bCount + " 개 )"); // 0102

			} else { // 예외처리

				JOptionPane.showConfirmDialog(null, "ID / PW 가 잘못 되었습니다.", "", JOptionPane.DEFAULT_OPTION);
			}
		}
		if (e.getSource() == _main.boardRegisterBtn) {  // 게시판 등록에 관한 Action
			// _boardId = new boardRGDialog(_f,"test");
			_boardId.setSize(400, 250);
			_boardId.setVisible(true);
			// _boardId.make.addActionListener(this);
			_boardId.nameField.setText("");
			_boardId.show();
		}

		if (e.getSource() == _main.ContentsRegisterBtn) { // 게시물 등록에 관한 Action

			_postdialog = new postDialog(_f, "test");
			_postdialog.setVisible(true);
			_postdialog.setSize(500, 680);
			_postdialog.selectChooser.addActionListener(new ActionListener() { // 게시물 등록시 사진 선택이 가능하도록
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// 파일 확장자 설정 0102
					JFileChooser fileChooser = new JFileChooser("./imgFile//"); //default folder 설정
					fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(".jpg", "aa")); // jpg 선택 가능
					int result = fileChooser.showOpenDialog(fileChooser);

					String root = "";

					if (result == JFileChooser.APPROVE_OPTION) {
						selectedFile = fileChooser.getSelectedFile();
						System.out.println("Image absolute root : " + selectedFile);
						// selectedFile 에서 파일의 링크를 갖고 있음 이거 DB로 보내면 됨

					}

				}
			});

			_postdialog.regist.addActionListener(new ActionListener() { // 게시물 regist 버튼이 눌렸을 경우의 Action 

				@Override
				public void actionPerformed(ActionEvent e) {
					PD = new postData();
					if (_postdialog.chkFill.isSelected()) { // 전체 공지 체크되면 전부에 등록

						PD.pTitle = _postdialog.postTitle.getText();
						PD.pContents = _postdialog.context.getText();

						int countBoard = 1;
						System.out.println("count :" + _dao.countBoard); // count 한 값만큼 등록
						for (int a = 1; a < _dao.countBoard + 1; a++) {
							PD.pbid = countBoard;
							_dao.announce(PD);// 전체 등록
							countBoard++;

							System.out.println("등록 후  : " + countBoard);
						}
					} else {
						System.out.println("등록 클릭 ");

						PD.pTitle = _postdialog.postTitle.getText(); // 그렇지 않으면 한군데 등록
						PD.pContents = _postdialog.context.getText();
						PD.pImage = selectedFile.toString();

						_dao.newPost(PD);
						_main.setPostList(_dao.refreshPS());// 용혁
						_postdialog.setVisible(false);
					}
				}

			});
			_postdialog.cancel.addActionListener(new ActionListener() { // 게시글 취소 버튼이 눌렸을 경우

				@Override
				public void actionPerformed(ActionEvent e) {
					_postdialog.postTitle.setText("제목을 입력하세요.");
					_postdialog.context.setText("내용을 작성해주세요.");
					JOptionPane.showConfirmDialog(null, "게시물 작성을 취소하시겠습니까?", "", JOptionPane.DEFAULT_OPTION);
					_postdialog.setVisible(false);
				}
			});
		}
		if (e.getSource() == _main.ContentsLogOutBtn) { // 메인 화면에서 로그아웃을 눌렀을 경우
			_f.previousPanel(); 
			_login.pwArea.setText(""); // text 초기화

		}

		if (e.getSource() == _main.boardDeleteBtn) { // 게시판 삭제 버튼이 눌렀을 경우
			System.out.println(_main.strList.getSelectedValue());
			BD = new boardData();
			BD.bName = _main.strList.getSelectedValue().toString();
			_dao.delBoard(BD);
			_main.setBoardList(_dao.refreshBD());
			// _main.strList.remove(_main.strList.getSelectedIndex());
		}
		if(e.getSource() == _main.deleteButton) {  // 게시물 삭제 버튼이 눌렸을 경우
			System.out.println(_main.postCombo.getSelectedItem());
			PD = new postData();
			PD.pId = (int) _main.postCombo.getSelectedItem();
			_dao.delPost(PD);
			_main.setPostList(_dao.refreshPS());// 용혁
		}
		if(e.getSource() == _main.hartButton) { // 하트 버튼이 눌렸을 경우
			//덕영 추가
			PD = new postData();
			PD.pId = (int) _main.postCombo.getSelectedItem();
			_dao.heartActive(PD); // hart에 like 1으로 Update
			_dao.heartShow(PD); // likes가 1인거 Select heartPid가 갖음

			_main.setPostList(_dao.refreshPS());
		}

		if (e.getSource() == _boardId.make) { // 게시판 등록을 눌렀을 경우
			if (_boardId.nameField.getText() == null) {
				_boardId.alert.setText(" 게시판 이름을 기입해주세요 ");
			} else {
				BD = new boardData();
				BD.bName = _boardId.nameField.getText();
				_dao.newBoard(BD);
				_main.setBoardList(_dao.refreshBD());
				_boardId.setVisible(false);

			}

		}	

	}

}
