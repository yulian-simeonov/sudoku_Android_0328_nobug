package com.gold.sudoku;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCLabel.TextAlignment;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;

public class PausedViewController extends CCLayer {

	PausedViewController()
	{
		CCSprite m_pBGSprite = CCSprite.sprite("isudokusplash.png");
        m_pBGSprite.setPosition(GB.g_winSize.width/2, GB.g_winSize.height/2);
        m_pBGSprite.setScaleX(GB.g_winSize.width / m_pBGSprite.getContentSize().width);
        m_pBGSprite.setScaleY(GB.g_winSize.height /m_pBGSprite.getContentSize().height);
        addChild(m_pBGSprite, 0);
        
		CCSprite m_pColorSprite = CCSprite.sprite("white_bg.png");
		m_pColorSprite.setPosition(this.getContentSize().width/2, this.getContentSize().height /2);
		m_pColorSprite.setScaleX(GB.g_winSize.width / m_pColorSprite.getContentSize().width);
		m_pColorSprite.setScaleY(GB.g_winSize.height /m_pColorSprite.getContentSize().height);
        this.addChild(m_pColorSprite, 1);
       
		CCLabel label = CCLabel.makeLabel("Game Paused",
				CGSize.make(320, 50),
				TextAlignment.CENTER, "Arial-BoldMT",
				30);
		label.setColor(ccColor3B.ccBLACK);
		label.setPosition(this.getContentSize().width/2, this.getContentSize().height * 2/3);
		label.setScale(GB.g_fScaleX);
		this.addChild(label, 2);

        CCMenu m_pMenu;
		CCMenuItemSprite button = CCMenuItemSprite.item(MainActivity.utils_GetImage(ImageType.kImageBtnResume),
			MainActivity.utils_GetImage(ImageType.kImageBtnResumeSel),
			null,
			this,
			"_onResume");
		button.setPosition(this.getContentSize().width/2, this.getContentSize().height /2);
		button.setScaleX(GB.g_fScaleX);
		button.setScaleY(GB.g_fScaleY);

		CCLabel resume = CCLabel.makeLabel("Resume Game",
				CGSize.make(320, 50),
				TextAlignment.CENTER, "Arial-BoldMT",
				30);
		resume.setColor(ccColor3B.ccWHITE);
		resume.setPosition(button.getContentSize().width/2, button.getContentSize().height /2);
		button.addChild(resume);

		m_pMenu = CCMenu.menu(button);
		m_pMenu.setPosition(0, 0);
		this.addChild(m_pMenu, 2);
	}
	
	public void _onResume(Object sender)
	{
		this.removeAllChildren(true);
		this.removeFromParentAndCleanup(true);
		
		MainActivity.stateGamePaused = false;
	}
}
