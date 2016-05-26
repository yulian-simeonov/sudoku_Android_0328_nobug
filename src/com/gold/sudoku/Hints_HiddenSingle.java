package com.gold.sudoku;

public class Hints_HiddenSingle extends Hints_HintProducer {
	/**
	 * Implementation of the Hidden Single solving technique.
	 */
	
	public void getHints(Hints_Grid grid, boolean aloneOnly)
	{
		this.getHints(grid,  _RegionType.kRegionBlock, aloneOnly);
		this.getHints(grid, _RegionType.kRegionColumn, aloneOnly);
		this.getHints(grid, _RegionType.kRegionRow, aloneOnly);
	}

	/**
	 * For each parts of the given type, check if a value has only one
	 * possible potential position.
	 * @param regionType the type of the parts to check
	 */  
	public void getHints(Hints_Grid grid, int regionType, boolean aloneOnly)
	{
		//Hints_Region** regions = [grid getRegions:regionType];
		Hints_Region[] regions = grid.getRegions(regionType);
		
		// Iterate on parts
		for(int regionIndex = 0; regionIndex < 9; regionIndex++) 
		{
			Hints_Region region = regions[regionIndex];
			
			// Iterate on values
			for(int value = 1; value <= 9; value++) 
			{
				// Get value's potential position
				Hints_BitSet potentialIndexes = region.getPotentialPositions(value);
				
				if(potentialIndexes.cardinality() == 1) 
				{
					// One potential position -> solution found
					int uniqueIndex = potentialIndexes.nextSetBit(0);
					
					Hints_Cell cell = region.getCell(uniqueIndex);
					
					boolean isAlone = (region.getEmptyCellCount() == 1);
					
					if(isAlone == aloneOnly)
						//[self addHint:[[[Hints_HiddenSingleHint alloc] initWith:region cell:cell value:value isAlone:isAlone] autorelease]];
						addHint( new Hints_HiddenSingleHint(region, cell, value, isAlone));
				}
			}
		}
	}
}
