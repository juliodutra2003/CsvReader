package net.crazyminds.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import datasource.DataProvider;
import net.crazyminds.controller.Response;
import net.crazyminds.utilities.CsvFileReader;

public final class Model {
	
	private static Model instance;
	
	ArrayList<ArrayList<String>> tableList = new ArrayList<ArrayList<String>>();
	String[] propertyNames;
	
	private Model()
	{}
	
	//synchronized - not allow more then one instance, by method being called by two different threads.
	public static synchronized Model getInstance()
	{
		if(instance == null)
			instance = new Model();
		return instance;
	}
	
	
	public Response Initialize(String workingFileName)
	{
		Response response = new Response();
		
		DataProvider dataProvider = new DataProvider();
		response = dataProvider.Provide(workingFileName);
		
		if(!response.GetStatus())
			return response;			
		
		ArrayList<ArrayList<String>> lines = (ArrayList<ArrayList<String>>) response.getValues();
		
		ArrayList<String> captionline =  lines.get(0);	
		propertyNames = new String[captionline.size()];
		for (int i = 0; i < captionline.size() ; i++)
		{
			propertyNames[i] = captionline.get(i);
		}
		
		
		for (int i = 1; i < lines.size(); i++)
		{	
			tableList.add(lines.get(i));
		}
		
		response.setStatus(true);
		response.setMessage(response.getMessage() + "\n");
		response.setMessage(response.getMessage() + "Data parsed.");
		return response;
	}

	public int GetTotalCount() {
		return tableList.size();
	}

	public boolean isValidProperty(String name) {
		
		for (String s: propertyNames)
		{
			if (s.equals(name))
				return true;
		}
		
		return false;
	}

	
	/**
	 * Gets all property names from the model
	 * 
	 * @return array string of property names
	 */
	public String[] GetProperties() {		
		return propertyNames;
	}

	/**
	 * Gets the total count of distinct values for the target property
	 * 
	 * @param property the property target to count distinct values 
	 * 
	 * @return array string of property names
	 */
	public int GetCountDistinct(String targetProperty) {
		
		int propertyIndex = GetPropertyCaptionIndex(targetProperty);
		
		ArrayList<String> distinctValues = new ArrayList<>();
		for(ArrayList<String> line: tableList )
		{
			String value = line.get(propertyIndex);
			if (!distinctValues.contains(value))
				distinctValues.add(value);
		}
		return distinctValues.size();
	}
	
	/**
	 * Gets the lines containing the target value as target property
	 * 
	 * @param targetProperty the property target to search 
	 * @param targetValue the target value to compare
	 * 
	 * @return ArrayList<ArrayList<String>> . Each ArrayList represents an ArrayList<String>> (line).
	 */
	public ArrayList<ArrayList<String>>  GetLines(String targetProperty, String targetValue) {
 
		int propertyIndex = GetPropertyCaptionIndex(targetProperty);
		ArrayList<ArrayList<String>> lineArrayList = new ArrayList<ArrayList<String>>();
		
		for(ArrayList<String> line : tableList)
		{
			if (line.get(propertyIndex).equals(targetValue))
				lineArrayList.add(line);
		}

		return lineArrayList;
	}
	
	
	/**
	 * Gets the all lines
	 * 	 * 
	 * @return ArrayList<ArrayList<String>> . Each ArrayList represents an ArrayList<String>> (line).
	 */
	public ArrayList<ArrayList<String>> GetLines() {		
		return tableList;
	}

	
	/**
	 * Gets the all lines
	 * 
	 * @param countParam amount of lines to return
	 * 
	 * @return ArrayList of ArrayList<String> . Each ArrayList<String> represents a line.
	 */
	public ArrayList<ArrayList<String>> GetLines(int countParam) {
		int amount  = (tableList.size() > countParam)?countParam:tableList.size();
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int i = 0 ; i < amount; i++)
		{
			list.add(tableList.get(i));
		}
		
		return list;
	}
	
	
	private int GetPropertyCaptionIndex(String caption)
	{
		for (int i = 0 ; i < propertyNames.length; i++)
		{
			if (propertyNames[i].equals(caption))
				return i;
		}
		
		
		//error
		System.out.println("UNEXPECTED ERROR, contact the developer.");
		return 0;
		
	}

}
