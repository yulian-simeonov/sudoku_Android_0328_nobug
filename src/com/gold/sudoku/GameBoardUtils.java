package com.gold.sudoku;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import com.gold.sudoku.GameGridType.SudokuGridItemType;

public class GameBoardUtils {
	
	//void DrawImageCentered2(CGRect bounds, UIImage* image, double scl)
	public static void DrawImageCentered2(CCSprite parent, CGRect bounds, CCSprite image, double scl)
	{
//		CGRect imageBounds;
		
//		imageBounds.origin.x = bounds.origin.x + ceil((bounds.size.width - image.size.width*scl) / 2);
//		imageBounds.origin.y = bounds.origin.y + ceil((bounds.size.height - image.size.height*scl) / 2);
//		imageBounds.size.width = image.size.width*scl;
//		imageBounds.size.height = image.size.height*scl;
	
		CGPoint pos;
		pos = CGPoint.ccp(bounds.origin.x + bounds.size.width/2, bounds.origin.y + bounds.size.height/2 );
		//mycode
		CCSprite node = CCSprite.sprite(image.getTexture(), image.getTextureRect());
		node.setPosition(pos.x, parent.getContentSize().height - pos.y);
		parent.addChild(node, 3);
	}

	//void DrawImageCentered(CGRect bounds, UIImage* image)
	public static void DrawImageCentered(CCSprite parent, CGRect bounds, CCSprite image)
	{
		DrawImageCentered2(parent, bounds, image, 1);
	}

	public static void DrawGameBoardItem(CCSprite parent, CGRect bounds, SudokuGridItemType item)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		if(item.number == 0|| (item.color == -1))
		{
			CGRect itemBounds = CGRect.make(0, 0, SkinManager.kNumberPossibleImageSizeX, SkinManager.kNumberPossibleImageSizeY);
			
			//bounds = CGRectInset(bounds, 1, 1);

			int cx = (int)Math.ceil(bounds.size.width / 3.0);
			int cy = (int)Math.ceil(bounds.size.height / 3.0);
			
			for(int index = 0; index < 9; index++)
			{
				if((item.candidates[index] < 0) || (item.candidates[index] > 1))
					continue;
				
				itemBounds.origin.x = bounds.origin.x + cx*(index%3);
				itemBounds.origin.y = bounds.origin.y + cy*(index/3);
				
				//CCSprite image = skinManager.getCandidateImageWithValue((index + 1), item.candidates[index]);
				CCSprite image = skinManager.getCandidateImageWithValue((index + 1), 0);
				DrawImageCentered2(parent, itemBounds, image, 1/Resolution.SCALE_FACTOR);
			}
		}
		else
		{
			CCSprite image = skinManager.getNumberImageWithValue(item.number, item.color, false);
			DrawImageCentered(parent, bounds, image);
		}
	}

//	#define kRegionLineWidth	4.0
//	#define kInvalidCellLineWidth	2

	public static void DrawCandidate(int x, int y, int value, int color)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		CGRect bounds;
		
		bounds = CGRect.make(boardDef.itemPosX[x], boardDef.itemPosY[y], boardDef.itemSizeX, boardDef.itemSizeY);	
//		bounds = CGRectInset(bounds, 1, 1);
		int cx = (int)Math.ceil(bounds.size.width / 3.0);
		int cy = (int)Math.ceil(bounds.size.height / 3.0);

		CGRect itemBounds = CGRect.make(0, 0, SkinManager.kNumberPossibleImageSizeX, SkinManager.kNumberPossibleImageSizeY);	
		itemBounds.origin.x = bounds.origin.x + cx*((value - 1)%3);
		itemBounds.origin.y = bounds.origin.y + cy*((value - 1)/3);
		
		//UIImage* image = [skinManager getCandidateImageWithValue:value color:color];
		CCSprite image = skinManager.getCandidateImageWithValue(value, color);
		//DrawImageCentered2(itemBounds, image, 1/Resolution.SCALE_FACTOR);
		DrawImageCentered2(GameScene.boardView, itemBounds, image, 1/Resolution.SCALE_FACTOR);
	}

	public static void HighlightCell(int x, int y, int color)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		CGRect bounds;
		
		bounds = CGRect.make(boardDef.itemPosX[x], boardDef.itemPosY[y], boardDef.itemSizeX, boardDef.itemSizeY);	
		
		//CGContextRef context = UIGraphicsGetCurrentContext();
		//CGContextSaveGState(context);
		CCSprite bgSprite;

		if(color == 0)
		{
			//CGContextSetRGBFillColor(context, 1.0, 0.0, 0.0, 0.5);
			bgSprite = CCSprite.sprite("cell_red.png");
		}
		else if(color == 1)
		{
			//CGContextSetRGBFillColor(context, 0.0, 1.0, 0.0, 0.5);
			bgSprite = CCSprite.sprite("cell_green.png");
		}
		else if(color == 2)
		{
			//CGContextSetRGBFillColor(context, 0.0, 0.0, 1.0, 0.5);
			bgSprite = CCSprite.sprite("cell_blue.png");
		}
		else
		{
			bgSprite = CCSprite.sprite("cell_blue.png");
		}
					
		//CGContextFillRect(context, bounds);
		//CGContextRestoreGState(context);
		CGPoint pos = CGPoint.ccp(bounds.origin.x + bounds.size.width/2, 318 - (bounds.origin.y + bounds.size.height/2));
		bgSprite.setPosition(pos);		
		GameScene.boardView.addChild(bgSprite, 2);
	}

	public static void HighlightWrongCell(int x, int y)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		CGRect bounds;
		
		bounds = CGRect.make(boardDef.itemPosX[x], boardDef.itemPosY[y], boardDef.itemSizeX, boardDef.itemSizeY);	
				
		//CGContextRef context = UIGraphicsGetCurrentContext();
		//CGContextSaveGState(context);
		
		//CGContextSetRGBFillColor(context, 1.0, 0.0, 0.0, 0.5);
		//CGContextFillRect(context, bounds);
		CCSprite bgSprite = CCSprite.sprite("cell_red.png");
		
		//CGContextSetLineWidth(context, 1);
		//CGContextSetRGBStrokeColor(context, 0.0, 0.0, 0.0, 1.0);
		CCSprite lineSprite = CCSprite.sprite("cell_block.png");
		
		//CGContextMoveToPoint(context, bounds.origin.x, bounds.origin.y);
		//CGContextAddLineToPoint(context, bounds.origin.x + bounds.size.width, bounds.origin.y + bounds.size.height);
		//CGContextStrokePath(context);
		
		//CGContextMoveToPoint(context, bounds.origin.x + bounds.size.width, bounds.origin.y);
		//CGContextAddLineToPoint(context, bounds.origin.x, bounds.origin.y + bounds.size.height);
		//CGContextStrokePath(context);
		CGPoint pos = CGPoint.ccp(bounds.origin.x + bounds.size.width/2, 318 - (bounds.origin.y + bounds.size.height/2));
		bgSprite.setPosition(pos);
		lineSprite.setPosition(pos);

		GameScene.boardView.addChild(bgSprite, 2);
		GameScene.boardView.addChild(lineSprite, 3);
		//CGContextRestoreGState(context);
	}


	//void DrawRegion(Hints_Region* region)
	public static void DrawRegion(Hints_Region region)
	{
		CGPoint topLeft;
		CGPoint bottomRight;
		Hints_Cell topLeftCell = null;
		Hints_Cell bottomRightCell = null;

		topLeftCell = region.getCell(0);
		bottomRightCell = region.getCell(8);

		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		
		topLeft = CGPoint.make(boardDef.itemPosX[topLeftCell.getX()], boardDef.itemPosY[topLeftCell.getY()]);
		bottomRight = CGPoint.make(boardDef.itemPosX[bottomRightCell.getX()] + boardDef.itemSizeX, boardDef.itemPosY[bottomRightCell.getY()] + boardDef.itemSizeY);
		
		//CGContextRef context = UIGraphicsGetCurrentContext();
		//CGContextSaveGState(context);
		
		// Set the line width so that the join is visible
		//CGContextSetLineWidth(context, kRegionLineWidth);
		CCSprite vLineSprite;
		CCSprite hLineSprite;
		
		switch(region.getType())
		{
		case _RegionType.kRegionRow:
			//CGContextSetRGBStrokeColor(context, 0.0, 0.0, 1.0, 0.7);
			vLineSprite = CCSprite.sprite("line_blue_v.png");
			hLineSprite = CCSprite.sprite("line_blue_h.png");
			break;
				
		case _RegionType.kRegionColumn:
			//CGContextSetRGBStrokeColor(context, 0.0, 1.0, 0.0, 0.7);
			vLineSprite = CCSprite.sprite("line_green_v.png");
			hLineSprite = CCSprite.sprite("line_green_h.png");
			break;

		case _RegionType.kRegionBlock:
			//CGContextSetRGBStrokeColor(context, 1.0, 0.0, 1.0, 0.7);
			vLineSprite = CCSprite.sprite("line_orange_v.png");
			hLineSprite = CCSprite.sprite("line_orange_h.png");
			break;
				
		default:
			//CGContextSetRGBStrokeColor(context, 0.0, 0.0, 1.0, 0.7);
			vLineSprite = CCSprite.sprite("line_blue_v.png");
			hLineSprite = CCSprite.sprite("line_blue_h.png");
			break;
		};
		
		// Line join miter, default
		//CGContextSetLineJoin(context, kCGLineJoinRound);
		
		//CGContextMoveToPoint(context, topLeft.x, topLeft.y);
		//CGContextAddLineToPoint(context, bottomRight.x, topLeft.y);
		//CGContextAddLineToPoint(context, bottomRight.x, bottomRight.y);
		//CGContextAddLineToPoint(context, topLeft.x, bottomRight.y);
		//CGContextAddLineToPoint(context, topLeft.x, topLeft.y);
		//CGContextStrokePath(context);
		float vSize = bottomRight.x - topLeft.x;
		float hSize = bottomRight.y - topLeft.y;
		float vScale = vSize/ boardDef.itemSizeX;
		float hScale = hSize / boardDef.itemSizeY;
				
		CGPoint posTop = CGPoint.ccp(topLeft.x + vSize/2, 318 - topLeft.y);
		CGPoint posBottom = CGPoint.ccp(topLeft.x + vSize/2, 318 - bottomRight.y);
		
		CGPoint posLeft = CGPoint.ccp(topLeft.x, 318-(topLeft.y + hSize/2));
		CGPoint posLight = CGPoint.ccp(bottomRight.x, 318- (topLeft.y + hSize/2));
		
		CCSprite topSprite = CCSprite.sprite(vLineSprite.getTexture());
		topSprite.setPosition(posTop);
		topSprite.setScaleX(vScale);
		
		CCSprite bottomSprite = CCSprite.sprite(vLineSprite.getTexture());
		bottomSprite.setPosition(posBottom);
		bottomSprite.setScaleX(vScale);

		CCSprite leftSprite = CCSprite.sprite(hLineSprite.getTexture());
		leftSprite.setPosition(posLeft);
		leftSprite.setScaleY(hScale);
		
		CCSprite rightSprite = CCSprite.sprite(hLineSprite.getTexture());
		rightSprite.setPosition(posLight);
		rightSprite.setScaleY(hScale);

		//CGContextRestoreGState(context);
		GameScene.boardView.addChild(topSprite, 2);
		GameScene.boardView.addChild(bottomSprite, 2);
		GameScene.boardView.addChild(leftSprite, 2);
		GameScene.boardView.addChild(rightSprite, 2);
		
	}

//	void DrawInvalidCell(int x, int y)
//	{
//		SkinManager skinManager = MainActivity.utils_GetSkinManager();
//		BoardDefType boardDef = skinManager.getBoardDef();
//		CGPoint topLeft = CGPoint.make(boardDef.itemPosX[x], boardDef.itemPosY[y]);
//		CGPoint bottomRight = CGPoint.make(boardDef.itemPosX[x] + boardDef.itemSizeX, boardDef.itemPosY[y] + boardDef.itemSizeY);
//
//		HighlightCell(x, y, 2);
//		
//		CGContextRef context = UIGraphicsGetCurrentContext();
//		CGContextSaveGState(context);
//
//		CGContextSetLineWidth(context, kInvalidCellLineWidth);
//		CGContextSetRGBStrokeColor(context, 0.0, 1.0, 0.0, 0.0);
//		CGContextSetLineJoin(context, kCGLineJoinRound);
//		
//		CGContextMoveToPoint(context, topLeft.x, topLeft.y);
//		CGContextAddLineToPoint(context, bottomRight.x, bottomRight.y);
//		CGContextStrokePath(context);
//
//		CGContextMoveToPoint(context, bottomRight.x, topLeft.y);
//		CGContextAddLineToPoint(context, topLeft.x, bottomRight.y);
//		CGContextStrokePath(context);
//		
//		CGContextRestoreGState(context);
//	}

	public static void DrawDirectHint(Hints_DirectHint hint)
	{
		//NSMutableArray* regions = [hint getRegions];
		NSMutableArray regions = (NSMutableArray)hint.getRegions();
		
		if(regions != null)
		{
			for(int regionIndex = 0; regionIndex < regions.size(); regionIndex++)
				DrawRegion((Hints_Region)regions.get(regionIndex));
		}
		
		if(hint.cell != null && hint.value != 0)
		{
			HighlightCell(hint.cell.getX(), hint.cell.getY(), 2);
			DrawCandidate(hint.cell.getX(), hint.cell.getY(), hint.value, 2);
		}
	}

	//void DrawMapedPotentials(NSMutableDictionary* potentials, int color)
	public static  void DrawMapedPotentials(Map potentials, int color)
	{
		//NSArray* keys = [potentials allKeys];
		Set keys = potentials.entrySet();

//		for(int keyIndex = 0; keyIndex < [keys count]; keyIndex++)
//		{
//			Hints_Cell cell = keys. objectAtIndex:keyIndex];
//			Hints_BitSet* bitSet = [potentials objectForKey:cell];
//			
//			for(int value = 1; value <= 9; value++)
//			{
//				if([bitSet get:value] == YES)
//				{
//					DrawCandidate([cell getX], [cell getY], value, color);
//				}
//			}
//		}

		Iterator it = keys.iterator();
		while(it.hasNext()) {
		    // key=value Map.Entry
		    Map.Entry m=(Map.Entry)it.next();
		    Hints_Cell cell = (Hints_Cell)m.getKey(); // getKey
		    Hints_BitSet bitSet = (Hints_BitSet)m.getValue(); // getValue
		    
			for(int value = 1; value <= 9; value++)
			{
				if(bitSet.get(value) == true)
				{
					DrawCandidate(cell.getX(), cell.getY(), value, color);
				}
			}
		}
		
	}

	public static void DrawIndirectHint(Hints_IndirectHint hint)
	{
		//NSMutableArray* regions = [hint getRegions];
		NSMutableArray regions = (NSMutableArray)hint.getRegions();
		
		if(regions != null)
		{
			for(int regionIndex = 0; regionIndex < regions.size(); regionIndex++)
				DrawRegion((Hints_Region)regions.get(regionIndex));
		}
		
		NSMutableArray selectedSells = (NSMutableArray)hint.getSelectedCells();
		if((selectedSells != null) && (selectedSells.size() != 0))
		{
			for(int index = 0; index < selectedSells.size(); index++)
			{
				Hints_Cell cell = (Hints_Cell)selectedSells.get(index);
				
				HighlightCell(cell.getX(), cell.getY(), 2);
			}
		}

		//NSMutableDictionary* greenPotentials = [hint getGreenPotentials:0];
		Map greenPotentials = hint.getGreenPotentials(0);
		if((greenPotentials != null) && (greenPotentials.size() != 0))
		{
			DrawMapedPotentials(greenPotentials, 2);
		}
		
		//NSMutableDictionary* redPotentials = [hint getRedPotentials:0];
		Map redPotentials = hint.getRedPotentials(0);
		if((redPotentials != null) && (redPotentials.size() != 0))
		{
			DrawMapedPotentials(redPotentials, 1);
		}
		
		//NSMutableDictionary* orangePotentials = [hint getOrangePotentials:0];
		Map orangePotentials = hint.getOrangePotentials(0);
		if((orangePotentials != null) && (orangePotentials.size() != 0))
		{
			DrawMapedPotentials(orangePotentials, 3);
		}
		
		//NSMutableDictionary* grayPotentials = [hint getGrayPotentials:0];
		Map grayPotentials = hint.getGrayPotentials(0);
		if((grayPotentials != null) && (grayPotentials.size() != 0))
		{
			DrawMapedPotentials(grayPotentials, 0);
		}
	}

	public static void DrawSuggestCellValuetHint(Hints_SuggestedCellValueHint hint)
	{
		HighlightCell(hint.cell.getX(), hint.cell.getY(), 2);	
		
		if(hint.isValue)
		{
			SkinManager skinManager = MainActivity.utils_GetSkinManager();
			BoardDefType boardDef = skinManager.getBoardDef();
			CGRect bounds;
			
			bounds = CGRect.make(boardDef.itemPosX[(hint.cell.getX())], boardDef.itemPosY[(hint.cell.getY())], boardDef.itemSizeX, boardDef.itemSizeY);	
			
			//UIImage* image = [skinManager getNumberImageWithValue:hint.value color:0 selected:NO];
			CCSprite image = skinManager.getNumberImageWithValue(hint.value, 0, false);
			DrawImageCentered(GameScene.boardView, bounds, image);
		}
	}

	public static void DrawValidInvalidValueHint(Hints_ValidInvalidValueHint hint)
	{
		for(int index = 0; index < hint.cells.size(); index++)
		{
			Hints_Cell cell = (Hints_Cell)hint.cells.get(index);
			
			if(hint.isValid)
				HighlightCell(cell.getX(), cell.getY(), 1);
			else
				HighlightCell(cell.getX(), cell.getY(), 0);
		}
	}

	public static void DrawWrongValuesHint(Hints_WrongValuesHint hint)
	{
		int index;
		
		for(index = 0; index < hint.cells.size(); index++)
		{
			Hints_Cell cell = (Hints_Cell)hint.cells.get(index);

			HighlightWrongCell(cell.getX(), cell.getY());
		}

		for(index = 0; index < hint.regions.size(); index++)
		{
			Hints_Region region = (Hints_Region)hint.regions.get(index);
			
			DrawRegion(region);
		}
	}

	public static void DrawHint(Hints_Hint hint)
	{
		//if([hint isKindOfClass:[Hints_PotentialsHint class]])
		if(hint.getClass().equals(Hints_PotentialsHint.class))
		{
			Hints_PotentialsHint potentialsHint = (Hints_PotentialsHint)hint;
			DrawMapedPotentials(potentialsHint.potentials, 1);
		}
		//else if([hint isKindOfClass:[Hints_WrongValuesHint class]])
		else if(hint.getClass().equals(Hints_WrongValuesHint.class))
		{
			DrawWrongValuesHint((Hints_WrongValuesHint)hint);
		}
		//else if([hint isKindOfClass:[Hints_SuggestedCellValueHint class]])
		else if(hint.getClass().equals(Hints_SuggestedCellValueHint.class))
		{
			DrawSuggestCellValuetHint((Hints_SuggestedCellValueHint)hint);
		}
		//else if([hint isKindOfClass:[Hints_ValidInvalidValueHint class]])
		else if(hint.getClass().equals(Hints_ValidInvalidValueHint.class))
		{
			DrawValidInvalidValueHint((Hints_ValidInvalidValueHint)hint);
		}
		//else if([hint isKindOfClass:[Hints_IndirectHint class]])
		else if(hint.getClass().equals( Hints_IndirectHint.class)
				|| hint.getClass().equals( Hints_LockingHint.class)
				|| hint.getClass().equals( Hints_XYWingHint.class)
				)
		{
			DrawIndirectHint((Hints_IndirectHint)hint);
		}
		//else if([hint isKindOfClass:[Hints_DirectHint class]])
		else if(hint.getClass().equals(Hints_DirectHint.class) 	 ||
			hint.getClass().equals(Hints_HiddenSingleHint.class) ||
			hint.getClass().equals(Hints_NakedSingleHint.class) )
		{
			DrawDirectHint((Hints_DirectHint)hint);
		}
	}

	public static String formatGameTime(int time)
	{
		int seconds = time % 60;
		time /= 60;
		int minutes = time % 60;
		time /= 60;
		int hours = time % 24;
		time /= 24;
		int days = time;
		
		String ssec = null, smin = null, shour = null, sday = null;
		days %= 10;
		sday = new String().format("%d", days);

		if(hours < 10)
			shour = new String().format("0%d", hours);
		else
			shour = new String().format("%d", hours);
			
		if(minutes < 10)
			smin = new String().format("0%d", minutes);
		else
			smin = new String().format("%d", minutes);
		
		if(seconds < 10)
			ssec = new String().format("0%d", seconds);
		else
			ssec = new String().format("%d", seconds);
			
		
//		return new String().format("%.1d:%.2d:%.2d:%.2d", days, hours, minutes, seconds);
		return new String().format("%s:%s:%s:%s", sday, shour, smin, ssec);
	}

}
