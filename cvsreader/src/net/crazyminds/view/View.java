package net.crazyminds.view;

import net.crazyminds.controller.Response;
import net.crazyminds.model.Model;

public class View {
	
	static String helpMessage =  "Commands: \n" + 
			   " help (show this help) \n"+
		       " quit (quit program) \n" + 
		       " show (show all valid properties) \n"+
			   " count * (show the count of registers) \n" +
			   " count disctint (show the total count of distinct values of a property ) \n" +
			   " filter (show all lines where a property has the value) \n";
	

	public static void ShowCommandPrompt() {
		
		System.out.print("> ");	
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
		String message = "Properties: \n";
		message += "----------------------------------------- \n";
		for (String s: (String[])response.getValues())
		{
			message += s + "\n";
		}
		System.out.println(message);		
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

}
