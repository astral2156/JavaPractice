package simplepainter;

import java.awt.Color;

public class DrawConstants { // 메뉴 버튼 관리

	final public static int DOT		= 0;  // 선택하는 타입에 따라 정수로 저장
	final public static int LINE	= 1;
	final public static int RECT	= 2;
	final public static int OVAL	= 3;
	final public static int UNDO    = 4;
	final public static int REDO    = 5;
	
	final public static int NONE	= 6;
	
	final public static String[] MENU =  // 메뉴 버튼에 텍스트 넣음
		{"DOT","LINE","RECT", "OVAL", "UNDO","REDO"};
	
	final public static Color[] MENU_EXIT = {Color.white, Color.black}; // 메뉴 색
	final public static Color[] MENU_ENTER= {Color.lightGray, Color.red}; // 메뉴 색 호버링
} // DrawConstants class
