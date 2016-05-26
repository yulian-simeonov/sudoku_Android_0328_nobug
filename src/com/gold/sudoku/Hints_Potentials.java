package com.gold.sudoku;

import java.util.HashMap;
import java.util.Map;

public class Hints_Potentials extends Hints_HintProducer {

	public void getHints(Hints_Grid grid)
	{
		//Map<K, V> dict = [NSMutableDictionary dictionary];
		Map<Hints_Cell, Hints_BitSet> dict = new HashMap<Hints_Cell, Hints_BitSet>();
	
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				Hints_Cell cell = grid.getCellAtX(x, y);
			
				if(cell.value == 0)
					//[dict setObject:cell.potentialValues forKey:cell];
					dict.put(cell, cell.potentialValues);
			}
		}
	
		Hints_PotentialsHint hint = new Hints_PotentialsHint(dict);
		addHint(hint);
	}

}
