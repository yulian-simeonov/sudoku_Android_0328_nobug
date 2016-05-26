package com.gold.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Hints_DirectHiddenSetHint extends Hints_IndirectHint
{

	ArrayList  cells;
	Hints_IntArray  values;
	Hints_Cell cell; // Hidden single cell
	int value; // Hidden single value
	Map orangePotentials;
	Map redPotentials;
	Hints_Region region;

	public  Hints_DirectHiddenSetHint(ArrayList _cells, Hints_IntArray _values ,
			Map _orangePotentials, Map _removePotentials,
			Hints_Region _region, Hints_Cell _cell, int _value)
	{
		//self = [super initWithRemovable:[NSMutableDictionary dictionary]];
		super(new HashMap<Hints_Cell, Hints_BitSet>());
		
		cells = _cells;
		values = _values;
		cell = _cell;
		value = _value;
		orangePotentials = _orangePotentials;
		redPotentials = _removePotentials;
		region = _region;
		
		//return self;
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

	public int getViewCount()
	{
		return 1;
	}

	public ArrayList getSelectedCells()
	{
		//return [NSMutableArray arrayWithObject:cell];
		NSMutableArray tmpArray = new NSMutableArray();
		tmpArray.add(cell);
		return tmpArray;
	}

	public Map getGreenPotentials(int viewNum)
	{
		Map result = null;
		//result = [NSMutableDictionary dictionaryWithDictionary:orangePotentials];
		result = new HashMap<Hints_Cell, Hints_BitSet>();
		
		//[result setObject:[Hints_SingletonBitSet create:value] forKey:cell];
		result.put(cell, Hints_SingletonBitSet.create(value));

		return result;
	}

	public Map getRedPotentials(int viewNum)
	{
		Map result;
		//result  = [NSMutableDictionary dictionaryWithDictionary:orangePotentials];
		result = new HashMap<Hints_Cell, Hints_BitSet>();
		result.putAll(orangePotentials);
		
		//NSArray* keyArray = [redPotentials allKeys];
		Set keyArray = redPotentials.entrySet();
		//for(int index = 0; index < [keyArray count]; index++) {
		//	Hints_Cell* _cell = [keyArray objectAtIndex:index];
		//	Hints_BitSet* _values = [redPotentials objectForKey:_cell];
		//	if([result objectForKey:_cell] != nil) 
		//	{
		//		Hints_BitSet* nvalues = [[[Hints_BitSet alloc] initWithBitSet:[result objectForKey:_cell]] autorelease];
		//		[nvalues or:_values];
		//		
		//		[result setObject:nvalues forKey:_cell];
		//	} 
		//	else
		//		[result setObject:_values forKey:_cell];
		//}

		Iterator it = keyArray.iterator();
		while(it.hasNext()) {
		    // key=value Map.Entry
		    Map.Entry m=(Map.Entry)it.next();
		    Hints_Cell _cell = (Hints_Cell)m.getKey(); // getKey
		    Hints_BitSet _values = (Hints_BitSet)m.getValue(); // getValue
		
			if(result.get(_cell) != null) 
			{
				Hints_BitSet nvalues = new Hints_BitSet((Hints_BitSet)(result.get(_cell)));
				nvalues.or(_values);
				
				//[result setObject:nvalues forKey:_cell];
				result.put(_cell, nvalues);
			} 
			else
				//[result setObject:_values forKey:_cell];
				result.put(_cell, _values);
		}
		
		return result;
	}

	public Map getOrangePotentials(int viewNum)
	{
		return orangePotentials;
	}

	public ArrayList getLinks(int viewNum)
	{
		return null;
	}

	public ArrayList getRegions()
	{
		//return [NSMutableArray arrayWithObject:region];
		NSMutableArray tmpArray = new NSMutableArray();
		tmpArray.add(region);
		return tmpArray;
	}
}
