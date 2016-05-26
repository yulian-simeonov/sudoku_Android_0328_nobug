package com.gold.sudoku;

import java.util.Vector;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.graphics.Bitmap;

public class FlyingKeypadView extends CCSprite {
	
    CCMenu m_pMenu;

	GameScene parentController;
	
	Vector<CCMenuItemSprite> numberButtons;
	Vector<CCMenuItemSprite> colorButtons;
	
	CCMenuItemSprite switchButtonNumbers;
	CCMenuItemSprite switchButtonCandidates;
	
	CCSprite selectionBack;
	
	int itemX;
	int itemY;
	
	boolean movementProcess;
	CGPoint movementLastPoint;

	public final static float  keyPadWidth	=	(float) (142.0*Resolution.IPADSCL);
	public final static float  keyPadHeight =	(float) (142.0*Resolution.IPADSCL);

	public static CGPoint _numberButtons[] = 
	{
		CGPoint.ccp(18, 18), //3, 3, 30, 30),
		CGPoint.ccp(53, 18), //38, 3, 30, 30),
		CGPoint.ccp(88, 18), //73, 3, 30, 30),
		CGPoint.ccp(18, 53), // 38, 30, 30),
		CGPoint.ccp(53, 53), //, 38, 30, 30),
		CGPoint.ccp(88, 53), //38, 30, 30),
		CGPoint.ccp(18, 88), //, 73, 30, 30),
		CGPoint.ccp(53, 88), //38, 73, 30, 30),
		CGPoint.ccp(88, 88), //, 73, 30, 30),
	};

	public static CGPoint _colorButtons[] = 
	{
		CGPoint.ccp(123, 10), //108, 3, 30, 13),
		CGPoint.ccp(123, 27), //108, 20, 30, 13),
		CGPoint.ccp(123, 45), //108, 38, 30, 13),
		CGPoint.ccp(123, 62), //108, 55, 30, 13),
		CGPoint.ccp(123, 80), //108, 73, 30, 13),
		CGPoint.ccp(123, 97), //108, 90, 30, 13),
	};

	//#define kButtonCLR		4*IPADSCL, 108*IPADSCL, 30*IPADSCL, 30*IPADSCL
	//#define kButtonOK			38*IPADSCL, 108*IPADSCL, 65*IPADSCL, 30*IPADSCL
	//#define kButtonSwitch		108*IPADSCL, 108*IPADSCL, 30*IPADSCL, 30*IPADSCL
	CGPoint kButtonCLR = CGPoint.ccp(19, 123);
	CGPoint kButtonOK = CGPoint.ccp(70, 123);
	CGPoint kButtonSwitch = CGPoint.ccp(123, 123);
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//public  initWithParentControler:(GameViewController*)_parentController
	public FlyingKeypadView(GameScene _parentController )
	{
		super("keypad_back.png");
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		//self = [super initWithFrame:CGRectMake(appDeleage.prefKeypadPosX, appDeleage.prefKeypadPosY, keyPadWidth, keyPadHeight)];
		//mytest
		CGPoint pos = CGPoint.ccp(GB.getX(MainActivity.prefKeypadPosX + keyPadWidth/2),
			GB.getY(MainActivity.prefKeypadPosY + keyPadHeight/2));
		setScaleX(GB.g_fScaleX);
		setScaleY(GB.g_fScaleY);
		setPosition(pos);
			
		parentController = _parentController;
		selectionBack = CCSprite.sprite("keypad_selection.png");
	}
	
	//- (void)drawRect:(CGRect)rect
	//{
	//[super drawRect:rect];
	//
	//UIImage* image = imageNamed(@"keypad_back.png");
	//[image drawInRect:self.bounds];
	//}
	
	public void _init()
	{
		createButtons();
		updateState();
	}
	

	public void createButtons()
	{
		//ToolBarButtonView button;
		CCMenuItemSprite button;
		CGPoint itemBounds;
	
		numberButtons = new Vector<CCMenuItemSprite>();

		m_pMenu = CCMenu.menu();
        CCSprite buttonNormal;
        CCSprite buttonSelected;
		CCSprite buttonDisabled;
	
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0; x < 3; x++)
			{
				itemBounds = _numberButtons[3*y + x];
			
				//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
				//button.buttonID = 3*y + x + 1;
				//button.parentView = self;
				//button.messageDelegate = self;
				//button.messageSelector = @selector(onNumberButtonSelect:);
				//button.movementMessageDelegate = self;
				
		        buttonNormal = CCSprite.sprite("mykeyback.png");
		        buttonSelected = CCSprite.sprite("mykeyback.png");
				buttonDisabled = CCSprite.sprite("mykeyback.png");
	
				button = CCMenuItemSprite.item(buttonNormal,
						buttonSelected,
						buttonDisabled,
						GameScene.gamescene,
						//"onNumberButtonSelect");
						"onCandidateSetNumber");
				button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
				button.setTag(3*y + x + 1);

				m_pMenu.addChild(button, 2);
				numberButtons.add(button);
			
				//[button release];
			}
		}
	
		colorButtons = new Vector<CCMenuItemSprite>();
		
		for(int index = 0; index < 6; index++)
		{
			itemBounds = _colorButtons[index];
			
			//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
			//button.buttonID = index + 1;
			//button.image = imageNamed([NSString stringWithFormat:@"keypad_color_%d.png", index]);
			//button.parentView = self;
			//button.messageDelegate = self;
			//button.messageSelector = @selector(onColorButtonSelect:);
			//button.movementMessageDelegate = self;
			String file = String.format("keypad_color_%d.png", index);
	        buttonNormal = CCSprite.sprite(file);
	        buttonSelected = CCSprite.sprite(file);
			buttonDisabled = CCSprite.sprite(file);
	
			button = CCMenuItemSprite.item(buttonNormal,
					buttonSelected,
					buttonDisabled,
					GameScene.gamescene,
					"onCandidateSetColor");//"onColorButtonSelect");
			button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
			button.setTag(index+1);

			m_pMenu.addChild(button, 2);
			
			colorButtons.add(button);
			
			//[button release];
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		//itemBounds = CGRectMake(kButtonCLR);
		itemBounds = kButtonCLR;

		//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
		//button.buttonID = 10001;
		//button.image = imageNamed(@"keypad_clr.png");
		//button.parentView = self;
		//button.messageDelegate = self;
		//button.messageSelector = @selector(onClrButtonSelect:);
		//button.movementMessageDelegate = self;
		//
		//[self addSubview:button];
		//[button release];
        buttonNormal = CCSprite.sprite("keypad_clr.png");
        buttonSelected = CCSprite.sprite("keypad_clr_sel.png");
		buttonDisabled = CCSprite.sprite("keypad_clr_sel.png");

		button = CCMenuItemSprite.item(buttonNormal,
				buttonSelected,
				buttonDisabled,
				GameScene.gamescene,
				"onCandidateCancel");//"onClrButtonSelect");
		button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
		button.setTag(10001);
		
		m_pMenu.addChild(button, 2);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		itemBounds = kButtonOK;
		
		//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
		//button.buttonID = 10002;
		//button.image = imageNamed(@"keypad_ok.png");
		//button.parentView = self;
		//button.messageDelegate = self;
		//button.messageSelector = @selector(onOkButtonSelect:);
		//button.movementMessageDelegate = self;
		//
		//[self addSubview:button];
		//[button release];
        buttonNormal = CCSprite.sprite("keypad_ok.png");
        buttonSelected = CCSprite.sprite("keypad_ok_sel.png");
		buttonDisabled = CCSprite.sprite("keypad_ok_sel.png");

		button = CCMenuItemSprite.item(buttonNormal,
				buttonSelected,
				buttonDisabled,
				GameScene.gamescene,
				"onCandidateOK");//"onOkButtonSelect");
		button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
		button.setTag(10002);
		m_pMenu.addChild(button, 2);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		itemBounds = kButtonSwitch;
		
		//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
		//button.buttonID = 10003;
		//button.image = imageNamed(@"keypad_numbers.png");
		//button.parentView = self;
		//button.messageDelegate = self;
		//button.messageSelector = @selector(onNumbersButtonSelect:);
		//button.movementMessageDelegate = self;
		//
		//[self addSubview:button];
		//self.switchButtonNumbers = button;
		//
		//[button release];
		String switchFile = String.format("keypad_numbers.png");
        buttonNormal = CCSprite.sprite(switchFile);
        buttonSelected = CCSprite.sprite(switchFile);
		buttonDisabled = CCSprite.sprite(switchFile);

		button = CCMenuItemSprite.item(buttonNormal,
				buttonSelected,
				buttonDisabled,
				GameScene.gamescene,
				"onCandidateMode"); //"onNumbersButtonSelect");
		button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
		button.setTag(10003);
		m_pMenu.addChild(button, 2);

		switchButtonNumbers = button;
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		itemBounds = kButtonSwitch;
		
		//button = [[ToolBarButtonView alloc] initWithFrame:itemBounds];
		//button.buttonID = 10004;
		//button.image = imageNamed(@"keypad_candidate.png");
		//button.parentView = self;
		//button.messageDelegate = self;
		//button.messageSelector = @selector(onCandidateButtonSelect:);
		//button.movementMessageDelegate = self;
		//
		//[self addSubview:button];
		//
		//[button release];
		String candiFile = String.format("keypad_candidate.png");
        buttonNormal = CCSprite.sprite(candiFile);
        buttonSelected = CCSprite.sprite(candiFile);
		buttonDisabled = CCSprite.sprite(candiFile);

		button = CCMenuItemSprite.item(buttonNormal,
				buttonSelected,
				buttonDisabled,
				GameScene.gamescene,
				"onCandidateMode"); //"onCandidateButtonSelect");
		button.setPosition(itemBounds.x, getBtnY(itemBounds.y));
		m_pMenu.addChild(button, 2);
		button.setTag(10004);
		
		switchButtonCandidates = button;
			
		m_pMenu.setPosition(0, 0);
		this.addChild(m_pMenu, 2);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void addKeypad()
	{
		//if(self.superview == parentController.view)
		//	return;
		//	
		parentController.addChild(this, 7);
		
		parentController.setIsTouchEnabled(false);
		
		//self.alpha = 0.0;
		//
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:0.3];
		//
		//self.alpha = 1.0;
		//
		//[UIView commitAnimations];
	}

//	public  void hideAnimationDidStop(NSString  animationID,NSNumber  finished,void  context)
//	{
//		[self removeFromSuperview];
//	}	

	public void removeKeypad()
	{
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:0.3];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(hideAnimationDidStop:finished:context:)];
		//
		//self.alpha = 0.0;
		//
		//[UIView commitAnimations];
		parentController.removeChild(this, true);
		parentController.setIsTouchEnabled(true);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//	public void onNumberButtonSelect(Object button)
//	{
//		parentController.onCandidateSetNumber(button);
//	}
//	
//	public void onColorButtonSelect(Object button)
//	{
//		parentController.onCandidateSetColor(button);
//	}
//
//	public void onClrButtonSelect(Object button)
//	{
//		parentController.onCandidateCancel(button);
//	}
//
//	public void onOkButtonSelect(Object button)
//	{
//		parentController.onCandidateOK(button);
//	}
//
//	public void onNumbersButtonSelect(Object button)
//	{
//		parentController.onCandidateMode(button);
//	}
//
//	public void onCandidateButtonSelect(Object button)
//	{
//		parentController.onCandidateMode(button);
//	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void selectItemX(int x, int y)
	{
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		BoardDefType boardDef = skinManager.getBoardDef();
		CGRect cellBounds = CGRect.make(boardDef.itemPosX[x], boardDef.itemPosY[y], boardDef.itemSizeX, boardDef.itemSizeY);
		CGRect keypadBounds = CGRect.make(0, 0, 0, 0);
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		itemX = x;
		itemY = y;
		
		//if(appDeleage.prefKeypadIsStickly && ((appDeleage.prefKeypadPosX != 0) && (appDeleage.prefKeypadPosY != 0)))
		//if(MainActivity.prefKeypadIsStickly && ((MainActivity.prefKeypadPosX != 0) && (MainActivity.prefKeypadPosY != 0)))
		//	return;
		
		keypadBounds.origin.x = (int)(cellBounds.origin.x + cellBounds.size.width/2 - keyPadWidth/2 + 1);
		keypadBounds.size.width = keyPadWidth;
		
		keypadBounds.origin.y = (int)(cellBounds.origin.y + cellBounds.size.height/2 - keyPadHeight/2 + (47 + 46));
		keypadBounds.size.height = keyPadHeight;
		
		if(keypadBounds.origin.x < 0)
			keypadBounds.origin.x = 0;
		
		if((keypadBounds.origin.x + keypadBounds.size.width) >= 318)
			keypadBounds.origin.x = 318 - keypadBounds.size.width;
		
		if(keypadBounds.origin.y < (47 + 46))
			keypadBounds.origin.y = (47 + 46);
		
		if((keypadBounds.origin.y + keypadBounds.size.height) >= (47 + 46 + 318))
			keypadBounds.origin.y = (47 + 46 + 318) - keypadBounds.size.height;
		
		//self.frame = keypadBounds;
		CGPoint pos = CGPoint.ccp(keypadBounds.origin.x + keypadBounds.size.width/2, keypadBounds.origin.y+keypadBounds.size.height/2);
		this.setPosition(GB.getX(pos.x), GB.getY(pos.y));

		MainActivity.prefKeypadPosX = (int)keypadBounds.origin.x;
		MainActivity.prefKeypadPosY = (int)keypadBounds.origin.y; 
	}
	
	
	public void updateState()
	{
		boolean inNumbersMode = parentController.boardView.activeMode;
		int color = parentController.boardView.activeColor;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		for(int index = 0; index < colorButtons.size(); index++)
		{
			//ToolBarButtonView button = (ToolBarButtonView*)[colorButtons objectAtIndex:index];
			CCMenuItemSprite button = colorButtons.elementAt(index);
			//button.hidden = !inNumbersMode;
			button.setVisible(inNumbersMode);
		}
		
		if(inNumbersMode)
		{
			//switchButtonNumbers.hidden = true;
			//switchButtonCandidates.hidden = false;
			switchButtonNumbers.setVisible(false);
			switchButtonCandidates.setVisible(true);
			
			for(int index = 0; index < numberButtons.size(); index++)
			{
				CCMenuItemSprite button = numberButtons.elementAt(index);
		
				button.removeAllChildren(true);
				
				CCSprite imageNormal = skinManager.getNumberImageWithValue(index + 1, color, false);
				CCSprite imageSelected = skinManager.getNumberImageWithValue(index + 1, color, true);
				
				//[button setImages:imageNormal hilight:imageSelected];
				button.setNormalImage(imageNormal);
				button.setSelectedImage(imageSelected);
				
				if(SudokuUtils.g_gameGrid.grid[itemY][itemX].number == (index + 1)) {
				//	button.setBackImage:selectionBack];
					CCSprite btnbg = CCSprite.sprite(selectionBack.getTexture());
					btnbg.setPosition(button.getContentSize().width/2, button.getContentSize().height/2);
					button.addChild(btnbg);
				}
				//else
				//	[button setBackImage:nil];
			}
		}
		else
		{
			switchButtonNumbers.setVisible(true);
			switchButtonCandidates.setVisible(false);
			
			for(int index = 0; index < numberButtons.size(); index++)
			{
				CCMenuItemSprite button = numberButtons.elementAt(index);

				button.removeAllChildren(true);

				CCSprite imageNormal = skinManager.getCandidateButtonImageWithValue(index + 1, false);
				CCSprite imageSelected = skinManager.getCandidateButtonImageWithValue(index + 1, true);
				
				//[button setImages:imageNormal hilight:imageSelected];
				button.setNormalImage(imageNormal);
				button.setNormalImage(imageSelected);
				
				if(SudokuUtils.g_gameGrid.grid[itemY][itemX].candidates[index] != -1) {
					//[button setBackImage:selectionBack];
					CCSprite btnbg = CCSprite.sprite(selectionBack.getTexture());
					btnbg.setPosition(button.getContentSize().width/2, button.getContentSize().height/2);
					button.addChild(btnbg);
				}
				//else
				//	[button setBackImage:nil];
			}
		}
	}
	
	public void onButtonOutsideMoveBegin()
	{
		movementProcess = false;
	}
	
	//public void onButtonOutsideMove(NSSet*)touches
	public void onButtonOutsideMove(CGPoint point)
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
	
		if(!MainActivity.prefKeypadIsDraggable)
			return;
	
		//CGPoint point = [[touches anyObject] locationInView:parentController.view];
		
		if(!movementProcess)
		{
			movementLastPoint = point;
			movementProcess = true;
		}
		else
		{
			double dx = point.x - movementLastPoint.x;
			double dy = point.y - movementLastPoint.y;
			
			movementLastPoint = point;
			
			//CGRect newFrame = self.frame;
			CGRect newFrame = this.getBoundingBox();
			
			newFrame.origin.x += dx;
			newFrame.origin.y += dy;
			
			if(newFrame.origin.x < 0)		
				newFrame.origin.x = 0;
			
			if(newFrame.origin.y < 0)		
				newFrame.origin.y = 0;
			
			//if((newFrame.origin.x + keyPadWidth) > SCREEN_WIDTH)		
			//	newFrame.origin.x = SCREEN_WIDTH - keyPadWidth;
			if((newFrame.origin.x + keyPadWidth * GB.g_fScaleX) > GB.g_winSize.width)		
				newFrame.origin.x = GB.g_winSize.width - keyPadWidth * GB.g_fScaleX;
			
			//if((newFrame.origin.y + keyPadHeight) > SCREEN_HEIGHT)		
			//	newFrame.origin.y = SCREEN_HEIGHT - keyPadHeight;
			if((newFrame.origin.y + keyPadHeight * GB.g_fScaleY) >  GB.g_winSize.height)		
				newFrame.origin.y =  GB.g_winSize.height - keyPadHeight * GB.g_fScaleY;
			//
			MainActivity.prefKeypadPosX = (int)newFrame.origin.x;
			MainActivity.prefKeypadPosY = (int)newFrame.origin.y;
			
			CGPoint newpos = CGPoint.ccp(GB.getX(MainActivity.prefKeypadPosX + keyPadWidth/2),
					GB.getY(MainActivity.prefKeypadPosY + keyPadHeight/2));
			this.setPosition(newpos);

			//
			//[UIView beginAnimations:nil context:nil];
			//[UIView setAnimationDuration:0.05];
			//[UIView setAnimationBeginsFromCurrentState:YES];
			//[UIView setAnimationCurve:UIViewAnimationCurveLinear];
			//self.frame = newFrame;
			//[UIView commitAnimations];
		}
	}
	
	public void onButtonOutsideMoveCancel()
	{
		movementProcess = false;
	}

	public static float getBtnY(float y)
	{
	    return 142 - y;
	}
}
