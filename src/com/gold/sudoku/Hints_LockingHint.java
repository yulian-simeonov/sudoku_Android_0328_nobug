package com.gold.sudoku;

import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

public class Hints_LockingHint extends Hints_IndirectHint {
	
	/**
	 * Locking hint (Pointing, Claiming, X-Wing, Swordfish or Jellyfish)
	 */
	ArrayList<Hints_Cell> cells = new ArrayList<Hints_Cell>();
	int value;
	Map highlightPotentials;
	ArrayList<Hints_Region> regions = new ArrayList<Hints_Region>();
	
	//public Hints_LockingHint(NSMutableArray _cells , int _value, Map _highlightPotentials, Map _removePotentials, ArrayList _regions )
	public Hints_LockingHint(ArrayList<Hints_Cell> _cells , int _value, Map _highlightPotentials, Map _removePotentials, ArrayList<Hints_Region> _regions )
	{
		//self = [super initWithRemovable:_removePotentials];
		super(_removePotentials);
		
		cells = _cells;
		value = _value;
		highlightPotentials = _highlightPotentials;
		regions = _regions;
	}

	public int getViewCount()
	{
		return 1;
	}

	public ArrayList<Hints_Cell> getSelectedCells()
	{
		return cells;
	}

	public Map getGreenPotentials(int viewNum )
	{
		return highlightPotentials;
	}

	public Map getRedPotentials(int viewNum)
	{
		return super.getRemovablePotentials();
	}

	public ArrayList getLinks(int viewNum)
	{
		return null;
	}

	public ArrayList<Hints_Region> getRegions()
	{
		return regions;
	}

	public boolean equals(Hints_Hint hint)
	{
		if(!(hint instanceof Hints_LockingHint ) )
			return false;
		   
		Hints_LockingHint otherHint = (Hints_LockingHint)hint;
		
		if(this.value != otherHint.value)
			return false;
		   
		if(this.cells.size() != otherHint.cells.size())
			return false;
		   
		return this.cells.containsAll(otherHint.cells);
	}

	public int hashCode()
	{
		int result = 0;
		
		for(int index = 0; index < cells.size(); index++)
		{
			Hints_Cell cell = cells.get(index);
			
			result ^= cell.hashCode();
		}
		
		result ^= value;
		
		return result;
	}
}
