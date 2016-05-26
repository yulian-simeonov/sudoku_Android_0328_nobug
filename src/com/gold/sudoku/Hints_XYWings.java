package com.gold.sudoku;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Hints_XYWings extends Hints_HintProducer {
	
	boolean isXYZ;

	public Hints_XYWings(boolean _isXYZ)
	{
		//self = [super init];
		super();

		isXYZ = _isXYZ;

		//return self;
	}

	/**
	* Test if the potential values of three cells are forming an XY-Wing.
	* <p>
	* In an XY-Wing, the three cells must have exactly the potential values xy,
	* xz and yz, respectively, where x, y and z are different number between
	* 1 and 9.
	* <p>
	* We test that their union has three value and their intersection is empty.
	* @param xyValues the potential values of the "XY" cell
	* @param xzValues the potential values of the "XZ" cell
	* @param yzValues the potential values of the "YZ" cell
	* @return whether the three potential values set are forming an XY-Wing.
	*/
	public boolean  isXYWing(Hints_BitSet xyValues, Hints_BitSet xzValues, Hints_BitSet yzValues)
	{
		if(xyValues.cardinality() != 2 || xzValues.cardinality() != 2 || yzValues.cardinality() != 2)	
			return false;
		
		Hints_BitSet _union = new Hints_BitSet(xyValues);
		_union.or(xzValues);
		_union.or(yzValues);
		
		Hints_BitSet inter = new Hints_BitSet(xyValues);
		inter.and(xzValues);
		inter.and(yzValues);

		return _union.cardinality() == 3 && inter.cardinality() == 0;
	}
	
	public boolean  isXYZWing(Hints_BitSet xyValues, Hints_BitSet xzValues,Hints_BitSet yzValues)
	{
		if((xyValues.cardinality()) != 3 || xzValues.cardinality() != 2 || yzValues.cardinality() != 2)
			return false;
		
		Hints_BitSet _union = new Hints_BitSet(xyValues);
		_union.or(xzValues);
		_union.or(yzValues);
		
		Hints_BitSet inter = new Hints_BitSet(xyValues);
		inter.and(xzValues);
		inter.and(yzValues);
		
		return _union.cardinality() == 3 && inter.cardinality() == 1;
	}

	public void getHints(Hints_Grid grid)
	{
		int targetCardinality = (isXYZ ? 3 : 2);
		
		for (int y = 0; y < 9; y++) 
		{
			for (int x = 0; x < 9; x++) 
			{
				Hints_Cell xyCell = grid.getCellAtX(x, y);
				Hints_BitSet xyValues = xyCell.getPotentialValues();
		
				if(xyValues.cardinality() == targetCardinality) 
				{
					// Potential XY cell found
					NSMutableArray xzCellHouse = xyCell.getHouseCells();
					for(int xzCellHouseIndex = 0; xzCellHouseIndex < xzCellHouse.size(); xzCellHouseIndex++)
					{
						Hints_Cell xzCell = (Hints_Cell)xzCellHouse.get(xzCellHouseIndex);
						
						Hints_BitSet xzValues = xzCell.getPotentialValues();
						if(xzValues.cardinality() == 2) 
						{
							// Potential XZ cell found. Do small test
							Hints_BitSet remValues = new Hints_BitSet(xyValues); //] autorelease];
							remValues.andNot(xzValues);
							
							if(remValues.cardinality() == 1) 
							{
								// We have found XZ cell, look for YZ cell
								NSMutableArray xyCellHouse = xyCell.getHouseCells(); 
								for(int xyCellHouseIndex = 0; xyCellHouseIndex < xyCellHouse.size(); xyCellHouseIndex++)
								{
									Hints_Cell yzCell = (Hints_Cell)xyCellHouse.get(xyCellHouseIndex);
									
									Hints_BitSet yzValues = yzCell.getPotentialValues();
									if(yzValues.cardinality() == 2) 
									{
										// Potential YZ cell found
										if(isXYZ) 
										{
											if(this.isXYZWing(xyValues, xzValues, yzValues)) 
											{
												// Found XYZ-Wing pattern
												Hints_XYWingHint hint = this.createHint(xyCell, xzCell, yzCell , xzValues, yzValues);
												
												if(hint.isWorth())
													this.addHint(hint);
											}
										} 
										else 
										{
											if(this.isXYWing(xyValues, xzValues, yzValues)) 
											{
												// Found XY-Wing pattern
												Hints_XYWingHint hint = this.createHint(xyCell, xzCell, yzCell, xzValues, yzValues);
												
												if(hint.isWorth())
													this.addHint(hint);
											}
										}
									} // yzValues.cardinality() == 2
								} // for yzCell
							} // xy - xz test
						} // xzValues.cardinality() == 2
					} // for xzCell
				} // xyValues.cardinality() == 2
			} // for x
		} // for y
	}


	public Hints_XYWingHint createHint(Hints_Cell xyCell ,Hints_Cell xzCell, Hints_Cell yzCell, Hints_BitSet xzValues, Hints_BitSet yzValues)
	{
		//Get the "z" value
		Hints_BitSet inter =  new Hints_BitSet(xzValues); // autorelease];
		inter.and(yzValues);
		int zValue = inter.nextSetBit(0);
		
		// Build list of removable potentials
		Map<Hints_Cell, Hints_BitSet> removablePotentials = new HashMap<Hints_Cell, Hints_BitSet>();
		//NSMutableDictionary* removablePotentials = [NSMutableDictionary dictionary];
		
		//Set<Cell> victims = new LinkedHashSet<Cell>(xzCell.getHouseCells());
		//NSMutableArray* victims = [NSMutableArray arrayWithArray:[xzCell getHouseCells]];
		NSMutableArray victims = (NSMutableArray)xzCell.getHouseCells().clone();
		victims.retainAll(yzCell.getHouseCells());
		
		if (isXYZ)
			victims.retainAll(xyCell.getHouseCells());
		
		victims.remove(xyCell);
		victims.remove(xzCell);
		victims.remove(yzCell);
		
		for(int index = 0; index < victims.size(); index++)
		{
			Hints_Cell cell = (Hints_Cell)victims.get(index);
			
			if(cell.hasPotentialValue(zValue))
				//[removablePotentials setObject:[Hints_SingletonBitSet create:zValue] forKey:cell];
				removablePotentials.put(cell, Hints_SingletonBitSet.create(zValue));
		}
		
		// Create hint
		return new Hints_XYWingHint(removablePotentials, isXYZ, xyCell, xzCell, yzCell, zValue); // autorelease];
	}

///*
//@Override
//public String toString() {
//return "XY-Wings";
//}
//*/

}
