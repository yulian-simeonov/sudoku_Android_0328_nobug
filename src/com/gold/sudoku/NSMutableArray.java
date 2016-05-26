package com.gold.sudoku;

import java.util.ArrayList;

public class NSMutableArray extends ArrayList{
	
	public  void addUniqueObject(Object object)
	{
		for(int index = 0; index < this.size(); index++)
		{
			Object obj = this.get(index);
			
			if(obj == object)
				return;
		}

		this.add(object);
	}

	public void retainAll(NSMutableArray array)
	{
		int index = 0;
	
		do
		{
			if(!array.contains(this.get(index)))
			{
				this.remove(index);
				index = 0;
			}
			else
				index += 1;
		}
		while(index < this.size());
	}

	public boolean isEmpty()
	{
		return (this.size() == 0);
	}

//	public void remove(Object object)
//	{
//		int result = -1;
//
//		for(int index = 0; index < this.size(); index++)
//		{
//			if(this.get(index) == object)
//			{
//				result = index;
//				break;
//			}
//		}
//	
//		if(result != -1)
//			this.remove(result);
//	}

//	public boolean contains(Object object)
//	{
//	for(int index = 0; index < [self count]; index++)
//	{
//		if([self objectAtIndex:index] == object)
//			return YES;
//	}
//
//		return false;
//	}
//
//	- (BOOL)containsAll:(NSMutableArray*)otherArray
//	{
//	for(int index = 0; index < [self count]; index++)
//	{
//		if(![otherArray contains:[self objectAtIndex:index]])
//			return NO;
//	}
//
//	return YES;
//	}

	public void setupSize(int size)
	{
		for(int index = 0; index < size; index++)
			add(null); //addObject:[NSNull null]];
	}

//	public boolean isEmpty() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
