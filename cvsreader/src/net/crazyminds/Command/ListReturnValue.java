package net.crazyminds.Command;

import java.util.ArrayList;
import java.util.Hashtable;

public class ListReturnValue {
	
	private String[] propertyNames;
	private ArrayList<ArrayList<String>> lines;
	
	
	public ListReturnValue(String[] propertyNames, ArrayList<ArrayList<String>> lines)
	{
		this.propertyNames = propertyNames;
		this.lines = lines;
	}


	public String[] getPropertyNames() {
		return propertyNames;
	}


	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}


	public ArrayList<ArrayList<String>> getLines() {
		return lines;
	}


	public void setLines(ArrayList<ArrayList<String>> lines) {
		this.lines = lines;
	}

	
}
