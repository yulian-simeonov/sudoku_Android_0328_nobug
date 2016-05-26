package com.gold.sudoku;

import java.util.ArrayList;
import java.util.Map;

public class Hints_HiddenSetHint extends Hints_IndirectHint {
    ArrayList cells;
	Hints_IntArray values;
	Map highlightPotentials;
    Hints_Region region;

	public Hints_HiddenSetHint(ArrayList _cells, Hints_IntArray _values, Map _highlightPotentials, Map _removePotentials, Hints_Region _region)
	{
		//self = [super initWithRemovable:_removePotentials];
		super(_removePotentials);
		
		cells = _cells;
		values = _values;
		highlightPotentials = _highlightPotentials;
		region = _region;
		
		//return self;
	}



	public int getViewCount()
	{
		return 1;
	}

	public ArrayList getSelectedCells()
	{
		return cells;
	}

	public  Map getGreenPotentials(int viewNum)
	{
		return highlightPotentials;
	}

	public  Map getRedPotentials(int viewNum )
	{
		return super.getRemovablePotentials();
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
