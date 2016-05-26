package com.gold.sudoku;

public class MenuDefenitions {

	public static MenuDef _menuDef_Tools_NewSudoku = null;
	public static MenuDef _menuDef_MoreTools_ClearNumbers_ByColor = null; 
	public static MenuDef _menuInfo_MoreTools_ClearNumbers_ByValue = null;
	public static MenuDef _menuInfo_MoreTools_ClearNumbers = null;
	public static MenuDef _menuInfo_MoreTools_ClearCandidates_ByValue = null;
	public static MenuDef _menuInfo_MoreTools_ClearCandidates = null;
	public static MenuDef _menuInfo_MoreTools_ChangeColors = null; 
	public static MenuDef _menuInfo_MoreTools = null;
	public static MenuDef _menuInfo_Options_NumberStyles = null;
	public static MenuDef _menuInfo_Options_PictureStyles = null; 
	public static MenuDef _menuInfo_Options_GridStyles = null;
	public static MenuDef _menuInfo_Options_BackStyles = null;
	public static MenuDef _menuInfo_Options_SkinPresets = null;
	public static MenuDef _menuInfo_Options_Symmetry = null;
	public static MenuDef _menuInfo_Options_Sounds = null;
	public static MenuDef _menuInfo_Options_Keypad_options = null;
	public static MenuDef _menuInfo_Options_Keypad = null;
	public static MenuDef _menuInfo_Options = null;
	public static MenuDef _menuInfo_AdvancedStatistics = null;
	public static MenuDef _menuInfo_GameCenter = null;
	public static MenuDef _menuInfo = null;
	public static MenuDef _menuHelp = null;
	public static MenuDef _menuWand_NakedSubset = null;
	public static MenuDef _menuWand_HiddenSubset_Items = null;
	public static MenuDef _menuWand_HiddenSubset_DirectItems = null;
	public static MenuDef _menuWand_HiddenSubset = null;
	public static MenuDef _menuWand_Fisherman = null;
	public static MenuDef _menuWand_Intersections = null;
	public static MenuDef _menuWand_Suggest_Value_Cell = null;
	public static MenuDef _menuWand_Wings = null;
	public static MenuDef _menuWand_AlignedExclusion = null;
	public static MenuDef _menuWand = null;
	public static MenuDef _menuWisard_PossibleSells = null;
	public static MenuDef _menuWisard_ImpossibleSells = null;
	public static MenuDef _menuWisard = null;
	public static MenuDef _menuTransform = null;
	public static MenuDef _menuFlag = null;
	public static MenuDef _menuInfo_MoreTools_ChangeColors_Colors = null;

//	int count;
//	String updateMenuDefCallback;
//	String stateUpdateCallback;
//	MenuItemDef []items;

	public MenuDefenitions ()
	{
		//////////////////////////////////////////////////////////////////////////////////////////////
		_menuDef_Tools_NewSudoku = new  MenuDef();
		
		_menuDef_Tools_NewSudoku.count = 6;
		_menuDef_Tools_NewSudoku.updateMenuDefCallback = "onStartNewGameUpddateMenuDef"; 
		_menuDef_Tools_NewSudoku.stateUpdateCallback = null;
		_menuDef_Tools_NewSudoku.items = new MenuItemDef[6];
		for(int i = 0; i < _menuDef_Tools_NewSudoku.count; i ++)
			_menuDef_Tools_NewSudoku.items[i] = new MenuItemDef();
		
		_menuDef_Tools_NewSudoku.items[0].itemID = 0;
		_menuDef_Tools_NewSudoku.items[0].itemName = "Simple";
		_menuDef_Tools_NewSudoku.items[0].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[0].imageName = "menu_diff_1.png";
		_menuDef_Tools_NewSudoku.items[0].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[0].submenu = null;
		
		_menuDef_Tools_NewSudoku.items[1].itemID = 1;
		_menuDef_Tools_NewSudoku.items[1].itemName = "Easy";
		_menuDef_Tools_NewSudoku.items[1].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[1].imageName = "menu_diff_2.png";
		_menuDef_Tools_NewSudoku.items[1].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[1].submenu = null;
		
		_menuDef_Tools_NewSudoku.items[2].itemID = 2;
		_menuDef_Tools_NewSudoku.items[2].itemName = "Medium";
		_menuDef_Tools_NewSudoku.items[2].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[2].imageName = "menu_diff_3.png";
		_menuDef_Tools_NewSudoku.items[2].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[2].submenu = null;
		
		_menuDef_Tools_NewSudoku.items[3].itemID = 3;
		_menuDef_Tools_NewSudoku.items[3].itemName = "Hard";
		_menuDef_Tools_NewSudoku.items[3].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[3].imageName = "menu_diff_4.png";
		_menuDef_Tools_NewSudoku.items[3].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[3].submenu = null;
		
		_menuDef_Tools_NewSudoku.items[4].itemID = 4;
		_menuDef_Tools_NewSudoku.items[4].itemName = "Very Hard";
		_menuDef_Tools_NewSudoku.items[4].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[4].imageName = "menu_diff_5.png";
		_menuDef_Tools_NewSudoku.items[4].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[4].submenu = null;
		
		_menuDef_Tools_NewSudoku.items[5].itemID = 5;
		_menuDef_Tools_NewSudoku.items[5].itemName = "Sudoku Master!";
		_menuDef_Tools_NewSudoku.items[5].imageType = ImageType.kImageBarEmpty;
		_menuDef_Tools_NewSudoku.items[5].imageName = "menu_diff_6.png";
		_menuDef_Tools_NewSudoku.items[5].menuCommand = "onStartNewGame";
		_menuDef_Tools_NewSudoku.items[5].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////////////
		_menuDef_MoreTools_ClearNumbers_ByColor = new MenuDef();
		
		_menuDef_MoreTools_ClearNumbers_ByColor.count = 6;
		_menuDef_MoreTools_ClearNumbers_ByColor.updateMenuDefCallback = null; 
		_menuDef_MoreTools_ClearNumbers_ByColor.stateUpdateCallback = null;
		_menuDef_MoreTools_ClearNumbers_ByColor.items = new MenuItemDef[6];
		for(int i = 0; i < _menuDef_MoreTools_ClearNumbers_ByColor.count; i ++)
			_menuDef_MoreTools_ClearNumbers_ByColor.items[i] = new MenuItemDef();

		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].itemID = 1;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].itemName = "Clear All Dark Gray";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].imageName = "menu_clear_dark_gray.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[0].submenu = null;
		
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].itemID = 2;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].itemName = "Clear All Red";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].imageName = "menu_clear_red.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[1].submenu = null;
		
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].itemID = 3;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].itemName = "Clear All Orange";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].imageName = "menu_clear_orange.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[2].submenu = null;
		
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].itemID = 4;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].itemName = "Clear All Yellow";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].imageName = "menu_clear_yellow.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[3].submenu = null;
		
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].itemID = 5;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].itemName = "Clear All Green";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].imageName = "menu_clear_green.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[4].submenu = null;
		
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].itemID = 6;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].itemName = "Clear All Blue";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].imageType = ImageType.kImageBarEmpty;
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].imageName = "menu_clear_blue.png";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].menuCommand = "onClearNumbersByColor";
		_menuDef_MoreTools_ClearNumbers_ByColor.items[5].submenu = null;
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ClearNumbers_ByValue = new MenuDef();
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.count = 9;
		_menuInfo_MoreTools_ClearNumbers_ByValue.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ClearNumbers_ByValue.stateUpdateCallback = null;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items = new MenuItemDef[9];
		for(int i = 0; i < _menuInfo_MoreTools_ClearNumbers_ByValue.count; i ++)
			_menuInfo_MoreTools_ClearNumbers_ByValue.items[i] = new MenuItemDef();
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].itemID = 1;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].itemName = "Clear All 1s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].imageName = "menu_clearnumbyval_1.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[0].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].itemID = 2;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].itemName = "Clear All 2s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].imageName = "menu_clearnumbyval_2.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[1].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].itemID = 3;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].itemName = "Clear All 3s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].imageName = "menu_clearnumbyval_3.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[2].submenu = null;
			
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].itemID = 4;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].itemName = "Clear All 4s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].imageName = "menu_clearnumbyval_4.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[3].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].itemID = 5;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].itemName = "Clear All 5s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].imageName = "menu_clearnumbyval_5.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[4].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].itemID = 6;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].itemName = "Clear All 6s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].imageName = "menu_clearnumbyval_6.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[5].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].itemID = 7;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].itemName = "Clear All 7s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].imageName = "menu_clearnumbyval_7.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[6].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].itemID = 8;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].itemName = "Clear All 8s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].imageName = "menu_clearnumbyval_8.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[7].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].itemID = 9;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].itemName = "Clear All 9s";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].imageName = "menu_clearnumbyval_9.png";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].menuCommand = "onClearNumbersByValue";
		_menuInfo_MoreTools_ClearNumbers_ByValue.items[8].submenu = null;
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ClearNumbers = new MenuDef();
		
		_menuInfo_MoreTools_ClearNumbers.count = 3;
		_menuInfo_MoreTools_ClearNumbers.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ClearNumbers.stateUpdateCallback = null;
		_menuInfo_MoreTools_ClearNumbers.items = new MenuItemDef[3];
		for(int i = 0; i < _menuInfo_MoreTools_ClearNumbers.count; i ++)
			_menuInfo_MoreTools_ClearNumbers.items[i] = new MenuItemDef();
		
		_menuInfo_MoreTools_ClearNumbers.items[0].itemID = 0;
		_menuInfo_MoreTools_ClearNumbers.items[0].itemName = "Clear All Numbers";
		_menuInfo_MoreTools_ClearNumbers.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers.items[0].imageName = "menu_clearnumb_all.png";
		_menuInfo_MoreTools_ClearNumbers.items[0].menuCommand = "onClearNumbersAll";
		_menuInfo_MoreTools_ClearNumbers.items[0].submenu = null;
		
		_menuInfo_MoreTools_ClearNumbers.items[1].itemID = 0;
		_menuInfo_MoreTools_ClearNumbers.items[1].itemName = "Clear Numbers By Color";
		_menuInfo_MoreTools_ClearNumbers.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers.items[1].imageName = "menu_clearnumb_color.png";
		_menuInfo_MoreTools_ClearNumbers.items[1].menuCommand = "onClearNumbersAll";
		_menuInfo_MoreTools_ClearNumbers.items[1].submenu = _menuDef_MoreTools_ClearNumbers_ByColor;
		
		_menuInfo_MoreTools_ClearNumbers.items[2].itemID = 0;
		_menuInfo_MoreTools_ClearNumbers.items[2].itemName = "Clear All Numbers";
		_menuInfo_MoreTools_ClearNumbers.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearNumbers.items[2].imageName = "menu_clearnumb_value.png";
		_menuInfo_MoreTools_ClearNumbers.items[2].menuCommand = "onClearNumbersAll";
		_menuInfo_MoreTools_ClearNumbers.items[2].submenu = _menuInfo_MoreTools_ClearNumbers_ByValue;
		
			
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ClearCandidates_ByValue = new MenuDef();
		
		_menuInfo_MoreTools_ClearCandidates_ByValue.count = 9;
		_menuInfo_MoreTools_ClearCandidates_ByValue.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ClearCandidates_ByValue.stateUpdateCallback = null;
		_menuInfo_MoreTools_ClearCandidates_ByValue.items = new MenuItemDef[9];
		
		for( int i = 0; i < 9; i++ )
		{
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i] = new MenuItemDef();
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].itemID = i+1;
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].itemName = String.format("Clear All %ds", i+1);
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].imageType = ImageType.kImageBarEmpty;
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].imageName = String.format("menu_clearcand_%ds.png", i+1);
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].menuCommand = "onClearPossibleByValue";
			_menuInfo_MoreTools_ClearCandidates_ByValue.items[i].submenu = null;
		}
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ClearCandidates = new MenuDef();
		
		_menuInfo_MoreTools_ClearCandidates.count = 2;
		_menuInfo_MoreTools_ClearCandidates.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ClearCandidates.stateUpdateCallback = null;
		_menuInfo_MoreTools_ClearCandidates.items = new MenuItemDef[2];
		for(int i = 0; i < _menuInfo_MoreTools_ClearCandidates.count; i ++)
			_menuInfo_MoreTools_ClearCandidates.items[i] = new MenuItemDef();
		
		_menuInfo_MoreTools_ClearCandidates.items[0].itemID = 0;
		_menuInfo_MoreTools_ClearCandidates.items[0].itemName = "Clear All Candidates";
		_menuInfo_MoreTools_ClearCandidates.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearCandidates.items[0].imageName = "menu_clearcand_all.png";
		_menuInfo_MoreTools_ClearCandidates.items[0].menuCommand = "onClearPossibleAll";
		_menuInfo_MoreTools_ClearCandidates.items[0].submenu = null;
		
		_menuInfo_MoreTools_ClearCandidates.items[1].itemID = 0;
		_menuInfo_MoreTools_ClearCandidates.items[1].itemName = "Clear Candidates By Value";
		_menuInfo_MoreTools_ClearCandidates.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ClearCandidates.items[1].imageName = "menu_clearcand_number.png";
		_menuInfo_MoreTools_ClearCandidates.items[1].menuCommand = null;
		_menuInfo_MoreTools_ClearCandidates.items[1].submenu = _menuInfo_MoreTools_ClearCandidates_ByValue;
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ChangeColors_Colors = new MenuDef();
		
		_menuInfo_MoreTools_ChangeColors_Colors.count = 6;
		_menuInfo_MoreTools_ChangeColors_Colors.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ChangeColors_Colors.stateUpdateCallback = null;
		_menuInfo_MoreTools_ChangeColors_Colors.items = new MenuItemDef[6];
		for(int i = 0; i < _menuInfo_MoreTools_ChangeColors_Colors.count; i ++)
			_menuInfo_MoreTools_ChangeColors_Colors.items[i] = new MenuItemDef();

		_menuInfo_MoreTools_ChangeColors_Colors.items[0].itemID = 1;
		_menuInfo_MoreTools_ChangeColors_Colors.items[0].itemName = "Dark Gray";
		_menuInfo_MoreTools_ChangeColors_Colors.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[0].imageName = "menu_col_grey.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[0].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[0].submenu = null;
		
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].itemID = 2;
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].itemName = "Red";
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].imageName = "menu_col_red.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[1].submenu = null;
		
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].itemID = 3;
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].itemName = "Orange";
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].imageName = "menu_col_orange.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[2].submenu = null;
		
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].itemID = 4;
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].itemName = "Yellow";
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].imageName = "menu_col_yellow.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[3].submenu = null;
		
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].itemID = 5;
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].itemName = "Green";
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].imageName = "menu_col_green.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[4].submenu = null;
			
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].itemID = 6;
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].itemName = "Blue";
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].imageName = "menu_col_blue.png";
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].menuCommand = "onChangeNumberColors";
		_menuInfo_MoreTools_ChangeColors_Colors.items[5].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools_ChangeColors = new MenuDef();
		
		_menuInfo_MoreTools_ChangeColors.count = 9;
		_menuInfo_MoreTools_ChangeColors.updateMenuDefCallback = null; 
		_menuInfo_MoreTools_ChangeColors.stateUpdateCallback = null;
		_menuInfo_MoreTools_ChangeColors.items = new MenuItemDef[9];
		
		for ( int i = 0; i < 9; i++ )
		{
			_menuInfo_MoreTools_ChangeColors.items[i] = new MenuItemDef();
			_menuInfo_MoreTools_ChangeColors.items[i].itemID = i+1;
			_menuInfo_MoreTools_ChangeColors.items[i].itemName = String.format("All Number %ds", i+1);
			_menuInfo_MoreTools_ChangeColors.items[i].imageType = ImageType.kImageBarEmpty;
			_menuInfo_MoreTools_ChangeColors.items[i].imageName = String.format("menu_n%d.png", i+1);
			_menuInfo_MoreTools_ChangeColors.items[i].menuCommand = null;
			_menuInfo_MoreTools_ChangeColors.items[i].submenu = _menuInfo_MoreTools_ChangeColors_Colors;
		}
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_MoreTools = new MenuDef();
		
		_menuInfo_MoreTools.count = 3;
		_menuInfo_MoreTools.updateMenuDefCallback = null; 
		_menuInfo_MoreTools.stateUpdateCallback = null;
		_menuInfo_MoreTools.items = new MenuItemDef[3];
		for(int i = 0; i < _menuInfo_MoreTools.count; i ++)
			_menuInfo_MoreTools.items[i] = new MenuItemDef();
		
		_menuInfo_MoreTools.items[0].itemID = 0;
		_menuInfo_MoreTools.items[0].itemName = "Clear Numbers";
		_menuInfo_MoreTools.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools.items[0].imageName = "menu_mt_clrnumbers.png";
		_menuInfo_MoreTools.items[0].menuCommand = null;
		_menuInfo_MoreTools.items[0].submenu = _menuInfo_MoreTools_ClearNumbers;
		
		
		_menuInfo_MoreTools.items[1].itemID = 0;
		_menuInfo_MoreTools.items[1].itemName = "Clear Candidates";
		_menuInfo_MoreTools.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools.items[1].imageName = "menu_mt_clrcands.png";
		_menuInfo_MoreTools.items[1].menuCommand = null;
		_menuInfo_MoreTools.items[1].submenu = _menuInfo_MoreTools_ClearCandidates;
		
		_menuInfo_MoreTools.items[2].itemID = 0;
		_menuInfo_MoreTools.items[2].itemName = "Change Number Colors";
		_menuInfo_MoreTools.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_MoreTools.items[2].imageName = "menu_mt_clrnumcol.png";
		_menuInfo_MoreTools.items[2].menuCommand = null;
		_menuInfo_MoreTools.items[2].submenu = _menuInfo_MoreTools_ChangeColors;
		
		/////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_NumberStyles = new MenuDef();
		_menuInfo_Options_NumberStyles.count = 5;
		_menuInfo_Options_NumberStyles.updateMenuDefCallback = null; 
		_menuInfo_Options_NumberStyles.stateUpdateCallback = "onNumbersStyleMenuUpdate";
		_menuInfo_Options_NumberStyles.items = new MenuItemDef[5];
		for(int i = 0; i < _menuInfo_Options_NumberStyles.count; i ++)
			_menuInfo_Options_NumberStyles.items[i] = new MenuItemDef();
		
		_menuInfo_Options_NumberStyles.items[0].itemID = 0;
		_menuInfo_Options_NumberStyles.items[0].itemName = "Plain Numbers";
		_menuInfo_Options_NumberStyles.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[0].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[0].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[0].submenu = null;
		
		_menuInfo_Options_NumberStyles.items[1].itemID = 1;
		_menuInfo_Options_NumberStyles.items[1].itemName = "Sketch Numbers";
		_menuInfo_Options_NumberStyles.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[1].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[1].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[1].submenu = null;
		
		_menuInfo_Options_NumberStyles.items[2].itemID = 7;
		_menuInfo_Options_NumberStyles.items[2].itemName = "Alpha";
		_menuInfo_Options_NumberStyles.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[2].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[2].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[2].submenu = null;
		
		_menuInfo_Options_NumberStyles.items[3].itemID = 8;
		_menuInfo_Options_NumberStyles.items[3].itemName = "Roman";
		_menuInfo_Options_NumberStyles.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[3].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[3].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[3].submenu = null;
				
		_menuInfo_Options_NumberStyles.items[4].itemID = 10;
		_menuInfo_Options_NumberStyles.items[4].itemName = "L.C.D.";
		_menuInfo_Options_NumberStyles.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[4].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[4].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[4].submenu = null;
		
		_menuInfo_Options_NumberStyles.items[4].itemID = 11;
		_menuInfo_Options_NumberStyles.items[4].itemName = "Pool";
		_menuInfo_Options_NumberStyles.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_NumberStyles.items[4].imageName = "menu_options_number_stules.png";
		_menuInfo_Options_NumberStyles.items[4].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_NumberStyles.items[4].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_PictureStyles = new MenuDef();
		
		_menuInfo_Options_PictureStyles.count = 5;
		_menuInfo_Options_PictureStyles.updateMenuDefCallback = null; 
		_menuInfo_Options_PictureStyles.stateUpdateCallback = "onNumbersStyleMenuUpdate";
		_menuInfo_Options_PictureStyles.items = new MenuItemDef[5];
		for(int i = 0; i < _menuInfo_Options_PictureStyles.count; i ++)
			_menuInfo_Options_PictureStyles.items[i] = new MenuItemDef();
		
		_menuInfo_Options_PictureStyles.items[0].itemID = 2;
		_menuInfo_Options_PictureStyles.items[0].itemName = "Bugs";
		_menuInfo_Options_PictureStyles.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_PictureStyles.items[0].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options_PictureStyles.items[0].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_PictureStyles.items[0].submenu = null;
		
		_menuInfo_Options_PictureStyles.items[1].itemID = 3;
		_menuInfo_Options_PictureStyles.items[1].itemName = "Flowers";
		_menuInfo_Options_PictureStyles.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_PictureStyles.items[1].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options_PictureStyles.items[1].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_PictureStyles.items[1].submenu = null;
		
		_menuInfo_Options_PictureStyles.items[2].itemID = 4;
		_menuInfo_Options_PictureStyles.items[2].itemName = "Kanji";
		_menuInfo_Options_PictureStyles.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_PictureStyles.items[2].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options_PictureStyles.items[2].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_PictureStyles.items[2].submenu = null;
		
		_menuInfo_Options_PictureStyles.items[3].itemID = 5;
		_menuInfo_Options_PictureStyles.items[3].itemName = "Yang Yang";
		_menuInfo_Options_PictureStyles.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_PictureStyles.items[3].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options_PictureStyles.items[3].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_PictureStyles.items[3].submenu = null;
		
		_menuInfo_Options_PictureStyles.items[4].itemID = 9;
		_menuInfo_Options_PictureStyles.items[4].itemName = "1980s";
		_menuInfo_Options_PictureStyles.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_PictureStyles.items[4].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options_PictureStyles.items[4].menuCommand = "onChangeNumbersStyle";
		_menuInfo_Options_PictureStyles.items[4].submenu = null;
	
		////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_GridStyles = new MenuDef();
		
		_menuInfo_Options_GridStyles.count = 7;
		_menuInfo_Options_GridStyles.updateMenuDefCallback = null; 
		_menuInfo_Options_GridStyles.stateUpdateCallback = "onGridStyleMenuUpdate";
		_menuInfo_Options_GridStyles.items = new MenuItemDef[7];
		for(int i = 0; i < _menuInfo_Options_GridStyles.count; i ++)
			_menuInfo_Options_GridStyles.items[i] = new MenuItemDef();
		
		_menuInfo_Options_GridStyles.items[0].itemID = 0;
		_menuInfo_Options_GridStyles.items[0].itemName = "Alpha";
		_menuInfo_Options_GridStyles.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[0].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[0].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[0].submenu = null;
		
		_menuInfo_Options_GridStyles.items[1].itemID = 1;
		_menuInfo_Options_GridStyles.items[1].itemName = "Mellow Yellow";
		_menuInfo_Options_GridStyles.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[1].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[1].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[1].submenu = null;
		
		_menuInfo_Options_GridStyles.items[2].itemID = 2;
		_menuInfo_Options_GridStyles.items[2].itemName = "Plain";
		_menuInfo_Options_GridStyles.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[2].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[2].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[2].submenu = null;
		
		_menuInfo_Options_GridStyles.items[3].itemID = 4;
		_menuInfo_Options_GridStyles.items[3].itemName = "Symbol";
		_menuInfo_Options_GridStyles.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[3].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[3].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[3].submenu = null;
		
		_menuInfo_Options_GridStyles.items[4].itemID = 3;
		_menuInfo_Options_GridStyles.items[4].itemName = "Shadow";
		_menuInfo_Options_GridStyles.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[4].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[4].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[4].submenu = null;
		
		_menuInfo_Options_GridStyles.items[5].itemID = 7;
		_menuInfo_Options_GridStyles.items[5].itemName = "Pitch";
		_menuInfo_Options_GridStyles.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[5].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[5].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[5].submenu = null;
		
		_menuInfo_Options_GridStyles.items[6].itemID = 8;
		_menuInfo_Options_GridStyles.items[6].itemName = "L.C.D.";
		_menuInfo_Options_GridStyles.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_GridStyles.items[6].imageName = "menu_gridstyle.png";
		_menuInfo_Options_GridStyles.items[6].menuCommand = "onChangeGridStyle";
		_menuInfo_Options_GridStyles.items[6].submenu = null;
		
		///////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_SkinPresets = new MenuDef();
		
		_menuInfo_Options_SkinPresets.count = 4;
		_menuInfo_Options_SkinPresets.updateMenuDefCallback = null; 
		_menuInfo_Options_SkinPresets.stateUpdateCallback = "onNumbersStyleMenuUpdate";
		_menuInfo_Options_SkinPresets.items = new MenuItemDef[4];
		for(int i = 0; i < _menuInfo_Options_SkinPresets.count; i ++)
			_menuInfo_Options_SkinPresets.items[i] = new MenuItemDef();
		
		_menuInfo_Options_SkinPresets.items[0].itemID = 0;
		_menuInfo_Options_SkinPresets.items[0].itemName = "Plain";
		_menuInfo_Options_SkinPresets.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_SkinPresets.items[0].imageName = "menu_options_skin_presets.png";
		_menuInfo_Options_SkinPresets.items[0].menuCommand = "onSkinPresets";
		_menuInfo_Options_SkinPresets.items[0].submenu = null;
		
		_menuInfo_Options_SkinPresets.items[1].itemID = 1;
		_menuInfo_Options_SkinPresets.items[1].itemName = "Alpha";
		_menuInfo_Options_SkinPresets.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_SkinPresets.items[1].imageName = "menu_options_skin_presets.png";
		_menuInfo_Options_SkinPresets.items[1].menuCommand = "onSkinPresets";
		_menuInfo_Options_SkinPresets.items[1].submenu = null;
		
		_menuInfo_Options_SkinPresets.items[2].itemID = 2;
		_menuInfo_Options_SkinPresets.items[2].itemName = "1980s";
		_menuInfo_Options_SkinPresets.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_SkinPresets.items[2].imageName = "menu_options_skin_presets.png";
		_menuInfo_Options_SkinPresets.items[2].menuCommand = "onSkinPresets";
		_menuInfo_Options_SkinPresets.items[2].submenu = null;
		
		_menuInfo_Options_SkinPresets.items[3].itemID = 3;
		_menuInfo_Options_SkinPresets.items[3].itemName = "L.C.D.";
		_menuInfo_Options_SkinPresets.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_SkinPresets.items[3].imageName = "menu_options_skin_presets.png";
		_menuInfo_Options_SkinPresets.items[3].menuCommand = "onSkinPresets";
		_menuInfo_Options_SkinPresets.items[3].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_Symmetry = new MenuDef();
		
		_menuInfo_Options_Symmetry.count = 3;
		_menuInfo_Options_Symmetry.updateMenuDefCallback = null; 
		_menuInfo_Options_Symmetry.stateUpdateCallback = "onSymmetryMenuUpdate";
		_menuInfo_Options_Symmetry.items = new MenuItemDef[3];
		for(int i = 0; i < _menuInfo_Options_Symmetry.count; i ++)
			_menuInfo_Options_Symmetry.items[i] = new MenuItemDef();
		
		_menuInfo_Options_Symmetry.items[0].itemID = 0;
		_menuInfo_Options_Symmetry.items[0].itemName = "Symmetrical";
		_menuInfo_Options_Symmetry.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Symmetry.items[0].imageName = "menu_options_symmetry.png";
		_menuInfo_Options_Symmetry.items[0].menuCommand = "onSymmetryMenu";
		_menuInfo_Options_Symmetry.items[0].submenu = null;
		
		_menuInfo_Options_Symmetry.items[1].itemID = 1;
		_menuInfo_Options_Symmetry.items[1].itemName = "Symmetrical and Assymetrical";
		_menuInfo_Options_Symmetry.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Symmetry.items[1].imageName = "menu_options_symmetry.png";
		_menuInfo_Options_Symmetry.items[1].menuCommand = "onSymmetryMenu";
		_menuInfo_Options_Symmetry.items[1].submenu = null;
		
		_menuInfo_Options_Symmetry.items[2].itemID = 2;
		_menuInfo_Options_Symmetry.items[2].itemName = "Assymetrical";
		_menuInfo_Options_Symmetry.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Symmetry.items[2].imageName = "menu_options_symmetry.png";
		_menuInfo_Options_Symmetry.items[2].menuCommand = "onSymmetryMenu";
		_menuInfo_Options_Symmetry.items[2].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_Sounds = new MenuDef();
		
		_menuInfo_Options_Sounds.count = 7;
		_menuInfo_Options_Sounds.updateMenuDefCallback = null; 
		_menuInfo_Options_Sounds.stateUpdateCallback = "onSoundsMenuUpdate";
		_menuInfo_Options_Sounds.items = new MenuItemDef[7];
		for(int i = 0; i < _menuInfo_Options_Sounds.count; i ++)
			_menuInfo_Options_Sounds.items[i] = new MenuItemDef();
		
		_menuInfo_Options_Sounds.items[0].itemID = 0;
		_menuInfo_Options_Sounds.items[0].itemName = "Sounds On";
		_menuInfo_Options_Sounds.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[0].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[0].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[0].submenu = null;
		
		_menuInfo_Options_Sounds.items[1].itemID = 1;
		_menuInfo_Options_Sounds.items[1].itemName = "Startup";
		_menuInfo_Options_Sounds.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[1].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[1].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[1].submenu = null;
		
		_menuInfo_Options_Sounds.items[2].itemID = 2;
		_menuInfo_Options_Sounds.items[2].itemName = "Close";
		_menuInfo_Options_Sounds.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[2].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[2].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[2].submenu = null;
		
		_menuInfo_Options_Sounds.items[3].itemID = 3;
		_menuInfo_Options_Sounds.items[3].itemName = "Transform";
		_menuInfo_Options_Sounds.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[3].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[3].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[3].submenu = null;
		
		_menuInfo_Options_Sounds.items[4].itemID = 4;
		_menuInfo_Options_Sounds.items[4].itemName = "Click";
		_menuInfo_Options_Sounds.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[4].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[4].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[4].submenu = null;
		
		_menuInfo_Options_Sounds.items[5].itemID = 5;
		_menuInfo_Options_Sounds.items[5].itemName = "Hint Controls";
		_menuInfo_Options_Sounds.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[5].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[5].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[5].submenu = null;
		
		_menuInfo_Options_Sounds.items[6].itemID = 6;
		_menuInfo_Options_Sounds.items[6].itemName = "Eraser (Undo)";
		_menuInfo_Options_Sounds.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Sounds.items[6].imageName = "menu_options_sounds.png";
		_menuInfo_Options_Sounds.items[6].menuCommand = "onSoundsMenu";
		_menuInfo_Options_Sounds.items[6].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_Keypad_options = new MenuDef();
		
		_menuInfo_Options_Keypad_options.count = 2;
		_menuInfo_Options_Keypad_options.updateMenuDefCallback = null; 
		_menuInfo_Options_Keypad_options.stateUpdateCallback = "onKeypadOptionsUpdate";
		_menuInfo_Options_Keypad_options.items = new MenuItemDef[2];
		for(int i = 0; i < _menuInfo_Options_Keypad_options.count; i ++)
			_menuInfo_Options_Keypad_options.items[i] = new MenuItemDef();

		_menuInfo_Options_Keypad_options.items[0].itemID = 0;
		_menuInfo_Options_Keypad_options.items[0].itemName = "Keypad is Draggable";
		_menuInfo_Options_Keypad_options.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Keypad_options.items[0].imageName = "menu_keypad.png";
		_menuInfo_Options_Keypad_options.items[0].menuCommand = "onKeypadOptions";
		_menuInfo_Options_Keypad_options.items[0].submenu = null;
		
		_menuInfo_Options_Keypad_options.items[1].itemID = 1;
		_menuInfo_Options_Keypad_options.items[1].itemName = "Keypad is Sticky";
		_menuInfo_Options_Keypad_options.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Keypad_options.items[1].imageName = "menu_keypad.png";
		_menuInfo_Options_Keypad_options.items[1].menuCommand = "onKeypadOptions";
		_menuInfo_Options_Keypad_options.items[1].submenu = null;
		
//		_menuInfo_Options_Keypad_options.items[2].itemID = 2;
//		_menuInfo_Options_Keypad_options.items[2].itemName = "Autohide Keypad";
//		_menuInfo_Options_Keypad_options.items[2].imageType = ImageType.kImageBarEmpty;
//		_menuInfo_Options_Keypad_options.items[2].imageName = "menu_keypad.png";
//		_menuInfo_Options_Keypad_options.items[2].menuCommand = "onKeypadOptions";
//		_menuInfo_Options_Keypad_options.items[2].submenu = null;
//		
//		_menuInfo_Options_Keypad_options.items[3].itemID = 3;
//		_menuInfo_Options_Keypad_options.items[3].itemName = "OK always hides Keypad";
//		_menuInfo_Options_Keypad_options.items[3].imageType = ImageType.kImageBarEmpty;
//		_menuInfo_Options_Keypad_options.items[3].imageName = "menu_keypad.png";
//		_menuInfo_Options_Keypad_options.items[3].menuCommand = "onKeypadOptions";
//		_menuInfo_Options_Keypad_options.items[3].submenu = null;
		
		////////////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options_Keypad = new MenuDef();
		
		_menuInfo_Options_Keypad.count = 2;
		_menuInfo_Options_Keypad.updateMenuDefCallback = null; 
		_menuInfo_Options_Keypad.stateUpdateCallback = "onKeyPadMenuUpdate";
		_menuInfo_Options_Keypad.items = new MenuItemDef[2];
		for(int i = 0; i < _menuInfo_Options_Keypad.count; i ++)
			_menuInfo_Options_Keypad.items[i] = new MenuItemDef();
		
		_menuInfo_Options_Keypad.items[0].itemID = 0;
		_menuInfo_Options_Keypad.items[0].itemName = "Fixed keypad";
		_menuInfo_Options_Keypad.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Keypad.items[0].imageName = "menu_keypad.png";
		_menuInfo_Options_Keypad.items[0].menuCommand = "onKeyPadMenu";
		_menuInfo_Options_Keypad.items[0].submenu = null;
		
		_menuInfo_Options_Keypad.items[1].itemID = 1;
		_menuInfo_Options_Keypad.items[1].itemName = "Flying keypad";
		_menuInfo_Options_Keypad.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options_Keypad.items[1].imageName = "menu_keypad.png";
		_menuInfo_Options_Keypad.items[1].menuCommand = "onKeyPadMenu";
		_menuInfo_Options_Keypad.items[1].submenu = null;
		
//		_menuInfo_Options_Keypad.items[2].itemID = 2;
//		_menuInfo_Options_Keypad.items[2].itemName = "Flying keypad options";
//		_menuInfo_Options_Keypad.items[2].imageType = ImageType.kImageBarEmpty;
//		_menuInfo_Options_Keypad.items[2].imageName = "menu_keypad.png";
//		_menuInfo_Options_Keypad.items[2].menuCommand = "onKeyPadMenu";
//		_menuInfo_Options_Keypad.items[2].submenu = _menuInfo_Options_Keypad_options;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuInfo_Options = new MenuDef();
		
		_menuInfo_Options.count = 7;
		_menuInfo_Options.updateMenuDefCallback = null; 
//		_menuInfo_Options.stateUpdateCallback = "onKeyPadMenuUpdate";
		_menuInfo_Options.stateUpdateCallback = null;
		_menuInfo_Options.items = new MenuItemDef[7];
		for(int i = 0; i < _menuInfo_Options.count; i ++)
			_menuInfo_Options.items[i] = new MenuItemDef();

		_menuInfo_Options.items[0].itemID = 0;
		_menuInfo_Options.items[0].itemName = "Number Styles";
		_menuInfo_Options.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[0].imageName = "menu_options_number_stules.png";
		_menuInfo_Options.items[0].menuCommand = null;
		_menuInfo_Options.items[0].submenu = _menuInfo_Options_NumberStyles;
		
		_menuInfo_Options.items[1].itemID = 0;
		_menuInfo_Options.items[1].itemName = "Picture Styles";
		_menuInfo_Options.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[1].imageName = "menu_options_picture_stules.png";
		_menuInfo_Options.items[1].menuCommand = null;
		_menuInfo_Options.items[1].submenu = _menuInfo_Options_PictureStyles;
		
		_menuInfo_Options.items[2].itemID = 0;
		_menuInfo_Options.items[2].itemName = "Grid Styles";
		_menuInfo_Options.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[2].imageName = "menu_gridstyle.png";
		_menuInfo_Options.items[2].menuCommand = null;
		_menuInfo_Options.items[2].submenu = _menuInfo_Options_GridStyles;
		
		_menuInfo_Options.items[3].itemID = 0;
		_menuInfo_Options.items[3].itemName = "Skin Presets";
		_menuInfo_Options.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[3].imageName = "menu_options_skin_presets.png";
		_menuInfo_Options.items[3].menuCommand = null;
		_menuInfo_Options.items[3].submenu = _menuInfo_Options_SkinPresets;
		
		_menuInfo_Options.items[4].itemID = 0;
		_menuInfo_Options.items[4].itemName = "Symmetry";
		_menuInfo_Options.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[4].imageName = "menu_options_symmetry.png";
		_menuInfo_Options.items[4].menuCommand = null;
		_menuInfo_Options.items[4].submenu = _menuInfo_Options_Symmetry;
		
		_menuInfo_Options.items[5].itemID = 0;
		_menuInfo_Options.items[5].itemName = "Sounds";
		_menuInfo_Options.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[5].imageName = "menu_options_sounds.png";
		_menuInfo_Options.items[5].menuCommand = null;
		_menuInfo_Options.items[5].submenu = _menuInfo_Options_Sounds;
		
		_menuInfo_Options.items[6].itemID = 0;
		_menuInfo_Options.items[6].itemName = "Keypad Type";
		_menuInfo_Options.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo_Options.items[6].imageName = "menu_keypad.png";
		_menuInfo_Options.items[6].menuCommand = null;
		_menuInfo_Options.items[6].submenu = _menuInfo_Options_Keypad;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuInfo_AdvancedStatistics = new MenuDef();
		
		_menuInfo_AdvancedStatistics.count = 7;
		_menuInfo_AdvancedStatistics.updateMenuDefCallback = null; 
		_menuInfo_AdvancedStatistics.stateUpdateCallback = null;
		_menuInfo_AdvancedStatistics.items = new MenuItemDef[7];
		for(int i = 0; i < _menuInfo_AdvancedStatistics.count; i ++)
			_menuInfo_AdvancedStatistics.items[i] = new MenuItemDef();
		
		_menuInfo_AdvancedStatistics.items[0].itemID = 0;
		_menuInfo_AdvancedStatistics.items[0].itemName = "Simple";
		_menuInfo_AdvancedStatistics.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[0].imageName = "menu_stat_1.png";
		_menuInfo_AdvancedStatistics.items[0].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[0].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[1].itemID = 1;
		_menuInfo_AdvancedStatistics.items[1].itemName = "Easy";
		_menuInfo_AdvancedStatistics.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[1].imageName = "menu_stat_2.png";
		_menuInfo_AdvancedStatistics.items[1].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[1].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[2].itemID = 2;
		_menuInfo_AdvancedStatistics.items[2].itemName = "Medium";
		_menuInfo_AdvancedStatistics.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[2].imageName = "menu_stat_3.png";
		_menuInfo_AdvancedStatistics.items[2].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[2].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[3].itemID = 3;
		_menuInfo_AdvancedStatistics.items[3].itemName = "Hard";
		_menuInfo_AdvancedStatistics.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[3].imageName = "menu_stat_4.png";
		_menuInfo_AdvancedStatistics.items[3].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[3].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[4].itemID = 4;
		_menuInfo_AdvancedStatistics.items[4].itemName = "Very Hard";
		_menuInfo_AdvancedStatistics.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[4].imageName = "menu_stat_5.png";
		_menuInfo_AdvancedStatistics.items[4].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[4].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[5].itemID = 5;
		_menuInfo_AdvancedStatistics.items[5].itemName = "SuDoku Master";
		_menuInfo_AdvancedStatistics.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[5].imageName = "menu_stat_6.png";
		_menuInfo_AdvancedStatistics.items[5].menuCommand = "onShowAdvancedStats";
		_menuInfo_AdvancedStatistics.items[5].submenu = null;
		
		_menuInfo_AdvancedStatistics.items[6].itemID = 0;
		_menuInfo_AdvancedStatistics.items[6].itemName = "Reset Statistics";
		_menuInfo_AdvancedStatistics.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo_AdvancedStatistics.items[6].imageName = "menu_clearstats.png";
		_menuInfo_AdvancedStatistics.items[6].menuCommand = "onResetStats";
		_menuInfo_AdvancedStatistics.items[6].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuInfo_GameCenter = new MenuDef();
		
		_menuInfo_GameCenter.count = 2;
		_menuInfo_GameCenter.updateMenuDefCallback = null; 
		_menuInfo_GameCenter.stateUpdateCallback = null;
		_menuInfo_GameCenter.items = new MenuItemDef[2];
		for(int i = 0; i < _menuInfo_GameCenter.count; i ++)
			_menuInfo_GameCenter.items[i] = new MenuItemDef();
		
		_menuInfo_GameCenter.items[0].itemID = 0;
		_menuInfo_GameCenter.items[0].itemName = "Leaderboard";
		_menuInfo_GameCenter.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo_GameCenter.items[0].imageName = "menu_gamecenter.png";
		_menuInfo_GameCenter.items[0].menuCommand = "onShowLeaderboard";
		_menuInfo_GameCenter.items[0].submenu = null;
		
		_menuInfo_GameCenter.items[1].itemID = 0;
		_menuInfo_GameCenter.items[1].itemName = "Achievements";
		_menuInfo_GameCenter.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo_GameCenter.items[1].imageName = "menu_gamecenter.png";
		_menuInfo_GameCenter.items[1].menuCommand = "onShowLeaderboard";
		_menuInfo_GameCenter.items[1].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////////
		_menuInfo = new MenuDef();
		
		_menuInfo.count = 7;
		_menuInfo.updateMenuDefCallback = null; 
		_menuInfo.stateUpdateCallback = null;
		_menuInfo.items = new MenuItemDef[7];
		for(int i = 0; i < _menuInfo.count; i ++)
			_menuInfo.items[i] = new MenuItemDef();
		
		_menuInfo.items[0].itemID = 0;
		_menuInfo.items[0].itemName = "Create New Sudoku";
		_menuInfo.items[0].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[0].imageName = "menu_tools_create_new.png";
		_menuInfo.items[0].menuCommand = null;
		_menuInfo.items[0].submenu = _menuDef_Tools_NewSudoku;
		
		_menuInfo.items[1].itemID = 0;
		_menuInfo.items[1].itemName = "Enter Own Sudoku";
		_menuInfo.items[1].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[1].imageName = "menu_tools_enter_own.png";
		_menuInfo.items[1].menuCommand = "onEnterOwnSudoku";
		_menuInfo.items[1].submenu = null;
		
		_menuInfo.items[2].itemID = 0;
		_menuInfo.items[2].itemName = "Solve!";
		_menuInfo.items[2].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[2].imageName = "menu_tools_solve.png";
		_menuInfo.items[2].menuCommand = "onSolve";
		_menuInfo.items[2].submenu = null;
		
		_menuInfo.items[3].itemID = 0;
		_menuInfo.items[3].itemName = "More Tools";
		_menuInfo.items[3].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[3].imageName = "menu_tools_more.png";
		_menuInfo.items[3].menuCommand = null;
		_menuInfo.items[3].submenu = _menuInfo_MoreTools;
		
		_menuInfo.items[4].itemID = 0;
		_menuInfo.items[4].itemName = "Options";
		_menuInfo.items[4].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[4].imageName = "menu_tools_options.png";
		_menuInfo.items[4].menuCommand = null;
		_menuInfo.items[4].submenu = _menuInfo_Options;
		
		_menuInfo.items[5].itemID = 0;
		_menuInfo.items[5].itemName = "Advanced Statistics";
		_menuInfo.items[5].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[5].imageName = "menu_tools_adv_stat.png";
		_menuInfo.items[5].menuCommand = null;
		_menuInfo.items[5].submenu = _menuInfo_AdvancedStatistics;
		
		_menuInfo.items[6].itemID = 0;
		_menuInfo.items[6].itemName = "Game Center";
		_menuInfo.items[6].imageType = ImageType.kImageBarEmpty;
		_menuInfo.items[6].imageName = "menu_gamecenter.png";
		_menuInfo.items[6].menuCommand = null;
		_menuInfo.items[6].submenu = _menuInfo_GameCenter;
		
		//////////////////////////////////////////////////////////////////////////////
		_menuHelp = new MenuDef();
		
		_menuHelp.count = 5;
		_menuHelp.updateMenuDefCallback = null; 
		_menuHelp.stateUpdateCallback = null;
		_menuHelp.items = new MenuItemDef[5];
		for(int i = 0; i < _menuHelp.count; i ++)
			_menuHelp.items[i] = new MenuItemDef();
		
		_menuHelp.items[0].itemID = 0;
		_menuHelp.items[0].itemName = "About";
		_menuHelp.items[0].imageType = ImageType.kImageBarEmpty;
		_menuHelp.items[0].imageName = "menu_help_about.png";
		_menuHelp.items[0].menuCommand = "onHelpAbout";
		_menuHelp.items[0].submenu = null;
		
		_menuHelp.items[1].itemID = 0;
		_menuHelp.items[1].itemName = "Credits";
		_menuHelp.items[1].imageType = ImageType.kImageBarEmpty;
		_menuHelp.items[1].imageName = "menu_help_credits.png";
		_menuHelp.items[1].menuCommand = "onHelpCredits";
		_menuHelp.items[1].submenu = null;
		
		_menuHelp.items[2].itemID = 0;
		_menuHelp.items[2].itemName = "Help";
		_menuHelp.items[2].imageType = ImageType.kImageBarEmpty;
		_menuHelp.items[2].imageName = "menu_help_help.png";
		_menuHelp.items[2].menuCommand = "onHelpHelp";
		_menuHelp.items[2].submenu = null;
		
		_menuHelp.items[3].itemID = 0;
		_menuHelp.items[3].itemName = "Other Titles";
		_menuHelp.items[3].imageType = ImageType.kImageBarEmpty;
		_menuHelp.items[3].imageName = "menu_help_other.png";
		_menuHelp.items[3].menuCommand = "onHelpOtherTitles";
		_menuHelp.items[3].submenu = null;
		
		_menuHelp.items[4].itemID = 0;
		_menuHelp.items[4].itemName = "Report a Bug";
		_menuHelp.items[4].imageType = ImageType.kImageBarEmpty;
		_menuHelp.items[4].imageName = "info.png";
		_menuHelp.items[4].menuCommand = "onHelpReportBug";
		_menuHelp.items[4].submenu = null;
		///////////////////////////////////////////////////////////////////////////////////
		_menuWand_NakedSubset = new MenuDef();
		
		_menuWand_NakedSubset.count = 4;
		_menuWand_NakedSubset.updateMenuDefCallback = null; 
		_menuWand_NakedSubset.stateUpdateCallback = null;
		_menuWand_NakedSubset.items = new MenuItemDef[4];
		for(int i = 0; i < _menuWand_NakedSubset.count; i ++)
			_menuWand_NakedSubset.items[i] = new MenuItemDef();
		
		_menuWand_NakedSubset.items[0].itemID = 0;
		_menuWand_NakedSubset.items[0].itemName = "Highlight Singles";
		_menuWand_NakedSubset.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_NakedSubset.items[0].imageName = "menu_naked_1.png";
		_menuWand_NakedSubset.items[0].menuCommand = "onShowNakedSubset";
		_menuWand_NakedSubset.items[0].submenu = null;
		
		_menuWand_NakedSubset.items[1].itemID = 1;
		_menuWand_NakedSubset.items[1].itemName = "Highlight Twins";
		_menuWand_NakedSubset.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_NakedSubset.items[1].imageName = "menu_naked_2.png";
		_menuWand_NakedSubset.items[1].menuCommand = "onShowNakedSubset";
		_menuWand_NakedSubset.items[1].submenu = null;
		
		_menuWand_NakedSubset.items[2].itemID = 2;
		_menuWand_NakedSubset.items[2].itemName = "Highlight Triplets";
		_menuWand_NakedSubset.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWand_NakedSubset.items[2].imageName = "menu_naked_3.png";
		_menuWand_NakedSubset.items[2].menuCommand = "onShowNakedSubset";
		_menuWand_NakedSubset.items[2].submenu = null;
		
		_menuWand_NakedSubset.items[3].itemID = 3;
		_menuWand_NakedSubset.items[3].itemName = "Highlight Quadruplets";
		_menuWand_NakedSubset.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWand_NakedSubset.items[3].imageName = "menu_naked_4.png";
		_menuWand_NakedSubset.items[3].menuCommand = "onShowNakedSubset";
		_menuWand_NakedSubset.items[3].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////
		_menuWand_HiddenSubset_Items = new MenuDef();
		
		_menuWand_HiddenSubset_Items.count = 4;
		_menuWand_HiddenSubset_Items.updateMenuDefCallback = null; 
		_menuWand_HiddenSubset_Items.stateUpdateCallback = null;
		_menuWand_HiddenSubset_Items.items = new MenuItemDef[4];
		for(int i = 0; i < _menuWand_HiddenSubset_Items.count; i ++)
			_menuWand_HiddenSubset_Items.items[i] = new MenuItemDef();
		
		_menuWand_HiddenSubset_Items.items[0].itemID = 0;
		_menuWand_HiddenSubset_Items.items[0].itemName = "Highlight Hidden Singles";
		_menuWand_HiddenSubset_Items.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_Items.items[0].imageName = "menu_hidden_1.png";
		_menuWand_HiddenSubset_Items.items[0].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_Items.items[0].submenu = null;
		
		_menuWand_HiddenSubset_Items.items[1].itemID = 1;
		_menuWand_HiddenSubset_Items.items[1].itemName = "Highlight Hidden Twins";
		_menuWand_HiddenSubset_Items.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_Items.items[1].imageName = "menu_hidden_2.png";
		_menuWand_HiddenSubset_Items.items[1].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_Items.items[1].submenu = null;
		
		_menuWand_HiddenSubset_Items.items[2].itemID = 2;
		_menuWand_HiddenSubset_Items.items[2].itemName = "Highlight Hidden Triplets";
		_menuWand_HiddenSubset_Items.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_Items.items[2].imageName = "menu_hidden_3.png";
		_menuWand_HiddenSubset_Items.items[2].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_Items.items[2].submenu = null;
		
		_menuWand_HiddenSubset_Items.items[3].itemID = 3;
		_menuWand_HiddenSubset_Items.items[3].itemName = "Highlight Hidden Quadruplets";
		_menuWand_HiddenSubset_Items.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_Items.items[3].imageName = "menu_hidden_4.png";
		_menuWand_HiddenSubset_Items.items[3].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_Items.items[3].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////////
		_menuWand_HiddenSubset_DirectItems = new MenuDef();
		
		_menuWand_HiddenSubset_DirectItems.count = 3;
		_menuWand_HiddenSubset_DirectItems.updateMenuDefCallback = null; 
		_menuWand_HiddenSubset_DirectItems.stateUpdateCallback = null;
		_menuWand_HiddenSubset_DirectItems.items = new MenuItemDef[3];
		for(int i = 0; i < _menuWand_HiddenSubset_DirectItems.count; i ++)
			_menuWand_HiddenSubset_DirectItems.items[i] = new MenuItemDef();
		
		_menuWand_HiddenSubset_DirectItems.items[0].itemID = 1;
		_menuWand_HiddenSubset_DirectItems.items[0].itemName = "Direct Hidden Twins";
		_menuWand_HiddenSubset_DirectItems.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_DirectItems.items[0].imageName = "menu_wand_hidden.png";
		_menuWand_HiddenSubset_DirectItems.items[0].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_DirectItems.items[0].submenu = null;
		
		_menuWand_HiddenSubset_DirectItems.items[1].itemID = 2;
		_menuWand_HiddenSubset_DirectItems.items[1].itemName = "Direct Hidden Triplets";
		_menuWand_HiddenSubset_DirectItems.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_DirectItems.items[1].imageName = "menu_wand_hidden.png";
		_menuWand_HiddenSubset_DirectItems.items[1].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_DirectItems.items[1].submenu = null;
		
		_menuWand_HiddenSubset_DirectItems.items[2].itemID = 3;
		_menuWand_HiddenSubset_DirectItems.items[2].itemName = "Direct Hidden Quadruplets";
		_menuWand_HiddenSubset_DirectItems.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset_DirectItems.items[2].imageName = "menu_wand_hidden.png";
		_menuWand_HiddenSubset_DirectItems.items[2].menuCommand = "onShowHiddenSubset";
		_menuWand_HiddenSubset_DirectItems.items[2].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuWand_HiddenSubset = new MenuDef();
		
		_menuWand_HiddenSubset.count = 2;
		_menuWand_HiddenSubset.updateMenuDefCallback = null; 
		_menuWand_HiddenSubset.stateUpdateCallback = null;
		_menuWand_HiddenSubset.items = new MenuItemDef[2];
		for(int i = 0; i < _menuWand_HiddenSubset.count; i ++)
			_menuWand_HiddenSubset.items[i] = new MenuItemDef();
		
		_menuWand_HiddenSubset.items[0].itemID = 0;
		_menuWand_HiddenSubset.items[0].itemName = "Hidden Subset";
		_menuWand_HiddenSubset.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset.items[0].imageName = "menu_wand_hidden.png";
		_menuWand_HiddenSubset.items[0].menuCommand = null;
		_menuWand_HiddenSubset.items[0].submenu = _menuWand_HiddenSubset_Items;
		
		_menuWand_HiddenSubset.items[1].itemID = 1;
		_menuWand_HiddenSubset.items[1].itemName = "Direct Hidden Subset";
		_menuWand_HiddenSubset.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_HiddenSubset.items[1].imageName = "menu_wand_hidden.png";
		_menuWand_HiddenSubset.items[1].menuCommand = null;
		_menuWand_HiddenSubset.items[1].submenu = _menuWand_HiddenSubset_DirectItems;
		
		///////////////////////////////////////////////////////////////////////
		_menuWand_Fisherman = new MenuDef();
		
		_menuWand_Fisherman.count = 3;
		_menuWand_Fisherman.updateMenuDefCallback = null; 
		_menuWand_Fisherman.stateUpdateCallback = null;
		_menuWand_Fisherman.items = new MenuItemDef[3];
		for(int i = 0; i < _menuWand_Fisherman.count; i ++)
			_menuWand_Fisherman.items[i] = new MenuItemDef();
		
		_menuWand_Fisherman.items[0].itemID = 2;
		_menuWand_Fisherman.items[0].itemName = "Highlight X-Wings";
		_menuWand_Fisherman.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_Fisherman.items[0].imageName = "menu_wand_xwings.png";
		_menuWand_Fisherman.items[0].menuCommand = "onFishermanSubset";
		_menuWand_Fisherman.items[0].submenu = null;
		
		_menuWand_Fisherman.items[1].itemID = 3;
		_menuWand_Fisherman.items[1].itemName = "Highlight Swordfish";
		_menuWand_Fisherman.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_Fisherman.items[1].imageName = "menu_wand_swordfish.png";
		_menuWand_Fisherman.items[1].menuCommand = "onFishermanSubset";
		_menuWand_Fisherman.items[1].submenu = null;
		
		_menuWand_Fisherman.items[2].itemID = 4;
		_menuWand_Fisherman.items[2].itemName = "Highlight Jellyfish";
		_menuWand_Fisherman.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWand_Fisherman.items[2].imageName = "menu_wand_jellyfish.png";
		_menuWand_Fisherman.items[2].menuCommand = "onFishermanSubset";
		_menuWand_Fisherman.items[2].submenu = null;
			
		//////////////////////////////////////////////////////////////////
		_menuWand_Intersections = new MenuDef();
		
		_menuWand_Intersections.count = 2;
		_menuWand_Intersections.updateMenuDefCallback = null; 
		_menuWand_Intersections.stateUpdateCallback = null;
		_menuWand_Intersections.items = new MenuItemDef[2];
		for(int i = 0; i < _menuWand_Intersections.count; i ++)
			_menuWand_Intersections.items[i] = new MenuItemDef();
		
		
		_menuWand_Intersections.items[0].itemID = 0;
		_menuWand_Intersections.items[0].itemName = "Intersections";
		_menuWand_Intersections.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_Intersections.items[0].imageName = "menu_inter24.png";
		_menuWand_Intersections.items[0].menuCommand = "onIntersectionsSubset";
		_menuWand_Intersections.items[0].submenu = null;
		
		_menuWand_Intersections.items[1].itemID = 1;
		_menuWand_Intersections.items[1].itemName = "Direct Intersections";
		_menuWand_Intersections.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_Intersections.items[1].imageName = "menu_inter24.png";
		_menuWand_Intersections.items[1].menuCommand = "onIntersectionsSubset";
		_menuWand_Intersections.items[1].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////////
		_menuWand_Suggest_Value_Cell = new MenuDef();
		
		_menuWand_Suggest_Value_Cell.count = 2;
		_menuWand_Suggest_Value_Cell.updateMenuDefCallback = null; 
		_menuWand_Suggest_Value_Cell.stateUpdateCallback = null;
		_menuWand_Suggest_Value_Cell.items = new MenuItemDef[2];
		for(int i = 0; i < _menuWand_Suggest_Value_Cell.count; i ++)
			_menuWand_Suggest_Value_Cell.items[i] = new MenuItemDef();
		
		_menuWand_Suggest_Value_Cell.items[0].itemID = 0;
		_menuWand_Suggest_Value_Cell.items[0].itemName = "Suggest a Cell";
		_menuWand_Suggest_Value_Cell.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_Suggest_Value_Cell.items[0].imageName = "menu_wand_suggest_cell.png";
		_menuWand_Suggest_Value_Cell.items[0].menuCommand = "onSuggestCell";
		_menuWand_Suggest_Value_Cell.items[0].submenu = null;
		
		_menuWand_Suggest_Value_Cell.items[1].itemID = 1;
		_menuWand_Suggest_Value_Cell.items[1].itemName = "Suggest a Value";
		_menuWand_Suggest_Value_Cell.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_Suggest_Value_Cell.items[1].imageName = "menu_wand_suggest_value.png";
		_menuWand_Suggest_Value_Cell.items[1].menuCommand = "onSuggestValue";
		_menuWand_Suggest_Value_Cell.items[1].submenu = null;
		
		/////////////////////////////////////////////////////////////////////////////////
		_menuWand_Wings = new MenuDef();
		
		_menuWand_Wings.count = 2;
		_menuWand_Wings.updateMenuDefCallback = null; 
		_menuWand_Wings.stateUpdateCallback = null;
		_menuWand_Wings.items = new MenuItemDef[2];
		for(int i = 0; i < _menuWand_Wings.count; i ++)
			_menuWand_Wings.items[i] = new MenuItemDef();
		
		_menuWand_Wings.items[0].itemID = 0;
		_menuWand_Wings.items[0].itemName = "XY Wing Subset";
		_menuWand_Wings.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand_Wings.items[0].imageName = "menu_xyz124.png";
		_menuWand_Wings.items[0].menuCommand = "onXYWingsSubset";
		_menuWand_Wings.items[0].submenu = null;
		
		_menuWand_Wings.items[1].itemID = 1;
		_menuWand_Wings.items[1].itemName = "XYZ Wing Subset";
		_menuWand_Wings.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand_Wings.items[1].imageName = "menu_xyz124.png";
		_menuWand_Wings.items[1].menuCommand = "onXYWingsSubset";
		_menuWand_Wings.items[1].submenu = null;
		
		///////////////////////////////////////////////////////////////////////
		_menuWand_AlignedExclusion = new MenuDef();
		
		_menuWand_AlignedExclusion.count = 3;
		_menuWand_AlignedExclusion.updateMenuDefCallback = null; 
		_menuWand_AlignedExclusion.stateUpdateCallback = null;
		_menuWand_AlignedExclusion.items = new MenuItemDef[3];
		for(int i = 0; i < _menuWand_AlignedExclusion.count; i ++)
			_menuWand_AlignedExclusion.items[i] = new MenuItemDef();

		_menuWand_AlignedExclusion.items[0].itemID = 0;
		_menuWand_AlignedExclusion.items[0].itemName = "Aligned Pair Exclusion";
		_menuWand_AlignedExclusion.items[0].imageType = ImageType.kImageBtnEmpty;
		_menuWand_AlignedExclusion.items[0].imageName = null;
		_menuWand_AlignedExclusion.items[0].menuCommand = null;
		_menuWand_AlignedExclusion.items[0].submenu = null;
		
		_menuWand_AlignedExclusion.items[1].itemID = 0;
		_menuWand_AlignedExclusion.items[1].itemName = "Aligned Triplet Exclusion";
		_menuWand_AlignedExclusion.items[1].imageType = ImageType.kImageBtnEmpty;
		_menuWand_AlignedExclusion.items[1].imageName = null;
		_menuWand_AlignedExclusion.items[1].menuCommand = null;
		_menuWand_AlignedExclusion.items[1].submenu = null;
		
		_menuWand_AlignedExclusion.items[2].itemID = 0;
		_menuWand_AlignedExclusion.items[2].itemName = "Aligned Quad Exclusion";
		_menuWand_AlignedExclusion.items[2].imageType = ImageType.kImageBtnEmpty;
		_menuWand_AlignedExclusion.items[2].imageName = null;
		_menuWand_AlignedExclusion.items[2].menuCommand = null;
		_menuWand_AlignedExclusion.items[2].submenu = null;
		
		///////////////////////////////////////////////////////////////////////
		_menuWand = new MenuDef();
		
		_menuWand.count = 7;
		_menuWand.updateMenuDefCallback = null; 
		_menuWand.stateUpdateCallback = null;
		_menuWand.items = new MenuItemDef[7];
		for(int i = 0; i < _menuWand.count; i ++)
			_menuWand.items[i] = new MenuItemDef();
		
		_menuWand.items[0].itemID = 0;
		_menuWand.items[0].itemName = "Wrong Values";
		_menuWand.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[0].imageName = "menu_wand_wrong.png";
		_menuWand.items[0].menuCommand = "onWrongValues";
		_menuWand.items[0].submenu = null;
		
		_menuWand.items[1].itemID = 0;
		_menuWand.items[1].itemName = "Suggest a Cell/Value";
		_menuWand.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[1].imageName = "menu_wand_suggest_cell.png";
		_menuWand.items[1].menuCommand = "onWrongValues";
		_menuWand.items[1].submenu = _menuWand_Suggest_Value_Cell;
		
		_menuWand.items[2].itemID = 0;
		_menuWand.items[2].itemName = "Naked Subset";
		_menuWand.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[2].imageName = "menu_wand_naked.png";
		_menuWand.items[2].menuCommand = null;
		_menuWand.items[2].submenu = _menuWand_NakedSubset;
				
		_menuWand.items[3].itemID = 0;
		_menuWand.items[3].itemName = "Hidden Subset";
		_menuWand.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[3].imageName = "menu_wand_hidden.png";
		_menuWand.items[3].menuCommand = null;
		_menuWand.items[3].submenu = _menuWand_HiddenSubset;
			
		_menuWand.items[4].itemID = 0;
		_menuWand.items[4].itemName = "Fisherman Subset";
		_menuWand.items[4].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[4].imageName = "menu_fisherman24.png";
		_menuWand.items[4].menuCommand = null;
		_menuWand.items[4].submenu = _menuWand_Fisherman;
				
		_menuWand.items[5].itemID = 0;
		_menuWand.items[5].itemName = "Intersections Subset";
		_menuWand.items[5].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[5].imageName = "menu_inter32.png";
		_menuWand.items[5].menuCommand = null;
		_menuWand.items[5].submenu = _menuWand_Intersections;
		
		_menuWand.items[6].itemID = 0;
		_menuWand.items[6].itemName = "XY/Z Wings Subset";
		_menuWand.items[6].imageType = ImageType.kImageBarEmpty;
		_menuWand.items[6].imageName = "menu_xyz124.png";
		_menuWand.items[6].menuCommand = null;
		_menuWand.items[6].submenu = _menuWand_Wings;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuWisard_PossibleSells = new MenuDef();
		
		_menuWisard_PossibleSells.count = 9;
		_menuWisard_PossibleSells.updateMenuDefCallback = null; 
		_menuWisard_PossibleSells.stateUpdateCallback = null;
		_menuWisard_PossibleSells.items = new MenuItemDef[9];
		for(int i = 0; i < _menuWisard_PossibleSells.count; i ++)
			_menuWisard_PossibleSells.items[i] = new MenuItemDef();
		
		_menuWisard_PossibleSells.items[0].itemID = 1;
		_menuWisard_PossibleSells.items[0].itemName = "1 / Red";
		_menuWisard_PossibleSells.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[0].imageName = "menu_possible_cell_1.png";
		_menuWisard_PossibleSells.items[0].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[0].submenu = null;
		
		_menuWisard_PossibleSells.items[1].itemID = 2;
		_menuWisard_PossibleSells.items[1].itemName = "2 / Orange";
		_menuWisard_PossibleSells.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[1].imageName = "menu_possible_cell_2.png";
		_menuWisard_PossibleSells.items[1].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[1].submenu = null;

		_menuWisard_PossibleSells.items[2].itemID = 3;
		_menuWisard_PossibleSells.items[2].itemName = "3 / Yellow";
		_menuWisard_PossibleSells.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[2].imageName = "menu_possible_cell_3.png";
		_menuWisard_PossibleSells.items[2].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[2].submenu = null;

		_menuWisard_PossibleSells.items[3].itemID = 4;
		_menuWisard_PossibleSells.items[3].itemName = "4 / Green";
		_menuWisard_PossibleSells.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[3].imageName = "menu_possible_cell_4.png";
		_menuWisard_PossibleSells.items[3].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[3].submenu = null;

		_menuWisard_PossibleSells.items[4].itemID = 5;
		_menuWisard_PossibleSells.items[4].itemName = "5 / Blue";
		_menuWisard_PossibleSells.items[4].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[4].imageName = "menu_possible_cell_5.png";
		_menuWisard_PossibleSells.items[4].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[4].submenu = null;

		_menuWisard_PossibleSells.items[5].itemID = 6;
		_menuWisard_PossibleSells.items[5].itemName = "6 / Indigo";
		_menuWisard_PossibleSells.items[5].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[5].imageName = "menu_possible_cell_6.png";
		_menuWisard_PossibleSells.items[5].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[5].submenu = null;

		_menuWisard_PossibleSells.items[6].itemID = 7;
		_menuWisard_PossibleSells.items[6].itemName = "7 / Violet";
		_menuWisard_PossibleSells.items[6].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[6].imageName = "menu_possible_cell_7.png";
		_menuWisard_PossibleSells.items[6].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[6].submenu = null;

		_menuWisard_PossibleSells.items[7].itemID = 8;
		_menuWisard_PossibleSells.items[7].itemName = "8 / Black";
		_menuWisard_PossibleSells.items[7].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[7].imageName = "menu_possible_cell_8.png";
		_menuWisard_PossibleSells.items[7].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[7].submenu = null;

		_menuWisard_PossibleSells.items[8].itemID = 9;
		_menuWisard_PossibleSells.items[8].itemName = "9 / White";
		_menuWisard_PossibleSells.items[8].imageType = ImageType.kImageBarEmpty;
		_menuWisard_PossibleSells.items[8].imageName = "menu_possible_cell_9.png";
		_menuWisard_PossibleSells.items[8].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_PossibleSells.items[8].submenu = null;

		//////////////////////////////////////////////////////////////////////////////////
		_menuWisard_ImpossibleSells = new MenuDef();
		
		_menuWisard_ImpossibleSells.count = 9;
		_menuWisard_ImpossibleSells.updateMenuDefCallback = null; 
		_menuWisard_ImpossibleSells.stateUpdateCallback = null;
		_menuWisard_ImpossibleSells.items = new MenuItemDef[9];
		for(int i = 0; i < _menuWisard_ImpossibleSells.count; i ++)
			_menuWisard_ImpossibleSells.items[i] = new MenuItemDef();
		
		_menuWisard_ImpossibleSells.items[0].itemID = 1;
		_menuWisard_ImpossibleSells.items[0].itemName = "1 / Red";
		_menuWisard_ImpossibleSells.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[0].imageName = "menu_impossible_cell_1.png";
		_menuWisard_ImpossibleSells.items[0].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[0].submenu = null;
		
		_menuWisard_ImpossibleSells.items[1].itemID = 2;
		_menuWisard_ImpossibleSells.items[1].itemName = "2 / Orange";
		_menuWisard_ImpossibleSells.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[1].imageName = "menu_impossible_cell_2.png";
		_menuWisard_ImpossibleSells.items[1].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[1].submenu = null;

		_menuWisard_ImpossibleSells.items[2].itemID = 3;
		_menuWisard_ImpossibleSells.items[2].itemName = "3 / Yellow";
		_menuWisard_ImpossibleSells.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[2].imageName = "menu_impossible_cell_3.png";
		_menuWisard_ImpossibleSells.items[2].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[2].submenu = null;

		_menuWisard_ImpossibleSells.items[3].itemID = 4;
		_menuWisard_ImpossibleSells.items[3].itemName = "4 / Green";
		_menuWisard_ImpossibleSells.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[3].imageName = "menu_impossible_cell_4.png";
		_menuWisard_ImpossibleSells.items[3].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[3].submenu = null;

		_menuWisard_ImpossibleSells.items[4].itemID = 5;
		_menuWisard_ImpossibleSells.items[4].itemName = "5 / Blue";
		_menuWisard_ImpossibleSells.items[4].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[4].imageName = "menu_impossible_cell_5.png";
		_menuWisard_ImpossibleSells.items[4].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[4].submenu = null;

		_menuWisard_ImpossibleSells.items[5].itemID = 6;
		_menuWisard_ImpossibleSells.items[5].itemName = "6 / Indigo";
		_menuWisard_ImpossibleSells.items[5].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[5].imageName = "menu_impossible_cell_6.png";
		_menuWisard_ImpossibleSells.items[5].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[5].submenu = null;

		_menuWisard_ImpossibleSells.items[6].itemID = 7;
		_menuWisard_ImpossibleSells.items[6].itemName = "7 / Violet";
		_menuWisard_ImpossibleSells.items[6].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[6].imageName = "menu_impossible_cell_7.png";
		_menuWisard_ImpossibleSells.items[6].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[6].submenu = null;

		_menuWisard_ImpossibleSells.items[7].itemID = 8;
		_menuWisard_ImpossibleSells.items[7].itemName = "8 / Black";
		_menuWisard_ImpossibleSells.items[7].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[7].imageName = "menu_impossible_cell_8.png";
		_menuWisard_ImpossibleSells.items[7].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[7].submenu = null;

		_menuWisard_ImpossibleSells.items[8].itemID = 9;
		_menuWisard_ImpossibleSells.items[8].itemName = "9 / White";
		_menuWisard_ImpossibleSells.items[8].imageType = ImageType.kImageBarEmpty;
		_menuWisard_ImpossibleSells.items[8].imageName = "menu_impossible_cell_9.png";
		_menuWisard_ImpossibleSells.items[8].menuCommand = "onPossibleImpossibleCell";
		_menuWisard_ImpossibleSells.items[8].submenu = null;

		//////////////////////////////////////////////////////////////////////////////////
		_menuWisard = new MenuDef();
		
		_menuWisard.count = 9;
		_menuWisard.updateMenuDefCallback = null; 
		_menuWisard.stateUpdateCallback = "onWisardMenuUpdate";
		_menuWisard.items = new MenuItemDef[9];
		for(int i = 0; i < _menuWisard.count; i ++)
			_menuWisard.items[i] = new MenuItemDef();
		
		_menuWisard.items[0].itemID = 0;
		_menuWisard.items[0].itemName = "Possible Values (10%)";
		_menuWisard.items[0].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[0].imageName = "menu_wisard_possible_values.png";
		_menuWisard.items[0].menuCommand = "onWisardPossibleValues";
		_menuWisard.items[0].submenu = null;

		_menuWisard.items[1].itemID = 1;
		_menuWisard.items[1].itemName = "Possible Cells (10%)";
		_menuWisard.items[1].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[1].imageName = "menu_wisard_possible_cells.png";
		_menuWisard.items[1].menuCommand = null;
		_menuWisard.items[1].submenu =  _menuWisard_PossibleSells;

		_menuWisard.items[2].itemID = 2;
		_menuWisard.items[2].itemName = "Impossible Cells (10%)";
		_menuWisard.items[2].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[2].imageName = "menu_wisard_impossible_cells.png";
		_menuWisard.items[2].menuCommand = null;
		_menuWisard.items[2].submenu = _menuWisard_ImpossibleSells;

		_menuWisard.items[3].itemID = 3;
		_menuWisard.items[3].itemName = "Duplicate Box/Row/Col (10%)";
		_menuWisard.items[3].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[3].imageName = "menu_wisard_duplicates.png";
		_menuWisard.items[3].menuCommand = "onWisardDuplicatesBoxRowCol";
		_menuWisard.items[3].submenu = null;

		_menuWisard.items[4].itemID = 4;
		_menuWisard.items[4].itemName = "Duplicate Values (10%)";
		_menuWisard.items[4].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[4].imageName = "menu_wisard_duplicate_values.png";
		_menuWisard.items[4].menuCommand = "onWisardDuplicateValues";
		_menuWisard.items[4].submenu = null;

		_menuWisard.items[5].itemID = 5;
		_menuWisard.items[5].itemName = "Show Candidates (10%)";
		_menuWisard.items[5].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[5].imageName = "menu_wisard_show_candidates.png";
		_menuWisard.items[5].menuCommand = "onWisardShowCandidates";
		_menuWisard.items[5].submenu = null;

		_menuWisard.items[6].itemID = 6;
		_menuWisard.items[6].itemName = "Calculate Candidates (25%)";
		_menuWisard.items[6].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[6].imageName = "menu_wisard_calculate_candidates.png";
		_menuWisard.items[6].menuCommand = "onWisardCalculateCandidates";
		_menuWisard.items[6].submenu = null;

		_menuWisard.items[7].itemID = 7;
		_menuWisard.items[7].itemName = "Auto Candidates (75%)";
		_menuWisard.items[7].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[7].imageName = "menu_wisard_auto_candidates.png";
		_menuWisard.items[7].menuCommand = "onWisardAutoCandidates";
		_menuWisard.items[7].submenu = null;

		_menuWisard.items[8].itemID = 8;
		_menuWisard.items[8].itemName = "Suggest a Technique (35%)";
		_menuWisard.items[8].imageType = ImageType.kImageBarEmpty;
		_menuWisard.items[8].imageName = "menu_wisard_suggest_tech.png";
		_menuWisard.items[8].menuCommand = "onWisardSuggestTech";
		_menuWisard.items[8].submenu = null;

		//////////////////////////////////////////////////////////////////////////////////
		_menuTransform = new MenuDef();
		
		_menuTransform.count = 4;
		_menuTransform.updateMenuDefCallback = null; 
		_menuTransform.stateUpdateCallback = null;
		_menuTransform.items = new MenuItemDef[4];
		for(int i = 0; i < _menuTransform.count; i ++)
			_menuTransform.items[i] = new MenuItemDef();
		
		_menuTransform.items[0].itemID = 0;
		_menuTransform.items[0].itemName = "Rotate Clockwise";
		_menuTransform.items[0].imageType = ImageType.kImageBarEmpty;
		_menuTransform.items[0].imageName = "icon_transform.png";;
		_menuTransform.items[0].menuCommand = "onTransformRotateClockWise";
		_menuTransform.items[0].submenu = null;

		_menuTransform.items[1].itemID = 1;
		_menuTransform.items[1].itemName = "Rotate Anti Clockwise";
		_menuTransform.items[1].imageType = ImageType.kImageBarEmpty;
		_menuTransform.items[1].imageName = "icon_transform_anticlockwise.png";
		_menuTransform.items[1].menuCommand = "onTransformRotateAntiClockWise";
		_menuTransform.items[1].submenu = null;

		_menuTransform.items[2].itemID = 2;
		_menuTransform.items[2].itemName = "Flip";
		_menuTransform.items[2].imageType = ImageType.kImageBarEmpty;
		_menuTransform.items[2].imageName = "icon_transform_vert.png";
		_menuTransform.items[2].menuCommand = "onTransformMirrorVertical";
		_menuTransform.items[2].submenu = null;

		_menuTransform.items[3].itemID = 3;
		_menuTransform.items[3].itemName = "Mirror";
		_menuTransform.items[3].imageType = ImageType.kImageBarEmpty;
		_menuTransform.items[3].imageName = "icon_transform_hor.png";
		_menuTransform.items[3].menuCommand = "onTransformMirrorHorisontal";
		_menuTransform.items[3].submenu = null;
		
		//////////////////////////////////////////////////////////////////////////////////
		_menuFlag = new MenuDef();
		
		_menuFlag.count = 4;
		_menuFlag.updateMenuDefCallback = null; 
		_menuFlag.stateUpdateCallback = null;
		_menuFlag.items = new MenuItemDef[4];
		for(int i = 0; i < _menuFlag.count; i ++)
			_menuFlag.items[i] = new MenuItemDef();
		
		_menuFlag.items[0].itemID = 0;
		_menuFlag.items[0].itemName = "Mark Red";
		_menuFlag.items[0].imageType = ImageType.kImageBarEmpty;
		_menuFlag.items[0].imageName = "menu_flag_red.png";
		_menuFlag.items[0].menuCommand = "onMarkFlagRed";
		_menuFlag.items[0].submenu = null;
		
		_menuFlag.items[1].itemID = 1;
		_menuFlag.items[1].itemName = "Mark Green";
		_menuFlag.items[1].imageType = ImageType.kImageBarEmpty;
		_menuFlag.items[1].imageName = "menu_flag_green.png";
		_menuFlag.items[1].menuCommand = "onMarkFlagGreen";
		_menuFlag.items[1].submenu = null;
		
		_menuFlag.items[2].itemID = 2;
		_menuFlag.items[2].itemName = "Mark Blue";
		_menuFlag.items[2].imageType = ImageType.kImageBarEmpty;
		_menuFlag.items[2].imageName = "menu_flag_blue.png";
		_menuFlag.items[2].menuCommand = "onMarkFlagBlue";
		_menuFlag.items[2].submenu = null;
		
		_menuFlag.items[3].itemID = 3;
		_menuFlag.items[3].itemName = "Mark Orange";
		_menuFlag.items[3].imageType = ImageType.kImageBarEmpty;
		_menuFlag.items[3].imageName = "menu_flag_orange.png";
		_menuFlag.items[3].menuCommand = "onMarkFlagOrange";
		_menuFlag.items[3].submenu = null;
	}
}
