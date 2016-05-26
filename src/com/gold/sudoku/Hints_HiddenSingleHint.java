package com.gold.sudoku;

public class Hints_HiddenSingleHint extends Hints_DirectHint {

	boolean isAlone; // Last empty cell in a region
	
	public Hints_HiddenSingleHint(Hints_Region _region, Hints_Cell _cell, int _value, boolean _isAlone)
	{
		//self = [super initWith:_region cell:_cell value:_value];
		super( _region, _cell, _value );
		
		isAlone = _isAlone;
		
		//return self;
	}

}
