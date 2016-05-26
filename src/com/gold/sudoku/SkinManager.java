package com.gold.sudoku;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGRect;

public class SkinManager {
	
	public final static float kNumberImageSizeX = 	(float) (30.0* Resolution.IPADSCL);
	public final static float kNumberImageSizeY	=	(float) (30.0* Resolution.IPADSCL);

	public final static float kNumberPossibleImageSizeX	= (float) (10.0* Resolution.IPADSCL);
	public final static float kNumberPossibleImageSizeY	= (float) (10.0* Resolution.IPADSCL);
	
//	SkinMainType mainSkinIndex;
//	SkinBoardType mainBoardIndex;
//	SkinNumbersType mainNumbersIndex;
	int mainSkinIndex;
	int mainBoardIndex;
	int mainNumbersIndex;
	
//	CCSprite images[kImageMaxCount];
	CCSprite []images; 
	
		String [][]_skinMainImages = 
		{
			{"bar_top_0.png", "bar_middle_0.png", "bar_bottom_0.png"}, //Darkness
			{"bar_top_1.png", "bar_middle_1.png", "bar_bottom_1.png"} //Sky
		};

		String _skinBoardImages[] = 
		{
			"board_0.png", //Alpha
			//"board_1.png", //BluePapirus
			"board_2.png", //MellowEllow
			"board_3.png", //Plain
			"board_4.png", //Shadow
			"board_5.png", //Symbol
			"board_6.png", //Shaddow
			"board_7.png", //Roman1
			"board_80s.png", //1980s
			"LCDBoard.png" //LED

		};


		BoardDefType []_boardsDef; 


		//String _skinMainBtnTopBarImages[2][4] =
		//{
			//{"btn_top_bar_help_0.png", "btn_top_bar_help_sel_0.png", "btn_top_bar_tools_0.png", "btn_top_bar_tools_sel_0.png"},
			//{"btn_top_bar_help_1.png", "btn_top_bar_help_sel_1.png", "btn_top_bar_tools_1.png", "btn_top_bar_tools_sel_1.png"}
		//};

		String [][]_skinMainBtnBottomBar = 
		{
			{"btn_bottom_back_0.png", null},
			{"btn_bottom_back_1.png", null}
		};

		String [][]_skinIcons = 
		{
			{"icon_undo.png", "icon_undo_sel.png"},
			{"icon_history.png", "icon_history_sel.png"},
			{"icon_flag.png", "icon_flag_sel.png"},
			{"icon_transform.png", "icon_transform_sel.png"},
			{"icon_wisard.png", "icon_wisard_sel.png"},
			{"icon_wand.png", "icon_wand_sel.png"},
		};

		String [][]_menuItems = 
		{
			{"menu_item.png", "menu_item_sel.png"},
			{"menu_btn_cancel.png", "menu_btn_cancel_sel.png"},
		};

		String [][]_numbersImages = 
		{
			{"numbers_0_base.png", "numbers_0_sel.png", null, "numbers_0_possible.png", "btn_numbers_possible_0.png"},
			{"numbers_1_base.png", "numbers_1_sel.png", null, "numbers_1_possible.png", "btn_numbers_possible_1.png"},
			{"numbers_2_base.png", "numbers_2_sel.png", "numbers_2_color_mask.png", "numbers_2_possible.png", "btn_numbers_possible_2.png"},
			{"numbers_3_base.png", "numbers_3_sel.png", "numbers_2_color_mask.png", "numbers_2_possible.png", "btn_numbers_possible_2.png"},
			{"numbers_4_base.png", "numbers_4_sel.png", "numbers_3_color_mask.png", "numbers_4_possible.png", "btn_numbers_possible_4.png"},
			{"numbers_5_base.png", "numbers_5_sel.png", "numbers_2_color_mask.png", "numbers_2_possible.png", "btn_numbers_possible_2.png"},
			{"numbers_6_base.png", "numbers_6_sel.png", "numbers_6_color_mask.png", "numbers_6_possible.png", "btn_numbers_possible_6.png"},
			{"numbers_7_base.png", "numbers_7_sel.png", null, "numbers_7_possible.png", "btn_numbers_possible_7.png"},
			{"numbers_8_base.png", "numbers_8_sel.png", null, "numbers_8_possible.png", "btn_numbers_possible_8.png"},
			{"numbers_9_base.png", "numbers_9_base.png", null, "numbers_9_possible.png", "btn_numbers_possible_9.png"},
			{"LCDnumbers.png", "LCDnumbers.png", null, "LCDpossible.png", "LCD_btn_possible.png"},
			{"numbers_pool_base.png", "numbers_pool_base.png", null, "numbers_pool_possible.png", "btn_numbers_possible_pool.png"},
		};

		//CCSprite [][]_numbersImagesHash = new CCSprite[7][9];
		//CCSprite [][]_numbersPossibleImagesHash = new CCSprite[4][9];;
		CCSprite [][]_numbersImagesHash = new CCSprite[7][10];
		CCSprite [][]_numbersPossibleImagesHash = new CCSprite[4][10];;
	
	public SkinManager()
	{
		images = new CCSprite[ImageType.kImageMaxCount];
		
		_boardsDef = new BoardDefType[9];
		
		for ( int i = 0; i < 9; i++ )
		{
			_boardsDef[i] = new BoardDefType();
		
			_boardsDef[i].itemSizeX = 34;
			_boardsDef[i].itemSizeY = 34;
		
			_boardsDef[i].itemPosX[0] = 1; _boardsDef[i].itemPosX[1] = 36; _boardsDef[i].itemPosX[2] = 71; 
			_boardsDef[i].itemPosX[3] = 107; _boardsDef[i].itemPosX[4] = 142; _boardsDef[i].itemPosX[5] = 177; 
			_boardsDef[i].itemPosX[6] = 213; _boardsDef[i].itemPosX[7] = 248; _boardsDef[i].itemPosX[8] = 283; 
		
			_boardsDef[i].itemPosY[0] = 1; _boardsDef[i].itemPosY[1] = 36; _boardsDef[i].itemPosY[2] = 71; 
			_boardsDef[i].itemPosY[3] = 107; _boardsDef[i].itemPosY[4] = 142; _boardsDef[i].itemPosY[5] = 177; 
			_boardsDef[i].itemPosY[6] = 213; _boardsDef[i].itemPosY[7] = 248; _boardsDef[i].itemPosY[8] = 283; 
		}
		

	}
//	#define releaseImage(id) {if(images[id]){ [images[id] release]; } images[id] = null;}
	
	public void releaseImage( int id )
	{
		if( images[id] != null )
		{
			images[id] = null;
		}
		
	}
	
	public void setSkinMain(int skinMain)
	{
		mainSkinIndex = skinMain;

		releaseImage(ImageType.kImageBarTop);
		releaseImage(ImageType.kImageBarMiddle);
		releaseImage(ImageType.kImageBarBottom);
		
		//releaseImage(kImageBarBtnHelp);
		//releaseImage(kImageBarBtnHelpSel);
		//releaseImage(kImageBarBtnTools);
		//releaseImage(kImageBarBtnToolsSel);
		
		releaseImage(ImageType.kImageBarBottomBack);
	    
	    releaseImage(ImageType.kImageIconHelp);
	    releaseImage(ImageType.kImageIconHelpSel);
	    
	    releaseImage(ImageType.kImageIconMenu);
	    releaseImage(ImageType.kImageIconMenuSel);
	}

	public void setSkinBoard(int skinBoard)
	{
		mainBoardIndex = skinBoard;
		
		releaseImage(ImageType.kImageBoard);
	}

	public void freeNumbersHash()
	{
		for(int row = 0; row < 7; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(_numbersImagesHash[row][col] == null )
					continue;
				
//				[_numbersImagesHash[row][col] release];
				_numbersImagesHash[row][col] = null;
			}
		}

		for(int row = 0; row < 2; row++)
		{
			for(int col = 0; col < 9; col++)
			{
				if(_numbersPossibleImagesHash[row][col] == null )
					continue;
				
//				[_numbersPossibleImagesHash[row][col] release];
				_numbersPossibleImagesHash[row][col] = null;
			}
		}
	}

	public void setSkinNumbers(int skinNumbers)
	{
		mainNumbersIndex = skinNumbers;
		
		releaseImage(ImageType.kImageNumberBase);
		releaseImage(ImageType.kImageNumberHighlight);
		releaseImage(ImageType.kImageNumberColorMask);
		releaseImage(ImageType.kImageCandidate);
		releaseImage(ImageType.kImageCandidateBtns);
		
		this.freeNumbersHash();
	
	}	


//	- (void)dealloc 
//	{
//		for(int index = 0; index < kImageMaxCount; index++)
//			releaseImage(index);
//		
//		[self freeNumbersHash];
//		
//	    [super dealloc];
//	}

	public CCSprite getImageByType( int imageType, String imageName )
	{
		if(images[imageType] == null)
		{
			images[imageType] = CCSprite.sprite(imageName); // [[UIImage imageNamed:(imageName)] retain];
		}
		return images[imageType];
	}
	public CCSprite getImage(int imageType)
	{
		CCSprite result = null;
		String name = null;
		
		if(imageType == ImageType.kImageBarEmpty)
			return null;

		switch(imageType)
		{
		case ImageType.kImageBtnEmpty: name = "btn_empty.png"; break;
		case ImageType.kImageBtnResume: name = "btn_resume.png"; break;
		case ImageType.kImageBtnResumeSel: name = "btn_resume_sel.png"; break;
		
		
		case ImageType.kImageBarTop: name = _skinMainImages[mainSkinIndex][0]; break;
		case ImageType.kImageBarMiddle: name = _skinMainImages[mainSkinIndex][1]; break;
		case ImageType.kImageBarBottom: name = _skinMainImages[mainSkinIndex][2]; break;
				
		case ImageType.kImageBoard: name = _skinBoardImages[mainBoardIndex]; break;
				
		//case kImageBarBtnHelp: name = _skinMainBtnTopBarImages[mainSkinIndex][0]; break;
		//case kImageBarBtnHelpSel: name = _skinMainBtnTopBarImages[mainSkinIndex][1]; break;
		//case kImageBarBtnTools: name = _skinMainBtnTopBarImages[mainSkinIndex][2]; break;
		//case kImageBarBtnToolsSel: name = _skinMainBtnTopBarImages[mainSkinIndex][3]; break;

		case ImageType.kImageBarBottomBack: name = _skinMainBtnBottomBar[mainSkinIndex][0]; break;
				
	    case ImageType.kImageIconHelp: name = "help.png"; break;
	    case ImageType.kImageIconHelpSel: name = "helpsel.png"; break;
	    case ImageType.kImageIconMenu: name = "menu2.png"; break;
	    case ImageType.kImageIconMenuSel: name = "menu2sel.png"; break;
	            
		case ImageType.kImageIconUndo: name = _skinIcons[0][0]; break;
		case ImageType.kImageIconUndoSel: name = _skinIcons[0][1]; break;
		case ImageType.kImageIconHistory: name = _skinIcons[1][0]; break;
		case ImageType.kImageIconHistorySel: name = _skinIcons[1][1]; break;
		case ImageType.kImageIconFlag: name = _skinIcons[2][0]; break;
		case ImageType.kImageIconFlagSel: name = _skinIcons[2][1]; break;
		case ImageType.kImageIconTransform: name = _skinIcons[3][0]; break;
		case ImageType.kImageIconTransformSel: name = _skinIcons[3][1]; break;
		case ImageType.kImageIconWisard: name = _skinIcons[4][0]; break;
		case ImageType.kImageIconWisardSel: name = _skinIcons[4][1]; break;
		case ImageType.kImageIconWand: name = _skinIcons[5][0]; break;
		case ImageType.kImageIconWandSel: name = _skinIcons[5][1]; break;

		case ImageType.kImageIconBarGray: name = "icon_dark_gray.png"; break;
		case ImageType.kImageIconBarRed: name = "icon_red.png"; break;
		case ImageType.kImageIconBarOrange: name = "icon_orange.png"; break;
		case ImageType.kImageIconBarYellow: name = "icon_yellow.png"; break;
		case ImageType.kImageIconBarGreen: name = "icon_green.png"; break;
		case ImageType.kImageIconBarBlue: name = "icon_blue.png"; break;
		case ImageType.kImageIconBarOK: name = "icon_ok.png"; break;	
		case ImageType.kImageIconBarCancel: name = "icon_cancel.png"; break;
		case ImageType.kImageIconBarPossible: name = "icon_candidate.png"; break;
		case ImageType.kImageIconBarCandidate: name = "icon_possible.png"; break;

		case ImageType.kImageIconBarGraySel: name = "icon_dark_gray_sel.png"; break;
		case ImageType.kImageIconBarRedSel: name = "icon_red_sel.png"; break;
		case ImageType.kImageIconBarOrangeSel: name = "icon_orange_sel.png"; break;
		case ImageType.kImageIconBarYellowSel: name = "icon_yellow_sel.png"; break;
		case ImageType.kImageIconBarGreenSel: name = "icon_green_sel.png"; break;
		case ImageType.kImageIconBarBlueSel: name = "icon_blue_sel.png"; break;
		case ImageType.kImageIconBarOKSel: name = "icon_ok_sel.png"; break;	
		case ImageType.kImageIconBarCancelSel: name = "icon_cancel_sel.png"; break;
		case ImageType.kImageIconBarPossibleSel: name = "icon_candidate_sel.png"; break;
		case ImageType.kImageIconBarCandidateSel: name = "icon_possible_sel.png"; break;
		
		case ImageType.kImageMenuHeader: name = "menu_header.png"; break;
		case ImageType.kImageMenuItem: name = _menuItems[0][0]; break;
		case ImageType.kImageMenuItemSel: name = _menuItems[0][1]; break;
		case ImageType.kImageMenuButton: name = _menuItems[1][0]; break;
		case ImageType.kImageMenuButtonSel: name = _menuItems[1][1]; break;
		case ImageType.kImageMenuIconNextLevel: name = "menu_next_level.png"; break;
				
		case ImageType.kImageNumberBase: name = _numbersImages[mainNumbersIndex][0]; break;
		case ImageType.kImageNumberHighlight: name = _numbersImages[mainNumbersIndex][1]; break;
		case ImageType.kImageNumberColorMask: name = _numbersImages[mainNumbersIndex][2]; break;
		case ImageType.kImageCandidate: name = _numbersImages[mainNumbersIndex][3]; break;
		case ImageType.kImageCandidateBtns: name = _numbersImages[mainNumbersIndex][4]; break;
				
		case ImageType.kIconControlBack: name = "button_back.png"; break;
		case ImageType.kIconControlForward: name = "button_forward.png"; break;
		case ImageType.kIconControlPlay: name = "button_play.png"; break;
		case ImageType.kIconControlPause: name = "button_pause.png"; break;

		case ImageType.kIconControlBackSel: name = "button_back_sel.png"; break;
		case ImageType.kIconControlForwardSel: name = "button_forward_sel.png"; break;
		case ImageType.kIconControlPlaySel: name = "button_play_sel.png"; break;
		case ImageType.kIconControlPauseSel: name = "button_pause_sel.png"; break;
				
		case ImageType.kIconControlTimer: name = "icon_timer.png"; break;
		case ImageType.kIconControlYangYang: name = "icon_yang_yang.png"; break;
		case ImageType.kIconControlShieldBlue: name = "icon_shield_blue.png"; break;
		case ImageType.kIconControlShieldGreen: name = "icon_shield_green.png"; break;
		case ImageType.kIconControlShieldOrange: name = "icon_shield_orange.png"; break;
		case ImageType.kIconControlShieldPurple: name = "icon_shield_purple.png"; break;
		case ImageType.kIconControlShieldRed: name = "icon_shield_red.png"; break;
		case ImageType.kIconControlShieldBlack: name = "icon_shield_black.png"; break;

		case ImageType.kIconControlTimerSel: name = "icon_timer_sel.png"; break;
		case ImageType.kIconControlYangYangSel: name = "icon_yang_yang_sel.png"; break;
		case ImageType.kIconControlShieldBlueSel: name = "icon_shield_blue_sel.png"; break;
		case ImageType.kIconControlShieldGreenSel: name = "icon_shield_green_sel.png"; break;
		case ImageType.kIconControlShieldOrangeSel: name = "icon_shield_orange_sel.png"; break;
		case ImageType.kIconControlShieldPurpleSel: name = "icon_shield_purple_sel.png"; break;
		case ImageType.kIconControlShieldRedSel: name = "icon_shield_red_sel.png"; break;
		case ImageType.kIconControlShieldBlackSel: name = "icon_shield_black_sel.png"; break;
		
		}

		if((images[imageType] == null) && (name != null))
			images[imageType] =      CCSprite.sprite(name) ; // imageNamed(name) ;
		
		result = images[imageType];	
		
		return result;
	}

	public BoardDefType getBoardDef()
	{
		return _boardsDef[mainBoardIndex];
	}

	 public CCSprite getBoardPartWithCol(int col, int row, float inset)
	{
		CCSprite imagePart = null;	
		BoardDefType boardDef = this.getBoardDef();
		CCSprite boardImage = this.getImage(ImageType.kImageBoard);
		
		//CGRect partBounds = CGRect.make(boardDef.itemPosX[col], boardDef.itemPosY[row], boardDef.itemSizeX, boardDef.itemSizeY); //CGRect.make(boardDef.itemPosX[col], boardDef.itemPosY[row], boardDef.itemSizeX, boardDef.itemSizeY);
		//partBounds = CGRectInset(partBounds, inset, inset);
		CGRect partBounds = CGRect.make(boardDef.itemPosX[col], boardDef.itemPosY[row], boardDef.itemSizeX, boardDef.itemSizeY); //CGRect.make(boardDef.itemPosX[col], boardDef.itemPosY[row], boardDef.itemSizeX, boardDef.itemSizeY);

////	CGImageRef image = CGImageCreateWithImageInRect(boardImage.CGImage, CGRectScale(partBounds,SCALE_FACTOR));
////	CCTexture2D texture = CCTextureCache.sharedTextureCache().addImage(livesString)
////				imagePart = CCSprite.sprite(texture, rect)
//		imagePart = [UIImage imageWithCGImage:image];
//		CGImageRelease(image);

		imagePart = CCSprite.sprite(boardImage.getTexture(), partBounds);
		return imagePart;
	}

	public CCSprite getNumberImageWithValue(int value,int color ,boolean selected)
	{
//		CGColorSpaceRef colorSpace;
//		CGContextRef imageContext;
		//CGImageRef image;
		CCSprite image;
		CCSprite resultImage = null;
		CGRect dstBounds;
		CGRect srcBounds;
		CCSprite numbersImage;
		CCSprite highlightImage;
		CCSprite colorMaskImage;
		
		if(value < 1 || value > 9)
			return null;
		
		if(!selected)
		{
			if(_numbersImagesHash[color][value - 1] != null)
				return _numbersImagesHash[color][value - 1];
		}

		dstBounds = CGRect.make(0, 0, kNumberImageSizeX, kNumberImageSizeY);
		srcBounds = CGRect.make(0, 0, kNumberImageSizeX, kNumberImageSizeY);
		numbersImage = this.getImage(ImageType.kImageNumberBase); // [self getImage:kImageNumberBase];
		highlightImage = this.getImage(ImageType.kImageNumberHighlight);
		colorMaskImage = this.getImage(ImageType.kImageNumberColorMask);
		
//		colorSpace = CGColorSpaceCreateDeviceRGB();
//		imageContext = CGBitmapContextCreate(null, kNumberImageSizeX, kNumberImageSizeY, 8, 0, colorSpace, kCGImageAlphaPremultipliedLast);
		
		srcBounds.origin.x = kNumberImageSizeX*(value - 1);
		
		if(selected)
		{
			//image = CGImageCreateWithImageInRect(highlightImage.CGImage, CGRectScale(srcBounds,SCALE_FACTOR));
			image = CCSprite.sprite(highlightImage.getTexture(), srcBounds);
//			CGContextDrawImage(imageContext, dstBounds, image);
//			CFRelease(image);
		}
		
		if(colorMaskImage == null)
			srcBounds.origin.y = kNumberImageSizeY*color;
		
		//image = CGImageCreateWithImageInRect(numbersImage.CGImage, CGRectScale(srcBounds,SCALE_FACTOR));
		image = CCSprite.sprite(numbersImage.getTexture(), srcBounds);
//		CGContextDrawImage(imageContext, dstBounds, image);
//		CFRelease(image);

		if((colorMaskImage != null) && (color > 0))
		{
			srcBounds.origin.y = 0;
			srcBounds.origin.x = kNumberImageSizeX*color;
			
			//image = CGImageCreateWithImageInRect(colorMaskImage.CGImage, CGRectScale(srcBounds,SCALE_FACTOR));
			image = CCSprite.sprite(colorMaskImage.getTexture(), srcBounds);
//			CGContextDrawImage(imageContext, dstBounds, image);
//			CFRelease(image);
		}
		
		//image = CGBitmapContextCreateImage(imageContext);
		
		//resultImage = [UIImage imageWithCGImage:image];
		resultImage = image;
		
//		CFRelease(image);
//		CFRelease(imageContext);
//		CFRelease(colorSpace);
		
		if(!selected)
			//_numbersImagesHash[color][value - 1] = [resultImage retain];
			_numbersImagesHash[color][value - 1] = resultImage;
		
		return resultImage;
	}

	public CCSprite getCandidateImageWithValue(int value,int color)
	{
//		CGImageRef image;
		CCSprite image;
		CGRect srcBounds = CGRect.make(0, 0, kNumberPossibleImageSizeX, kNumberPossibleImageSizeY);
		CCSprite numbersSrc = getImage(ImageType.kImageCandidate);
		CCSprite resultImage = null;
		
		if(value < 1 || value > 9)
			return null;
		
		if(_numbersPossibleImagesHash[color][value] != null)
			return _numbersPossibleImagesHash[color][value];
		
		srcBounds.origin.x = kNumberPossibleImageSizeX*(value - 1);
		srcBounds.origin.y = kNumberPossibleImageSizeY*color;
		
		//image = CGImageCreateWithImageInRect(numbersSrc.CGImage, CGRectScale(srcBounds,Resolution.SCALE_FACTOR));
		image = CCSprite.sprite(numbersSrc.getTexture(), srcBounds);
		//resultImage = [UIImage imageWithCGImage:image];
		resultImage = image;
		
		//CFRelease(image);
		
		//_numbersPossibleImagesHash[color][value] = [resultImage retain];
		_numbersPossibleImagesHash[color][value] = resultImage;
		
		return resultImage;
	}

	public CCSprite getCandidateButtonImageWithValue(int value, boolean selected)
	{
		//CGImageRef image;
		CCSprite image;
		CGRect srcBounds = CGRect.make(0, 0, kNumberImageSizeX, kNumberImageSizeY);
		CCSprite numbersSrc = getImage(ImageType.kImageCandidateBtns);
		CCSprite resultImage= null;
		
		if(value < 1 || value > 9)
			return null;
		
		srcBounds.origin.x = kNumberImageSizeX*(value - 1);
		if(selected) srcBounds.origin.y += kNumberImageSizeY;
		
		//image = CGImageCreateWithImageInRect(numbersSrc.CGImage, CGRectScale(srcBounds,SCALE_FACTOR));
		image = CCSprite.sprite(numbersSrc.getTexture(), srcBounds);
		//resultImage = [UIImage imageWithCGImage:image];
		resultImage = image;
		
		//CFRelease(image);
		
		return resultImage;
	}
}
