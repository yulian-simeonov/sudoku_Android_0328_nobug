package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_IntArray {
	
	ArrayList<Integer> array;

	public Hints_IntArray()
	{
		array = new ArrayList<Integer>();
	}
	
	public Hints_IntArray( int size)
	{
		array = new ArrayList<Integer>();
		for(int index = 0; index < size; index++)
		{
			array.add(0);
		}
			
	}

	
	public int get(int index)
	{
		int number =array.get(index);

	return number;// intValue];
	}

	public void set(int value, int index)
	{
	
		array.set(index, value);
	}

	public int count()
	{
		return array.size();
	}

}
