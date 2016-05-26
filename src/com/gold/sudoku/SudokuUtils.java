package com.gold.sudoku;

import java.util.Vector;

import org.cocos2d.types.CGSize;

import com.gold.sudoku.GameGridType.SudokuGridItemType;

public class SudokuUtils {
	
	public static GameGridType g_gameGrid = new GameGridType();
	public static GameGridType g_tmpGameGrid = new GameGridType();
	public static GameStatsType g_gameStats = new GameStatsType();

	public static Hints_Grid g_gameHintsGrid;
	//NSMutableArray* g_gameHintsAccumulator;
	public static Vector<Hints_Hint> g_gameHintsAccumulator;
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void intToByteArray(int paramInt, /*OUT*/ byte[] arrayOfByte, int index)
	{
		byte i = (byte)(paramInt & 0xFF);
		byte j = (byte)(paramInt >> 8 & 0xFF);
		byte k = (byte)(paramInt >> 16 & 0xFF);
		byte m = (byte)(paramInt >> 24 & 0xFF);

		arrayOfByte[index+3] = i;
		arrayOfByte[index+2] = j;
		arrayOfByte[index+1] = k;
		arrayOfByte[index] = m;
	}

	public static int byteArrayToInt(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
	{
		int i = -1;

		int j = paramByte4;
		int k = paramByte3;
		int m = paramByte2;
		int n = paramByte1;
		i = (0xFF & n) << 24 | (0xFF & m) << 16 | (0xFF & k) << 8 | 0xFF & j;
		return i;
	}

	public static String SudokuStats_ToString()
	{
		//NSData* data = [NSData dataWithBytes:&g_gameStats length:sizeof(g_gameStats)];
		//return [data base64Encoding];
		byte[] tmpchar = new byte[176]; //44*4 = 176

		int index = 0;
		
		intToByteArray(g_gameStats.totalGameCount, tmpchar, index);
		index += 4;
		intToByteArray(g_gameStats.totalScore, tmpchar, index);
		index += 4;
		
		for(int i=0; i<6; i++){
			intToByteArray(g_gameStats.statsGameCount[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsScoreMin[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsScoreMax[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsScoreFull[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsTimeMin[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsTimeMax[i], tmpchar, index);
			index += 4;
			intToByteArray(g_gameStats.statsTimeFull[i], tmpchar, index);
			index += 4;
		}
		
		return bytesToHex(tmpchar);
	}
	
	public static void SudokuStats_FromString(String str)
	{
		//memset(&g_gameStats, 0, sizeof(g_gameStats));
		
		if(str != null)
		{
			//NSData* data = [NSData dataWithBase64EncodedString:str];
			//[data getBytes:&g_gameStats length:sizeof(g_gameStats)];
			byte[] tmpchar = hexToBytes(str);
			
			int index = 0;
			
			g_gameStats.totalGameCount = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
			index += 4;
			g_gameStats.totalScore = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
			index += 4;
			
			for(int i=0; i<6; i++){
				g_gameStats.statsGameCount[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsScoreMin[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsScoreMax[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsScoreFull[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsTimeMin[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsTimeMax[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
				g_gameStats.statsTimeFull[i] = byteArrayToInt(tmpchar[index], tmpchar[index+1], tmpchar[index+2], tmpchar[index+3]);
				index += 4;
			}
		}
	}
	
	public static int SudokuStats_GameScoreToRatingIndex()
	{
		int result = 0;

		if(g_gameStats.totalScore >= 5000 && g_gameStats.totalScore < 40000)
		{
			result = 0;
		}
		else if(g_gameStats.totalScore >= 40000 && g_gameStats.totalScore < 160000)
		{
			result = 1;
		}
		else if(g_gameStats.totalScore >= 160000 && g_gameStats.totalScore < 640000)
		{
			result = 2;
		}
		else if(g_gameStats.totalScore >= 640000 && g_gameStats.totalScore < 2000000)
		{
			result = 3;
		}
		else if(g_gameStats.totalScore >= 2000000)
		{
			result = 4;
		}
	
		return result;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//// hints section
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean SudokuUtils_ItemEditable(int row, int col)
	{
		//return !(g_gameGrid.grid[row][col].number) || (g_gameGrid.grid[row][col].color != 0);
		return (g_gameGrid.grid[row][col].number == 0) || (g_gameGrid.grid[row][col].color != 0);
	}
	
	public static boolean SudokuUtils_ItemNumberMode(int row, int col)
	{
		boolean result = g_gameGrid.grid[row][col].color != -1;
		
		if((g_gameGrid.grid[row][col].number == 0) && (g_gameGrid.grid[row][col].color == -1))
			result = true;
		
		return result;
	}
	
	public static void SudokuUtils_ResetBoard()
	{
		//memset(&g_gameGrid, 0, sizeof(g_gameGrid));
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				g_gameGrid.grid[row][col].color = -1;
				
				for(int possible = 0; possible < 9; possible++)
					g_gameGrid.grid[row][col].candidates[possible] = -1;
			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean SudokuUtils_ClearNumbersAll()
	{
		boolean result = false;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(SudokuUtils_ItemEditable(row, col) == false)
					continue;
				
				g_gameGrid.grid[row][col].number = 0;
				g_gameGrid.grid[row][col].color = -1;
				result = true;
			}
		}
		
		return result;
	}

	public static boolean SudokuUtils_ClearNumbersByValue(int value)
	{
		boolean result = false;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(!SudokuUtils_ItemEditable(row, col))
					continue;
				
				if(g_gameGrid.grid[row][col].number != value)
					continue;
				
				g_gameGrid.grid[row][col].number = 0;
				g_gameGrid.grid[row][col].color = -1;
				result = true;
			}
		}
	
		return result;
	}

	public static boolean SudokuUtils_ClearNumbersByColor(int color)
	{
		boolean result = false;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(!SudokuUtils_ItemEditable(row, col))
					continue;
				
				if(g_gameGrid.grid[row][col].color != color)
					continue;
				
				g_gameGrid.grid[row][col].number = 0;
				g_gameGrid.grid[row][col].color = -1;
				result = true;
			}
		}
		
		return result;
	}

	public static boolean SudokuUtils_ClearCandidatesAll()
	{
		boolean result = false;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				for(int possible = 0; possible < 9; possible++)
				{
					if(g_gameGrid.grid[row][col].candidates[possible] != -1)
						result = true;
						
					g_gameGrid.grid[row][col].candidates[possible] = -1;
				}
			}
		}
		
		return result;
	}
	
	public static boolean SudokuUtils_ClearCandidatesByValue(int value)
	{
		boolean result = false;
		
		if(value < 1)
			return false;
	
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(g_gameGrid.grid[row][col].candidates[value - 1] != -1)
					result = true;
					
				g_gameGrid.grid[row][col].candidates[value - 1] = -1;
			}
		}
		
		return result;
	}
	
	public static boolean SudokuUtils_ChangeNumberColors(int value, int color)
	{
		boolean result = false;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(!SudokuUtils_ItemEditable(row, col))
					continue;
				
				if(g_gameGrid.grid[row][col].number != value)
					continue;
				
				g_gameGrid.grid[row][col].color = color;
				result = true;
			}
		}
		
		return result;
	}
	
	//void SudokuUtils_FillCandidates(GameGridType* gameGrid)
	public static void SudokuUtils_FillCandidates(GameGridType gameGrid)
	{
		SudokuHints_InitHintsGrid();
	
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				for(int value = 0; value < 9; value++)
				{
					if(g_gameHintsGrid.getCellAtX(x, y).hasPotentialValue(value + 1) == true)
						//(*gameGrid)[y][x].candidates[value] = 0;
						gameGrid.grid[y][x].candidates[value] = 0;
					else
						//(*gameGrid)[y][x].candidates[value] = -1;
						gameGrid.grid[y][x].candidates[value] = -1;
				}
			}
		}
	}
	
	//void SudokuUtils_GridToSolverString(GameGridType gameGrid, char* str)
	public static void SudokuUtils_GridToSolverString(GameGridType gameGrid, char[] str)
	{
		int index = 0;
		
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				//if((*gameGrid)[y][x].number != 0 && (*gameGrid)[y][x].color == 0)
				if(gameGrid.grid[y][x].number != 0 && gameGrid.grid[y][x].color == 0)
					str[index] = (char)('0' + gameGrid.grid[y][x].number);
				else
					str[index] = '-';
				
				index += 1;
			}
		}
		
		str[index] = 0;
	}
	
	public static void SudokuUtils_SolverStringToGrid(char[] str, GameGridType gameGrid)
	{
		int index = 0;
		
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(!((gameGrid.grid[y][x].number != 0) && (gameGrid.grid[y][x].color == 0)))
				{
					gameGrid.grid[y][x].number = str[index] - '0';
					gameGrid.grid[y][x].color = 1;
				}
				
				index += 1;
			}
		}
	}
	
	public static void SudokuUtils_GridToString(GameGridType gameGrid, char[] str)
	{
		int index = 0;
		
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(gameGrid.grid[y][x].number != 0 && gameGrid.grid[y][x].color != -1)
					str[index] = (char)('0' + gameGrid.grid[y][x].number);
				else
					str[index] = '-';
				
				index += 1;
			}
		}
		
		str[index] = 0;
	}
	
	public static int SudokuUtils_GetEmptyCellsCount(GameGridType gameGrid)
	{
		int count = 0;
		
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if((gameGrid.grid[y][x].number == 0) || (gameGrid.grid[y][x].color == -1))
				{
					count += 1;
				}
			}
		}
		
		return count;
	}
	
	//void SudokuUtils_Desymmetrize(GameGridType* gameGrid)
	public static void SudokuUtils_Desymmetrize(GameGridType gameGrid)
	{
		GameGridType tmpGrid = new GameGridType();
		
		//memmove(&tmpGrid, gameGrid, sizeof(GameGridType));
		tmpGrid.memmove(gameGrid);
	
		//Swap Horizontal 7,8,9 with Horizontal 4,5,6
		for(int b = 0; b < 3; b++)
		{
			for(int a = 0; a < 9; a++)
			{
				gameGrid.grid[6 + b][a] = tmpGrid.grid[3 + b][a];
				gameGrid.grid[3 + b][a] = tmpGrid.grid[6 + b][a];
			}
		}
		
		//memmove(&tmpGrid, gameGrid, sizeof(GameGridType));
		tmpGrid.memmove(gameGrid);
	
		for(int b = 0; b < 3; b++)
		{
			for(int a = 0; a < 9; a++)
			{
				gameGrid.grid[a][6 + b] = tmpGrid.grid[a][3 + b];
				gameGrid.grid[a][3 + b] = tmpGrid.grid[a][6 + b];
			}
		}
		
		//memmove(&tmpGrid, gameGrid, sizeof(GameGridType));
		tmpGrid.memmove(gameGrid);
	
		//Swap Row 5 and 6
		for(int a = 0; a < 9; a++)
		{
			gameGrid.grid[5][a] = tmpGrid.grid[4][a];
			gameGrid.grid[4][a] = tmpGrid.grid[5][a];
		}
	}
	
	//boolean SudokuUtils_MakeNewGame(int level, int* pNumber)
	public static boolean SudokuUtils_MakeNewGame(int level)
	{
		if(level < 0 || level > 5)
			return false;
		
		//srandom((unsigned)(mach_absolute_time() & 0xFFFFFFFF));
		//int game = random() % kSudokuDataItemsInGroup;
		int game = GB.arc4random() % SudokuData.kSudokuDataItemsInGroup;
		
		//*pNumber = game;
		GameScene.gameNumber = game;
		
		SudokuUtils_ResetBoard();
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				SudokuUtils.g_gameGrid.grid[row][col].number = SudokuData.SudokuData_GetBase(level, game, row, col);
				SudokuUtils.g_gameGrid.grid[row][col].color = 0;
			}
		}
	
		for(int step = 0; step < 40; step++)
		{
			//int rndValue1 = random() % 9 + 1;
			//int rndValue2 = random() % 9 + 1;
			int rndValue1 = GB.arc4random() % 9 + 1;
			int rndValue2 = GB.arc4random() % 9 + 1;
	
			for(int row = 0; row < 9; row++)
			{
				for(int col = 0; col < 9; col++)
				{
					if(SudokuUtils.g_gameGrid.grid[row][col].number == rndValue1)
					{
						SudokuUtils.g_gameGrid.grid[row][col].number = rndValue2;
					}
					else if(SudokuUtils.g_gameGrid.grid[row][col].number == rndValue2)
					{
						SudokuUtils.g_gameGrid.grid[row][col].number = rndValue1;
					}
				}
			}
		}
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(SudokuData.SudokuData_GetMask(level, game, row, col) == 0)
				{
					SudokuUtils.g_gameGrid.grid[row][col].number = 0;
				}
			}
		}
		
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void SudokuHints_Init()
	{
		//g_gameHintsAccumulator = [[NSMutableArray array] retain];
		g_gameHintsAccumulator = new Vector<Hints_Hint>();
		//g_gameHintsGrid = [[Hints_Grid alloc] init];
		g_gameHintsGrid = new Hints_Grid();
	}
	
	public static void SudokuHints_Free()
	{
		//[g_gameHintsAccumulator release];
		//[g_gameHintsGrid release];
	}
	
	public static void SudokuHints_InitHintsGrid()
	{
		SudokuUtils.g_gameHintsGrid.reset();
		g_gameHintsAccumulator.removeAllElements();
		
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				if(SudokuUtils_ItemNumberMode(y, x) && (g_gameGrid.grid[y][x].number != 0))
				{
					Hints_Cell cell = g_gameHintsGrid.getCellAtX(x, y);
					cell.setValueAndCancel(g_gameGrid.grid[y][x].number);
					cell.persist = (g_gameGrid.grid[y][x].color == 0);
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// history section
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// byte -> hex string
	public static String bytesToHex(byte[] data)
	{
		if (data==null) {
			return null;
		}
	  
		int len = data.length;
		String str = "";
	  
		for (int i=0; i<len; i++) {
			if ((data[i]&0xFF)<16) { 
				str = str + "0" + Integer.toHexString(data[i]&0xFF);
			} else {
				str = str + Integer.toHexString(data[i]&0xFF);
			}
		}
	  
		return str;
	 }
	 
	 // hex string -> byte
	 public static byte[] hexToBytes(String str) {
		 if (str==null) {
			 return null;
		 } else if (str.length() < 2) {
			 return null;
		 } else {
			 int len = str.length() / 2;
			 byte[] buffer = new byte[len];
			 for (int i=0; i<len; i++) {
				 buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
			 }
			 return buffer;
		 }
	 }
	 
	public static String SudokuHistory_GameGridToString()
	{
		//NSData* data = [NSData dataWithBytes:&g_gameGrid length:sizeof(g_gameGrid)];
		//return [data base64Encoding];
		byte[] tmpchar = new byte[891];

		int index = 0;
		
		for(int i=0; i<9; i++){
			for(int j=0; j< 9; j++){
				tmpchar[index] = (byte)g_gameGrid.grid[i][j].number;
				index += 1;
				tmpchar[index] = (byte)g_gameGrid.grid[i][j].color;
				index += 1;
				for(int k=0; k < 9; k ++)
				{
					tmpchar[index] = (byte)g_gameGrid.grid[i][j].candidates[k];
					index += 1;
				}
			}
		}
		
		return bytesToHex(tmpchar);
	}
	
	public static void SudokuHistory_StringToGameGrid(String str)
	{
		//NSData* data = [NSData dataWithBase64EncodedString:str];
		//[data getBytes:&g_gameGrid length:sizeof(g_gameGrid)];
		byte[] tmpchar = hexToBytes(str);
		
		int index = 0;
		
		for(int i = 0; i <9; i++) {
			for(int j = 0; j <9; j++) {
				g_gameGrid.grid[i][j].number = (int)tmpchar[index];
				index += 1;
				g_gameGrid.grid[i][j].color = (int)tmpchar[index];
				index += 1;
				for(int k=0; k < 9; k ++)
				{
					g_gameGrid.grid[i][j].candidates[k] = (int)tmpchar[index];
					index += 1;
				}
			}			
		}
	}
	
	public static void SudokuHistory_AddCurrentState()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		String curState = SudokuHistory_GameGridToString();
	
		MainActivity.history.add(curState);
	}
	
	public static boolean SudokuHistory_Undo()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(MainActivity.history.size() < 2)
			return false;
		
		//int pos = [appDelegate.history count] - 2;
		int pos = MainActivity.history.size() - 2;
		
		String newState = MainActivity.history.get(pos);
		SudokuHistory_StringToGameGrid(newState);
		//[appDelegate.history removeObjectsInRange:NSMakeRange(pos + 1, 1)];
		MainActivity.history.remove(pos+1);
		
		return true;
	}
	
	public static boolean SudokuHistory_RestoreAtIndex(int index)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		if(MainActivity.history.size() <= index)
			return true;
		
		String newState = MainActivity.history.get(index);
		SudokuHistory_StringToGameGrid(newState);
		
		return true;
	}
	
	public static void SudokuHistory_RestoreActive()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
	
		int pos = MainActivity.history.size() - 1;
	
		if(pos >= 0)
		{
			String newState = MainActivity.history.get(pos);
			SudokuHistory_StringToGameGrid(newState);
		}
	}
	
	public static void SudokuHistory_SetLastPos(int index)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
	
		if(index >= (MainActivity.history.size() - 1))
			return;
		
		int start = index + 1;
		int count = MainActivity.history.size() - start;
		
		//MainActivity.history. removeObjectsInRange:NSMakeRange(start, count)];
		for(int i = 0; i < count; i++)
		{
			MainActivity.history.remove(start + i);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// transform
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//void SudokuTransform_TranslateCoord(int mode, int x, int y, int* pNewX, int* pNewY)
	public static void SudokuTransform_TranslateCoord(int mode, int x, int y, int[] NewXY)
	{
	switch(mode)
		{
		case 0://  @"Rotate Clockwise"
			//*pNewX = 8 - y;
			//*pNewY = x;
			NewXY[0] = 8 - y;
			NewXY[1] = x;
			break;
				
		case 1://  @"Rotate Anti Clockwise"
			//*pNewX = y;
			//*pNewY = 8 - x;
			NewXY[0] = y;
			NewXY[1] = 8 - x;
			break;
				
		case 2://  @"Mirrior Vertical"
			//*pNewX = x;
			//*pNewY = 8 - y;
			NewXY[0] = x;
			NewXY[1] = 8 -y;
			break;
				
		case 3://  @"Mirror Horisontal"
			//*pNewX = 8 - x;
			//*pNewY = y;
			NewXY[0] = 8 - x;
			NewXY[1] = y;
			break;
		}
	}
	
	public static void SudokuTransform_TranslateBoard(int mode)
	{
		//SudokuGridItemType tmpGameGrid[9][9];
		GameGridType tmpGameGrid = new GameGridType();
		
		int newX, newY;
		int newXY[] = {0, 0};
		
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				SudokuTransform_TranslateCoord(mode, x, y, newXY); //newX, &newY);
				tmpGameGrid.grid[newXY[1]][newXY[0]] = g_gameGrid.grid[y][x];
			}
		}
		
		//memmove(&g_gameGrid, &tmpGameGrid, sizeof(tmpGameGrid));
		g_gameGrid.memmove(tmpGameGrid);
	}
}
