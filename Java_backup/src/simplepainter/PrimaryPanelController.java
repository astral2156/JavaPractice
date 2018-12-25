package simplepainter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

public class PrimaryPanelController { // ����

	protected PrimaryPanelView view;

	public PrimaryPanelController(PrimaryPanelView view) {
		this.view = view; // view �޾ƿ�
		view.addMenuListener(new ButtonMenuListener()); // view�� ������ ����
	} // PrimaryPanelController()

	protected class ButtonMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent event) { // �׼�
			Object obj = event.getSource(); // obj�� �޾ƿ�

			if (obj == view.btnColor) { // btnColor�� �����ٸ�
				view.drawingPanel.data.colorShape = JColorChooser.showDialog(view.drawingPanel, "Color",
						view.drawingPanel.data.colorShape); // data���ִ��÷������� �⺻���� ������
				// �������Ѱ� colorshape�� ��. �÷� ����

			} else {

				view.chkFill.setVisible(false);// �ؿ� �ΰ��� ��쿡�� if true�� �ǵ���. ��Ʈ ���߸� üũ�� ���̵���
				view.lblSizeNWidth.setVisible(false); // ���̵���
				view.txtSizeNWidth.setVisible(false); // ���̵���
				for (int i = 0; i < 6; i++) { // �޴��� ���� ����
					if (obj == view.btnMenuArray[i]) {
						view.drawingPanel.data.nDrawType = i; // ���ڷ� ����
						// System.out.println("nDrawType : " + view.drawingPanel.data.nDrawType); //������

				
						if (i == DrawConstants.RECT ) { // rect or oval
							view.chkFill.setVisible(true); // ä��� ��ư ���̰�
							view.lblSizeNWidth.setVisible(true); // ������ ���� �� ���̰�
							view.txtSizeNWidth.setVisible(true); // ������ ���� �ؽ�Ʈ ���̰�
							view.lblSizeNWidth.setText("Width : "); // �ؽ�Ʈ
							view.txtSizeNWidth.setText("1"); // ����Ʈ
							 view.lblStatus.setText("status : RECT" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
									
						}
						if (i == DrawConstants.OVAL) { // rect or oval
							view.chkFill.setVisible(true); // ä��� ��ư ���̰�
							view.lblSizeNWidth.setVisible(true); // ������ ���� �� ���̰�
							view.txtSizeNWidth.setVisible(true); // ������ ���� �ؽ�Ʈ ���̰�
							view.lblSizeNWidth.setText("Width : "); // �ؽ�Ʈ
							view.txtSizeNWidth.setText("1"); // ����Ʈ
							 view.lblStatus.setText("status : OVAL" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
						}

						if (i == DrawConstants.LINE) { // line �϶�
							view.lblSizeNWidth.setVisible(true); // ���̰�
							view.txtSizeNWidth.setVisible(true); // ���̰�
							view.lblSizeNWidth.setText("Width : "); // �ý�Ʈ
							view.txtSizeNWidth.setText("1"); // ����Ʈ
							 view.lblStatus.setText("status : LINE" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
						}

						if (i == DrawConstants.DOT) { // dot�϶�

							view.lblSizeNWidth.setVisible(true); // ���̰�
							view.txtSizeNWidth.setVisible(true); // ���̰�
							view.lblSizeNWidth.setText("Size : "); // �ؽ�Ʈ
							view.txtSizeNWidth.setText("10"); // ����Ʈ
							 view.lblStatus.setText("status : DOT" + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
								
							

						}
						
						if (view.drawingPanel.dataList.size() > 0) { // ������ �۵� ���ϰ�
							
							
							if (i == DrawConstants.UNDO) { // dot�϶�

							view.lblSizeNWidth.setVisible(false); // ���̰�
							view.txtSizeNWidth.setVisible(false); // ���̰�
							view.drawingPanel.undoClicked();
							 view.lblStatus.setText("status : UNDO Clicked");
								
							}
						}
						else {

							System.out.println("redoError");
						}
						if(view.drawingPanel.undoList.size()>0) {
							if (i == DrawConstants.REDO) { // dot�϶�

								view.lblSizeNWidth.setVisible(false); // ���̰�
								view.txtSizeNWidth.setVisible(false); // ���̰�
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
