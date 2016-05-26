package com.gold.sudoku;

public class GameStatsType
{
	int totalGameCount;
	int totalScore;
	
	int statsGameCount[] = new int[6];
	int statsScoreMin[] = new int[6];
	int statsScoreMax[] = new int[6];
	int statsScoreFull[] = new int[6];
	int statsTimeMin[] = new int[6];
	int statsTimeMax[] = new int[6];
	int statsTimeFull[] = new int[6];
	
	GameStatsType()
	{
		memset();
	}
	
	public void memset()
	{
		totalGameCount = 0;
		totalScore = 0;

		for(int i = 0; i < 6; i ++)
		{
			statsGameCount[i] = 0;
			statsScoreMin[i] = 0;
			statsScoreMax[i] = 0;
			statsScoreFull[i] = 0;
			statsTimeMin[i] = 0;
			statsTimeMax[i] = 0;
			statsTimeFull[i] = 0;
		}
	}
};
//typedef struct GameStatsType GameStatsType;

