package com.gold.sudoku;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.cocos2d.actions.interval.CCMoveTo;
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
import android.view.View;
import android.webkit.WebView;

public class MenuView extends CCSprite {

	CCMenu m_pMenu;
	
	final public static float kMenuBottomDelta = 44*Resolution.IPADSCL;	
	final public static float kMenuHeaderHeight = 23*Resolution.IPADSCL;	
	final public static float kMenuTopDelta = 40*Resolution.IPADSCL;
	final public static float kMenuItemsDelta	= 0*Resolution.IPADSCL;
                  
	final public static float kMenuButtonOffset = 43*Resolution.IPADSCL;
	final public static float kMenuButtonWidth = 152*Resolution.IPADSCL;
	final public static float kMenuButtonHeight = 42*Resolution.IPADSCL;
	              
	final public static float kMenuBackOffsetTop = 4*Resolution.IPADSCL;
	final public static float kMenuBackOffsetLeft = 4*Resolution.IPADSCL;
	final public static float kMenuBackWidth = 100*Resolution.IPADSCL;
	final public static float kMenuBackHeight = 32*Resolution.IPADSCL;
                  
	final public static float kMenuTitleOffsetTop = 4*Resolution.IPADSCL;
	final public static float kMenuTitleOffsetLeft = 108*Resolution.IPADSCL;
	final public static float kMenuTitleWidth = 210*Resolution.IPADSCL;
	final public static float kMenuTitleHeight	= 32*Resolution.IPADSCL;
                  
	final public static float  kMenuShowHideAnimationDuration = 0.5f;
	final public static float kMenuShowHideFromSubmenu = 0.6f;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	CCLabel labelName;
		
	//	id messageDelegate;
	GameScene parentView;
	
	int prevMenuID;
	String name;
	MenuDef pMenuDef;
		
	//NSMutableArray* menuStack;
	public ArrayList<MenuStackItem> menuStack = null;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//- (void)constructMenuWithItems:(const MenuItemDef*)pItems count:(int)count updateSel:(SEL)updateSel updateDel:(id)updateDel
	public void constructMenuWithItems(final MenuItemDef []pItems,  int count, String updateSel)
	{
		int menuHeight = (int)(kMenuTopDelta + (count*MenuItemView.kMenuItemDefaultHeight + count*kMenuItemsDelta) + kMenuBottomDelta);
		//int dx = (self.bounds.size.width - kMenuItemDefaultWidth)/2;
		int dx = (int)(this.getContentSize().width - MenuItemView.kMenuItemDefaultWidth)/2;
		int offset;
		
		//UIView* backView = [[UIView alloc] initWithFrame:CGRectMake(self.bounds.origin.x, self.bounds.size.height - menuHeight, self.bounds.size.width, menuHeight)];
		//backView.userInteractionEnabled = NO;
		//backView.backgroundColor = [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.9];
		//[self addSubview:backView];
		//[backView release];                 
				
		//UIImageView* headerView = [[UIImageView alloc] initWithFrame:CGRectMake(self.bounds.origin.x, self.bounds.size.height - menuHeight, self.bounds.size.width, kMenuHeaderHeight)];
		//headerView.image = utils_GetImage(kImageMenuHeader);
		//[self addSubview:headerView];
		//[headerView release];
		CCSprite image  = MainActivity.utils_GetImage(ImageType.kImageMenuHeader);
        CCSprite headerView = CCSprite.sprite(image.getTexture());
        headerView.setPosition(this.getContentSize().width/2, menuHeight);
        addChild(headerView, 1);
		
		if(name != null)
		{
			//UILabel* label = [[UILabel alloc] initWithFrame:CGRectMake(kMenuTitleOffsetLeft, self.bounds.size.height - menuHeight + kMenuTitleOffsetTop, kMenuTitleWidth, kMenuTitleHeight)];
			//label.text = name;
			//label.backgroundColor = [UIColor clearColor];
			//label.textColor = [UIColor whiteColor];
			//label.font = [UIFont boldSystemFontOfSize:kMenuItemFontSize];
			//label.adjustsFontSizeToFitWidth = YES;
			//label.shadowColor = [UIColor grayColor];
			//label.shadowOffset = CGSizeMake(0, 1);
			//[self addSubview:label];
			//[label release];
			CCLabel label = CCLabel.makeLabel(name,
					CGSize.make(kMenuTitleWidth, kMenuTitleHeight),
					TextAlignment.CENTER, "Arial-BoldMT",
					MenuItemView.kMenuItemFontSize);
			label.setColor(ccColor3B.ccGRAY);
			label.setPosition(kMenuTitleOffsetLeft + kMenuTitleWidth/2, menuHeight);

	        addChild(label, 2);
		}
		
		m_pMenu = CCMenu.menu();
		m_pMenu.setPosition(0,0);
		this.addChild(m_pMenu, 2);
		
		if(menuStack != null && menuStack.size() != 0)
		{
			//UIButton* backButton = [UIButton buttonWithType:UIButtonTypeRoundedRect];
			//backButton.frame = CGRectMake(kMenuBackOffsetLeft, self.bounds.size.height - menuHeight + kMenuBackOffsetTop, kMenuBackWidth, kMenuBackHeight);
			//[backButton setTitle:NSLocalizedString(@"Back",@"") forState:UIControlStateNormal];
			//[backButton addTarget:self action:@selector(onBack:) forControlEvents:UIControlEventTouchUpInside];
			//if(!ISIPHONE)
			//	backButton.titleLabel.font = [UIFont boldSystemFontOfSize:[UIFont systemFontSize]*IPADSCL];
			//[self addSubview:backButton];
			
	        CCSprite backButtonNormal = CCSprite.sprite("btn_back_0.png");
	        CCSprite backButtonSelected = CCSprite.sprite("btn_back_sel_0.png");
	        CCSprite backButtonDisabled = CCSprite.sprite("btn_back_sel_0.png");

	        CCMenuItemSprite backButton = CCMenuItemSprite.item(backButtonNormal,
	        		backButtonSelected,
	        		backButtonDisabled,
					this,
					"onBack");
	        backButton.setPosition(kMenuBackOffsetLeft + kMenuBackWidth/2, menuHeight);

			m_pMenu.addChild(backButton, 2);

		}
		
		//offset = self.bounds.size.height - menuHeight + kMenuTopDelta;
		offset = (int)(menuHeight - kMenuTopDelta);
		
		for(int index = 0; index < count; index++)
		{
			//MenuItemView itemView = MenuItemView.initWithPoint(CGPoint.ccp(dx, offset), def:&pItems[index] delegate:self updateSel:updateSel updateDel:updateDel];
			MenuItemView itemView = new MenuItemView(CGPoint.ccp(dx, offset), pItems[index], this, updateSel);
			this.addChild(itemView, 2);
			//[itemView release];
			
			offset -= MenuItemView.kMenuItemDefaultHeight + kMenuItemsDelta;
		}
		
		//UIButton* button = [[UIButton alloc] initWithFrame:CGRectMake(self.bounds.origin.x + (self.bounds.size.width - kMenuButtonWidth)/2, self.bounds.size.height - kMenuButtonOffset, kMenuButtonWidth, kMenuButtonHeight)];
		//[button setTitle:NSLocalizedString(@"Cancel",@"") forState:UIControlStateNormal];
		//[button setBackgroundImage:utils_GetImage(kImageMenuButton) forState:UIControlStateNormal];
		//[button setBackgroundImage:utils_GetImage(kImageMenuButtonSel) forState:UIControlStateHighlighted];
		//[button addTarget:self action:@selector(onCancel:) forControlEvents:UIControlEventTouchUpInside];
		//if(!ISIPHONE)
		//	button.titleLabel.font = [UIFont boldSystemFontOfSize:[UIFont systemFontSize]*IPADSCL];
		//[self addSubview:button];
		//[button release];
        CCSprite cancelButtonNormal = MainActivity.utils_GetImage(ImageType.kImageMenuButton);
        CCSprite cancelButtonSelected = MainActivity.utils_GetImage(ImageType.kImageMenuButtonSel);
        CCSprite cancelButtonDisabled = MainActivity.utils_GetImage(ImageType.kImageMenuButtonSel);

        CCMenuItemSprite cancelButton = CCMenuItemSprite.item(cancelButtonNormal,
        		cancelButtonSelected,
        		cancelButtonDisabled,
				this,
				"onCancel");
        cancelButton.setPosition(this.getContentSize().width/2, kMenuButtonOffset - kMenuButtonHeight /2);

		CCLabel cancellabel = CCLabel.makeLabel("Cancel",
				CGSize.make(cancelButton.getContentSize().width, cancelButton.getContentSize().height),
				TextAlignment.CENTER, "Arial-BoldMT",
				MenuItemView.kMenuItemFontSize);
		cancellabel.setColor(ccColor3B.ccWHITE);
		cancellabel.setPosition(cancelButton.getContentSize().width/2, cancelButton.getContentSize().height/2);

		cancelButton.addChild(cancellabel);
       
        
		m_pMenu.addChild(cancelButton, 2);

	}

	//- (id)initWithItems:(const MenuDef*)pMenu delegeate:(id)delegeate parent:(UIView*)parent menuName:(NSString*)menuName stack:(NSMutableArray*)stack
	public MenuView(final MenuDef pMenu, GameScene parent, String menuName, ArrayList stack)
	{
		//self = [super initWithFrame:parent.bounds];
		super("black_bg.png");
		setPosition(GB.g_winSize.width/2, GB.g_winSize.height/2);
		setScaleX(GB.g_winSize.width / getContentSize().width);
		setScaleY(GB.g_winSize.height /getContentSize().height);

		parentView = parent;
		//messageDelegate = delegeate;
		
		pMenuDef = pMenu;
		name = menuName;

		//self.backgroundColor = [UIColor colorWithRed:0.0 green:0.0 blue:0.0 alpha:0.7];
		Object updateDel = null;
		String updateSel = null;
		
		//if(delegeate && pMenu->stateUpdateCallback)
		if(pMenu.stateUpdateCallback != null)
		{
			//updateDel = delegeate;
			//updateSel = NSSelectorFromString(pMenu->stateUpdateCallback);
			updateSel = pMenu.stateUpdateCallback;
		}

		if(stack != null) {
			menuStack = (ArrayList<MenuStackItem>)stack.clone(); //[NSMutableArray arrayWithArray:stack];
		}
		
		//if(delegeate != null && pMenu.updateMenuDefCallback != null)
//		if(pMenu.updateMenuDefCallback != null)
//		{
//			int size = sizeof(MenuDef) + sizeof(MenuItemDef)*pMenu->count;
//			MenuDef* tmpMenuDef = malloc(size);
//			
//			memmove(tmpMenuDef, pMenu, size);
//		
//			SEL menuDefUpdateSel = NSSelectorFromString(pMenu->updateMenuDefCallback);
//			
//			[delegeate performSelector:menuDefUpdateSel withObject:[NSValue valueWithPointer:tmpMenuDef]];
//			[self constructMenuWithItems:tmpMenuDef->items count:tmpMenuDef->count updateSel:updateSel updateDel:updateDel];
//			
//			free(tmpMenuDef);
//		}
//		else
		{
			//constructMenuWithItems(pMenu.items, pMenu.count, updateSel, updateDel);
			constructMenuWithItems(pMenuDef.items, pMenuDef.count, updateSel);
		}
			
	}
	

	//- (void)dealloc 
	//{
	//[labelName release];
	//[name release];
	//[menuStack release];
	//
	//[super dealloc];
	//}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void showMenuTransition(int transition)
	{
		switch(transition)
		{
		case GC.kCATransitionFromTop:
			this.setPosition(GB.g_winSize.width/2, GB.g_winSize.height*3/2);
			break;
		case GC.kCATransitionFromLeft:
			this.setPosition(-GB.g_winSize.width/2, GB.g_winSize.height/2);
			break;
		case GC.kCATransitionFromRight:
			this.setPosition(GB.g_winSize.width*3/2, GB.g_winSize.height/2);
			break;
		}
		
		parentView.addChild(this);
		
		//CATransition *animation = [CATransition animation];
		//[animation setDelegate:self];
		//	
		//[animation setType:kCATransitionMoveIn];
		//[animation setSubtype:transition];
		//	
		//[animation setDuration:kMenuShowHideAnimationDuration];
		//[animation setTimingFunction:[CAMediaTimingFunction functionWithName:kCAMediaTimingFunctionEaseInEaseOut]];
		//
		//[[self layer] addAnimation:animation forKey:nil];
		
		CGPoint pos = CGPoint.ccp(GB.g_winSize.width/2, GB.g_winSize.height/2); 
	    CCMoveTo move = CCMoveTo.action(1.0f, pos);

	}

	public void showMenu()
	{
		showMenuTransition(GC.kCATransitionFromTop);
	}

	public void showSubmenu()
	{
		showMenuTransition(GC.kCATransitionFromRight);
	}

	public void showBackSubmenu()
	{
		showMenuTransition(GC.kCATransitionFromLeft);
	}

	//- (void) hideAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	//{
	//[self removeFromSuperview];
	////[self release];
	//}

	public void hideMenu(double timeout)
	{
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:timeout];
		//[UIView setAnimationBeginsFromCurrentState:YES];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(hideAnimationDidStop:finished:context:)];
		//
		//self.alpha = 0;
		//
		//[UIView commitAnimations];
		hideAnimationWithCommandDidStop();
	}

	//public void hideAnimationWithCommandDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void hideAnimationWithCommandDidStop()
	{
		this.removeFromParentAndCleanup(true);
	}
	
	public void onMenuItemSelected(MenuItemView sender)
	{
		SoundUtils.Sounds_PlayClick();
		
		if(sender.nextLevelMenu != null)
		{
			//NSMutableArray* array;
			ArrayList<MenuStackItem> array;
			
			if(menuStack != null)
				//array = [NSMutableArray arrayWithArray:menuStack];
				array = (ArrayList<MenuStackItem>)menuStack.clone();
			else
				//array = [NSMutableArray array];
				array = new ArrayList<MenuStackItem>();
			
			MenuStackItem item = new MenuStackItem();	// alloc] init];
			item.name = name;
			item.pMenuDef = pMenuDef;
			
			array.add(item);
			
			//MenuView* menuView = [[MenuView alloc] initWithItems:sender.nextLevelMenu delegeate:messageDelegate parent:parentView menuName:sender.menuName stack:array];
			MenuView menuView = new MenuView(sender.nextLevelMenu, parentView, sender.menuName, array);
			parentView.addChild(menuView, 10);
			menuView.prevMenuID = sender.menuID;
			//[menuView showSubmenu];
			//[menuView release];
			
			hideMenu(kMenuShowHideFromSubmenu);
		}
		else
		{
			if(sender.menuCommand != null)
			{
//				SEL messageSelector = NSSelectorFromString(sender.menuCommand);
//				
//				if(messageDelegate && messageSelector)
//					[messageDelegate performSelector:messageSelector withObject:sender];
				
				try {
					Method method = GameScene.class.getMethod(sender.menuCommand, new Class[] {Object.class});
					//method.invoke(parentView, (Object [])null);
					method.invoke(parentView, sender);

					} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
//			[UIView beginAnimations:nil context:sender];
//			[UIView setAnimationDuration:kMenuShowHideAnimationDuration];
//			[UIView setAnimationBeginsFromCurrentState:YES];
//			[UIView setAnimationDelegate:self];
//			[UIView setAnimationDidStopSelector:@selector(hideAnimationWithCommandDidStop:finished:context:)];
//			
//			self.alpha = 0;
//			
//			[UIView commitAnimations];
			hideAnimationWithCommandDidStop();
		}
	}
	
	public void onCancel(Object sender)
	{
		hideMenu(kMenuShowHideAnimationDuration);
	}

	public void onBack(Object sender)
	{
		if(menuStack == null || menuStack.size() == 0 )
			return;
			
		//MenuStackItem item = menuStack lastObject];
		int last = menuStack.size();
		MenuStackItem item = menuStack.get(last-1);
		
		//NSMutableArray* array = [NSMutableArray arrayWithArray:menuStack];
		ArrayList<MenuStackItem> array = (ArrayList<MenuStackItem>)menuStack.clone();
		array.remove(last-1); // removeLastObject];
		
		//MenuView menuView = new MenuView(item.pMenuDef, MainActivity.app, parentView, item.name, array);
		MenuView menuView = new MenuView(item.pMenuDef, parentView, item.name, array);
		parentView.addChild(menuView, 10);
		//[menuView showBackSubmenu];
		//[menuView release];
		
		hideMenu(kMenuShowHideFromSubmenu);
	}
}
