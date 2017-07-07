package com.ljustin.poc.tetris;

import java.util.Arrays;
import java.util.List;

public class App {
	public static void main(String[] args) {
		Tetromino l = TetrominoFactory.newTetromino(Tetromino.Type.I);
		System.out.println(l);
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
	private Grid grid;
	
	public Tetromino(Type type, Grid grid) {
		this.type = type;
		this.grid = grid;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public void rotate() {
		/*
		 *  xNew = -y
		 *	yNew = x
		 */
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		Grid grid = this.getGrid();
		boolean[][] cells = grid.getCells();
		for (int i=0; i<Grid.SIZE;i++) {
			for (int j=0; j<Grid.SIZE; j++) {
				if (j<=(Grid.SIZE-1)) {
					sb.append(j==0 ? "|": "");
					sb.append(cells[j][i] ? "x" : " ");
					sb.append("|");
				} 
				
				if (j==Grid.SIZE-1)
					sb.append("\n");
				
			}
		}
		return sb.toString();
	}
}

class TetrominoFactory {
	private TetrominoFactory() {}
	public static Tetromino newTetromino(Tetromino.Type type) {
		Grid grid = Grid.newGrid();
		List<Coord> coord = null;
		switch(type) {
			case I:
				coord = Arrays.asList(new Coord(0,1), new Coord(1,1), new Coord(2,1), new Coord(3,1));
				break;
		}
		for (int i=0;i<coord.size();i++) {
			Coord cd = coord.get(i);
			grid.setCell(cd, true);
		}
		return new Tetromino(type, grid);
	}
}

class Grid {
	static final int SIZE = 4; // 4 x 4 grid
	private boolean[][] cells;
	private Grid() {}
	public static Grid newGrid() {
		Grid grid = new Grid();
		grid.cells =  new boolean[SIZE][SIZE];
		return grid;
	}
	public void setCell(Coord coord, boolean value) {
		cells[coord.x][coord.y] = value;
	}
	// todo: iterator
	public boolean[][] getCells() {
		return this.cells;
	}
}

class Coord {
	int x,y;
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


