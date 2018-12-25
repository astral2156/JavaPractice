package simplepainter;

import java.awt.Color;

public class DrawConstants { // �޴� ��ư ����

	final public static int DOT		= 0;  // �����ϴ� Ÿ�Կ� ���� ������ ����
	final public static int LINE	= 1;
	final public static int RECT	= 2;
	final public static int OVAL	= 3;
	final public static int UNDO    = 4;
	final public static int REDO    = 5;
	
	final public static int NONE	= 6;
	
	final public static String[] MENU =  // �޴� ��ư�� �ؽ�Ʈ ����
		{"DOT","LINE","RECT", "OVAL", "UNDO","REDO"};
	
	final public static Color[] MENU_EXIT = {Color.white, Color.black}; // �޴� ��
	final public static Color[] MENU_ENTER= {Color.lightGray, Color.red}; // �޴� �� ȣ����
} // DrawConstants class
