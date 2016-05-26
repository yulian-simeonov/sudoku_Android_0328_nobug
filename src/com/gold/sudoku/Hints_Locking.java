package com.gold.sudoku;

import java.util.HashMap;
import java.util.Map;

public class Hints_Locking extends Hints_HintProducer {

	boolean isDirectMode;

	public Hints_Locking(boolean _isDirectMode)
	{
		//self = [super init];
		super();
		isDirectMode = _isDirectMode;
		//return self;
	}

	public void getHints(Hints_Grid grid)
	{
		this.getHints(grid, _RegionType.kRegionBlock, _RegionType.kRegionColumn);
		this.getHints(grid, _RegionType.kRegionBlock, _RegionType.kRegionRow);
		this.getHints(grid, _RegionType.kRegionColumn, _RegionType.kRegionBlock);
		this.getHints(grid, _RegionType.kRegionRow, _RegionType.kRegionBlock);
		
	}
	
	/**
	 * Given two part types, iterate on pairs of parts of both types that
	 * are crossing. For each such pair (p1, p2), check if all the potential
	 * positions of a value in p1 are also in p2.
	 * <p>
	 * Note: at least one of the two part type must be a
	 * {@link Grid.Block 3x3 square}.
	 * @param regionType1 the first part type
	 * @param regionType2 the second part type
	 */
	public  void getHints(Hints_Grid grid, int regionType1, int regionType2)
	{
		// Iterate on pairs of parts
		for(int i1 = 0; i1 < 9; i1++) 
		{
			for(int i2 = 0; i2 < 9; i2++) 
			{
				Hints_Region region1 = (grid.getRegions(regionType1))[i1];
				Hints_Region region2 = (grid.getRegions(regionType2))[i2];
				
				if(region1.crosses(region2)) 
				{
					NSMutableArray region2Cells = region2.getCellSet();
					
					// Iterate on values
					for (int value = 1; value <= 9; value++) 
					{
						boolean isInCommonSet = true;
						// Get the potential positions of the value in part1
						Hints_BitSet potentialPositions = region1.getPotentialPositions(value);
						// Note: if cardinality == 1, this is Hidden Single in part1
						if (potentialPositions.cardinality() > 1) 
						{
							// Test if all potential positions are also in part2
							for (int i = 0; i < 9; i++) 
							{
								if(potentialPositions.get(i)) 
								{
									Hints_Cell cell = region1.getCell(i);
									if(!region2Cells.contains(cell))
										isInCommonSet = false;
								}
							}
							
							if(isInCommonSet) 
							{
								if (isDirectMode) 
								{
									lookForFollowingHiddenSingles(grid, regionType1, i1, region1, region2, value);
								} 
								else 
								{
									// Potential solution found
									Hints_IndirectHint hint = createLockingHint(region1, region2, null, value);
									if(hint.isWorth())
										addHint(hint);
								}
							}
						}
					} // for each value
				} // if parts are crossing
			}
		}
	}

	public void lookForFollowingHiddenSingles(Hints_Grid grid, int regionType1, int i1 ,Hints_Region region1 , Hints_Region region2, int value)
	{
		//Look if the pointing / claiming induce a hidden single
		for(int i3 = 0; i3 < 9; i3++) 
		{
			if(i3 != i1) 
			{
				Hints_Region region3 = (grid.getRegions(regionType1))[i3];
				
				if(region3.crosses(region2)) 
				{
					// Region <> region1 but crosses region2
					NSMutableArray region2Cells = region2.getCellSet();
					Hints_BitSet potentialPositions3 = region3.getPotentialPositions(value);
					
					if(potentialPositions3.cardinality() > 1) 
					{
						int nbRemainInRegion3 = 0;
						Hints_Cell hcell = null;
						for (int i = 0; i < 9; i++) 
						{
							if(potentialPositions3.get(i)) 
							{
								Hints_Cell cell = region3.getCell(i);
								if(!region2Cells.contains(cell)) 
								{ // This position is not removed
									nbRemainInRegion3++;
									hcell = cell;
								}
							}
						}
						
						if(nbRemainInRegion3 == 1) 
						{
							Hints_IndirectHint hint = createLockingHint(region1, region2, hcell, value);
							if(hint.isWorth())
								addHint(hint);
						}
					}
				}
			}
		}
	}

	public Hints_IndirectHint createLockingHint(Hints_Region p1, Hints_Region p2 ,Hints_Cell hcell, int value )
	{
		// Build highlighted potentials
		//NSMutableDictionary* cellPotentials = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellPotentials = new HashMap<Hints_Cell, Hints_BitSet>();
		
		for (int i = 0; i < 9; i++) 
		{
			Hints_Cell cell = p1.getCell(i);
			
			if(cell.hasPotentialValue(value))
				//[cellPotentials setObject:[Hints_SingletonBitSet create:value] forKey:cell];
				cellPotentials.put(cell, Hints_SingletonBitSet.create(value));
		}
		
		// Build removable potentials
		//NSMutableDictionary* cellRemovablePotentials = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellRemovablePotentials = new HashMap<Hints_Cell, Hints_BitSet>();
		NSMutableArray highlightedCells = new NSMutableArray();
		NSMutableArray p1Cells = p1.getCellSet();
		
		for(int i = 0; i < 9; i++) 
		{
			Hints_Cell cell = p2.getCell(i);
			
			if(!p1Cells.contains(cell)) 
			{
				if(cell.hasPotentialValue(value))
					//[cellRemovablePotentials setObject:[Hints_SingletonBitSet create:value] forKey:cell];
					cellRemovablePotentials.put(cell, Hints_SingletonBitSet.create(value));
	
			} 
			else if (cell.hasPotentialValue(value))
				highlightedCells.add(cell);
		}
		
		// Build list of cells
		//NSMutableArray cells = [NSMutableArray arrayWithArray:highlightedCells];
		NSMutableArray cells = (NSMutableArray)highlightedCells.clone();
		Hints_IndirectHint hint;
		
		// Build hint
		if(isDirectMode) {
			//hint = [[[Hints_DirectLockingHint alloc] initWith:cells cell:hcell value:value highlightPotentials:cellPotentials removePotentials:cellRemovablePotentials regions:[NSMutableArray arrayWithObjects:p1, p2, nil]] autorelease];
			NSMutableArray tmpArray = new NSMutableArray();
			tmpArray.add(p1);
			tmpArray.add(p2);
			hint = new Hints_DirectLockingHint(cells, hcell, value, cellPotentials, cellRemovablePotentials, tmpArray);
		} else {
			NSMutableArray tmpArray = new NSMutableArray();
			tmpArray.add(p1);
			tmpArray.add(p2);
			//hint = [[[Hints_LockingHint alloc] initWith:cells value:value highlightPotentials:cellPotentials removePotentials:cellRemovablePotentials regions:[NSMutableArray arrayWithObjects:p1, p2, nil]] autorelease];
			hint = new Hints_LockingHint(cells, value, cellPotentials, cellRemovablePotentials, tmpArray);
		}
		
		return hint;
	}
///*
// @Override
// public String toString() {
// if (isDirectMode)
// return "Direct Intersections";
// else
// return "Intersections";
// }
// */

}
