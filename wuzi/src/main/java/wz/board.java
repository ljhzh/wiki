package wz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.JFrame;

import wz.client.KeyMonitor;

public class board extends JFrame {

	private static final double u_length = 15.00;
	private static final int xWidth = 15;
	private static final int yHeight = 15;
	public static boolean player = false;
	public static final double Width = xWidth * u_length;
	public static final double Height = yHeight * u_length;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 700;
	public static LinkedList<piece> pieces = new LinkedList<piece>();//数据棋子
	DataPieces dp = new DataPieces();
	
	private board getBoardClass(){
		return this;
	}
	
	private static class DataPieces {//棋盘数据
		private static int[][] a ;//数据棋盘
		
		private static void init() {
			a = new int[xWidth][yHeight];
			for(int i=0; i<15; i++) {
				for(int j=0 ; j<15 ; j++) {
					a[i][j] = 0; 
				}
			}
		}
		
		protected DataPieces() {
			init();
		}
		
		public void a(int[][] board) {
			this.a = board;
		}
		
	}
	
	
	@Override
	public void paint(Graphics g) {		
		if(player) {
			for (int i = 0; i < pieces.size(); i++) {
				piece p = pieces.get(i);
				p.draw(g);
			}
		} else {
			g.clearRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			draw(g);
			player = true;
		}
	}
	
	
	
	public void getBoard() {
		for(int i=0;i<15;i++) {
 			for(int j=0;j<15;j++){
 				System.out.print(dp.a[i][j]+" ");
 			}
 			System.out.println();
 		}
	}
	
	public void resetBoard() {
		board b = new board();
		dp = new DataPieces();
		//pieces.removeAll(pieces);
		this.repaint();
	}
	
	public void play(int x,int y , int color) {
		dp.a[x][y] = color;
	}
	
	public int[][] getPlay() {
		return dp.a;
	}
	
	public boolean end() {
		return false;
	}
	
	public void draw(Graphics g) {
		for(int i=0;i<xWidth;i++) {
			g.drawLine(120+i*40, 60, 120+i*40, 620);
			g.drawLine(120, 60+40*i, 680, 60+40*i);
		}
	}

}
