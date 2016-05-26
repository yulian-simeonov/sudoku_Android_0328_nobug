package com.gold.sudoku;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

public class ProgressToolBar extends ToolBarView{
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	ButtonItemDef _buttonsMiddleBar_progress[] = 
	{
		//{1, {5, 5, 30, 30}, kIconControlBack, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onPrev:"},
		new ButtonItemDef(1, CGPoint.ccp(20, 20), ImageType.kIconControlBack, ImageType.kIconControlBackSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onPrev"),
		//{2, {39, 5, 30, 30}, kIconControlPlay, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onPlayPause:"},
		new ButtonItemDef(2, CGPoint.ccp(54, 20), ImageType.kIconControlPlay, ImageType.kIconControlPlaySel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onPlayPause"),
		//{3, {74, 5, 30, 30}, kIconControlForward, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onNext:"},
		new ButtonItemDef(3, CGPoint.ccp(89, 20), ImageType.kIconControlForward, ImageType.kIconControlForwardSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onNext"),
		//{102, {284, 5, 30, 30}, kImageIconBarCancel, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onClose:"},
		new ButtonItemDef(102, CGPoint.ccp(299, 20), ImageType.kImageIconBarCancel, ImageType.kImageIconBarCancelSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onClose"),
	};
	
	CCMenu m_pMenu;
	
	int current;
	int count;	

	//	NSTimer* progressTimer;
	boolean progressTimer = false;
	//id barMessageDelegate;
	Object barMessageDelegate;
	
	CCLabel progressLabel;

	ProgressToolBar (int type, CGPoint pos)
	{
		super(type, pos);
	}
	
//	//- (void)onTimer:(NSTimer*)timer
//	public void _onTimer(float dt)
//	{
//		onNext(null);
//	}
//
//	public void stopProgressTimer()
//	{
//		//if(progressTimer)
//		//{
//		//	[progressTimer invalidate];
//		//	self.progressTimer = nil;
//		//}
//		this.unschedule("_onTimer");
//		progressTimer = false;
//	}
//
//	public void startProgressTimer()
//	{
//		stopTimer();
//		progressTimer = true;
//		
//		//self.progressTimer = [NSTimer timerWithTimeInterval:2.0 target:self selector:@selector(onTimer:) userInfo:nil repeats:YES];
//		//[[NSRunLoop currentRunLoop] addTimer:progressTimer forMode:NSDefaultRunLoopMode];
//		this.schedule("_onTimer", 2.0f);
//	}

	public void updateLabel()
	{
		progressLabel.setString(String.format("%d / %d", current+1, count));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//- (void)initProgressBar:(id)barDelegate
	public void initProgressBar(Object barDelegate)
	{
		messageDelegate = this;
		barMessageDelegate = barDelegate;
				
		//loadButtons(_buttonsMiddleBar_progress count:(sizeof(_buttonsMiddleBar_progress)/sizeof(ButtonItemDef)));
		loadButtons(_buttonsMiddleBar_progress, 4);
		
		//UILabel* label = [[UILabel alloc] initWithFrame:rcHistoryLabel];
		//label.font = [UIFont boldSystemFontOfSize:22*IPADSCL];
		//label.backgroundColor = [UIColor clearColor];
		//label.textColor = [UIColor colorWithRed:0.7 green:0.7 blue:0.7 alpha:1.0];
		//label.shadowColor = [UIColor colorWithRed:0.3 green:0.3 blue:0.3 alpha:0.7];
		//label.shadowOffset = CGSizeMake(1.0, 1.0);
		//self.progressLabel = label;
		//[self addSubview:label];
		//[label release];
		
		CCLabel label;
		label = CCLabel.makeLabel("xxxxxxxxxx", CGSize.make(this._view.getContentSize().width, this._view.getContentSize().height), TextAlignment.CENTER, "Marker Felt", 22);
		label.setColor(ccColor3B.ccc3(158, 158, 158));
		label.setPosition(Resolution.rcHistoryLabel.x, Resolution.rcHistoryLabel.y);
		progressLabel = label;
		this._view.addChild(label);

	}
	
	//- (void)dealloc
	//{
	//	[progressTimer release];
	//	[barMessageDelegate release];
	//	[progressLabel release];
	//	
	//	[super dealloc];
	//}

	public void setCount(int _count)
	{
		current = 0;
		count = _count;
	
		updateLabel();
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onPrev(Object sender)
	{
		current -= 1;
		if(current < 0)
			current = (count - 1);
		
		updateLabel();
		
		//SEL selector = NSSelectorFromString(@"onProgressShowIndex:");
		//if(selector)
		//	[barMessageDelegate performSelector:selector withObject:self];
		((GameScene)barMessageDelegate).onProgressShowIndex(this);
	}
	
	public void onPlayPause(Object sender)
	{
		if(progressTimer == true)
		{
			((GameScene)barMessageDelegate).stopProgressTimer();
			//self getButtonByID:2] setImagesID:kIconControlPlay hilight:kImageBarEmpty];
			CCMenuItemSprite button = getButtonByID(2);
			button.setNormalImage(MainActivity.utils_GetImage(ImageType.kIconControlPlay));
			button.setSelectedImage(MainActivity.utils_GetImage(ImageType.kIconControlPlaySel));
		}
		else
		{
			((GameScene)barMessageDelegate).startProgressTimer();
			onNext(null);
			//[[self getButtonByID:2] setImagesID:kIconControlPause hilight:kImageBarEmpty];
			CCMenuItemSprite button = getButtonByID(2);
			button.setNormalImage(MainActivity.utils_GetImage(ImageType.kIconControlPause));
			button.setSelectedImage(MainActivity.utils_GetImage(ImageType.kIconControlPauseSel));
		}
	}

	public void onNext(Object sender)
	{
		current += 1;
		if(current >= count)
			current = 0;
		
		updateLabel();
		
		//SEL selector = NSSelectorFromString(@"onProgressShowIndex:");
		//if(selector)
		//	[barMessageDelegate performSelector:selector withObject:self];
		((GameScene)barMessageDelegate).onProgressShowIndex(null);
	}

	public void onClose(Object sender)
	{
		((GameScene)barMessageDelegate).stopProgressTimer();
		
		CCMenuItemSprite button = getButtonByID(2);
		button.setNormalImage(MainActivity.utils_GetImage(ImageType.kIconControlPlay));
		button.setSelectedImage(MainActivity.utils_GetImage(ImageType.kIconControlPlaySel));

		//SEL selector = NSSelectorFromString(@"onProgressClose:");
		//if(selector)
		//	[barMessageDelegate performSelector:selector withObject:self];
		((GameScene)barMessageDelegate).onProgressClose(this);
	}
	
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
