package com.gold.sudoku;

import java.util.HashMap;
import java.util.Map;

public class Hints_HiddenSet extends Hints_HintProducer{

	int degree;
    boolean isDirect;

	public Hints_HiddenSet(int _degree ,boolean _isDirect)
	{
		super();
		
		degree = _degree;
		isDirect = _isDirect;
	}
		
	public void getHints(Hints_Grid grid)
	{
		getHints(grid, _RegionType.kRegionBlock);
		getHints(grid, _RegionType.kRegionColumn);
		getHints(grid, _RegionType.kRegionRow);
	}
		
	/**
	 * For each parts of the given type, check if a n-tuple of cells have
	 * a common n-tuple of potential values, and no other potential value.
	 * @param regionType the type of the parts to check
	 * @param degree the degree of the tuples to search
	 */
	public void getHints(Hints_Grid grid, int regionType)
	{
		//Hints_Region[][] regions = grid.getRegions(regionType);
		Hints_Region[] regions = grid.getRegions(regionType);
		
		// Iterate on parts
		for(int regionIndex = 0; regionIndex < 9; regionIndex++)
		{
			Hints_Region region = regions[regionIndex];
			
			int nbEmptyCells = region.getEmptyCellCount();
			
			if(nbEmptyCells > degree * 2 || (isDirect && nbEmptyCells > degree)) 
			{
				Hints_Permutations perm = new Hints_Permutations(degree, 9); //] autorelease];
				// Iterate on tuple of values
				while(perm.hasNext()) 
				{
					Hints_IntArray values = perm.nextBitNums();
//					assert([values count] == degree);
					
					// Build the value tuple
					for (int i = 0; i < values.count(); i++)
					{
						int val = values.get(i);
						values.set(val+1, i); // 0..8 -> 1..9
					}
					
					// Build potential positions for each value of the tuple
					NSMutableArray potentialIndexes = new NSMutableArray(); // array];
					potentialIndexes.setupSize(degree);
					
					for (int i = 0; i < degree; i++)
						//potentialIndexes.replaceObjectAtIndex(i, region.getPotentialPositions(values.get(i)));
						potentialIndexes.set(i, region.getPotentialPositions(values.get(i)));
					
					// Look for a common tuple of potential positions, with same degree
					Hints_BitSet commonPotentialPositions = Hints_CommonTuples.searchCommonTuple(potentialIndexes, degree);
					if(commonPotentialPositions != null) 
					{
						// Hint found
						Hints_IndirectHint hint = createHiddenSetHint(region, values, commonPotentialPositions);
						if(hint != null && hint.isWorth())
							addHint(hint);
					}
				}
			}
		}
	}
		
	public Hints_IndirectHint createHiddenSetHint(Hints_Region region, Hints_IntArray values, Hints_BitSet commonPotentialPositions)
	{
	    // Create set of fixed values, and set of other values
		Hints_BitSet valueSet = new Hints_BitSet(10);
		for(int i = 0; i < values.count(); i++)
			valueSet.set(true, values.get(i));
		
		NSMutableArray cells = new NSMutableArray();
		cells.setupSize(degree);
		
		int dstIndex = 0;
		// Look for concerned potentials and removable potentials
		//NSMutableDictionary* cellPValues = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellPValues = new HashMap<Hints_Cell, Hints_BitSet>();
		//NSMutableDictionary* cellRemovePValues = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellRemovePValues = new HashMap<Hints_Cell, Hints_BitSet>();
		
		
		for(int index = 0; index < 9; index++) 
		{
			Hints_Cell cell = region.getCell(index);
			if(commonPotentialPositions.get(index)) 
			{
				cellPValues.put(cell, valueSet);
				// Look for the potential values we can remove
				Hints_BitSet removablePotentials = new Hints_BitSet(10);
				for(int value = 1; value <= 9; value++) 
				{
					if(!valueSet.get(value) && cell.hasPotentialValue(value))
						removablePotentials.set(true, value);
				}
				
				if(!removablePotentials.isEmpty())
					cellRemovePValues.put(cell, removablePotentials);
				
				//[cells replaceObjectAtIndex:dstIndex++ withObject:cell];
				cells.set(dstIndex++, cell);
			}
		}
		
		if(isDirect) 
		{
			// Look for Hidden Single
			for (int value = 1; value <= 9; value++) 
			{
				if(!valueSet.get(value)) 
				{
					Hints_BitSet positions = region.copyPotentialPositions(value);
					if(positions.cardinality() > 1) 
					{
						positions.andNot(commonPotentialPositions);
						
						if(positions.cardinality() == 1) 
						{
							// Hidden single found
							int index = positions.nextSetBit(0);
							Hints_Cell cell = region.getCell(index);

							return new Hints_DirectHiddenSetHint(cells, values, cellPValues, cellRemovePValues, region, cell, value);
									 
						}
					}
				}
			}
			// Nothing found
			return null;
		} 

		//return [[[Hints_HiddenSetHint alloc] initWith:cells values:values highlightPotentials:cellPValues removePotentials:cellRemovePValues region:region] autorelease];
		return new Hints_HiddenSetHint(cells, values, cellPValues, cellRemovePValues, region);
		
	}

}
