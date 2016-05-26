package com.gold.sudoku;

public class Hints_SuggestedCellValueHint  extends Hints_Hint{
	
	boolean isValue;
	int value;
	Hints_Cell cell;
	
	Hints_SuggestedCellValueHint()
	{
		isValue = false;
		int value = -1;
		cell = new Hints_Cell(null, -1, -1);
	}
	
}
