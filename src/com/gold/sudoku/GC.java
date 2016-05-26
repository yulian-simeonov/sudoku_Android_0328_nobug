package com.gold.sudoku;

public class GC {
	static float kGameViewViewsAnimationDuration = 0.3f;

	//MoveType
	public static final int kCATransitionFromTop = 1;
	public static final int kCATransitionFromRight = 2;
	public static final int kCATransitionFromLeft = 3;
	
	// GameModeType
	public static final int kGameModeNormal = 0;
	public static final int kGameModeEnterCandidates = 1;
	public static final int kGameModeEnterOwnSudoku = 2;
	public static final int kGameModeShowHints = 3;
	public static final int kGameModeShowHistory = 4;
	public static final int kGameModeShowStaticHint = 5;

	
	static public  int REVIEW_STATE = 304;
	static public  String VERSION_TO_DISPLAY = "V3.04";

	static public  int  INTERSTATIALS = 1; 
	static public  boolean ADS_ENABLE = true;
	static public  boolean IS_FREE_VERSION = true;

	static public String ACH_BEGINNER  = "BeginnerIAP25";
	static public String ACH_APPRENTICE = "ApprenticeIAP25";
	static public String ACH_NOVICE  = "NoviceIAP25";
	static public String ACH_EXPERT  = "ExpertIAP25";
	static public String ACH_MASTER  = "MasterIAP25";

}
