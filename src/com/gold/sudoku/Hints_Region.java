package com.gold.sudoku;

public class Hints_Region {
	
	public Hints_Grid grid;


	public Hints_Region( Hints_Grid _grid )
	{
		grid = _grid;
	}


	public int getType()
	{
		return 0;
	}

	public Hints_Cell getCell(int index)
	{
		return null;
	}

	/**
	* Get the index of the given cell within this region.
	* <p>
	* The returned value is consistent with {@link #getCell(int)}.
	* @param cell the cell whose index to get
	* @return the index of the cell, or -1 if the cell does not belong to
	* this region.
	*/
	public int indexOf(Hints_Cell cell)
	{
		/*
		 * This code is not really used. The method is always overriden
		 */
		for (int i = 0; i < 9; i++) 
		{
			if( this.getCell(i) == cell)
				return i;
		}
	
		return -1;
	}

	/**
	* Test whether this region contains the given value, that is,
	* is a cell of this region is filled with the given value.
	* @param value the value to check for
	* @return whether this region contains the given value
	*/
	public boolean contains(int value)
	{
		for(int i = 0; i < 9; i++) 
		{
			if( this.getCell(i).getValue() == value)
				return true;
		}
	
		return false;
	}

	/**
	* Get the potential positions of the given value within this region.
	* The bits of the returned bitset correspond to indexes of cells, as
	* in {@link #getCell(int)}. Only the indexes of cells that have the given
	* value as a potential value are included in the bitset (see
	* {@link Cell#getPotentialValues()}).
	* @param value the value whose potential positions to get
	* @return the potential positions of the given value within this region
	* @see Cell#getPotentialValues()
	*/
	public Hints_BitSet getPotentialPositions(int value )
	{
		Hints_BitSet result = new Hints_BitSet(9) ;//[[[Hints_BitSet alloc] initWithSize:9] autorelease];

		for(int index = 0; index < 9; index++) 
		{
			Hints_Cell cell = this.getCell(index);
			result.set(cell.hasPotentialValue(value), index);
		}
	
		return result;
	}

	public  Hints_BitSet copyPotentialPositions(int value)
	{
		return this.getPotentialPositions(value); // No need to clone, this is alreay hand-made
	}

	/**
	* Get the cells of this region. The iteration order of the result
	* matches the order of the cells returned by {@link #getCell(int)}.
	* @return the cells of this region.
	*/
	public NSMutableArray getCellSet()
	{
		NSMutableArray result =  new NSMutableArray();

		for (int i = 0; i < 9; i++)
			result.addUniqueObject(this.getCell(i));

		return result;
	}

	/**
	* Return the cells that are common to this region and the
	* given region
	* @param other the other region
	* @return the cells belonging to this region and to the other region
	*/
	public NSMutableArray commonCells(Hints_Region other )
	{
		NSMutableArray result = this.getCellSet();

		result.retainAll(other.getCellSet());

		return result;
	}

	/**
	* Test whether thsi region crosses an other region.
	* <p>
	* A region crosses another region if they have at least one
	* common cell. In particular, any rows cross any columns.
	* @param other the other region
	* @return whether this region crosses the other region.
	*/
	public boolean crosses(Hints_Region other )
	{
		return !this.commonCells(other).isEmpty();
	}

	/**
	* Get the number of cells of this region that are still empty.
	* @return the number of cells of this region that are still empty
	*/
	public int getEmptyCellCount()
	{
		int result = 0;
	
		for(int i = 0; i < 9; i++)
		{
			if(this.getCell(i).isEmpty())
				result++;
		}
	
			return result;
	}

}
