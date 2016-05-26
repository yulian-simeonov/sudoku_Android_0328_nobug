package com.gold.sudoku;

import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.graphics.Bitmap;

public class ToolBarButtonView extends CCSprite {

	int buttonID;
	
	public static int imageIconBack;
	public static int imageIconBackHilight;
//	UIImage* imageBack;
	
	public static int imageIcon;
	public static int imageIconHilight;
//	UIImage* image;
//	UIImage* imageHilight;

	boolean hilighted;
	
//	id messageDelegate;
//	SEL messageSelector;
//	UIView* parentView;
	
	boolean selected;
	boolean prevState;
	boolean needExecute;
	
	public ToolBarButtonView(CCTexture2D texture) {
		super(texture);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(CCTexture2D texture, CGRect rect) {
		super(texture, rect);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(CCSpriteFrame spriteFrame) {
		super(spriteFrame);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(String spriteFrameName, boolean isFrame) {
		super(spriteFrameName, isFrame);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(String filepath) {
		super(filepath);
		// TODO Auto-generated constructor stub
	}
	public ToolBarButtonView() {
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(String filepath, CGRect rect) {
		super(filepath, rect);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(Bitmap image, String key) {
		super(image, key);
		// TODO Auto-generated constructor stub
	}

	public ToolBarButtonView(CCSpriteSheet spritesheet, CGRect rect) {
		super(spritesheet, rect);
		// TODO Auto-generated constructor stub
	}
	
	
	private static ToolBarButtonView _View = null;
	public static ToolBarButtonView initWithFrame(int type, CGPoint pos)
	{
		//imageIconBack = ImageType.kImageBarEmpty;
		//imageIconBackHilight = ImageType.kImageBarEmpty;
		//imageIcon = ImageType.kImageBarEmpty;
		//imageIconHilight = ImageType.kImageBarEmpty;
		imageIconBack = ImageType.kImageBtnEmpty;
		imageIconBackHilight = ImageType.kImageBtnEmpty;
		imageIcon = ImageType.kImageBtnEmpty;
		imageIconHilight = ImageType.kImageBtnEmpty;

		
		_View = new ToolBarButtonView(MainActivity.utils_GetImage(type).getTexture());
		_View.setPosition(GB.getX(pos.x), GB.getY(pos.y));
		_View.setScaleX(GB.g_fScaleX);
		_View.setScaleY(GB.g_fScaleY);
		
		return _View;
	}

	//	- (id)initWithFrame:(CGRect)frame
	//	{
	//		self = [super initWithFrame:frame];
	//	
	//		self.backgroundColor = [UIColor clearColor];
	//	
	//		imageIconBack = kImageBarEmpty;
	//		imageIconBackHilight = kImageBarEmpty;
	//		imageIcon = kImageBarEmpty;
	//		imageIconHilight = kImageBarEmpty;
	//	
	//		return self;
	//	}

	//- (void)drawRect:(CGRect)rect 
	public void drawRect() 
	{
//		UIImage* imBack = nil;
//		UIImage* imIcon = nil;
//		double dx, dy;
//		
//		if(hilighted)
//			imBack = utils_GetImage(imageIconBackHilight);
//		
//		if(!imBack)
//			imBack = utils_GetImage(imageIconBack);
//		
//		if(!imBack)
//			imBack = imageBack;
//		
//		if(imBack)
//		{
//			UIImage* stretchImage = [imBack stretchableImageWithLeftCapWidth:5.0 topCapHeight:14.0];
//			[stretchImage drawInRect:self.bounds];
//		}
//			
//		if(hilighted)
//		{
//			if(imageHilight && (imageIconHilight == kImageBarEmpty))
//				imIcon = imageHilight;
//			else
//				imIcon = utils_GetImage(imageIconHilight);
//		}
//			
//		if(!imIcon)
//		{
//			if(image && (imageIcon == kImageBarEmpty))
//				imIcon = image;
//			else
//				imIcon = utils_GetImage(imageIcon);
//		}
//		
//		if(imIcon)
//		{
//			dx = ceil((self.bounds.size.width - imIcon.size.width)/2);
//			dy = ceil((self.bounds.size.height - imIcon.size.height)/2);
//			
//			[imIcon drawInRect:CGRectMake(self.bounds.origin.x + dx, self.bounds.origin.y + dy, imIcon.size.width, imIcon.size.height)];
//		}
	}
	
//- (void)dealloc 
//{
//[image release];
//[imageHilight release];
//
//[messageDelegate release];
//
//[super dealloc];
//}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//- (void)setHilight:(boolean)newValue
//{
//hilighted = newValue;
//
//[self setNeedsDisplay];
//}
//
//- (void)setImagesID:(ImageType)icon hilight:(ImageType)hilightIcon
//{
//imageIcon = icon;
//imageIconHilight = hilightIcon;
//self.image = nil;
//self.imageHilight = nil;
//
//[self setNeedsDisplay];
//}
//
//- (void)setImages:(UIImage*)icon hilight:(UIImage*)hilightIcon
//{
//imageIcon = kImageBarEmpty;
//imageIconHilight = kImageBarEmpty;
//self.image = icon;
//self.imageHilight = hilightIcon;
//
//[self setNeedsDisplay];
//}
//
//- (void)setBackImage:(UIImage*)icon
//{
//imageIconBack = kImageBarEmpty;
//imageBack = icon;
//
//[self setNeedsDisplay];
//}
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//- (void)buttonAnimationDidStop:(NSString *)animationID finished:(NSNumber *)finished context:(void *)context
//{
//if(needExecute)
//{
//	if(messageDelegate && messageSelector)
//		[messageDelegate performSelector:messageSelector withObject:self];
//	
//	SoundUtils.Sounds_PlayClick();
//}
//
//needExecute = NO;
//}
//
//- (void)setTapEffect:(boolean)on
//{
//[parentView bringSubviewToFront:self];
//
//[UIView beginAnimations:nil context:nil];
//[UIView setAnimationCurve:UIViewAnimationCurveEaseOut];
//[UIView setAnimationDuration:0.15];
//[UIView setAnimationBeginsFromCurrentState:YES];
//[UIView setAnimationDelegate:self];
//[UIView setAnimationDidStopSelector:@selector(buttonAnimationDidStop:finished:context:)];
//
//if(on)
//{
//	double transformFactor = (self.bounds.size.width + kButtonSelectionGrowPixels)/self.bounds.size.width;
//	self.transform = CGAffineTransformMakeScale(transformFactor, transformFactor);
//}
//else
//{
//	self.transform = CGAffineTransformMakeScale(1.0, 1.0);
//}
//	
//
//[UIView commitAnimations];
//}
//
//- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
//{
//selected = YES;
//prevState = hilighted;
//needExecute = NO;
//
//[self setHilight:!prevState];
//
//[self setTapEffect:YES];
//
//if(movementMessageDelegate)
//{
//	SEL sel = NSSelectorFromString(@"onButtonOutsideMoveBegin");
//	if(sel)
//		[movementMessageDelegate performSelector:sel];
//}
//}
//
//- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event
//{
//needExecute = NO;
//[self setHilight:prevState];
//[self setTapEffect:NO];
//
//if(movementMessageDelegate)
//{
//	SEL sel = NSSelectorFromString(@"onButtonOutsideMoveCancel");
//	if(sel)
//		[movementMessageDelegate performSelector:sel];
//}
//}
//
//- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event
//{
//boolean internal;
//CGPoint point = [[touches anyObject] locationInView:self];
//
//internal = CGRectContainsPoint(self.bounds, point);
//
//needExecute = internal;
//
//[self setHilight:prevState];
//[self setTapEffect:NO];
//
//if(movementMessageDelegate)
//{
//	SEL sel = NSSelectorFromString(@"onButtonOutsideMoveCancel");
//	if(sel)
//		[movementMessageDelegate performSelector:sel];
//}
//}
//
//- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event
//{
//boolean internal; 
//CGPoint point = [[touches anyObject] locationInView:self];
//
//internal = CGRectContainsPoint(self.bounds, point);
//
//if(internal != selected)
//{
//	if(internal)
//	{
//		[self setHilight:!prevState];
//		[self setTapEffect:YES];
//	}
//	else
//	{
//		[self setHilight:prevState];			
//		[self setTapEffect:NO];
//	}
//}
//
//selected = internal;
//
//if(movementMessageDelegate && !internal)
//{
//	SEL sel = NSSelectorFromString(@"onButtonOutsideMove:");
//	if(sel)
//		[movementMessageDelegate performSelector:sel withObject:touches];
//}
//}

}
