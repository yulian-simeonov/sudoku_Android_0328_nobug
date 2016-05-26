package com.gold.sudoku;

import org.cocos2d.menus.CCMenu;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class HistoryToolBar extends ToolBarView {
	
	CCMenu m_pMenu;
	//UISlider historySlider;
	int _maximumValue = 1;
	int _value = 1;
	int sliderpos = 100;
	
	int flag = 0;
	
	ButtonItemDef []_buttonsMiddleBar_History = 
	{
		//{101, {249, 5, 30, 30}, kImageIconBarCancel, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, "onHistoryCancel:"},
		new ButtonItemDef(101, CGPoint.ccp(264, 20), ImageType.kImageIconBarCancel, ImageType.kImageIconBarCancelSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onHistoryCancel"),
		//{102, {284, 5, 30, 30}, kImageIconBarOK, kImageBarEmpty, kImageBarBottomBack, kImageBarBottomBack, "onHistoryOK:"},
		new ButtonItemDef(102, CGPoint.ccp(299, 20), ImageType.kImageIconBarOK, ImageType.kImageIconBarOKSel, ImageType.kImageBarBottomBack, ImageType.kImageBarBottomBack, "onHistoryOK"),
	};


	HistoryToolBar(int type, CGPoint pos)
	{
		super(type, pos);
	}
	
	public  void initHistoryBar()
	{
		//loadButtons(_buttonsMiddleBar_History,  count:(sizeof(_buttonsMiddleBar_History)/sizeof(ButtonItemDef)));
//		loadButtons(_buttonsMiddleBar_History,  2);
		
//		UISlider* slider = [[UISlider alloc] initWithFrame:rcHistorySlider];
//		slider.backgroundColor = [UIColor clearColor];
//		[self addSubview:slider];
//		[slider addTarget:self action:@selector(sliderAction:) forControlEvents:UIControlEventValueChanged];
//		self.historySlider = slider;
//		[slider release];
		
//		UISlider slider = new UISlider();
	}
	
	public boolean checkend()
	{
		sliderAction();

		if(flag == 1)
		{
			((GameScene)(this.messageDelegate)).onHistoryCancel(this);
			return true;
		}
		else if(flag == 2)
		{
			((GameScene)(this.messageDelegate)).onHistoryOK(this);
			return true;
		}
		
		return false;
	}
	
	public void addSliderActivity(){
		
		flag = 0;

		final Activity mActivity=CCDirector.sharedDirector().getActivity();
		final LinearLayout view=(LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.activity_slider, null);

		
		final LinearLayout hv1 = new LinearLayout(mActivity);
 		hv1.setOrientation(LinearLayout.HORIZONTAL);
	    LinearLayout.LayoutParams paramhv1 = new LinearLayout.LayoutParams(
	    	     LinearLayout.LayoutParams.FILL_PARENT,
	    	     LinearLayout.LayoutParams.WRAP_CONTENT);
	    
	    CGRect rect = Resolution.kPositionBarMiddleRect;
	    paramhv1.setMargins((int)GB.getX(rect.origin.x), (int)GB.getY_(rect.origin.y), 0, 0);
	    paramhv1.width = (int)GB.getX(rect.size.width);
	    paramhv1.height = (int)GB.getY_(rect.size.height);
	    view.addView(hv1, paramhv1);
	
	    final SeekBar  m_SeekBar = new SeekBar(mActivity); 
		m_SeekBar.setMax(100);
		m_SeekBar.setProgress(100);
		m_SeekBar.incrementProgressBy(1);

	    LinearLayout.LayoutParams paramseek = new LinearLayout.LayoutParams(
	    	     LinearLayout.LayoutParams.WRAP_CONTENT,
	    	     LinearLayout.LayoutParams.WRAP_CONTENT);
	    paramseek.leftMargin = (int)GB.getX(5);
	    paramseek.topMargin = (int)GB.getY_(15);
	    paramseek.width = (int)GB.getX(239);
	    hv1.addView(m_SeekBar, paramseek);

	    m_SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				sliderpos = seekBar.getProgress();
			}
		});

	    final Button  btnCancel = new Button(mActivity); 
	    btnCancel.setWidth((int)GB.getX(30));
	    btnCancel.setHeight((int)GB.getY_(30));
	    btnCancel.setId(123459);
	    btnCancel.setBackgroundResource(R.drawable.icon_cancel);
	    LinearLayout.LayoutParams paramcancel = new LinearLayout.LayoutParams(
	    	     LinearLayout.LayoutParams.WRAP_CONTENT,
	    	     LinearLayout.LayoutParams.WRAP_CONTENT);
	    paramcancel.leftMargin = (int)GB.getX(5);
	    paramcancel.rightMargin = (int)GB.getX(6);
	    paramcancel.topMargin = (int)GB.getY_(5);
        //android:layout_weight="1"
	    hv1.addView(btnCancel, paramcancel);

	    final Button  btnOk = new Button(mActivity); 
	    btnOk.setWidth((int)GB.getX(30));
	    btnOk.setHeight((int)GB.getY_(30));
	    btnOk.setId(123460);
	    btnOk.setBackgroundResource(R.drawable.icon_ok);
	    LinearLayout.LayoutParams paramok = new LinearLayout.LayoutParams(
	    	     LinearLayout.LayoutParams.WRAP_CONTENT,
	    	     LinearLayout.LayoutParams.WRAP_CONTENT);
	    paramok.rightMargin = (int)GB.getX(6);
	    paramok.topMargin = (int)GB.getY_(5);
        //android:layout_weight="1"
	    hv1.addView(btnOk, paramok);

	    btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				view.removeAllViews();
				flag = 1;
			}
		});

	    btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				view.removeAllViews();
				flag = 2;
			}
		});

	    mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
		    	mActivity.addContentView(view, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
		});
	}
	
	public void drawFlag(String flagImageName, int pos)
	{
		//int maxValue = historySlider.maximumValue;
		int maxValue = _maximumValue;
		
		if(pos <= 0 || pos > maxValue)
			return;
		
		//CGRect sliderBounds = historySlider.bounds;
		//CGRect flagBounds = sliderBounds;
		CGRect flagBounds = CGRect.make(5, 5, 240, 42);
		flagBounds.origin.y = 2*Resolution.IPADSCL;
		flagBounds.size.height = 15*Resolution.IPADSCL;
		
		double offset = (double)pos * flagBounds.size.width / (double)maxValue;
		flagBounds.origin.x += offset - 10*Resolution.IPADSCL;
		flagBounds.size.width = 20*Resolution.IPADSCL;
		
		//DrawImageCentered(GameCenter. flagBounds, imageNamed(flagImageName));
		GameBoardUtils.DrawImageCentered(GameScene.toolBarHistory._view, flagBounds, CCSprite.sprite(flagImageName));
	}

	//- (void)drawRect:(CGRect)rect 
	public void drawRect() 
	{
		//[super drawRect:rect];
		GameScene.toolBarHistory._view.removeAllChildren(true);
		
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		drawFlag("menu_flag_red_small.png", MainActivity.stateFlagPosRed);
		drawFlag("menu_flag_green_small.png", MainActivity.stateFlagPosGreen);
		drawFlag("menu_flag_blue_small.png", MainActivity.stateFlagPosBlue);
		drawFlag("menu_flag_orange_small.png", MainActivity.stateFlagPosOrange);
	}

	public void refresh()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		//historySlider.maximumValue = MainActivity.history.size() - 1.0;
		_maximumValue = MainActivity.history.size() - 1;
		//historySlider.value = MainActivity.history.size() - 1.0;
		_value = MainActivity.history.size() - 1;
		
		//[self setNeedsDisplay];
		drawRect();
	}

	//- (void)sliderAction:(id)sender
	public void sliderAction()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		if(_value < 0 || _maximumValue < 0)
			return;
		
		//SudokuHistory_RestoreAtIndex((int)(historySlider.value + 0.5));
		_value = (int)(sliderpos / 100.0f * _maximumValue);
		SudokuUtils.SudokuHistory_RestoreAtIndex((int)(_value + 0.5));
		//[appDelegate.viewController redrawAllSubviews:appDelegate.viewController.boardView];
		GameScene.boardView.drawRect();
	}

}
