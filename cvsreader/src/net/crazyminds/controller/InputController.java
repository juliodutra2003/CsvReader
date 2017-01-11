package net.crazyminds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import net.crazyminds.utilities.CsvFileReader;
import net.crazyminds.view.View;


/**
 * 
 * @author julio
 *
 * Main Class - Controller layer
 */

public class InputController {
	
	static BufferedReader consoleReader;
	static String workingFileName;
	
	public static String HelpMessage =  "Commands: \n" + 
						   " help (show this help) \n"+
					       " quit (quit program) \n" + 
						   " count * (show the count of registers) \n" +
						   " count disctint (show the total count of distinct values of a property ) \n" +
						   " filter (show all lines where a property has the value) \n";
	
	public static void main(String... args) {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		 
		Response response = new Response();
		response = Initialize(args);
		if (response.Status)
		{
			View.ShowOpenning();	
			readConsole();
		}
		else
		{
			View.ShowFatalError(response);
		}
		
		
	}
	
	static Response Initialize(String[] args )
	{
		Response response = new Response();
		
		if (args != null && args.length > 0 )
		{	
			workingFileName = args[0];
			
			CsvFileReader multiLineFileReader = new CsvFileReader();
			String[] lines = null;
			response = multiLineFileReader.Read(workingFileName, lines);
			multiLineFileReader=null;
			if (response.Status)
			{
				return response;
			}
			else
			{
				return response;
			}
		}
		else
		{			 
            response.Status = false;
            response.Message = "Need a file name as parameter.";
            return response;
		}
	}
	
	static void readConsole() {
		Response workingResponse = new Response();
	    boolean loop_cond=true;
	    String input = "";

	    while(loop_cond == true)
	    {
	        try
	        {
	        	View.ShowCommandPrompt();
	        	input = consoleReader.readLine();
	        	workingResponse =  ProcessCommand(input);
	            if( workingResponse.Status )
	            {
	            	View.ShowCommandOkMessage( workingResponse );
	            }
	            else
	            {
	            	View.ShowCommandWarningMessage( workingResponse );
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }
		
	}
	
	
	/**
	 *
	 * Current Valid Commands:
	 * 
	 *	 help: (show valid commands)
	 *   quit: (quit program)
	 *	 count * (show the total count of registers) 
	 *	 count distinct [property] (show the total count of distinct values of a property )
	 *	 filter [property] [value] (show all lines where a property has the value)
	 *			
	 */

	private static Response ProcessCommand(String input) {
		Response response = new Response();
		String[] commandline = input.split(" ");
		String command = commandline[0];
		
		switch(command)
		{
			case "help":
			{
				response.Status = true;
				response.Message = HelpMessage;
				break;
			}
			case "quit":
			{
				response.Status = true;
				response.Message = command + " Command not found";
				View.ShowQuitMessage(response);
				System.exit(0);
				break;
			}
			case "count":
			{
				response.Status = true;
				response.Message = command + " Command not found";
				break;
			}
			case "filter":
			{
				response.Status = true;
				response.Message = command + " Command not found";
				break;
			}
			default:
				response.Status = false;
				response.Message = command + " Command not found";
				break;
		}
		
		return response;
		
	}
}
