package com.gold.sudoku;

public class Hints_ValidInvalidValue extends Hints_HintProducer{

	int value;
	boolean isValid;


	public  Hints_ValidInvalidValue (int _value, boolean _isValid)
	{
		super();
	
		value = _value;
		isValid = _isValid;
	}

	public void getHints (Hints_Grid grid)
	{
		NSMutableArray cells = new NSMutableArray(); // array];
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				Hints_Cell cell = grid.getCellAtX(x, y);
				
				if(cell.value == 0)
				{
					if(isValid)
					{
						if(cell.potentialValues.get(value) == true)
							cells.add(cell);
					}
					else
					{
						if(cell.potentialValues.get(value) == false)
							cells.add(cell);
					}
				}
			}
		}

		//Hints_ValidInvalidValueHint* hint = [[[Hints_ValidInvalidValueHint alloc] initWith:cells isValid:isValid] autorelease];
		Hints_ValidInvalidValueHint hint = new Hints_ValidInvalidValueHint(cells, isValid); 
		
		addHint(hint);
	}
}
