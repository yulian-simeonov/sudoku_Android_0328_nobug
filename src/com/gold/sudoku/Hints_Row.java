package com.gold.sudoku;

public class Hints_Row extends Hints_Region {
	/**
	 * A row of a sudoku grid.
	 */
	
	public	int rowNum;
	
	public Hints_Row(Hints_Grid _grid, int  _rowNum )
	{
//		self = [super initWithGrid:_grid];
		super(_grid);

		rowNum = _rowNum;
	}

	public int  getType()
	{
		return _RegionType.kRegionRow;
	}

	public int getRowNum()
	{
		return rowNum;
	}

	public Hints_Cell getCell(int index)
	{
		return grid.getCellAtX(index ,rowNum ) ;  //[grid getCellAtX:index y:rowNum];
	}

	public int indexOf(Hints_Cell cell )
	{
		return cell.getX();
	}

	public boolean crosses(Hints_Region other )
	{
		if((other instanceof Hints_Block) ) 
		{
			Hints_Block square = (Hints_Block)other;
			return rowNum / 3 == square.vNum;
		} 
		else if((other instanceof Hints_Column ) ) 
		{
			return true;
		} 
		else if( (other instanceof Hints_Row) ) 
		{
			Hints_Row row = (Hints_Row)other;
			return this.rowNum == row.rowNum;
		} 

		return true;
	}
}
