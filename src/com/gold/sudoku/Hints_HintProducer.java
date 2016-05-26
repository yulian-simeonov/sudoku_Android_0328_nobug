package com.gold.sudoku;

import java.util.Vector;


public class Hints_HintProducer {
	
	Vector<Hints_Hint> hintsArray = new Vector<Hints_Hint>();

	public void addHint(Hints_Hint hint)
	{
		for(int index = 0; index < hintsArray.size(); index++)
		{
			Hints_Hint curHint = (Hints_Hint) hintsArray.get(index);
		
			if(curHint.equals(hint)/* && ([curHint hashCode] != [hint hashCode])*/)
				return;
		}
	
		hintsArray.add(hint);
	}

}
