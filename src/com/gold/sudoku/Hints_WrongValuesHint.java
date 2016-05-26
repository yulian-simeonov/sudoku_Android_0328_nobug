package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_WrongValuesHint extends Hints_Hint {
	ArrayList cells;
	ArrayList regions;
	
	public Hints_WrongValuesHint(ArrayList _cells, ArrayList _regions)
	{
		//self = [super init];
		super();
		
		cells = _cells;
		regions = _regions;
	}

	public ArrayList getCells()
	{
		return cells;
	}

}
