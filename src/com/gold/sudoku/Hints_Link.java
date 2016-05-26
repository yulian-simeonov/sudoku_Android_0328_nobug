package com.gold.sudoku;

public class Hints_Link {

	Hints_Cell srcCell;
	int srcValue;
	Hints_Cell dstCell;
	int dstValue;

	
	
	public Hints_Link(Hints_Cell _srcCell, int _srcValue, Hints_Cell _dstCell, int _dstValue )
	{
		super();
		
		srcCell = _srcCell;
		srcValue = _srcValue;
		dstCell = _dstCell;
		dstValue = _dstValue;
		
		//return self;
	}


	public Hints_Cell getSrcCell()
	{
		return srcCell;
	}

	public int getSrcValue()
	{
		return srcValue;
	}

	public  Hints_Cell getDstCell()
	{
		return dstCell;
	}

	public int getDstValue()
	{
		return dstValue;
	}

}
