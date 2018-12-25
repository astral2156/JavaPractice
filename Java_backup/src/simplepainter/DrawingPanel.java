package simplepainter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel { //������ �׸��� �κ�

	protected ArrayList<DrawData> dataList; // ��ο� ����Ÿ ����Ʈ�� ����
	protected ArrayList<DrawData> undoList;
	protected DrawData data; // ��ο� ����Ÿ
	protected boolean bDrag; // �巡���ߴ��� Ȯ��
	protected PrimaryPanelView view; //�Ķ���� �ޱ�����
	protected int size;
	
	private DrawListener drawL; // ��ο� ������

	public DrawingPanel(PrimaryPanelView view) {  //���� ������ ���� ���� �Ķ��� ��������
		
		this.view = view; // this.view�� ���� ��𼭵� ���� ����
		
		setBackground(Color.white); // ������
		setBorder(BorderFactory.createLineBorder(Color.darkGray)); // ��輱 ����

		drawL = new DrawListener(); // ������ ����
		this.addMouseListener(drawL); // ���콺 ������ �߰�
		this.addMouseMotionListener(drawL);// ���콺 ��� ������ �߰�

		dataList = new ArrayList<DrawData>(); // ����Ÿ ����Ʈ�� ����
		data = new DrawData();// ��ο쵥��Ÿ ����
		undoList = new ArrayList<DrawData>();

		bDrag = false; //����Ʈ ����

	} // DrawingPanel()

	public void paintComponent(Graphics page) { //�巡�� �Ҷ�, ����ɶ� �ݺ��ؼ� for������ �׸�
		super.paintComponent(page);

		size = dataList.size();
		System.out.println(size);
		
		Graphics2D page2D = (Graphics2D) page; //
		
		
		if (bDrag) {	//�巡���Ҷ� �׸��� �κ�
			page.setColor(data.colorShape); 
			switch (data.nDrawType) {// data ���
			case DrawConstants.LINE: // ������ ���
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); // �α� ����
				page.drawLine(data.pt1.x, data.pt1.y, data.pt2.x, data.pt2.y); // �׸���
				break;
			case DrawConstants.RECT: // �簢�� �� ���
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); //�α� ����
				if(data.bFill) paintFillRect(page, data.pt1, data.pt2); // bfill��  true�� ä���� �׸���
				else paintRect(page, data.pt1, data.pt2); // �簢�� �׸���
				break;
			
			case DrawConstants.OVAL: // �� �׸���
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); //�α� ����
				if(data.bFill) paintFillOval(page, data.pt1, data.pt2); // ä���� �׸���
				else paintOval (page, data.pt1, data.pt2); // �׳� �׸���
				break;
			} // switch
		} // if
/*
		for (DrawData getData : dataList) {  //����Ȱ� �׸���. ����Ȱ� �׸��� �κ��� �̰�
			page.setColor(getData.colorShape); // ����� get data ���
			switch (getData.nDrawType) { // ����ġ ���̽�
			
			case DrawConstants.DOT: // ��Ʈ �϶� 
				page.fillOval(getData.pt1.x - getData.nSize / 2, getData.pt1.y - getData.nSize / 2, getData.nSize, //��Ʈ�� ������ ���
						getData.nSize);
				break;
				
			case DrawConstants.LINE: // �����϶�
				page2D = (Graphics2D) page; 
				page2D.setStroke(new BasicStroke(getData.nSize)); //������ �����ͼ� �׸�
				page.drawLine(getData.pt1.x, getData.pt1.y, getData.pt2.x, getData.pt2.y); // ������ �׸�
				break;
				
			case DrawConstants.RECT: // �簢���϶�
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //�α� ���� 
				if(getData.bFill) paintFillRect(page, getData.pt1, getData.pt2); // ä�� �����͸� �̰ɷ� �׸�
				else paintRect(page, getData.pt1, getData.pt2); // �׸�
				break;
				
			case DrawConstants.OVAL: // �� �϶�
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //�α� ����
				if(getData.bFill) paintFillOval(page, getData.pt1, getData.pt2); //ä�� �����͸� �̰ɷ� �׸�
				else paintOval (page, getData.pt1, getData.pt2); //�׸�
				//paintOval(page,getData.pt1,getData.pt2); // for �������� getData�� �޾ƿͼ� ����Ѵ�.
				break;
			case DrawConstants.UNDO: // �� �϶�

				break;
				
				
				
				
			} // switch
		} // for
*/
		
		DrawAct(page); //  3��. 
		
		
	} // paintComponent()

	
	private void DrawAct(Graphics page) {

		Graphics2D page2D = (Graphics2D) page;
		
		for (DrawData getData : dataList) {  //����Ȱ� �׸���. ����Ȱ� �׸��� �κ��� �̰�
			page.setColor(getData.colorShape); // ����� get data ���
			switch (getData.nDrawType) { // ����ġ ���̽�
			
			case DrawConstants.DOT: // ��Ʈ �϶� 
				page.fillOval(getData.pt1.x - getData.nSize / 2, getData.pt1.y - getData.nSize / 2, getData.nSize, //��Ʈ�� ������ ���
						getData.nSize);
				break;
				
			case DrawConstants.LINE: // �����϶�
				page2D = (Graphics2D) page; 
				page2D.setStroke(new BasicStroke(getData.nSize)); //������ �����ͼ� �׸�
				page.drawLine(getData.pt1.x, getData.pt1.y, getData.pt2.x, getData.pt2.y); // ������ �׸�
				break;
				
			case DrawConstants.RECT: // �簢���϶�
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //�α� ���� 
				if(getData.bFill) paintFillRect(page, getData.pt1, getData.pt2); // ä�� �����͸� �̰ɷ� �׸�
				else paintRect(page, getData.pt1, getData.pt2); // �׸�
				break;
				
			case DrawConstants.OVAL: // �� �϶�
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //�α� ����
				if(getData.bFill) paintFillOval(page, getData.pt1, getData.pt2); //ä�� �����͸� �̰ɷ� �׸�
				else paintOval (page, getData.pt1, getData.pt2); //�׸�
				//paintOval(page,getData.pt1,getData.pt2); // for �������� getData�� �޾ƿͼ� ����Ѵ�.
				break;
			case DrawConstants.UNDO: // undo �϶�
			case DrawConstants.REDO: // undo �϶�
				

				break;
				
				
				
				
			} // switch
		} // for
	}
	
	private void paintRect(Graphics page, Point pt1, Point pt2) { // �׸� �׸���

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.drawRect(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // ���� ���, ū�ſ��� ������ ����
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.drawRect(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.drawRect(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.drawRect(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // ������ ������ �ȵǴ� ū�ż� ������ ��
		}
		
	}
	
	private void paintFillRect(Graphics page, Point pt1, Point pt2) { //�׸� ä��� �׸���

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.fillRect(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // ���� ���, ū�ſ��� ������ ����
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.fillRect(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.fillRect(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.fillRect(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // ������ ������ �ȵǴ� ū�ż� ������ ��
		}
	}


	private void paintOval(Graphics page, Point pt1, Point pt2) { // ���׸��� 

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.drawOval(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // ���� ���, ū�ſ��� ������ ����
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.drawOval(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.drawOval(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.drawOval(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // ������ ������ �ȵǴ� ū�ż� ������ ��
		}
	}
	
	private void paintFillOval(Graphics page, Point pt1, Point pt2) { // �� ä��� �׸���

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.fillOval(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // ���� ���, ū�ſ��� ������ ����
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.fillOval(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.fillOval(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.fillOval(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // ������ ������ �ȵǴ� ū�ż� ������ ��
		}
	}
	
	public void undoClicked() {
		if (data.nDrawType == DrawConstants.UNDO) {
			Object obj = new Object();
			obj = dataList.get(size-1);
			undoList.add((DrawData) obj);
			dataList.remove(size-1);
			repaint();
		}
			
	}
	
	public void redoClicked() {
		if (data.nDrawType == DrawConstants.REDO) {
			Object obj  = new Object();
			int undoSize;
			undoSize = undoList.size();
			System.out.println("size:"+undoSize);
			obj = undoList.get(undoSize-1);
			undoList.remove(undoSize-1);
			dataList.add((DrawData) obj);
			repaint();
		}
			
	}
	
	
	

	private class DrawListener implements MouseListener, MouseMotionListener {  //���콺 ����
		public void mouseClicked(MouseEvent event) {
			if (data.nDrawType == DrawConstants.DOT) { // ��Ʈ���
				data.pt1 = event.getPoint(); // �̺�Ʈ �߻��� ��ġ ����
				data.nSize =Integer.parseInt(view.txtSizeNWidth.getText()); // ������ ����
				dataList.add(new DrawData(data)); // ����Ʈ�� �߰�
				//System.out.println(data.nDrawType);
				 view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
				
				repaint();
			} // if
		} // mouseClicked()

		public void mousePressed(MouseEvent event) { // ��Ģ�� ������ �ϴµ� �Ѱ��� ���� ����.
			bDrag = true; //�巡�� �ϰ��
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT
					|| data.nDrawType == DrawConstants.OVAL||data.nDrawType == DrawConstants.UNDO ||data.nDrawType == DrawConstants.REDO) {

				
				data.pt1 = event.getPoint(); //����Ʈ �޾ƿ�
				data.bFill = view.chkFill.isSelected(); // view�� �����ؾ��ϴµ� ����� ���� - > �̱��� ���� ���� ���ٰ���, �Ķ���� �н� �������гο� �並 �Ѱ��� �� ���� �� �ؿ� ����׺�
							// upcall�� �ؾ��� �� �Ȥ��µ� �ѵΰ��� ��
				data.nSize = Integer.parseInt(view.txtSizeNWidth.getText()); //ũ�� ����
				view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
				
				
			} // if
		} // mousePressed()

		public void mouseReleased(MouseEvent event) { //���콺 ��������
			bDrag = false; // �巡�� ����Ʈ
			 view.lblStatus.setText("status : " + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
				
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT // �����̰ų� ��Ʈ�ų� �����̸�
					|| data.nDrawType == DrawConstants.OVAL) {

				data.pt2 = event.getPoint(); // ����Ʈ �޾ƿ�
				dataList.add(new DrawData(data)); // ���帮��Ʈ�� �߰�
				
				repaint();
				
			} // if
		} // mouseReleased()

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

		public void mouseDragged(MouseEvent event) { // �巡���Ҷ�
			view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
			
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT
					|| data.nDrawType == DrawConstants.OVAL) {

				data.pt2 = event.getPoint(); // ����Ʈ �޾ƿ�
				repaint(); // �ٽñ׸�
				
			} // if
		} // mouseDragged()

		public void mouseMoved(MouseEvent event) {
		}
	} // DrawListener class

} // DrawingPanel class
