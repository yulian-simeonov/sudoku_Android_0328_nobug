package com.gold.sudoku;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCDelayTime;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;

public class MainScene extends CCLayer {
    CCSprite m_pBGSprite;

	@Override
	public void onExit() {
		removeAllChildren(true);
	    //CCTextureCache.sharedTextureCache().removeAllTextures();
	}

//	- (void)mainShowAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
//	{
//		[splashScreen removeFromSuperview];
//		self.splashScreen = nil;
//	}

	//- (void)onSplashTimer:(NSTimer*)theTimer
	public void onSplashTimer()
	{
		//[theTimer invalidate];
		
//		[window addSubview:viewController.view];
//		viewController.view.alpha = 0;
		
//		[UIView beginAnimations:nil context:nil];
//		[UIView setAnimationDuration:0.8];
//		[UIView setAnimationDelegate:self];
//		[UIView setAnimationDidStopSelector:@selector(mainShowAnimationDidStop:finished:context:)];
//		viewController.view.alpha = 1;
//		[UIView commitAnimations];
		
		CCScene scene = CCScene.node();
		scene.addChild(new GameScene(), -1);
		CCDirector.sharedDirector().runWithScene(scene);

	}

//	- (void)splashAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void splashAnimationDidStop()
	{
//		NSTimer* timer = [NSTimer timerWithTimeInterval:2.0 target:self selector:@selector(onSplashTimer:) userInfo:nil repeats:NO];
//		[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
		onSplashTimer();
	}

	MainScene(){
		m_pBGSprite = CCSprite.sprite("isudokusplash.png");
        m_pBGSprite.setPosition(GB.g_winSize.width/2, GB.g_winSize.height/2);
        m_pBGSprite.setScaleX(GB.g_winSize.width / m_pBGSprite.getContentSize().width);
        m_pBGSprite.setScaleY(GB.g_winSize.height /m_pBGSprite.getContentSize().height);
        addChild(m_pBGSprite, 0);
        
		//[UIView beginAnimations:nil context:nil];
		//[UIView setAnimationDuration:1.5];
		//[UIView setAnimationDelegate:self];
		//[UIView setAnimationDidStopSelector:@selector(splashAnimationDidStop:finished:context:)];
		//splashScreen.frame = window.bounds;
		//[UIView commitAnimations];

		//m_pBGSprite.setScale(0.1f);
		////CCScaleTo scaleX_ani = CCScaleTo.action(2.0f, GB.g_winSize.width / m_pBGSprite.getContentSize().width);
		//CCScaleTo scaleX_ani = CCScaleTo.action(2.0f, GB.g_fScaleX);
		//CCScaleTo scaleY_ani = CCScaleTo.action(2.0f, GB.g_winSize.height /m_pBGSprite.getContentSize().height);
        
        m_pBGSprite.runAction(CCSequence.actions(
        		//scaleX_ani,
	    		CCDelayTime.action(2.0f),
				CCCallFunc.action(this, "splashAnimationDidStop")));
        //m_pBGSprite.runAction(scaleY_ani);
    	
        SoundUtils.Sounds_PlayStartup();
	}
	
	

}
