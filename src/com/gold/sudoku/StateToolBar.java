package com.gold.sudoku;

import java.util.Vector;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

import android.graphics.Bitmap;

public class StateToolBar extends ToolBarView {
	CCMenu m_pMenu;
	
	CCLabel timeLabel;
	CCLabel scoreLabel;
	//NSTimer* timer;

	ButtonItemDef _buttonsMiddleBar[] = 
	{
		//{1, {5, 5, 30, 30}, kIconControlTimer, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onMiddleBarTimer:"},
		new ButtonItemDef(1, CGPoint.ccp(20, 20), ImageType.kIconControlTimer, ImageType.kIconControlTimerSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onMiddleBarTimer"),
		//{5, {144, 5, 30, 30}, kIconControlYangYang, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onMiddleBarYangYang:"},
		new ButtonItemDef(5, CGPoint.ccp(159, 20), ImageType.kIconControlYangYang, ImageType.kIconControlYangYangSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onMiddleBarYangYang"),
		//{9,	{284, 5, 30, 30}, kIconControlShieldBlack, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onMiddleBarShield:"},
		new ButtonItemDef(9, CGPoint.ccp(299, 20), ImageType.kIconControlShieldBlack, ImageType.kIconControlShieldBlackSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onMiddleBarShield"),
	};
	
	//const int _buttonsMiddleBarCount = sizeof(_buttonsMiddleBar)/sizeof(ButtonItemDef);
	final int _buttonsMiddleBarCount = 3;
	

	StateToolBar(int type, CGPoint pos)
	{
		super(type, pos);
	}
	
	public void initStateBar()
	{
		loadButtons(_buttonsMiddleBar,  _buttonsMiddleBarCount);
		
		CCLabel label1, label2;
		
		//label = [[UILabel alloc] initWithFrame:CGRectMakeScale(39, 5, 98, 30, IPADSCL)];
		//label.font = [UIFont systemFontOfSize:20*IPADSCL];
		//label.backgroundColor = [UIColor clearColor];
		//label.textColor = [UIColor colorWithRed:0.7 green:0.7 blue:0.7 alpha:1.0];
		//label.shadowColor = [UIColor colorWithRed:0.3 green:0.3 blue:0.3 alpha:0.7];
		//label.shadowOffset = CGSizeMake(1.0, 1.0);
		//label.textAlignment = UITextAlignmentCenter;
		//label.text = @"0:00:00:00";
		label1 = CCLabel.makeLabel("0:00:00:00", CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 20);
		label1.setColor(ccColor3B.ccc3(158, 158, 158));
//		label1.setPosition(GB.getX(88), GB.getY(20));
		label1.setPosition(88, 20);
		timeLabel = label1;
		this._view.addChild(label1);
		//[label release];
		
		//label = [[UILabel alloc] initWithFrame:CGRectMakeScale(179, 5, 98, 30, IPADSCL)];
		//label.font = [UIFont systemFontOfSize:20*IPADSCL];
		//label.backgroundColor = [UIColor clearColor];
		//label.textColor = [UIColor colorWithRed:0.7 green:0.7 blue:0.7 alpha:1.0];
		//label.shadowColor = [UIColor colorWithRed:0.3 green:0.3 blue:0.3 alpha:0.7];
		//label.shadowOffset = CGSizeMake(1.0, 1.0);
		//label.textAlignment = UITextAlignmentCenter;
		//label.text = @"0000000";
		label2 = CCLabel.makeLabel("0000000", CGSize.make(480,50), TextAlignment.CENTER, "Marker Felt", 20);
		label2.setColor(ccColor3B.ccc3(158, 158, 158));
//		label2.setPosition(GB.getX(228), GB.getY(20));
		label2.setPosition(228, 20);
		scoreLabel = label2;
		//[self addSubview:label];
		this._view.addChild(label2);
		//[label release];
		
		updateState();
		
		//self.timer = [NSTimer timerWithTimeInterval:1.0 target:self selector:@selector(onTimer:) userInfo:nil repeats:YES];
		//[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
	}
	
//	- (void)dealloc
//	{
//		[timeLabel release];
//		[scoreLabel release];
//		
//		[super dealloc];
//	}
	
	public void updateState()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		timeLabel.setString(GameBoardUtils.formatGameTime(MainActivity.stateGameTime));
		String score = String.format("%d", MainActivity.stateGameScore);
		int len = score.length();
		if(len < 7)
		{
			for(int i = len; i < 7; i ++)
				score = "0" + score;  
		}
		scoreLabel.setString(score);
		
		
		int images[] = {
				ImageType.kIconControlShieldRed,
				ImageType.kIconControlShieldOrange,
				ImageType.kIconControlShieldGreen,
				ImageType.kIconControlShieldBlue,
				ImageType.kIconControlShieldPurple,
				ImageType.kIconControlShieldBlack
		};

		int selimages[] = {
				ImageType.kIconControlShieldRedSel,
				ImageType.kIconControlShieldOrangeSel,
				ImageType.kIconControlShieldGreenSel,
				ImageType.kIconControlShieldBlueSel,
				ImageType.kIconControlShieldPurpleSel,
				ImageType.kIconControlShieldBlackSel
		};
		
		CCMenuItemSprite button = getButtonByID(9);
		//button.setImagesID(images[SudokuStats_GameScoreToRatingIndex(), kImageBarEmpty);
		button.setNormalImage(MainActivity.utils_GetImage(images[SudokuUtils.SudokuStats_GameScoreToRatingIndex()]));
		button.setSelectedImage(MainActivity.utils_GetImage(selimages[SudokuUtils.SudokuStats_GameScoreToRatingIndex()]));
	}

}
