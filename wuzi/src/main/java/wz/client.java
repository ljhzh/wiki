package wz;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

public class client extends JFrame implements MouseListener{
	
	public static int NUM = 0;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 700;
	private static boolean b1 = false;
	private static boolean b2 = false;
	private boolean address = false;
	private static boolean play = false;
	private static boolean repaint = false; 
	private robot robot;
	int piecesnum =0;
	int num = 0;
	private int x;
	private int y;
	private int ch=1,cv=1,cil=1,cir=1;
	
	ConnDialog dialog = new ConnDialog();
	User user = null;
	
	Container con = this.getContentPane();
	board checkerboard = new board();
	LinkedList<piece> pieces = checkerboard.pieces;
	@Override
	public void paint(Graphics g) {
		checkerboard.paint(g);
	}
	
	public void reset() {
		checkerboard.player = false;
		checkerboard.resetBoard();
		this.repaint();
	}
	
	public void win(){
		System.out.println("you have won");
		this.play = false;
	}
	
	
	public int judge(int[][] array,int x,int y) {
		
		num=1;
		int sum=0;
		int ch=1;//水平
		int cv=1;//垂直
		int cil=1;//左斜角
		int cir=1;//右斜角
		int x1=x;
		int y1=y;
		while(--x>0) {
			
			if(array[x][y]==array[x+1][y] && array[x][y]>0) {
				ch++;
			} else {
				x = x1;
				break;
			}
			if(ch==5) {
				return 0;
			}
		}
		
		while(++x<15){
			if(array[x][y] == array[x-1][y] && array[x][y]>0){
				ch++;
			} else {
				x = x1;
				break;
			}
			if(ch==5) {
				return 0;
			}
		}
		
		while(--y>0){
			if(array[x][y] == array[x][y+1] && array[x][y]>0) {
				cv++;
			} else {
				y = y1;
				break;
			}
			if(cv==5) {
				return 0;
			}
		}
		
		while(++y<15){
			if(array[x][y] == array[x][y-1] && array[x][y]>0) {
				cv++;
			} else {
				y = y1;
				break;
			}
			if(cv==5) {
				return 0;
			} 
		}
		
		while(--y>0 && --x>0){
			if(array[x][y]==array[x+1][y+1] && array[x][y]>0) {
				cil++;
			} else {
				x=x1;
				y=y1;
				break;
			}
			if(cil==5) {	
				return 0;
			}
		}
		
		while(++y<15 && ++x<15) {
			if(array[x][y] == array[x-1][y-1] && array[x][y]>0){
				cil++;
			} else {
				x=x1;
				y=y1;
				break;
			}
			if(cil==5) {			
				return 0;
			} 
		}
		
		while(--y>0 && ++x<15){
			if(array[x][y]==array[x-1][y+1] && array[x][y]>0) {
				cir++;
			} else {
				x=x1;
				y=y1;
				break;
			}
			if(cir==5) {
				return 0;
			}
		}
		
		while(++y<15 && --x>0){
			if(array[x][y] == array[x+1][y-1] && array[x][y]>0){
				cir++;
			} else {
				x=x1;
				y=y1;
				break;
			}
			if(cir==5) {
				return 0;
			} 
		}
		this.ch = ch;
		this.cv = cv;
		this.cil = cil;
		this.cir = cir;
		return 1;
		
	}
	
	public void launchFrame() {		
		
		this.setLocation(100, 50);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);	
		this.setTitle("wuziqi");
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.addKeyListener(new KeyMonitor());
		this.addMouseListener(this);
		this.setVisible(true);		
		
	}
	
	public static void main(String args[]) {
		client c = new client();
		c.launchFrame();
		
	}
	
	
	
	
	class KeyMonitor extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			//checkerboard.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_C) {
				dialog.setVisible(true);
			} else {
				//checkerboard.keyPressed(e);
			}
		}
		
	}
	
	class ConnDialog extends Dialog {
		Button b = new Button("new game");
		boolean flag = false;
		TextField name = new TextField("ljh", 12);

		public ConnDialog() {
			super(client.this, true);
			this.setBackground(Color.GREEN);
			this.setLayout(new FlowLayout());
			this.add(new Label("name:"));
			this.add(name);
			this.add(b);
			this.setLocation(100, 100);
			this.pack();
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					setVisible(false);
				}
			});
			b.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					reset();
					String uname = name.getText().trim();
					user = new User(NUM,uname);
					flag = user.UserStart();
					client.this.play = flag;//可以进行下棋
					pieces.removeAll(pieces);
					setVisible(false);					
				}
			});
			
		}

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(play){
			piece p1 = null;
			piece p2 = null;
			this.x = e.getX();
			this.y = e.getY();
			
			if(x<120 || x>680 || y<60 || y>620) {
				this.address = false;
			} else {
				this.address = true;
			}
			
			if(address) {
				int k = ((x+20)-120)/40;
				int j = (y-40)/40;
				System.out.println("坐标为："+x+"-"+y+",位置："+j+","+k);
				if(checkerboard.getPlay()[j][k]==0){
					if(piecesnum%2==0) {
						if(!b1){
							p1 = new piece(1,k,j);
							checkerboard.play(j, k, 1);
							pieces.add(p1);
							if(judge(checkerboard.getPlay(),j,k)==0){
								win();
								checkerboard.getBoard();
								b2 = true;
							} 
						}
						
						if(!b2){
							robot.play(checkerboard.getPlay(),j, k,this.ch,this.cv,this.cil,this.cir);
							p2 = new piece(2,robot.roboty,robot.robotx);
							checkerboard.play(robot.robotx, robot.roboty, 2);
							pieces.add(p2);
							if(judge(checkerboard.getPlay(),j,k)==0){
								win();
								checkerboard.getBoard();
								b1 = true;
							} 
						}
					}
					piecesnum+=2;
					this.repaint();
					//this.repaint = true;
					
				} else {
					System.out.println(checkerboard.getPlay()[k-1][j-1]+"该处已有棋子！");
				}
			}
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
