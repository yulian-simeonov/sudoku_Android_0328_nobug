package com.gold.sudoku;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemImage;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;


public class ImageViewer extends CCLayer {
	CCMenu m_pMenu;
	boolean endflag = false;

	ImageViewer()
	{
		CCSprite m_pBGSprite = CCSprite.sprite("CreditsScroller.png");
        m_pBGSprite.setPosition(GB.g_winSize.width/2, GB.g_winSize.height/2);
        m_pBGSprite.setScaleX(GB.g_winSize.width / m_pBGSprite.getContentSize().width);
        m_pBGSprite.setScaleY(GB.g_winSize.height /m_pBGSprite.getContentSize().height);
        addChild(m_pBGSprite, 0);
        
		m_pMenu = CCMenu.menu();
		m_pMenu.setPosition(0, 0);
		this.addChild(m_pMenu, 1);
      
		CCMenuItemImage button = CCMenuItemImage.item("btn_close.png",
				"btn_close_sel.png",
				null,
				this,
				"onClose");
		button.setPosition(CGPoint.ccp(GB.g_winSize.width/2, GB.getY_(button.getContentSize().height/2)));
		button.setScaleX(GB.g_winSize.width / button.getContentSize().width);
		button.setScaleY(GB.g_fScaleY);
		m_pMenu.addChild(button);
	}
	
	public void onClose(Object sender)
	{
		this.removeFromParentAndCleanup(true);
	}
}
