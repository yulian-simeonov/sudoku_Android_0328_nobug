package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_DirectHint extends Hints_Hint{

    Hints_Region region; // The concerned region, if any
    Hints_Cell cell; // The cell that can be filled
    int value; // The value that can be put in the cell
	
	public Hints_DirectHint( Hints_Region _region, Hints_Cell _cell, int _value )
	{
		//self = [super init];
		super();
		
		region = _region;
		cell = _cell;
		value = _value;
	}

	
	public Hints_Region getRegion()
	{
		return region;
	}

	public ArrayList getRegions()
	{
		ArrayList result= null;
		
		if(region != null) {
			// [NSMutableArray arrayWithObject:region];
			result = new NSMutableArray();
			result.add(region);
		} else
			result = (ArrayList)(new NSMutableArray()); // array];
	
		return result;
	}

	public Hints_Cell getCell()
	{
		return cell;
	}

	public int getValue()
	{
		return value;
	}

	public boolean equals(Hints_Hint hint)
	{
		if(!(hint instanceof Hints_DirectHint) )
			return false;
		
		Hints_DirectHint otherHint = (Hints_DirectHint)hint;
		return (this.cell.equals(otherHint.cell)) && (this.value == otherHint.value);
	}

	public int hashCode()
	{
		return (this.cell.hashCode() ^ this.value);
	}
}
