package com.gold.sudoku;

public class Hints_Permutations {
	int countBits;
	int countOnes;
	long mask;
		
	long value;
	boolean isLast;
	
	/**
	 * Create a new binary permutations generator.
	 * <p>
	 * The maximum supported value for <code>countBits</code>
	 * is 64. <code>countOnes</code> must be equal or less than
	 * <code>countBits</code>.
	 * @param countOnes the number of bits equal to one
	 * @param countBits the length of the binary numbers in bits
	 */
	public  Hints_Permutations(int _countOnes, int _countBits)
	{
		//self = [super init];
		super();
		
		//assert(!(_countOnes < 0));
		//assert(!(_countBits < 0));
		//assert(!(_countOnes > _countBits));
		//assert(!(_countBits > 64));
		
		countBits = _countBits;
		countOnes = _countOnes;
		value = (1 << countOnes) - 1;
		mask = (1L << (countBits - countOnes)) - 1;
		isLast = (countBits == 0);
		
		//return self;
	}

	/**
	 * Test if there are more permutations available
	 * @return whether there are more permutations available
	 */
	public boolean hasNext()
	{
		boolean result = !isLast;
		
		isLast = ((value & -value) & mask) == 0;
		
		return result;
	}

	/**
	 * Get the next binary permutation.
	 * @return the next binary permutation
	 */
	public long next()
	{
		long result = value;
		
		if(!isLast) 
		{
			long smallest = value & -value;
			long ripple = value + smallest;
			long ones = value ^ ripple;
			
			ones = (ones >> 2) / smallest;
			value = ripple | ones;
		}
		
		return result;
	}

	/**
	 * Get the next binary permutation as an array
	 * of bit indexes.
	 * @return the 0-based indexes of the bits that are set
	 * to one.
	 */
	public Hints_IntArray nextBitNums()
	{
		long _mask = this.next();
		Hints_IntArray result = new Hints_IntArray(countOnes);
		int dst = 0;
		
		for(int src = 0; src < countBits; src++) 
		{
			if ((_mask & (1L << src)) != 0) // Bit number 'src' is set
			{
				result.set(src, dst);
				dst += 1;
			}
		}
		
		return result;
	}

}
