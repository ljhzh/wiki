package wz;

import java.awt.Frame;

public class chese extends Frame{
	
	private static board checkerboard;//һ������
	private static final int black = 1;
	private static final int white = 2;
	private static final int num = 225;
	private static final int blpiece = 113;//����113ö 
	private static final int whpiece = 112;//����112ö
	private static int pgroup[][]; //����

	
	
	
	public static void init(User u){
		System.out.println();
		checkerboard = new board();
		//checkerboard.init();
		checkerboard.getBoard();;
	}
	
	public boolean judge(User u) {
		return false;
	}
	
}
