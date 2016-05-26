package com.gold.sudoku;

public class Hints_WrongValues extends Hints_HintProducer {

	boolean skipPersist;
	boolean addRegions;


	public Hints_WrongValues(boolean _skipPersist, boolean _addRegions)
	{
		//self = [super init];
		super();
	
		skipPersist = _skipPersist;
		addRegions = _addRegions;
	}

	public void getHints(Hints_Grid grid)
	{
		Grid solverGrid;
		char curGrid[] = new char[90]; //[90];
		GameGridType tmpGrid = new GameGridType();
		
		//memset(&tmpGrid, 0, sizeof(tmpGrid));
		SudokuUtils.SudokuUtils_GridToSolverString(SudokuUtils.g_gameGrid, curGrid);
		
		//init_solve_engine(NULL, NULL, NULL, 1, 0);
		sudoku_engine.init_solve_engine(1, 0);
		solverGrid = sudoku_engine.solve_sudoku(curGrid);
		if(grid != null)
		{
			if(solverGrid.solncount >= 1)
			{
				sudoku_engine.format_answer(solverGrid, curGrid);
				SudokuUtils.SudokuUtils_SolverStringToGrid(curGrid, tmpGrid);
			}
			
			sudoku_engine.free_soln_list(solverGrid);
		}	
		
		NSMutableArray cellArray = new NSMutableArray(); // array];
		NSMutableArray regionsArray = new NSMutableArray(); // array];
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y< 9; y++)
			{
				Hints_Cell curCell = grid.getCellAtX(x, y);
				if(curCell.value == 0)
					continue;
		
				if(curCell.value != tmpGrid.grid[y][x].number)
				{
					cellArray.addUniqueObject(curCell);
				}
			}
		}
		
		if(cellArray.size() != 0)
		{
			//Hints_WrongValuesHint hint = new Hints_WrongValuesHint( alloc] initWithCells:cellArray regions:regionsArray] autorelease];
			Hints_WrongValuesHint hint = new Hints_WrongValuesHint(cellArray, regionsArray); //] autorelease];
			addHint(hint);
		}
	}
}
