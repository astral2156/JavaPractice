package simplepainter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class PrimaryPanelController { // 설정

	protected PrimaryPanelView view;

	public PrimaryPanelController(PrimaryPanelView view) {
		this.view = view; // view 받아옴
		view.addMenuListener(new ButtonMenuListener()); // view에 리스너 설정
	} // PrimaryPanelController()

	protected class ButtonMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent event) { // 액션
			Object obj = event.getSource(); // obj로 받아옴

			if (obj == view.btnColor) { // btnColor를 눌렀다면
				view.drawingPanel.data.colorShape = JColorChooser.showDialog(view.drawingPanel, "Color",
						view.drawingPanel.data.colorShape); // data에있는컬러값ㅇㄹ 기본으로 보여줌
				// 색설정한게 colorshape에 들어감. 컬러 설정

			} else {

				view.chkFill.setVisible(false);// 밑에 두가지 경우에만 if true로 되도록. 렉트 오발만 체크가 보이도록
				view.lblSizeNWidth.setVisible(false); // 보이도록
				view.txtSizeNWidth.setVisible(false); // 보이도록
				for (int i = 0; i < 6; i++) { // 메뉴에 관한 수정
					if (obj == view.btnMenuArray[i]) {
						view.drawingPanel.data.nDrawType = i; // 숫자로 구분
						// System.out.println("nDrawType : " + view.drawingPanel.data.nDrawType); //디버깅용

				
						if (i == DrawConstants.RECT ) { // rect or oval
							view.chkFill.setVisible(true); // 채우기 버튼 보이게
							view.lblSizeNWidth.setVisible(true); // 사이즈 설정 라벨 보이게
							view.txtSizeNWidth.setVisible(true); // 사이즈 설정 텍스트 보이게
							view.lblSizeNWidth.setText("Width : "); // 텍스트
							view.txtSizeNWidth.setText("1"); // 디폴트
							 view.lblStatus.setText("status : RECT" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
									
						}
						if (i == DrawConstants.OVAL) { // rect or oval
							view.chkFill.setVisible(true); // 채우기 버튼 보이게
							view.lblSizeNWidth.setVisible(true); // 사이즈 설정 라벨 보이게
							view.txtSizeNWidth.setVisible(true); // 사이즈 설정 텍스트 보이게
							view.lblSizeNWidth.setText("Width : "); // 텍스트
							view.txtSizeNWidth.setText("1"); // 디폴트
							 view.lblStatus.setText("status : OVAL" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
						}

						if (i == DrawConstants.LINE) { // line 일때
							view.lblSizeNWidth.setVisible(true); // 보이게
							view.txtSizeNWidth.setVisible(true); // 보이게
							view.lblSizeNWidth.setText("Width : "); // 택스트
							view.txtSizeNWidth.setText("1"); // 디폴트
							 view.lblStatus.setText("status : LINE" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
						}

						if (i == DrawConstants.DOT) { // dot일때

							view.lblSizeNWidth.setVisible(true); // 보이게
							view.txtSizeNWidth.setVisible(true); // 보이게
							view.lblSizeNWidth.setText("Size : "); // 텍스트
							view.txtSizeNWidth.setText("10"); // 디폴트
							 view.lblStatus.setText("status : DOT" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
							

						}
						
						if (view.drawingPanel.dataList.size() > 0) { // 없을때 작동 안하게
							
							
							if (i == DrawConstants.UNDO) { // dot일때

							view.lblSizeNWidth.setVisible(false); // 보이게
							view.txtSizeNWidth.setVisible(false); // 보이게
							view.drawingPanel.undoClicked();
							 view.lblStatus.setText("status : UNDO Clicked");
								
							}
						}
						else {

							System.out.println("redoError");
						}
						if(view.drawingPanel.undoList.size()>0) {
							if (i == DrawConstants.REDO) { // dot일때

								view.lblSizeNWidth.setVisible(false); // 보이게
								view.txtSizeNWidth.setVisible(false); // 보이게
								view.drawingPanel.redoClicked();

								 view.lblStatus.setText("status : REDO Clicked");
							}
						}else {
							System.out.println("redoError");
						}
						
						break;
					} // if
				} // for

			} // else

		} // actionPerformed()
	} // ButtonMenuListener class
} // PrimaryPanelController class
