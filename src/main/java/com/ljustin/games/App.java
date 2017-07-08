package com.ljustin.games;


public class App {
	public static final int GRID_SIZE = 4;
	public static void main(String[] args) {
		Tetromino l = TetrominoFactory.newTetromino(Tetromino.Type.I);
		System.out.println(l);
		l.rotate();
		System.out.println(l);
		l.rotate();
		System.out.println(l);
		l.rotate();
		System.out.println(l);
		Tetromino s = TetrominoFactory.newTetromino(Tetromino.Type.S);
		System.out.println(s);
		s.rotate();
		System.out.println(s);
		s.rotate();
		System.out.println(s);
		s.rotate();
		System.out.println(s);
		Tetromino o = TetrominoFactory.newTetromino(Tetromino.Type.O);
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o = TetrominoFactory.newTetromino(Tetromino.Type.J);
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o = TetrominoFactory.newTetromino(Tetromino.Type.L);
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o = TetrominoFactory.newTetromino(Tetromino.Type.T);
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o = TetrominoFactory.newTetromino(Tetromino.Type.Z);
		System.out.println(o);
		o.rotate();
		System.out.println(o);
		o.rotate();
		System.out.println(o);
	}
}

class Tetromino {
	public enum Type {
		J,
		L,
		S,
		T,
		Z,
		O,
		I;
	}
	private Type type;
	private Point[] points;
	private Point origin;
	
	public Tetromino(Type type, Point[] points) {
		this.type = type;
		this.points = points;
		for (Point point:points) {
			if (point.origin) {
				this.origin = point;
			}
		}
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Point[] getPoints() {
		return points;
	}

	public void setPoints(Point[] points) {
		this.points = points;
	}
	
	public void rotate() {
		for (Point point:points) {
			/*
			 *  | | | | |
			 *	|x|o|x|x|
			 *	| | | | |
			 *	| | | | |
			 *  (0,1)
			 *  (1,1)
			 *  (0,1) -> (-1,0) -> (-1,0) -> (0,1) -> (0,-1) -> (1, 0)
			 *  (3,1) -> (2,0) -> (2,0) -> (0, 2) -> (0,-2) -> (1, -1)
			 *  											-> (1, 3)
			 */
			if (!point.origin && this.type != Type.O) {
				// translate point wrt new origin
				point.x -= this.origin.x;
				point.y -= this.origin.y;
				// as y increase downward for (0,0) origin
				point.y = -point.y;
				
		        // May need to round results after rotation
		        int x3 = (int)Math.round(point.x * Math.cos(Math.PI/2) - point.y * Math.sin(Math.PI/2)); 
		        int y3 = (int)Math.round(point.x * Math.sin(Math.PI/2) + point.y * Math.cos(Math.PI/2));

		        if (this.type != Type.I)
		        	y3 = -y3;	
		        /*
				int temp = point.x;
				int x2 = -point.y;
				int y2 = temp;
				
				y2 = -y2;
				*/
				x3 += this.origin.x;
				y3 += this.origin.y;
				
				point.x = x3;
				point.y = y3;
			}
		}

		
		
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i=0; i<App.GRID_SIZE;i++) {
			for (int j=0; j<App.GRID_SIZE; j++) {
				if (j<=(App.GRID_SIZE-1)) {
					sb.append(j==0 ? "|": "");
					int pointOnGridIdx=-1;
					for (int k=0;k<points.length;k++) {
						if (points[k].x==j && points[k].y==i) {
							pointOnGridIdx = k;
						}
					}
					sb.append(pointOnGridIdx != -1 ? points[pointOnGridIdx].origin ? "o" : "x" : " ");
					sb.append("|");
				} 
				
				if (j==App.GRID_SIZE-1)
					sb.append("\n");
				
			}
		}
		return sb.toString();
	}
}

class TetrominoFactory {
	private TetrominoFactory() {}
	public static Tetromino newTetromino(Tetromino.Type type) {
		Point[] points = null;
		switch(type) {
			case I:
				points = new Point[]{ new Point(0,1), new Point(1,1,true), new Point(2,1), new Point(3,1)};
				break;
			case S:
				points = new Point[]{ new Point(1,0), new Point(1,1,true), new Point(2,0), new Point(0,1)};
				break;
			case O:
				points = new Point[]{ new Point(1,0), new Point(2,0), new Point(1,1), new Point(2,1)};
				break;
			case L:
				points = new Point[]{ new Point(0,0), new Point(0,1), new Point(1,1,true), new Point(2,1)};
				break;
			case J:
				points = new Point[]{ new Point(2,0), new Point(0,1), new Point(1,1,true), new Point(2,1)};
				break;
			case T:
				points = new Point[]{ new Point(1,0), new Point(0,1), new Point(1,1,true), new Point(2,1)};
				break;
			case Z:
				points = new Point[]{ new Point(0,0), new Point(1,0), new Point(1,1,true), new Point(2,1)};
				break;
		}

		return new Tetromino(type, points);
	}
}

class Point {
	int x,y;
	boolean origin;
	public Point(int x, int y) {
		this(x, y, false);
	}
	public Point(int x, int y, boolean origin) {
		this.x = x;
		this.y = y;
		this.origin = origin;
	}
}


