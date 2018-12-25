package simplepainter;

import java.awt.Color;
import java.awt.Point;

public class DrawData { // 데이터

	public int nDrawType; // 그리기 타입
	public Point pt1, pt2; // 포인터
	public int nSize; // 크기
	public Color colorShape; // 색 설정
	public boolean bFill; // 체크
	
	public DrawData() { // 데이타 그리기
		nDrawType = DrawConstants.NONE; // 어떤 모양 인지
		pt1 = new Point();
		pt2 = new Point();
		nSize = 1; // 사이즈 1
		colorShape = Color.black; // 기본 검정 설정
		bFill = false; // 채우기 기본 false
	} // DrawData()
	
	public DrawData(DrawData data) {  // 데이터 그릴때 사용 
		this.nDrawType = data.nDrawType; // 타입 저장
		this.pt1 = data.pt1; // 저장된 데이터 저장
		this.pt2 = data.pt2; // 저장된 데이터 저장
		this.nSize = data.nSize; // 사이즈 저장
		this.colorShape = data.colorShape; // 색 저장
		this.bFill = data.bFill; //채우기 설정 저장
	} // DrawData()
	
	
} // DrawData class
