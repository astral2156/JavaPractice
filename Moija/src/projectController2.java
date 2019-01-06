
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class projectController2 implements ActionListener {
	
	//�׼Ǹ����� ���ۿ� ���� Ŭ����.

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

	// boardPanel boardPn;// ����
	File selectedFile;

	projectController2(LoginPanel login, MainPanel2 main, ProjectMain f, boardRGDialog boardId, ProjectDAO dao) {
		_login = login;
		_main = main;
		_f = f;
		_boardId = boardId;
		_dao = dao;
		
	}// Ŭ�������� ���� �����ش�.

	@Override
	public void actionPerformed(ActionEvent e) { // ��ư Ŭ���� ���� Action��.
		

		if (e.getSource() == _login.register) {  // ȸ�� ��� ��ư�� ������ ���
			_dialog = new registDialog(_f, "test");
			_dialog.setVisible(true);
			_dialog.setSize(400, 400);
			_dialog.registButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// ����� ��� Ȯ�� �񱳺κ�, �׷��� ������ ���� 0102
					if (String.valueOf(_dialog.pwF.getPassword()) // ȸ�� ���
							.equals(String.valueOf(_dialog.pwcheckF.getPassword()))) {
						userAct.userRegist(_dialog.idF.getText(), String.valueOf(_dialog.pwF.getPassword()),
								_dialog.emailF.getText());
						JOptionPane.showConfirmDialog(null, "ȸ�� ��� �Ϸ�!", "", JOptionPane.DEFAULT_OPTION);
					} else { 
						System.out.println(String.valueOf(_dialog.pwF.getPassword()) + ","
								+ String.valueOf(_dialog.pwcheckF.getPassword()));
						JOptionPane.showConfirmDialog(null, "��й�ȣ�� �ٸ��ϴ�!", "", JOptionPane.DEFAULT_OPTION);
					}
				}
			});
			_dialog.idcheck.addActionListener(new ActionListener() {  // ���̵� �ߺ� �˻�
				public void actionPerformed(ActionEvent e) {
					id = _dialog.idF.getText();
					if (userAct.idCheck(id) == false) { // idCheck �޼ҵ�
						JOptionPane.showConfirmDialog(null, id + "�� ����� �� �����ϴ�. �ٸ� �Ƶ� �Է��ϼ���", "",
								JOptionPane.DEFAULT_OPTION);

					} else {
						JOptionPane.showConfirmDialog(null, "����� �� �ִ� ID�Դϴ�", "", JOptionPane.DEFAULT_OPTION);

					}

				}

			});

		}
		if (e.getSource() == _login.login) { // �α��� ��ư�� ������ ���
			String resultLogin;
			resultLogin = userAct.loginCheck(_login.idArea.getText(), String.valueOf(_login.pwArea.getPassword()));

			if (resultLogin == "login") { // �α��� ���� ���
				int bCount = 0;
				id = _login.idArea.getText();// id �޾ƿ��� ���������
				_dao.getId(id);
				_f.nextPanel();
				_main.setBoardList(_dao.refreshBD());
				_main.setPostList(_dao.refreshPS());// ����
				// System.out.println(PD.pWriter + " :www " + id);
				bCount = userAct.getPostNumber(id);
				_main.infoLbl.setText("User Info : " + id + " (�ۼ� �Խñ� :" + bCount + " �� )"); // 0102

			} else { // ����ó��

				JOptionPane.showConfirmDialog(null, "ID / PW �� �߸� �Ǿ����ϴ�.", "", JOptionPane.DEFAULT_OPTION);
			}
		}
		if (e.getSource() == _main.boardRegisterBtn) {  // �Խ��� ��Ͽ� ���� Action
			// _boardId = new boardRGDialog(_f,"test");
			_boardId.setSize(400, 250);
			_boardId.setVisible(true);
			// _boardId.make.addActionListener(this);
			_boardId.nameField.setText("");
			_boardId.show();
		}

		if (e.getSource() == _main.ContentsRegisterBtn) { // �Խù� ��Ͽ� ���� Action

			_postdialog = new postDialog(_f, "test");
			_postdialog.setVisible(true);
			_postdialog.setSize(500, 680);
			_postdialog.selectChooser.addActionListener(new ActionListener() { // �Խù� ��Ͻ� ���� ������ �����ϵ���
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// ���� Ȯ���� ���� 0102
					JFileChooser fileChooser = new JFileChooser("./imgFile//"); //default folder ����
					fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(".jpg", "aa")); // jpg ���� ����
					int result = fileChooser.showOpenDialog(fileChooser);

					String root = "";

					if (result == JFileChooser.APPROVE_OPTION) {
						selectedFile = fileChooser.getSelectedFile();
						System.out.println("Image absolute root : " + selectedFile);
						// selectedFile ���� ������ ��ũ�� ���� ���� �̰� DB�� ������ ��

					}

				}
			});

			_postdialog.regist.addActionListener(new ActionListener() { // �Խù� regist ��ư�� ������ ����� Action 

				@Override
				public void actionPerformed(ActionEvent e) {
					PD = new postData();
					if (_postdialog.chkFill.isSelected()) { // ��ü ���� üũ�Ǹ� ���ο� ���

						PD.pTitle = _postdialog.postTitle.getText();
						PD.pContents = _postdialog.context.getText();

						int countBoard = 1;
						System.out.println("count :" + _dao.countBoard); // count �� ����ŭ ���
						for (int a = 1; a < _dao.countBoard + 1; a++) {
							PD.pbid = countBoard;
							_dao.announce(PD);// ��ü ���
							countBoard++;

							System.out.println("��� ��  : " + countBoard);
						}
					} else {
						System.out.println("��� Ŭ�� ");

						PD.pTitle = _postdialog.postTitle.getText(); // �׷��� ������ �ѱ��� ���
						PD.pContents = _postdialog.context.getText();
						PD.pImage = selectedFile.toString();

						_dao.newPost(PD);
						_main.setPostList(_dao.refreshPS());// ����
						_postdialog.setVisible(false);
					}
				}

			});
			_postdialog.cancel.addActionListener(new ActionListener() { // �Խñ� ��� ��ư�� ������ ���

				@Override
				public void actionPerformed(ActionEvent e) {
					_postdialog.postTitle.setText("������ �Է��ϼ���.");
					_postdialog.context.setText("������ �ۼ����ּ���.");
					JOptionPane.showConfirmDialog(null, "�Խù� �ۼ��� ����Ͻðڽ��ϱ�?", "", JOptionPane.DEFAULT_OPTION);
					_postdialog.setVisible(false);
				}
			});
		}
		if (e.getSource() == _main.ContentsLogOutBtn) { // ���� ȭ�鿡�� �α׾ƿ��� ������ ���
			_f.previousPanel(); 
			_login.pwArea.setText(""); // text �ʱ�ȭ

		}

		if (e.getSource() == _main.boardDeleteBtn) { // �Խ��� ���� ��ư�� ������ ���
			System.out.println(_main.strList.getSelectedValue());
			BD = new boardData();
			BD.bName = _main.strList.getSelectedValue().toString();
			_dao.delBoard(BD);
			_main.setBoardList(_dao.refreshBD());
			// _main.strList.remove(_main.strList.getSelectedIndex());
		}
		if(e.getSource() == _main.deleteButton) {  // �Խù� ���� ��ư�� ������ ���
			System.out.println(_main.postCombo.getSelectedItem());
			PD = new postData();
			PD.pId = (int) _main.postCombo.getSelectedItem();
			_dao.delPost(PD);
			_main.setPostList(_dao.refreshPS());// ����
		}
		if(e.getSource() == _main.hartButton) { // ��Ʈ ��ư�� ������ ���
			//���� �߰�
			PD = new postData();
			PD.pId = (int) _main.postCombo.getSelectedItem();
			_dao.heartActive(PD); // hart�� like 1���� Update
			_dao.heartShow(PD); // likes�� 1�ΰ� Select heartPid�� ����

			_main.setPostList(_dao.refreshPS());
		}

		if (e.getSource() == _boardId.make) { // �Խ��� ����� ������ ���
			if (_boardId.nameField.getText() == null) {
				_boardId.alert.setText(" �Խ��� �̸��� �������ּ��� ");
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
