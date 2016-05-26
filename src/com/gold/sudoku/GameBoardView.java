package com.gold.sudoku;

import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.gold.sudoku.GameGridType.SudokuGridItemType;

public class GameBoardView extends CCSprite{

	GameBoardItemView activeSelection = null;
	GameScene parentView;
	
	SudokuGridItemType itemBackup;
	
	boolean activeMode = false;
	int activeColor;
	
	int hintIndex;
	
	boolean isTransformAnimationInProgress;
	GameBoardItemView[][] transformTmpItems = new GameBoardItemView[9][9]; //9*9
	int processedCount;
	int translateMode;
	
	boolean showCandidatesMode = false;


	//public GameBoardView initWithFrame(CGPoint pos)
	public GameBoardView(CGPoint pos)
	{
		super(MainActivity.utils_GetImage(ImageType.kImageBoard).getTexture());
		setPosition(GB.getX(pos.x), GB.getY(pos.y));
		setScaleX(GB.g_fScaleX);
		setScaleY(GB.g_fScaleY);
//		self = [super initWithFrame:frame];
//		self.backgroundColor = [UIColor clearColor];
		hintIndex = -1;
		activeMode = true;
//		
	}

	//public void drawRect:(CGRect)rect 
	public void drawRect() 
	{
		//UIImage* image = utils_GetImage(kImageBoard);
		//if(image)
		//	[image drawInRect:self.bounds];
		CCSprite image = MainActivity.utils_GetImage(ImageType.kImageBoard);
		if(image != null)
			this.setTexture(image.getTexture());
		
		this.removeAllChildren(true);
		
		if(isTransformAnimationInProgress)
			return;
		
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		
		CGRect itemBounds;
		
		for(int row = 0; row < 9; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				itemBounds = CGRect.make(boardDef.itemPosX[col], boardDef.itemPosY[row], boardDef.itemSizeX, boardDef.itemSizeY);
				GameBoardUtils.DrawGameBoardItem(this, itemBounds, SudokuUtils.g_gameGrid.grid[row][col]);
			}
		}
		
		if(showCandidatesMode)
		{
			if(activeSelection != null)
			{
				Hints_Region region;
				
				activeSelection.add();
				activeSelection._drawRect();
				
				region = SudokuUtils.g_gameHintsGrid.getRowAtX(activeSelection.col,  activeSelection.row);
				//GameBoardUtils.DrawRegion(region);
				GameBoardUtils.DrawRegion(region);
				
				region = SudokuUtils.g_gameHintsGrid.getColumnAtX(activeSelection.col, activeSelection.row);
				//GameBoardUtils.DrawRegion(region);
				GameBoardUtils.DrawRegion(region);
				
				region = SudokuUtils.g_gameHintsGrid.getBlockAtX(activeSelection.col, activeSelection.row);
				//GameBoardUtils.DrawRegion(region);
				GameBoardUtils.DrawRegion(region);
			}
		}
		else
			drawHints();
	}
	
	public void drawHints()
	{
		if((hintIndex == -1) || (SudokuUtils.g_gameHintsAccumulator.size() <= hintIndex) || (SudokuUtils.g_gameHintsAccumulator.size() == 0))
			return;

		Hints_Hint hint = SudokuUtils.g_gameHintsAccumulator.get(hintIndex);
		
		GameBoardUtils.DrawHint(hint);
	}


//- (void)dealloc 
//{
//	[super dealloc];
//}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//- (BOOL)hitTest:(CGPoint)hitPoint colRef:(int*)pCol rowRef:(int*)pRow
	//{
	//	if(!CGRectContainsPoint(self.bounds, hitPoint))
	//		return false;
	//	
	//	*pCol = (int)(hitPoint.x/(self.bounds.size.width / 9.0));
	//	*pRow = (int)(hitPoint.y/(self.bounds.size.height / 9.0));
	//	
	//	return true;
	//}
	
	public void clearSelection(boolean restoreBackup)
	{
		if(activeSelection == null)
			return;
		
		if(restoreBackup)
		{
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col] = itemBackup;
		}
		else if(!activeMode)
		{
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col].color = itemBackup.color;
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col].number = itemBackup.number;
		}
		
//		setNeedsDisplay();
		drawRect();
		
		activeSelection.remove();
		activeSelection = null;
	}
	
	public void updateSelectionWithPoint(CGPoint point) 
	{
		int col, row;
		//boolean result = hitTest(point colRef:&col rowRef:&row);
		//if(!result)
		//	return;
		
        CGRect boardRect = CGRect.make(this.getPosition().x - (this.getBoundingBox().size.width/2),
        		this.getPosition().y - (this.getBoundingBox().size.height/2),
                this.getBoundingBox().size.width,
                this.getBoundingBox().size.height);

		if(!CGRect.containsPoint(boardRect, point))
			return;

		//mycode
		float orgX = boardRect.origin.x;
		float orgY = (this.getPosition().y - (this.getBoundingBox().size.height/2));
		CGPoint offPoint = CGPoint.ccp(point.x - orgX, point.y - orgY);
			
		col = (int)(offPoint.x/(boardRect.size.width / 9.0));
		row = 8 - (int)(offPoint.y/(boardRect.size.height / 9.0));

		
		updateSelectionWithRow(row, col);
		drawRect();
	}

	public void updateSelectionWithRow(int row, int col)
	{
		if(!SudokuUtils.SudokuUtils_ItemEditable(row, col) && (parentView.activeMode != GC.kGameModeEnterOwnSudoku))
			return;
		
		if(activeSelection != null && (activeSelection.col == col) && (activeSelection.row == row))
			return;
		
		if(showCandidatesMode)
		{
			if(SudokuUtils.SudokuUtils_ItemNumberMode(row, col) && (SudokuUtils.g_gameGrid.grid[row][col].number != 0))
				return;
		}
		
		if(activeMode == false && activeSelection != null)
		{
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col].number = itemBackup.number;
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col].color = itemBackup.color;
		}
		
		clearSelection(false);
		
		GameBoardItemView itemView;
		
		if(showCandidatesMode)
		{
			//itemView = [[GameBoardItemView alloc] initWithParent:self itemCol:col itemRow:row gameGrid:&g_tmpGameGrid];
			itemView = new GameBoardItemView(this, col, row, SudokuUtils.g_tmpGameGrid);
			activeSelection = itemView;
			itemView.add(); 
			//[itemView release];
			
			return;
		}
		else
		{
			//itemView = [[GameBoardItemView alloc] initWithParent:self itemCol:col itemRow:row gameGrid:nil];
			itemView = new GameBoardItemView(this, col, row, null);
			activeSelection = itemView;
			itemView.add();
			//[itemView beginAnimating];
			itemView.beginAnimating();
			//[itemView release];
		}
		
		itemBackup = SudokuUtils.g_gameGrid.grid[row][col];
		
		/*	Fixup for entry mode
			activeMode = SudokuUtils_ItemNumberMode(row, col);
			
			boolean candidatesPresent = NO;
			for(int index = 0; index < 9; index++)
			{
				if(g_gameGrid[row][col].candidates[index] != -1)
					candidatesPresent = YES;
			}
			
			if((g_gameGrid[row][col].number == 0) && candidatesPresent)
				activeMode = NO;
		*/
		
		/*	
			if(parentView.activeMode != kGameModeEnterOwnSudoku)
			{
				if((itemBackup.color != -1) && (itemBackup.color != 0))
					activeColor = itemBackup.color;
			
				if(activeColor < 1 || activeColor > 6) 
					activeColor = 1;
			}
		*/
		if(parentView.activeMode != GC.kGameModeEnterOwnSudoku)
		{
			if(activeColor < 1 || activeColor > 6) 
				activeColor = 1;
		}
		else
			activeColor = 0;
		
		if(!activeMode)
		{
			SudokuUtils.g_gameGrid.grid[row][col].color = -1;
		}
		
		parentView.onBoardItemSelect();
	}

//- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
//{
//	CGPoint point = [[touches anyObject] locationInView:self];
//}
//
//- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event
//{
//	[self clearSelection:YES];
//}
//
//- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
//{
//	CGPoint point = [[touches anyObject] locationInView:self];
//	[self updateSelectionWithPoint:point];
//}
//
//- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
//{
//	SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
//	
//	if(appDelegate.prefUseFlyingKeypad && activeSelection)
//		return;
//	
//	CGPoint point = [[touches anyObject] locationInView:self];
//	[self updateSelectionWithPoint:point];
//	}	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void resetState()
	{
		clearSelection(false);
		showHintDone();
		showCandidatesMode = false;
//		setNeedsDisplay();
		drawRect();
	}
	
	public void restoreState()
	{
		clearSelection(true);
	}
	
	public void cancelStateToDefaults()
	{
		if(activeMode)
		{
			itemBackup.number = 0;
		}
		else
		{
			for(int index = 0; index < 9; index++)
				itemBackup.candidates[index] = -1;
		}
		
		clearSelection(true);
	}
	
	public void setState(boolean isCandidate)
	{
		//SudokuGridItemType* item = &g_gameGrid[activeSelection.row][activeSelection.col];
		//add by GOLD
		if(activeSelection == null)
			return;
		
		SudokuGridItemType item = SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col];	
		activeMode = isCandidate;
		
		if(isCandidate)
		{
			/*item->color = activeColor*/;
			
			item.color = itemBackup.color;
			if(item.color < 0)
				item.color = 1;
		}
		else
		{
			item.color = -1;
		}
	}
	
	public void setColor(int color)
	{
		activeColor	= color;
		
		//if(activeMode)
		if(activeMode && activeSelection!= null)
			SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col].color = activeColor;
	}
	
	public void setNumber(int number)
	{
		//if(!activeSelection)
		if(activeSelection == null)
			return;
		
		//SudokuGridItemType* item = &g_gameGrid[activeSelection.row][activeSelection.col];
		SudokuGridItemType item = SudokuUtils.g_gameGrid.grid[activeSelection.row][activeSelection.col];
		
		if(activeMode)
		{
			item.color = activeColor;
			item.number = number;
			
			itemBackup.color = activeColor;
			itemBackup.number = number;
		}
		else
		{
			if(item.candidates[number - 1] == -1)
				item.candidates[number - 1] = 0;
			else
				item.candidates[number - 1] = -1;
		}
		
		//[self setNeedsDisplay];
		drawRect();
		//[activeSelection setNeedsDisplay];
		activeSelection._drawRect();
	}
	
	public void acceptValue()
	{
		clearSelection(false);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void showHint(int index)
	{
		clearSelection(false);
		
		//userInteractionEnabled = false;
		parentView.setIsTouchEnabled(false);
		hintIndex = index;
//		[self setNeedsDisplay];
		drawRect();
	}

	public void showHintDone()
	{
		//userInteractionEnabled = true;
		parentView.setIsTouchEnabled(true);
	    CCTouchDispatcher.sharedDispatcher().setDispatchEvents(true);
		hintIndex = -1;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//-(void)transformAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void transformAnimationDidStop()
	{
//		processedCount += 1;
//		
//		if(processedCount >= 81)
		{
			isTransformAnimationInProgress = false;
			//userInteractionEnabled = YES;
			SudokuUtils.SudokuTransform_TranslateBoard(translateMode);
			SudokuUtils.SudokuHistory_AddCurrentState();
			//[self setNeedsDisplay];
			drawRect();
			
//			for(int x = 0; x < 9; x++)
//			{
//				for(int y = 0; y < 9; y++)
//				{
//					transformTmpItems[x][y].removeFromParentAndCleanup(true); // removeFromSuperview];
//					//[transformTmpItems[x][y] release];
//					transformTmpItems[x][y] = null;
//				}
//			}
		}
	}

	public void processTransformAnimation(int mode)
	{
		int x, y, newX, newY;
		int outXY[] = {0, 0};
	
		translateMode = mode;
		processedCount = 0;
//		isTransformAnimationInProgress = true;
		//userInteractionEnabled = false;
		//[self setNeedsDisplay];
	
//		for(x = 0; x < 9; x++)
//		{
//			for(y = 0; y < 9; y++)
//			{
//				//transformTmpItems[x][y] = [[GameBoardItemView alloc] initWithParent:self itemCol:x itemRow:y gameGrid:nil];
//				transformTmpItems[x][y] = GameBoardItemView.initWithParent(this, x, y, null);
//				//transformTmpItems[x][y].drawBackground = NO;
//				this.addChild(transformTmpItems[x][y]); //[self addSubview:transformTmpItems[x][y]];
//			}
//		}
	
//		for(x = 0; x < 9; x++)
//		{
//			for(y = 0; y < 9; y++)
//			{
//				//SudokuTransform_TranslateCoord(mode, x, y, &newX, &newY);
//				SudokuUtils.SudokuTransform_TranslateCoord(mode, x, y, outXY);
//				newX = outXY[0];
//				newY = outXY[1];
//				//transformTmpItems[x][y].animateMoveToX(newX, newY, animateStopSel:@selector(transformAnimationDidStop:finished:context:)];
//				transformTmpItems[x][y].animateMoveToX(newX, newY, this, "transformAnimationDidStop");
//			}
//		}
		transformAnimationDidStop();
	}

	void beginShowCandidates()
	{
		showCandidatesMode = true;
	}

}
