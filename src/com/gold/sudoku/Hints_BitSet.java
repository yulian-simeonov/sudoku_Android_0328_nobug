package com.gold.sudoku;

public class Hints_BitSet {
	
	
	boolean []items; //24

	public Hints_BitSet(int size )
	{
		items = new boolean[24];
		for(int index = 0; index < 24; index++)
			items[index] = false;
	}
	

	public Hints_BitSet (Hints_BitSet bitSet)
	{
		items = new boolean[24];
		for(int index = 0; index < 24; index++)
			items[index] =  bitSet.get(index) ;
	}

	
	public int size()
	{
		int result = 0;
	
		for(int index = 0; index < 24; index++)
		{
			if(items[index])
				result = index + 1;
		}
	
		return result;
	}

	public boolean get(int index)
	{
		//assert((index >= 0) && (index < 24));

		return items[index];
	}

	public void set( boolean value, int index)
	{
		//assert((index >= 0) && (index < 24));

		items[index] = value;
	}

	public void or(Hints_BitSet bitSet)
	{
		int count = this.size();
	
		if(bitSet.size() > count)
			count = bitSet.size();
	
		for(int index = 0; index < count; index++)
			items[index] = items[index] | bitSet.get(index);
	}

	public void and(Hints_BitSet bitSet)
	{
		int count = this.size();
	
		if(bitSet.size() > count)
			count = bitSet.size();
	
		for(int index = 0; index < count; index++)
			items[index] = items[index] & bitSet.get(index) ;
	}

	public void andNot(Hints_BitSet bitSet)
	{
		int count = this.size();
	
		if(bitSet.size() > count)
			count = bitSet.size();
	
		for(int index = 0; index < count; index++)
			items[index] = items[index] & !bitSet.get(index);
	}

	public int cardinality()
	{
		int result = 0;
	
		for(int index = 0; index < 24; index++)
		{
			if(items[index]) 
				result += 1;
		}		
	
		return result;
	}

	public int nextSetBit(int fromIndex)
	{
		if(fromIndex >= 24)
			return -1;
	
		for(int index = fromIndex; index < 24; index++)
		{
			if(items[index]) 
				return index;
		}
	
		return -1;
	}

	public boolean isEmpty()
	{
		return (this.size() == 0);
	}

	public  void clear()
	{
		for(int index = 0; index < 24; index++)
			items[index] = false;
	}

	public void reset()
	{
		for(int index = 0; index < 24; index++)
			items[index] = true;
	}

}

