package net.crazyminds.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.crazyminds.controller.Response;
import net.crazyminds.utilities.CsvFileReader;

public final class Model {
	
	private static Model instance;
	
	List<Hashtable<String, String>> tableList = new ArrayList<Hashtable<String, String>>();
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
		CsvFileReader multiLineFileReader = new CsvFileReader();
		String[] lines = null;
		response = multiLineFileReader.Read(workingFileName);
		if(!response.GetStatus())
			return response;
		lines = (String[]) response.values;
		multiLineFileReader=null;		
		
		propertyNames = lines[0].split(",");
		
		for (int i = 1; i < lines.length; i++)
		{
			String line = lines[i];
			String[] columns = line.split(",");
			
			if(columns.length != propertyNames.length )
			{
				response.setStatus(false);
				response.setMessage("There is an error on line " +i);
				return response;
			}
			
			Hashtable<String, String> lineProperties = new Hashtable<>();
			for (int j = 0; j < propertyNames.length; j++)
			{
				lineProperties.put(propertyNames[j], columns[j]);
			}
			
			tableList.add(lineProperties);
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
	 * @return array string of property names
	 */
	public int GetCountDistinct(String property) {
		
		ArrayList<String> distinctValues = new ArrayList<>();
		for(Hashtable<String, String> line: tableList )
		{
			String value = line.get(property);
			if (!distinctValues.contains(value))
				distinctValues.add(value);
		}
		return distinctValues.size();
	}

}
