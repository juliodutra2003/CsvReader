package net.crazyminds.view;

import java.util.ArrayList;

import net.crazyminds.controller.Response;
import net.crazyminds.model.FileData;

/**
 *  
 * @author julio
 * 
 * View Layer - Responsible for showing the results in the interface
 * 
 * 
 * Optimizations:
 * No list or objects are explicitly created to not compromise memory.
 * 
 */

public class View {
	
	private static final String helpMessage =  "Commands: \n" + 
			   " help (show this help) \n"+
		       " quit (quit program) \n" + 
		       " show (show all valid properties) \n"+
		       " file (show all lines) \n"+
		       " file count (show a set of lines, if a max count equals to count) \n"+
			   " count * (show the count of registers) \n" +
			   " count disctint [propertyname] (show the total count of [distinct] values of a [property] ) \n" +
			   " filter property [value] (show all lines where a [property] has the [value]) \n";
	
	private static final String prompt =  "> ";
	
	private static final String propertyCaptionMessage =  "Properties: \n" + 
			   											  " -----------------------------------------";
	

	public static void ShowCommandPrompt() {
		
		System.out.print(prompt);	
	}
	
	public static void ShowFatalError(Response<?> response ) {
		System.out.println(response.getMessage());
		System.out.println("Program Terminated.");
	}

	public static void ShowOpenning() {
		System.out.println("Welcome to CsvReader 0.1 SNAPSHOT");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("Author: Julio Dutra");
		System.out.println("juliodutra2003@yahoo.com.br");
		System.out.println("----------------------------------------------------------------------");
		
	}

	
	/**
	 * Default printing method for execution OK.
	 * Prints the Message from response
	 * 
	 * @param response
	 */
	public static void ShowCommandOkMessage(Response<?> response) {
		System.out.println(response.getMessage());
	}

	
	/**
	 * Default printing method for execution ERROR/WARNING.
	 * Prints the Message from response
	 * 
	 * @param response
	 */
	public static void ShowCommandWarningMessage(Response<?> response) {
		System.out.println(response.getMessage());
	}
	
	
	/**
	 * Prints the command HELP info
	 * 
	 * @param response
	 */
	public static void ShowHelpMessage() {
		System.out.println(helpMessage);		
	}
	
	
	/**
	 * Prints quit message after program exit
	 * 
	 * @param response
	 */
	public static void ShowQuitMessage(Response<?> response) {
		System.out.println(response.getMessage());		
	}

	/**
	 * Prints the command SHOW info
	 * 
	 * @param response
	 */
	public static void ShowPropertiesMessage(Response response) {
		System.out.println( propertyCaptionMessage );
		for (String s: (String[])response.getValues())
		{
			System.out.println( s );
		}	
	}
	
	/**
	 * Prints the command FILE info, prints the file
	 * 
	 * @param response
	 */
	public static void ShowFile(Response<?> response) {
		if(response.GetStatus())
		{
			FileData list = (FileData)response.getValues();
			for (String s: list.getPropertyNames())
			{
				if (list.getPropertyNames()[list.getPropertyNames().length-1] == s)
					System.out.println( s);
				else
				{
					System.out.print(s);
				    System.out.print(",");
				}
			}
			
			for (ArrayList<String> line : list.getLines() )
			{
				for(String value: line)
				{
					if (line.get(line.size() - 1) == value)
						System.out.println( value);
					else
					{
						System.out.print( value);
						System.out.print( ",");
					}
				}
			}
		}
		else
		{
			System.out.println(response.getMessage());
		}
		
	}
	
	
	/**
	 * Prints the command COUNT info
	 * 
	 * @param response
	 */
	public static void ShowCountValue(Response<?> response) {
		if(response.GetStatus())
		{
			System.out.println(response.getValues());
		}
		else
		{
			System.out.println(response.getMessage());
		}
		
	}
	
	/**
	 * Prints the command FILTER info
	 * 
	 * @param response
	 */
	public static void ShowFilterValue(Response<?> response) {
		if(response.GetStatus())
		{
			FileData filterValue = (FileData)response.getValues();
			
			for (String s: filterValue.getPropertyNames())
			{
				if (filterValue.getPropertyNames()[filterValue.getPropertyNames().length - 1] == s)
					System.out.println( s);
				else
				{
					System.out.print(s);
					System.out.print(",");
				}
			}
			
			for (ArrayList<String> line : filterValue.getLines() )
			{
				for(String value: line)
				{
					if (line.get(line.size() - 1) == value)
						System.out.println( value);
					else
					{
						System.out.print(value);
						System.out.print(",");
					}
				}
			}
		}
		else
		{
			System.out.println(response.getMessage());
		}
		
	}

}
