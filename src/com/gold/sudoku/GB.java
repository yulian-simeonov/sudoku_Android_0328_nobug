package com.gold.sudoku;

import java.lang.reflect.Method;
import java.util.Random;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGSize;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class GB {
	public static int PTM_RATIO = 32;
	public static int G_WIDTH = 320;
	public static int G_HEIGHT = 480;

	public static CGSize  g_winSize = CGSize.make(0, 0);
	
	public static float g_fScaleX = 1.0f;
	public static float g_fScaleY = 1.0f;



	//////////////////////////////////////////////////////////////////////////////////
	
	GB() {
		
	}

	public static float getX(float x)
	{
	    float fx;
	    
	    fx = g_winSize.width * x / G_WIDTH;
	    return fx;
	}

	public static float getY(float y)
	{
	    float fy;
	    fy = g_winSize.height - g_winSize.height * y / G_HEIGHT;
	    return fy;
	}

	public static float getY_(float y)
	{
	    float fy;
	    fy = g_winSize.height * y / G_HEIGHT;
	    return fy;
	}

	public static int arc4random()
	{
		Random random = new Random();
		return random.nextInt(655536);
	}

	public static int RANDOM_SEED() {
		//srandom((unsigned)(mach_absolute_time() & 0xFFFFFFFF))
		Random random = new Random();
		return random.nextInt();
	}
	
	public static float RANDOM_FLOAT() {
		//((float)random() / (float)INT32_MAX)
		Random random = new Random();
		return random.nextFloat();
	}

	public static void AlertUtils_ShowDoneAlert(final String title, final String message)
	{
	    CCDirector.sharedDirector().getActivity().runOnUiThread(new Runnable() {
	        public void run() {
	            AlertDialog.Builder builder = new AlertDialog.Builder(CCDirector.sharedDirector().getActivity());
	            builder.setTitle(title)
	            	.setMessage(message)
	            	.setCancelable(false)
	                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                           //CCDirector.sharedDirector().getActivity().finish();
	                           dialog.cancel();
	                           GameScene.onDoneMessageClosed(0);
	                       }
	                   });
	            AlertDialog alert = builder.create();
	            alert.show();
	        }
	    });
	}

	public static void AlertUtils_ShowYesNoAlert(final String title, final String message)
	{
		
	    CCDirector.sharedDirector().getActivity().runOnUiThread(new Runnable() {
	        public void run() {
	            AlertDialog.Builder builder = new AlertDialog.Builder(CCDirector.sharedDirector().getActivity());
	            builder.setTitle(title)
	            	.setMessage(message)
	            	.setCancelable(false)
	                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int id) {
	                           //CCDirector.sharedDirector().getActivity().finish();
	                           dialog.cancel();
	                           GameScene.onAskReviewResult(0);
	                       }
	                   })
	                .setNegativeButton("No", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int id) {
	                        //CCDirector.sharedDirector().getActivity().finish();
	                        dialog.cancel();
	                    }
                });
	            AlertDialog alert = builder.create();
	            alert.show();
	        }
	    });
	}
}
