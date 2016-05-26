package com.gold.sudoku;

import java.lang.reflect.Method;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

public class MenuItemView extends CCSprite {
	
	CCMenu m_pMenu;
	
	final public static float kMenuItemDefaultWidth		= (320*Resolution.IPADSCL);
	final public static float kMenuItemDefaultHeight	= (42.0f*Resolution.IPADSCL);
	
	//final public static float kMenuItemImageBounds		7*Resolution.IPADSCL, 5*Resolution.IPADSCL, 30*Resolution.IPADSCL, 30*Resolution.IPADSCL
	final public static CGPoint kMenuItemImageBounds	= CGPoint.ccp(22*Resolution.IPADSCL, 20*Resolution.IPADSCL);
	final public static CGRect kMenuItemLabelBounds		= CGRect.make(54*Resolution.IPADSCL, 5*Resolution.IPADSCL, 258*Resolution.IPADSCL, 30*Resolution.IPADSCL);
	final public static float kMenuItemFontSize			= (18.0f*Resolution.IPADSCL);
	
	final public static float kMenuItemIconNextWidth	= (25.0f*Resolution.IPADSCL);
	final public static float kMenuItemIconCheckWidth	= (40.0f*Resolution.IPADSCL);
	final public static float kMenuItemIconDelta		= (2.0f*Resolution.IPADSCL);
	
	final public static float kMenuItemAnimationDuration = 0.2f;
	
	//	UIImageView* selectionView;
	//	UIImageView* backgroundView;
	
	public MenuDef nextLevelMenu;
	public int menuID;
	public String menuCommand;
	public String menuName;
	public boolean checkMark;
	
	boolean selected;
	boolean needExecute;
	
	MenuView parentMenu;
	
	//- (void)initSubViewsWithName:(NSString*)name image:(ImageType)imageType imageName:(NSString*)imageName;
	public void initSubViewsWithName(String name, int imageType, String imageName)
	{
		//self.backgroundColor = [UIColor clearColor];
		
		//selectionView = [[UIImageView alloc] initWithFrame:CGRectMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds), 0, 0)];
		//selectionView.alpha = 0;
		//selectionView.backgroundColor = [UIColor clearColor];
		//selectionView.image = utils_GetImage(kImageMenuItemSel);
		//selectionView.userInteractionEnabled = NO;	
		//
		//UIImageView* backImage = [[UIImageView alloc] initWithFrame:self.bounds];
		//backImage.backgroundColor = [UIColor clearColor];
		//backImage.image = utils_GetImage(kImageMenuItem);
		//[self addSubview:backImage];
		//[backImage release];
		//
		//[self addSubview:selectionView];
        CCSprite menuButtonNormal = MainActivity.utils_GetImage(ImageType.kImageMenuItem);
        CCSprite menuButtonSelected =  MainActivity.utils_GetImage(ImageType.kImageMenuItemSel);
        CCSprite menuButtonDisabled = MainActivity.utils_GetImage(ImageType.kImageMenuItemSel);

        CCMenuItemSprite menuButton = CCMenuItemSprite.item(menuButtonNormal,
        		menuButtonSelected,
        		menuButtonDisabled,
				this,
				"onMenuItemViewSelected");
        menuButton.setPosition(this.getContentSize().width/2, this.getContentSize().height/2);

		m_pMenu.addChild(menuButton, 2);
		
		CGRect bounds = CGRect.make(kMenuItemLabelBounds);
		
		if(nextLevelMenu != null)
		{
			bounds.size.width -= kMenuItemIconNextWidth + 2*kMenuItemIconDelta;
			
			//UIImageView* iconImage = [[UIImageView alloc] initWithFrame:CGRectMake(bounds.origin.x + bounds.size.width + kMenuItemIconDelta, bounds.origin.y, kMenuItemIconNextWidth, bounds.size.height)];
			//iconImage.backgroundColor = [UIColor clearColor];
			//iconImage.image = utils_GetImage(kImageMenuIconNextLevel);
			//[self addSubview:iconImage];
			//[iconImage release];
			CCSprite image = MainActivity.utils_GetImage(ImageType.kImageMenuIconNextLevel);
			CCSprite iconImage = CCSprite.sprite(image.getTexture());
			iconImage.setPosition(CGPoint.ccp(bounds.origin.x + bounds.size.width + kMenuItemIconDelta +kMenuItemIconNextWidth/2,
					this.getContentSize().height/2));
			this.addChild(iconImage, 3);
			
		}
		
		if(checkMark)
		{
			bounds.size.width -= kMenuItemIconCheckWidth + 2*kMenuItemIconDelta;
			
			//UIImageView* iconImage = [[UIImageView alloc] initWithFrame:CGRectMake(bounds.origin.x + bounds.size.width + kMenuItemIconDelta, 0, kMenuItemIconNextWidth, self.bounds.size.height)];
			//iconImage.backgroundColor = [UIColor clearColor];
			//iconImage.image = imageNamed(@"menu_checkmark.png");
			//iconImage.contentMode = UIViewContentModeCenter;
			//[self addSubview:iconImage];
			//[iconImage release];
			CCSprite iconImage = CCSprite.sprite("menu_checkmark.png");
			iconImage.setPosition(CGPoint.ccp(bounds.origin.x + bounds.size.width + kMenuItemIconDelta +kMenuItemIconNextWidth/2,
					this.getContentSize().height/2));
			this.addChild(iconImage, 3);
		}
		
		//CCLabel label = CCLabel.l [[UILabel alloc] initWithFrame:bounds];
		//label.backgroundColor = [UIColor clearColor];
		//label.font = [UIFont boldSystemFontOfSize:kMenuItemFontSize];
		//label.adjustsFontSizeToFitWidth = YES;
		//label.shadowColor = [UIColor whiteColor];
		//label.shadowOffset = CGSizeMake(0, 1);
		//label.text = name;
		//[self addSubview:label];
		//[label release];
		CCLabel label = CCLabel.makeLabel(name,
				CGSize.make(bounds.size.width, bounds.size.height),
				TextAlignment.CENTER, "Arial-BoldMT",
				MenuItemView.kMenuItemFontSize);
		label.setColor(ccColor3B.ccWHITE);
		label.setPosition(CGPoint.ccp(bounds.origin.x + bounds.size.width/2, this.getContentSize().height/2));
        this.addChild(label, 3);
		
		//[UIImageView alloc] initWithFrame:CGRectMake(kMenuItemImageBounds)];
		//imageView.contentMode = UIViewContentModeCenter;
		//imageView.backgroundColor = [UIColor clearColor];
		CCSprite image;
		if((imageType == ImageType.kImageBarEmpty) && (imageName != null))
			image = CCSprite.sprite(imageName);
		else
			image = MainActivity.utils_GetImage(imageType);
		
		CCSprite imageView = CCSprite.sprite(image.getTexture());
		imageView.setPosition(CGPoint.ccp(kMenuItemImageBounds.x, this.getContentSize().height/2));
		this.addChild(imageView, 3);
		//[imageView release];
		 
	}

	//- (id)initWithPoint:(CGPoint)point def:(const MenuItemDef*)itemDef delegate:(MenuView*)parentView updateSel:(SEL)updateSel updateDel:(id)updateDel;
	public MenuItemView(CGPoint point, MenuItemDef itemDef, MenuView parentView, String updateSel) 
	{
		//self = [super initWithFrame:frame];
		super("menu_item_bg.png");

		//CGRect frame = CGRectMake(point.x, point.y, kMenuItemDefaultWidth, kMenuItemDefaultHeight);
		CGPoint frame = CGPoint.ccp(point.x + kMenuItemDefaultWidth/2, point.y);
		setPosition(frame);
	
		nextLevelMenu =( MenuDef)itemDef.submenu;
		menuID = itemDef.itemID;
		
		if(itemDef.menuCommand != null)
			menuCommand = new String().format("%s", itemDef.menuCommand);
		
		if(itemDef.itemName != null)
			menuName = new String().format("%s", itemDef.itemName);
		
		parentMenu = parentView;
		
		//if(updateDel && updateSel)
		//		[updateDel performSelector:updateSel withObject:self];
		if(updateSel != null) {
			try {
				Method method = GameScene.class.getMethod(updateSel, new Class[] {Object.class});
				method.invoke(GameScene.gamescene, this);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		m_pMenu = CCMenu.menu();
		m_pMenu.setPosition(0,0);
		this.addChild(m_pMenu, 2);
		

		initSubViewsWithName(itemDef.itemName, itemDef.imageType, itemDef.imageName);
	}
	
//	- (void)dealloc 
//	{
//		[menuCommand release];
//		[menuName release];
//		
//		[super dealloc];
//	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//- (void)selectionAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	//{
	//}
	//
	//- (void)deselectionAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	//{
	//if(needExecute)
	//	[parentMenu onMenuItemSelected:self];
	//}

	//mycode
	public void onMenuItemViewSelected(Object Sender)
	{
		parentMenu.onMenuItemSelected(this);
	}
	
	public void setSelection()
	{
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:kMenuItemAnimationDuration];
		//[UIView setAnimationBeginsFromCurrentState:YES];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(selectionAnimationDidStop:finished:context:)];
		
		//selectionView.frame = self.bounds;
		//selectionView.alpha = 1;
		
		//[UIView commitAnimations];
		
		selected = true;
	}

	public void clearSelection()
	{
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:kMenuItemAnimationDuration];
		//[UIView setAnimationBeginsFromCurrentState:YES];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(deselectionAnimationDidStop:finished:context:)];
		//
		//selectionView.frame = CGRectMake(CGRectGetMidX(self.bounds), CGRectGetMidY(self.bounds), 0, 0);
		//selectionView.alpha = 0;
		//
		//[UIView commitAnimations];
		
		selected = false;
	}

//- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
//{
//[self setSelection];
//needExecute = NO;
//}
//
//- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event
//{
//needExecute = NO;
//
//if(selected)
//	[self clearSelection];
//}
//
//- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
//{
//CGPoint point = [[touches anyObject] locationInView:self];
//
//needExecute = CGRectContainsPoint(self.bounds, point);
//
//if(selected) [self clearSelection];
//}
//
//- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
//{
//BOOL internal; 
//CGPoint point = [[touches anyObject] locationInView:self];
//
//internal = CGRectContainsPoint(self.bounds, point);
//
//if(internal != selected)
//{
//	if(internal)
//		[self setSelection];
//	else
//		[self clearSelection];
//}
//
//selected = internal;
//}	

	int prevMenuID()
	{
		return parentMenu.prevMenuID;
	}

}
