package com.gold.sudoku;

public class Hints_Column extends Hints_Region {
	/**
	 * A column a sudoku grid
	 */
	public	int columnNum;
	
	public Hints_Column(Hints_Grid _grid, int _columnNum )
	{
		//self = [super initWithGrid:_grid];
		super(_grid);

		columnNum = _columnNum;
	}

	public int getType()
	{
		return _RegionType.kRegionColumn;
	}

	public int getColumnNum()
	{
		return columnNum;
	}

	public Hints_Cell getCell(int index)
	{
	return grid.getCellAtX(columnNum,index);
	}

	public int indexOf(Hints_Cell cell)
	{
	return cell.getY();
	}

	public boolean crosses(Hints_Region other)
	{
	if(other instanceof Hints_Block) 
	{
		Hints_Block square = (Hints_Block)other;
		return columnNum / 3 == square.hNum;
	} 
	else if(other instanceof Hints_Row ) 
	{
		return true;
	} 
	else if(other instanceof Hints_Column ) 
	{
		Hints_Column column = (Hints_Column)other;
		return this.columnNum == column.columnNum;
	} 

	return false;
	}
}
