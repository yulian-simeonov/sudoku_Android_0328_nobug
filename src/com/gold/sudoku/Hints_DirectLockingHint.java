package com.gold.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hints_DirectLockingHint extends Hints_IndirectHint 
{
	ArrayList cells;
	Hints_Cell cell;
	int value;
	Map redPotentials;
	Map orangePotentials;
    ArrayList regions;
		
	public Hints_DirectLockingHint(ArrayList _cells, Hints_Cell _cell, int _value, Map _highlightPotentials, Map _removePotentials, ArrayList _regions)
	{
		//self = [super initWithRemovable:[NSMutableDictionary dictionary]];
		super(new HashMap<Hints_Cell, Hints_BitSet>());
		
		cells = _cells;
		cell = _cell;
		value = _value;
		redPotentials = _removePotentials;
		orangePotentials = _highlightPotentials;
		regions = _regions;
	}

	public int getViewCount()
	{
		return 1;
	}

	public  ArrayList getSelectedCells()
	{
		//return [NSMutableArray arrayWithObject:cell];
		NSMutableArray tmpArray = new NSMutableArray();
		tmpArray.add(cell);
		return tmpArray;
	}

	public Hints_Cell getCell()
	{
		return cell;
	}

	public int getValue()
	{
		return value;
	}

	public boolean isWorth()
	{
		return true;
	}

	public Map getGreenPotentials(int viewNum )
	{
		//result = [NSMutableDictionary dictionaryWithDictionary:orangePotentials];
		Map result = new HashMap<Hints_Cell, Hints_BitSet>();
		result.putAll(orangePotentials);
		
		//[result setObject:[Hints_SingletonBitSet create:value] forKey:cell];
		result.put(cell, Hints_SingletonBitSet.create(value));
		
		return result;
	}

	public Map getRedPotentials(int viewNum)
	{
		//Map result = null;
		//result = [NSMutableDictionary dictionaryWithDictionary:redPotentials];
		Map<Hints_Cell, Hints_BitSet> result = new HashMap<Hints_Cell, Hints_BitSet>();
		result.putAll(redPotentials);
		
		//[result addEntriesFromDictionary:orangePotentials];
		result.putAll(orangePotentials);
		
		return result;
	}

	public Map getOrangePotentials(int viewNum)
	{
		return orangePotentials;
	}

	public ArrayList getLinks(int viewNum)
	{
		//return [NSMutableArray array];
		return new NSMutableArray();
	}

	public ArrayList getRegions()
	{
		return regions;
	}

	public boolean equals(Hints_Hint hint)
	{
		if (!(hint instanceof Hints_DirectLockingHint) )
		if( hint instanceof Hints_DirectLockingHint )
			return false;
		
		Hints_DirectLockingHint otherHint = (Hints_DirectLockingHint)hint;
		
		if(this.value != otherHint.value)
			return false;
		
		if( this.cells.size() != otherHint.cells.size() )
			return false;
		
		return this.cells.containsAll(otherHint.cells);
	}

	public  int hashCode()
	{
		int result = 0;
		
		for(int index = 0; index < cells.size(); index++)
		{
			Hints_Cell _cell = (Hints_Cell) cells.get(index);
			
			result ^= _cell.hashCode();
		}
		
		result ^= value;
		
		return result;
	}
}
