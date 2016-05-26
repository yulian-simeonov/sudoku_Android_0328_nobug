package com.gold.sudoku;

public class SudokuData {
	final static int kSudokuDataGroupsCount		= 6;
	final static int kSudokuDataItemsInGroup	=	300;

	
	public static int SudokuData_GetBase(int group, int item, int row, int col)
	{
		char []values = null;
		int index;
		
		if(item >= kSudokuDataItemsInGroup)
			return 0;
		
		switch(group)
		{
		case 0:
			//values = _base_0[item];
			if(item < 100)
				values = _base_0_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_0_1._mbase[item];
			} else {
				item -= 200;
				values = _base_0_2._mbase[item];
			}
			break;
		case 1:
			if(item < 100)
				values = _base_1_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_1_1._mbase[item];
			} else {
				item -= 200;
				values = _base_1_2._mbase[item];
			}
			break;
		case 2:
			if(item < 100)
				values = _base_2_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_2_1._mbase[item];
			} else {
				item -= 200;
				values = _base_2_2._mbase[item];
			}
			break;
		case 3:
			if(item < 100)
				values = _base_3_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_3_1._mbase[item];
			} else {
				item -= 200;
				values = _base_3_2._mbase[item];
			}
			break;
		case 4:
			if(item < 100)
				values = _base_4_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_4_1._mbase[item];
			} else {
				item -= 200;
				values = _base_4_2._mbase[item];
			}
			break;
		case 5:
			if(item < 100)
				values = _base_5_0._mbase[item];
			else if(item < 200) {
				item -= 100;
				values = _base_5_1._mbase[item];
			} else {
				item -= 200;
				values = _base_5_2._mbase[item];
			}
			break;
		};
		
		index = row*9 + col;
		
		return values[index] - '0';
	};
	
	public static int SudokuData_GetMask(int group, int item, int row, int col)
	{
		char []values = null;
		int index;
		
		if(item >= kSudokuDataItemsInGroup)
			return 0;
		
		switch(group)
		{
			case 0:
				if(item < 100)
					values = _base_mask_0_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_0_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_0_2._mbase[item];
				}
				break;
			case 1:
				if(item < 100)
					values = _base_mask_1_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_1_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_1_2._mbase[item];
				}
				break;
			case 2:
				if(item < 100)
					values = _base_mask_2_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_2_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_2_2._mbase[item];
				}
				break;
			case 3:
				if(item < 100)
					values = _base_mask_3_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_3_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_3_2._mbase[item];
				}
				break;
			case 4:
				if(item < 100)
					values = _base_mask_4_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_4_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_4_2._mbase[item];
				}
				break;
			case 5:
				if(item < 100)
					values = _base_mask_5_0._mbase[item];
				else if(item < 200) {
					item -= 100;
					values = _base_mask_5_1._mbase[item];
				} else {
					item -= 200;
					values = _base_mask_5_2._mbase[item];
				}
				break;
		};
		
		index = row*9 + col;
		
		if(values[index] == '-')
			return 0;
		
		return values[index] - '0';
	}
}
