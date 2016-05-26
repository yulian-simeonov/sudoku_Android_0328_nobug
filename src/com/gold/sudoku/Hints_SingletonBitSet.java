package com.gold.sudoku;

public class Hints_SingletonBitSet {

	public static Hints_BitSet create(int value)
	{
		Hints_BitSet result = new Hints_BitSet( 10 );
		
		result.set(true, value);
		
		return result;
	}
}
