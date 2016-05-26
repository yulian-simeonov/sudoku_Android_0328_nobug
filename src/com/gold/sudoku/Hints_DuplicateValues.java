package com.gold.sudoku;

public class Hints_DuplicateValues extends Hints_HintProducer{

	boolean skipPersist;
	boolean addRegions;


	public Hints_DuplicateValues(boolean _skipPersist , boolean _addRegions)
	{
		//self = [super init];
		super();

		skipPersist = _skipPersist;
		addRegions = _addRegions;
	}

	public void getHints(Hints_Grid grid)
	{
		int index;
		int valuesCount;
		Hints_Cell cell;
		
		NSMutableArray cellArray = new NSMutableArray(); // array];
		NSMutableArray regionsArray = new NSMutableArray(); // array];
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				Hints_Cell curCell = grid.getCellAtX(x, y);
				if(curCell.value == 0)
					continue;
				
				for(int regionType = 0; regionType < _RegionType.kRegionLast; regionType++)
				{
					Hints_Region region = grid.getRegionType(regionType, curCell);
					NSMutableArray houseCells = region.getCellSet();
					valuesCount = 0;
		
					for(index = 0; index < houseCells.size(); index++)
					{
						cell = (Hints_Cell)houseCells.get(index);
						if(cell.value == curCell.value)
							valuesCount += 1;
					}
		
					if(valuesCount > 1)
					{
						if(skipPersist)
						{
							if(!curCell.persist)
								cellArray.addUniqueObject(curCell);
						}
						else
							cellArray.addUniqueObject(curCell);
							
						if(addRegions)
						{
							regionsArray.addUniqueObject(region); 
						}
					}
				}
			}
		}
		
		if(cellArray.size() != 0 || regionsArray.size() != 0)
		{
			Hints_WrongValuesHint hint = new Hints_WrongValuesHint(cellArray, regionsArray); // autorelease];
			addHint(hint);
		}
	}
}
