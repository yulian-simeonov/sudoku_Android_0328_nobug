package com.gold.sudoku;

import java.util.Vector;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

public class ToolBarView extends CCLayer {
	public static int barImage;
    CCMenu m_pBottomMenu;

	//NSMutableArray* buttonsArray;
	//public static Vector<ToolBarButtonView> buttonsArray = null; 
	Vector<CCMenuItemSprite> buttonsArray = null; 	
	Object messageDelegate;

	CCSprite _view = null;
	//public static ToolBarView initWithFrame(CGRect frame)
	ToolBarView(int type, CGPoint pos)
	{
		_view = CCSprite.sprite(MainActivity.utils_GetImage(type).getTexture());
		_view.setPosition(GB.getX(pos.x), GB.getY(pos.y));
		_view.setScaleX(GB.g_fScaleX);
		_view.setScaleY(GB.g_fScaleY);
//		self = [super initWithFrame:frame];
//		self.backgroundColor = [UIColor clearColor];
//		return self;
//	}

	//- (void)drawRect:(CGRect)rect 
	//{
	//		UIImage* image = utils_GetImage(barImage);
	//	
	//		if(image)
	//			[image drawInRect:self.bounds];
	}
	
//
//- (void)dealloc 
//{
//	[buttonsArray release];
//	[messageDelegate release];
//	
//	[super dealloc];
//}
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void loadButtons(ButtonItemDef buttons[],  int count)
	{
		int index;
		//ToolBarButtonView buttonView;
		CCMenuItemSprite buttonView;
		
		//if(!buttonsArray) self.buttonsArray = [NSMutableArray array];
		if(buttonsArray == null) buttonsArray = new Vector<CCMenuItemSprite>();
		
		for(index = 0; index < buttonsArray.size(); index++)
		{
			buttonView = buttonsArray.elementAt(index);
			//[buttonView removeFromSuperview];
			buttonView.removeFromParentAndCleanup(true);
		}
		
		buttonsArray.removeAllElements();
		
		m_pBottomMenu = CCMenu.menu();
		for(index = 0; index < count; index++)
		{
			////buttonView = [[ToolBarButtonView alloc] initWithFrame:buttons[index].bounds];
			//buttonView = ToolBarButtonView.initWithFrame(buttons[index].imageIcon, buttons[index].bounds);
			//buttonView.buttonID = buttons[index].buttonID;
			//buttonView.imageIconBack = buttons[index].imageBack;
			//buttonView.imageIconBackHilight = buttons[index].imageBackHilight;
			//buttonView.imageIcon = buttons[index].imageIcon;
			//buttonView.imageIconHilight = buttons[index].imageIconHilight;
			////buttonView.messageDelegate = self.messageDelegate;
			////buttonView.parentView = self;
			//buttonView.drawRect();
			
			//if(buttons[index].selectorName != null)
			//buttonView.messageSelector = NSSelectorFromString(buttons[index].selectorName);
	
			//this.addChild(buttonView);
			//buttonsArray.add(buttonView);
			//[buttonView release];
			CCSprite image = MainActivity.utils_GetImage(buttons[index].imageBack);
			CCSprite bgSprite = CCSprite.sprite(image.getTexture());
			bgSprite.setPosition(CGPoint.ccp(buttons[index].bounds.x, buttons[index].bounds.y));
			bgSprite.setScaleX(1.5f);
			this._view.addChild(bgSprite, 1);
			
			buttonView = CCMenuItemSprite.item(MainActivity.utils_GetImage(buttons[index].imageIcon),
				MainActivity.utils_GetImage(buttons[index].imageIconHilight),
				null,
				(CCNode)this.messageDelegate,
				buttons[index].selectorName);
			buttonView.setPosition(CGPoint.ccp(buttons[index].bounds.x, buttons[index].bounds.y));
			buttonView.setTag(buttons[index].buttonID);

			m_pBottomMenu.addChild(buttonView, 2);
			buttonsArray.add(buttonView);
		}
		m_pBottomMenu.setPosition(0, 0);
		this._view.addChild(m_pBottomMenu, 2);
	}
	
	//- (ToolBarButtonView*)getButtonByID:(int)buttonID
	public CCMenuItemSprite getButtonByID(int buttonID)
	{
		//ToolBarButtonView* button;
		CCMenuItemSprite button;
	
		for(int index = 0; index < buttonsArray.size(); index++)
		{
			button = buttonsArray.elementAt(index);
			
			if(button.getTag() == buttonID)
				return button;
		}
		
		return null;
	}

}
