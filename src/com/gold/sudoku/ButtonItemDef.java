package com.gold.sudoku;

import org.cocos2d.types.CGPoint;

public class ButtonItemDef {
		int buttonID;
		//CGRect bounds;
		CGPoint bounds;
		int imageIcon;
		int imageIconHilight;
		int imageBack;
		int imageBackHilight;
		String  selectorName;

		ButtonItemDef(int arg1, CGPoint arg2, int arg3, int arg4, int arg5, int arg6, String arg7) {
			buttonID = arg1;
			bounds = arg2;
			imageIcon = arg3;
			imageIconHilight = arg4;
			imageBack = arg5;
			imageBackHilight = arg6;
			selectorName = arg7;
		}

}
