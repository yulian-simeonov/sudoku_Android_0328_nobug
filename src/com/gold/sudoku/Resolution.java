package com.gold.sudoku;

import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

public class Resolution {

	
	public static float SCALE_FACTOR;
	public static float IPADSCL;
	public static boolean ISIPHONE;
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
//	public static CGRect kPositionBarTop;
//	public static CGRect kPositionBarBottom;
//	public static CGRect kPositionBarMiddle;
//	public static CGRect kPositionBoard;
//	public static CGRect rcHistorySlider;
//	public static CGRect rcHistoryLabel;
	public static CGPoint kPositionBarTop;
	public static CGPoint kPositionBarBottom;
	
	public static CGRect kPositionBarMiddleRect;
	public static CGPoint kPositionBarMiddle;
	public static CGPoint kPositionBoard;
	public static CGPoint rcHistorySlider;
	public static CGPoint rcHistoryLabel;
	
	public static ButtonItemDef _buttonsMiddleBar[];
	public static ButtonItemDef _buttonsMiddleBar_progress[];
	public static ButtonItemDef _buttonsMiddleBar_History[];
	public static ButtonItemDef _buttonsMiddleBar_ststicHint[];
	public static ButtonItemDef _buttonsBottomBarCandidate[];
	public static ButtonItemDef _buttonsMiddleBarCandidate[];
	public static BoardDefType _boardsDef[];
	public static CGRect _numberButtons[];
	public static CGRect _colorButtons[];

	public static  float scaleX;
	public static float scaleY;
	

	static void Scale(org.cocos2d.types.CGRect rc, float scale, float xoff, float yoff)
	{
		rc.origin.x = (float) (rc.origin.x * scale + xoff);
		rc.origin.y = (float) (rc.origin.y * scale + yoff);
		rc.size.width *= scale;
		rc.size.height *= scale;
	}

	public static void InitResolution( )
	{
		scaleX = GB.g_fScaleX;
		scaleY = GB.g_fScaleY;
		
//		if (isIPad() || UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//		{
//			// The device is an iPad running iPhone 3.2 or later.
//			ISIPHONE = false;
//			SCALE_FACTOR = 1;
//			IPADSCL = 2;
//		}
//		else
		{
			// The device is an iPhone or iPod touch.
			ISIPHONE = true;
//			if([[UIScreen mainScreen] respondsToSelector:@selector(scale)])
//				SCALE_FACTOR = [[UIScreen mainScreen] scale];
//			else
			SCALE_FACTOR = 1;
			IPADSCL = 1;
		}
		
		
		kPositionBarTop = CGPoint.ccp(160, 23);// CGRect.make(0, 0, ,320,46)   
	    kPositionBarBottom = CGPoint.ccp(160, 436);    //CGRect.make(0, 412, 320, 48);
		kPositionBarMiddleRect = CGRect.make(0, 46, 320, 42);	// = CGRect.make(0, 46, 320, 46-5);
		kPositionBarMiddle = CGPoint.ccp(160, 66.5f);	// = CGRect.make(0, 46, 320, 46-5);
		kPositionBoard = CGPoint.ccp(160, 252);	// = CGRect.make(1, (47 + 46), 318, 318);
		rcHistorySlider = CGPoint.ccp(134.5f, 25);	// = CGRect.make(5, 15, 239, 20);
		rcHistoryLabel = CGPoint.ccp(196.5f, 20);	// = CGRect.make(144, 5, 249 - 144, 30);

//		if(ISIPHONE)
//		{
//			SCREEN_WIDTH = 320 * scaleX;
//			SCREEN_HEIGHT = 480 * scaleY;
//			kPositionBarTop = CGRect.make(0, 0, 320, 46);// CGRect.make(0, 0, , h)   
//  		    kPositionBarBottom = CGRect.make(0, 412, 320, 48);
//			kPositionBarMiddle = CGRect.make(0, 46, 320, 46-5);
//			kPositionBoard = CGRect.make(1, (47 + 46), 318, 318);
//			rcHistorySlider = CGRect.make(5, 15, 239, 20);
//			rcHistoryLabel = CGRect.make(144, 5, 249 - 144, 30);
//		}
//		else
//		{
//			SCREEN_WIDTH = 768 * scaleX;
//			SCREEN_HEIGHT = 1024 * scaleY;
//			kPositionBarTop = CGRect.make(0, 0, SCREEN_WIDTH, 46*2 * scaleY); //Make(0, 0, SCREEN_WIDTH, 46*2);
//			kPositionBarBottom = CGRect.make(0, SCREEN_HEIGHT-20* scaleX-48*2*scaleX, SCREEN_WIDTH, 48*2*scaleY);
//			kPositionBarMiddle = CGRect.make(0, 46*2*scaleY, SCREEN_WIDTH, 46*2*scaleY);
//			kPositionBoard = CGRect.make(64*scaleX+1*2*scaleX, (47 + 46)*2*scaleY + 40*scaleY, 318*2*scaleX, 318*2*scaleY);
//			rcHistorySlider = CGRect.make(5*2*scaleX, 15*2*scaleY, 239*2*scaleX+128*scaleX, 20*2*scaleY);
//			rcHistoryLabel = CGRect.make(192*2*scaleX, 5*2*scaleY, 249*2*scaleX - 144*2*scaleX, 30*2*scaleY);

//			//_buttonsTopBar[0].bounds = CGRectMake(13*2, 7*2, 69*2, 30*2);
//			//_buttonsTopBar[1].bounds = CGRectMake(294*2, 7*2, 82*2, 30*2);
//			Scale(_buttonsMiddleBar[0].bounds,IPADSCL,0,0);
//			Scale(_buttonsMiddleBar[1].bounds,IPADSCL,0,0);
//			Scale(_buttonsMiddleBar[2].bounds,IPADSCL,0,0);
//			this.Scale(_buttonsBottomBar[0].bounds,IPADSCL,64.0f,0);
//			
//			this.Scale(_buttonsBottomBar[1].bounds,IPADSCL,64,0);
//			this.Scale(_buttonsBottomBar[2].bounds,IPADSCL,64,0);
//			this.Scale(_buttonsBottomBar[3].bounds,IPADSCL,64,0);
//			this.Scale(_buttonsBottomBar[4].bounds,IPADSCL,64,0);
//			this.Scale(_buttonsBottomBar[5].bounds,IPADSCL,64,0);
//	        this.Scale(_buttonsBottomBar[6].bounds,IPADSCL,64,0);
//	        this.Scale(_buttonsBottomBar[7].bounds,IPADSCL,64,0);
//	        
//			Scale(_buttonsMiddleBar_History[0].bounds,IPADSCL,128,0);
//			Scale(_buttonsMiddleBar_History[1].bounds,IPADSCL,128,0);
//			Scale(_buttonsMiddleBar_progress[0].bounds,IPADSCL,0,0);
//			Scale(_buttonsMiddleBar_progress[1].bounds,IPADSCL,0,0);
//			Scale(_buttonsMiddleBar_progress[2].bounds,IPADSCL,0,0);
//			Scale(_buttonsMiddleBar_progress[3].bounds,IPADSCL,128,0);
//			Scale(_buttonsMiddleBar_ststicHint[0].bounds,IPADSCL,128,0);
//
//			for(int i=0; i<9; i++)
//				Scale(_buttonsMiddleBarCandidate[i].bounds,IPADSCL,64,0);
//			
//			for(int i=0; i<9; i++)
//				Scale(_buttonsBottomBarCandidate[i].bounds,IPADSCL,64,0);
//			
//			for(int i=0; i<9; i++)
//				Scale(_numberButtons[i],IPADSCL,0,0);
//			
//			for(int i=0; i<6; i++)
//				Scale(_colorButtons[i],IPADSCL,0,0);
//			
//			for(int i=0; i<9; i++)
//			{
//				_boardsDef[i].itemSizeX *= IPADSCL;
//				_boardsDef[i].itemSizeY *= IPADSCL;
//			
//				for(int j=0; j<9; j++)
//				{
//					_boardsDef[i].itemPosX[j] *= IPADSCL;
//					_boardsDef[i].itemPosY[j] *= IPADSCL;
//				}
//			}
//		}
	}

	public CGRect CGRectMakeScale(float x, float y, float width, float height, float scale)
	{
		return CGRect.make(x*scale,y*scale,width*scale,height*scale);
	}

	public CGRect CGRectScale(CGRect rc, float scale)
	{
		return CGRect.make(
			rc.origin.x * scale,
			rc.origin.y * scale,
			rc.size.width * scale,
			rc.size.height * scale);
	}

	

}
