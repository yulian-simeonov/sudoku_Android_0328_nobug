package com.gold.sudoku;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class GameBoardItemView extends CCSprite{

	float kAnimatingDeltaStep = 0.06f;
	float kAnimatingMin = 0.2f;
	float kAnimatingMax = 1.0f;
	float kAnimatingAlpha = 0.5f;
	float kAnimatingFrameTime = 0.13f;
                              
	float kGameBoardItemShowTime = 0.1f;
	float kGameBoardItemHideTime = 0.5f;
	float kGameBoardItemGrowScale = 1.15f;

	float kAnimatingMoveToTime = 1.0f;

	int row;
	int col;
	GameBoardView parentView;
	
	//NSTimer* animatingTimer;	
	double animatingColor;
	double animatingDirection;
	
	boolean drawBackground;
	GameGridType activeGameGrid;

	/////////////////////////////////////////////////////////////////////
	//- (id)initWithParent:(GameBoardView*)parent itemCol:(int)itemCol itemRow:(int)itemRow gameGrid:(GameGridType*)gameGrid
	//public static GameBoardItemView initWithParent(GameBoardView parent,int itemCol, int itemRow, GameGridType gameGrid)
	public GameBoardItemView(GameBoardView parent,int itemCol, int itemRow, GameGridType gameGrid)
	{
		//CCSprite image = skinManager.getBoardPartWithCol(col, row, -1.0f);
		super(MainActivity.utils_GetSkinManager().getBoardPartWithCol(itemCol, itemRow, -1.0f).getTexture(),
				MainActivity.utils_GetSkinManager().getBoardPartWithCol(itemCol, itemRow, -1.0f).getTextureRect());

		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		
		CGRect itemBounds = CGRect.make(boardDef.itemPosX[itemCol], boardDef.itemPosY[itemRow], boardDef.itemSizeX, boardDef.itemSizeY);
		//itemBounds = CGRectInset(itemBounds, -1, -1);
		//self = [super initWithFrame:itemBounds];

		//self.userInteractionEnabled = NO;
		
		parentView = parent;
		row = itemRow;
		col = itemCol;

		//backgroundColor = [UIColor clearColor];
		drawBackground = true;
		//if(gameGrid == nil || gameGrid == NULL)
		if(gameGrid == null)
			 activeGameGrid = SudokuUtils.g_gameGrid;
		else
			activeGameGrid = gameGrid;

		//mycode
		CGPoint pos = CGPoint.ccp(boardDef.itemPosX[itemCol] + boardDef.itemSizeX/2, boardDef.itemPosY[itemRow] + boardDef.itemSizeY/2);
		setPosition(pos.x, 318-pos.y);
		
	}

	//- (void)drawRect:(CGRect)rect 
	public void _drawRect() 
	{
		//		[super drawRect:rect];
		//		SkinManager* skinManager = utils_GetSkinManager();
		//		UIImage* image = [skinManager getBoardPartWithCol:col row:row inset:-1.0];
		
		//		if(drawBackground)
		//			[image drawInRect:self.bounds];
//		parentView.addChild(this, 7);
		
//		if(animatingTimer)
//		{
//			CGContextRef context;
//			CGRect animatingBounds = CGRectInset(self.bounds, 2, 2);
//			
//			context = UIGraphicsGetCurrentContext();
//
//			CGContextSaveGState(context);
//			CGContextSetRGBFillColor(context, animatingColor, animatingColor, animatingColor, kAnimatingAlpha);
//			CGContextFillRect(context, animatingBounds);
//			CGContextRestoreGState(context);
//		}
		
		//DrawGameBoardItem(self.bounds, &(*activeGameGrid)[row][col]);
		CGRect rect = this.getTextureRect();
		rect.origin.x = 0;
		rect.origin.y = 0;
		GameBoardUtils.DrawGameBoardItem(this, rect, activeGameGrid.grid[row][col]);
	}	

//	- (void)dealloc 
//	{
//		[parentView release];
//		[animatingTimer	release];
//		
//	    [super dealloc];
//	}

	public void add()
	{
		parentView.addChild(this, 7);
		
//		[UIView beginAnimations:nil context:nil];
//		[UIView setAnimationDuration:kGameBoardItemShowTime];
//		[UIView setAnimationBeginsFromCurrentState:YES];
//
//		double dx = 0.0;
//		double dy = 0.0;
//		double delta = (self.bounds.size.width*(kGameBoardItemGrowScale - 1.0)) / (2.0*kGameBoardItemGrowScale);
//		
//		if(col == 0) dx = delta;
//		if(col == 8) dx = -delta;
//		if(row == 0) dy = delta;
//		if(row == 8) dy = -delta;
//		
//		self.transform = CGAffineTransformMakeScale(kGameBoardItemGrowScale, kGameBoardItemGrowScale);
//		self.transform = CGAffineTransformTranslate(self.transform, dx, dy);
//		
//		[UIView commitAnimations];
	}

	//public void hideAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void hideAnimationDidStop()
	{
		//removeFromSuperview;
		this.removeFromParentAndCleanup(true);
	}

	public void remove()
	{
		//if(animatingTimer)
		//	[animatingTimer invalidate];
		
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:kGameBoardItemHideTime];
		//[UIView setAnimationBeginsFromCurrentState:YES];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(hideAnimationDidStop:finished:context:)];
		//hideAnimationDidStop();		
		//self.transform = CGAffineTransformMakeScale(1, 1);
		hideAnimationDidStop();
		//[UIView commitAnimations];
	}

	//- (void)onAnimatingTimer:(NSTimer*)timer
	public void onAnimatingTimer()
	{
//		if(animatingColor >= kAnimatingMax)
//			animatingDirection = -kAnimatingDeltaStep;
//
//		if(animatingColor <= kAnimatingMin)
//			animatingDirection = kAnimatingDeltaStep;
//
//		animatingColor += animatingDirection;
//		
		//[self setNeedsDisplay];
		this._drawRect();
	}

	public void beginAnimating()
	{
//		self.animatingTimer = [NSTimer timerWithTimeInterval:kAnimatingFrameTime target:self selector:@selector(onAnimatingTimer:) userInfo:nil repeats:YES];
//		[[NSRunLoop currentRunLoop] addTimer:animatingTimer forMode:NSDefaultRunLoopMode];
		onAnimatingTimer();
	}

	public void animateMoveToX(int x, int y, CCSprite parent, String animateStopSel)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		
//		[UIView beginAnimations:nil context:self];
//		[UIView setAnimationDuration:kAnimatingMoveToTime];
//		[UIView setAnimationBeginsFromCurrentState:YES];
//		[UIView setAnimationDelegate:parentView];
//		[UIView setAnimationDidStopSelector:animateStopSel];
		
//		CGRect itemBounds = CGRect.make(boardDef.itemPosX[x], boardDef.itemPosY[y], boardDef.itemSizeX, boardDef.itemSizeY);
		CGPoint itemPos = CGPoint.ccp(boardDef.itemPosX[x] + boardDef.itemSizeX/2, 318 - boardDef.itemPosY[y] + boardDef.itemSizeY/2);
//		itemBounds = CGRectInset(itemBounds, -1, -1);
//		
//		self.frame = itemBounds;
//		
//		[UIView commitAnimations];
		
		CCMoveTo move = CCMoveTo.action(1.0f, itemPos);
		this.runAction(CCSequence.actions(
				move,
				CCCallFunc.action(parent, animateStopSel)));
	}
}
