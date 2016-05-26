package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_CommonTuples {
	/**
	 * Heart engine for the Naked Sets, Hidden Sets and N-Fishes rules.
	 */
	public static Hints_BitSet searchCommonTuple(ArrayList candidates, int degree)
	{
		Hints_BitSet result = new Hints_BitSet(9); // autorelease];

		for(int index = 0; index < candidates.size(); index++)
		{
			Hints_BitSet candidate = (Hints_BitSet)candidates.get(index);
			
			if(candidate.cardinality() <= 1)
				return null;
			
			result.or(candidate);
		}
		
		if(result.cardinality() == degree)
			return result;
		
		return null;
	}

	/**
	 * Same as before, but all bitsets must only have non-zero
	 * cardinality instead of grater than one.
	 * (Used for Unique Loops and BUGs type 3)
	 */
	public Hints_BitSet searchCommonTupleLight(ArrayList candidates,int degree)
	{
		Hints_BitSet result = new Hints_BitSet(9); // autorelease];
		
		for(int index = 0; index < candidates.size(); index++)
		{
			Hints_BitSet candidate = (Hints_BitSet)candidates.get(index);
			
			result.or(candidate);
			
			if(candidate.cardinality() == 0)
				return null;
		}
		
		if(result.cardinality() == degree)
			return result;
		
		return null;
	}

}
