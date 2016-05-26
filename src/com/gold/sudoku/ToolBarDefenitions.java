package com.gold.sudoku;

import org.cocos2d.types.CGPoint;

public class ToolBarDefenitions {

	public static ButtonItemDef _buttonsBottomBar[] = 
	{
		//{-1, {1,        9, 39, 30}, kImageIconHelp, kImageIconHelpSel, kImageBarBottomBack, kImageBarBottomBack, "onHelp:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f, 24), ImageType.kImageIconHelp, ImageType.kImageIconHelpSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onHelp"),
		//{-1, {1 + 1*40, 9, 39, 30}, kImageIconMenu, kImageIconMenuSel, kImageBarBottomBack, kImageBarBottomBack, "onInfo:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 1*40, 24), ImageType.kImageIconMenu, ImageType.kImageIconMenuSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onInfo"),
		//{-1, {1 + 2*40, 9, 39, 30}, kImageIconUndo, kImageIconUndoSel, kImageBarBottomBack, kImageBarBottomBack, "onUndo:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 2*40, 24), ImageType.kImageIconUndo, ImageType.kImageIconUndoSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onUndo"),
		//{-1, {1 + 3*40, 9, 39, 30}, kImageIconHistory, kImageIconHistorySel, kImageBarBottomBack, kImageBarBottomBack, "onHistory:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 3*40, 24), ImageType.kImageIconHistory, ImageType.kImageIconHistorySel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onHistory"),
		//{-1, {1 + 4*40, 9, 39, 30}, kImageIconFlag, kImageIconFlagSel, kImageBarBottomBack, kImageBarBottomBack, "onFlag:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 4*40, 24), ImageType.kImageIconFlag, ImageType.kImageIconFlagSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onFlag"),
		//{-1, {1 + 5*40, 9, 39, 30}, kImageIconTransform, kImageIconTransformSel, kImageBarBottomBack, kImageBarBottomBack, "onTransform:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 5*40, 24), ImageType.kImageIconTransform, ImageType.kImageIconTransformSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onTransform"),
		//{-1, {1 + 6*40, 9, 39, 30}, kImageIconWisard, kImageIconWisardSel, kImageBarBottomBack, kImageBarBottomBack, "onWisard:"},
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 6*40, 24), ImageType.kImageIconWisard, ImageType.kImageIconWisardSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onWisard"),
		//{-1, {1 + 7*40, 9, 39, 30}, kImageIconWand, kImageIconWandSel, kImageBarBottomBack, kImageBarBottomBack, "onWand:"}
		new ButtonItemDef(-1, CGPoint.ccp(19.5f + 7*40, 24), ImageType.kImageIconWand, ImageType.kImageIconWandSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onWand"),
	};
	
	//const int _buttonsBottomBarCount = sizeof(_buttonsBottomBar)/sizeof(ButtonItemDef);
	final public static int _buttonsBottomBarCount = 8;

	public static ButtonItemDef _buttonsBottomBarCandidate[] = 
	{
		//{1, {5, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(1, CGPoint.ccp(20, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{2, {39, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(2, CGPoint.ccp(54, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{3, {74, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(3, CGPoint.ccp(89, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{4, {109, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(4, CGPoint.ccp(124, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{5, {144, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(5, CGPoint.ccp(159, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{6, {179, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(6, CGPoint.ccp(194, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{7, {214, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(7, CGPoint.ccp(229, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{8, {249, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(8, CGPoint.ccp(264, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
		//{9,	{284, 9, 30, 30}, kImageBarEmpty, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetNumber:"},
		new ButtonItemDef(9, CGPoint.ccp(299, 24), ImageType.kImageBtnEmpty, ImageType.kImageBtnEmpty, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetNumber"),
	};
	
	//const int _buttonsBottomBarCandidateCount = sizeof(_buttonsBottomBarCandidate)/sizeof(ButtonItemDef);
	final public static int _buttonsBottomBarCandidateCount = 9;

	public static ButtonItemDef _buttonsMiddleBarCandidate[] = 
	{
		//{1, {5, 5, 30, 30}, kImageIconBarGray, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(1, CGPoint.ccp(20, 20), ImageType.kImageIconBarGray, ImageType.kImageIconBarGraySel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{2, {39, 5, 30, 30}, kImageIconBarRed, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(2, CGPoint.ccp(54, 20), ImageType.kImageIconBarRed, ImageType.kImageIconBarRedSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{3, {74, 5, 30, 30}, kImageIconBarOrange, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(3, CGPoint.ccp(89, 20), ImageType.kImageIconBarOrange, ImageType.kImageIconBarOrangeSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{4, {109, 5, 30, 30}, kImageIconBarYellow, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(4, CGPoint.ccp(124, 20), ImageType.kImageIconBarYellow, ImageType.kImageIconBarYellowSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{5, {144, 5, 30, 30}, kImageIconBarGreen, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(5, CGPoint.ccp(159, 20), ImageType.kImageIconBarGreen, ImageType.kImageIconBarGreenSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{6, {179, 5, 30, 30}, kImageIconBarBlue, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateSetColor:"},
		new ButtonItemDef(6, CGPoint.ccp(194, 20), ImageType.kImageIconBarBlue, ImageType.kImageIconBarBlueSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateSetColor"),
		//{100, {214, 5, 30, 30}, kImageIconBarPossible, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateMode:"},
		new ButtonItemDef(100, CGPoint.ccp(229, 20), ImageType.kImageIconBarPossible, ImageType.kImageIconBarPossibleSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateMode"),
		//{101, {249, 5, 30, 30}, kImageIconBarCancel, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateCancel:"},
		new ButtonItemDef(101, CGPoint.ccp(264, 20), ImageType.kImageIconBarCancel, ImageType.kImageIconBarCancelSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateCancel"),
		//{102, {284, 5, 30, 30}, kImageIconBarOK, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onCandidateOK:"},
		new ButtonItemDef(102, CGPoint.ccp(299, 20), ImageType.kImageIconBarOK, ImageType.kImageIconBarOKSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onCandidateOK"),
	};
	
	//const int _buttonsMiddleBarCandidateCount = sizeof(_buttonsMiddleBarCandidate)/sizeof(ButtonItemDef);
	final public static int _buttonsMiddleBarCandidateCount = 9;
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//

}
