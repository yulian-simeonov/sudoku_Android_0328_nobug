package com.gold.sudoku;

import java.util.ArrayList;
import java.util.Vector;

public class Hints_Cell {
	
	Hints_Grid grid;
    int x;
    int y;
	int value;
    Hints_BitSet potentialValues;
	boolean persist;
		
	/**
	* Create a new cell
	* @param grid the grid this cell is part of
	* @param x the x coordinate of this cell (0=leftmost, 8=rightmost)
	* @param y the y coordinate of this cell (0=topmost, 8=bottommost)
	*/
	public Hints_Cell(Hints_Grid _grid, int inx, int iny)
	{
		potentialValues = new Hints_BitSet(9); //[[Hints_BitSet alloc] initWithSize:9];

		this.grid = _grid;
		x = inx;
		y = iny;
	}

	
	/**
	* Get the x coordinate of this cell.
	* 0 = leftmost, 8 = rightmost
	* @return the x coordinate of this cell
	*/
	public int getX()
	{
		return x;
	}

	/**
	* Get the y coordinate of this cell.
	* 0 = topmost, 8 = bottommost
	* @return the y coordinate of this cell
	*/
	public  int getY()
	{
		return y;
	}

	/**
	* Get the value of this cell. Returns <tt>0</tt>
	* if this cell is still empty.
	* @return the value of this cell.
	*/
	public int getValue()
	{
		return value;
	}

	/**
	* Get whether this cell is empty
	* @return whether this cell is empty
	*/
	public boolean isEmpty()
	{
		return (value == 0);
	}

	/**
	* Set the value of this cell.
	* @param value the value of this cell. Use <tt>0</tt> to
	* clear it.
	*/
	public void setValue(int _value)
	{
		value = _value;
	}

	/**
	* Set the value of this cell, and remove that value
	* from the potential values of all controlled cells.
	* <p>
	* This cell must be empty before this call, and the
	* given value must be non-zero.
	* @param value the value to set this cell to.
	* @see #getHouseCells()
	*/
	public void setValueAndCancel(int _value)
	{
		value = _value;
		potentialValues.clear();
	
		for(int regionType = 0; regionType < _RegionType.kRegionLast; regionType++) 
		{
			Hints_Region region = grid.getRegionType(regionType, x, y); //getRegionType:regionType atX:x atY:y];
			
			for(int i = 0; i < 9; i++) 
			{
				Hints_Cell other = region.getCell(i);
				other.removePotentialValue(_value);
			}
		}
	}

	/**
	* Get the potential values for this cell.
	* <p>
	* The result is returned as a bitset. Each of the
	* bit number 1 to 9 is set if the corresponding
	* value is a potential value for this cell. Bit number
	* <tt>0</tt> is not used and ignored.
	* @return the potential values for this cell
	*/
	public Hints_BitSet getPotentialValues()
	{
		return potentialValues;
	}

	/**
	* Test whether the given value is a potential
	* value for this cell.
	* @param value the potential value to test, between 1 and 9, inclusive
	* @return whether the given value is a potential value for this cell
	*/
	public  boolean hasPotentialValue(int _value) 
	{
		return potentialValues.get(_value);
	}

	/**
	* Add the given value as a potential value for this cell
	* @param value the value to add, between 1 and 9, inclusive
	*/
	public  void addPotentialValue(int _value) 
	{
		potentialValues.set(true, _value); 
	}

	/**
	* Remove the given value from the potential values of this cell.
	* @param value the value to remove, between 1 and 9, inclusive
	*/
	public void removePotentialValue(int _value)
	{
		potentialValues.set(false, _value) ;
	}

	public void removePotentialValues(Hints_BitSet valuesToRemove)
	{
		potentialValues.andNot(valuesToRemove);
	}

	public void clearPotentialValues()
	{
		potentialValues.clear();
	}

	/**
	* Get the cells that form the "house" of this cell. The
	* "house" cells are all the cells that are in the
	* same block, row or column.
	* <p>
	* The iteration order is guaranted to be the same on each
	* invocation of this method for the same cell. (this is
	* necessary to ensure that hints of the same difficulty
	* are always returned in the same order).
	* @return the cells that are controlled by this cell
	*/
	public NSMutableArray getHouseCells()
	{
		NSMutableArray result = new NSMutableArray();// [NSMutableArray array];

		for(int regionType = 0; regionType < _RegionType.kRegionLast; regionType++) 
		{
			Hints_Region region = grid.getRegionType(regionType, x, y); 
			
			// Add all cell of that region
			for(int i = 0; i < 9; i++)
				result.addUniqueObject(region.getCell(i));
		}
	
		//Remove this cell
		result.remove(this);

		return result;
	}

	/**
	* Copy this cell to another one. The value and potential values
	* are copied, but the grid reference and the coordinates are not.
	* @param other the cell to copy this cell to
	*/
	public void copyTo(Hints_Cell other) 
	{
		//assert((x == other.x) && (y == other.y));

		other.value = value;
		other.potentialValues = new  Hints_BitSet( potentialValues ) ;     //[[[Hints_BitSet alloc] initWithBitSet:potentialValues] autorelease];
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//

//	- (id)copyWithZone:(NSZone *)zone 
//	{
//		return [self retain];
//	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//

	public boolean equals(Hints_Cell cell)
	{
	return (this == cell);
	}

	public int hashCode()
	{
		//	return (int)this;
		//hb mark
		
		return 1;
	}
}
