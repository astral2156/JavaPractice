package simplepainter;

import java.awt.Color;
import java.awt.Point;

public class DrawData { // ������

	public int nDrawType; // �׸��� Ÿ��
	public Point pt1, pt2; // ������
	public int nSize; // ũ��
	public Color colorShape; // �� ����
	public boolean bFill; // üũ
	
	public DrawData() { // ����Ÿ �׸���
		nDrawType = DrawConstants.NONE; // � ��� ����
		pt1 = new Point();
		pt2 = new Point();
		nSize = 1; // ������ 1
		colorShape = Color.black; // �⺻ ���� ����
		bFill = false; // ä��� �⺻ false
	} // DrawData()
	
	public DrawData(DrawData data) {  // ������ �׸��� ��� 
		this.nDrawType = data.nDrawType; // Ÿ�� ����
		this.pt1 = data.pt1; // ����� ������ ����
		this.pt2 = data.pt2; // ����� ������ ����
		this.nSize = data.nSize; // ������ ����
		this.colorShape = data.colorShape; // �� ����
		this.bFill = data.bFill; //ä��� ���� ����
	} // DrawData()
	
	
} // DrawData class
