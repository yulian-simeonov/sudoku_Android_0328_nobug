package com.gold.sudoku;

public class Hints_NakedSingle extends Hints_HintProducer {
	
	/**
	 * Check if a cell has only one potential value, and accumulate
	 * corresponding hints
	 */
	public void getHints(Hints_Grid grid)
	{
		//Hints_Region** regions = [grid getRegions:kRegionRow];
		Hints_Region[] regions = grid.getRegions(_RegionType.kRegionRow);
		
		// Iterate on parts
		for(int regionIndex = 0; regionIndex < 9; regionIndex++)
		{
			Hints_Region region = regions[regionIndex];
			
			// Iterate on cells
			for(int index = 0; index < 9; index++) 
			{
				Hints_Cell cell = region.getCell(index);
				
				// Get the cell's potential values
				Hints_BitSet potentialValues = cell.getPotentialValues();
				
				if(potentialValues.cardinality() == 1) 
				{
					// One potential value -> solution found
					int uniqueValue = potentialValues.nextSetBit(0);
					//addHint([[Hints_NakedSingleHint alloc] initWith:nil cell:cell value:uniqueValue] autorelease]];
					addHint(new Hints_NakedSingleHint(null, cell, uniqueValue));
				}
			}
		}
	}

}
