package com.gold.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hints_XYWingHint extends Hints_IndirectHint {
	

	boolean isXYZ;
	Hints_Cell xyCell;
	Hints_Cell xzCell;
    Hints_Cell yzCell;
    int value;


	public  Hints_XYWingHint(Map _removablePotentials, boolean _isXYZ, Hints_Cell _xyCell, Hints_Cell _xzCell, Hints_Cell _yzCell, int _value)
	{
		super(_removablePotentials);
		
		isXYZ = _isXYZ;
	    this.xyCell = _xyCell;
	    this.xzCell = _xzCell;
	    this.yzCell = _yzCell;
	    value = _value;
	}

	public int getX() 
	{
		Hints_BitSet xyPotentials = xyCell.getPotentialValues();
		
		int x = xyPotentials.nextSetBit(0);
		
		if (x == value)
			x = xyPotentials.nextSetBit(x + 1);
		
		return x;
	}

	public int getY() 
	{
		Hints_BitSet xyPotentials = xyCell.getPotentialValues();
		
		int x = this.getX();
		int y = xyPotentials.nextSetBit(x + 1);
		
		if (y == value)
			y = xyPotentials.nextSetBit(y + 1);
		
		return y;
	}

	public  Map getGreenPotentials(int viewNum )
	{
		//NSMutableDictionary* result = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> result = new HashMap<Hints_Cell, Hints_BitSet>();
		
		// x and y of XY cell (orange)
		result.put(xyCell, xyCell.getPotentialValues()); // setObject:[xyCell getPotentialValues] forKey:xyCell];
		
		// z value (green)
		Hints_BitSet zSet = Hints_SingletonBitSet.create(value);
		//[result setObject:zSet forKey:xzCell];
		result.put(xzCell, zSet); // setObject:zSet forKey:xzCell];
		//[result setObject:zSet forKey:yzCell];
		result.put(yzCell, zSet); // setObject:zSet forKey:yzCell];
		
		return result;
	}

	public  Map getRedPotentials(int viewNum)
	{
		//NSMutableDictionary* result = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> result = new HashMap<Hints_Cell, Hints_BitSet>();
		
		//[result addEntriesFromDictionary:[super getRemovablePotentials]];
		result.putAll(super.getRemovablePotentials());
		
		// Add x and y of XY cell (orange)
		Hints_BitSet xy = new Hints_BitSet(10); // autorelease];
		xy.clear();
		xy.set(true, getX());
		xy.set(true, getY());
		
		//[result setObject:xy forKey:xyCell];
		result.put(xyCell, xy);
		
		return result;
	}

	public Map getOrangePotentials(int viewNum)
	{
		//NSMutableDictionary* result = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> result = new HashMap<Hints_Cell, Hints_BitSet>();
		
		NSMutableArray links = (NSMutableArray)getLinks(0);
		Hints_BitSet bitSet = new Hints_BitSet(10); // autorelease];
		
		for(int index = 0; index < links.size(); index++)
		{
			Hints_Link link = (Hints_Link)links.get(index);
			
			bitSet.set(true, link.srcValue);
		}

		//[result setObject:bitSet forKey:xyCell];
		result.put(xyCell, bitSet);
		
		return result;
	}

	public  Map getGrayPotentials(int viewNum)
	{
		//NSMutableDictionary* result = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> result = new HashMap<Hints_Cell, Hints_BitSet>();
		
		NSMutableArray links = (NSMutableArray)getLinks(0);
		
		for(int index = 0; index < links.size(); index++)
		{
			Hints_Link link = (Hints_Link)links.get(index);
			
			//[result setObject:[Hints_SingletonBitSet create:link.dstValue] forKey:link.dstCell];
			result.put(link.dstCell, Hints_SingletonBitSet.create(link.dstValue));
		}
		
		return result;
	}

	public int getRemainingValue(Hints_Cell c )
	{
		Hints_BitSet result = new Hints_BitSet(c.getPotentialValues());
		result.set(false, value);
		
		return result.nextSetBit(0);
	}

	public ArrayList getLinks(int viewNum)
	{
		NSMutableArray result = new NSMutableArray();
		
		int xValue = getRemainingValue(xzCell);
		
		Hints_Link xLink = new Hints_Link(xyCell, xValue, xzCell, xValue); 
		result.add(xLink);
		
		int yValue = getRemainingValue(yzCell);
		Hints_Link yLink = new Hints_Link(xyCell, yValue, yzCell, yValue);
		
		result.add(yLink);
		
		return result;
	}

	public ArrayList getRegions()
	{
		return new NSMutableArray(); // array];
	}

	public ArrayList getSelectedCells()
	{
		//return [NSMutableArray arrayWithObjects:xyCell, xzCell, yzCell, nil];
		NSMutableArray tmpArray = new NSMutableArray();
		tmpArray.add(xyCell);
		tmpArray.add(xzCell);
		tmpArray.add(yzCell);
		return tmpArray;
	}

	public int getViewCount()
	{
		return 1;
	}

	public boolean equals(Hints_Hint hint)
	{
		if (!(hint instanceof Hints_XYWingHint ) )
			return false;
		
		Hints_XYWingHint otherHint = (Hints_XYWingHint)hint;
		
		if(this.isXYZ != otherHint.isXYZ)
			return false;
		
		if(this.xyCell != otherHint.xyCell || this.value != otherHint.value)
			return false;
		
		if (this.xzCell != otherHint.xzCell && this.xzCell != otherHint.yzCell)
			return false;
		
		if (this.yzCell != otherHint.xzCell && this.yzCell != otherHint.yzCell)
			return false;
		
		return true;
	}

	public int hashCode()
	{
		return ( (xyCell.hashCode()) ^ yzCell.hashCode() ^ xzCell.hashCode() );
	}

}
