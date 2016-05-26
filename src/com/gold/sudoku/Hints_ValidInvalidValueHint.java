package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_ValidInvalidValueHint extends Hints_Hint {

	boolean isValid;
	ArrayList cells;

	public Hints_ValidInvalidValueHint(ArrayList _cells, boolean _isValid)
	{
		super();
		
		isValid = _isValid;
		cells = _cells;
	}
	
	public ArrayList getCells()
	{
		return cells;
	}
}
