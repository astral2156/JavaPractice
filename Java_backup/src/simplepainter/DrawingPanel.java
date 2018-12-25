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

public class DrawingPanel extends JPanel { //실제로 그리는 부분

	protected ArrayList<DrawData> dataList; // 드로우 데이타 리스트로 관리
	protected ArrayList<DrawData> undoList;
	protected DrawData data; // 드로우 데이타
	protected boolean bDrag; // 드래그했는지 확인
	protected PrimaryPanelView view; //파라미터 받기위해
	protected int size;
	
	private DrawListener drawL; // 드로우 리스너

	public DrawingPanel(PrimaryPanelView view) {  //갈색 변수는 로컬 변수 파랑은 전역변수
		
		this.view = view; // this.view에 넣음 어디서든 쓰기 위해
		
		setBackground(Color.white); // 색설정
		setBorder(BorderFactory.createLineBorder(Color.darkGray)); // 경계선 설정

		drawL = new DrawListener(); // 리스너 설정
		this.addMouseListener(drawL); // 마우스 리스너 추가
		this.addMouseMotionListener(drawL);// 마우스 모션 리스너 추가

		dataList = new ArrayList<DrawData>(); // 데이타 리스트로 관리
		data = new DrawData();// 드로우데이타 생성
		undoList = new ArrayList<DrawData>();

		bDrag = false; //디폴트 폴스

	} // DrawingPanel()

	public void paintComponent(Graphics page) { //드래그 할때, 저장될땐 반복해서 for문으로 그림
		super.paintComponent(page);

		size = dataList.size();
		System.out.println(size);
		
		Graphics2D page2D = (Graphics2D) page; //
		
		
		if (bDrag) {	//드래그할때 그리는 부분
			page.setColor(data.colorShape); 
			switch (data.nDrawType) {// data 사용
			case DrawConstants.LINE: // 라인일 경우
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); // 두깨 설정
				page.drawLine(data.pt1.x, data.pt1.y, data.pt2.x, data.pt2.y); // 그리기
				break;
			case DrawConstants.RECT: // 사각형 일 경우
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); //두깨 설정
				if(data.bFill) paintFillRect(page, data.pt1, data.pt2); // bfill이  true면 채워서 그리기
				else paintRect(page, data.pt1, data.pt2); // 사각형 그리기
				break;
			
			case DrawConstants.OVAL: // 원 그리기
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(data.nSize)); //두깨 설정
				if(data.bFill) paintFillOval(page, data.pt1, data.pt2); // 채워서 그리기
				else paintOval (page, data.pt1, data.pt2); // 그냥 그리기
				break;
			} // switch
		} // if
/*
		for (DrawData getData : dataList) {  //저장된걸 그린다. 저장된걸 그리는 부분은 이곳
			page.setColor(getData.colorShape); // 저장된 get data 사용
			switch (getData.nDrawType) { // 스위치 케이스
			
			case DrawConstants.DOT: // 도트 일때 
				page.fillOval(getData.pt1.x - getData.nSize / 2, getData.pt1.y - getData.nSize / 2, getData.nSize, //도트를 저장한 대로
						getData.nSize);
				break;
				
			case DrawConstants.LINE: // 라인일때
				page2D = (Graphics2D) page; 
				page2D.setStroke(new BasicStroke(getData.nSize)); //데이터 가져와서 그림
				page.drawLine(getData.pt1.x, getData.pt1.y, getData.pt2.x, getData.pt2.y); // 데이터 그림
				break;
				
			case DrawConstants.RECT: // 사각형일때
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //두깨 설정 
				if(getData.bFill) paintFillRect(page, getData.pt1, getData.pt2); // 채운 데이터면 이걸로 그림
				else paintRect(page, getData.pt1, getData.pt2); // 그림
				break;
				
			case DrawConstants.OVAL: // 원 일때
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //두깨 설정
				if(getData.bFill) paintFillOval(page, getData.pt1, getData.pt2); //채운 데이터면 이걸로 그림
				else paintOval (page, getData.pt1, getData.pt2); //그림
				//paintOval(page,getData.pt1,getData.pt2); // for 문에서는 getData로 받아와서 사용한다.
				break;
			case DrawConstants.UNDO: // 원 일때

				break;
				
				
				
				
			} // switch
		} // for
*/
		
		DrawAct(page); //  3번. 
		
		
	} // paintComponent()

	
	private void DrawAct(Graphics page) {

		Graphics2D page2D = (Graphics2D) page;
		
		for (DrawData getData : dataList) {  //저장된걸 그린다. 저장된걸 그리는 부분은 이곳
			page.setColor(getData.colorShape); // 저장된 get data 사용
			switch (getData.nDrawType) { // 스위치 케이스
			
			case DrawConstants.DOT: // 도트 일때 
				page.fillOval(getData.pt1.x - getData.nSize / 2, getData.pt1.y - getData.nSize / 2, getData.nSize, //도트를 저장한 대로
						getData.nSize);
				break;
				
			case DrawConstants.LINE: // 라인일때
				page2D = (Graphics2D) page; 
				page2D.setStroke(new BasicStroke(getData.nSize)); //데이터 가져와서 그림
				page.drawLine(getData.pt1.x, getData.pt1.y, getData.pt2.x, getData.pt2.y); // 데이터 그림
				break;
				
			case DrawConstants.RECT: // 사각형일때
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //두깨 설정 
				if(getData.bFill) paintFillRect(page, getData.pt1, getData.pt2); // 채운 데이터면 이걸로 그림
				else paintRect(page, getData.pt1, getData.pt2); // 그림
				break;
				
			case DrawConstants.OVAL: // 원 일때
				page2D = (Graphics2D) page;
				page2D.setStroke(new BasicStroke(getData.nSize)); //두깨 설정
				if(getData.bFill) paintFillOval(page, getData.pt1, getData.pt2); //채운 데이터면 이걸로 그림
				else paintOval (page, getData.pt1, getData.pt2); //그림
				//paintOval(page,getData.pt1,getData.pt2); // for 문에서는 getData로 받아와서 사용한다.
				break;
			case DrawConstants.UNDO: // undo 일때
			case DrawConstants.REDO: // undo 일때
				

				break;
				
				
				
				
			} // switch
		} // for
	}
	
	private void paintRect(Graphics page, Point pt1, Point pt2) { // 네모 그리기

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.drawRect(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // 좌측 상단, 큰거에서 작은거 빼기
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.drawRect(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.drawRect(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.drawRect(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // 음수가 나오면 안되니 큰거서 작은거 뺌
		}
		
	}
	
	private void paintFillRect(Graphics page, Point pt1, Point pt2) { //네모 채우기 그리기

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.fillRect(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // 좌측 상단, 큰거에서 작은거 빼기
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.fillRect(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.fillRect(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.fillRect(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // 음수가 나오면 안되니 큰거서 작은거 뺌
		}
	}


	private void paintOval(Graphics page, Point pt1, Point pt2) { // 원그리기 

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.drawOval(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // 좌측 상단, 큰거에서 작은거 빼기
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.drawOval(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.drawOval(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.drawOval(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // 음수가 나오면 안되니 큰거서 작은거 뺌
		}
	}
	
	private void paintFillOval(Graphics page, Point pt1, Point pt2) { // 원 채우기 그리기

		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			page.fillOval(pt1.x, pt1.y, pt2.x - pt1.x, pt2.y - pt1.y); // 좌측 상단, 큰거에서 작은거 빼기
		} else if (pt1.x < pt2.x && pt1.y > pt2.y) {
			page.fillOval(pt1.x, pt2.y, pt2.x - pt1.x, pt1.y - pt2.y);
		} else if (pt1.x > pt2.x && pt1.y < pt2.y) {
			page.fillOval(pt2.x, pt1.y, pt1.x - pt2.x, pt2.y - pt1.y);
		} else if (pt1.x > pt2.x && pt1.y > pt2.y) {
			page.fillOval(pt2.x, pt2.y, pt1.x - pt2.x, pt1.y - pt2.y); // 음수가 나오면 안되니 큰거서 작은거 뺌
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
	
	
	

	private class DrawListener implements MouseListener, MouseMotionListener {  //마우스 관리
		public void mouseClicked(MouseEvent event) {
			if (data.nDrawType == DrawConstants.DOT) { // 도트라면
				data.pt1 = event.getPoint(); // 이벤트 발생한 위치 저장
				data.nSize =Integer.parseInt(view.txtSizeNWidth.getText()); // 사이즈 저장
				dataList.add(new DrawData(data)); // 리스트에 추가
				//System.out.println(data.nDrawType);
				 view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
				
				repaint();
			} // if
		} // mouseClicked()

		public void mousePressed(MouseEvent event) { // 원칙은 나눠야 하는데 한곳에 같이 쓴다.
			bDrag = true; //드래그 일경우
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT
					|| data.nDrawType == DrawConstants.OVAL||data.nDrawType == DrawConstants.UNDO ||data.nDrawType == DrawConstants.REDO) {

				
				data.pt1 = event.getPoint(); //포인트 받아옴
				data.bFill = view.chkFill.isSelected(); // view에 접근해야하는데 방법이 없으 - > 싱글톤 패턴 ㅇ로 접근가능, 파라ㅣ터 패싱 ㄷ로윙패널에 뷰를 넘겨줄 것 현재 뷰 밑에 드로잉뷰
							// upcall을 해야함 잘 안ㅆ는데 한두개는 씀
				data.nSize = Integer.parseInt(view.txtSizeNWidth.getText()); //크기 저장
				view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
				
				
			} // if
		} // mousePressed()

		public void mouseReleased(MouseEvent event) { //마우스 놓았을때
			bDrag = false; // 드래그 디폴트
			 view.lblStatus.setText("status : " + "COlor : " + view.drawingPanel.data.colorShape + "size :" +   view.txtSizeNWidth.getText());
				
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT // 라인이거나 렉트거나 오발이면
					|| data.nDrawType == DrawConstants.OVAL) {

				data.pt2 = event.getPoint(); // 포인트 받아옴
				dataList.add(new DrawData(data)); // 저장리스트에 추가
				
				repaint();
				
			} // if
		} // mouseReleased()

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}

		public void mouseDragged(MouseEvent event) { // 드래그할때
			view.lblStatus.setText("status :  Dragged :" + "pt1 : " + data.pt1.x + data.pt1.y + "pt2 : " + data.pt2.x +data.pt2.y );
			
			if (data.nDrawType == DrawConstants.LINE || data.nDrawType == DrawConstants.RECT
					|| data.nDrawType == DrawConstants.OVAL) {

				data.pt2 = event.getPoint(); // 포인트 받아옴
				repaint(); // 다시그림
				
			} // if
		} // mouseDragged()

		public void mouseMoved(MouseEvent event) {
		}
	} // DrawListener class

} // DrawingPanel class
