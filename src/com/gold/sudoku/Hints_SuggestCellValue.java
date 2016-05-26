package com.gold.sudoku;

import java.util.Vector;

public class Hints_SuggestCellValue extends Hints_HintProducer {

	boolean isValue;

	public Hints_SuggestCellValue(boolean _isValue)
	{
		//self = [super init];
		super();

		isValue = _isValue;
	}

	public void getHints(Hints_Grid grid)
	{
		Vector<Hints_Hint> tmpHintsArray = new Vector<Hints_Hint>(); 
		
		Hints_NakedSingle nakedSingle = new Hints_NakedSingle();	// alloc] init] autorelease];
		nakedSingle.hintsArray = tmpHintsArray;
		nakedSingle.getHints(grid);
		
		if(tmpHintsArray.size() == 0)
		{
			Hints_HiddenSingle hiddenSingle = new Hints_HiddenSingle(); // alloc] init] autorelease];
			hiddenSingle.hintsArray = tmpHintsArray;
			hiddenSingle.getHints(grid, true);
			hiddenSingle.getHints(grid, false);
		}
		
		if(tmpHintsArray.size() == 0)
		{
			for(int y = 0; y < 9; y++)
			{
				for(int x = 0; x < 9; x++)
				{
					Hints_Cell cell = grid.getCellAtX(x, y);
					
					if(cell.value != 0)
						continue;
					
					Grid solverGrid;
					char curGrid[] = new char[90]; //[90];
					GameGridType tmpGrid = new GameGridType();
					
					//memset(&tmpGrid, 0, sizeof(tmpGrid));
					
					SudokuUtils.SudokuUtils_GridToSolverString(SudokuUtils.g_gameGrid, curGrid);
					
					//init_solve_engine(NULL, NULL, NULL, 1, 0);
					sudoku_engine.init_solve_engine(1, 0);
					solverGrid = sudoku_engine.solve_sudoku(curGrid);
					if(solverGrid != null)
					{
						sudoku_engine.format_answer(solverGrid, curGrid);
						SudokuUtils.SudokuUtils_SolverStringToGrid(curGrid, tmpGrid);
						
						Hints_SuggestedCellValueHint newHint = new Hints_SuggestedCellValueHint(); // alloc] init] autorelease];
						
						newHint.isValue = isValue;
						newHint.cell = cell;
						newHint.value = tmpGrid.grid[y][x].number;
						
						addHint(newHint);
						
						sudoku_engine.free_soln_list(solverGrid);
					}
					
					return;
				}
			}
		}
		else
		{
			Hints_Hint hint = tmpHintsArray.elementAt(0);
			
			Hints_SuggestedCellValueHint newHint = new Hints_SuggestedCellValueHint(); // alloc] init] autorelease];
			
			newHint.isValue = isValue;
			newHint.cell = hint.getCell();
			newHint.value = hint.getValue();
			
			addHint(newHint);
		}
	}
}
