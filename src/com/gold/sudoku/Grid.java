package com.gold.sudoku;

public class Grid
{
	short []cellflags;
    short []solved;
    short []cell;
    short tail, givens, exposed, maxlvl, inc, reward;
    int score, solncount, pass_mods;
    Grid next;
    
    
    public Grid()
    {
    	cellflags = new short[sudoku_engine.PUZZLE_CELLS];
    	solved    = new short[sudoku_engine.PUZZLE_CELLS];
    	cell      = new short[sudoku_engine.PUZZLE_CELLS];
    	
    	next = null;
    }
}
