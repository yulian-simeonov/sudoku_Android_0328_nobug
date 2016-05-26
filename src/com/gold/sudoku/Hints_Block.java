package com.gold.sudoku;

public class Hints_Block extends Hints_Region{
	/**
	 * A 3x3 block of a sudoku grid.
	 */
	
	public 	int vNum;
	public	int hNum;
	
	Hints_Region region;
	

	public Hints_Block( Hints_Grid  grid, int  invNum, int  inhNum)
	{
		super( grid );
		vNum =  invNum;
		hNum = inhNum;
	}

	 public  int getType()
	 {
		 return _RegionType.kRegionBlock;
	 }

	 public int getVIndex()
	 {
	 return vNum;
	 }

	 public int getHIndex()
	 {
	 return hNum;
	 }

	 public Hints_Cell getCell(int index)
	 {
		 return  super.grid.getCellAtX((hNum * 3 + index % 3), (vNum * 3 + index / 3));
	 }

	 public int indexOf(Hints_Cell cell)
	 {
		 return (cell.getY() % 3) * 3 + (cell.getX() % 3);
	 }

	 public boolean crosses(Hints_Region other)
	 {
		 if(other instanceof Hints_Row) 
		 {
			 Hints_Row _row = (Hints_Row)other;
	 	
			 return _row.crosses(this);
		 } 
		 else if(other instanceof Hints_Column ) 
		 {
			 Hints_Column _col = (Hints_Column)other;
	 	
			 return _col.crosses(this);
		 } 
		 else if(other instanceof Hints_Block) 
		 {
			 Hints_Block square = (Hints_Block)other;
	 	
			 return (this.vNum == square.vNum) && (this.hNum == square.hNum);
		 } 

		 return false;
	 }
}
