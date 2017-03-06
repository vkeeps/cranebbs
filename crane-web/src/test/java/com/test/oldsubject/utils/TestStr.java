package com.test.oldsubject.utils;

import java.util.HashMap;
import java.util.Map;

/**
* @author  Crane:
* @version 5.0
* @time 2017年3月4日 上午2:21:00
* 
*/
public class TestStr {

	
	public static void main(String[] args) {
		int[] a= {1,2,3};
		String str ="23asdadasdadsadsadawwwsdasd2pzh";
	       Map<String,Integer> maps = new HashMap<String,Integer>();
	       for(int i=0;i<str.length();i++){
	    	   String key = String.valueOf((str.charAt(i)));
	    	   if(!maps.containsKey(key))
	    	       maps.put(key, 1);
	    	   else{
	    		   int val =maps.get(key);
	    		   maps.put(key, val+1);
	    	   }
	    	   
	       }
	       
	       for(@SuppressWarnings("rawtypes")Map.Entry i : maps.entrySet()){
	    	   System.out.println(i.getKey()+ "=="+i.getValue()+"次");
	       }
	}
}
