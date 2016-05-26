package com.gold.sudoku;

public class TUPLE_LIST_INFO {

	int[] tuple_list;
    int tuple_count;

    TUPLE_LIST_INFO(int[] list, int c)
    {
    	if(c > 0)
    		tuple_list = new int[c];
    	
    	for (int i =  0; i <c; i ++)
    		tuple_list[i] = list[i] ;
    	tuple_count = c;
    }
}
