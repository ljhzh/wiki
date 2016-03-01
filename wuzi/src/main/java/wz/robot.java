package wz;

import java.util.ArrayList;
import java.util.Random;

public class robot {

	private static User user = null;
	public static int robotx ;
	public static int roboty ;
	private static dir cch = new dir();
	private static dir ccv = new dir();
	private static dir ccil = new dir();
	private static dir ccir = new dir();
	
	public robot() {	
		this.user = new User(10001,"robot");
	}
	/*
	 * ch=Ë®Æ½
	   cv=´¹Ö±
	   cil=×óĞ±½Ç
	   cir=ÓÒĞ±½Ç
	 * */
	public static void play(int[][] array,int x,int y,int ch,int cv, int cil, int cir) {
		makedir(array,ch,cv,cil,cir,x,y);
	}
	
	public static boolean empty(int[][] a,int x,int y) {
		if(a[x][y]==0){
			return true;
		} else 
			return false;
	}
	
	public static void ch(int[][] array,int x,int y,int ch,int flag) {
		if(x>0 && x<14) {
			if(flag == 1) {
				x = handle.left(x, y);
				while(array[x][y]==array[x+1][y] || !empty(array, x, y)) {
					x = handle.left(x, y);
				}
			} else if(flag == 0) {
				x = handle.right(x, y);
				//System.out.println(x+" ,"+y);
				while(array[x][y]==array[x-1][y] || !empty(array, x, y)) {
					x = handle.right(x, y);
				}	
			}
		}
		
		robotx = x;
		roboty = y;
	}
	
	public static void cv(int[][] array,int x,int y,int ch,int flag) {
		if(flag == 1){
			if(y>0 && y<14){
				y = handle.up(x, y);
				while(array[x][y]==array[x][y+1] || !empty(array, x, y)) {
					y = handle.up(x, y);
				}
			}
		} else if(flag == 0){
			if(x>0 && x<14){
				y = handle.down(x, y);
				while(array[x][y]==array[x][y-1] || !empty(array, x, y)) {
					y = handle.down(x, y);
				}
			}
		}
		
		robotx = x;
		roboty = y;
	}
	
	public static void cil(int[][] array,int x,int y,int ch,int flag) {
		if(flag == 1){
			if(y>0 && y<14 && x>0 && x < 14){
				x = handle.left(x,y);
				y = handle.up(x, y);
				while(array[x][y]==array[x+1][y+1] || !empty(array, x, y)) {
					x = handle.left(x,y);
					y = handle.up(x, y);
				}
			}
		} else if(flag == 0){
			if(x>0 && x<14){
				while(array[x][y]==array[x-1][y-1] || !empty(array, x, y)) {
					x = handle.right(x,y);
					y = handle.down(x, y);
				}
			}
		}
		
		robotx = x;
		roboty = y;
	}
	
	public static void cir(int[][] array,int x,int y,int ch,int flag) {
		if(flag == 1){
			if(y>0 && y<14 && x>0 && x < 14){
				x = handle.right(x, y);
				y = handle.up(x, y);
				while(array[x][y]==array[x-1][y+1] || !empty(array, x, y)) {
					x = handle.right(x, y);
					y = handle.up(x, y);
				}
			}
		} else if(flag == 0){
			if(x>0 && x<14){
				x = handle.left(x, y);
				y = handle.down(x, y);
				while(array[x][y]==array[x+1][y-1] || !empty(array, x, y)) {
					x = handle.left(x, y);
					y = handle.down(x, y);
				}
			}
		}
		
		robotx = x;
		roboty = y;
		
	}

	
	private static void makedir(int[][] array,int ch,int cv,int cil,int cir,int x,int y){
		robotx = x;
		roboty = y;
		int max = max(ch,cv,cil,cir);
		int dir = judge(ch,cv,cil,cir,max);
		if(dir == 1){
			ch(array,robotx,roboty,ch,makeflag());
		} else if (dir == 2) {
			cv(array,robotx,roboty,cv,makeflag());
		} else if (dir == 3) {
			cil(array,robotx,roboty,cil,makeflag());
		} else if (dir == 4) {
			cir(array,robotx,roboty,cir,makeflag());
		} 
		
	}
	
	private static int match(int ch,int cv,int cil, int cir,int max) {
		ArrayList<dir> dirlist = new ArrayList<dir>();
		int count = 0;
		int match = 0;
		if(ch == max) {
			count += count(ch,max);
			dirlist.add(cch);
		}
		if(cv == max) {
			count += count(cv,max);
			dirlist.add(ccv);
		}
		if(cil == max) {
			count += count(cil,max);
			dirlist.add(ccil);
		} 
		if(cir == max) {
			count += count(cir,max);
			dirlist.add(ccir);
		}	
		Random rand = new Random();
		match = rand.nextInt(count);	
		System.out.println(dirlist.get(match).dir);
		return dirlist.get(match).dir;
	}
	
	private static int judge(int ch,int cv,int cil, int cir,int max) {
		int count=0;
		count += count(ch,max);
		count += count(cv,max);
		count += count(cil,max);
		count += count(cir,max);
		if(count>1) {
			return match(ch, cv, cil, cir,max);
		} else {
			if(ch == max){
				return 1;
			} else if(cv == max) {
				return 2;
			} else if(cil == max) {
				return 3;
			} else if(cir == max) {
				return 4;
			} else
				return match(ch, cv, cil, cir,max);
		}
		
	}
	
	private static int count(int x,int y){
		if(x==y){
			return 1;
		} else
			return 0;
	}
	
	
	private static int max(int a,int b,int c,int d){
		int max = 0;
		max = getmax(a,max);
		max = getmax(b,max);
		max = getmax(c,max);
		max = getmax(d,max);
		return max;
	}
	
	private static int getmax(int x,int b){
		if(x>b){
			return x;
		} else
			return b;
	}

	private static int makeflag(){
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	private static class dir {
		
		private static int id=0;
		public int dir;

		public dir() {
			id++;
			this.dir = id;
		}
	}	
	
}
