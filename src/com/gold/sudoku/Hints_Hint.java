package com.gold.sudoku;

import java.util.ArrayList;

public class Hints_Hint {
	
	
	/**
	 * Get the cell that can be filled, if any,
	 * by applying this hint
	 * @return the cell that can be filled
	 */
	public Hints_Cell getCell()
	{
		return null;
	}

	/**
	 * Get the value that can be placed in the cell,
	 * if any.
	 * @return the value that can be placed in the cell
	 * @see #getCell()
	 */
	public int getValue()
	{
		return 0;
	}

	/**
	 * Get the regions concerned by this hint.
	 * <tt>null</tt> can be returned if this hint does
	 * not depend on regions.
	 * @return the regions concerned by this hint
	 */
	public ArrayList<Hints_Region> getRegions()
	{
//		return [NSMutableArray array];
		return null; //hb
	}

	public  boolean  equals(Hints_Hint hint)
	{
		return (this.hashCode() == hint.hashCode() );
	}

	public int hashCode()
	{
		//return (int)this;
		return 0;
	}

}
