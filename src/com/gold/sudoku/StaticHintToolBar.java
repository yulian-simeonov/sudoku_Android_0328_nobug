package com.gold.sudoku;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

public class StaticHintToolBar extends ToolBarView {
	CCMenu m_pMenu;
	Object barMessageDelegate;
	CCLabel nameLabel;

	ButtonItemDef _buttonsMiddleBar_ststicHint[] = 
	{
		//{102, {284, 5, 30, 30}, kImageIconBarCancel, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, @"onClose:"},
		new ButtonItemDef(102, CGPoint.ccp(299, 20), ImageType.kImageIconBarCancel, ImageType.kImageIconBarCancelSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onClose"),
	};
	

	StaticHintToolBar(int type, CGPoint pos)
	{
		super(type, pos);
	}

	//- (void)initStaticHintBar:(id)barDelegate
	public void initStaticHintBar(Object barDelegate)
	{
		messageDelegate = this;
		barMessageDelegate = barDelegate;
		
		//loadButtons(_buttonsMiddleBar_ststicHint,  count:(sizeof(_buttonsMiddleBar_ststicHint)/sizeof(ButtonItemDef)));
		loadButtons(_buttonsMiddleBar_ststicHint,  1);
		
		//UILabel* label = [[UILabel alloc] initWithFrame:CGRectMakeScale(5, 5, 249 - 5, 30, IPADSCL)];
		//label.font = [UIFont boldSystemFontOfSize:22*IPADSCL];
		//label.backgroundColor = [UIColor clearColor];
		//label.textColor = [UIColor colorWithRed:0.7 green:0.7 blue:0.7 alpha:1.0];
		//label.shadowColor = [UIColor colorWithRed:0.3 green:0.3 blue:0.3 alpha:0.7];
		//label.shadowOffset = CGSizeMake(1.0, 1.0);
		//label.adjustsFontSizeToFitWidth = YES;
		//self.nameLabel = label;
		//[self addSubview:label];
		//[label release];

		CCLabel label;
		label = CCLabel.makeLabel("xxxxxxxxxx", CGSize.make(this._view.getContentSize().width, this._view.getContentSize().height), TextAlignment.LEFT, "Marker Felt", 22);
		label.setColor(ccColor3B.ccc3(158, 158, 158));
		label.setPosition(Resolution.rcHistoryLabel.x, Resolution.rcHistoryLabel.y);
		nameLabel = label;
		this._view.addChild(label);
	}
	
//	- (void)dealloc
//	{
//		[barMessageDelegate release];
//		[nameLabel release];
//	
//		[super dealloc];
//	}
//	
	public void setLabel(String label)
	{
		nameLabel.setString(label);
	}
	
	public void onClose(Object sender)
	{
//		SEL selector = NSSelectorFromString(@"onStaticHintClose:");
//		if(selector)
//			[barMessageDelegate performSelector:selector withObject:self];
		((GameScene)barMessageDelegate).onStaticHintClose(this);
	}
}
