package com.gold.sudoku;

import java.util.HashMap;
import java.util.Map;

public class Hints_Fisherman extends Hints_HintProducer {
	
	int degree;
		
	public Hints_Fisherman(int _degree)
	{
		//self = [super init];
		super();
		degree = _degree;
	}

	public  void getHints(Hints_Grid grid)
	{
		getHints(grid, _RegionType.kRegionColumn, _RegionType.kRegionRow);
		getHints(grid, _RegionType.kRegionRow, _RegionType.kRegionColumn);
	}

	//public void getHints(Hints_Grid grid , _RegionType partType1, _RegionType partType2)
	public void getHints(Hints_Grid grid, int partType1, int partType2)
	{
		//assert(!(partType1 == partType2));
		
		// Get occurance count for each value
		int occurances[] = new int[10]; //[10];
		
		for (int value = 1; value <= 9; value++)
			occurances[value] = grid.getCountOccurancesOfValue(value);
		
		//Hints_Region** parts = [grid getRegions:partType1];
		Hints_Region[] parts = grid.getRegions(partType1);
		// Iterate on lines tuples
		Hints_Permutations perm = new Hints_Permutations(degree, 9); 
		
		while(perm.hasNext()) 
		{
			//Hints_IntArray* indexes = [perm nextBitNums];
			Hints_IntArray indexes = perm.nextBitNums();
		
			//assert([indexes count] == degree);
			
			Hints_BitSet myIndexes = new Hints_BitSet(9); // autorelease];
			for(int i = 0; i < indexes.count(); i++)
				myIndexes.set(true, indexes.get(i));
			
			// Iterate on values
			for (int value = 1; value <= 9; value++) 
			{
				// Pattern is only possible if there are at least (degree * 2) missing occurances
				// of the value.
				if(occurances[value] + degree * 2 <= 9) 
				{
					
					// Check for exactly the same positions of the value in all lines
					//NSMutableArray* positions = [NSMutableArray array];
					NSMutableArray positions = new NSMutableArray(); 
					positions.setupSize(degree);
					
					for (int i = 0; i < degree; i++)
						//[positions replaceObjectAtIndex:i withObject:[parts[([indexes get:i])] getPotentialPositions:value]];
						positions.set(i, parts[(indexes.get(i))].getPotentialPositions(value));
					
					Hints_BitSet common = Hints_CommonTuples.searchCommonTuple(positions, degree);
					
					if(common != null) 
					{
						// Potential hint found
						//Hints_IndirectHint* hint = [self createFishHint:grid otherPartType:partType1 myPartType:partType2 otherIndexes:myIndexes myIndexes:common value:value];
						Hints_IndirectHint hint = createFishHint(grid, partType1, partType2, myIndexes, common, value);
						if(hint.isWorth())
							addHint(hint);
					}
				}
			}
		}
	}

	//public Hints_IndirectHint createFishHint(Hints_Grid grid, _RegionType otherPartType, _RegionType myPartType, Hints_BitSet otherIndexes, Hints_BitSet myIndexes, int value )
	public Hints_IndirectHint createFishHint(Hints_Grid grid, int otherPartType, int myPartType, Hints_BitSet otherIndexes, Hints_BitSet myIndexes, int value )
	{
		Hints_Region[] myParts = grid.getRegions(myPartType);
		Hints_Region[] otherParts = grid.getRegions(otherPartType);
		
		// Build parts
		NSMutableArray parts1 = new NSMutableArray(); // array];
		NSMutableArray parts2 = new NSMutableArray(); // array];
		for (int i = 0; i < 9; i++) 
		{
			if(otherIndexes.get(i))
				parts1.add(otherParts[i]);
			
			if (myIndexes.get(i))
				parts2.add(myParts[i]);
		}
		
		//assert([parts1 count] == [parts2 count]);
		NSMutableArray allParts = new NSMutableArray(); // array];
		allParts.setupSize(parts1.size() + parts2.size());
		
		for (int i = 0; i < parts1.size(); i++) 
		{
			//[allParts replaceObjectAtIndex:(i * 2) withObject:[parts1 objectAtIndex:i]];
			allParts.set((i * 2), parts1.get(i));
			//[allParts replaceObjectAtIndex:(i * 2 + 1) withObject:[parts2 objectAtIndex:i]];
			allParts.set((i * 2 + 1), parts2.get(i));
		}
		
		// Build highlighted potentials and cells
		NSMutableArray cells = new NSMutableArray(); // array];
		//NSMutableDictionary* cellPotentials = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellPotentials= new HashMap<Hints_Cell, Hints_BitSet>(); 
		
		for (int i = 0; i < 9; i++) 
		{
			for (int j = 0; j < 9; j++) 
			{
				if(myIndexes.get(i) && otherIndexes.get(j)) 
				{
					Hints_Cell cell = myParts[i].getCell(j);
					if(cell.hasPotentialValue(value)) 
					{
						cells.add(cell);
						//[cellPotentials setObject:[Hints_SingletonBitSet create:value] forKey:cell];
						cellPotentials.put(cell, Hints_SingletonBitSet.create(value));
					}
				}
			}
		}
		
		//NSMutableArray allCells = [NSMutableArray arrayWithArray:cells];
		NSMutableArray allCells = (NSMutableArray)cells.clone();
		
		// Build removable potentials
		//NSMutableDictionary* cellRemovablePotentials = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> cellRemovablePotentials= new HashMap<Hints_Cell, Hints_BitSet>(); 
		
		for(int i = 0; i < 9; i++) 
		{
			if(myIndexes.get(i)) 
			{
				// Check if value appears outside from otherIndexes
				Hints_BitSet potentialPositions = myParts[i].copyPotentialPositions(value);
				potentialPositions.andNot(otherIndexes);
				if(!potentialPositions.isEmpty()) 
				{
					for(int j = 0; j < 9; j++) 
					{
						if(potentialPositions.get(j))
							//[cellRemovablePotentials setObject:[Hints_SingletonBitSet create:value] forKey:[myParts[i] getCell:j]];
							cellRemovablePotentials.put(myParts[i].getCell(j), Hints_SingletonBitSet.create(value));
					}
				}
			}
		}
		
		//return [[[Hints_LockingHint alloc] initWith:allCells value:value highlightPotentials:cellPotentials removePotentials:cellRemovablePotentials regions:allParts] autorelease];
		return new Hints_LockingHint(allCells, value, cellPotentials ,cellRemovablePotentials, allParts);
	}

	/*
	 @Override
	 public String toString() {
	 if (degree == 2)
	 return "X-Wings";
	 else if (degree == 3)
	 return "Swordfishes";
	 else if (degree == 4)
	 return "Jellyfishes";
	 return null;
	 }
	 */
}
