package com.gold.sudoku;

public class GameGridType{

	SudokuGridItemType grid[][] = new SudokuGridItemType[9][9];
	
	GameGridType(){
		for(int i=0; i<9; i++){
			for(int j=0; j< 9; j++){
				grid[i][j] = new SudokuGridItemType();
			}
		}
	}

	public void memmove(GameGridType newGrid)
	{
		for(int i=0; i<9; i++){
			for(int j=0; j< 9; j++){
				this.grid[i][j] = newGrid.grid[i][j];
			}
		}
	}
	
	public class SudokuGridItemType {
		
		int number;
		int color;
		int candidates[] = new int[9]; 
		
		SudokuGridItemType(){
			number = -1;
			color = -1;
			for(int i=0; i<9; i++){
				candidates[i] = -1;
			}
		}
		
	}
	
}
//typedef SudokuGridItemType GameGridType[9][9];

