package com.gold.sudoku;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.sound.SoundEngine;

public class SoundUtils {
//	SoundEffect* g_SoundStartup;
//	SoundEffect* g_SoundClose;
//	SoundEffect* g_SoundTransform;
//	SoundEffect* g_SoundClick;
//	SoundEffect* g_SoundHint;
//	SoundEffect* g_SoundEraser;

	public static void Sounds_Init()
	{
		//NSString* soundPath;
		//		
		//soundPath = [[NSBundle mainBundle] pathForResource:@"Startup" ofType:@"wav"];
		//g_SoundStartup = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		//
		//soundPath = [[NSBundle mainBundle] pathForResource:@"Close" ofType:@"wav"];
		//g_SoundClose = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		//
		//soundPath = [[NSBundle mainBundle] pathForResource:@"Transform" ofType:@"wav"];
		//g_SoundTransform = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		//
		//soundPath = [[NSBundle mainBundle] pathForResource:@"Click" ofType:@"wav"];
		//g_SoundClick = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		//
		//soundPath = [[NSBundle mainBundle] pathForResource:@"HintFound" ofType:@"wav"];
		//g_SoundHint = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		//
		//soundPath = [[NSBundle mainBundle] pathForResource:@"Undo" ofType:@"wav"];
		//g_SoundEraser = [[SoundEffect soundEffectWithContentsOfFile:soundPath] retain];
		
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.click);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.close);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.hintfound);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.startup);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.transform);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.undo);
	}

	public static void Sounds_Free()
	{
		//[g_SoundStartup release];
		//[g_SoundClose release];
		//[g_SoundTransform release];
		//[g_SoundClick release];
		//[g_SoundHint release];
		//[g_SoundEraser release];
		
		SoundEngine.sharedEngine().realesAllSounds();
		SoundEngine.sharedEngine().realesAllEffects();
	}

	public static void Sounds_PlayStartup()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsStartup)
			return;
		
		//g_SoundStartup.play();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.startup);
	}

	public static void Sounds_PlayClose()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsClose)
			return;
		
		//g_SoundClose.play();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.close);
	}

	public static void Sounds_PlayTransform()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsTransform)
			return;
		
		//g_SoundTransform.play();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.transform);
	}

	public static void Sounds_PlayClick()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsClick)
			return;
		
		//[g_SoundClick play];
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.click);
	}
	
	public static void Sounds_PlayHintControls()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsHintControls)
			return;
		
		//g_SoundHint.play();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.hintfound);
	}

	public static void Sounds_PlayEraser()
	{
		//SudokuAppDelegate* appDelegate = utils_GetAppDelegate();
		
		if(!MainActivity.prefSoundsOn)
			return;
		
		if(!MainActivity.prefSoundsEraser)
			return;

		//g_SoundEraser.play();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity().getApplicationContext(), R.raw.undo);
	}

}
