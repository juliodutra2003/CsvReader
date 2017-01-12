package net.crazyminds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import net.crazyminds.Command.Count;
import net.crazyminds.Command.Filter;
import net.crazyminds.Command.Show;
import net.crazyminds.model.Model;
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
	static Count countCommand = new Count();
	static Show showCommand = new Show();
	static Filter filterCommand = new Filter();
	
	public static void main(String... args) {
		consoleReader = new BufferedReader(new InputStreamReader(System.in));
		 
		Response response = new Response();
		response = Initialize(args);
		if (response.GetStatus())
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
			
			response = Model.getInstance().Initialize(workingFileName);
			
			if (response.GetStatus())
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
            response.setStatus(false);
            response.setMessage("Need a file name as parameter.");
            return response;
		}
	}
	
	static void readConsole() {
		boolean loop_cond=true;
	    String input = "";

	    while(loop_cond == true)
	    {
	        try
	        {
	        	View.ShowCommandPrompt();
	        	input = consoleReader.readLine();
	        	ProcessCommand(input);
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

	private static void ProcessCommand(String input) {
		Response response = new Response();
		String[] commandline = input.split(" ");
		commandline = Sanitize(commandline);
		String command = commandline[0];
		
		if(command == null)
		{	
			response.setStatus(true);
			response.setMessage("");
			View.ShowCommandOkMessage( response );
			return;
		}
		
		switch(command)
		{
			case "help":
			{
				View.ShowHelpMessage();
				break;
			}
			case "quit":
			{
				response.setStatus(true);
				response.setMessage("bye.");
				View.ShowQuitMessage(response);
				System.exit(0);
				break;
			}
			case "show":
			{
				response = showCommand.Interpret(commandline);		
				View.ShowPropertiesMessage( response );
				break;
			}
			case "count":
			{
				response = countCommand.Interpret(commandline);
				View.ShowCountValue( response );
				break;
			}
			case "filter":
			{
				response = filterCommand.Interpret(commandline);
				View.ShowFilterValue( response );
				break;
			}
			default:
				response.setStatus(false);
				response.setMessage(command + " Command not found");
				View.ShowCommandWarningMessage( response );
				break;
		}		
	}
	
	
	/**
	 * Receives raw String[] of command line 
	 *  
	 * @param commandline is the name of the file to read.
	 * 
	 * @return return sanitized string[] line command 
	 */
	static String[] Sanitize(String[] commandline){
		ArrayList<String> sanitizedCommandline = new ArrayList<String>();
		 
		for(String s: commandline)
		{
			if( !s.equals("") && !s.equals(" ") )
			{
				sanitizedCommandline.add(s);
			}
		}
		
		return sanitizedCommandline.toArray(commandline);		
	}
}
