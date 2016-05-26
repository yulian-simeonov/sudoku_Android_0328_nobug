package com.gold.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hints_NakedSet  extends Hints_HintProducer {

	int degree;

	public Hints_NakedSet(int _degree)
	{
		super(); //self = [super init];
		
		////		assert((_degree > 1) && (_degree <= 4));
		degree = _degree;
		
		//return self;
	}

	public void getHints(Hints_Grid grid)
	{
		this.getHints(grid, _RegionType.kRegionBlock);
		this.getHints(grid, _RegionType.kRegionColumn);
		this.getHints(grid, _RegionType.kRegionRow);
	}

	/**
	 * For each regions of the given type, check if a n-tuple of values have
	 * a common n-tuple of potential positions, and no other potential position.
	 */
	public void getHints(Hints_Grid grid, int regionType)
	{
		//Hints_Region** regions = [grid getRegions:regionType];
		Hints_Region[] regions = grid.getRegions(regionType);
		
		// Iterate on parts
		for(int regionIndex = 0; regionIndex < 9; regionIndex++)
		{
			Hints_Region region = regions[regionIndex];
			
			if(region.getEmptyCellCount() >= (degree * 2)) 
			{
				Hints_Permutations perm = new Hints_Permutations(degree, 9);
				
				// Iterate on tuples of positions
				while(perm.hasNext())
				{
					Hints_IntArray indexes = perm.nextBitNums();
					//assert([indexes count] == degree);
					
					// Build the cell tuple
					NSMutableArray cells = new NSMutableArray();
					cells.setupSize(degree);
					
					for (int i = 0; i < cells.size(); i++)
						//[cells replaceObjectAtIndex:i withObject:[region getCell:[indexes get:i]]];
						cells.set(i, region.getCell(indexes.get(i)));
					
					// Build potential values for each position of the tuple
					NSMutableArray potentialValues = new NSMutableArray();
					potentialValues.setupSize(degree);
					
					for (int i = 0; i < degree; i++)
						//[potentialValues replaceObjectAtIndex:i withObject:[[cells objectAtIndex:i] getPotentialValues]];
						potentialValues.set(i, ((Hints_Cell)(cells.get(i))).getPotentialValues());
					
					// Look for a common tuple of potential values, with same degree
					Hints_BitSet commonPotentialValues = Hints_CommonTuples.searchCommonTuple(potentialValues, degree);
					if (commonPotentialValues != null) 
					{
						// Potential hint found
						Hints_IndirectHint hint = createValueUniquenessHint(region, cells, commonPotentialValues);
						if(hint.isWorth())
							addHint(hint);
					}
				}
			}
		}
	}

	public Hints_IndirectHint createValueUniquenessHint(Hints_Region region, ArrayList cells, Hints_BitSet commonPotentialValues )
	{
		// Build value list
		Hints_IntArray values = new Hints_IntArray(degree);
		int dstIndex = 0;
		
		for(int value = 1; value <= 9; value++) 
		{
			if(commonPotentialValues.get(value))
				values.set(value, dstIndex++);
		}
		
		// Build concerned cell potentials
		//NSMutableDictionary* cellPValues = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellPValues = new HashMap<Hints_Cell, Hints_BitSet>();
		
		for(int index = 0; index < cells.size(); index++)
		{
			Hints_Cell cell = (Hints_Cell)cells.get(index);
			
			Hints_BitSet potentials = new Hints_BitSet(10);
			potentials.or(commonPotentialValues);
			potentials.and(cell.getPotentialValues());
			
			//[cellPValues setObject:potentials forKey:cell];
			cellPValues.put(cell, potentials);
		}
		
		// Build removable potentials
		//NSMutableDictionary* cellRemovePValues = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellRemovePValues = new HashMap<Hints_Cell, Hints_BitSet>();
		
		for (int i = 0; i < 9; i++) 
		{
			Hints_Cell otherCell = region.getCell(i);
			
			if(!cells.contains(otherCell)) 
			{
				// Get removable potentials
				Hints_BitSet removablePotentials = new Hints_BitSet(10);
				for(int value = 1; value <= 9; value++) 
				{
					if(commonPotentialValues.get(value) && otherCell.hasPotentialValue(value))
						//[removablePotentials set:YES atIndex:value];
						removablePotentials.set(true, value);
				}
				
				if(!removablePotentials.isEmpty())
					//[cellRemovePValues setObject:removablePotentials forKey:otherCell];
					cellRemovePValues.put(otherCell, removablePotentials);
			}
		}

		//return [[[Hints_NakedSetHint alloc] initWith:cells values:values highlightPotentials:cellPValues removePotentials:cellRemovePValues region:region] autorelease];
		return new Hints_NakedSetHint(cells, values, cellPValues, cellRemovePValues, region);
	}
//
//	/*
//	 if (degree == 2)
//	 return "Naked Pairs";
//	 else if (degree == 3)
//	 return "Naked Triplets";
//	 else if (degree == 4)
//	 return "Naked Quads";
//	 return "Naked Sets " + degree;
//	 */	

}
