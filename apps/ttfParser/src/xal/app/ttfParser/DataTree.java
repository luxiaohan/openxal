/*
 * DataTree.java - Creates a data structure for organizing and accessing information about a specified RF gap
 * @author James Ghawaly Jr.
 * Created on Mon June 15 13:23:35 EDT 2015
 *
 * Copyright (c) 2015 Spallation Neutron Source
 * Oak Ridge National Laboratory
 * Oak Ridge, TN 37830
 */

package xal.app.ttfParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataTree {
	// create a new map
	static Map<String,List<String>> map = new HashMap<String, List<String>>();
	public static void main(String[] args){
		
	}
	// This method adds a field to the current map, which matches the gap_id key to the values pertaining to that key
	public void addListToTree(String gapID,List<String> lstStr){
		map.put(gapID,lstStr);
	}
	// This method is used to get a certain value for a certain gap
	// Example: To get the ttf polynomial of MEBT3 RF Gap 1, use dataTree.getValue("MEBT3:Rg1","ttf")
	public String getValue(String key,String valueIdentifier){
		int index = 0;
		List<String> values;
		String value;
		// This switch statement returns the index of the information to fetch
		switch (valueIdentifier) {
			case "primary_sequence": index = 0;
				break;
			case "secondary_sequence": index = 1;
				break;
			case "ttf": index = 2;
				break;
			case "ttfp": index = 3;
				break;
			case "stf": index = 4;
				break;
			case "stfp": index = 5;
				break;
			default: index = -10;
				break;
		}
		// return the value list pertaining to the key
		values = map.get(key);
		// return the individual value pertaining to the valueIdentifier
		value = values.get(index);

		return value;
	}
	
	public ArrayList<String> getGaps() {
		ArrayList keyList = new ArrayList();
		Set keys = map.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();){
			String key = i.next().toString();
			keyList.add(key);
		}
		return keyList;
				
	}
	
}