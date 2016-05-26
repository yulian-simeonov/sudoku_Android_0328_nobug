package com.gold.sudoku;

import org.cocos2d.config.ccMacros;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.menus.CCMenu;
import org.cocos2d.menus.CCMenuItemSprite;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;

public class GameScene extends CCLayer {
	
	public static MenuDefenitions _menuDefinitions = new MenuDefenitions();
	
	public static GameScene gamescene;
	public static int gameNumber;
	public static int gameLevel;

//    GADBannerView *bannerView_;
//    UIView *adView;
//    NSDate *endDate,*startDate;
//
	
    CCMenu m_pStateMenu;

	//ToolBarView toolBarTop;
	//ToolBarView toolBarBottom;
	//StateToolBar toolBarMiddle;
    ToolBarView toolBarTop;
    ToolBarView toolBarBottom;
    ToolBarView toolBarBottomEmpty;
    StateToolBar toolBarMiddle;
	
	ToolBarView toolBarMiddleCandidate;
	ToolBarView toolBarBottomCandidate;
	
	ProgressToolBar toolBarMiddleProgress;
	public static HistoryToolBar toolBarHistory;
	
	StaticHintToolBar toolBarStaticHint;
	
	public static GameBoardView boardView;
	
	FlyingKeypadView keypadView;
	boolean m_bkeypadControl;
	
	int activeMode;
	ToolBarView activeBarMiddle;
	ToolBarView prevBarMiddle;
	ToolBarView activeBarBottom;
	ToolBarView prevBarBottom;

   private static GameScene _sharedGameLayer = null;
		

	GameScene(){
		_sharedGameLayer = this;
		gamescene = this;
		loadView();
		viewDidLoad();
		
		this.schedule("TimerCount", 1.0f);

		this.setIsTouchEnabled(true);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void initMainScreenBars()
	{
		//self.toolBarTop = [[ToolBarView alloc] initWithFrame:kPositionBarTop];
		toolBarTop = new ToolBarView(ImageType.kImageBarTop, Resolution.kPositionBarTop);
		//toolBarTop.barImage = kImageBarTop;
		toolBarTop.messageDelegate = this;
		//[self.view addSubview:toolBarTop];
		this.addChild(toolBarTop._view);

		//self.toolBarBottom = [[ToolBarView alloc] initWithFrame:kPositionBarBottom];
		toolBarBottom = new ToolBarView(ImageType.kImageBarBottom, Resolution.kPositionBarBottom);
		//toolBarBottom.barImage = kImageBarBottom;
		toolBarBottom.messageDelegate = this;
		((ToolBarView)toolBarBottom).loadButtons(ToolBarDefenitions._buttonsBottomBar, ToolBarDefenitions._buttonsBottomBarCount);
		//[self.view addSubview:toolBarBottom];
		activeBarBottom = toolBarBottom;
		this.addChild(toolBarBottom._view, 1);

		toolBarBottomEmpty = new ToolBarView(ImageType.kImageBarBottom, Resolution.kPositionBarBottom);
		toolBarBottom.messageDelegate = this;

		toolBarMiddle = new StateToolBar(ImageType.kImageBarMiddle, Resolution.kPositionBarMiddle);
		//toolBarMiddle.barImage = kImageBarMiddle;
		toolBarMiddle.messageDelegate = this;
		toolBarMiddle.initStateBar();
		//[self.view addSubview:toolBarMiddle];
		activeBarMiddle = toolBarMiddle;
		this.addChild(toolBarMiddle._view, 1);

		//self.toolBarBottomCandidate = [[ToolBarView alloc] initWithFrame:kPositionBarBottom];
		toolBarBottomCandidate = new ToolBarView(ImageType.kImageBarBottom, Resolution.kPositionBarBottom);
		//toolBarBottomCandidate.barImage = kImageBarBottom;
		toolBarBottomCandidate.messageDelegate = this;
		toolBarBottomCandidate.loadButtons(ToolBarDefenitions._buttonsBottomBarCandidate, ToolBarDefenitions._buttonsBottomBarCandidateCount);

		//self.toolBarMiddleCandidate = [[ToolBarView alloc] initWithFrame:kPositionBarMiddle];
		toolBarMiddleCandidate = new ToolBarView(ImageType.kImageBarMiddle, Resolution.kPositionBarMiddle);
		//toolBarMiddleCandidate.barImage = kImageBarMiddle;
		toolBarMiddleCandidate.messageDelegate = this;
		toolBarMiddleCandidate.loadButtons(ToolBarDefenitions._buttonsMiddleBarCandidate, ToolBarDefenitions._buttonsMiddleBarCandidateCount);

		//self.toolBarMiddleProgress = [[ProgressToolBar alloc] initWithFrame:kPositionBarMiddle];
		toolBarMiddleProgress = new ProgressToolBar(ImageType.kImageBarMiddle, Resolution.kPositionBarMiddle);
		//toolBarMiddleProgress.barImage = kImageBarMiddle;
		toolBarMiddleProgress.initProgressBar(this);

		//self.toolBarHistory = [[HistoryToolBar alloc] initWithFrame:kPositionBarMiddle];
		toolBarHistory = new HistoryToolBar(ImageType.kImageBarMiddle, Resolution.kPositionBarMiddle);
		//toolBarHistory.barImage = kImageBarMiddle;
		toolBarHistory.messageDelegate = this;
		toolBarHistory.initHistoryBar();

		//self.toolBarStaticHint = [[StaticHintToolBar alloc] initWithFrame:kPositionBarMiddle];
		toolBarStaticHint = new StaticHintToolBar(ImageType.kImageBarMiddle, Resolution.kPositionBarMiddle);
		//toolBarStaticHint.barImage = kImageBarMiddle;
		toolBarStaticHint.initStaticHintBar(this);
	}

	public void initMainScreenBoard()
	{
		//self.boardView = [[GameBoardView alloc] initWithFrame:kPositionBoard];
		boardView = new GameBoardView(Resolution.kPositionBoard);
		//boardView.parentView = self;
		boardView.parentView = this;
		//[self.view addSubview:boardView];
		this.addChild(boardView, 1);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void loadView() 
	{
		activeMode = GC.kGameModeNormal;
		initMainScreenBars();
		initMainScreenBoard();

		FlyingKeypadView _keypadView = new FlyingKeypadView(this);
		_keypadView._init();
		if(boardView.activeSelection != null)
			_keypadView.selectItemX(boardView.activeSelection.col, boardView.activeSelection.row);
		keypadView = _keypadView;
		//[_keypadView release];
		
		SudokuUtils.SudokuUtils_ResetBoard();
	}

	public void viewDidLoad() 
	{
		//[super viewDidLoad];

		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		if(MainActivity.firstStart)
		{
			//[self onStartNewGame:nil];
			onStartNewGame(null);
			//[self performSelector:@selector(showStartMessagealert) withObject:nil afterDelay:0.5];
			showStartMessagealert();
		}
		else
		{
			onUtilCommandCommon();
		}

//		//****************** Google Ads ***************************//
//		self.startDate = [NSDate date];
//		self.endDate = [NSDate date];
//		if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad){
//			if (!adView) {
//				if (ADS_ENABLE == 1) {
//					adView = [[[UIView alloc]initWithFrame:CGRectMake(0,0, 768, 90)]autorelease];
//					[adView setBackgroundColor:[UIColor whiteColor]];
//					[self.view addSubview:adView];
//					NSLog(@"main iPad");
//				
//					bannerView_ = [[GADBannerView alloc] initWithAdSize:kGADAdSizeSmartBannerPortrait];
//					bannerView_.adUnitID = @"7a0de5531aea44f6"; //Mediation ID iPAd
//					bannerView_.delegate = self;
//					bannerView_.rootViewController = self;
//				
//					[adView addSubview:bannerView_];
//					[bannerView_ loadRequest:[GADRequest request]];
//				}
//			}
//		} else {
//			if (!adView) {
//				if (ADS_ENABLE == 1) {
//					adView = [[[UIView alloc]initWithFrame:CGRectMake(0, -5, self.view.frame.size.width, 50)]autorelease];
//					[adView setBackgroundColor:[UIColor clearColor]];
//					[self.view addSubview:adView];
//					NSLog(@"main iphone");
//				
//					bannerView_ = [[GADBannerView alloc] initWithAdSize:kGADAdSizeBanner];
//					bannerView_.adUnitID = @"db06251cd01445bf"; //Mediation ID IPHONE
//					bannerView_.rootViewController = self;
//					bannerView_.delegate = self;
//				
//					bannerView_.transform = CGAffineTransformMakeScale(1, 0.80);
//				
//					[adView addSubview:bannerView_];
//					[bannerView_ loadRequest:[GADRequest request]];
//				}
//			}
//		}
//
//		if (ADS_ENABLE == 1) {
//			[NSTimer scheduledTimerWithTimeInterval:20.0
//			target:self
//			selector:@selector(sendBannerRequest)
//			userInfo:nil
//			repeats:YES];
//		}
	}

//	- (void)sendBannerRequest{
//		if (bannerView_) {
//		self.endDate = [NSDate date];
//		NSTimeInterval timeDifference = [self.endDate timeIntervalSinceDate:self.startDate];
//		// NSLog(@"Interval %f",timeDifference);
//	
//		if (timeDifference >= 40) {
//	
//		NSLog(@"again sending request");
//		self.startDate = [NSDate date];
//		[bannerView_ loadRequest:[GADRequest request]];
//		}
//	
//	
//	
//		}
//	}

	public void showStartMessagealert ()
	{
		String message = "Complete the SuDoku with the numbers 1 to 9 without repeating a number in any Column, Row, or 3x3 Grid.\nEvery SuDoku has just One solution.\nThis one is 'Simple' to get you started.\nGood Luck!";
		GB.AlertUtils_ShowDoneAlert("RULES OF THE GAME", message);
	}

//	- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation
//	{
//		if(ISIPHONE)
//		return (toInterfaceOrientation == UIInterfaceOrientationPortrait);
//		else
//		return (toInterfaceOrientation == UIInterfaceOrientationPortrait || toInterfaceOrientation == UIInterfaceOrientationPortraitUpsideDown);
//	}

//	public void dealloc 
//	{
//	[self setStartDate:nil];
//	[self setEndDate:nil];
//	[toolBarTop release];
//	[toolBarBottom release];
//	[toolBarMiddle release];
//
//	[toolBarMiddleCandidate release];
//	[toolBarBottomCandidate release];
//
//	[toolBarMiddleProgress release];
//	[toolBarHistory release];
//
//	[toolBarStaticHint release];
//
//	[boardView	release];
//	[keypadView release];
//
//	[super dealloc];
//}

     public static GameScene sharedGameLayer(){
    	return _sharedGameLayer;
    }
    
	public void viewDidAppear(boolean animated)
	{
		//[super viewDidAppear:animated];
	
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		MainActivity.stateGamePaused = false;
	}
	
    public void onResume( Object sender )
    {
    	CCDirector.sharedDirector().resume();
    	resumeSchedulerAndActions();
		MainActivity.stateGamePaused = false;
    }

	public void viewDidDisappear(boolean animated)
	{
		//[super viewDidDisappear:animated];
	
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		MainActivity.stateGamePaused = true;
	}
	
    public void onPause( Object sender )
    {
    	CCDirector.sharedDirector().pause();
    	pauseSchedulerAndActions();
    }

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//public void redrawAllSubviews:(UIView*)viewToRedraw
//	public void redrawAllSubviews(UIView*)viewToRedraw
//	{
//		if(viewToRedraw == null)
//			viewToRedraw = self.view;
//		
//		[viewToRedraw setNeedsDisplay];
//		
//		if(!viewToRedraw.subviews)
//			return;
//		
//		for(int index = 0; index < [viewToRedraw.subviews count]; index++)
//		{
//			UIView* childView = [viewToRedraw.subviews objectAtIndex:index];
//			[self redrawAllSubviews:childView];
//		}
//	}

	public void redrawAllBars()
	{
		//[self redrawAllSubviews:toolBarTop];
		//[self redrawAllSubviews:toolBarBottom];
		//[self redrawAllSubviews:toolBarBottom];
		//[self redrawAllSubviews:toolBarMiddleCandidate];
		//[self redrawAllSubviews:toolBarBottomCandidate];
		//[self redrawAllSubviews:toolBarStaticHint];
	}

	public void onUtilCommandCommon()
	{
		SudokuUtils.SudokuHistory_RestoreActive();
		boardView.resetState();
		switchToGameMode(GC.kGameModeNormal);
//		[self redrawAllSubviews:self.view];
//		[self redrawAllBars];
		
		keypadView.removeKeypad();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void updateBottomCandidateBarToNumbers(int color)
	{
		int index;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		for(index = 1; index <= 9; index++)
		{
			CCSprite imageNormal = skinManager.getNumberImageWithValue(index, color, false);
			CCSprite imageSelected = skinManager.getNumberImageWithValue(index, color, true);
			
			//ToolBarButtonView* button = [toolBarBottomCandidate getButtonByID:index];
			CCMenuItemSprite button = toolBarBottomCandidate.getButtonByID(index);
			//[button setImages:imageNormal hilight:imageSelected];
			button.setNormalImage(imageNormal);
			button.setSelectedImage(imageSelected);
		}
	}

	public void updateBottomCandidateBarToPossibleNumbers()
	{
		int index;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		for(index = 1; index <= 9; index++)
		{
			//UIImage* imageNormal = [skinManager getCandidateButtonImageWithValue:index selected:NO];
			//UIImage* imageSelected = [skinManager getCandidateButtonImageWithValue:index selected:YES];
			CCSprite imageNormal = skinManager.getCandidateButtonImageWithValue(index, false);
			CCSprite imageSelected = skinManager.getCandidateButtonImageWithValue(index, true);
			
			//ToolBarButtonView* button = [toolBarBottomCandidate getButtonByID:index];
			CCMenuItemSprite button = toolBarBottomCandidate.getButtonByID(index);
			//[button setImages:imageNormal hilight:imageSelected];
			button.setNormalImage(imageNormal);
			button.setSelectedImage(imageSelected);
		}
	}

	public void updateToEnterSudokuState()
	{
		updateBottomCandidateBarToNumbers(0);
		
		for(int index = 1; index <= 6; index++)
			toolBarMiddleCandidate.getButtonByID(index).setVisible(false); //].hidden = YES;
		
		toolBarMiddleCandidate.getButtonByID(100).setVisible(false); //].hidden = YES;
	}

	public void updateToEnterCandidateState()
	{
		for(int index = 1; index <= 6; index++)
			toolBarMiddleCandidate.getButtonByID(index).setVisible(true); // .hidden = NO;
		
		toolBarMiddleCandidate.getButtonByID(100).setVisible(true); //hidden = NO;
		toolBarMiddleCandidate.getButtonByID(101).setVisible(true); //hidden = NO;
		
		//[[toolBarMiddleCandidate getButtonByID:100] setImagesID:kImageIconBarCandidate hilight:kImageBarEmpty];
		toolBarMiddleCandidate.getButtonByID(100).setNormalImage(MainActivity.utils_GetImage(ImageType.kImageIconBarCandidate));
	}

	public void updateToEnterCandidatePossibleState()
	{
		for(int index = 1; index <= 6; index++)
			toolBarMiddleCandidate.getButtonByID(index).setVisible(false); //.hidden = YES;
		
		toolBarMiddleCandidate.getButtonByID(100).setVisible(true); //hidden = NO;
		toolBarMiddleCandidate.getButtonByID(101).setVisible(true); //hidden = NO;
		//
		//[[toolBarMiddleCandidate getButtonByID:100] setImagesID:kImageIconBarPossible hilight:kImageBarEmpty];
		toolBarMiddleCandidate.getButtonByID(100).setNormalImage(MainActivity.utils_GetImage(ImageType.kImageIconBarPossible));
	}

	public void updateCandidateBarsState()
	{
		if(activeMode == GC.kGameModeEnterOwnSudoku)
			updateToEnterSudokuState();
		else
			updateToEnterCandidateState();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//public void toolbarMiddleAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void toolbarMiddleAnimationDidStop()
	{
		if(prevBarMiddle != null)
		{
			prevBarMiddle._view.removeFromParentAndCleanup(false);
			prevBarMiddle = null;
		}
	}

	//public void switchMiddleBarTo:(UIView*)toView
	public void switchMiddleBarTo(ToolBarView toView)
	{
		if(toView == activeBarMiddle)
			return;
		
		if(prevBarMiddle != null)
		{
			//[prevBarMiddle removeFromSuperview]; 
			prevBarMiddle._view.removeFromParentAndCleanup(false); 
			prevBarMiddle = null;
			//activeBarMiddle.alpha = 1.0;
			activeBarMiddle._view.setVisible(true);
		}
		
		prevBarMiddle = activeBarMiddle;
		activeBarMiddle = toView;
		
		//toView.alpha = 0.0;
		toView._view.setVisible(false);
		//[self.view addSubview:toView];
		this.addChild(toView._view, 1);
		
//		[UIView beginAnimations:@"MiddleBarAnimating" context:nil];
//		[UIView setAnimationDuration:kGameViewViewsAnimationDuration];
//		[UIView setAnimationDelegate:self];
//		[UIView setAnimationBeginsFromCurrentState:NO];
//		[UIView setAnimationDidStopSelector:@selector(toolbarMiddleAnimationDidStop:finished:context:)];
		
		toolbarMiddleAnimationDidStop();
		//toView.alpha = 1.0;
		toView._view.setVisible(true);
		
//		[UIView commitAnimations];
	}
		
	//- (void)toolbarBottomAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
	public void toolbarBottomAnimationDidStop()
	{
		if(prevBarBottom != null)
		{
			prevBarBottom._view.removeFromParentAndCleanup(false);
			prevBarBottom = null;
		}
	}
	
	public void switchBottomBarTo(ToolBarView toView)
	{
		if(toView == activeBarBottom)
			return;
		
		if(prevBarBottom != null)
		{
			//[prevBarBottom removeFromSuperview]; 
			prevBarBottom._view.removeFromParentAndCleanup(true); 
			prevBarBottom = null;
			//activeBarBottom.alpha = 1.0;
			activeBarBottom._view.setVisible(true);
		}
		
		prevBarBottom = activeBarBottom;
		activeBarBottom = toView;
		
		//toView.alpha = 0.0;
		toView._view.setVisible(false);
		//[self.view addSubview:toView];
		this.addChild(toView._view);
		
//		[UIView beginAnimations:@"BottomBarAnimating" context:nil];
//		[UIView setAnimationDuration:kGameViewViewsAnimationDuration];
//		[UIView setAnimationDelegate:self];
//		[UIView setAnimationBeginsFromCurrentState:NO];
//		[UIView setAnimationDidStopSelector:@selector(toolbarBottomAnimationDidStop:finished:context:)];
		
		toolbarBottomAnimationDidStop();
		//toView.alpha = 1.0;
		toView._view.setVisible(true);
		
//		[UIView commitAnimations];
	}

	//public void switchToGameMode(GameModeType mode)
	public void switchToGameMode(int mode)
	{
		ToolBarView newMiddleBar = null;
		ToolBarView newBottomBar = null;
		
		if(activeMode == mode)
			return;
		
		switch(mode)
		{
		case GC.kGameModeNormal:
			newMiddleBar = toolBarMiddle;
			newBottomBar = toolBarBottom;
			break;
		
		case GC.kGameModeEnterCandidates:
			newMiddleBar = toolBarMiddleCandidate;
			newBottomBar = toolBarBottomCandidate;
			break;
		
		case GC.kGameModeEnterOwnSudoku:
			newMiddleBar = toolBarMiddleCandidate;
			newBottomBar = toolBarBottomCandidate;
			break;
		
		case GC.kGameModeShowHints:
			newMiddleBar = toolBarMiddleProgress;
			newBottomBar = toolBarBottom;
			break;
		
		case GC.kGameModeShowHistory:
			newMiddleBar = toolBarHistory;
			//newBottomBar = toolBarBottom;
			newBottomBar = toolBarBottomEmpty;
			break;
		
		case GC.kGameModeShowStaticHint:
			newMiddleBar = toolBarStaticHint;
			newBottomBar = toolBarBottom;
			break;
		}
		
		switchMiddleBarTo(newMiddleBar);
		switchBottomBarTo(newBottomBar);
		
		activeMode = mode;	
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	boolean __SolverCallBack(final Grid g)
	{
		return (g.solncount < 2);
	}
	
	int __SolverCallBackFull(final Grid g)
	{
		return 1;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean validateAndProcessGameInProgress()
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();

		if(MainActivity.stateGameInProgress)
			return true;

		GB.AlertUtils_ShowDoneAlert("Error", "Please start new game or enter your own to use this operation.");

		return false;
	}

	boolean validateSudokuSolved()
	{
		Grid grid;
		Grid gridSrc;
		char curGrid[] = new char[90]; //[90];
		char answer[] = new char[90]; //[90];
		boolean result = false;
		
		if(SudokuUtils.SudokuUtils_GetEmptyCellsCount(SudokuUtils.g_gameGrid) != 0)
			return false;
		
		SudokuUtils.SudokuUtils_GridToSolverString(SudokuUtils.g_gameGrid, curGrid);
		
		//init_solve_engine(__SolverCallBackFull, NULL, NULL, 0, 0);
		sudoku_engine.init_solve_engine(0, 0);
		grid = sudoku_engine.solve_sudoku(curGrid);
		if(grid != null)
		{
			gridSrc = grid;
		
			do
			{
				sudoku_engine.format_answer(grid, answer);
			
				SudokuUtils.SudokuUtils_GridToString(SudokuUtils.g_gameGrid, curGrid);
			
				//if(strcmp(curGrid, answer) == 0)
				if(curGrid.equals(answer) == true)
					result = true;
			
				grid = grid.next;
			}
			while(grid != null);
			
			sudoku_engine.free_soln_list(gridSrc);
		}
		
		return result;
	}
	
	String curRatingToName()
	{
		String messages[] = 
		{
			//NSLocalizedString(@"I want to learn the Art of SuDoku",@""),
			//NSLocalizedString(@"Beginner",@""),
			//NSLocalizedString(@"Apprentice",@""),
			//NSLocalizedString(@"Novice",@""),
			//NSLocalizedString(@"Expert",@""),
			//NSLocalizedString(@"SuDoku Master!",@""),

			"I want to learn the Art of SuDoku",
			"Beginner",
			"Apprentice",
			"Novice",
			"Expert",
			"SuDoku Master!",
		};
		
		return messages[SudokuUtils.SudokuStats_GameScoreToRatingIndex()];
	}
	
	public static void leaveReview()
	{
//		if (IS_FREE_VERSION == true)
//		{
//			//FREE VERSION
//			[[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://userpub.itunes.apple.com/WebObjects/MZUserPublishing.woa/wa/addUserReview?id=521559009&type=Purple+Software"]];
//		}
//		else
//		{
//			//PAID
//			[[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"https://userpub.itunes.apple.com/WebObjects/MZUserPublishing.woa/wa/addUserReview?id=565439978&type=Purple+Software"]];
//		}
	}

	//public void onAskReviewResult:(NSNumber*)index
	public static void onAskReviewResult(int index)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		//if([index intValue] == 0)
		if(index == 0)
		{
			MainActivity.reviewGameCount = -1;
			//[self leaveReview];
			leaveReview();
		}
	}

	//public void onDoneMessageClosed:(NSNumber*)index
	public static void onDoneMessageClosed(int index)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		if(MainActivity.reviewGameCount >= 0)
		{
			MainActivity.reviewGameCount++;
			
			if(MainActivity.reviewGameCount == 2)
				//AlertUtils_ShowYesNoAlert(self, @selector(onAskReviewResult:), nil, NSLocalizedString(@"Please leave a review and let us know how we are doing. If there is enough interest then we will continue developing SuDoku. Would you like to leave a review now?",@""));
				GB.AlertUtils_ShowYesNoAlert(null, "Please leave a review and let us know how we are doing. If there is enough interest then we will continue developing SuDoku. Would you like to leave a review now?");
			if(MainActivity.reviewGameCount == 5)
				//AlertUtils_ShowYesNoAlert(self, @selector(onAskReviewResult:), nil, NSLocalizedString(@"Please rate Sudoku if you like it and want us to keep updating it! Would you like to leave a review now?",@""));
				GB.AlertUtils_ShowYesNoAlert(null, "Please rate Sudoku if you like it and want us to keep updating it! Would you like to leave a review now?");
			if(MainActivity.reviewGameCount > 5 && MainActivity.reviewGameCount < 21 && (MainActivity.reviewGameCount % 5) == 0)
				//AlertUtils_ShowYesNoAlert(self, @selector(onAskReviewResult:), nil, NSLocalizedString(@"Are you ready to write a review for Sudoku? Great reviews lead to great updates!",@""));
				GB.AlertUtils_ShowYesNoAlert(null, "Are you ready to write a review for Sudoku? Great reviews lead to great updates!");
		}
	}

	public void onSudokuSolved()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		MainActivity.stateGameInProgress = false;

		onUtilCommandCommon();
		
		if(MainActivity.stateCurSudokuLevel != -1 && MainActivity.stateGameScore != 0)
		{
			SudokuUtils.g_gameStats.totalGameCount += 1;
			int oldScore = SudokuUtils.g_gameStats.totalScore;
			SudokuUtils.g_gameStats.totalScore += MainActivity.stateGameScore;
			int newScore = SudokuUtils.g_gameStats.totalScore;
			
//			[[GameCenter sharedInstance] setScore:newScore];
			
//			int limit = 5000;
//			if(newScore < limit)
//			{
//				if(newScore > 0)
//			[[GameCenter sharedInstance] setAchievement:ACH_BEGINNER percent:100.0*newScore/limit];
//			}
//			else
//			{
//			if(oldScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_BEGINNER percent:100];
//			}
//			
//			if(newScore >= limit)
//			{
//			limit = 40000;
//			if(newScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_APPRENTICE percent:100.0*newScore/limit];
//			else
//			{
//			if(oldScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_APPRENTICE percent:100];
//			}
//			}
//			
//			if(newScore >= limit)
//			{
//			limit = 160000;
//			if(newScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_NOVICE percent:100.0*newScore/limit];
//			else
//			{
//			if(oldScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_NOVICE percent:100];
//			}
//			}
//			
//			if(newScore >= limit)
//			{
//			limit = 640000;
//			if(newScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_EXPERT percent:100.0*newScore/limit];
//			else
//			{
//			if(oldScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_EXPERT percent:100];
//			}
//			}
//			
//			if(newScore >= limit)
//			{
//			limit = 2000000;
//			if(newScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_MASTER percent:100.0*newScore/limit];
//			else
//			{
//			if(oldScore < limit)
//			[[GameCenter sharedInstance] setAchievement:ACH_MASTER percent:100];
//			}
//			}
			
			SudokuUtils.g_gameStats.statsGameCount[MainActivity.stateCurSudokuLevel] += 1;
			
			if(SudokuUtils.g_gameStats.statsScoreMin[MainActivity.stateCurSudokuLevel] > MainActivity.stateGameScore || SudokuUtils.g_gameStats.statsScoreMin[MainActivity.stateCurSudokuLevel] == 0)
				SudokuUtils.g_gameStats.statsScoreMin[MainActivity.stateCurSudokuLevel] = MainActivity.stateGameScore;
			
			if(SudokuUtils.g_gameStats.statsScoreMax[MainActivity.stateCurSudokuLevel] < MainActivity.stateGameScore || SudokuUtils.g_gameStats.statsScoreMax[MainActivity.stateCurSudokuLevel] == 0)
				SudokuUtils.g_gameStats.statsScoreMax[MainActivity.stateCurSudokuLevel] = MainActivity.stateGameScore;
			
			SudokuUtils.g_gameStats.statsScoreFull[MainActivity.stateCurSudokuLevel] += MainActivity.stateGameScore;
			
			if(SudokuUtils.g_gameStats.statsTimeMin[MainActivity.stateCurSudokuLevel] > MainActivity.stateGameTime || SudokuUtils.g_gameStats.statsTimeMin[MainActivity.stateCurSudokuLevel] == 0)
				SudokuUtils.g_gameStats.statsTimeMin[MainActivity.stateCurSudokuLevel] = MainActivity.stateGameTime;
			
			if(SudokuUtils.g_gameStats.statsTimeMax[MainActivity.stateCurSudokuLevel] < MainActivity.stateGameTime || SudokuUtils.g_gameStats.statsTimeMax[MainActivity.stateCurSudokuLevel] == 0)
				SudokuUtils.g_gameStats.statsTimeMax[MainActivity.stateCurSudokuLevel] = MainActivity.stateGameTime;
			
			SudokuUtils.g_gameStats.statsTimeFull[MainActivity.stateCurSudokuLevel] += MainActivity.stateGameTime;
		}
			
		if(MainActivity.stateCurSudokuLevel == -1)
		{
			//AlertUtils_ShowDoneAlert(self, @selector(onDoneMessageClosed:), NSLocalizedString(@"Congratulations!",@""), NSLocalizedString(@"You solved it! However only the inbuilt SuDokus affect your rating.",@""));
			GB.AlertUtils_ShowDoneAlert("Congratulations!", "You solved it! However only the inbuilt SuDokus affect your rating.");
		}
		else if(MainActivity.stateGameScore == 0)
		{
			//AlertUtils_ShowDoneAlert(self, @selector(onDoneMessageClosed:), NSLocalizedString(@"Congratulations!",@""), NSLocalizedString(@"You solved it! Try solving without using hints to improve your rating.",@""));
			GB.AlertUtils_ShowDoneAlert("Congratulations!", "You solved it! Try solving without using hints to improve your rating.");
		}
		else
		{
			//NSString* template = NSLocalizedString(@"Your score has increased by %d\nYour score is now: %d\nRating: %@\nYou have completed:\n%d Simple SuDoku(s)\n%d Easy SuDoku(s)\n%d Medium SuDoku(s)\n%d Hard SuDoku(s)\n%d Very Hard SuDoku(s)\n%d Master SuDoku(s)",@"");
			String template = "Your score has increased by %d\nYour score is now: %d\nRating: %s\nYou have completed:\n%d Simple SuDoku(s)\n%d Easy SuDoku(s)\n%d Medium SuDoku(s)\n%d Hard SuDoku(s)\n%d Very Hard SuDoku(s)\n%d Master SuDoku(s)";
			//NSString* message = [NSString stringWithFormat:template, appDelegate.stateGameScore, g_gameStats.totalScore, [self curRatingToName], g_gameStats.statsGameCount[0], g_gameStats.statsGameCount[1], g_gameStats.statsGameCount[2], g_gameStats.statsGameCount[3], g_gameStats.statsGameCount[4], g_gameStats.statsGameCount[5]];
			String message = String.format(template, MainActivity.stateGameScore, SudokuUtils.g_gameStats.totalScore, curRatingToName(), SudokuUtils.g_gameStats.statsGameCount[0], SudokuUtils.g_gameStats.statsGameCount[1], SudokuUtils.g_gameStats.statsGameCount[2], SudokuUtils.g_gameStats.statsGameCount[3], SudokuUtils.g_gameStats.statsGameCount[4], SudokuUtils.g_gameStats.statsGameCount[5]);
			//AlertUtils_ShowDoneAlert(self, @selector(onDoneMessageClosed:), NSLocalizedString(@"Congratulations!",@""), message);
			GB.AlertUtils_ShowDoneAlert("Congratulations!", message);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onBoardItemSelect()
	{
		if(activeMode == GC.kGameModeEnterOwnSudoku)
			return;
		
		if(!validateAndProcessGameInProgress())
			return;
		
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(MainActivity.prefUseFlyingKeypad)
		{
			keypadView.selectItemX(boardView.activeSelection.col, boardView.activeSelection.row);
			keypadView.updateState();
			keypadView.addKeypad();
		}
		else
		{
			switchToGameMode(GC.kGameModeEnterCandidates);
		
			if(boardView.activeMode)
			{
				updateToEnterCandidateState();
				updateBottomCandidateBarToNumbers(boardView.activeColor);
			}
			else
			{
				updateToEnterCandidatePossibleState();
				updateBottomCandidateBarToPossibleNumbers();
			}
		}
		
		if(!boardView.activeMode)
			SudokuUtils.SudokuHistory_AddCurrentState();
	}

	public void onCandidateSetColor(Object sender)
	{
		CCMenuItemSprite buttonItem = (CCMenuItemSprite)sender;
		
		boardView.setColor(buttonItem.getTag());
		
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();	
		if(MainActivity.prefUseFlyingKeypad)
		{
			keypadView.updateState();
		}
		else
		{
			updateBottomCandidateBarToNumbers(buttonItem.getTag());
		}
	}

	public void onCandidateMode(Object sender)
	{
		boardView.setState(!boardView.activeMode);
		
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();	
		if(MainActivity.prefUseFlyingKeypad)
		{
			keypadView.updateState();
		}
		else
		{
			onBoardItemSelect();
		}
	}

	public void onCandidateCancel(Object sender)	
	{
		boardView.cancelStateToDefaults();
		
		if(activeMode != GC.kGameModeEnterOwnSudoku)
		{
			SudokuUtils.SudokuHistory_AddCurrentState();
			wisardUpdateAutoCandidates();
			switchToGameMode(GC.kGameModeNormal);
		
			//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
			if(MainActivity.prefUseFlyingKeypad)
			{
				if(MainActivity.prefKeypadAutohide)
					keypadView.removeKeypad();
				else
				{
					boardView.updateSelectionWithRow(keypadView.itemY, keypadView.itemX);
					keypadView.updateState();
				}
			}
		}
		else // THEY CANCELLED ENTERING A SUDOKU
		{
			SudokuUtils.SudokuHistory_AddCurrentState();
			wisardUpdateAutoCandidates();
			switchToGameMode(GC.kGameModeNormal);
		
			//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
			if(MainActivity.prefUseFlyingKeypad)
			{
				if(MainActivity.prefKeypadAutohide)
					keypadView.removeKeypad();
				else
				{
					boardView.updateSelectionWithRow(keypadView.itemY, keypadView.itemX);
					keypadView.updateState();
				}
			}
			
			////LOAD A NEW SIMPLE ONE
			onStartNewGame(null);
		}
	}

	public void onCandidateOK(Object sender)
	{
		if(activeMode == GC.kGameModeEnterOwnSudoku)
		{
			boardView.acceptValue();
		
			Grid grid;
			char curGrid[] = new char[90]; //90
			//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
			SudokuUtils.SudokuUtils_GridToSolverString(SudokuUtils.g_gameGrid, curGrid);
		
			//init_solve_engine(__SolverCallBack, NULL, NULL, 1, 0);
			sudoku_engine.init_solve_engine(1, 0);
			grid = sudoku_engine.solve_sudoku(curGrid);
			if(grid != null)
			{
				if(grid.solncount == 1)
				{
					MainActivity.stateGameInProgress = true;
					onUtilCommandCommon();
				}
			
				sudoku_engine.free_soln_list(grid);
			}
			else
			{
				//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Error",@""), NSLocalizedString(@"Enetered Sudoku is invalid. They have no or more than 1 solution. Please enter valid sudoku or start new game.",@""));
				GB.AlertUtils_ShowDoneAlert("Error", "Enetered Sudoku is invalid. They have no or more than 1 solution. Please enter valid sudoku or start new game.");
			}
		}
		else
		{
			boardView.acceptValue();
			switchToGameMode(GC.kGameModeNormal);
			wisardUpdateAutoCandidates();
			
			if(!boardView.activeMode)
				SudokuUtils.SudokuHistory_AddCurrentState();
			
			//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
			
			if(MainActivity.prefUseFlyingKeypad)
			{
				if(MainActivity.prefKeypadAutohide || MainActivity.prefKeypadHideOnOK)
					keypadView.removeKeypad();
				else
				{
					boardView.updateSelectionWithRow(keypadView.itemY, keypadView.itemX);
					keypadView.updateState();
				}
			}
			
			if(validateSudokuSolved())
				onSudokuSolved();
		}
	}

	public void onCandidateSetNumber(Object sender)
	{
		CCMenuItemSprite buttonItem = (CCMenuItemSprite)sender;
		
		boardView.setNumber(buttonItem.getTag());

		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();	

		if(activeMode == GC.kGameModeEnterOwnSudoku)
		{
			boardView.acceptValue();
		}
		else
		{
			if(boardView.activeMode)
			{
				boardView.acceptValue();
				wisardUpdateAutoCandidates();	
				SudokuUtils.SudokuHistory_AddCurrentState();
				switchToGameMode(GC.kGameModeNormal);	
			
				if(MainActivity.prefUseFlyingKeypad)
				{
					keypadView.updateState();
			
					if(MainActivity.prefKeypadAutohide)
						keypadView.removeKeypad();
					else
					{
						boardView.updateSelectionWithRow(keypadView.itemY, keypadView.itemX);
						keypadView.updateState();
					}
				}
		
				if(validateSudokuSolved())
					onSudokuSolved();
			}
			else if(MainActivity.prefUseFlyingKeypad)
			{
				keypadView.updateState();
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onHelp(Object sender)
	{
		//ShowMenu(&_menuHelp, self, self.view);
		ShowMenu(_menuDefinitions._menuHelp);
	}
	
	public void onInfo(Object sender)
	{
		//if(GameCenter.sharedInstance.isAuthenticated())
		if(GameCenter.isAuthenticated())
			_menuDefinitions._menuInfo.count = 7;
		else
			_menuDefinitions._menuInfo.count = 6;
		
		//ShowMenu(&_menuInfo, self, self.view);
		ShowMenu(_menuDefinitions._menuInfo);
	}

	public void onUndo(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		if(!SudokuUtils.SudokuHistory_Undo())
		{
			//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), NSLocalizedString(@"There is nothing to Undo.",@""));
			GB.AlertUtils_ShowDoneAlert("Information", "There is nothing to Undo.");
		}
		else
		{
			wisardUpdateAutoCandidates();
			onUtilCommandCommon();
			SoundUtils.Sounds_PlayEraser();
		}
	}

	public void checkHistory(float dt)
	{
		toolBarHistory.checkend();
	}
	
	
	public void onHistory(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		onUtilCommandCommon();
		switchToGameMode(GC.kGameModeShowHistory);
		toolBarHistory.refresh();
		boardView.showHint(-1);
		
		schedule("checkHistory", 0.1f);
		toolBarHistory.addSliderActivity();
	}

	public void onFlag(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		//ShowMenu(&_menuFlag, self, self.view);
		ShowMenu(_menuDefinitions._menuFlag);
	}

	public void onTransform(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		//ShowMenu(&_menuTransform, self, self.view);
		ShowMenu(_menuDefinitions._menuTransform);
	}

	public void onWisard(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		//ShowMenu(&_menuWisard, self, self.view);
		ShowMenu(_menuDefinitions._menuWisard);
	}

	public void onWand(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		//ShowMenu(&_menuWand, self, self.view);
		ShowMenu(_menuDefinitions._menuWand);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onHelpAbout(Object sender)
	{
		onUtilCommandCommon();
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"About",@""), [NSString stringWithFormat:@"Mastersoft SuDoku %@\n(C) 2012 Mastersoft Ltd",VERSION_TO_DISPLAY]);
		String message = String.format("Mastersoft SuDoku %s\n(C) 2012 Mastersoft Ltd", GC.VERSION_TO_DISPLAY);
		GB.AlertUtils_ShowDoneAlert("About", message);
	}
	
	public void onHelpCredits(Object sender)
	{
		onUtilCommandCommon();
		
		//ImageViewController * viewController = [[ImageViewController  alloc] initWithImageName:@"CreditsScroller.png"];
		////[self presentModalViewController:viewController animated:YES];
		//
		//[UIView transitionWithView:self.view duration:0.8
		//options:UIViewAnimationOptionTransitionCrossDissolve //change to whatever animation you like
		//animations:^ { [self.view addSubview:viewController.view];  }
		//completion:nil];
		////[viewController release];
		//
		
		ImageViewer imgView = new ImageViewer();
		this.addChild(imgView, 12);
	}

	public void onHelpHelp(Object sender)
	{
		onUtilCommandCommon();
		
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		MainActivity.stateGamePaused = true;
		
		//HelpViewController* tmpViewController = [[HelpViewController alloc] initWithNibName:@"HelpViewController" bundle:[NSBundle mainBundle]];
		//HelpViewController* viewController = tmpViewController;
		//viewController.view.frame = self.view.bounds;
		//[viewController loadHTML:@"iSuDoku" ext:@"htm"];
		//
		//[UIView transitionWithView:self.view duration:0.8
		//options:UIViewAnimationOptionTransitionFlipFromTop //change to whatever animation you like
		//animations:^ { [self.view addSubview:viewController.view];  }
		//completion:nil];
	    gotoUrl("http://www.sudoku.com");
	}

	public void gotoUrl(String url)
	{
	    if(url.equals("") || url == null)
	    {
	    	GB.AlertUtils_ShowDoneAlert("Alert", "There is no URL.");
	        return;
	    }
	    
	    //if(url.startsWith("http://") == false)
	    // 	url = (new StringBuilder()).append("http://").append(url).toString();
	    	
	    final String tmpUrl = url;
	    CCDirector.sharedDirector().getActivity().runOnUiThread(new Runnable() {
	        public void run() {
	            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tmpUrl));
	            CCDirector.sharedDirector().getActivity().startActivity(browserIntent);
	    }});
	 }

	public void onHelpOtherTitles(Object sender)
	{
		onUtilCommandCommon();
		//AlertUtils_ShowDoneAlert(nil, nil, @"Other Titles", @"Chess - 4th in World Micro Championships\nBrain School Brain Trainer\nBrain College\nMy Last Cigarette");
		String message = String.format("Chess - 4th in World Micro Championships\nBrain School Brain Trainer\nBrain College\nMy Last Cigarette");
		GB.AlertUtils_ShowDoneAlert("Other Titles", message);
	}

	
	public void onHelpReportBug(Object sender)
	{
		Intent i = new Intent(Intent.ACTION_SEND);  
		//i.setType("text/plain"); //use this line for testing in the emulator  
		i.setType("message/rfc822") ; // use from live device
		i.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@mastersoftmobilesolutions.com"});  
		i.putExtra(Intent.EXTRA_SUBJECT,"Fruit Salad Bug Report");  
		i.putExtra(Intent.EXTRA_TEXT,"Detailed bug description:\n\nHow can bug be reproduced?:");  
		CCDirector.theApp.startActivity(Intent.createChooser(i, "Sent from my Phone."));
		
		
	}
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void initStartGameVariables()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		//MainActivity appDelegate = MainActivity.app;
		
		SudokuUtils.SudokuUtils_ResetBoard();
		boardView.resetState();

		MainActivity.stateFlagPosRed = 0;
		MainActivity.stateFlagPosGreen = 0;
		MainActivity.stateFlagPosBlue = 0;
		MainActivity.stateFlagPosOrange = 0;

		MainActivity.stateCurSudokuLevel = -1;
		MainActivity.stateCurSudokuNumber = -1;
		MainActivity.stateGameTime = 0;
		MainActivity.stateGameScore = 0;
		MainActivity.stateAutoCandidates = false;
		MainActivity.stateFlagPosRed = 0;
		MainActivity.stateFlagPosGreen = 0;
		MainActivity.stateFlagPosBlue = 0;
		MainActivity.stateFlagPosOrange = 0;

		MainActivity.stateGameInProgress = false;

		MainActivity.history.clear(); // .removeAllObjects();
	}

	public void onEnterOwnSudoku(Object sender)
	{
//		/*************** CACHE CHARTBOOST ****************/
//		
//		NSLog(@"01 ATTEMPTING TOP CACHE A CHARTBOOST AD");
//		[[Chartboost sharedChartboost] cacheInterstitial:@"various"];
//		
//		/*************************************************/
//		
//		switch(INTERSTATIALS)
//		{
//		case 1:
//		{
//			if ([[Chartboost sharedChartboost] hasCachedInterstitial]  == YES)
//			{
//				NSLog(@"02 A CHARTBOOST ad is cached so we will show it.");
//				//SHOW THE CHART BOOST AD
//				[self performSelector:@selector(requestCharboostAd) withObject:nil afterDelay:0.5];
//				break;
//			}
//			else
//			{
//				NSLog(@"02 NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD.");
//				//NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD
//				if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				else
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				break;
//			}
//		}
//		
//		case 2:
//		{
//			NSLog(@"Flurry Interstatial");
//		
//			if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//			{
//				[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//			}
//			else
//			{
//				[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//			}
//			break;
//			}
//		}
		
		initStartGameVariables();
		
		//[boardView setNeedsDisplay];
		boardView.drawRect();
		
		switchToGameMode(GC.kGameModeEnterOwnSudoku);
		updateToEnterSudokuState();
		boardView.setColor(0);
	}

	public void requestCharboostAd()
	{
		//NSLog(@"requested");
		//[[Chartboost sharedChartboost] showInterstitial];
	}


	public void onSolve(Object sender)
	{
//		/*************** CACHE CHARTBOOST ****************/
//		
//		NSLog(@"01 ATTEMPTING TOP CACHE A CHARTBOOST AD");
//		[[Chartboost sharedChartboost] cacheInterstitial:@"various"];
//		
//		/*************************************************/
//		
//		switch(INTERSTATIALS)
//		{
//			case 1:
//			{
//				if ([[Chartboost sharedChartboost] hasCachedInterstitial]  == YES)
//				{
//					NSLog(@"02 A CHARTBOOST ad is cached so we will show it.");
//					//SHOW THE CHART BOOST AD
//					[self performSelector:@selector(requestCharboostAd) withObject:nil afterDelay:0.5];
//					break;
//				}
//				else
//				{
//					NSLog(@"02 NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD.");
//					//NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD
//					if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//					{
//						[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//					}
//					else
//					{
//						[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//					}
//					break;
//				}
//			}
//		
//			case 2:
//			{
//				NSLog(@"Flurry Interstatial");
//				
//				if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				else
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				break;
//			}
//		}
		
		Grid grid;
		char curGrid[] = new char[90]; //[90];
		char answer[] = new char[90]; //[90];
		
		SudokuUtils.SudokuUtils_GridToSolverString(SudokuUtils.g_gameGrid, curGrid);
		//init_solve_engine(__SolverCallBack, NULL, NULL, 1, 0);
		sudoku_engine.init_solve_engine(1, 0);
		grid = sudoku_engine.solve_sudoku(curGrid);
		if(grid != null)
		{
			if(grid.solncount >= 1)
			{
				//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
				
				MainActivity.stateCurSudokuLevel = -1;
				MainActivity.stateCurSudokuNumber = -1;
				
				sudoku_engine.format_answer(grid, answer);
				SudokuUtils.SudokuUtils_SolverStringToGrid(answer, SudokuUtils.g_gameGrid);
				SudokuUtils.SudokuHistory_AddCurrentState();
				
				onSudokuSolved();
			}
			
			sudoku_engine.free_soln_list(grid);
		}	
	}
		
	public void onStartNewGameUpddateMenuDef(Object sender)
	{
		//MenuDef menuDef = (MenuDef)[sender pointerValue];
		MenuDef menuDef = (MenuDef)sender;
		String _names[] = {
				"Buy 5 Level Expansion Pack",
				"Purchase Medium levels",
				"Purchase Hard levels",
				"Purchase Very Hard levels",
				"Purchase Master levels"};
		
		int activatedMenusCount = 0;
		for(int index = 0; index < 5; index++)
		{
			activatedMenusCount += 1;
		}
		
		if(activatedMenusCount == 5)
			return;
		
		menuDef.count = activatedMenusCount + 2;
		menuDef.items[activatedMenusCount + 1].itemName = _names[activatedMenusCount];
	}

	public void onStartNewGame(Object sender)
	{
//		/*************** CACHE CHARTBOOST ****************/
//		NSLog(@"01 ATTEMPTING TOP CACHE A CHARTBOOST AD");
//		[[Chartboost sharedChartboost] cacheInterstitial:@"various"];
//		/*************************************************/

		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		//int gameNumber;
		//int gameLevel;

		//gameLevel = (menuItem) ? (menuItem.menuID) : (0);
		if(menuItem == null)
			gameLevel = 0;
		else
			gameLevel = menuItem.menuID;

		initStartGameVariables();

		//SudokuUtils_MakeNewGame(gameLevel, &gameNumber);
		SudokuUtils.SudokuUtils_MakeNewGame(gameLevel);

		if((MainActivity.prefSymmetryMode == 1 && (GB.RANDOM_FLOAT() > 0.5)) || (MainActivity.prefSymmetryMode == 2))
			SudokuUtils.SudokuUtils_Desymmetrize(SudokuUtils.g_gameGrid);

//		SudokuUtils.SudokuTransform_TranslateBoard(GB.arc4random() % 4);

		MainActivity.stateCurSudokuLevel = gameLevel;
		MainActivity.stateCurSudokuNumber = gameNumber;
		MainActivity.stateGameInProgress = true;

		MainActivity.stateGameScore = 3600*(gameLevel + 1);

		//[boardView setNeedsDisplay];
		boardView.drawRect();
		switchToGameMode(GC.kGameModeNormal);
		SudokuUtils.SudokuHistory_AddCurrentState();


		if(gameLevel > 0)
		{
//			switch(INTERSTATIALS)
//			{
//			case 1:
//			{
//				if ([[Chartboost sharedChartboost] hasCachedInterstitial]  == YES)
//				{
//					NSLog(@"02 A CHARTBOOST ad is cached so we will show it.");
//					//SHOW THE CHART BOOST AD
//					[self performSelector:@selector(requestCharboostAd) withObject:nil afterDelay:0.5];
//					break;
//				}
//				else
//				{
//					NSLog(@"02 NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD.");
//					//NO CHART BOOST AD CACHED SO SHOW THE FLURRY AD
//					if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//					{
//						[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//					}
//					else
//					{
//						[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//					}
//					break;
//				}
//		
//				break;
//			}
//			case 2:
//			{
//				NSLog(@"Flurry Interstatial");
//		
//				if (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				else
//				{
//					[FlurryAds showAdForSpace:@"Sudoku iPad iPhone Full Screen" view:self.view size:FULLSCREEN timeout:0];
//				}
//				break;
//				}
//			}
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onNumbersStyleMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		menuItem.checkMark = (skinManager.mainNumbersIndex == menuItem.menuID);
	}

	public void onChangeNumbersStyle(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		skinManager.setSkinNumbers(menuItem.menuID);
		onUtilCommandCommon();
	}

	public void onBackStyleMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		menuItem.checkMark = (skinManager.mainSkinIndex == menuItem.menuID);
	}

	public void onChangeBackStyle(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		skinManager.setSkinMain(menuItem.menuID);
		onUtilCommandCommon();
	}


	public void onGridStyleMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		menuItem.checkMark = (skinManager.mainBoardIndex == menuItem.menuID);
	}

	public void onChangeGridStyle(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		skinManager.setSkinBoard(menuItem.menuID);
		onUtilCommandCommon();
	}

	public void onSkinPresets(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		SkinManager skinManager = MainActivity.utils_GetSkinManager();
		
		switch(menuItem.menuID)
		{
		case 0:
			skinManager.setSkinNumbers(0);
			skinManager.setSkinBoard(SkinBoardType.kSkinBoardPlain);
			break;
		case 1:
			skinManager.setSkinNumbers(7);
			skinManager.setSkinBoard(SkinBoardType.kSkinBoardAlpha);
			break;
		case 2:
			skinManager.setSkinNumbers(9);
			skinManager.setSkinBoard(SkinBoardType.kSkinBoardSpace);
			break;
		case 3:
			skinManager.setSkinNumbers(10);
			skinManager.setSkinBoard(SkinBoardType.kSkinBoardLED);
			break;
		}
		
		onUtilCommandCommon();
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onClearNumbersAll(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		if(SudokuUtils.SudokuUtils_ClearNumbersAll())
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
	}
	
	public void onClearNumbersByColor(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		MenuItemView menuItem = (MenuItemView)sender;
		
		if(SudokuUtils.SudokuUtils_ClearNumbersByColor(menuItem.menuID))
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
	}

	public void onClearNumbersByValue(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		MenuItemView menuItem = (MenuItemView)sender;
		
		if(SudokuUtils.SudokuUtils_ClearNumbersByValue(menuItem.menuID))
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
	}

	public void onClearPossibleAll(Object sender)
	{
		if(!validateAndProcessGameInProgress())
			return;
		
		if(SudokuUtils.SudokuUtils_ClearCandidatesAll())
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
}

	public void onClearPossibleByValue(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		if(!validateAndProcessGameInProgress())
			return;
		
		if(SudokuUtils.SudokuUtils_ClearCandidatesByValue(menuItem.menuID))
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
}

	public void onChangeNumberColors(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		if(!validateAndProcessGameInProgress())
			return;
		
		if(SudokuUtils.SudokuUtils_ChangeNumberColors(menuItem.parentMenu.prevMenuID, menuItem.menuID))
		{
			wisardUpdateAutoCandidates();
			SudokuUtils.SudokuHistory_AddCurrentState();
		}
		
		onUtilCommandCommon();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onSymmetryMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		menuItem.checkMark = (MainActivity.prefSymmetryMode == menuItem.menuID);
	}

	public void onSymmetryMenu(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		MainActivity.prefSymmetryMode = menuItem.menuID;
	}

	public void onSoundsMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		switch(menuItem.menuID)
		{
		case 0: menuItem.checkMark = MainActivity.prefSoundsOn; break;		
		case 1: menuItem.checkMark = MainActivity.prefSoundsStartup; break;		
		case 2: menuItem.checkMark = MainActivity.prefSoundsClose; break;		
		case 3: menuItem.checkMark = MainActivity.prefSoundsTransform; break;		
		case 4: menuItem.checkMark = MainActivity.prefSoundsClick; break;		
		case 5: menuItem.checkMark = MainActivity.prefSoundsHintControls; break;		
		case 6: menuItem.checkMark = MainActivity.prefSoundsEraser; break;
		}
	}

	public void onSoundsMenu(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		switch(menuItem.menuID)
		{
		case 0: MainActivity.prefSoundsOn = !MainActivity.prefSoundsOn; break;		
		case 1: MainActivity.prefSoundsStartup = !MainActivity.prefSoundsStartup; break;		
		case 2: MainActivity.prefSoundsClose = !MainActivity.prefSoundsClose; break;		
		case 3: MainActivity.prefSoundsTransform = !MainActivity.prefSoundsTransform; break;		
		case 4: MainActivity.prefSoundsClick = !MainActivity.prefSoundsClick; break;		
		case 5: MainActivity.prefSoundsHintControls = !MainActivity.prefSoundsHintControls; break;		
		case 6: MainActivity.prefSoundsEraser = !MainActivity.prefSoundsEraser; break;
		}
	}

	public void onKeypadOptionsUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		switch(menuItem.menuID)
		{
		case 0: menuItem.checkMark = MainActivity.prefKeypadIsDraggable; break;		
		case 1: menuItem.checkMark = MainActivity.prefKeypadIsStickly; break;		
		case 2: menuItem.checkMark = MainActivity.prefKeypadAutohide; break;
		case 3: menuItem.checkMark = MainActivity.prefKeypadHideOnOK; break;
		}
	}

	public void onKeypadOptions(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		switch(menuItem.menuID)
		{
		case 0: MainActivity.prefKeypadIsDraggable = !MainActivity.prefKeypadIsDraggable; break;		
		case 1: MainActivity.prefKeypadIsStickly = !MainActivity.prefKeypadIsStickly; break;
		case 2: MainActivity.prefKeypadAutohide = !MainActivity.prefKeypadAutohide; break;
		case 3: MainActivity.prefKeypadHideOnOK = !MainActivity.prefKeypadHideOnOK; break;
		}
	}

	public void onKeyPadMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		switch(menuItem.menuID)
		{
		case 0:
			menuItem.checkMark = !MainActivity.prefUseFlyingKeypad;
			break;
		case 1:
			menuItem.checkMark = MainActivity.prefUseFlyingKeypad;
			break;		
		}
	}
	
	public void onKeyPadMenu(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		MainActivity.prefUseFlyingKeypad = (menuItem.menuID == 1);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void wisardUpdateAutoCandidates()
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		if(MainActivity.stateAutoCandidates)
		{
			SudokuUtils.SudokuHints_InitHintsGrid();
			SudokuUtils.SudokuUtils_FillCandidates(SudokuUtils.g_gameGrid);
		}
	}
	
	public void onWisardMenuUpdate(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		if(menuItem.menuID == 7)
		menuItem.checkMark = MainActivity.stateAutoCandidates;
	}
	
	public void switchToShowStaticHint(String name)
	{
		 onUtilCommandCommon();	
		
		switchToGameMode(GC.kGameModeShowStaticHint);
		toolBarStaticHint.nameLabel.setString(name);
	}

	public void onStaticHintClose(Object sender)
	{
		onUtilCommandCommon();
	}

	public void showNoHintsAlert(String hintName)
	{
		//NSString* message = [NSString stringWithFormat:NSLocalizedString(@"No %@ were found.",@""), hintName];
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), message);
		String message = String.format("No %s were found.", hintName);
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), message);
		GB.AlertUtils_ShowDoneAlert("Information", message);
	}

	public void onStaticHintResult(String hintName)
	{
		if(SudokuUtils.g_gameHintsAccumulator.size() == 0)
		{
			onUtilCommandCommon();
			showNoHintsAlert(hintName);
		}
		else
		{
			switchToShowStaticHint(hintName);
			boardView.showHint(0);
			SoundUtils.Sounds_PlayHintControls();
		}
	}

	public void wisardReduceScore(int percents)
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		MainActivity.stateGameScore -= MainActivity.stateGameScore*percents/100;
		if(MainActivity.stateGameScore < 0)
			MainActivity.stateGameScore = 0;
	}

	public void onWisardPossibleValues(Object sender)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		SudokuUtils.SudokuUtils_FillCandidates(SudokuUtils.g_tmpGameGrid);
		
		wisardReduceScore(10);
		
		switchToShowStaticHint("Possible values");
		boardView.beginShowCandidates();	
	}

	String _names[] = 
	{
		"",
		"1/Red",
		"2/Orange",
		"3/Yellow",
		"4/Green",
		"5/Blue",
		"6/Indigo",
		"7/Violet",
		"8/Black",
		"9/White"
	};

	public void onPossibleImpossibleCell(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		String name;
		
		wisardReduceScore(10);
		
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		if((menuItem.prevMenuID() != 1) && (menuItem.prevMenuID() != 2))
			return;
	
		boolean isValid = menuItem.prevMenuID() == 1;
		name = String.format(((isValid) ? ("Possible cells %s") : ("Impossible cells %s")), _names[menuItem.menuID]);
		
		Hints_ValidInvalidValue validInvalidHint = new Hints_ValidInvalidValue(menuItem.menuID, isValid); 
		validInvalidHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		validInvalidHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult(name);
	}

	public void onWisardDuplicatesBoxRowCol(Object sender)
	{
		wisardReduceScore(10);
		
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_DuplicateValues wrongHint = new Hints_DuplicateValues(false, true);
		wrongHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		wrongHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult("Duplicates Row/Box/Col");
	}

	public void onWisardDuplicateValues(Object sender)
	{
		wisardReduceScore(10);
		
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_DuplicateValues wrongHint = new Hints_DuplicateValues(false, false); 
		wrongHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		wrongHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult("Duplicates Values");
	}

	public void onWisardShowCandidates(Object sender)
	{
		wisardReduceScore(10);
		
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_Potentials potentialsHint = new Hints_Potentials(); // alloc] init] autorelease];
		potentialsHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		potentialsHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult("Show Candidates");
	}

	public void onWisardCalculateCandidates(Object sender)
	{
		wisardReduceScore(25);
		
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		SudokuUtils.SudokuUtils_FillCandidates(SudokuUtils.g_gameGrid);
		SudokuUtils.SudokuHistory_AddCurrentState();
		//[self redrawAllSubviews:self.view];
		this.boardView.drawRect();
	}

	public void onWisardAutoCandidates(Object sender)
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		
		if(!MainActivity.stateAutoCandidates)
			wisardReduceScore(75);
		
		MainActivity.stateAutoCandidates = !MainActivity.stateAutoCandidates;
		
		wisardUpdateAutoCandidates();
		
		if(MainActivity.stateAutoCandidates)
			SudokuUtils.SudokuHistory_AddCurrentState();
		
		//[self redrawAllSubviews:self.view];
		this.boardView.drawRect();
	}

	public void onWisardSuggestTech(Object sender)
	{
		wisardReduceScore(35);
		onUtilCommandCommon();
		
		String message = null;
		
		
		if(hintsGenerateNakedSingle() != 0)
		{
			//message = NSLocalizedString(@"That is easy. There is at least one Naked Single!",@"");
			message = "That is easy. There is at least one Naked Single!";
		}
		else if(hintsGenerateHiddenSingle() != 0)
		{
			//message = NSLocalizedString(@"Look for at least one Hidden Single.",@"");
			message = "Look for at least one Hidden Single.";
		}
		else if(hintsGenerateNakedSet(2) != 0)
		{
			//message = NSLocalizedString(@"Try looking for one or more Naked Twins.",@"");
			message = "Try looking for one or more Naked Twins.";
		}
		else if(hintsGenerateHiddenSet(2, false) != 0)
		{
			//message = NSLocalizedString(@"There are one or more Hidden Twins.",@"");
			message = "There are one or more Hidden Twins.";
		}
		else if(hintsGenerateNakedSet(3) != 0)
		{
			//message = NSLocalizedString(@"There is at least one Naked Triplet.",@"");
			message = "There is at least one Naked Triplet.";
		}
		else if(hintsGenerateHiddenSet(3, false) != 0)
		{
			//message = NSLocalizedString(@"Look for at least one Hidden Triplet.",@"");
			message = "Look for at least one Hidden Triplet.";
		}
		else if(hintsGenerateNakedSet(4) != 0)
		{
			//message = NSLocalizedString(@"Look for Naked Quadruplets.",@"");
			message = "Look for Naked Quadruplets.";
		}
		else if(hintsGenerateHiddenSet(4, false) != 0)
		{
			//message = NSLocalizedString(@"There is at least one Hidden Quadruplet. These are very difficult to spot.",@"");
			message = "There is at least one Hidden Quadruplet. These are very difficult to spot.";
		}
		else if(hintsGenerateFishermanSet(2) != 0)
		{
			//message = NSLocalizedString(@"Look for an X-Wing.",@"");
			message = "Look for an X-Wing.";
		}
		else if(hintsGenerateFishermanSet(3) != 0)
		{
			//message = NSLocalizedString(@"Can you see the Swordfish?",@"");
			message = "Can you see the Swordfish?";
		}
		else if(hintsGenerateFishermanSet(4) != 0)
		{
			//message = NSLocalizedString(@"There is a Jellyfish lurking in there.",@"");
			message = "There is a Jellyfish lurking in there.";
		}
		else
		{
			//message = NSLocalizedString(@"Try using the Ball of String.",@"");
			message = "Try using the Ball of String.";
		}
		
		if(message != null)
		{
			//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Suggest a Technique",@""), message);
			GB.AlertUtils_ShowDoneAlert("Suggest a Technique", message);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void showHintsResults(String hintName)
	{
		int count = SudokuUtils.g_gameHintsAccumulator.size();
		
		if(count == 0)
		{
			onUtilCommandCommon();
			showNoHintsAlert(hintName);
		}
		else
		{
			switchToGameMode(GC.kGameModeShowHints);
			toolBarMiddleProgress.setCount(count);
			boardView.showHint(toolBarMiddleProgress.current);
			//redrawAllSubviews(self.view];
			SoundUtils.Sounds_PlayHintControls();
		}
	}

	public void onHintsReduceScore()
	{
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		MainActivity.stateGameScore = 0;
	}

	public void onWrongValues(Object sender)
	{
		onHintsReduceScore();
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		//Hints_WrongValues wrongHint = new Hints_WrongValues().initWithSkipPersist(true, false); // autorelease];
		Hints_WrongValues wrongHint = new Hints_WrongValues(true, false);
		wrongHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		wrongHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult("Wrong Values");
	}

	public void onSuggestCell(Object sender)
	{
		onHintsReduceScore();
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_SuggestCellValue suggestHint = new Hints_SuggestCellValue(false); // alloc] initWith:NO] autorelease];
		suggestHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		suggestHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		onStaticHintResult("Suggested Cell");
	}
	
	public void onSuggestValue(Object sender)
	{
		onHintsReduceScore();
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_SuggestCellValue suggestHint = new Hints_SuggestCellValue(true);
		suggestHint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		suggestHint.getHints(SudokuUtils.g_gameHintsGrid);
		
		
		onStaticHintResult("Suggested Value");
	}

	public int hintsGenerateNakedSingle()
	{
		SudokuUtils.SudokuHints_InitHintsGrid();	
		
		Hints_NakedSingle nakedSingle = new Hints_NakedSingle(); // alloc] init] autorelease];
		nakedSingle.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		nakedSingle.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public int hintsGenerateNakedSet(int degree)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_NakedSet nakedSet = new Hints_NakedSet(degree); //] autorelease];
		nakedSet.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		nakedSet.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public void onShowNakedSubset(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		//NSString* hintNames[] = {NSLocalizedString(@"Naked Single",@""), NSLocalizedString(@"Naked Twins",@""), NSLocalizedString(@"Naked Triplets",@""), NSLocalizedString(@"Naked Quadruplets",@"")};
		String hintNames[] = {
			"Naked Single",
			"Naked Twins",
			"Naked Triplets",
			"Naked Quadruplets"
		};
		
		onHintsReduceScore();
		onUtilCommandCommon();
		
		if(menuItem.menuID == 0)
		{
			hintsGenerateNakedSingle();
		}
		else
		{
			hintsGenerateNakedSet(menuItem.menuID + 1);
		}
		
		showHintsResults(hintNames[menuItem.menuID]);
	}

	public int hintsGenerateHiddenSingle()
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_HiddenSingle hiddenSingle = new Hints_HiddenSingle(); // alloc] init] autorelease];
		hiddenSingle.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		hiddenSingle.getHints(SudokuUtils.g_gameHintsGrid, true);
		hiddenSingle.getHints(SudokuUtils.g_gameHintsGrid, false);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public int hintsGenerateHiddenSet(int degree, boolean isDirect)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_HiddenSet hiddenSet = new Hints_HiddenSet(degree, isDirect); //] autorelease];
		hiddenSet.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		hiddenSet.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public void onShowHiddenSubset(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		boolean isDirect = (menuItem.parentMenu.prevMenuID == 1);
		
		onHintsReduceScore();
		onUtilCommandCommon();
		
		if(menuItem.menuID == 0)
		{
			hintsGenerateHiddenSingle();
		}
		else
		{
			hintsGenerateHiddenSet(menuItem.menuID + 1, isDirect);
		}
		
		String hintName;
		//NSString* hintNames[] = {NSLocalizedString(@"Hidden Single",@""), NSLocalizedString(@"Hidden Twins",@""), NSLocalizedString(@"Hidden Triplets",@""), NSLocalizedString(@"Hidden Quadruplets",@"")};
		String hintNames[] = {
				"Hidden Single",
				"Hidden Twins",
				"Hidden Triplets",
				"Hidden Quadruplets"};
		
		if(isDirect)
			hintName = String.format("Direct %s", hintNames[menuItem.menuID]);
		else
			hintName = hintNames[menuItem.menuID];
		
		
		showHintsResults(hintName);
	}

	public int hintsGenerateIntersectionsSubset(boolean isDirect)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_Locking hint = new Hints_Locking(isDirect);
		hint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		hint.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public void onIntersectionsSubset(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		onHintsReduceScore();
		onUtilCommandCommon();
		
		hintsGenerateIntersectionsSubset(menuItem.menuID == 1);
		
		showHintsResults((menuItem.menuID == 1) ? ("Direct Intersections") : ("Intersections"));
	}

	public int hintsGenerateFishermanSet(int degree)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		//Hints_Fisherman hint = [[[Hints_Fisherman alloc] initWithDegree:degree] autorelease];
		Hints_Fisherman hint = new Hints_Fisherman(degree);
		hint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		hint.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}

	public void onFishermanSubset(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		onHintsReduceScore();
		onUtilCommandCommon();
		
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		hintsGenerateFishermanSet(menuItem.menuID);
		
		String hintNames[] = 
		{
			"",
			"",
			"X-Wings", 
			"Swordfish",
			"Jellyfish"
		};
		
		showHintsResults(hintNames[menuItem.menuID]);
	}

	public int hintsGenerateXYWingsSet(boolean isXYZ)
	{
		SudokuUtils.SudokuHints_InitHintsGrid();
		
		Hints_XYWings hint = new Hints_XYWings(isXYZ); // autorelease];
		hint.hintsArray = SudokuUtils.g_gameHintsAccumulator;
		hint.getHints(SudokuUtils.g_gameHintsGrid);
		
		return SudokuUtils.g_gameHintsAccumulator.size();
	}


	public void onXYWingsSubset(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		
		onHintsReduceScore();
		onUtilCommandCommon();
		
		hintsGenerateXYWingsSet(menuItem.menuID == 1);
		
		showHintsResults((menuItem.menuID == 1) ? ("XYZ-Wings") : ("XY-Wings"));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onProgressShowIndex(Object sender)
	{
		boardView.showHint(toolBarMiddleProgress.current);
	}

	public void onProgressClose(Object sender)
	{
		onUtilCommandCommon();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onHistoryCancel(Object sender)
	{
		boardView.showHintDone();
		onUtilCommandCommon();
		
		unschedule("checkHistory");
	}

	public void onHistoryOK(Object sender)
	{
		//SudokuHistory_SetLastPos(toolBarHistory.historySlider.value);
		SudokuUtils.SudokuHistory_SetLastPos(toolBarHistory._value);
		
		boardView.showHintDone();
		onUtilCommandCommon();
		
		unschedule("checkHistory");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onMarkFlagRed(Object sender)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		MainActivity.stateFlagPosRed = MainActivity.history.size() - 1;
	}

	public void onMarkFlagGreen(Object sender)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		MainActivity.stateFlagPosGreen = MainActivity.history.size() - 1;
	}

	public void onMarkFlagBlue(Object sender)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		MainActivity.stateFlagPosBlue = MainActivity.history.size() - 1;
	}

	public void onMarkFlagOrange(Object sender)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		MainActivity.stateFlagPosOrange = MainActivity.history.size() - 1;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onTransformRotateClockWise(Object sender)
	{
		onUtilCommandCommon();
		boardView.processTransformAnimation(0);
		SoundUtils.Sounds_PlayTransform();
	}

	public void onTransformRotateAntiClockWise(Object sender)
	{
		onUtilCommandCommon();
		boardView.processTransformAnimation(1);
		SoundUtils.Sounds_PlayTransform();
	}

	public void onTransformMirrorVertical(Object sender)
	{
		onUtilCommandCommon();
		boardView.processTransformAnimation(2);
		SoundUtils.Sounds_PlayTransform();
	}

	public void onTransformMirrorHorisontal(Object sender)
	{
		onUtilCommandCommon();
		boardView.processTransformAnimation(3);
		SoundUtils.Sounds_PlayTransform();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onShowAdvancedStats(Object sender)
	{
		MenuItemView menuItem = (MenuItemView)sender;
		int level = menuItem.menuID;
		String message;
		
		if(SudokuUtils.g_gameStats.statsGameCount[level] == 0)
		{
			//message = [NSString stringWithFormat:NSLocalizedString(@"%@ Level\nNone Available. You have yet to complete a SuDoku at this level!",@""), menuItem.menuName];
			message = String.format("%s Level\nNone Available. You have yet to complete a SuDoku at this level!", menuItem.menuName);
		}
		else
		{
			//NSString* template = NSLocalizedString(@"%@ Level\n\nGames Played: %d\n\nBest Score: %d\nWorst Score: %d\nAverage Score: %d\n\nFastest Time: %@\nSlowest Time: %@\nAverage Time: %@",@"");
			String template = "%s Level\n\nGames Played: %d\n\nBest Score: %d\nWorst Score: %d\nAverage Score: %d\n\nFastest Time: %s\nSlowest Time: %s\nAverage Time: %s";
			message = String.format(template,
					menuItem.menuName, 
					SudokuUtils.g_gameStats.statsGameCount[level], 
					SudokuUtils.g_gameStats.statsScoreMax[level], 
					SudokuUtils.g_gameStats.statsScoreMin[level], 
					SudokuUtils.g_gameStats.statsScoreFull[level]/SudokuUtils.g_gameStats.statsGameCount[level], 
					GameBoardUtils.formatGameTime(SudokuUtils.g_gameStats.statsTimeMin[level]),
					GameBoardUtils.formatGameTime(SudokuUtils.g_gameStats.statsTimeMax[level]),
					GameBoardUtils.formatGameTime(SudokuUtils.g_gameStats.statsTimeFull[level]/SudokuUtils.g_gameStats.statsGameCount[level]));
		}
		
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Advanced Stats",@""), message);
		GB.AlertUtils_ShowDoneAlert("Advanced Stats", message);
	}


	public void onResetStats(Object sender)
	{
		//memset(&g_gameStats, 0, sizeof(g_gameStats));
		SudokuUtils.g_gameStats.memset();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	public void leaderboardViewControllerDidFinish:(GKLeaderboardViewController *)viewController  
//	{  
//		//[self dismissModalViewControllerAnimated:YES];  
//	}  

//	public void achievementViewControllerDidFinish:(GKAchievementViewController *)viewController
//	{
//		//[self dismissModalViewControllerAnimated:YES];
//	}

	public void onShowLeaderboard(Object sender)
	{
		//if(![[GameCenter sharedInstance] isAuthenticated])
		//return;
		//
		//GKLeaderboardViewController *leaderboardController = [[GKLeaderboardViewController alloc] init];      
		//
		//if (leaderboardController != nil)
		//{  
		//leaderboardController.leaderboardDelegate = self;  
		//[self presentModalViewController: leaderboardController animated: YES];  
		//}  
	}

	public void onShowAchievements(Object sender)
	{
		//if(![[GameCenter sharedInstance] isAuthenticated])
		//return;
		//
		//GKAchievementViewController *achievementController = [[GKAchievementViewController alloc] init];      
		//
		//if (achievementController != nil)
		//{  
		//achievementController.achievementDelegate = self;  
		//[self presentModalViewController:achievementController animated: YES];  
		//}  
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void onMiddleBarTimer(Object sender)
	{
		PausedViewController tmpViewController = new PausedViewController();
		PausedViewController viewController = tmpViewController;
		this.addChild(tmpViewController, 12);
		
		//viewController.view.frame = self.view.bounds;
		//SudokuAppDelegate* appDeleage = utils_GetAppDelegate();
		MainActivity.stateGamePaused = true;
		
		//[UIView transitionWithView:self.view duration:0.5
		//options:UIViewAnimationOptionTransitionCrossDissolve //change to whatever animation you like
		//animations:^ { [self.view addSubview:viewController.view];  }
		//completion:nil];
	}

	public void onMiddleBarYangYang(Object sender)
	{
		//NSString* template = NSLocalizedString(@"Score: %d\n\nYou have completed:\n%d Simple SuDoku(s)\n%d Easy SuDoku(s)\n%d Medium SuDoku(s)\n%d Hard SuDoku(s)\n%d Very Hard SuDoku(s)\n%d Master SuDoku(s)",@"");
		//NSString* message = [NSString stringWithFormat:template, g_gameStats.totalScore, g_gameStats.statsGameCount[0], g_gameStats.statsGameCount[1], g_gameStats.statsGameCount[2], g_gameStats.statsGameCount[3], g_gameStats.statsGameCount[4], g_gameStats.statsGameCount[5]];
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), message);
		String template = "Score: %d\n\nYou have completed:\n%d Simple SuDoku(s)\n%d Easy SuDoku(s)\n%d Medium SuDoku(s)\n%d Hard SuDoku(s)\n%d Very Hard SuDoku(s)\n%d Master SuDoku(s)";
		String message = String.format(template,
				SudokuUtils.g_gameStats.totalScore,
				SudokuUtils.g_gameStats.statsGameCount[0],
				SudokuUtils.g_gameStats.statsGameCount[1],
				SudokuUtils.g_gameStats.statsGameCount[2],
				SudokuUtils.g_gameStats.statsGameCount[3],
				SudokuUtils.g_gameStats.statsGameCount[4],
				SudokuUtils.g_gameStats.statsGameCount[5]);
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), message);
		GB.AlertUtils_ShowDoneAlert("Information",message);
	}

	public void onMiddleBarShield(Object sender)
	{
		//NSString* message = [NSString stringWithFormat:NSLocalizedString(@"Your rating: %@",@""), [self curRatingToName]];
		String message = String.format("Your rating: %s", curRatingToName());
		//AlertUtils_ShowDoneAlert(nil, nil, NSLocalizedString(@"Information",@""), message);
		GB.AlertUtils_ShowDoneAlert("Information", message);
	}

	

//#pragma mark- GADBannerViewDelgates
//	public void adViewDidReceiveAd:(GADBannerView *)bannerView {
//
//self.startDate = [NSDate date];
//[UIView beginAnimations:@"BannerSlide" context:nil];
////NSLog(@"banner ad found");
//[UIView commitAnimations];
//}
//
//	public void adView:(GADBannerView *)bannerViewdidFailToReceiveAdWithError:(GADRequestError *)error {
//
//NSLog(@"adView:didFailToReceiveAdWithError:%@", [error localizedDescription]);
//}
	
	/////////////////////////////////// mycode /////////////////////////////////////////////////////	
	
	@Override
    public boolean ccTouchesBegan(MotionEvent event)
	{
		CGPoint point = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
//		CGPoint point = [[touches anyObject] locationInView:self];
		
		return true;
	}

	@Override
    public boolean ccTouchesCancelled(MotionEvent event)
	{
		CGPoint location = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
	
		//[self clearSelection:YES];
		boardView.clearSelection(true);
		
		return true;
	}

	@Override
    public boolean ccTouchesEnded(MotionEvent event)
	{
		CGPoint point = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		
		//[self updateSelectionWithPoint:point];
		boardView.updateSelectionWithPoint(point);
		
		return true;
	}

	@Override
    public boolean ccTouchesMoved(MotionEvent event)
	{
		CGPoint point = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
		
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		//by GOLD
	    if (MainActivity.prefUseFlyingKeypad == true && m_bkeypadControl == true)
	    {
	    	keypadView.onButtonOutsideMove(point);
	     }

		//if(appDelegate.prefUseFlyingKeypad && activeSelection)
		if(MainActivity.prefUseFlyingKeypad == true && boardView.activeSelection != null)
			return true;
		
//		CGPoint point = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()));
//		//[self updateSelectionWithPoint:point];
//		boardView.updateSelectionWithPoint(point);
		return true;
	}	

	//void ShowMenu(const MenuDef* pMenu, id delegeate, UIView* parent)
	public void ShowMenu(MenuDef pMenu)
	{
		//MenuView menuView = MenuView.initWithItems(pMenu, delegeate, parent, null, null);
		MenuView menuView = new MenuView(pMenu, this, null, null);
		this.addChild(menuView, 10);
//		menuView.showMenu();
//		menuView.release();
	}

	public void TimerCount(float dt)
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.stateGameInProgress || MainActivity.stateGamePaused)
			return;
		
		MainActivity.stateGameTime += 1;
		MainActivity.stateGameScore -= 1; 
		if(MainActivity.stateGameScore < 0)
			MainActivity.stateGameScore = 0;
		
		toolBarMiddle.updateState();
	}
	
	public void onEmptyProcedure(Object sender)
	{
		onUtilCommandCommon();
	}

	///////////////////////////////////////////////////////////////////////////////
	//- (void)onTimer:(NSTimer*)timer
	public void _onProgressTimer(float dt)
	{
		toolBarMiddleProgress.onNext(null);
	}

	public void stopProgressTimer()
	{
		//if(progressTimer)
		//{
		//	[progressTimer invalidate];
		//	self.progressTimer = nil;
		//}
		unschedule("_onProgressTimer");
		toolBarMiddleProgress.progressTimer = false;
	}

	public void startProgressTimer()
	{
		stopProgressTimer();
		toolBarMiddleProgress.progressTimer = true;
		
		//self.progressTimer = [NSTimer timerWithTimeInterval:2.0 target:self selector:@selector(onTimer:) userInfo:nil repeats:YES];
		//[[NSRunLoop currentRunLoop] addTimer:progressTimer forMode:NSDefaultRunLoopMode];
		schedule("_onProgressTimer", 2.0f);
	}

}
