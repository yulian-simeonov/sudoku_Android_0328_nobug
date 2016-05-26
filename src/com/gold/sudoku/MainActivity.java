package com.gold.sudoku;

import java.util.ArrayList;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.opengl.CCGLSurfaceView;
import org.cocos2d.sound.SoundEngine;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

public class MainActivity extends Activity {
	public static MainActivity app;
	public CCGLSurfaceView mGLSurfaceView;
	private boolean isCreated = false;

	// ////////////////////////////////////////////////////////////////////////////////
	public static boolean prefConfig;
	public static boolean prefState;

	public static int prefSkinMain;
	public static int prefSkinBoard;
	public static int prefSkinNumbers;
	public static int prefSymmetryMode;
	public static boolean prefSoundsOn;
	public static boolean prefSoundsStartup;
	public static boolean prefSoundsClose;
	public static boolean prefSoundsTransform;
	public static boolean prefSoundsClick;
	public static boolean prefSoundsHintControls;
	public static boolean prefSoundsEraser;
	public static boolean prefUseFlyingKeypad;
	public static boolean prefKeypadIsDraggable;
	public static boolean prefKeypadIsStickly;
	public static boolean prefKeypadAutohide;
	public static boolean prefKeypadHideOnOK;
	public static int prefKeypadPosX;
	public static int prefKeypadPosY;

	public static int stateCurSudokuLevel;
	public static int stateCurSudokuNumber;
	public static boolean stateAutoCandidates;
	public static int stateGameTime;
	public static int stateGameScore;
	public static boolean stateGameInProgress;

	public static int stateFlagPosRed;
	public static int stateFlagPosGreen;
	public static int stateFlagPosBlue;
	public static int stateFlagPosOrange;

	public static int reviewState;
	public static int reviewGameCount;

	public static boolean stateGamePaused;

	// NSMutableArray* history;
	public static ArrayList<String> history = null;

	public static SkinManager skinManager;
	public static boolean firstStart;

	// added by GOLD
	public String kPrefConfig = "kPrefConfig";
	public boolean kPrefConfigDef = false;
	public String kPrefState = "kPrefState";
	public boolean kPrefStateDef = false;

	public String kPrefSkinMain = "kPrefSkinMain";
	public int kPrefSkinMainDef = 0;

	public String kPrefSkinBoard = "kPrefSkinBoard";
	public int kPrefSkinBoardDef = 0;

	public String kPrefSkinNumbers = "kPrefSkinNumbers";
	public int kPrefSkinNumbersDef = 0;

	public String kPrefSymmetryMode = "kPrefSymmetryMode";
	public int kPrefSymmetryModeDef = 0;

	public String kPrefSoundsOn = "kPrefSoundsOn";
	public boolean kPrefSoundsOnDef = true;

	public String kPrefSoundsStartup = "kPrefSoundsStartup";
	public boolean kPrefSoundsStartupDef = true;

	public String kPrefSoundsClose = "kPrefSoundsClose";
	public boolean kPrefSoundsCloseDef = true;

	public String kPrefSoundsTransform = "kPrefSoundsTransform";
	public boolean kPrefSoundsTransformDef = true;

	public String kPrefSoundsClick = "kPrefSoundsClick";
	public boolean kPrefSoundsClickDef = true;

	public String kPrefSoundsHintControls = "kPrefSoundsHintControls";
	public boolean kPrefSoundsHintControlsDef = true;

	public String kPrefSoundsEraser = "kPrefSoundsEraser";
	public boolean kPrefSoundsEraserDef = true;

	public String kPrefUseFlyingKeypad = "kPrefUseFlyingKeypad";
	public boolean kPrefUseFlyingKeypadDef = true;

	public String kPrefKeypadIsDraggable = "kPrefKeypadIsDraggable";
	public boolean kPrefKeypadIsDraggableDef = true;

	public String kPrefKeypadIsStickly = "kPrefKeypadIsStickly";
	public boolean kPrefKeypadIsSticklyDef = true;

	public String kPrefKeypadAutohide = "kPrefKeypadAutohide";
	public boolean kPrefKeypadAutohideDef = true;

	public String kPrefKeypadHideOnOK = "kPrefKeypadHideOnOK";
	public boolean kPrefKeypadHideOnOKDef = true;

	public String kPrefKeypadPosX = "kPrefKeypadPosX";
	public int kPrefKeypadPosXDef = 0;

	public String kPrefKeypadPosY = "kPrefKeypadPosY";
	public int kPrefKeypadPosYDef = 0;

	public String kStateCurSudokuLevel = "kStateCurSudokuLevel";
	public int kStateCurSudokuLevelDef = -1;

	public String kStateCurSudokuNumber = "kStateCurSudokuNumber";
	public int kStateCurSudokuNumberDef = -1;

	public String kStateAutoCandidates = "kStateAutoCandidates";
	public boolean kStateAutoCandidatesDef = false;

	public String kStateGameTime = "kStateGameTime";
	public int kStateGameTimeDef = 0;

	public String kStateGameScore = "kStateGameScore";
	public int kStateGameScoreDef = 0;

	public String kStateGameInProgress = "kStateGameInProgress";
	public boolean kStateGameInProgressDef = false;

	public String kStateFlagPosRed = "kStateFlagPosRed";
	public int kStateFlagPosRedDef = 0;

	public String kStateFlagPosGreen = "kStateFlagPosGreen";
	public int kStateFlagPosGreenDef = 0;

	public String kStateFlagPosBlue = "kStateFlagPosBlue";
	public int kStateFlagPosBlueDef = 0;

	public String kStateFlagPosOrange = "kStateFlagPosOrange";
	public int kStateFlagPosOrangeDef = 0;

	public String kReviewState = "kReviewState";
	public String kReviewGameCount = "kReviewGameCount";

	public String kStateStats = "kStateStats";

	public static final String ADMOB_ID = "a1514755184e28f";
	public static final String REVMOB_ID = "5074393cb7b72c0c000000cd";

	// public static AdView adView;
	// public static boolean m_fRemoveAds;

	// chartboost
	public static final String CHARTBOOST_ID = "51475ab817ba47b96a000000";
    public static final String CHARTBOOST_SIG = "cc1195ddcd9782e9115d06f2e7d15d019fd5806d";
	// "cc1195ddcd9782e9115d06f2e7d15d019fd5806d";
//	public static final String CHARTBOOST_ID = "4f7b433509b6025804000002";
//	public static final String CHARTBOOST_SIG = "dd2d41b69ac01b80f443f5b6cf06096d457f82bd";

	public static Chartboost cb;

	public static RelativeLayout m_Layout;
	public static AdView adView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (!isCreated) {
			isCreated = true;
		} else {
			return;
		}

		/*
		 * super.onCreate(savedInstanceState);
		 * setContentView(R.layout.activity_gold_miner);
		 */
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mGLSurfaceView = new CCGLSurfaceView(this);
		setContentView(mGLSurfaceView);

		CCDirector.sharedDirector().attachInView(mGLSurfaceView);
		CCDirector.sharedDirector().setDeviceOrientation(CCDirector.kCCDeviceOrientationPortrait);
		getScaledCoordinate();

		SetupAds();

		cb = Chartboost.sharedChartboost();
		cb.onCreate(this, CHARTBOOST_ID, CHARTBOOST_SIG,
				new ChartboostDelegate() {
					@Override
					public boolean shouldDisplayInterstitial(String location) {
						Log.i("DBG", "SHOULD DISPLAY INTERSTITIAL '" + location
								+ "'?");
						return true;
					}

					@Override
					public boolean shouldRequestInterstitial(String location) {
						Log.i("DBG", "SHOULD REQUEST INSTERSTITIAL '"
								+ location + "'?");
						return true;
					}

					@Override
					public void didCacheInterstitial(String location) {
						Log.i("DBG", "INTERSTITIAL '" + location + "' CACHED");
					}

					@Override
					public void didFailToLoadInterstitial(String location) {
						// Show a house ad or do something else when a
						// chartboost interstitial fails to load

						Log.i("DBG", "INTERSTITIAL '" + location
								+ "' REQUEST FAILED");
//						Toast.makeText(MainActivity.this,
//								"Interstitial '" + location + "' Load Failed",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didDismissInterstitial(String location) {

						// Immediately re-caches an interstitial
						cb.cacheInterstitial(location);

						Log.i("DBG", "INTERSTITIAL '" + location
								+ "' DISMISSED");
//						Toast.makeText(MainActivity.this,
//								"Dismissed Interstitial '" + location + "'",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didCloseInterstitial(String location) {
						Log.i("DBG", "INSTERSTITIAL '" + location + "' CLOSED");
//						Toast.makeText(MainActivity.this,
//								"Closed Interstitial '" + location + "'",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didClickInterstitial(String location) {
						Log.i("DBG", "DID CLICK INTERSTITIAL '" + location
								+ "'");
//						Toast.makeText(MainActivity.this,
//								"Clicked Interstitial '" + location + "'",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didShowInterstitial(String location) {
						Log.i("DBG", "INTERSTITIAL '" + location + "' SHOWN");
					}

					@Override
					public boolean shouldDisplayLoadingViewForMoreApps() {
						return true;
					}

					@Override
					public boolean shouldRequestMoreApps() {

						return true;
					}

					@Override
					public boolean shouldDisplayMoreApps() {
						Log.i("DBG", "SHOULD DISPLAY MORE APPS?");
						return true;
					}

					@Override
					public void didFailToLoadMoreApps() {
						Log.i("DBG", "MORE APPS REQUEST FAILED");
//						Toast.makeText(MainActivity.this,
//								"More Apps Load Failed", Toast.LENGTH_SHORT)
//								.show();
					}

					@Override
					public void didCacheMoreApps() {
						Log.i("DBG", "MORE APPS CACHED");
					}

					@Override
					public void didDismissMoreApps() {
						Log.i("DBG", "MORE APPS DISMISSED");
//						Toast.makeText(MainActivity.this,
//								"Dismissed More Apps", Toast.LENGTH_SHORT)
//								.show();
					}

					@Override
					public void didCloseMoreApps() {
						Log.i("DBG", "MORE APPS CLOSED");
//						Toast.makeText(MainActivity.this, "Closed More Apps",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didClickMoreApps() {
						Log.i("DBG", "MORE APPS CLICKED");
//						Toast.makeText(MainActivity.this, "Clicked More Apps",
//								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void didShowMoreApps() {
						Log.i("DBG", "MORE APPS SHOWED");
					}

					@Override
					public boolean shouldRequestInterstitialsInFirstSession() {
						return true;
					}
				});
		cb.startSession();
		cb.cacheInterstitial();

		Resolution.InitResolution();

		this.history = new ArrayList<String>(); // [NSMutableArray array];
		skinManager = new SkinManager();
		// self.inAppPurchase = [[[LevelsInAppPurchase alloc] init]
		// autorelease];

		loadUserPrefs();
		loadGameState();

		skinManager.setSkinMain(prefSkinMain);
		skinManager.setSkinBoard(prefSkinBoard);
		skinManager.setSkinNumbers(prefSkinNumbers);

		SudokuUtils.SudokuHints_Init();
		SoundUtils.Sounds_Init();

		// [self createProgressView];
		// //[inAppPurchase initInAppPurchase];
		//
		// [window makeKeyAndVisible];
		// [self showSplashScreen];
		// [GameCenter sharedInstance];
		//
		// //Start Flurry Session
		// if ([[UIDevice currentDevice]userInterfaceIdiom] ==
		// UIUserInterfaceIdiomPhone) {
		//
		// //NB I have used same session ID for both iPhone and iPad
		// [Flurry startSession:@"4VYTDV4P5W87Q4MM39S3"]; // CONFIRMED by Dom
		//
		// }else{
		//
		// [Flurry startSession:@"4VYTDV4P5W87Q4MM39S3"]; // CONFIRMED by Dom
		// }

		CCScene scene = CCScene.node();
		scene.addChild(new MainScene(), -1);
		CCDirector.sharedDirector().runWithScene(scene);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		if (hasFocus) {
			// SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity().getApplicationContext(),
			// R.raw.snd_bgmain, true);
		} else {
			SoundEngine.sharedEngine().pauseSound();
			applicationDidEnterBackground();
		}

		super.onWindowFocusChanged(hasFocus);
	}

	private void SetupAds() {
		m_Layout = new RelativeLayout(this);

		addContentView(m_Layout, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		adView = new AdView(this, AdSize.BANNER, ADMOB_ID);
		// G.m_fRemoveAds = false;

		RelativeLayout.LayoutParams lpParams = new RelativeLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		lpParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lpParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		m_Layout.addView(adView, lpParams);
		AdRequest request = new AdRequest();
		adView.loadAd(request);
	}

	@Override
	protected void onStart() {
		super.onStart();
		cb.onStart(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		if (GameScene.sharedGameLayer() != null) {
			GameScene.sharedGameLayer().onPause(null);
		}

		CCDirector.sharedDirector().pause();
		SoundEngine.sharedEngine().pauseSound();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (GameScene.sharedGameLayer() != null) {
			GameScene.sharedGameLayer().onResume(null);
		}
		CCDirector.sharedDirector().onResume();
		cb.showInterstitial();
	}

	@Override
	protected void onDestroy() {
		applicationWillTerminate();
		cb.onDestroy(this);
		super.onDestroy();
	}

	private void getScaledCoordinate() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		GB.g_winSize.width = displayMetrics.widthPixels;
		GB.g_winSize.height = displayMetrics.heightPixels;

		GB.g_fScaleX = GB.g_winSize.width / GB.G_WIDTH;
		GB.g_fScaleY = GB.g_winSize.height / GB.G_HEIGHT;
	}

	// /////////////////////////////////////////////////////////////////////////////////

	public void loadUserPrefs() {
		SharedPreferences settings = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("sudoku_setting.plist", 0);

		prefSkinMain = kPrefSkinMainDef;
		prefSkinBoard = kPrefSkinBoardDef;
		prefSkinNumbers = kPrefSkinNumbersDef;

		prefSymmetryMode = kPrefSymmetryModeDef;
		prefSoundsOn = kPrefSoundsOnDef;
		prefSoundsStartup = kPrefSoundsStartupDef;
		prefSoundsClose = kPrefSoundsCloseDef;
		prefSoundsTransform = kPrefSoundsTransformDef;
		prefSoundsClick = kPrefSoundsClickDef;
		prefSoundsHintControls = kPrefSoundsHintControlsDef;
		prefSoundsEraser = kPrefSoundsEraserDef;
		prefUseFlyingKeypad = kPrefUseFlyingKeypadDef;
		prefKeypadIsDraggable = kPrefKeypadIsDraggableDef;
		prefKeypadIsStickly = kPrefKeypadIsSticklyDef;
		prefKeypadAutohide = kPrefKeypadAutohideDef;
		prefKeypadHideOnOK = kPrefKeypadHideOnOKDef;
		prefKeypadPosX = kPrefKeypadPosXDef;
		prefKeypadPosY = kPrefKeypadPosYDef;

		prefConfig = settings.getBoolean(kPrefConfig, kPrefConfigDef);
		if (!prefConfig) {
			firstStart = true;
			return;
		}

		prefSkinMain = settings.getInt(kPrefSkinMain, kPrefSkinMainDef);
		prefSkinBoard = settings.getInt(kPrefSkinBoard, kPrefSkinBoardDef);
		prefSkinNumbers = settings
				.getInt(kPrefSkinNumbers, kPrefSkinNumbersDef);

		prefSymmetryMode = settings.getInt(kPrefSymmetryMode,
				kPrefSymmetryModeDef);
		prefSoundsOn = settings.getBoolean(kPrefSoundsOn, kPrefSoundsOnDef);
		prefSoundsStartup = settings.getBoolean(kPrefSoundsStartup,
				kPrefSoundsStartupDef);
		prefSoundsClose = settings.getBoolean(kPrefSoundsClose,
				kPrefSoundsCloseDef);
		prefSoundsTransform = settings.getBoolean(kPrefSoundsTransform,
				kPrefSoundsTransformDef);
		prefSoundsClick = settings.getBoolean(kPrefSoundsClick,
				kPrefSoundsClickDef);
		prefSoundsHintControls = settings.getBoolean(kPrefSoundsHintControls,
				kPrefSoundsHintControlsDef);
		prefSoundsEraser = settings.getBoolean(kPrefSoundsEraser,
				kPrefSoundsEraserDef);
		prefUseFlyingKeypad = settings.getBoolean(kPrefUseFlyingKeypad,
				kPrefUseFlyingKeypadDef);
		prefKeypadIsDraggable = settings.getBoolean(kPrefKeypadIsDraggable,
				kPrefKeypadIsDraggableDef);
		prefKeypadIsStickly = settings.getBoolean(kPrefKeypadIsStickly,
				kPrefKeypadIsSticklyDef);
		prefKeypadAutohide = settings.getBoolean(kPrefKeypadAutohide,
				kPrefKeypadAutohideDef);
		prefKeypadHideOnOK = settings.getBoolean(kPrefKeypadHideOnOK,
				kPrefKeypadHideOnOKDef);
		prefKeypadPosX = settings.getInt(kPrefKeypadPosX, kPrefKeypadPosXDef);
		prefKeypadPosY = settings.getInt(kPrefKeypadPosY, kPrefKeypadPosYDef);

		prefKeypadAutohide = true;
		prefKeypadHideOnOK = true;
	}

	public void saveUserPrefs() {

		SharedPreferences settings = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("sudoku_setting.plist", 0);

		SharedPreferences.Editor editor;
		editor = settings.edit();

		// added by GOLD
		editor.putBoolean(kPrefConfig, true);
		editor.commit();

		editor.putInt(kPrefSkinMain, prefSkinMain);
		editor.commit();
		editor.putInt(kPrefSkinBoard, prefSkinBoard);
		editor.commit();
		editor.putInt(kPrefSkinNumbers, prefSkinNumbers);
		editor.commit();
		editor.putInt(kPrefSymmetryMode, prefSymmetryMode);
		editor.commit();
		editor.putBoolean(kPrefSoundsOn, prefSoundsOn);
		editor.commit();
		editor.putBoolean(kPrefSoundsStartup, prefSoundsStartup);
		editor.commit();
		editor.putBoolean(kPrefSoundsClose, prefSoundsClose);
		editor.commit();
		editor.putBoolean(kPrefSoundsTransform, prefSoundsTransform);
		editor.commit();
		editor.putBoolean(kPrefSoundsClick, prefSoundsClick);
		editor.commit();
		editor.putBoolean(kPrefSoundsHintControls, prefSoundsHintControls);
		editor.commit();
		editor.putBoolean(kPrefSoundsEraser, prefSoundsEraser);
		editor.commit();
		editor.putBoolean(kPrefUseFlyingKeypad, prefUseFlyingKeypad);
		editor.commit();
		editor.putBoolean(kPrefKeypadIsDraggable, prefKeypadIsDraggable);
		editor.commit();
		editor.putBoolean(kPrefKeypadIsStickly, prefKeypadIsStickly);
		editor.commit();
		editor.putBoolean(kPrefKeypadAutohide, prefKeypadAutohide);
		editor.commit();
		editor.putBoolean(kPrefKeypadHideOnOK, prefKeypadHideOnOK);
		editor.commit();
		editor.putInt(kPrefKeypadPosX, prefKeypadPosX);
		editor.commit();
		editor.putInt(kPrefKeypadPosY, prefKeypadPosY);
		editor.commit();
		// [settings writeToFile:settingsPath atomically:YES];
	}

	public void loadGameState() {
		// NSString* dir =
		// [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,
		// NSUserDomainMask, YES) objectAtIndex:0];
		// NSString* statePath = [dir
		// stringByAppendingPathComponent:@"state.plist"];
		// NSDictionary* state = [NSDictionary
		// dictionaryWithContentsOfFile:statePath];
		//
		// id value;

		stateCurSudokuLevel = kStateCurSudokuLevelDef;
		stateCurSudokuNumber = kStateCurSudokuNumberDef;
		stateAutoCandidates = kStateAutoCandidatesDef;
		stateGameTime = kStateGameTimeDef;
		stateGameScore = kStateGameScoreDef;
		stateGameInProgress = kStateGameInProgressDef;
		stateFlagPosRed = kStateFlagPosRedDef;
		stateFlagPosGreen = kStateFlagPosGreenDef;
		stateFlagPosBlue = kStateFlagPosBlueDef;
		stateFlagPosOrange = kStateFlagPosOrangeDef;

		SudokuUtils.SudokuStats_FromString(null);
		// if(state == nil) return;

		SharedPreferences state = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("sudoku_state.plist", 0);

		prefState = state.getBoolean(kPrefState, kPrefStateDef);
		if (!prefState) {
			firstStart = true;
			return;
		}

		reviewGameCount = state.getInt(kReviewGameCount, 0);
		reviewState = state.getInt(kReviewState, -1);

		if (reviewState != GC.REVIEW_STATE)
			reviewGameCount = 0;
		reviewState = GC.REVIEW_STATE;

		stateCurSudokuLevel = state.getInt(kStateCurSudokuLevel,
				kStateCurSudokuLevelDef);
		stateCurSudokuNumber = state.getInt(kStateCurSudokuNumber,
				kStateCurSudokuNumberDef);
		stateAutoCandidates = state.getBoolean(kStateAutoCandidates,
				kStateAutoCandidatesDef);

		stateGameTime = state.getInt(kStateGameTime, kStateGameTimeDef);

		stateGameScore = state.getInt(kStateGameScore, kStateGameScoreDef);

		stateGameInProgress = state.getBoolean(kStateGameInProgress,
				kStateGameInProgressDef);

		stateFlagPosRed = state.getInt(kStateFlagPosRed, kStateFlagPosRedDef);

		stateFlagPosGreen = state.getInt(kStateFlagPosGreen,
				kStateFlagPosGreenDef);

		stateFlagPosBlue = state
				.getInt(kStateFlagPosBlue, kStateFlagPosBlueDef);

		stateFlagPosOrange = state.getInt(kStateFlagPosOrange,
				kStateFlagPosOrangeDef);

		String value = state.getString(kStateStats, null);
		if (value != null && value != "")
			SudokuUtils.SudokuStats_FromString(value);

		// NSString* historyPath = [dir
		// stringByAppendingPathComponent:@"history.plist"];
		// history = [NSMutableArray arrayWithContentsOfFile:historyPath];
		// if(!self.history)
		// self.history = [NSMutableArray array];

		// added by GOLD
		SharedPreferences his = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("sudoku_history.plist", 0);

		history = new NSMutableArray();

		int n = 0;
		boolean ret = false;
		ret = his.getBoolean(String.valueOf(n), false);
		if (!ret) {
			return;
		}

		n++;
		int len = his.getInt(String.valueOf(n), 0);

		n++;
		int i = 0;
		for (i = 0; i < len; i++) {
			String str = his.getString(String.valueOf(n), "");
			if (str != null && str != "")
				history.add(str);
			n++;
		}
	}

	public void saveGameState() {
		// NSString* dir =
		// [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory,
		// NSUserDomainMask, YES) objectAtIndex:0];
		// NSString* statePath = [dir
		// stringByAppendingPathComponent:@"state.plist"];
		// NSMutableDictionary* state = [NSMutableDictionary dictionary];

		SharedPreferences state = CCDirector.sharedDirector().getActivity()
				.getSharedPreferences("sudoku_state.plist", 0);

		SharedPreferences.Editor editor;
		editor = state.edit();

		// added by GOLD
		editor.putBoolean(kPrefState, true);
		editor.commit();

		editor.putInt(kStateCurSudokuLevel, stateCurSudokuLevel);
		editor.commit();

		editor.putInt(kStateCurSudokuNumber, stateCurSudokuNumber);
		editor.commit();

		editor.putBoolean(kStateAutoCandidates, stateAutoCandidates);
		editor.commit();

		editor.putInt(kStateGameTime, stateGameTime);
		editor.commit();

		editor.putInt(kStateGameScore, stateGameScore);
		editor.commit();

		editor.putBoolean(kStateGameInProgress, stateGameInProgress);
		editor.commit();

		editor.putInt(kStateFlagPosRed, stateFlagPosRed);
		editor.commit();

		editor.putInt(kStateFlagPosGreen, stateFlagPosGreen);
		editor.commit();

		editor.putInt(kStateFlagPosBlue, stateFlagPosBlue);
		editor.commit();

		editor.putInt(kStateFlagPosOrange, stateFlagPosOrange);
		editor.commit();

		editor.putInt(kReviewGameCount, reviewGameCount);
		editor.commit();

		editor.putInt(kReviewState, reviewState);
		editor.commit();

		// [state setObject:SudokuStats_ToString() forKey:kStateStats];
		editor.putString(kStateStats, SudokuUtils.SudokuStats_ToString());

		editor.putInt(kStateCurSudokuLevel, stateCurSudokuLevel);
		editor.commit();

		// [state writeToFile:statePath atomically:YES];

		// NSString* historyPath = [dir
		// stringByAppendingPathComponent:@"history.plist"];
		// [history writeToFile:historyPath atomically:YES];
		SharedPreferences historyPath = CCDirector.sharedDirector()
				.getActivity().getSharedPreferences("sudoku_history.plist", 0);
		SharedPreferences.Editor editor2;
		editor2 = historyPath.edit();

		// added by GOLD
		int i;
		int n = 0;
		boolean ret = true;
		editor2.putBoolean(String.valueOf(n).toString(), ret);
		n++;

		int len = history.size();
		editor2.putInt(String.valueOf(n), len);
		n++;
		for (i = 0; i < len; i++) {
			editor2.putString(String.valueOf(n), history.get(i));
			n++;
		}
		editor2.commit();
	}

	// /////////////////////////////////////////////////////////////////////////////////
	// - (void) applicationDidEnterBackground:(UIApplication*)application
	public void applicationDidEnterBackground() {
		prefSkinMain = skinManager.mainSkinIndex;
		prefSkinBoard = skinManager.mainBoardIndex;
		prefSkinNumbers = skinManager.mainNumbersIndex;

		saveUserPrefs();
		saveGameState();
	}

	// - (void)applicationWillTerminate:(UIApplication *)application
	public void applicationWillTerminate() {
		SoundUtils.Sounds_PlayClose();

		prefSkinMain = skinManager.mainSkinIndex;
		prefSkinBoard = skinManager.mainBoardIndex;
		prefSkinNumbers = skinManager.mainNumbersIndex;

		saveUserPrefs();
		saveGameState();

		SudokuUtils.SudokuHints_Free();
		SoundUtils.Sounds_Free();
	}

	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// - (void)startNetProgress
	// {
	// UIApplication* app = [UIApplication sharedApplication];
	//
	// app.networkActivityIndicatorVisible = YES;
	// netProgressCount += 1;
	// }
	//
	// - (void)stopNetProgress
	// {
	// UIApplication* app = [UIApplication sharedApplication];
	//
	// netProgressCount -= 1;
	//
	// if(netProgressCount <= 0)
	// app.networkActivityIndicatorVisible = NO;
	// }

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// #define STATE_LABEL_ID 1
	// #define STATE_INDICATOR_ID 2
	//
	// - (void)createProgressView
	// {
	// UIView* _view = [[UIView alloc] initWithFrame:CGRectMake(0, 0,
	// SCREEN_WIDTH, SCREEN_HEIGHT)];
	// self.progressView = _view;
	// self.progressView.backgroundColor = [UIColor blackColor];
	// self.progressView.alpha = 0.95;
	// [_view release];
	//
	// UILabel* label = [[UILabel alloc] initWithFrame:CGRectMake(10, 200, 300,
	// 110)];
	// label."DBG" = STATE_LABEL_ID;
	// label.font = [UIFont boldSystemFontOfSize:22];
	// label.backgroundColor = [UIColor clearColor];
	// label.textColor = [UIColor whiteColor];
	// label.numberOfLines = 0;
	// label.textAlignment = UITextAlignmentCenter;
	// [self.progressView addSubview:label];
	// [label release];
	//
	// UIActivityIndicatorView* indicator = [[UIActivityIndicatorView alloc]
	// initWithFrame:CGRectMake(142, 140, 37, 37)];
	// indicator.activityIndicatorViewStyle =
	// UIActivityIndicatorViewStyleWhiteLarge;
	// indicator."DBG" = STATE_INDICATOR_ID;
	// [self.progressView addSubview:indicator];
	// [indicator release];
	// }
	//
	// - (void)showProgress:(NSString*)labelStr
	// {
	// UILabel* label = (UILabel*)[progressView viewWith"DBG":STATE_LABEL_ID];
	// UIActivityIndicatorView* indicator =
	// (UIActivityIndicatorView*)[self.progressView
	// viewWith"DBG":STATE_INDICATOR_ID];
	//
	// label.text = labelStr;
	// [indicator startAnimating];
	// [self.window addSubview:progressView];
	// }
	//
	// - (void)hideProgress
	// {
	// UIActivityIndicatorView* indicator =
	// (UIActivityIndicatorView*)[self.progressView
	// viewWith"DBG":STATE_INDICATOR_ID];
	//
	// [indicator stopAnimating];
	// [progressView removeFromSuperview];
	// }
	//
	// - (void)applicationDidBecomeActive:(UIApplication *)application {
	//
	// Chartboost *cb = [Chartboost sharedChartboost];
	//
	// // **Sudoku app
	//
	// cb.appId = @"5043772917ba47d82c000023";
	// cb.appSignature = @"eb9c7b8bf2c1ecda19741c971674d48abbbb8f2f";
	//
	// // **test app
	//
	// //cb.appId = @"50630a7117ba47c502000029";
	// //cb.appSignature = @"2f1cd076438e25b99eb743f2c66c6949b86d1dfc";
	//
	// cb.delegate = self;
	// [cb startSession];
	//
	// // Cache an interstitial at the default location
	// [cb cacheInterstitial];
	//
	// NSLog(@"Chart Boost Session Started");
	// }
	//
	// #pragma mark chartboost delegates
	//
	// - (void)didCacheInterstitial:(NSString *)location {
	//
	// NSLog(@"interstitial cached at location %@", location);
	// }
	//
	// - (void)didFailToLoadInterstitial:(NSString *)location {
	//
	// NSLog(@"failure to load interstitial at location %@", location);
	//
	// }
	//
	// - (BOOL)shouldDisplayInterstitial:(NSString *)location {
	//
	// NSLog(@"about to display interstitial at location %@", location);
	//
	// return YES;
	// }
	//
	// - (void)didDismissInterstitial:(NSString *)location {
	//
	// NSLog(@"dismissed interstitial at location %@", location);
	//
	// [[Chartboost sharedChartboost] cacheInterstitial:location];
	// }
	//
	//
	// @end
	//
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// //
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	// SudokuAppDelegate* utils_GetAppDelegate()
	// {
	// SudokuAppDelegate* appDelegate = (SudokuAppDelegate*)[[UIApplication
	// sharedApplication] delegate];
	// return appDelegate;
	// }
	//
	public static SkinManager utils_GetSkinManager() {
		// SudokuAppDelegate* appDelegate = (SudokuAppDelegate*)[[UIApplication
		// sharedApplication] delegate];
		// return appDelegate.skinManager;
		return MainActivity.skinManager;
	}

	// UIImage* utils_GetImage(ImageType image)
	public static CCSprite utils_GetImage(int image) {
		// SudokuAppDelegate* appDelegate = (SudokuAppDelegate*)[[UIApplication
		// sharedApplication] delegate];
		// return [appDelegate.skinManager getImage:image];
		return MainActivity.skinManager.getImage(image);
	}

	public static BoardDefType utils_GetBoardDef() {
		// SudokuAppDelegate* appDelegate = (SudokuAppDelegate*)[[UIApplication
		// sharedApplication] delegate];
		// return [appDelegate.skinManager getBoardDef];
		return MainActivity.skinManager.getBoardDef();
	}

}
