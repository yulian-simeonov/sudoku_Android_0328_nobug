package com.gold.sudoku;

public class Hints_Grid {
	
    Hints_Cell [][]cells;//[9][9];
		
    Hints_Row []rows;//[9];
    Hints_Column []columns;//[9];
    Hints_Block []blocks;//[9];
	
	public Hints_Grid()
	{
		cells = new Hints_Cell[9][9];
		rows = new Hints_Row[9];
		columns = new Hints_Column[9];
		blocks = new Hints_Block[9];
		
		for(int y = 0; y < 9; y++) 
		{
			for(int x = 0; x < 9; x++) 
			{
				cells[y][x] = new Hints_Cell( this, x, y );  // [[Hints_Cell alloc] initWithGrid:self x:x y:y];
			}
		}
	
		// Build subparts views
		for(int i = 0; i < 9; i++) 
		{
			rows[i] = new Hints_Row(this ,i);
			columns[i] = new Hints_Column(this ,i);
			blocks[i] = new Hints_Block(this, (i/3) ,(i%3));
		}
	}

	
	public void reset()
	{
		for(int y = 0; y < 9; y++) 	
		{
			for(int x = 0; x < 9; x++) 	
			{
				cells[y][x].value = 0;
			
				cells[y][x].potentialValues.clear();
			
				for(int index = 1; index <= 9; index++)
					cells[y][x].potentialValues.set(true, index);
			}
		}
	}

	/**
	* Get the cell at the given coordinates
	* @param x the x coordinate (0=leftmost, 8=rightmost)
	* @param y the y coordinate (0=topmost, 8=bottommost)
	* @return the cell at the given coordinates
	*/
	public  Hints_Cell getCellAtX(int x, int y)
	{
		return cells[y][x];
	}

	/**
	* Get the 9 regions of the given type
	* @param regionType the type of the regions to return. Must be one of
	* {@link Grid.Block}, {@link Grid.Row} or {@link Grid.Column}.
	* @return the 9 regions of the given type
	*/
	//public Hints_Region[][] getRegions(int regionType )
	public Hints_Region[] getRegions(int regionType )
	{
		Hints_Region [] regions = null;  // = nil;

		if(regionType == _RegionType.kRegionRow)
		{
			regions = rows;
		}
		else if(regionType == _RegionType.kRegionColumn)
		{
			regions = columns;
		}
		else if(regionType == _RegionType.kRegionBlock)
		{
			regions = blocks;
		}
		else
		{
			
		}
	
		return regions;
	}

	/**
	* Get the row at the given index.
	* Rows are numbered from top to bottom.
	* @param num the index of the row to get, between 0 and 8, inclusive
	* @return the row at the given index
	*/
	public Hints_Row getRow(int num)
	{
		return rows[num];
	}

	/**
	* Get the column at the given index.
	* Columns are numbered from left to right.
	* @param num the index of the column to get, between 0 and 8, inclusive
	* @return the column at the given index
	*/
	public Hints_Column getColumn( int num )
	{
		return columns[num];
	}

	/**
	* Get the block at the given index.
	* Blocks are numbered from left to right, top to bottom.
	* @param num the index of the block to get, between 0 and 8, inclusive
	* @return the block at the given index
	*/
	public Hints_Block getBlock(int num)
	{
		return blocks[num];
	}

	/**
	* Get the block at the given location
	* @param vPos the vertical position, between 0 to 2, inclusive
	* @param hPos the horizontal position, between 0 to 2, inclusive
	* @return the block at the given location
	*/
	public Hints_Block getBlock(int vPos, int hPos)
	{
		return blocks[vPos * 3 + hPos];
	}

	//Cell values

	/**
	* Set the value of a cell
	* @param x the horizontal coordinate of the cell
	* @param y the vertical coordinate of the cell
	* @param value the value to set the cell to. Use 0 to clear the cell.
	*/
	public void setCellValueX(int x,  int y, int value)
	{
		cells[y][x].setValue(value) ;
	}

	/**
	* Get the value of a cell
	* @param x the horizontal coordinate of the cell
	* @param y the vertical coordinate of the cell
	* @return the value of the cell, or 0 if the cell is empty
	*/
	public int getCellValueX(int x , int y)
	{
		return cells[y][x].getValue();
	}

	/**
	* Get the row at the given location
	* @param x the horizontal coordinate
	* @param y the vertical coordinate
	* @return the row at the given coordinates
	*/
	public Hints_Row getRowAtX(int x, int y)
	{
		return rows[y];
	}

	/**
	* Get the column at the given location
	* @param x the horizontal coordinate
	* @param y the vertical coordinate
	* @return the column at the given location
	*/
	public Hints_Column getColumnAtX(int x, int y)
	{
		return columns[x];
	}

	/**
	* Get the 3x3 block at the given location
	* @param x the horizontal coordinate
	* @param y the vertical coordinate
	* @return the block at the given coordinates (the coordinates
	* are coordinates of a cell)
	*/
	public Hints_Block getBlockAtX(int x, int y )
	{
		return blocks[(y / 3) * 3 + (x / 3)];
	}

	public Hints_Region getRegionType(int regionType, int x, int y)
	{
		Hints_Region result = null;

		if(regionType ==_RegionType.kRegionRow)
			result = this.getRowAtX(x, y);
		else if (regionType == _RegionType.kRegionColumn)
			result = this.getColumnAtX(x, y);
		else
			result = this.getBlockAtX(x, y);

		return result;
	}

	public Hints_Region getRegionType(int regionType, Hints_Cell cell)
	{
		return  this.getRegionType(regionType, cell.getX(), cell.getY());
	}

	/**
	* Get the first cell that cancels the given cell.
	* <p>
	* More precisely, get the first cell that:
	* <ul>
	* <li>is in the same row, column or block of the given cell
	* <li>contains the given value
	* </ul>
	* The order used for the "first" is not defined, but is guaranted to be
	* consistent accross multiple invocations.
	* @param target the cell
	* @param value the value
	* @return the first cell that share a region with the given cell, and has
	* the given value
	*/
	public  Hints_Cell getFirstCancellerOf(Hints_Cell target, int value) 
	{
	for(int regionType = 0; regionType < _RegionType.kRegionLast; regionType++) 
	{
		Hints_Region region = this.getRegionType(regionType ,target);
		
		for(int i = 0; i < 9; i++) 
		{
			Hints_Cell cell = region.getCell(i);
			
			if(!(cell == target) && (cell.getValue() == value))
				return cell;
		}
	}

	return null;
	}

	/**
	* Copy the content of this grid to another grid.
	* The values of the cells and their potential values
	* are copied.
	* @param other the grid to copy this grid to
	*/
	public void copyTo(Hints_Grid other)
	{
		for (int y = 0; y < 9; y++) 
		{
			for (int x = 0; x < 9; x++) 
			{
				cells[y][x].copyTo(other.getCellAtX(x, y));
			}
		}
	}

	/**
	* Get the number of occurances of a given value in this grid
	* @param value the value
	* @return the number of occurances of a given value in this grid
	*/
	public int getCountOccurancesOfValue(int value)
	{
	int result = 0;

	for(int y = 0; y < 9; y++) 
	{
		for (int x = 0; x < 9; x++) 
		{
			if( cells[y][x].getValue() == value)
				result++;
		}
	}

	return result;
	}

}
