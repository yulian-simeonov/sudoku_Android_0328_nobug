package com.gold.sudoku;

import java.util.ArrayList;
import java.util.Map;

public class Hints_IndirectHint extends Hints_Hint {
	//	Map<K, V> removablePotentials;
	Map removablePotentials;
	/**
	 * Create a new indirect hint.
	 * @param rule the rule that discovered the hint
	 * @param removablePotentials the potential values that can be removed
	 * in cells of the sudoku grid by applying this hint.
	 */
	public Hints_IndirectHint (Map _removablePotentials) 
	{
		//self = [super init];
		super();
		
		removablePotentials = _removablePotentials;
		//return self;
	}

	
	/**
	 * Get the potential values that can be removed from cells of the sudoku
	 * grid by applying this hint. The keys are cells of the grid, and the
	 * values are the bit set of values that can be removed from the corresponding
	 * cell. Note that the bit sets can only contain values ranging between 1 and 9.
	 * @return the potential values that can be removed from cells of the sudoku
	 * grid by applying this hint
	 */
	public Map getRemovablePotentials() 
	{
		return removablePotentials;
	}

	/**
	 * Test is this hint is worth, that is, if it really allows one to remove
	 * at least one potential value from a cell of the grid.
	 * <p>
	 * This method is used, because it is frequent that a solving pattern is
	 * dicovered, but does not allow any progress in the current grid. But this
	 * fact is hard to discover before the pattern itself.
	 * @return whether this hint allows some progress in the solving process
	 */
	public boolean isWorth()
	{
		return !(removablePotentials.isEmpty());
	}

	//  Visual representation

	/**
	 * Get the number of views that are required to make a visual
	 * representation of this hint.
	 */
	public  int getViewCount()
	{
		return 0;
	}

	/**
	 * Get the cells that must be highlighted in a visual representation
	 * of this hint.
	 * @return the cells that must be highlighted in a visual representation, or
	 * <tt>null</tt> if none
	 * 
	 */
	//public ArrayList getSelectedCells()
	public ArrayList getSelectedCells()
	{
		//return [NSMutableArray array];
		return new NSMutableArray();
	}

	/**
	 * Get the cell potential values that must be highlighted in green
	 * (or in a positive-sounding color) in a visual representation of this
	 * hint.
	 * <p>
	 * Note that potential values that must be highlighted both in green
	 * and in red (according to {@link #getRedPotentials(int)}, will be
	 * highlighted in orange.
	 * @param viewNum the view number, zero-based.
	 * @return the cell potential values to highlight in green, or
	 * <tt>null</tt> if none
	 * @see #getViewCount()
	 */
	public Map getGreenPotentials(int viewNum)
	{
		//return [NSMutableDictionary dictionary];
		return null;
	}

	/**
	 * Get the cell potential values that must be highlighted in red
	 * (or in a negative-sounding color) in a visual representation of this
	 * hint.
	 * @param viewNum the view number, zero-based
	 * @return the cell potential values to highlight in red, or
	 * <tt>null</tt> if none
	 * @see #getViewCount()
	 */
	public  Map getRedPotentials(int viewNum)
	{
		//return [NSMutableDictionary dictionary];
		return null;
	}

	public Map getOrangePotentials(int viewNum)
	{
		//return [NSMutableDictionary dictionary];
		return null;
	}

	public Map getGrayPotentials(int viewNum)
	{
		//return [NSMutableDictionary dictionary];
		return null;
	}

	public Map getBluePotentials(Hints_Grid grid, int viewNum)
	{
		//return [NSMutableDictionary dictionary];
		return null;
	}

	/**
	 * Get the links to draw in a visual representation of this hint.
	 * @param viewNum the vien number, zero-based
	 * @return the links to draw, or <tt>null</tt> if none.
	 * @see #getViewCount()
	 * @see Link
	 */
	public  ArrayList getLinks(int viewNum)
	{
		//return [NSMutableArray array];
		return null;
	}
}
